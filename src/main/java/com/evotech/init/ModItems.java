package com.evotech.init;

import com.evotech.EvoTech;
import com.evotech.item.ModArmorMaterial;
import com.evotech.item.ModTier;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EvoTech.MOD_ID);

    // === INGOTY ===
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CONSTANTAN_INGOT = ITEMS.register("constantan_ingot", () -> new Item(new Item.Properties()));

    // === NUGGET ===
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELECTRUM_NUGGET = ITEMS.register("electrum_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CONSTANTAN_NUGGET = ITEMS.register("constantan_nugget", () -> new Item(new Item.Properties()));

    // === PYŁY / MATERIAŁY PRZETWÓRCZE ===
    public static final RegistryObject<Item> CRUSHED_TIN = ITEMS.register("crushed_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_LEAD = ITEMS.register("crushed_lead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_SILVER = ITEMS.register("crushed_silver", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_ALUMINUM = ITEMS.register("crushed_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_NICKEL = ITEMS.register("crushed_nickel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_ZINC = ITEMS.register("crushed_zinc", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_TIN = ITEMS.register("dust_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_LEAD = ITEMS.register("dust_lead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_SILVER = ITEMS.register("dust_silver", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_IRON = ITEMS.register("dust_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_GOLD = ITEMS.register("dust_gold", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_ALUMINUM = ITEMS.register("dust_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_NICKEL = ITEMS.register("dust_nickel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_ZINC = ITEMS.register("dust_zinc", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke", () -> new Item(new Item.Properties()));

    // === KOŁA ZĘBATE ===
    public static final RegistryObject<Item> GEAR_COPPER = ITEMS.register("gear_copper", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_TIN = ITEMS.register("gear_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_BRONZE = ITEMS.register("gear_bronze", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_IRON = ITEMS.register("gear_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_STEEL = ITEMS.register("gear_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_ALUMINUM = ITEMS.register("gear_aluminum", () -> new Item(new Item.Properties()));

    // === PŁYTY ===
    public static final RegistryObject<Item> PLATE_COPPER = ITEMS.register("plate_copper", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_TIN = ITEMS.register("plate_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_BRONZE = ITEMS.register("plate_bronze", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_IRON = ITEMS.register("plate_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_STEEL = ITEMS.register("plate_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_ALUMINUM = ITEMS.register("plate_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_NICKEL = ITEMS.register("plate_nickel", () -> new Item(new Item.Properties()));

    // === PRĘTY ===
    public static final RegistryObject<Item> ROD_COPPER = ITEMS.register("rod_copper", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_IRON = ITEMS.register("rod_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_STEEL = ITEMS.register("rod_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_BRONZE = ITEMS.register("rod_bronze", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_ALUMINUM = ITEMS.register("rod_aluminum", () -> new Item(new Item.Properties()));

    // === NARZĘDZIA - FLINT ===
    public static final RegistryObject<Item> FLINT_PICKAXE = ITEMS.register("flint_pickaxe",
            () -> new PickaxeItem(ModTier.FLINT, 1, -2.8F, new Item.Properties().durability(200)));
    public static final RegistryObject<Item> FLINT_AXE = ITEMS.register("flint_axe",
            () -> new AxeItem(ModTier.FLINT, 5.0F, -3.0F, new Item.Properties().durability(200)));
    public static final RegistryObject<Item> FLINT_SHOVEL = ITEMS.register("flint_shovel",
            () -> new ShovelItem(ModTier.FLINT, 1.5F, -3.0F, new Item.Properties().durability(200)));
    public static final RegistryObject<Item> FLINT_HOE = ITEMS.register("flint_hoe",
            () -> new HoeItem(ModTier.FLINT, -2, -1.0F, new Item.Properties().durability(200)));
    public static final RegistryObject<Item> FLINT_SWORD = ITEMS.register("flint_sword",
            () -> new SwordItem(ModTier.FLINT, 3, -2.4F, new Item.Properties().durability(200)));

    // === NARZĘDZIA - COPPER ===
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModTier.COPPER, 1, -2.8F, new Item.Properties().durability(300)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModTier.COPPER, 5.0F, -3.0F, new Item.Properties().durability(300)));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModTier.COPPER, 1.5F, -3.0F, new Item.Properties().durability(300)));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModTier.COPPER, -1, -1.0F, new Item.Properties().durability(300)));
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModTier.COPPER, 3, -2.2F, new Item.Properties().durability(300)));

    // === NARZĘDZIA - BRONZE ===
    public static final RegistryObject<Item> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe",
            () -> new PickaxeItem(ModTier.BRONZE, 1, -2.8F, new Item.Properties().durability(500)));
    public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register("bronze_axe",
            () -> new AxeItem(ModTier.BRONZE, 5.0F, -3.0F, new Item.Properties().durability(500)));
    public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel",
            () -> new ShovelItem(ModTier.BRONZE, 1.5F, -3.0F, new Item.Properties().durability(500)));
    public static final RegistryObject<Item> BRONZE_HOE = ITEMS.register("bronze_hoe",
            () -> new HoeItem(ModTier.BRONZE, -1, -1.0F, new Item.Properties().durability(500)));
    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new SwordItem(ModTier.BRONZE, 3, -2.2F, new Item.Properties().durability(500)));

    // === NARZĘDZIA - STEEL ===
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModTier.STEEL, 2, -2.8F, new Item.Properties().durability(800)));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModTier.STEEL, 6.0F, -3.0F, new Item.Properties().durability(800)));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModTier.STEEL, 2.0F, -3.0F, new Item.Properties().durability(800)));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModTier.STEEL, 0, -1.0F, new Item.Properties().durability(800)));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModTier.STEEL, 4, -2.2F, new Item.Properties().durability(800)));

    // === PANCERZ - COPPER ===
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // === PANCERZ - BRONZE ===
    public static final RegistryObject<Item> BRONZE_HELMET = ITEMS.register("bronze_helmet",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BOOTS = ITEMS.register("bronze_boots",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // === PANCERZ - STEEL ===
    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // === ELEKTRONIKA ===
    public static final RegistryObject<Item> BASIC_CIRCUIT = ITEMS.register("basic_circuit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = ITEMS.register("advanced_circuit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILICON_WAFER = ITEMS.register("silicon_wafer", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INSULATED_COPPER_WIRE = ITEMS.register("insulated_copper_wire", () -> new Item(new Item.Properties()));

    // === PALIWO ===
    public static final RegistryObject<Item> BIOCHAR = ITEMS.register("biochar", () -> new Item(new Item.Properties()));

    // === PRZEWODNIK ===
    public static final RegistryObject<Item> ENGINEERING_GUIDE = ITEMS.register("engineering_guide",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // === MŁOTY ===
    public static final RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
            () -> new PickaxeItem(Tiers.STONE, 2, -3.2F, new Item.Properties().durability(150)));
    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new PickaxeItem(Tiers.IRON, 3, -2.8F, new Item.Properties().durability(400)));
    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            () -> new PickaxeItem(ModTier.STEEL, 3, -2.8F, new Item.Properties().durability(700)));

    // === BLOCK ITEMS - STORAGE ===
    public static final RegistryObject<Item> TIN_BLOCK = ITEMS.register("tin_block", () -> new BlockItem(ModBlocks.TIN_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BLOCK = ITEMS.register("bronze_block", () -> new BlockItem(ModBlocks.BRONZE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEAD_BLOCK = ITEMS.register("lead_block", () -> new BlockItem(ModBlocks.LEAD_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block", () -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILVER_BLOCK = ITEMS.register("silver_block", () -> new BlockItem(ModBlocks.SILVER_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_BLOCK = ITEMS.register("aluminum_block", () -> new BlockItem(ModBlocks.ALUMINUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> NICKEL_BLOCK = ITEMS.register("nickel_block", () -> new BlockItem(ModBlocks.NICKEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ZINC_BLOCK = ITEMS.register("zinc_block", () -> new BlockItem(ModBlocks.ZINC_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELECTRUM_BLOCK = ITEMS.register("electrum_block", () -> new BlockItem(ModBlocks.ELECTRUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONSTANTAN_BLOCK = ITEMS.register("constantan_block", () -> new BlockItem(ModBlocks.CONSTANTAN_BLOCK.get(), new Item.Properties()));

    // === BLOCK ITEMS - ORES ===
    public static final RegistryObject<Item> TIN_ORE_BLOCK = ITEMS.register("tin_ore", () -> new BlockItem(ModBlocks.TIN_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE_BLOCK = ITEMS.register("deepslate_tin_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_TIN_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEAD_ORE_BLOCK = ITEMS.register("lead_ore", () -> new BlockItem(ModBlocks.LEAD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_LEAD_ORE_BLOCK = ITEMS.register("deepslate_lead_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_LEAD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILVER_ORE_BLOCK = ITEMS.register("silver_ore", () -> new BlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_SILVER_ORE_BLOCK = ITEMS.register("deepslate_silver_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_SILVER_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_ORE_BLOCK = ITEMS.register("aluminum_ore", () -> new BlockItem(ModBlocks.ALUMINUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_ALUMINUM_ORE_BLOCK = ITEMS.register("deepslate_aluminum_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> NICKEL_ORE_BLOCK = ITEMS.register("nickel_ore", () -> new BlockItem(ModBlocks.NICKEL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_NICKEL_ORE_BLOCK = ITEMS.register("deepslate_nickel_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ZINC_ORE_BLOCK = ITEMS.register("zinc_ore", () -> new BlockItem(ModBlocks.ZINC_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_ZINC_ORE_BLOCK = ITEMS.register("deepslate_zinc_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_ZINC_ORE.get(), new Item.Properties()));

    // === BLOCK ITEMS - MACHINES & FUNCTIONAL ===
    public static final RegistryObject<Item> CRUSHING_TABLE_BLOCK = ITEMS.register("crushing_table", () -> new BlockItem(ModBlocks.CRUSHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALLOY_SMELTER_BLOCK = ITEMS.register("alloy_smelter", () -> new BlockItem(ModBlocks.ALLOY_SMELTER.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELECTRIC_FURNACE_BLOCK = ITEMS.register("electric_furnace", () -> new BlockItem(ModBlocks.ELECTRIC_FURNACE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MACERATOR_BLOCK = ITEMS.register("macerator", () -> new BlockItem(ModBlocks.MACERATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> GENERATOR_BLOCK = ITEMS.register("generator", () -> new BlockItem(ModBlocks.GENERATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> COMPRESSOR_BLOCK = ITEMS.register("compressor", () -> new BlockItem(ModBlocks.COMPRESSOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> MACHINE_FRAME_BLOCK = ITEMS.register("machine_frame", () -> new BlockItem(ModBlocks.MACHINE_FRAME.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_COIL_BLOCK = ITEMS.register("copper_coil", () -> new BlockItem(ModBlocks.COPPER_COIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CABLE_BLOCK = ITEMS.register("copper_cable", () -> new BlockItem(ModBlocks.COPPER_CABLE.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
