import numpy as np
from PIL import Image, ImageDraw, ImageFilter
import os, random, math

BASE = r'C:\Users\Piotr\minecraft-mod\src\main\resources\assets\evotech\textures'
BLOCK_DIR = os.path.join(BASE, 'block')
ITEM_DIR = os.path.join(BASE, 'item')
GUI_DIR = os.path.join(BASE, 'gui')
for d in [BLOCK_DIR, ITEM_DIR, GUI_DIR]:
    os.makedirs(d, exist_ok=True)

_perm = np.arange(256, dtype=int)
np.random.seed(42)
np.random.shuffle(_perm)
_perm = np.tile(_perm, 2)

def _fade(t):
    return t * t * t * (t * (t * 6 - 15) + 10)

def _lerp(a, b, t):
    return a + t * (b - a)

def _grad(h, x, y):
    h = h & 3
    if h == 0: return x + y
    if h == 1: return -x + y
    if h == 2: return x - y
    return -x - y

def perlin2d(x, y):
    xi = int(math.floor(x)) & 255
    yi = int(math.floor(y)) & 255
    xf = x - math.floor(x)
    yf = y - math.floor(y)
    u = _fade(xf)
    v = _fade(yf)
    aa = _perm[_perm[xi] + yi]
    ab = _perm[_perm[xi] + yi + 1]
    ba = _perm[_perm[xi + 1] + yi]
    bb = _perm[_perm[xi + 1] + yi + 1]
    x1 = _lerp(_grad(aa, xf, yf), _grad(ba, xf - 1, yf), u)
    x2 = _lerp(_grad(ab, xf, yf - 1), _grad(bb, xf - 1, yf - 1), u)
    return _lerp(x1, x2, v)

def fbm(x, y, octaves=4, lac=2.0, gain=0.5):
    val, amp, freq = 0.0, 1.0, 1.0
    for _ in range(octaves):
        val += amp * perlin2d(x * freq, y * freq)
        amp *= gain; freq *= lac
    return val

def noise_array(w, h, scale=8.0, seed=0):
    arr = np.zeros((h, w), dtype=np.float64)
    for y in range(h):
        for x in range(w):
            arr[y, x] = fbm(x / scale + seed, y / scale + seed, 4)
    rng = arr.max() - arr.min() + 1e-10
    return (arr - arr.min()) / rng

def save(arr, path):
    arr = np.clip(arr, 0, 255).astype(np.uint8)
    if arr.ndim == 2:
        arr = np.stack([arr, arr, arr, np.full_like(arr, 255)], axis=-1)
    elif arr.shape[2] == 3:
        alpha = np.full((arr.shape[0], arr.shape[1], 1), 255, dtype=np.uint8)
        arr = np.concatenate([arr, alpha], axis=2)
    Image.fromarray(arr, 'RGBA').save(path)

def color_arr(w, h, color):
    arr = np.zeros((h, w, 4), dtype=np.uint8)
    arr[:, :, 0] = color[0]; arr[:, :, 1] = color[1]; arr[:, :, 2] = color[2]; arr[:, :, 3] = 255
    return arr

def darken(c, a): return tuple(max(0, v - a) for v in c[:3])
def lighten(c, a): return tuple(min(255, v + a) for v in c[:3])
def lerp_c(c1, c2, t):
    t = max(0.0, min(1.0, t))
    return tuple(int(c1[i] + (c2[i] - c1[i]) * t) for i in range(3))

def make_stone(seed=0, dark=False):
    n1 = noise_array(16, 16, 6.0, seed)
    n2 = noise_array(16, 16, 3.0, seed + 100)
    n3 = noise_array(16, 16, 12.0, seed + 200)
    if dark:
        base, base2 = (55, 54, 52), (40, 40, 42)
    else:
        base, base2 = (120, 119, 116), (95, 95, 95)
    arr = color_arr(16, 16, base)
    for y in range(16):
        for x in range(16):
            t = n1[y, x] * 0.6 + n2[y, x] * 0.25 + n3[y, x] * 0.15
            arr[y, x, :3] = lerp_c(base2, base, t)
    return arr
ORE_DATA = {
    'tin': ((180, 120, 60), (140, 90, 40)),
    'lead': ((120, 125, 150), (90, 95, 120)),
    'silver': ((210, 210, 220), (180, 180, 195)),
    'aluminum': ((180, 185, 195), (150, 155, 165)),
    'nickel': ((180, 180, 100), (150, 150, 75)),
    'zinc': ((140, 160, 200), (110, 130, 170)),
}

def gen_ore_textures():
    print("Generating ore textures...")
    for name, (mc, md) in ORE_DATA.items():
        arr = make_stone(10, False)
        n = noise_array(16, 16, 2.5, hash(name) % 10000)
        random.seed(hash(name) % 10000 + 999)
        for y in range(16):
            for x in range(16):
                if n[y, x] > 0.65 and random.random() > 0.3:
                    arr[y, x, :3] = md if n[y, x] > 0.78 else mc
        save(arr, os.path.join(BLOCK_DIR, f'{name}_ore.png'))
        arr2 = make_stone(10, True)
        for y in range(16):
            for x in range(16):
                if n[y, x] > 0.65 and random.random() > 0.3:
                    arr2[y, x, :3] = md if n[y, x] > 0.78 else mc
        save(arr2, os.path.join(BLOCK_DIR, f'deepslate_{name}_ore.png'))
        print(f"  {name}_ore, deepslate_{name}_ore")

METAL_BLOCK_DATA = {
    'tin_block': ((165, 170, 180), (120, 125, 135), (195, 200, 210)),
    'bronze_block': ((170, 120, 60), (120, 80, 35), (200, 150, 90)),
    'lead_block': ((85, 88, 100), (55, 58, 70), (115, 118, 130)),
    'steel_block': ((145, 148, 158), (105, 108, 118), (180, 183, 193)),
    'silver_block': ((200, 200, 210), (165, 165, 180), (225, 225, 235)),
    'aluminum_block': ((185, 190, 200), (150, 155, 165), (210, 215, 225)),
    'nickel_block': ((160, 155, 145), (120, 115, 105), (190, 185, 175)),
    'zinc_block': ((140, 150, 170), (105, 115, 135), (170, 180, 200)),
    'electrum_block': ((200, 180, 100), (160, 140, 65), (230, 210, 135)),
    'constantan_block': ((170, 110, 85), (125, 75, 55), (200, 140, 115)),
}

def gen_metal_blocks():
    print("Generating metal blocks...")
    for i, (name, (c, cd, cl)) in enumerate(METAL_BLOCK_DATA.items()):
        arr = color_arr(16, 16, c)
        n = noise_array(16, 16, 10.0, 200 + i)
        for y in range(16):
            for x in range(16):
                arr[y, x, :3] = lerp_c(cd, cl, n[y, x])
        for x in range(16):
            arr[0, x, :3] = cl; arr[1, x, :3] = lerp_c(c, cl, 0.3)
            arr[14, x, :3] = lerp_c(c, cd, 0.3); arr[15, x, :3] = cd
        for y in range(16):
            arr[y, 0, :3] = cl; arr[y, 1, :3] = lerp_c(c, cl, 0.3)
            arr[y, 14, :3] = lerp_c(c, cd, 0.3); arr[y, 15, :3] = cd
        arr[0, 0, :3] = lighten(cl, 15); arr[15, 15, :3] = darken(cd, 15)
        save(arr, os.path.join(BLOCK_DIR, f'{name}.png'))
        print(f"  {name}")
MACHINE_DATA = [
    ('macerator', (100, 100, 105), (120, 110, 100), (160, 160, 165), 'macerator'),
    ('generator', (110, 110, 115), (80, 80, 85), (200, 180, 40), 'generator'),
    ('compressor', (105, 108, 115), (90, 90, 95), (140, 140, 145), 'compressor'),
    ('alloy_smelter', (95, 90, 88), (80, 75, 73), (200, 100, 40), 'alloy_smelter'),
    ('electric_furnace', (100, 100, 105), (80, 40, 30), (200, 100, 40), 'electric_furnace'),
    ('machine_frame', (130, 133, 140), (100, 100, 105), (80, 80, 85), 'machine_frame'),
    ('copper_coil', (80, 80, 85), (180, 100, 40), (200, 120, 60), 'copper_coil'),
    ('crushing_table', (140, 100, 50), (100, 100, 105), (90, 90, 95), 'crushing_table'),
]

def gen_machine_textures():
    print("Generating machine textures...")
    for name, bc, dc, ac, var in MACHINE_DATA:
        arr = color_arr(16, 16, bc)
        n = noise_array(16, 16, 8.0, hash(name) % 10000)
        for y in range(16):
            for x in range(16):
                arr[y, x, :3] = lerp_c(darken(bc, 15), bc, 0.5 + n[y, x] * 0.15)
        dc2, lc = darken(bc, 25), lighten(bc, 20)
        for x in range(16):
            arr[0, x, :3] = lc; arr[1, x, :3] = darken(bc, 5)
            arr[14, x, :3] = bc; arr[15, x, :3] = dc2
        for y in range(16):
            arr[y, 0, :3] = lc; arr[y, 1, :3] = darken(bc, 5)
            arr[y, 14, :3] = bc; arr[y, 15, :3] = dc2

        if var == 'macerator':
            for x in range(5, 11):
                for y in range(5, 11):
                    d = math.sqrt((x-8)**2 + (y-8)**2)
                    if d < 4: arr[y, x, :3] = dc
                    if d < 2: arr[y, x, :3] = lighten(dc, 30)
        elif var == 'generator':
            for x in range(3, 13):
                arr[3, x, :3] = ac; arr[12, x, :3] = ac
            for y in range(3, 13):
                arr[y, 3, :3] = ac; arr[y, 12, :3] = ac
            for x in range(5, 11):
                for y in range(5, 11): arr[y, x, :3] = dc
        elif var == 'compressor':
            for x in range(4, 12):
                arr[6, x, :3] = dc2; arr[9, x, :3] = dc2
            for x in range(6, 10):
                for y in range(7, 9): arr[y, x, :3] = ac
        elif var == 'alloy_smelter':
            for x in range(4, 12):
                for y in range(9, 13): arr[y, x, :3] = dc
            for x in range(5, 11):
                arr[10, x, :3] = ac
                arr[11, x, :3] = (255, 120, 30)
        elif var == 'electric_furnace':
            for x in range(3, 13):
                arr[4, x, :3] = ac; arr[11, x, :3] = ac
            for y in range(5, 11):
                arr[y, 3, :3] = ac; arr[y, 12, :3] = ac
                for x in range(4, 12): arr[y, x, :3] = (80, 40, 30)
                for x in range(5, 11, 2): arr[y, x, :3] = (200, 100, 40)
        elif var == 'machine_frame':
            fc = bc
            for x in range(16):
                arr[0, x, :3] = lighten(fc, 20); arr[15, x, :3] = darken(fc, 20)
                arr[7, x, :3] = darken(fc, 10); arr[8, x, :3] = darken(fc, 10)
            for y in range(16):
                arr[y, 0, :3] = lighten(fc, 20); arr[y, 15, :3] = darken(fc, 20)
                arr[y, 7, :3] = darken(fc, 10); arr[y, 8, :3] = darken(fc, 10)
            for y in range(0, 8):
                for x in range(1, 7): arr[y, x] = [0,0,0,0]
                for x in range(9, 15): arr[y, x] = [0,0,0,0]
            for y in range(9, 16):
                for x in range(1, 7): arr[y, x] = [0,0,0,0]
                for x in range(9, 15): arr[y, x] = [0,0,0,0]
        elif var == 'copper_coil':
            for y in range(3, 13):
                for x in range(3, 13):
                    d = math.sqrt((x-8)**2 + (y-8)**2)
                    if 2 < d < 5: arr[y, x, :3] = (180, 100, 40)
                    elif d <= 2: arr[y, x, :3] = (200, 120, 60)
        elif var == 'crushing_table':
            for y in range(0, 6):
                for x in range(16):
                    arr[y, x, :3] = lerp_c((140, 100, 50), (170, 120, 65), n[y, x])
            for x in range(0, 16, 4):
                for y in range(6, 16):
                    arr[y, x, :3] = (100, 100, 105)

        save(arr, os.path.join(BLOCK_DIR, f'{name}.png'))
        print(f"  {name}")
TOOL_COLORS = {
    'flint': ((130, 120, 100), (90, 85, 75)),
    'copper': ((180, 110, 60), (140, 80, 40)),
    'bronze': ((170, 120, 60), (130, 85, 35)),
    'steel': ((160, 163, 170), (120, 123, 130)),
}

SHAPES = {
    'pickaxe': [
        "................",
        "..............##",
        ".............#..",
        "............#...",
        "...#.......#....",
        "..##......#.....",
        ".#..#....#......",
        "#....#..#.......",
        "......###.......",
        ".......#........",
        ".......#........",
        ".......#........",
        ".......#........",
        ".......#........",
        ".......#........",
        ".......#........",
    ],
    'axe': [
        "................",
        ".............##.",
        "............##..",
        "...........##...",
        "...#......##....",
        "..##.....##.....",
        ".#..#...##......",
        "#....#.##.......",
        "......##........",
        "......#.........",
        "......#.........",
        "......#.........",
        "......#.........",
        "......#.........",
        "......#.........",
        "......#.........",
    ],
    'shovel': [
        "................",
        "................",
        "................",
        ".......#........",
        ".......##.......",
        ".......###......",
        ".......###......",
        ".......###......",
        "........#.......",
        "........#.......",
        "........#.......",
        "........#.......",
        "........#.......",
        "........#.......",
        "........#.......",
        "........#.......",
    ],
    'hoe': [
        "................",
        "................",
        "................",
        "................",
        "........########",
        ".......#........",
        "......#.........",
        ".....#..........",
        "....#...........",
        "...#............",
        "..#.............",
        "..#.............",
        "..#.............",
        "..#.............",
        "..#.............",
        "..#.............",
    ],
    'sword': [
        "................",
        "..............#.",
        "..............#.",
        ".............#..",
        ".............#..",
        "............#...",
        "............#...",
        "...........#....",
        "..........#.....",
        ".........#......",
        ".......##.......",
        "......#.........",
        "......#.........",
        "......#.........",
        "......#.........",
        "......#.........",
    ],
}

def make_tool(tool_type, tier_color, seed=0):
    shape = SHAPES[tool_type]
    arr = np.zeros((16, 16, 4), dtype=np.uint8)
    handle = (101, 67, 33)
    for y, row in enumerate(shape):
        for x, ch in enumerate(row):
            if ch == '#':
                if tool_type == 'sword':
                    arr[y, x, :3] = tier_color if y <= 5 else (170,170,175) if y <= 7 else handle
                elif tool_type == 'shovel':
                    arr[y, x, :3] = tier_color if y <= 4 else (170,170,175) if y <= 5 else handle
                else:
                    arr[y, x, :3] = tier_color if (y <= 2 or (y <= 4 and x >= 8)) else (170,170,175) if y <= 3 else handle
                arr[y, x, 3] = 255
    n = noise_array(16, 16, 3.0, seed)
    for y in range(16):
        for x in range(16):
            if arr[y, x, 3] > 0:
                bright = int((n[y, x] - 0.5) * 20)
                arr[y, x, :3] = np.clip(arr[y, x, :3].astype(int) + bright, 0, 255).astype(np.uint8)
    return arr

def gen_tool_textures():
    print("Generating tool textures...")
    for tier, (tc, td) in TOOL_COLORS.items():
        for tool in SHAPES:
            arr = make_tool(tool, tc, seed=hash(f"{tier}_{tool}") % 10000)
            save(arr, os.path.join(ITEM_DIR, f'{tier}_{tool}.png'))
        print(f"  {tier} tools")

def make_hammer(tier_color, seed=0):
    arr = np.zeros((16, 16, 4), dtype=np.uint8)
    handle = (101, 67, 33)
    for y in range(16):
        for x in range(16):
            if 10 <= y <= 15 and 7 <= x <= 8:
                arr[y, x, :3] = handle; arr[y, x, 3] = 255
            if 10 <= y <= 11 and 6 <= x <= 9:
                arr[y, x, :3] = (170, 170, 175); arr[y, x, 3] = 255
            if 3 <= y <= 9 and 3 <= x <= 12:
                arr[y, x, :3] = tier_color; arr[y, x, 3] = 255
            if y == 3 and 3 <= x <= 12: arr[y, x, :3] = lighten(tier_color, 30)
            if y == 9 and 3 <= x <= 12: arr[y, x, :3] = darken(tier_color, 25)
            if x == 3 and 3 <= y <= 9: arr[y, x, :3] = lighten(tier_color, 20)
            if x == 12 and 3 <= y <= 9: arr[y, x, :3] = darken(tier_color, 15)
    return arr

def gen_hammer_textures():
    print("Generating hammer textures...")
    for name, c in [('stone_hammer', (140,140,140)), ('iron_hammer', (190,190,195)), ('steel_hammer', (160,163,170))]:
        save(make_hammer(c, hash(name) % 10000), os.path.join(ITEM_DIR, f'{name}.png'))
        print(f"  {name}")
ARMOR_COLORS = {
    'copper': ((180, 110, 60), (140, 80, 40)),
    'bronze': ((170, 120, 60), (130, 85, 35)),
    'steel': ((160, 163, 170), (120, 123, 130)),
}
ARMOR_SHAPES = {
    'helmet': [
        "................","................","................",
        "....########....","...#........#...","..#..........#..",
        "..#..........#..",".#............#.",".#............#.",
        ".#............#.",".#..#......#..#.",".#..#......#..#.",
        "..##........##..","................","................","................",
    ],
    'chestplate': [
        "................","................",".#............#.",
        ".##..........##.",".##..........##.",".###..####..###.",
        ".###..####..###.",".###..####..###.",".###..####..###.",
        ".##....##....##.",".##....##....##.",".#......#......#.",
        ".#......#......#.","................","................","................",
    ],
    'leggings': [
        "................","................",".#............#.",
        ".##..........##.",".##..........##.",".##..........##.",
        ".##..........##.",".##..........##.",".##..........##.",
        ".##..........##.","..#..........#..","..#..........#..",
        "..#..........#..","..#..........#..","..#..........#..","................",
    ],
    'boots': [
        "................","................","................","................",
        "................","................","................",
        "..#..........#..","..#..........#..","..#..........#..",
        "..##........##..","..###......###..","..###########...",
        "..###########...","................","................",
    ],
}

def gen_armor_textures():
    print("Generating armor textures...")
    for tier, (tc, td) in ARMOR_COLORS.items():
        for atype, shape in ARMOR_SHAPES.items():
            arr = np.zeros((16, 16, 4), dtype=np.uint8)
            for y, row in enumerate(shape):
                for x, ch in enumerate(row):
                    if ch == '#':
                        n = perlin2d(x * 0.5 + hash(f"{tier}_{atype}") % 10000, y * 0.5) * 15
                        c = np.clip(np.array(tc, dtype=float) + n, 0, 255).astype(int)
                        arr[y, x, :3] = c; arr[y, x, 3] = 255
            save(arr, os.path.join(ITEM_DIR, f'{tier}_{atype}.png'))
        print(f"  {tier} armor")

INGOT_COLORS = {
    'tin_ingot': ((175, 175, 185), (135, 135, 145), (200, 200, 210)),
    'bronze_ingot': ((180, 130, 60), (140, 95, 35), (210, 160, 90)),
    'lead_ingot': ((95, 98, 110), (65, 68, 80), (125, 128, 140)),
    'steel_ingot': ((155, 158, 168), (115, 118, 128), (185, 188, 198)),
    'silver_ingot': ((210, 210, 220), (175, 175, 190), (230, 230, 240)),
    'aluminum_ingot': ((195, 200, 210), (160, 165, 175), (215, 220, 230)),
    'nickel_ingot': ((170, 165, 155), (130, 125, 115), (200, 195, 185)),
    'zinc_ingot': ((150, 160, 180), (115, 125, 145), (180, 190, 210)),
    'electrum_ingot': ((210, 190, 100), (170, 150, 65), (240, 220, 135)),
    'constantan_ingot': ((180, 120, 90), (140, 85, 60), (210, 150, 120)),
}

def make_ingot(cm, cd, cl, seed=0):
    arr = np.zeros((16, 16, 4), dtype=np.uint8)
    n = noise_array(16, 16, 5.0, seed)
    for y in range(16):
        for x in range(16):
            if 4 <= y <= 12:
                le = max(0, 2 + (y - 4) // 2)
                re = min(16, 13 - (y - 4) // 2)
                if le <= x <= re:
                    t = n[y, x] * 0.3
                    if y <= 5: arr[y, x, :3] = lerp_c(cl, cm, 0.3 + t)
                    elif y <= 9: arr[y, x, :3] = lerp_c(cm, cd, t)
                    else: arr[y, x, :3] = lerp_c(cd, darken(cd, 20), t)
                    arr[y, x, 3] = 255
    return arr

def gen_ingot_textures():
    print("Generating ingot textures...")
    for name, (cm, cd, cl) in INGOT_COLORS.items():
        save(make_ingot(cm, cd, cl, hash(name) % 10000), os.path.join(ITEM_DIR, f'{name}.png'))
    print(f"  {len(INGOT_COLORS)} ingots")

def gen_nugget_textures():
    print("Generating nugget textures...")
    for name, (cm, cd, cl) in INGOT_COLORS.items():
        nn = name.replace('_ingot', '_nugget')
        arr = np.zeros((16, 16, 4), dtype=np.uint8)
        n = noise_array(16, 16, 4.0, hash(nn) % 10000)
        for y in range(16):
            for x in range(16):
                d = math.sqrt((x-8)**2 + (y-8)**2)
                if d < 4:
                    t = n[y, x] * 0.3
                    arr[y, x, :3] = lerp_c(cm, cd, 0.3 + t) if d >= 2 else lerp_c(cl, cm, 0.4 + t)
                    arr[y, x, 3] = 255
        save(arr, os.path.join(ITEM_DIR, f'{nn}.png'))
    print(f"  {len(INGOT_COLORS)} nuggets")
DUST_COLORS = {
    'dust_tin': ((180, 180, 190), (145, 145, 155)),
    'dust_lead': ((100, 103, 115), (70, 73, 85)),
    'dust_silver': ((200, 200, 215), (170, 170, 185)),
    'dust_iron': ((185, 175, 165), (150, 140, 130)),
    'dust_gold': ((210, 190, 100), (175, 155, 65)),
    'dust_aluminum': ((190, 195, 205), (155, 160, 170)),
    'dust_nickel': ((175, 170, 160), (140, 135, 125)),
    'dust_zinc': ((150, 160, 180), (115, 125, 145)),
}

def gen_dust_textures():
    print("Generating dust textures...")
    random.seed(42)
    for name, (c, cd) in DUST_COLORS.items():
        arr = np.zeros((16, 16, 4), dtype=np.uint8)
        n = noise_array(16, 16, 3.0, hash(name) % 10000)
        for y in range(16):
            for x in range(16):
                d = math.sqrt((x-8)**2 + (y-7)**2)
                if d < 5.5 and n[y, x] > 0.3:
                    alpha = int(255 * max(0, 1 - d / 6.0) * min(1, n[y, x] * 2))
                    if alpha > 30:
                        arr[y, x, :3] = lerp_c(cd, c, n[y, x])
                        arr[y, x, 3] = min(255, alpha)
        save(arr, os.path.join(ITEM_DIR, f'{name}.png'))
    print(f"  {len(DUST_COLORS)} dusts")

CRUSHED_COLORS = {
    'crushed_tin': ((170, 130, 70), (130, 95, 45)),
    'crushed_lead': ((110, 115, 135), (80, 85, 110)),
    'crushed_silver': ((195, 195, 210), (165, 165, 180)),
    'crushed_aluminum': ((175, 180, 190), (145, 150, 160)),
    'crushed_nickel': ((170, 170, 100), (140, 140, 75)),
    'crushed_zinc': ((135, 150, 185), (105, 120, 155)),
}

def gen_crushed_textures():
    print("Generating crushed ore textures...")
    for name, (c, cd) in CRUSHED_COLORS.items():
        arr = np.zeros((16, 16, 4), dtype=np.uint8)
        n = noise_array(16, 16, 2.5, hash(name) % 10000)
        random.seed(hash(name) % 10000 + 500)
        for y in range(16):
            for x in range(16):
                if n[y, x] > 0.4:
                    sz = random.randint(1, 3)
                    for dy in range(sz):
                        for dx in range(sz):
                            yy, xx = y + dy, x + dx
                            if 0 <= yy < 16 and 0 <= xx < 16 and n[yy, xx] > 0.35:
                                arr[yy, xx, :3] = lerp_c(cd, c, n[yy, xx])
                                arr[yy, xx, 3] = 255
        save(arr, os.path.join(ITEM_DIR, f'{name}.png'))
    print(f"  {len(CRUSHED_COLORS)} crushed")

GEAR_COLORS = {
    'gear_copper': ((180, 110, 60), (140, 80, 40)),
    'gear_tin': ((170, 170, 180), (130, 130, 140)),
    'gear_bronze': ((170, 120, 60), (130, 85, 35)),
    'gear_iron': ((190, 190, 195), (150, 150, 155)),
    'gear_steel': ((160, 163, 170), (120, 123, 130)),
    'gear_aluminum': ((190, 195, 205), (155, 160, 170)),
}

def gen_gear_textures():
    print("Generating gear textures...")
    for name, (c, cd) in GEAR_COLORS.items():
        arr = np.zeros((16, 16, 4), dtype=np.uint8)
        cx, cy = 8, 8
        for y in range(16):
            for x in range(16):
                d = math.sqrt((x-cx)**2 + (y-cy)**2)
                if d < 6:
                    angle = math.atan2(y - cy, x - cx)
                    teeth = abs(math.sin(angle * 4))
                    if d < 1.5:
                        arr[y, x, :3] = cd; arr[y, x, 3] = 255
                    elif d < 3:
                        arr[y, x, :3] = c; arr[y, x, 3] = 255
                    elif d < 4.5 and teeth > 0.5:
                        arr[y, x, :3] = c; arr[y, x, 3] = 255
                    elif d < 4.5:
                        arr[y, x, :3] = cd; arr[y, x, 3] = 255
                    elif d < 5.5 and teeth > 0.6:
                        arr[y, x, :3] = lighten(c, 20); arr[y, x, 3] = 255
        save(arr, os.path.join(ITEM_DIR, f'{name}.png'))
    print(f"  {len(GEAR_COLORS)} gears")

PLATE_COLORS = {
    'plate_copper': ((180, 110, 60), (140, 80, 40), (210, 140, 90)),
    'plate_tin': ((170, 170, 180), (130, 130, 140), (200, 200, 210)),
    'plate_bronze': ((170, 120, 60), (130, 85, 35), (200, 150, 90)),
    'plate_iron': ((190, 190, 195), (150, 150, 155), (215, 215, 220)),
    'plate_steel': ((160, 163, 170), (120, 123, 130), (190, 193, 200)),
    'plate_aluminum': ((190, 195, 205), (155, 160, 170), (215, 220, 230)),
    'plate_nickel': ((170, 165, 155), (130, 125, 115), (200, 195, 185)),
}

def gen_plate_textures():
    print("Generating plate textures...")
    for name, (c, cd, cl) in PLATE_COLORS.items():
        arr = color_arr(16, 16, c)
        n = noise_array(16, 16, 8.0, hash(name) % 10000)
        for y in range(2, 14):
            for x in range(2, 14):
                arr[y, x, :3] = lerp_c(cd, cl, n[y, x] * 0.3 + 0.3)
        for x in range(2, 14):
            arr[2, x, :3] = cl; arr[13, x, :3] = cd
        for y in range(2, 14):
            arr[y, 2, :3] = cl; arr[y, 13, :3] = cd
        save(arr, os.path.join(ITEM_DIR, f'{name}.png'))
    print(f"  {len(PLATE_COLORS)} plates")

ROD_COLORS = {
    'rod_copper': ((180, 110, 60), (140, 80, 40)),
    'rod_iron': ((190, 190, 195), (150, 150, 155)),
    'rod_steel': ((160, 163, 170), (120, 123, 130)),
    'rod_bronze': ((170, 120, 60), (130, 85, 35)),
    'rod_aluminum': ((190, 195, 205), (155, 160, 170)),
}

def gen_rod_textures():
    print("Generating rod textures...")
    for name, (c, cd) in ROD_COLORS.items():
        arr = np.zeros((16, 16, 4), dtype=np.uint8)
        for y in range(1, 15):
            arr[y, 7, :3] = c; arr[y, 8, :3] = c
            arr[y, 6, :3] = cd; arr[y, 9, :3] = cd
            arr[y, 7, 3] = 255; arr[y, 8, 3] = 255
            arr[y, 6, 3] = 255; arr[y, 9, 3] = 255
        arr[0, 7, :3] = cd; arr[0, 8, :3] = cd; arr[0, 7, 3] = 255; arr[0, 8, 3] = 255
        arr[15, 7, :3] = cd; arr[15, 8, :3] = cd; arr[15, 7, 3] = 255; arr[15, 8, 3] = 255
        save(arr, os.path.join(ITEM_DIR, f'{name}.png'))
    print(f"  {len(ROD_COLORS)} rods")
def gen_electronics_textures():
    print("Generating electronics textures...")
    arr = color_arr(16, 16, (40, 120, 50))
    for x in range(2, 14):
        for y in range(2, 14):
            arr[y, x, :3] = (50, 140, 60)
    for x in [3, 5, 7, 9, 11]:
        for y in range(3, 13):
            arr[y, x, :3] = (180, 180, 60)
    for y in [3, 6, 9, 12]:
        for x in range(3, 13):
            arr[y, x, :3] = (180, 180, 60)
    save(arr, os.path.join(ITEM_DIR, 'basic_circuit.png'))

    arr2 = color_arr(16, 16, (120, 40, 40))
    for x in range(2, 14):
        for y in range(2, 14):
            arr2[y, x, :3] = (140, 50, 50)
    for x in [3, 5, 7, 9, 11]:
        for y in range(3, 13):
            arr2[y, x, :3] = (220, 200, 60)
    for y in [3, 6, 9, 12]:
        for x in range(3, 13):
            arr2[y, x, :3] = (220, 200, 60)
    save(arr2, os.path.join(ITEM_DIR, 'advanced_circuit.png'))
    print("  basic_circuit, advanced_circuit")

def gen_materials_textures():
    print("Generating material textures...")
    arr = np.zeros((16, 16, 4), dtype=np.uint8)
    n = noise_array(16, 16, 3.0, 1200)
    for y in range(3, 13):
        for x in range(3, 13):
            d = math.sqrt((x-8)**2 + (y-8)**2)
            if d < 5:
                arr[y, x, :3] = lerp_c((40, 40, 45), (20, 20, 25), n[y, x])
                arr[y, x, 3] = 255
    save(arr, os.path.join(ITEM_DIR, 'coal_coke.png'))

    arr2 = np.zeros((16, 16, 4), dtype=np.uint8)
    n2 = noise_array(16, 16, 3.0, 1201)
    for y in range(3, 13):
        for x in range(3, 13):
            d = math.sqrt((x-8)**2 + (y-8)**2)
            if d < 5:
                arr2[y, x, :3] = lerp_c((80, 50, 30), (50, 30, 15), n2[y, x])
                arr2[y, x, 3] = 255
    save(arr2, os.path.join(ITEM_DIR, 'biochar.png'))

    arr3 = color_arr(16, 16, (140, 150, 170))
    n3 = noise_array(16, 16, 10.0, 1202)
    for y in range(4, 12):
        for x in range(4, 12):
            arr3[y, x, :3] = lerp_c((120, 130, 150), (160, 170, 190), n3[y, x])
    save(arr3, os.path.join(ITEM_DIR, 'silicon_wafer.png'))

    arr4 = np.zeros((16, 16, 4), dtype=np.uint8)
    for y in range(16):
        for x in range(16):
            d = math.sqrt((x-8)**2 + (y-8)**2)
            if 2 < d < 3:
                arr4[y, x, :3] = (180, 110, 60); arr4[y, x, 3] = 255
            elif 4 < d < 5:
                arr4[y, x, :3] = (160, 95, 50); arr4[y, x, 3] = 255
    save(arr4, os.path.join(ITEM_DIR, 'copper_wire.png'))

    arr5 = np.zeros((16, 16, 4), dtype=np.uint8)
    for y in range(16):
        for x in range(16):
            d = math.sqrt((x-8)**2 + (y-8)**2)
            if 2 < d < 3:
                arr5[y, x, :3] = (60, 60, 60); arr5[y, x, 3] = 255
            elif 4 < d < 5:
                arr5[y, x, :3] = (50, 50, 50); arr5[y, x, 3] = 255
            elif 3 <= d <= 4:
                arr5[y, x, :3] = (160, 100, 50); arr5[y, x, 3] = 255
    save(arr5, os.path.join(ITEM_DIR, 'insulated_copper_wire.png'))

    arr6 = np.zeros((16, 16, 4), dtype=np.uint8)
    for y in range(1, 15):
        for x in range(1, 15):
            arr6[y, x, :3] = (139, 90, 43); arr6[y, x, 3] = 255
    for y in range(3, 13):
        for x in range(3, 13):
            arr6[y, x, :3] = (240, 235, 220)
    for x in [5, 7, 9]:
        arr6[x, 5, :3] = (60, 40, 20); arr6[x, 7, :3] = (60, 40, 20)
    cx, cy = 8, 9
    for y in range(16):
        for x in range(16):
            d = math.sqrt((x-cx)**2 + (y-cy)**2)
            if d < 2.5:
                angle = math.atan2(y - cy, x - cx)
                teeth = abs(math.sin(angle * 4))
                if teeth > 0.6: arr6[y, x, :3] = (160, 160, 165)
                else: arr6[y, x, :3] = (120, 120, 125)
            elif d < 3.5:
                arr6[y, x, :3] = (120, 120, 125)
    save(arr6, os.path.join(ITEM_DIR, 'engineering_guide.png'))
    print("  coal_coke, biochar, silicon_wafer, copper_wire, insulated_copper_wire, engineering_guide")
def draw_slot(arr, sx, sy):
    for x in range(sx, sx + 16):
        arr[sy, x, :3] = (50, 50, 50); arr[sy, x, 3] = 255
        arr[sy + 15, x, :3] = (100, 100, 100); arr[sy + 15, x, 3] = 255
    for y in range(sy, sy + 16):
        arr[y, sx, :3] = (50, 50, 50); arr[y, sx, 3] = 255
        arr[y, sx + 15, :3] = (100, 100, 100); arr[y, sx + 15, 3] = 255
    for x in range(sx + 1, sx + 15):
        for y in range(sy + 1, sy + 15):
            arr[y, x, :3] = (130, 130, 130); arr[y, x, 3] = 255

def draw_arrow(arr, ax, ay):
    for x in range(ax, ax + 22):
        arr[ay, x, :3] = (100, 100, 100); arr[ay, x, 3] = 255
        arr[ay + 7, x, :3] = (100, 100, 100); arr[ay + 7, x, 3] = 255
    for y in range(ay, ay + 8):
        arr[y, ax, :3] = (100, 100, 100); arr[y, ax, 3] = 255
    arr[ay + 3, ax + 22, :3] = (100, 100, 100); arr[ay + 3, ax + 22, 3] = 255
    arr[ay + 4, ax + 23, :3] = (100, 100, 100); arr[ay + 4, ax + 23, 3] = 255

def make_gui(slots, arrows=None, extra_slots=None):
    arr = color_arr(176, 166, (198, 198, 198))
    for x in range(176):
        arr[0, x, :3] = (85, 85, 85); arr[165, x, :3] = (50, 50, 50)
    for y in range(166):
        arr[y, 0, :3] = (85, 85, 85); arr[y, 175, :3] = (50, 50, 50)
    for sx, sy in slots:
        draw_slot(arr, sx, sy)
    if arrows:
        for ax, ay in arrows:
            draw_arrow(arr, ax, ay)
    if extra_slots:
        for ex, ey, ew, eh, color in extra_slots:
            for y in range(ey, ey + eh):
                for x in range(ex, ex + ew):
                    arr[y, x, :3] = color; arr[y, x, 3] = 255
    return arr

def gen_gui_textures():
    print("Generating GUI textures...")
    arr = make_gui([(56, 17), (56, 35), (116, 26)], [(79, 26)])
    save(arr, os.path.join(GUI_DIR, 'alloy_smelter_gui.png'))
    arr = make_gui([(56, 17), (116, 26)], [(79, 26)])
    save(arr, os.path.join(GUI_DIR, 'electric_furnace_gui.png'))
    arr = make_gui([(56, 35), (116, 35)], [(79, 35)])
    save(arr, os.path.join(GUI_DIR, 'macerator_gui.png'))
    arr = make_gui([(56, 35)], None, [(73, 36, 1, 12, (255, 160, 40))])
    save(arr, os.path.join(GUI_DIR, 'generator_gui.png'))
    arr = make_gui([(56, 35), (116, 35)], [(79, 35)])
    save(arr, os.path.join(GUI_DIR, 'compressor_gui.png'))

    arr2 = color_arr(176, 166, (198, 198, 198))
    for x in range(176):
        arr2[0, x, :3] = (85, 85, 85); arr2[165, x, :3] = (50, 50, 50)
    for y in range(166):
        arr2[y, 0, :3] = (85, 85, 85); arr2[y, 175, :3] = (50, 50, 50)
    for x in range(15, 161):
        for y in range(20, 148):
            arr2[y, x, :3] = (245, 240, 230)
    for x in range(15, 161):
        arr2[20, x, :3] = (100, 100, 100); arr2[147, x, :3] = (100, 100, 100)
    for y in range(20, 148):
        arr2[y, 15, :3] = (100, 100, 100); arr2[y, 160, :3] = (100, 100, 100)
    random.seed(99)
    for row in range(10):
        y = 28 + row * 10
        for x in range(20, 155):
            if random.random() > 0.3:
                arr2[y, x, :3] = (180, 175, 165)
    save(arr2, os.path.join(GUI_DIR, 'recipe_book_gui.png'))
    print("  all GUIs")
if __name__ == '__main__':
    print("=" * 60)
    print("  EvoTech Texture Generator (Pillow+NumPy)")
    print("=" * 60)
    print()
    gen_ore_textures(); print()
    gen_metal_blocks(); print()
    gen_tool_textures(); print()
    gen_hammer_textures(); print()
    gen_armor_textures(); print()
    gen_ingot_textures(); print()
    gen_nugget_textures(); print()
    gen_dust_textures(); print()
    gen_crushed_textures(); print()
    gen_gear_textures(); print()
    gen_plate_textures(); print()
    gen_rod_textures(); print()
    gen_electronics_textures(); print()
    gen_materials_textures(); print()
    gen_machine_textures(); print()
    gen_gui_textures(); print()
    count = sum(1 for _, _, fs in os.walk(BASE) for f in fs if f.endswith('.png'))
    print("=" * 60)
    print(f"  Done! Generated {count} texture files.")
    print("=" * 60)
