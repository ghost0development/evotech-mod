"""EvoTech Texture Generator — Pillow + NumPy, no external noise."""

from __future__ import annotations

import math
import os
from pathlib import Path
from typing import Tuple

import numpy as np
from PIL import Image, ImageDraw

# ---------------------------------------------------------------------------
# Configuration
# ---------------------------------------------------------------------------
BASE = Path(r"C:\Users\Piotr\minecraft-mod\src\main\resources\assets\evotech\textures")
BLOCK = BASE / "block"
ITEM = BASE / "item"
GUI = BASE / "gui"

SIZE = 16

# ---------------------------------------------------------------------------
# Perlin noise (self-contained)
# ---------------------------------------------------------------------------

def _permutation_table(seed: int = 0) -> np.ndarray:
    rng = np.random.RandomState(seed)
    p = np.arange(256, dtype=np.int32)
    rng.shuffle(p)
    return np.tile(p, 2)


def _grad(hash_val: np.ndarray, x: np.ndarray, y: np.ndarray) -> np.ndarray:
    h = hash_val & 3
    u = np.where(h < 2, x, y)
    v = np.where(h < 2, y, x)
    return np.where(h & 1, -u, u) + np.where(h & 2, -v, v)


def perlin2d(
    shape: Tuple[int, int],
    scale: float = 1.0,
    octaves: int = 1,
    persistence: float = 0.5,
    lacunarity: float = 2.0,
    seed: int = 42,
) -> np.ndarray:
    """Return a 2-D Perlin noise array in [0, 1]."""

    perm = _permutation_table(seed)
    height, width = shape
    result = np.zeros(shape, dtype=np.float64)

    for octave in range(octaves):
        amp = persistence ** octave
        freq = lacunarity ** octave
        sx = int(height * scale * freq) + 2
        sy = int(width * scale * freq) + 2

        y_coords = np.linspace(0, sx / (scale * freq), sx, endpoint=False)
        x_coords = np.linspace(0, sy / (scale * freq), sy, endpoint=False)
        xx, yy = np.meshgrid(x_coords, y_coords)

        xi = np.floor(xx).astype(np.int32) % 256
        yi = np.floor(yy).astype(np.int32) % 256
        xf = xx - np.floor(xx)
        yf = yy - np.floor(yy)

        u = xf * xf * (3 - 2 * xf)
        v = yf * yf * (3 - 2 * yf)

        p = perm
        aa = p[p[xi] + yi]
        ab = p[p[xi] + yi + 1]
        ba = p[p[xi + 1] + yi]
        bb = p[p[xi + 1] + yi + 1]

        x1 = _grad(aa, xf, yf) * (1 - u) + _grad(ba, xf - 1, yf) * u
        x2 = _grad(ab, xf, yf - 1) * (1 - u) + _grad(bb, xf - 1, yf - 1) * u
        n = x1 * (1 - v) + x2 * v

        n = (n + 1) / 2
        from PIL import Image as _Img
        arr = _Img.fromarray((n * 255).astype(np.uint8)).resize(
            (width, height), _Img.BILINEAR
        )
        result += np.asarray(arr, dtype=np.float64) / 255.0 * amp

    result = result / (1 - persistence ** octaves) if persistence != 1 else result
    mn, mx = result.min(), result.max()
    if mx - mn > 0:
        result = (result - mn) / (mx - mn)
    return np.clip(result, 0, 1)


# ---------------------------------------------------------------------------
# Colour helpers
# ---------------------------------------------------------------------------

def lerp_color(c1: Tuple, c2: Tuple, t: float) -> Tuple:
    return tuple(int(a + (b - a) * t) for a, b in zip(c1, c2))


def clamp_rgb(c: Tuple) -> Tuple:
    return tuple(max(0, min(255, int(v))) for v in c)


# ---------------------------------------------------------------------------
# Primitive drawing helpers
# ---------------------------------------------------------------------------

def noise_array(w: int, h: int, scale: float = 8.0, seed: int = 0) -> np.ndarray:
    return perlin2d((h, w), scale=scale, octaves=3, persistence=0.5, seed=seed)


def new_rgba() -> Image.Image:
    return Image.new("RGBA", (SIZE, SIZE), (0, 0, 0, 0))


def draw_rect(img: Image.Image, xy, fill, outline=None):
    ImageDraw.Draw(img).rectangle(xy, fill=fill, outline=outline)


def save(img: Image.Image, path: Path):
    path.parent.mkdir(parents=True, exist_ok=True)
    img.save(path)
    print(f"  {path.relative_to(BASE)}")


# ---------------------------------------------------------------------------
# TEXTURE: Ores  (base_stone + ore_spots)
# ---------------------------------------------------------------------------

def _stone_bg(seed: int = 0) -> Image.Image:
    arr = noise_array(16, 16, scale=10, seed=seed)
    img = new_rgba()
    for y in range(16):
        for x in range(16):
            v = arr[y, x]
            c = lerp_color((95, 90, 85), (130, 125, 120), v)
            img.putpixel((x, y), (*c, 255))
    return img


def _deepslate_bg(seed: int = 1) -> Image.Image:
    arr = noise_array(16, 16, scale=10, seed=seed)
    img = new_rgba()
    for y in range(16):
        for x in range(16):
            v = arr[y, x]
            c = lerp_color((55, 52, 50), (80, 78, 75), v)
            img.putpixel((x, y), (*c, 255))
    return img


ORE_SPOTS = [
    (3, 3), (4, 3), (3, 4),
    (10, 9), (11, 9), (11, 10),
    (6, 12), (7, 12), (7, 13),
]


def _make_ore(bg: Image.Image, main: Tuple, dark: Tuple, rng: np.random.RandomState) -> Image.Image:
    img = bg.copy()
    for x, y in ORE_SPOTS:
        v = rng.random()
        c = lerp_color(main, dark, v * 0.4)
        img.putpixel((x, y), (*c, 255))
        # slight highlight pixel nearby
        nx, ny = min(x + 1, 15), min(y + 1, 15)
        c2 = lerp_color(main, (255, 255, 255), 0.15)
        img.putpixel((nx, ny), (*c2, 255))
    return img


def gen_ore(name: str, main: Tuple, dark: Tuple, seed: int):
    rng = np.random.RandomState(seed)
    save(_make_ore(_stone_bg(seed), main, dark, rng), BLOCK / f"{name}.png")
    save(_make_ore(_deepslate_bg(seed), main, dark, rng), BLOCK / f"deepslate_{name}.png")


EXISTING_ORE_COLORS = {
    "tin": ((180, 170, 150), (135, 125, 105)),
    "lead": ((110, 110, 120), (75, 75, 85)),
    "silver": ((200, 200, 210), (160, 160, 175)),
    "aluminum": ((200, 200, 200), (160, 160, 160)),
    "nickel": ((180, 175, 155), (140, 135, 115)),
    "zinc": ((170, 180, 190), (130, 140, 150)),
}

NEW_ORE_COLORS = {
    "tungsten": ((120, 115, 110), (85, 80, 75)),
    "titanium": ((170, 175, 185), (130, 135, 145)),
    "platinum": ((215, 210, 200), (180, 175, 165)),
    "chromium": ((160, 165, 175), (120, 125, 135)),
    "cobalt": ((70, 80, 140), (45, 55, 110)),
    "uranium": ((80, 140, 70), (50, 110, 40)),
    "lithium": ((180, 190, 200), (150, 160, 170)),
    "iridium": ((200, 205, 215), (170, 175, 185)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Metal blocks  (border + metallic fill)
# ---------------------------------------------------------------------------

def _metal_block(main: Tuple, dark: Tuple, light: Tuple, seed: int = 0) -> Image.Image:
    img = new_rgba()
    arr = noise_array(14, 14, scale=8, seed=seed)
    for y in range(16):
        for x in range(16):
            if x == 0 or y == 0 or x == 15 or y == 15:
                img.putpixel((x, y), (*dark, 255))
            elif x == 1 or y == 1:
                img.putpixel((x, y), (*light, 255))
            else:
                ny, nx = min(y - 2, 13), min(x - 2, 13)
                v = arr[ny, nx]
                c = lerp_color(main, light, v * 0.3)
                img.putpixel((x, y), (*c, 255))
    return img


EXISTING_METAL_BLOCK_COLORS = {
    "tin_block": ((175, 165, 145), (130, 120, 100), (200, 190, 170)),
    "bronze_block": ((180, 130, 70), (140, 90, 30), (210, 160, 100)),
    "lead_block": ((105, 105, 115), (70, 70, 80), (135, 135, 145)),
    "steel_block": ((145, 145, 155), (110, 110, 120), (175, 175, 185)),
    "silver_block": ((200, 200, 210), (160, 160, 175), (225, 225, 235)),
    "aluminum_block": ((195, 195, 195), (155, 155, 155), (220, 220, 220)),
    "nickel_block": ((175, 170, 150), (135, 130, 110), (200, 195, 175)),
    "zinc_block": ((165, 175, 185), (125, 135, 145), (195, 205, 215)),
    "electrum_block": ((215, 195, 100), (175, 155, 60), (240, 220, 130)),
    "constantan_block": ((185, 135, 110), (145, 95, 70), (215, 165, 140)),
}

NEW_METAL_BLOCK_COLORS = {
    "tungsten_block": ((115, 110, 105), (80, 75, 70), (145, 140, 135)),
    "titanium_block": ((175, 180, 190), (135, 140, 150), (200, 205, 215)),
    "platinum_block": ((210, 205, 195), (175, 170, 160), (235, 230, 220)),
    "chromium_block": ((155, 160, 170), (115, 120, 130), (185, 190, 200)),
    "cobalt_block": ((65, 75, 135), (40, 50, 105), (90, 100, 165)),
    "uranium_block": ((75, 135, 65), (45, 105, 35), (105, 165, 95)),
    "lithium_block": ((185, 195, 205), (155, 165, 175), (210, 220, 230)),
    "iridium_block": ((195, 200, 210), (165, 170, 180), (220, 225, 235)),
    "invar_block": ((165, 155, 140), (125, 115, 100), (195, 185, 170)),
    "nichrome_block": ((145, 120, 105), (110, 85, 70), (175, 150, 135)),
    "tungsten_carbide_block": ((100, 95, 85), (65, 60, 50), (130, 125, 115)),
    "titanium_alloy_block": ((170, 175, 190), (130, 135, 150), (200, 205, 220)),
    "super_alloy_block": ((195, 185, 120), (155, 145, 80), (225, 215, 150)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Machines
# ---------------------------------------------------------------------------

def _machine_base(body: Tuple, dark: Tuple, accent: Tuple, variant: str, lit: bool = False) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    # outer border
    draw.rectangle((0, 0, 15, 15), fill=dark)
    # inner body
    draw.rectangle((1, 1, 14, 14), fill=body)
    # bottom panel (darker)
    draw.rectangle((1, 10, 14, 14), fill=dark)
    # accent strip
    draw.rectangle((1, 9, 14, 10), fill=accent)
    # screen / display
    if lit:
        glow = tuple(min(255, c + 60) for c in accent)
        draw.rectangle((4, 2, 11, 8), fill=glow)
        draw.rectangle((5, 3, 10, 7), fill=accent)
    else:
        draw.rectangle((4, 2, 11, 8), fill=(40, 40, 40))
        draw.rectangle((5, 3, 10, 7), fill=(50, 60, 55))
    # side bolts
    for pos in [(2, 2), (13, 2), (2, 13), (13, 13)]:
        img.putpixel(pos, (*accent, 255))
    return img


EXISTING_MACHINE_DATA = [
    ("macerator", (115, 115, 120), (85, 85, 90), (160, 100, 40), "macerator"),
    ("generator", (100, 105, 100), (70, 75, 70), (180, 160, 50), "generator"),
    ("compressor", (120, 118, 115), (90, 88, 85), (100, 150, 180), "compressor"),
    ("alloy_smelter", (130, 110, 95), (100, 80, 65), (200, 120, 50), "alloy_smelter"),
    ("electric_furnace", (135, 120, 110), (105, 90, 80), (200, 80, 40), "electric_furnace"),
    ("machine_frame", (110, 110, 115), (80, 80, 85), (140, 140, 145), "machine_frame"),
    ("copper_coil", (120, 80, 45), (90, 50, 15), (200, 130, 60), "copper_coil"),
    ("crushing_table", (100, 90, 75), (70, 60, 45), (140, 120, 80), "crushing_table"),
]

NEW_MACHINE_DATA = [
    ("steam_engine", (110, 100, 90), (80, 70, 60), (180, 160, 60), "steam_engine"),
    ("wire_mill", (105, 108, 115), (80, 83, 90), (180, 110, 50), "wire_mill"),
    ("assembling_machine", (120, 120, 125), (90, 90, 95), (80, 180, 80), "assembling_machine"),
    ("matter_scanner", (130, 125, 135), (100, 95, 105), (60, 140, 200), "matter_scanner"),
]


# ---------------------------------------------------------------------------
# TEXTURE: Cables
# ---------------------------------------------------------------------------

def _cable(core: Tuple, insulation: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((6, 0, 9, 15), fill=insulation)
    draw.rectangle((7, 0, 8, 15), fill=core)
    img.putpixel((6, 0), (*core, 255))
    img.putpixel((9, 0), (*core, 255))
    img.putpixel((6, 15), (*core, 255))
    img.putpixel((9, 15), (*core, 255))
    return img


# ---------------------------------------------------------------------------
# TEXTURE: Ingots
# ---------------------------------------------------------------------------

def _ingot(main: Tuple, dark: Tuple, light: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.polygon([(3, 14), (12, 14), (14, 11), (8, 3), (2, 11)], fill=main)
    draw.line([(3, 14), (12, 14)], fill=dark, width=1)
    draw.line([(12, 14), (14, 11)], fill=dark, width=1)
    draw.line([(3, 14), (2, 11)], fill=light, width=1)
    draw.line([(2, 11), (8, 3)], fill=light, width=1)
    # highlight
    draw.line([(5, 10), (10, 10)], fill=light, width=1)
    draw.line([(6, 8), (9, 8)], fill=light, width=1)
    return img


EXISTING_INGOT_COLORS = {
    "tin_ingot": ((180, 170, 150), (135, 125, 105), (205, 195, 175)),
    "bronze_ingot": ((180, 130, 70), (140, 90, 30), (210, 160, 100)),
    "lead_ingot": ((105, 105, 115), (70, 70, 80), (135, 135, 145)),
    "steel_ingot": ((150, 150, 155), (115, 115, 120), (180, 180, 185)),
    "silver_ingot": ((200, 200, 210), (160, 160, 175), (225, 225, 235)),
    "aluminum_ingot": ((200, 200, 200), (160, 160, 160), (225, 225, 225)),
    "nickel_ingot": ((180, 175, 155), (140, 135, 115), (205, 200, 180)),
    "zinc_ingot": ((170, 180, 190), (130, 140, 150), (200, 210, 220)),
    "electrum_ingot": ((215, 195, 100), (175, 155, 60), (240, 220, 130)),
    "constantan_ingot": ((185, 135, 110), (145, 95, 70), (215, 165, 140)),
}

NEW_INGOT_COLORS = {
    "tungsten_ingot": ((120, 115, 110), (85, 80, 75), (150, 145, 140)),
    "titanium_ingot": ((180, 185, 195), (140, 145, 155), (205, 210, 220)),
    "platinum_ingot": ((220, 215, 205), (185, 180, 170), (240, 235, 225)),
    "chromium_ingot": ((165, 170, 180), (125, 130, 140), (195, 200, 210)),
    "cobalt_ingot": ((75, 85, 145), (50, 60, 115), (100, 110, 175)),
    "uranium_ingot": ((85, 145, 75), (55, 115, 45), (115, 175, 105)),
    "lithium_ingot": ((185, 195, 205), (150, 160, 170), (210, 220, 230)),
    "iridium_ingot": ((205, 210, 220), (170, 175, 185), (230, 235, 245)),
    "invar_ingot": ((170, 160, 145), (130, 120, 105), (200, 190, 175)),
    "nichrome_ingot": ((150, 125, 110), (115, 90, 75), (180, 155, 140)),
    "tungsten_carbide_ingot": ((105, 100, 90), (70, 65, 55), (135, 130, 120)),
    "titanium_alloy_ingot": ((175, 180, 195), (135, 140, 155), (205, 210, 225)),
    "super_alloy_ingot": ((200, 190, 125), (160, 150, 85), (230, 220, 155)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Nuggets
# ---------------------------------------------------------------------------

def _nugget(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.polygon([(5, 13), (11, 13), (13, 9), (8, 4), (3, 9)], fill=main)
    draw.line([(5, 13), (11, 13)], fill=dark, width=1)
    draw.line([(11, 13), (13, 9)], fill=dark, width=1)
    draw.line([(3, 13), (3, 9)], fill=dark, width=1)
    return img


# ---------------------------------------------------------------------------
# TEXTURE: Dusts
# ---------------------------------------------------------------------------

def _dust(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    rng = np.random.RandomState(hash(main) % 2**31)
    for _ in range(28):
        x = rng.randint(2, 14)
        y = rng.randint(6, 14)
        c = lerp_color(main, dark, rng.random() * 0.5)
        img.putpixel((x, y), (*c, 200 + rng.randint(0, 56)))
    return img


EXISTING_DUST_COLORS = {
    "dust_tin": ((175, 165, 145), (130, 120, 100)),
    "dust_lead": ((100, 100, 110), (65, 65, 75)),
    "dust_silver": ((195, 195, 205), (155, 155, 170)),
    "dust_iron": ((180, 170, 160), (140, 130, 120)),
    "dust_gold": ((220, 195, 80), (180, 155, 40)),
    "dust_aluminum": ((195, 195, 195), (155, 155, 155)),
    "dust_nickel": ((175, 170, 150), (135, 130, 110)),
    "dust_zinc": ((165, 175, 185), (125, 135, 145)),
}

NEW_DUST_COLORS = {
    "dust_tungsten": ((120, 115, 110), (85, 80, 75)),
    "dust_titanium": ((170, 175, 185), (130, 135, 145)),
    "dust_platinum": ((215, 210, 200), (180, 175, 165)),
    "dust_chromium": ((160, 165, 175), (120, 125, 135)),
    "dust_cobalt": ((70, 80, 140), (45, 55, 110)),
    "dust_uranium": ((80, 140, 70), (50, 110, 40)),
    "dust_lithium": ((180, 190, 200), (150, 160, 170)),
    "dust_iridium": ((200, 205, 215), (170, 175, 185)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Crushed ore
# ---------------------------------------------------------------------------

def _crushed(main: Tuple, dark: Tuple, seed: int = 0) -> Image.Image:
    img = new_rgba()
    rng = np.random.RandomState(seed)
    arr = noise_array(16, 16, scale=6, seed=seed)
    for y in range(16):
        for x in range(16):
            if arr[y, x] > 0.45:
                c = lerp_color(main, dark, rng.random() * 0.4)
                img.putpixel((x, y), (*c, 220))
    return img


EXISTING_CRUSHED_COLORS = {
    "crushed_tin": ((180, 170, 150), (135, 125, 105)),
    "crushed_lead": ((105, 105, 115), (70, 70, 80)),
    "crushed_silver": ((200, 200, 210), (160, 160, 175)),
    "crushed_aluminum": ((200, 200, 200), (160, 160, 160)),
    "crushed_nickel": ((180, 175, 155), (140, 135, 115)),
    "crushed_zinc": ((170, 180, 190), (130, 140, 150)),
}

NEW_CRUSHED_COLORS = {
    "crushed_tungsten": ((120, 115, 110), (85, 80, 75)),
    "crushed_titanium": ((170, 175, 185), (130, 135, 145)),
    "crushed_platinum": ((215, 210, 200), (180, 175, 165)),
    "crushed_chromium": ((160, 165, 175), (120, 125, 135)),
    "crushed_cobalt": ((70, 80, 140), (45, 55, 110)),
    "crushed_uranium": ((80, 140, 70), (50, 110, 40)),
    "crushed_lithium": ((180, 190, 200), (150, 160, 170)),
    "crushed_iridium": ((200, 205, 215), (170, 175, 185)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Gears
# ---------------------------------------------------------------------------

def _gear(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.ellipse((3, 3, 12, 12), fill=main)
    draw.ellipse((5, 5, 10, 10), fill=(40, 40, 40))
    for angle in range(0, 360, 45):
        cx, cy = 8, 8
        rad = math.radians(angle)
        x1 = int(cx + 4 * math.cos(rad))
        y1 = int(cy + 4 * math.sin(rad))
        x2 = int(cx + 7 * math.cos(rad))
        y2 = int(cy + 7 * math.sin(rad))
        draw.line([(x1, y1), (x2, y2)], fill=main, width=2)
    return img


EXISTING_GEAR_COLORS = {
    "gear_copper": ((180, 120, 60), (140, 80, 20)),
    "gear_tin": ((175, 165, 145), (130, 120, 100)),
    "gear_bronze": ((180, 130, 70), (140, 90, 30)),
    "gear_iron": ((170, 170, 175), (130, 130, 135)),
    "gear_steel": ((145, 145, 155), (110, 110, 120)),
    "gear_aluminum": ((195, 195, 195), (155, 155, 155)),
}

NEW_GEAR_COLORS = {
    "gear_tungsten": ((120, 115, 110), (85, 80, 75)),
    "gear_titanium": ((175, 180, 190), (135, 140, 150)),
    "gear_platinum": ((215, 210, 200), (180, 175, 165)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Plates
# ---------------------------------------------------------------------------

def _plate(main: Tuple, dark: Tuple, light: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((1, 5, 14, 11), fill=main)
    draw.line([(1, 5), (14, 5)], fill=light, width=1)
    draw.line([(1, 11), (14, 11)], fill=dark, width=1)
    draw.rectangle((3, 7, 12, 9), fill=light)
    return img


EXISTING_PLATE_COLORS = {
    "plate_copper": ((180, 120, 60), (140, 80, 20), (210, 150, 90)),
    "plate_tin": ((175, 165, 145), (130, 120, 100), (200, 190, 170)),
    "plate_bronze": ((180, 130, 70), (140, 90, 30), (210, 160, 100)),
    "plate_iron": ((170, 170, 175), (130, 130, 135), (200, 200, 205)),
    "plate_steel": ((145, 145, 155), (110, 110, 120), (175, 175, 185)),
    "plate_aluminum": ((195, 195, 195), (155, 155, 155), (220, 220, 220)),
    "plate_nickel": ((175, 170, 150), (135, 130, 110), (200, 195, 175)),
}

NEW_PLATE_COLORS = {
    "plate_tungsten": ((120, 115, 110), (85, 80, 75), (150, 145, 140)),
    "plate_titanium": ((175, 180, 190), (135, 140, 150), (200, 205, 215)),
    "plate_platinum": ((215, 210, 200), (180, 175, 165), (240, 235, 225)),
    "plate_chromium": ((160, 165, 175), (120, 125, 135), (190, 195, 205)),
    "plate_iridium": ((200, 205, 215), (170, 175, 185), (225, 230, 240)),
    "plate_invar": ((165, 155, 140), (125, 115, 100), (195, 185, 170)),
    "plate_nichrome": ((145, 120, 105), (110, 85, 70), (175, 150, 135)),
    "plate_tungsten_carbide": ((100, 95, 85), (65, 60, 50), (130, 125, 115)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Rods
# ---------------------------------------------------------------------------

def _rod(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((7, 1, 8, 14), fill=main)
    draw.rectangle((7, 1, 7, 14), fill=dark)
    draw.rectangle((6, 0, 9, 0), fill=dark)
    draw.rectangle((6, 15, 9, 15), fill=dark)
    return img


EXISTING_ROD_COLORS = {
    "rod_copper": ((180, 120, 60), (140, 80, 20)),
    "rod_iron": ((170, 170, 175), (130, 130, 135)),
    "rod_steel": ((145, 145, 155), (110, 110, 120)),
    "rod_bronze": ((180, 130, 70), (140, 90, 30)),
    "rod_aluminum": ((195, 195, 195), (155, 155, 155)),
}

NEW_ROD_COLORS = {
    "rod_tungsten": ((120, 115, 110), (85, 80, 75)),
    "rod_titanium": ((175, 180, 190), (135, 140, 150)),
    "rod_platinum": ((215, 210, 200), (180, 175, 165)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Tools  (pickaxe, axe, shovel, hoe, sword)
# ---------------------------------------------------------------------------

STICK = (140, 110, 70)
STICK_DK = (100, 75, 40)

TOOL_HEADS = {
    "pickaxe": {"fill": [(5, 2), (6, 2), (7, 2), (8, 2), (9, 2), (10, 2),
                          (4, 3), (5, 3), (6, 3), (7, 3), (8, 3), (9, 3), (10, 3), (11, 3)],
                "tip": [(5, 2), (6, 2), (7, 2), (10, 2), (11, 2)]},
    "axe": {"fill": [(5, 2), (6, 2), (7, 2), (8, 2),
                      (4, 3), (5, 3), (6, 3), (7, 3), (8, 3),
                      (4, 4), (5, 4), (6, 4), (7, 4)],
            "tip": [(4, 3), (4, 4), (5, 2)]},
    "shovel": {"fill": [(7, 1), (7, 2), (7, 3), (7, 4), (7, 5),
                         (6, 1), (6, 2), (6, 3),
                         (8, 1), (8, 2), (8, 3)],
               "tip": [(7, 1), (6, 1), (8, 1)]},
    "hoe": {"fill": [(5, 2), (6, 2), (7, 2), (8, 2),
                      (4, 3), (5, 3), (6, 3), (7, 3)],
            "tip": [(5, 2), (6, 2)]},
    "sword": {"fill": [(7, 1), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6), (7, 7),
                        (6, 5), (8, 5)],
              "tip": [(7, 1), (7, 2)]},
}


def _tool(kind: str, main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    head = TOOL_HEADS[kind]
    for x, y in head["fill"]:
        c = dark if (x, y) in head["tip"] else main
        img.putpixel((x, y), (*c, 255))
    # handle / stick
    if kind == "sword":
        for y in range(8, 14):
            img.putpixel((7, y), (*STICK, 255))
        img.putpixel((7, 14), (*STICK_DK, 255))
        img.putpixel((6, 9), (*STICK, 255))
        img.putpixel((8, 9), (*STICK, 255))
    else:
        for y in range(4, 14):
            img.putpixel((7, y), (*STICK, 255))
        img.putpixel((7, 14), (*STICK_DK, 255))
    return img


EXISTING_TOOL_COLORS = {
    "flint": ((70, 70, 70), (40, 40, 40)),
    "copper": ((180, 120, 60), (140, 80, 20)),
    "bronze": ((180, 130, 70), (140, 90, 30)),
    "steel": ((150, 150, 155), (115, 115, 120)),
}

NEW_TOOL_COLORS = {
    "tungsten": ((120, 115, 110), (85, 80, 75)),
    "titanium": ((180, 185, 195), (140, 145, 155)),
    "iridium": ((205, 210, 220), (170, 175, 185)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Hammers
# ---------------------------------------------------------------------------

def _hammer(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((5, 2, 10, 5), fill=main)
    draw.rectangle((5, 2, 10, 2), fill=dark)
    draw.rectangle((5, 5, 10, 5), fill=dark)
    for y in range(6, 14):
        img.putpixel((7, y), (*STICK, 255))
    img.putpixel((7, 14), (*STICK_DK, 255))
    return img


EXISTING_HAMMER_COLORS = {
    "stone_hammer": ((130, 130, 130), (90, 90, 90)),
    "iron_hammer": ((170, 170, 175), (130, 130, 135)),
    "steel_hammer": ((150, 150, 155), (115, 115, 120)),
}

NEW_HAMMER_COLORS = {
    "tungsten_hammer": ((120, 115, 110), (85, 80, 75)),
    "titanium_hammer": ((180, 185, 195), (140, 145, 155)),
    "iridium_hammer": ((205, 210, 220), (170, 175, 185)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Armor
# ---------------------------------------------------------------------------

def _helmet(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.polygon([(3, 14), (3, 6), (5, 3), (10, 3), (12, 6), (12, 14)], fill=main)
    draw.line([(3, 14), (3, 6), (5, 3), (10, 3), (12, 6), (12, 14)], fill=dark, width=1)
    draw.rectangle((5, 8, 10, 11), fill=dark)
    return img


def _chestplate(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.polygon([(4, 1), (6, 1), (8, 3), (12, 3), (14, 5), (14, 14), (1, 14), (1, 5), (3, 3), (7, 3)], fill=main)
    draw.line([(4, 1), (6, 1), (8, 3), (12, 3), (14, 5), (14, 14)], fill=dark, width=1)
    draw.line([(4, 1), (3, 3), (1, 5), (1, 14)], fill=dark, width=1)
    draw.line([(7, 4), (7, 12)], fill=dark, width=1)
    return img


def _leggings(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((3, 1, 12, 5), fill=main)
    draw.rectangle((3, 6, 7, 14), fill=main)
    draw.rectangle((8, 6, 12, 14), fill=main)
    draw.rectangle((3, 1, 12, 1), fill=dark)
    draw.rectangle((3, 6, 12, 6), fill=dark)
    draw.rectangle((3, 14, 7, 14), fill=dark)
    draw.rectangle((8, 14, 12, 14), fill=dark)
    draw.line([(7, 6), (8, 6)], fill=dark, width=1)
    return img


def _boots(main: Tuple, dark: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((3, 5, 7, 14), fill=main)
    draw.rectangle((8, 5, 12, 14), fill=main)
    draw.rectangle((2, 12, 7, 14), fill=main)
    draw.rectangle((8, 12, 13, 14), fill=main)
    draw.rectangle((3, 5, 12, 5), fill=dark)
    draw.rectangle((2, 14, 7, 14), fill=dark)
    draw.rectangle((8, 14, 13, 14), fill=dark)
    return img


EXISTING_ARMOR_COLORS = {
    "copper": ((180, 120, 60), (140, 80, 20)),
    "bronze": ((180, 130, 70), (140, 90, 30)),
    "steel": ((150, 150, 155), (115, 115, 120)),
}

NEW_ARMOR_COLORS = {
    "tungsten": ((120, 115, 110), (85, 80, 75)),
    "titanium": ((180, 185, 195), (140, 145, 155)),
    "iridium": ((205, 210, 220), (170, 175, 185)),
}


# ---------------------------------------------------------------------------
# TEXTURE: Electronics
# ---------------------------------------------------------------------------

def _circuit(board: Tuple, trace: Tuple, pad: Tuple) -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((1, 1, 14, 14), fill=board)
    draw.line([(3, 4), (12, 4)], fill=trace, width=1)
    draw.line([(3, 7), (9, 7)], fill=trace, width=1)
    draw.line([(3, 10), (12, 10)], fill=trace, width=1)
    draw.line([(6, 4), (6, 10)], fill=trace, width=1)
    draw.line([(10, 4), (10, 10)], fill=trace, width=1)
    for x, y in [(4, 4), (8, 7), (4, 10), (10, 10)]:
        draw.rectangle((x - 1, y - 1, x + 1, y + 1), fill=pad)
    return img


EXISTING_ELECTRONICS = [
    ("basic_circuit", (40, 100, 40), (200, 180, 50), (200, 50, 50)),
    ("advanced_circuit", (40, 40, 100), (50, 200, 200), (200, 100, 50)),
    ("silicon_wafer", (150, 150, 160), (80, 80, 100), (120, 120, 140)),
    ("copper_wire", (180, 120, 60), (140, 80, 20), (210, 150, 90)),
    ("insulated_copper_wire", (60, 60, 60), (180, 120, 60), (30, 30, 30)),
]

NEW_ELECTRONICS = [
    ("quantum_chip", (30, 30, 80), (60, 60, 200), (100, 100, 255)),
    ("resistor", (60, 50, 40), (180, 100, 50), (220, 200, 100)),
    ("capacitor", (80, 40, 40), (50, 50, 150), (200, 200, 50)),
    ("transistor", (40, 40, 40), (200, 200, 200), (80, 180, 80)),
    ("processor", (40, 80, 40), (200, 180, 50), (50, 200, 50)),
    ("advanced_processor", (30, 30, 70), (50, 50, 200), (100, 200, 255)),
    ("electric_motor", (100, 100, 110), (70, 70, 80), (180, 180, 50)),
    ("machine_part", (120, 120, 125), (85, 85, 90), (160, 160, 165)),
    ("steel_frame", (140, 140, 150), (100, 100, 110), (170, 170, 180)),
]


def _copper_wire_item() -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.line([(2, 8), (13, 8)], fill=(180, 120, 60), width=3)
    draw.line([(2, 7), (13, 7)], fill=(140, 80, 20), width=1)
    draw.line([(2, 11), (13, 11)], fill=(210, 150, 90), width=1)
    return img


def _insulated_wire_item() -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.line([(2, 7), (13, 7)], fill=(40, 40, 40), width=1)
    draw.line([(2, 8), (13, 8)], fill=(60, 60, 60), width=3)
    draw.line([(2, 9), (13, 9)], fill=(40, 40, 40), width=1)
    draw.line([(5, 8), (5, 8)], fill=(180, 120, 60), width=1)
    draw.line([(10, 8), (10, 8)], fill=(180, 120, 60), width=1)
    return img


# ---------------------------------------------------------------------------
# TEXTURE: Other materials
# ---------------------------------------------------------------------------

def _coal_coke() -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((3, 3, 12, 12), fill=(40, 35, 30))
    draw.rectangle((4, 4, 11, 11), fill=(55, 50, 45))
    draw.rectangle((6, 6, 9, 9), fill=(70, 65, 55))
    return img


def _biochar() -> Image.Image:
    img = new_rgba()
    rng = np.random.RandomState(77)
    draw = ImageDraw.Draw(img)
    draw.rectangle((3, 3, 12, 12), fill=(35, 30, 25))
    for _ in range(12):
        x, y = rng.randint(4, 12), rng.randint(4, 12)
        draw.rectangle((x, y, x, y), fill=(50, 45, 38))
    return img


def _engineering_guide() -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((2, 1, 13, 14), fill=(60, 60, 140))
    draw.rectangle((3, 2, 12, 13), fill=(80, 80, 180))
    draw.rectangle((4, 3, 11, 5), fill=(200, 200, 60))
    draw.rectangle((4, 7, 11, 7), fill=(200, 200, 200))
    draw.rectangle((4, 9, 11, 9), fill=(200, 200, 200))
    draw.rectangle((4, 11, 11, 11), fill=(200, 200, 200))
    return img


def _creosote() -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((4, 2, 11, 14), fill=(80, 60, 30))
    draw.rectangle((5, 1, 10, 2), fill=(100, 80, 40))
    draw.rectangle((5, 4, 10, 12), fill=(110, 85, 45))
    draw.rectangle((6, 6, 9, 8), fill=(140, 110, 60))
    return img


def _crude_rubber() -> Image.Image:
    img = new_rgba()
    rng = np.random.RandomState(99)
    for y in range(3, 14):
        for x in range(4, 12):
            v = rng.random()
            c = lerp_color((30, 30, 30), (60, 60, 60), v)
            img.putpixel((x, y), (*c, 255))
    return img


def _machine_casing() -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((0, 0, 15, 15), fill=(120, 120, 125))
    draw.rectangle((1, 1, 14, 14), fill=(140, 140, 145))
    draw.rectangle((2, 2, 13, 13), fill=(130, 130, 135))
    draw.rectangle((4, 4, 11, 11), fill=(145, 145, 150))
    draw.rectangle((6, 6, 9, 9), fill=(100, 100, 105))
    return img


def _steam_canister() -> Image.Image:
    img = new_rgba()
    draw = ImageDraw.Draw(img)
    draw.rectangle((5, 1, 10, 2), fill=(150, 150, 155))
    draw.rectangle((4, 3, 11, 14), fill=(130, 130, 135))
    draw.rectangle((5, 4, 10, 13), fill=(160, 170, 180))
    draw.rectangle((6, 5, 9, 12), fill=(180, 190, 200))
    return img


# ---------------------------------------------------------------------------
# TEXTURE: GUI backgrounds
# ---------------------------------------------------------------------------

def _gui_bg(w: int, h: int, title: str) -> Image.Image:
    img = Image.new("RGBA", (w, h), (200, 200, 200, 255))
    draw = ImageDraw.Draw(img)
    draw.rectangle((0, 0, w - 1, h - 1), outline=(60, 60, 60))
    draw.rectangle((1, 1, w - 2, h - 2), outline=(180, 180, 180))
    draw.rectangle((2, 2, w - 3, 16), fill=(80, 80, 100))
    return img


# ---------------------------------------------------------------------------
# MAIN GENERATION
# ---------------------------------------------------------------------------

def main():
    print("=== EvoTech Texture Generator ===")

    # --- ORES ---
    print("\n[Ores]")
    for name, (main, dark) in EXISTING_ORE_COLORS.items():
        gen_ore(name, main, dark, seed=hash(name) % 2**31)
    for name, (main, dark) in NEW_ORE_COLORS.items():
        gen_ore(name, main, dark, seed=hash(name) % 2**31)

    # --- METAL BLOCKS ---
    print("\n[Metal Blocks]")
    for name, (main, dark, light) in {**EXISTING_METAL_BLOCK_COLORS, **NEW_METAL_BLOCK_COLORS}.items():
        save(_metal_block(main, dark, light, seed=hash(name) % 2**31), BLOCK / f"{name}.png")

    # --- MACHINES ---
    print("\n[Machines]")
    for name, body, dark, accent, variant in EXISTING_MACHINE_DATA:
        save(_machine_base(body, dark, accent, variant, lit=False), BLOCK / f"{name}.png")
        save(_machine_base(body, dark, accent, variant, lit=True), BLOCK / f"{name}_lit.png")
    for name, body, dark, accent, variant in NEW_MACHINE_DATA:
        save(_machine_base(body, dark, accent, variant, lit=False), BLOCK / f"{name}.png")
        save(_machine_base(body, dark, accent, variant, lit=True), BLOCK / f"{name}_lit.png")

    # --- CABLES ---
    print("\n[Cables]")
    save(_cable((220, 190, 50), (60, 60, 60)), ITEM / "gold_cable.png")
    save(_cable((180, 180, 220), (40, 40, 40)), ITEM / "super_cable.png")

    # --- INGOTS ---
    print("\n[Ingots]")
    for name, (main, dark, light) in {**EXISTING_INGOT_COLORS, **NEW_INGOT_COLORS}.items():
        save(_ingot(main, dark, light), ITEM / f"{name}.png")

    # --- NUGGETS ---
    print("\n[Nuggets]")
    all_ingot_colors = {**EXISTING_INGOT_COLORS, **NEW_INGOT_COLORS}
    for name, (main, dark, _) in all_ingot_colors.items():
        nugget_name = name.replace("_ingot", "_nugget")
        save(_nugget(main, dark), ITEM / f"{nugget_name}.png")

    # --- DUSTS ---
    print("\n[Dusts]")
    for name, (main, dark) in {**EXISTING_DUST_COLORS, **NEW_DUST_COLORS}.items():
        save(_dust(main, dark), ITEM / f"{name}.png")

    # --- CRUSHED ---
    print("\n[Crushed]")
    for name, (main, dark) in {**EXISTING_CRUSHED_COLORS, **NEW_CRUSHED_COLORS}.items():
        save(_crushed(main, dark, seed=hash(name) % 2**31), ITEM / f"{name}.png")

    # --- GEARS ---
    print("\n[Gears]")
    for name, (main, dark) in {**EXISTING_GEAR_COLORS, **NEW_GEAR_COLORS}.items():
        save(_gear(main, dark), ITEM / f"{name}.png")

    # --- PLATES ---
    print("\n[Plates]")
    for name, (main, dark, light) in {**EXISTING_PLATE_COLORS, **NEW_PLATE_COLORS}.items():
        save(_plate(main, dark, light), ITEM / f"{name}.png")

    # --- RODS ---
    print("\n[Rods]")
    for name, (main, dark) in {**EXISTING_ROD_COLORS, **NEW_ROD_COLORS}.items():
        save(_rod(main, dark), ITEM / f"{name}.png")

    # --- TOOLS ---
    print("\n[Tools]")
    for material, colors in {**EXISTING_TOOL_COLORS, **NEW_TOOL_COLORS}.items():
        for kind in TOOL_HEADS:
            save(_tool(kind, *colors), ITEM / f"{material}_{kind}.png")

    # --- HAMMERS ---
    print("\n[Hammers]")
    for name, (main, dark) in {**EXISTING_HAMMER_COLORS, **NEW_HAMMER_COLORS}.items():
        save(_hammer(main, dark), ITEM / f"{name}.png")

    # --- ARMOR ---
    print("\n[Armor]")
    armor_funcs = {"helmet": _helmet, "chestplate": _chestplate, "leggings": _leggings, "boots": _boots}
    for material, colors in {**EXISTING_ARMOR_COLORS, **NEW_ARMOR_COLORS}.items():
        for part, func in armor_funcs.items():
            save(func(*colors), ITEM / f"{material}_{part}.png")

    # --- ELECTRONICS ---
    print("\n[Electronics]")
    for name, board, trace, pad in EXISTING_ELECTRONICS:
        if name == "copper_wire":
            save(_copper_wire_item(), ITEM / f"{name}.png")
        elif name == "insulated_copper_wire":
            save(_insulated_wire_item(), ITEM / f"{name}.png")
        else:
            save(_circuit(board, trace, pad), ITEM / f"{name}.png")
    for name, board, trace, pad in NEW_ELECTRONICS:
        save(_circuit(board, trace, pad), ITEM / f"{name}.png")

    # --- MATERIALS ---
    print("\n[Materials]")
    save(_coal_coke(), ITEM / "coal_coke.png")
    save(_biochar(), ITEM / "biochar.png")
    save(_engineering_guide(), ITEM / "engineering_guide.png")
    save(_creosote(), ITEM / "creosote.png")
    save(_crude_rubber(), ITEM / "crude_rubber.png")
    save(_machine_casing(), ITEM / "machine_casing.png")
    save(_steam_canister(), ITEM / "steam_canister.png")

    # --- GUIs ---
    print("\n[GUIs]")
    gui_names = [
        "alloy_smelter_gui", "electric_furnace_gui", "macerator_gui",
        "generator_gui", "compressor_gui", "recipe_book_gui",
        "steam_engine_gui", "wire_mill_gui", "assembling_machine_gui",
        "matter_scanner_gui",
    ]
    for name in gui_names:
        save(_gui_bg(176, 166, name), GUI / f"{name}.png")

    # --- Summary ---
    count = 0
    for d in [BLOCK, ITEM, GUI]:
        if d.exists():
            count += sum(1 for _ in d.rglob("*.png"))
    print(f"\n=== Done — {count} textures written ===")


if __name__ == "__main__":
    main()
