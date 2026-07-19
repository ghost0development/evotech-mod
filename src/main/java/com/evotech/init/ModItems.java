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

    // ===== ERA 1: STONE AGE =====
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

    // ===== ERA 2: COPPER AGE =====
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
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ===== ERA 3: BRONZE AGE =====
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
    public static final RegistryObject<Item> BRONZE_HELMET = ITEMS.register("bronze_helmet",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BOOTS = ITEMS.register("bronze_boots",
            () -> new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ===== ERA 4: IRON/STEEL AGE =====
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
    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ===== ERA 5: INDUSTRIAL AGE - TUNGSTEN =====
    public static final RegistryObject<Item> TUNGSTEN_PICKAXE = ITEMS.register("tungsten_pickaxe",
            () -> new PickaxeItem(ModTier.TUNGSTEN, 3, -2.6F, new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> TUNGSTEN_AXE = ITEMS.register("tungsten_axe",
            () -> new AxeItem(ModTier.TUNGSTEN, 7.0F, -2.8F, new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> TUNGSTEN_SHOVEL = ITEMS.register("tungsten_shovel",
            () -> new ShovelItem(ModTier.TUNGSTEN, 2.5F, -2.8F, new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> TUNGSTEN_HOE = ITEMS.register("tungsten_hoe",
            () -> new HoeItem(ModTier.TUNGSTEN, 1, -0.8F, new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> TUNGSTEN_SWORD = ITEMS.register("tungsten_sword",
            () -> new SwordItem(ModTier.TUNGSTEN, 5, -2.0F, new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> TUNGSTEN_HELMET = ITEMS.register("tungsten_helmet",
            () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_CHESTPLATE = ITEMS.register("tungsten_chestplate",
            () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_LEGGINGS = ITEMS.register("tungsten_leggings",
            () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_BOOTS = ITEMS.register("tungsten_boots",
            () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ===== ERA 6: ELECTRIC AGE - TITANIUM =====
    public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModTier.TITANIUM, 4, -2.4F, new Item.Properties().durability(1800)));
    public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModTier.TITANIUM, 8.0F, -2.6F, new Item.Properties().durability(1800)));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModTier.TITANIUM, 3.0F, -2.6F, new Item.Properties().durability(1800)));
    public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModTier.TITANIUM, 2, -0.6F, new Item.Properties().durability(1800)));
    public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(ModTier.TITANIUM, 6, -1.8F, new Item.Properties().durability(1800)));
    public static final RegistryObject<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ===== ERA 7: DIGITAL AGE - IRIDIUM =====
    public static final RegistryObject<Item> IRIDIUM_PICKAXE = ITEMS.register("iridium_pickaxe",
            () -> new PickaxeItem(ModTier.IRIDIUM, 5, -2.2F, new Item.Properties().durability(2500)));
    public static final RegistryObject<Item> IRIDIUM_AXE = ITEMS.register("iridium_axe",
            () -> new AxeItem(ModTier.IRIDIUM, 9.0F, -2.4F, new Item.Properties().durability(2500)));
    public static final RegistryObject<Item> IRIDIUM_SHOVEL = ITEMS.register("iridium_shovel",
            () -> new ShovelItem(ModTier.IRIDIUM, 3.5F, -2.4F, new Item.Properties().durability(2500)));
    public static final RegistryObject<Item> IRIDIUM_HOE = ITEMS.register("iridium_hoe",
            () -> new HoeItem(ModTier.IRIDIUM, 3, -0.4F, new Item.Properties().durability(2500)));
    public static final RegistryObject<Item> IRIDIUM_SWORD = ITEMS.register("iridium_sword",
            () -> new SwordItem(ModTier.IRIDIUM, 7, -1.6F, new Item.Properties().durability(2500)));
    public static final RegistryObject<Item> IRIDIUM_HELMET = ITEMS.register("iridium_helmet",
            () -> new ArmorItem(ModArmorMaterial.IRIDIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> IRIDIUM_CHESTPLATE = ITEMS.register("iridium_chestplate",
            () -> new ArmorItem(ModArmorMaterial.IRIDIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> IRIDIUM_LEGGINGS = ITEMS.register("iridium_leggings",
            () -> new ArmorItem(ModArmorMaterial.IRIDIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> IRIDIUM_BOOTS = ITEMS.register("iridium_boots",
            () -> new ArmorItem(ModArmorMaterial.IRIDIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ===== MŁOTY =====
    public static final RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
            () -> new PickaxeItem(Tiers.STONE, 2, -3.2F, new Item.Properties().durability(150)));
    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new PickaxeItem(Tiers.IRON, 3, -2.8F, new Item.Properties().durability(400)));
    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            () -> new PickaxeItem(ModTier.STEEL, 3, -2.8F, new Item.Properties().durability(700)));
    public static final RegistryObject<Item> TUNGSTEN_HAMMER = ITEMS.register("tungsten_hammer",
            () -> new PickaxeItem(ModTier.TUNGSTEN, 4, -2.6F, new Item.Properties().durability(1000)));
    public static final RegistryObject<Item> TITANIUM_HAMMER = ITEMS.register("titanium_hammer",
            () -> new PickaxeItem(ModTier.TITANIUM, 5, -2.4F, new Item.Properties().durability(1500)));
    public static final RegistryObject<Item> IRIDIUM_HAMMER = ITEMS.register("iridium_hammer",
            () -> new PickaxeItem(ModTier.IRIDIUM, 6, -2.2F, new Item.Properties().durability(2200)));

    // ===== INGOTY =====
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
    public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHROMIUM_INGOT = ITEMS.register("chromium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRIDIUM_INGOT = ITEMS.register("iridium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INVAR_INGOT = ITEMS.register("invar_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NICHROME_INGOT = ITEMS.register("nichrome_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_CARBIDE_INGOT = ITEMS.register("tungsten_carbide_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_INGOT = ITEMS.register("titanium_alloy_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPER_ALLOY_INGOT = ITEMS.register("super_alloy_ingot", () -> new Item(new Item.Properties()));

    // ===== NUGGET =====
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
    public static final RegistryObject<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHROMIUM_NUGGET = ITEMS.register("chromium_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_NUGGET = ITEMS.register("cobalt_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LITHIUM_NUGGET = ITEMS.register("lithium_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRIDIUM_NUGGET = ITEMS.register("iridium_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INVAR_NUGGET = ITEMS.register("invar_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NICHROME_NUGGET = ITEMS.register("nichrome_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_CARBIDE_NUGGET = ITEMS.register("tungsten_carbide_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_NUGGET = ITEMS.register("titanium_alloy_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPER_ALLOY_NUGGET = ITEMS.register("super_alloy_nugget", () -> new Item(new Item.Properties()));

    // ===== PYŁY / MATERIAŁY PRZETWÓRCZE =====
    public static final RegistryObject<Item> CRUSHED_TIN = ITEMS.register("crushed_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_LEAD = ITEMS.register("crushed_lead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_SILVER = ITEMS.register("crushed_silver", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_ALUMINUM = ITEMS.register("crushed_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_NICKEL = ITEMS.register("crushed_nickel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_ZINC = ITEMS.register("crushed_zinc", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_TUNGSTEN = ITEMS.register("crushed_tungsten", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_TITANIUM = ITEMS.register("crushed_titanium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_PLATINUM = ITEMS.register("crushed_platinum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_CHROMIUM = ITEMS.register("crushed_chromium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_COBALT = ITEMS.register("crushed_cobalt", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_URANIUM = ITEMS.register("crushed_uranium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_LITHIUM = ITEMS.register("crushed_lithium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_IRIDIUM = ITEMS.register("crushed_iridium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DUST_TIN = ITEMS.register("dust_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_LEAD = ITEMS.register("dust_lead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_SILVER = ITEMS.register("dust_silver", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_IRON = ITEMS.register("dust_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_GOLD = ITEMS.register("dust_gold", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_ALUMINUM = ITEMS.register("dust_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_NICKEL = ITEMS.register("dust_nickel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_ZINC = ITEMS.register("dust_zinc", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_TUNGSTEN = ITEMS.register("dust_tungsten", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_TITANIUM = ITEMS.register("dust_titanium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_PLATINUM = ITEMS.register("dust_platinum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_CHROMIUM = ITEMS.register("dust_chromium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_COBALT = ITEMS.register("dust_cobalt", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_URANIUM = ITEMS.register("dust_uranium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_LITHIUM = ITEMS.register("dust_lithium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUST_IRIDIUM = ITEMS.register("dust_iridium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke", () -> new Item(new Item.Properties()));

    // ===== KOŁA ZĘBATE =====
    public static final RegistryObject<Item> GEAR_COPPER = ITEMS.register("gear_copper", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_TIN = ITEMS.register("gear_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_BRONZE = ITEMS.register("gear_bronze", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_IRON = ITEMS.register("gear_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_STEEL = ITEMS.register("gear_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_ALUMINUM = ITEMS.register("gear_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_TUNGSTEN = ITEMS.register("gear_tungsten", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_TITANIUM = ITEMS.register("gear_titanium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_PLATINUM = ITEMS.register("gear_platinum", () -> new Item(new Item.Properties()));

    // ===== PŁYTY =====
    public static final RegistryObject<Item> PLATE_COPPER = ITEMS.register("plate_copper", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_TIN = ITEMS.register("plate_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_BRONZE = ITEMS.register("plate_bronze", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_IRON = ITEMS.register("plate_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_STEEL = ITEMS.register("plate_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_ALUMINUM = ITEMS.register("plate_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_NICKEL = ITEMS.register("plate_nickel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_TUNGSTEN = ITEMS.register("plate_tungsten", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_TITANIUM = ITEMS.register("plate_titanium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_PLATINUM = ITEMS.register("plate_platinum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_CHROMIUM = ITEMS.register("plate_chromium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_IRIDIUM = ITEMS.register("plate_iridium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_INVAR = ITEMS.register("plate_invar", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_NICHROME = ITEMS.register("plate_nichrome", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATE_TUNGSTEN_CARBIDE = ITEMS.register("plate_tungsten_carbide", () -> new Item(new Item.Properties()));

    // ===== PRĘTY =====
    public static final RegistryObject<Item> ROD_COPPER = ITEMS.register("rod_copper", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_IRON = ITEMS.register("rod_iron", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_STEEL = ITEMS.register("rod_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_BRONZE = ITEMS.register("rod_bronze", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_ALUMINUM = ITEMS.register("rod_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_TUNGSTEN = ITEMS.register("rod_tungsten", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_TITANIUM = ITEMS.register("rod_titanium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_PLATINUM = ITEMS.register("rod_platinum", () -> new Item(new Item.Properties()));

    // ===== ELEKTRONIKA =====
    public static final RegistryObject<Item> BASIC_CIRCUIT = ITEMS.register("basic_circuit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = ITEMS.register("advanced_circuit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> QUANTUM_CHIP = ITEMS.register("quantum_chip", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILICON_WAFER = ITEMS.register("silicon_wafer", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INSULATED_COPPER_WIRE = ITEMS.register("insulated_copper_wire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RESISTOR = ITEMS.register("resistor", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CAPACITOR = ITEMS.register("capacitor", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRANSISTOR = ITEMS.register("transistor", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PROCESSOR = ITEMS.register("processor", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADVANCED_PROCESSOR = ITEMS.register("advanced_processor", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELECTRIC_MOTOR = ITEMS.register("electric_motor", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MACHINE_PART = ITEMS.register("machine_part", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_FRAME = ITEMS.register("steel_frame", () -> new Item(new Item.Properties()));

    // ===== PALIWO =====
    public static final RegistryObject<Item> BIOCHAR = ITEMS.register("biochar", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CREOSOTE = ITEMS.register("creosote", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // ===== KOMPONENTY =====
    public static final RegistryObject<Item> CRUDE_RUBBER = ITEMS.register("crude_rubber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MACHINE_CASING = ITEMS.register("machine_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEAM_CANISTER = ITEMS.register("steam_canister", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // ===== PORADNIK =====
    public static final RegistryObject<Item> ENGINEERING_GUIDE = ITEMS.register("engineering_guide",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // ===== BLOCK ITEMS - STORAGE =====
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
    public static final RegistryObject<Item> TUNGSTEN_BLOCK = ITEMS.register("tungsten_block", () -> new BlockItem(ModBlocks.TUNGSTEN_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_BLOCK = ITEMS.register("titanium_block", () -> new BlockItem(ModBlocks.TITANIUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_BLOCK = ITEMS.register("platinum_block", () -> new BlockItem(ModBlocks.PLATINUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHROMIUM_BLOCK = ITEMS.register("chromium_block", () -> new BlockItem(ModBlocks.CHROMIUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> COBALT_BLOCK = ITEMS.register("cobalt_block", () -> new BlockItem(ModBlocks.COBALT_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_BLOCK = ITEMS.register("uranium_block", () -> new BlockItem(ModBlocks.URANIUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> LITHIUM_BLOCK = ITEMS.register("lithium_block", () -> new BlockItem(ModBlocks.LITHIUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRIDIUM_BLOCK = ITEMS.register("iridium_block", () -> new BlockItem(ModBlocks.IRIDIUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> INVAR_BLOCK = ITEMS.register("invar_block", () -> new BlockItem(ModBlocks.INVAR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> NICHROME_BLOCK = ITEMS.register("nichrome_block", () -> new BlockItem(ModBlocks.NICHROME_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_CARBIDE_BLOCK = ITEMS.register("tungsten_carbide_block", () -> new BlockItem(ModBlocks.TUNGSTEN_CARBIDE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_BLOCK = ITEMS.register("titanium_alloy_block", () -> new BlockItem(ModBlocks.TITANIUM_ALLOY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SUPER_ALLOY_BLOCK = ITEMS.register("super_alloy_block", () -> new BlockItem(ModBlocks.SUPER_ALLOY_BLOCK.get(), new Item.Properties()));

    // ===== BLOCK ITEMS - ORES =====
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
    public static final RegistryObject<Item> TUNGSTEN_ORE_BLOCK = ITEMS.register("tungsten_ore", () -> new BlockItem(ModBlocks.TUNGSTEN_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_TUNGSTEN_ORE_BLOCK = ITEMS.register("deepslate_tungsten_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_ORE_BLOCK = ITEMS.register("titanium_ore", () -> new BlockItem(ModBlocks.TITANIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_TITANIUM_ORE_BLOCK = ITEMS.register("deepslate_titanium_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_ORE_BLOCK = ITEMS.register("platinum_ore", () -> new BlockItem(ModBlocks.PLATINUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_PLATINUM_ORE_BLOCK = ITEMS.register("deepslate_platinum_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHROMIUM_ORE_BLOCK = ITEMS.register("chromium_ore", () -> new BlockItem(ModBlocks.CHROMIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_CHROMIUM_ORE_BLOCK = ITEMS.register("deepslate_chromium_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> COBALT_ORE_BLOCK = ITEMS.register("cobalt_ore", () -> new BlockItem(ModBlocks.COBALT_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_COBALT_ORE_BLOCK = ITEMS.register("deepslate_cobalt_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_COBALT_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_ORE_BLOCK = ITEMS.register("uranium_ore", () -> new BlockItem(ModBlocks.URANIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_URANIUM_ORE_BLOCK = ITEMS.register("deepslate_uranium_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LITHIUM_ORE_BLOCK = ITEMS.register("lithium_ore", () -> new BlockItem(ModBlocks.LITHIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_LITHIUM_ORE_BLOCK = ITEMS.register("deepslate_lithium_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_LITHIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRIDIUM_ORE_BLOCK = ITEMS.register("iridium_ore", () -> new BlockItem(ModBlocks.IRIDIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_IRIDIUM_ORE_BLOCK = ITEMS.register("deepslate_iridium_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_IRIDIUM_ORE.get(), new Item.Properties()));

    // ===== BLOCK ITEMS - MACHINES & FUNCTIONAL =====
    public static final RegistryObject<Item> CRUSHING_TABLE_BLOCK = ITEMS.register("crushing_table", () -> new BlockItem(ModBlocks.CRUSHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALLOY_SMELTER_BLOCK = ITEMS.register("alloy_smelter", () -> new BlockItem(ModBlocks.ALLOY_SMELTER.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELECTRIC_FURNACE_BLOCK = ITEMS.register("electric_furnace", () -> new BlockItem(ModBlocks.ELECTRIC_FURNACE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MACERATOR_BLOCK = ITEMS.register("macerator", () -> new BlockItem(ModBlocks.MACERATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> GENERATOR_BLOCK = ITEMS.register("generator", () -> new BlockItem(ModBlocks.GENERATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> COMPRESSOR_BLOCK = ITEMS.register("compressor", () -> new BlockItem(ModBlocks.COMPRESSOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> MACHINE_FRAME_BLOCK = ITEMS.register("machine_frame", () -> new BlockItem(ModBlocks.MACHINE_FRAME.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_COIL_BLOCK = ITEMS.register("copper_coil", () -> new BlockItem(ModBlocks.COPPER_COIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEAM_ENGINE_BLOCK = ITEMS.register("steam_engine", () -> new BlockItem(ModBlocks.STEAM_ENGINE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WIRE_MILL_BLOCK = ITEMS.register("wire_mill", () -> new BlockItem(ModBlocks.WIRE_MILL.get(), new Item.Properties()));
    public static final RegistryObject<Item> ASSEMBLING_MACHINE_BLOCK = ITEMS.register("assembling_machine", () -> new BlockItem(ModBlocks.ASSEMBLING_MACHINE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MATTER_SCANNER_BLOCK = ITEMS.register("matter_scanner", () -> new BlockItem(ModBlocks.MATTER_SCANNER.get(), new Item.Properties()));

    // ===== BLOCK ITEMS - CABLES =====
    public static final RegistryObject<Item> COPPER_CABLE_BLOCK = ITEMS.register("copper_cable", () -> new BlockItem(ModBlocks.COPPER_CABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_CABLE_BLOCK = ITEMS.register("gold_cable", () -> new BlockItem(ModBlocks.GOLD_CABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SUPER_CABLE_BLOCK = ITEMS.register("super_cable", () -> new BlockItem(ModBlocks.SUPER_CABLE.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
