import os
import json

RESOURCES = r"C:\Users\Piotr\minecraft-mod\src\main\resources"
MODID = "evotech"

ASSETS = os.path.join(RESOURCES, "assets", MODID)
DATA = os.path.join(RESOURCES, "data", MODID)
MC_DATA = os.path.join(RESOURCES, "data", "minecraft")
FORGE_DATA = os.path.join(RESOURCES, "data", "forge")


def write_json(path, data):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2, ensure_ascii=False)


def read_json(path):
    if os.path.exists(path):
        with open(path, "r", encoding="utf-8") as f:
            return json.load(f)
    return None


def block_model_cube_all(name):
    return {"parent": "minecraft:block/cube_all", "textures": {"all": f"{MODID}:block/{name}"}}


def item_model_block(name):
    return {"parent": f"{MODID}:block/{name}"}


def item_model_generated(name):
    return {"parent": "minecraft:item/generated", "textures": {"layer0": f"{MODID}:item/{name}"}}


def simple_blockstate(name):
    return {"variants": {"": {"model": f"{MODID}:block/{name}"}}}


def lit_blockstate(name):
    return {
        "variants": {
            "lit=false": {"model": f"{MODID}:block/{name}"},
            "lit=true": {"model": f"{MODID}:block/{name}_lit"},
        }
    }


def standard_loot_table(block_name, drop_item=None):
    if drop_item is None:
        drop_item = f"{MODID}:{block_name}"
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "rolls": 1.0,
                "entries": [
                    {"type": "minecraft:item", "name": drop_item}
                ],
                "conditions": [
                    {"condition": "minecraft:survives_explosion"}
                ],
            }
        ],
    }


def add_tag_entry(tag_data, entry):
    if tag_data is None:
        return {"replace": False, "values": [entry]}
    if entry not in tag_data.get("values", []):
        tag_data.setdefault("values", []).append(entry)
    return tag_data


def update_existing_file(path, new_keys, merge_strategy="add_keys"):
    existing = read_json(path)
    if existing is None:
        return new_keys.copy()
    if merge_strategy == "add_keys":
        for k, v in new_keys.items():
            existing[k] = v
        return existing
    elif merge_strategy == "add_list_entries":
        for entry in new_keys.get("values", []):
            if entry not in existing.get("values", []):
                existing.setdefault("values", []).append(entry)
        return existing
    return existing


# ============================================================
# 1. BLOCKSTATES
# ============================================================
print("Generating blockstates...")

simple_blocks = [
    "aluminum_ore", "deepslate_aluminum_ore",
    "nickel_ore", "deepslate_nickel_ore",
    "zinc_ore", "deepslate_zinc_ore",
    "aluminum_block", "nickel_block", "zinc_block",
    "electrum_block", "constantan_block",
    "machine_frame", "copper_coil",
]

for name in simple_blocks:
    write_json(os.path.join(ASSETS, "blockstates", f"{name}.json"), simple_blockstate(name))

lit_blocks = ["macerator", "generator", "compressor"]
for name in lit_blocks:
    write_json(os.path.join(ASSETS, "blockstates", f"{name}.json"), lit_blockstate(name))

print(f"  Created {len(simple_blocks) + len(lit_blocks)} blockstates")

# ============================================================
# 2. BLOCK MODELS
# ============================================================
print("Generating block models...")

all_cube_blocks = [
    "aluminum_ore", "deepslate_aluminum_ore",
    "nickel_ore", "deepslate_nickel_ore",
    "zinc_ore", "deepslate_zinc_ore",
    "aluminum_block", "nickel_block", "zinc_block",
    "electrum_block", "constantan_block",
    "machine_frame", "copper_coil",
    "macerator", "macerator_lit",
    "generator", "generator_lit",
    "compressor", "compressor_lit",
]

for name in all_cube_blocks:
    write_json(os.path.join(ASSETS, "models", "block", f"{name}.json"), block_model_cube_all(name))

print(f"  Created {len(all_cube_blocks)} block models")

# ============================================================
# 3. ITEM MODELS
# ============================================================
print("Generating item models...")

# Block item models
block_item_names = [
    "aluminum_ore", "deepslate_aluminum_ore",
    "nickel_ore", "deepslate_nickel_ore",
    "zinc_ore", "deepslate_zinc_ore",
    "aluminum_block", "nickel_block", "zinc_block",
    "electrum_block", "constantan_block",
    "machine_frame", "copper_coil",
    "macerator", "generator", "compressor",
]

for name in block_item_names:
    write_json(os.path.join(ASSETS, "models", "item", f"{name}.json"), item_model_block(name))

# Generated item models
generated_items = [
    # Ingots
    "aluminum_ingot", "nickel_ingot", "zinc_ingot",
    "electrum_ingot", "constantan_ingot",
    # Nuggets
    "aluminum_nugget", "nickel_nugget", "zinc_nugget",
    "electrum_nugget", "constantan_nugget",
    # Dusts
    "dust_aluminum", "dust_nickel", "dust_zinc",
    # Crushed
    "crushed_aluminum", "crushed_nickel", "crushed_zinc",
    # Gears
    "gear_copper", "gear_tin", "gear_bronze", "gear_iron", "gear_steel", "gear_aluminum",
    # Plates
    "plate_copper", "plate_tin", "plate_bronze", "plate_iron",
    "plate_steel", "plate_aluminum", "plate_nickel",
    # Rods
    "rod_copper", "rod_iron", "rod_steel", "rod_bronze", "rod_aluminum",
    # Guide
    "engineering_guide",
]

for name in generated_items:
    write_json(os.path.join(ASSETS, "models", "item", f"{name}.json"), item_model_generated(name))

print(f"  Created {len(block_item_names) + len(generated_items)} item models")

# ============================================================
# 4. LOOT TABLES
# ============================================================
print("Generating loot tables...")

ore_block_loot = [
    ("aluminum_ore", f"{MODID}:aluminum_ore"),
    ("deepslate_aluminum_ore", f"{MODID}:aluminum_ore"),
    ("nickel_ore", f"{MODID}:nickel_ore"),
    ("deepslate_nickel_ore", f"{MODID}:nickel_ore"),
    ("zinc_ore", f"{MODID}:zinc_ore"),
    ("deepslate_zinc_ore", f"{MODID}:zinc_ore"),
]

storage_blocks = [
    "aluminum_block", "nickel_block", "zinc_block",
    "electrum_block", "constantan_block",
    "machine_frame", "copper_coil",
]

machine_blocks = ["macerator", "generator", "compressor"]

loot_dir = os.path.join(DATA, "loot_tables", "blocks")

for block_name, drop_item in ore_block_loot:
    write_json(os.path.join(loot_dir, f"{block_name}.json"), standard_loot_table(block_name, drop_item))

for block_name in storage_blocks + machine_blocks:
    write_json(os.path.join(loot_dir, f"{block_name}.json"), standard_loot_table(block_name))

print(f"  Created {len(ore_block_loot) + len(storage_blocks) + len(machine_blocks)} loot tables")

# ============================================================
# 5. TAGS
# ============================================================
print("Generating tags...")

# --- Forge ore tags ---
forge_ore_dir = os.path.join(FORGE_DATA, "tags", "ores")
ore_tag_entries = {
    "aluminum": ["evotech:aluminum_ore", "evotech:deepslate_aluminum_ore"],
    "nickel": ["evotech:nickel_ore", "evotech:deepslate_nickel_ore"],
    "zinc": ["evotech:zinc_ore", "evotech:deepslate_zinc_ore"],
}
for tag_name, entries in ore_tag_entries.items():
    existing = read_json(os.path.join(forge_ore_dir, f"{tag_name}.json"))
    if existing is None:
        data = {"replace": False, "values": entries}
    else:
        data = existing
        for e in entries:
            if e not in data.get("values", []):
                data["values"].append(e)
    write_json(os.path.join(forge_ore_dir, f"{tag_name}.json"), data)

# --- Forge ingot tags ---
forge_ingot_dir = os.path.join(FORGE_DATA, "tags", "items", "ingots")
ingot_tag_entries = {
    "aluminum": ["evotech:aluminum_ingot"],
    "nickel": ["evotech:nickel_ingot"],
    "zinc": ["evotech:zinc_ingot"],
    "electrum": ["evotech:electrum_ingot"],
    "constantan": ["evotech:constantan_ingot"],
}
for tag_name, entries in ingot_tag_entries.items():
    existing = read_json(os.path.join(forge_ingot_dir, f"{tag_name}.json"))
    if existing is None:
        data = {"replace": False, "values": entries}
    else:
        data = existing
        for e in entries:
            if e not in data.get("values", []):
                data["values"].append(e)
    write_json(os.path.join(forge_ingot_dir, f"{tag_name}.json"), data)

# --- Forge gear tags ---
forge_gear_dir = os.path.join(FORGE_DATA, "tags", "items", "gears")
gear_tag_entries = {
    "copper": ["evotech:gear_copper"],
    "tin": ["evotech:gear_tin"],
    "bronze": ["evotech:gear_bronze"],
    "iron": ["evotech:gear_iron"],
    "steel": ["evotech:gear_steel"],
    "aluminum": ["evotech:gear_aluminum"],
}
for tag_name, entries in gear_tag_entries.items():
    write_json(os.path.join(forge_gear_dir, f"{tag_name}.json"), {"replace": False, "values": entries})

# --- Forge plate tags ---
forge_plate_dir = os.path.join(FORGE_DATA, "tags", "items", "plates")
plate_tag_entries = {
    "copper": ["evotech:plate_copper"],
    "tin": ["evotech:plate_tin"],
    "bronze": ["evotech:plate_bronze"],
    "iron": ["evotech:plate_iron"],
    "steel": ["evotech:plate_steel"],
    "aluminum": ["evotech:plate_aluminum"],
    "nickel": ["evotech:plate_nickel"],
}
for tag_name, entries in plate_tag_entries.items():
    write_json(os.path.join(forge_plate_dir, f"{tag_name}.json"), {"replace": False, "values": entries})

# --- Forge rod tags ---
forge_rod_dir = os.path.join(FORGE_DATA, "tags", "items", "rods")
rod_tag_entries = {
    "copper": ["evotech:rod_copper"],
    "iron": ["evotech:rod_iron"],
    "steel": ["evotech:rod_steel"],
    "bronze": ["evotech:rod_bronze"],
    "aluminum": ["evotech:rod_aluminum"],
}
for tag_name, entries in rod_tag_entries.items():
    write_json(os.path.join(forge_rod_dir, f"{tag_name}.json"), {"replace": False, "values": entries})

# --- Minecraft block tags ---
pickaxe_path = os.path.join(MC_DATA, "tags", "blocks", "mineable", "pickaxe.json")
pickaxe_existing = read_json(pickaxe_path)
if pickaxe_existing is None:
    pickaxe_existing = {"replace": False, "values": []}
pickaxe_new = [
    "evotech:aluminum_ore", "evotech:deepslate_aluminum_ore",
    "evotech:nickel_ore", "evotech:deepslate_nickel_ore",
    "evotech:zinc_ore", "evotech:deepslate_zinc_ore",
    "evotech:aluminum_block", "evotech:nickel_block", "evotech:zinc_block",
    "evotech:electrum_block", "evotech:constantan_block",
    "evotech:machine_frame", "evotech:copper_coil",
    "evotech:macerator", "evotech:generator", "evotech:compressor",
]
for e in pickaxe_new:
    if e not in pickaxe_existing["values"]:
        pickaxe_existing["values"].append(e)
write_json(pickaxe_path, pickaxe_existing)

stone_tool_path = os.path.join(MC_DATA, "tags", "blocks", "needs_stone_tool.json")
stone_existing = read_json(stone_tool_path)
if stone_existing is None:
    stone_existing = {"replace": False, "values": []}
stone_new = [
    "evotech:aluminum_ore", "evotech:nickel_ore", "evotech:zinc_ore",
    "evotech:aluminum_block", "evotech:nickel_block", "evotech:zinc_block",
    "evotech:electrum_block", "evotech:constantan_block",
]
for e in stone_new:
    if e not in stone_existing["values"]:
        stone_existing["values"].append(e)
write_json(stone_tool_path, stone_existing)

iron_tool_path = os.path.join(MC_DATA, "tags", "blocks", "needs_iron_tool.json")
iron_existing = read_json(iron_tool_path)
if iron_existing is None:
    iron_existing = {"replace": False, "values": []}
iron_new = [
    "evotech:deepslate_aluminum_ore", "evotech:deepslate_nickel_ore", "evotech:deepslate_zinc_ore",
]
for e in iron_new:
    if e not in iron_existing["values"]:
        iron_existing["values"].append(e)
write_json(iron_tool_path, iron_existing)

print("  Tags generated")

# ============================================================
# 6. WORLDGEN
# ============================================================
print("Generating worldgen configs...")

configured_dir = os.path.join(DATA, "worldgen", "configured_feature")
placed_dir = os.path.join(DATA, "worldgen", "placed_feature")

# Configured features
write_json(os.path.join(configured_dir, "aluminum_ore.json"), {
    "type": "minecraft:ore",
    "config": {
        "size": 7,
        "discard_chance_on_air_exposure": 0,
        "targets": [
            {
                "target": {
                    "predicate_type": "minecraft:tag_match",
                    "tag": "minecraft:base_stone_overworld"
                },
                "state": {"Name": "evotech:aluminum_ore"}
            }
        ]
    }
})

write_json(os.path.join(configured_dir, "nickel_ore.json"), {
    "type": "minecraft:ore",
    "config": {
        "size": 6,
        "discard_chance_on_air_exposure": 0,
        "targets": [
            {
                "target": {
                    "predicate_type": "minecraft:tag_match",
                    "tag": "minecraft:base_stone_overworld"
                },
                "state": {"Name": "evotech:nickel_ore"}
            }
        ]
    }
})

write_json(os.path.join(configured_dir, "zinc_ore.json"), {
    "type": "minecraft:ore",
    "config": {
        "size": 9,
        "discard_chance_on_air_exposure": 0,
        "targets": [
            {
                "target": {
                    "predicate_type": "minecraft:tag_match",
                    "tag": "minecraft:base_stone_overworld"
                },
                "state": {"Name": "evotech:zinc_ore"}
            }
        ]
    }
})

# Placed features
write_json(os.path.join(placed_dir, "aluminum_ore.json"), {
    "feature": "evotech:aluminum_ore",
    "placement": [
        {"type": "minecraft:count", "count": 8},
        {"type": "minecraft:in_square"},
        {
            "type": "minecraft:height_range",
            "height": {
                "type": "minecraft:uniform",
                "min_inclusive": {"above_bottom": 0},
                "max_inclusive": {"absolute": 128}
            }
        },
        {"type": "minecraft:biome"}
    ]
})

write_json(os.path.join(placed_dir, "nickel_ore.json"), {
    "feature": "evotech:nickel_ore",
    "placement": [
        {"type": "minecraft:count", "count": 6},
        {"type": "minecraft:in_square"},
        {
            "type": "minecraft:height_range",
            "height": {
                "type": "minecraft:uniform",
                "min_inclusive": {"absolute": 20},
                "max_inclusive": {"absolute": 80}
            }
        },
        {"type": "minecraft:biome"}
    ]
})

write_json(os.path.join(placed_dir, "zinc_ore.json"), {
    "feature": "evotech:zinc_ore",
    "placement": [
        {"type": "minecraft:count", "count": 10},
        {"type": "minecraft:in_square"},
        {
            "type": "minecraft:height_range",
            "height": {
                "type": "minecraft:uniform",
                "min_inclusive": {"absolute": 30},
                "max_inclusive": {"absolute": 90}
            }
        },
        {"type": "minecraft:biome"}
    ]
})

# Biome modifier - update existing
biome_modifier_path = os.path.join(DATA, "forge", "biome_modifier", "add_ores.json")
biome_modifier = read_json(biome_modifier_path)
if biome_modifier is None:
    biome_modifier = {
        "type": "forge:add_features",
        "biomes": "#is_overworld",
        "features": [],
        "step": "underground_ores"
    }
for ore_feat in ["evotech:aluminum_ore", "evotech:nickel_ore", "evotech:zinc_ore"]:
    if ore_feat not in biome_modifier["features"]:
        biome_modifier["features"].append(ore_feat)
write_json(biome_modifier_path, biome_modifier)

print("  Worldgen generated")

# ============================================================
# 7. RECIPES
# ============================================================
print("Generating recipes...")

recipes_dir = os.path.join(DATA, "recipes")


def smelting_recipe(recipe_name, ingredient, result, xp=0.7):
    return {
        "type": "minecraft:smelting",
        "ingredient": {"item": ingredient},
        "result": result,
        "experience": xp,
        "cookingtime": 200
    }


def shaped_recipe(recipe_name, pattern, key, result, result_count=1):
    out = {"type": "minecraft:shaped", "pattern": pattern, "key": key,
           "result": {"item": f"{MODID}:{result_name(result)}", "count": result_count}}
    return out


def result_name(item_str):
    if item_str.startswith(f"{MODID}:"):
        return item_str[len(f"{MODID}:"):]
    return item_str


def shaped(recipe_pattern, recipe_key, result_item, count=1):
    return {
        "type": "minecraft:shaped",
        "pattern": recipe_pattern,
        "key": recipe_key,
        "result": {"item": result_item, "count": count}
    }


def shapeless(recipe_ingredients, result_item, count=1):
    return {
        "type": "minecraft:shapeless",
        "ingredients": [{"item": i} for i in recipe_ingredients],
        "result": {"item": result_item, "count": count}
    }


def block_storage_recipe(block_item, ingot_item):
    return shaped(
        ["III", "III", "III"],
        {"I": ingot_item},
        block_item
    )


def block_unstorage_recipe(block_item, ingot_item):
    return shapeless([block_item], ingot_item, 9)


def nugget_compress_recipe(nugget_item, ingot_item):
    return shaped(
        ["NNN", "NNN", "NNN"],
        {"N": nugget_item},
        ingot_item
    )


def nugget_decompress_recipe(ingot_item, nugget_item):
    return shapeless([ingot_item], nugget_item, 9)


# --- Ore smelting ---
new_ore_smelts = [
    ("aluminum_ore_smelting", "evotech:aluminum_ore", "evotech:aluminum_ingot"),
    ("deepslate_aluminum_ore_smelting", "evotech:deepslate_aluminum_ore", "evotech:aluminum_ingot"),
    ("nickel_ore_smelting", "evotech:nickel_ore", "evotech:nickel_ingot"),
    ("deepslate_nickel_ore_smelting", "evotech:deepslate_nickel_ore", "evotech:nickel_ingot"),
    ("zinc_ore_smelting", "evotech:zinc_ore", "evotech:zinc_ingot"),
    ("deepslate_zinc_ore_smelting", "evotech:deepslate_zinc_ore", "evotech:zinc_ingot"),
]

for recipe_name, ingredient, result in new_ore_smelts:
    write_json(os.path.join(recipes_dir, f"{recipe_name}.json"), smelting_recipe(recipe_name, ingredient, result))

# --- Dust smelting ---
new_dust_smelts = [
    ("dust_aluminum_smelting", "evotech:dust_aluminum", "evotech:aluminum_ingot"),
    ("dust_nickel_smelting", "evotech:dust_nickel", "evotech:nickel_ingot"),
    ("dust_zinc_smelting", "evotech:dust_zinc", "evotech:zinc_ingot"),
]

for recipe_name, ingredient, result in new_dust_smelts:
    write_json(os.path.join(recipes_dir, f"{recipe_name}.json"), smelting_recipe(recipe_name, ingredient, result))

# --- Storage block crafting (9 ingots -> 1 block) ---
storage_blocks = ["aluminum_block", "nickel_block", "zinc_block", "electrum_block", "constantan_block"]
for block in storage_blocks:
    ingot = f"evotech:{block.replace('_block', '_ingot')}"
    write_json(os.path.join(recipes_dir, f"{block}.json"), block_storage_recipe(f"evotech:{block}", ingot))

# --- Block -> 9 ingots (shapeless) ---
for block in storage_blocks:
    ingot = f"evotech:{block.replace('_block', '_ingot')}"
    write_json(os.path.join(recipes_dir, f"{ingot.split(':')[1]}_from_block.json"),
               block_unstorage_recipe(f"evotech:{block}", ingot))

# --- 9 nuggets -> 1 ingot ---
new_alloys = ["aluminum", "nickel", "zinc", "electrum", "constantan"]
for metal in new_alloys:
    write_json(os.path.join(recipes_dir, f"{metal}_ingot_from_nuggets.json"),
               nugget_compress_recipe(f"evotech:{metal}_nugget", f"evotech:{metal}_ingot"))

# --- 1 ingot -> 9 nuggets ---
for metal in new_alloys:
    write_json(os.path.join(recipes_dir, f"{metal}_nugget_from_ingot.json"),
               nugget_decompress_recipe(f"evotech:{metal}_ingot", f"evotech:{metal}_nugget"))

# --- Gear recipes (4 ingots + stick -> 1 gear) ---
gear_data = {
    "gear_copper": "evotech:copper_ingot",
    "gear_tin": "evotech:tin_ingot",
    "gear_bronze": "evotech:bronze_ingot",
    "gear_iron": "minecraft:iron_ingot",
    "gear_steel": "evotech:steel_ingot",
    "gear_aluminum": "evotech:aluminum_ingot",
}

for gear, ingot in gear_data.items():
    write_json(os.path.join(recipes_dir, f"{gear}.json"), shaped(
        ["I I", " S ", "I I"],
        {"I": ingot, "S": "minecraft:stick"},
        f"evotech:{gear}"
    ))

# --- Plate recipes (2x2 ingots -> 4 plates) ---
plate_data = {
    "plate_copper": "evotech:copper_ingot",
    "plate_tin": "evotech:tin_ingot",
    "plate_bronze": "evotech:bronze_ingot",
    "plate_iron": "minecraft:iron_ingot",
    "plate_steel": "evotech:steel_ingot",
    "plate_aluminum": "evotech:aluminum_ingot",
    "plate_nickel": "evotech:nickel_ingot",
}

for plate, ingot in plate_data.items():
    write_json(os.path.join(recipes_dir, f"{plate}.json"), shaped(
        ["II", "II"],
        {"I": ingot},
        f"evotech:{plate}",
        count=4
    ))

# --- Rod recipes (2 ingots vertical -> 2 rods) ---
rod_data = {
    "rod_copper": "evotech:copper_ingot",
    "rod_iron": "minecraft:iron_ingot",
    "rod_steel": "evotech:steel_ingot",
    "rod_bronze": "evotech:bronze_ingot",
    "rod_aluminum": "evotech:aluminum_ingot",
}

for rod, ingot in rod_data.items():
    write_json(os.path.join(recipes_dir, f"{rod}.json"), shaped(
        ["I", "I"],
        {"I": ingot},
        f"evotech:{rod}",
        count=2
    ))

# --- Alloy recipes (shapeless, alloy smelter) ---
write_json(os.path.join(recipes_dir, "electrum_ingot.json"), shapeless(
    ["minecraft:gold_ingot", "evotech:silver_ingot"],
    "evotech:electrum_ingot",
    count=2
))

write_json(os.path.join(recipes_dir, "constantan_ingot.json"), shapeless(
    ["minecraft:copper_ingot", "evotech:nickel_ingot"],
    "evotech:constantan_ingot",
    count=2
))

# --- Machine crafting recipes ---
write_json(os.path.join(recipes_dir, "macerator.json"), shaped(
    ["SIS", "ICI", "SIS"],
    {"S": "evotech:steel_ingot", "I": "minecraft:iron_ingot", "C": "minecraft:cobblestone"},
    "evotech:macerator"
))

write_json(os.path.join(recipes_dir, "generator.json"), shaped(
    ["IRI", "ICI", "IRI"],
    {"I": "minecraft:iron_ingot", "R": "minecraft:redstone", "C": "minecraft:cobblestone"},
    "evotech:generator"
))

write_json(os.path.join(recipes_dir, "compressor.json"), shaped(
    ["SSS", "SIS", " C "],
    {"S": "evotech:steel_ingot", "I": "minecraft:iron_ingot", "C": "minecraft:cobblestone"},
    "evotech:compressor"
))

write_json(os.path.join(recipes_dir, "machine_frame.json"), shaped(
    ["SIS", "I I", "SIS"],
    {"S": "evotech:steel_ingot", "I": "minecraft:iron_ingot"},
    "evotech:machine_frame"
))

write_json(os.path.join(recipes_dir, "copper_coil.json"), shaped(
    ["CWC", "CWC", " C "],
    {"C": "evotech:copper_ingot", "W": "evotech:copper_wire"},
    "evotech:copper_coil"
))

write_json(os.path.join(recipes_dir, "engineering_guide.json"), shapeless(
    ["minecraft:book", "evotech:gear_iron"],
    "evotech:engineering_guide"
))

print("  Recipes generated")

# ============================================================
# 8. LANGUAGE FILES
# ============================================================
print("Updating language files...")

en_us_new = {
    "item.evotech.aluminum_ingot": "Aluminum Ingot",
    "item.evotech.nickel_ingot": "Nickel Ingot",
    "item.evotech.zinc_ingot": "Zinc Ingot",
    "item.evotech.electrum_ingot": "Electrum Ingot",
    "item.evotech.constantan_ingot": "Constantan Ingot",
    "item.evotech.aluminum_nugget": "Aluminum Nugget",
    "item.evotech.nickel_nugget": "Nickel Nugget",
    "item.evotech.zinc_nugget": "Zinc Nugget",
    "item.evotech.electrum_nugget": "Electrum Nugget",
    "item.evotech.constantan_nugget": "Constantan Nugget",
    "item.evotech.dust_aluminum": "Aluminum Dust",
    "item.evotech.dust_nickel": "Nickel Dust",
    "item.evotech.dust_zinc": "Zinc Dust",
    "item.evotech.crushed_aluminum": "Crushed Aluminum Ore",
    "item.evotech.crushed_nickel": "Crushed Nickel Ore",
    "item.evotech.crushed_zinc": "Crushed Zinc Ore",
    "item.evotech.gear_copper": "Copper Gear",
    "item.evotech.gear_tin": "Tin Gear",
    "item.evotech.gear_bronze": "Bronze Gear",
    "item.evotech.gear_iron": "Iron Gear",
    "item.evotech.gear_steel": "Steel Gear",
    "item.evotech.gear_aluminum": "Aluminum Gear",
    "item.evotech.plate_copper": "Copper Plate",
    "item.evotech.plate_tin": "Tin Plate",
    "item.evotech.plate_bronze": "Bronze Plate",
    "item.evotech.plate_iron": "Iron Plate",
    "item.evotech.plate_steel": "Steel Plate",
    "item.evotech.plate_aluminum": "Aluminum Plate",
    "item.evotech.plate_nickel": "Nickel Plate",
    "item.evotech.rod_copper": "Copper Rod",
    "item.evotech.rod_iron": "Iron Rod",
    "item.evotech.rod_steel": "Steel Rod",
    "item.evotech.rod_bronze": "Bronze Rod",
    "item.evotech.rod_aluminum": "Aluminum Rod",
    "block.evotech.aluminum_ore": "Aluminum Ore",
    "block.evotech.deepslate_aluminum_ore": "Deepslate Aluminum Ore",
    "block.evotech.nickel_ore": "Nickel Ore",
    "block.evotech.deepslate_nickel_ore": "Deepslate Nickel Ore",
    "block.evotech.zinc_ore": "Zinc Ore",
    "block.evotech.deepslate_zinc_ore": "Deepslate Zinc Ore",
    "block.evotech.aluminum_block": "Aluminum Block",
    "block.evotech.nickel_block": "Nickel Block",
    "block.evotech.zinc_block": "Zinc Block",
    "block.evotech.electrum_block": "Electrum Block",
    "block.evotech.constantan_block": "Constantan Block",
    "block.evotech.macerator": "Macerator",
    "block.evotech.generator": "Generator",
    "block.evotech.compressor": "Compressor",
    "block.evotech.machine_frame": "Machine Frame",
    "block.evotech.copper_coil": "Copper Coil",
    "container.evotech.macerator": "Macerator",
    "container.evotech.generator": "Generator",
    "container.evotech.compressor": "Compressor",
}

pl_pl_new = {
    "item.evotech.aluminum_ingot": "Sztabka Aluminiowa",
    "item.evotech.nickel_ingot": "Sztabka Niklowa",
    "item.evotech.zinc_ingot": "Sztabka Cynkowa",
    "item.evotech.electrum_ingot": "Sztabka Elektrum",
    "item.evotech.constantan_ingot": "Sztabka Constantanu",
    "item.evotech.aluminum_nugget": "Od\u0142amek Aluminiowy",
    "item.evotech.nickel_nugget": "Od\u0142amek Niklowy",
    "item.evotech.zinc_nugget": "Od\u0142amek Cynkowy",
    "item.evotech.electrum_nugget": "Od\u0142amek Elektrum",
    "item.evotech.constantan_nugget": "Od\u0142amek Constantanu",
    "item.evotech.dust_aluminum": "Py\u0142 Aluminiowy",
    "item.evotech.dust_nickel": "Py\u0142 Niklowy",
    "item.evotech.dust_zinc": "Py\u0142 Cynkowy",
    "item.evotech.crushed_aluminum": "Zmielona Ruda Aluminiowa",
    "item.evotech.crushed_nickel": "Zmielona Ruda Niklowa",
    "item.evotech.crushed_zinc": "Zmielona Ruda Cynkowa",
    "item.evotech.gear_copper": "Miedziane Ko\u0142o Z\u0119bate",
    "item.evotech.gear_tin": "Cynowe Ko\u0142o Z\u0119bate",
    "item.evotech.gear_bronze": "Br\u0105zowe Ko\u0142o Z\u0119bate",
    "item.evotech.gear_iron": "\u017belazne Ko\u0142o Z\u0119bate",
    "item.evotech.gear_steel": "Stalowe Ko\u0142o Z\u0119bate",
    "item.evotech.gear_aluminum": "Aluminiowe Ko\u0142o Z\u0119bate",
    "item.evotech.plate_copper": "Miedziana P\u0142yta",
    "item.evotech.plate_tin": "Cynowa P\u0142yta",
    "item.evotech.plate_bronze": "Br\u0105zowa P\u0142yta",
    "item.evotech.plate_iron": "\u017belazna P\u0142yta",
    "item.evotech.plate_steel": "Stalowa P\u0142yta",
    "item.evotech.plate_aluminum": "Aluminiowa P\u0142yta",
    "item.evotech.plate_nickel": "Niklowa P\u0142yta",
    "item.evotech.rod_copper": "Miedziany Pr\u0119t",
    "item.evotech.rod_iron": "\u017belazny Pr\u0119t",
    "item.evotech.rod_steel": "Stalowy Pr\u0119t",
    "item.evotech.rod_bronze": "Br\u0105zowy Pr\u0119t",
    "item.evotech.rod_aluminum": "Aluminiowy Pr\u0119t",
    "block.evotech.aluminum_ore": "Ruda Aluminiowa",
    "block.evotech.deepslate_aluminum_ore": "\u0141upkowa Ruda Aluminiowa",
    "block.evotech.nickel_ore": "Ruda Niklowa",
    "block.evotech.deepslate_nickel_ore": "\u0141upkowa Ruda Niklowa",
    "block.evotech.zinc_ore": "Ruda Cynkowa",
    "block.evotech.deepslate_zinc_ore": "\u0141upkowa Ruda Cynkowa",
    "block.evotech.aluminum_block": "Blok Aluminiowy",
    "block.evotech.nickel_block": "Blok Niklowy",
    "block.evotech.zinc_block": "Blok Cynkowy",
    "block.evotech.electrum_block": "Blok Elektrum",
    "block.evotech.constantan_block": "Blok Constantanu",
    "block.evotech.macierator": "Mikser",
    "block.evotech.generator": "Generator",
    "block.evotech.compressor": "Kompresor",
    "block.evotech.machine_frame": "Rama Maszyny",
    "block.evotech.copper_coil": "Miedziana Cewka",
    "container.evotech.macierator": "Mikser",
    "container.evotech.generator": "Generator",
    "container.evotech.compressor": "Kompresor",
}

# Merge en_us
en_path = os.path.join(ASSETS, "lang", "en_us.json")
en_existing = read_json(en_path) or {}
en_existing.update(en_us_new)
write_json(en_path, en_existing)

# Merge pl_pl
pl_path = os.path.join(ASSETS, "lang", "pl_pl.json")
pl_existing = read_json(pl_path) or {}
pl_existing.update(pl_pl_new)
write_json(pl_path, pl_existing)

print(f"  Updated {len(en_us_new)} en_us and {len(pl_pl_new)} pl_pl translations")

# ============================================================
# SUMMARY
# ============================================================
print("\nDone! Resource generation complete.")
print(f"  Blockstates: {len(simple_blocks) + len(lit_blocks)}")
print(f"  Block models: {len(all_cube_blocks)}")
print(f"  Item models: {len(block_item_names) + len(generated_items)}")
print(f"  Loot tables: {len(ore_block_loot) + len(storage_blocks) + len(machine_blocks)}")
print(f"  Tags: ore={len(ore_tag_entries)}, ingot={len(ingot_tag_entries)}, gear={len(gear_tag_entries)}, plate={len(plate_tag_entries)}, rod={len(rod_tag_entries)}")
print(f"  Worldgen: 3 configured + 3 placed + biome modifier updated")
print(f"  Recipes: smelting + storage + gears + plates + rods + alloys + machines")
print(f"  Lang: en_us + pl_pl updated")
