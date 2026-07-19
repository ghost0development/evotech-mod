package com.evotech.init;

import com.evotech.EvoTech;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EvoTech.MOD_ID);

    public static final RegistryObject<CreativeModeTab> EVOTECH_TAB = CREATIVE_TABS.register("evotech_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.evotech"))
                    .icon(() -> new ItemStack(ModItems.IRIDIUM_INGOT.get()))
                    .displayItems((params, output) -> {
                        // === ERA 2: COPPER AGE ===
                        output.accept(ModItems.COPPER_PICKAXE.get());
                        output.accept(ModItems.COPPER_AXE.get());
                        output.accept(ModItems.COPPER_SHOVEL.get());
                        output.accept(ModItems.COPPER_HOE.get());
                        output.accept(ModItems.COPPER_SWORD.get());
                        output.accept(ModItems.COPPER_HELMET.get());
                        output.accept(ModItems.COPPER_CHESTPLATE.get());
                        output.accept(ModItems.COPPER_LEGGINGS.get());
                        output.accept(ModItems.COPPER_BOOTS.get());

                        // === ERA 3: BRONZE AGE ===
                        output.accept(ModItems.BRONZE_PICKAXE.get());
                        output.accept(ModItems.BRONZE_AXE.get());
                        output.accept(ModItems.BRONZE_SHOVEL.get());
                        output.accept(ModItems.BRONZE_HOE.get());
                        output.accept(ModItems.BRONZE_SWORD.get());
                        output.accept(ModItems.BRONZE_HELMET.get());
                        output.accept(ModItems.BRONZE_CHESTPLATE.get());
                        output.accept(ModItems.BRONZE_LEGGINGS.get());
                        output.accept(ModItems.BRONZE_BOOTS.get());

                        // === ERA 4: STEEL AGE ===
                        output.accept(ModItems.STEEL_PICKAXE.get());
                        output.accept(ModItems.STEEL_AXE.get());
                        output.accept(ModItems.STEEL_SHOVEL.get());
                        output.accept(ModItems.STEEL_HOE.get());
                        output.accept(ModItems.STEEL_SWORD.get());
                        output.accept(ModItems.STEEL_HELMET.get());
                        output.accept(ModItems.STEEL_CHESTPLATE.get());
                        output.accept(ModItems.STEEL_LEGGINGS.get());
                        output.accept(ModItems.STEEL_BOOTS.get());

                        // === ERA 5: INDUSTRIAL - TUNGSTEN ===
                        output.accept(ModItems.TUNGSTEN_PICKAXE.get());
                        output.accept(ModItems.TUNGSTEN_AXE.get());
                        output.accept(ModItems.TUNGSTEN_SHOVEL.get());
                        output.accept(ModItems.TUNGSTEN_HOE.get());
                        output.accept(ModItems.TUNGSTEN_SWORD.get());
                        output.accept(ModItems.TUNGSTEN_HELMET.get());
                        output.accept(ModItems.TUNGSTEN_CHESTPLATE.get());
                        output.accept(ModItems.TUNGSTEN_LEGGINGS.get());
                        output.accept(ModItems.TUNGSTEN_BOOTS.get());

                        // === ERA 6: ELECTRIC - TITANIUM ===
                        output.accept(ModItems.TITANIUM_PICKAXE.get());
                        output.accept(ModItems.TITANIUM_AXE.get());
                        output.accept(ModItems.TITANIUM_SHOVEL.get());
                        output.accept(ModItems.TITANIUM_HOE.get());
                        output.accept(ModItems.TITANIUM_SWORD.get());
                        output.accept(ModItems.TITANIUM_HELMET.get());
                        output.accept(ModItems.TITANIUM_CHESTPLATE.get());
                        output.accept(ModItems.TITANIUM_LEGGINGS.get());
                        output.accept(ModItems.TITANIUM_BOOTS.get());

                        // === ERA 7: DIGITAL - IRIDIUM ===
                        output.accept(ModItems.IRIDIUM_PICKAXE.get());
                        output.accept(ModItems.IRIDIUM_AXE.get());
                        output.accept(ModItems.IRIDIUM_SHOVEL.get());
                        output.accept(ModItems.IRIDIUM_HOE.get());
                        output.accept(ModItems.IRIDIUM_SWORD.get());
                        output.accept(ModItems.IRIDIUM_HELMET.get());
                        output.accept(ModItems.IRIDIUM_CHESTPLATE.get());
                        output.accept(ModItems.IRIDIUM_LEGGINGS.get());
                        output.accept(ModItems.IRIDIUM_BOOTS.get());

                        // === MŁOTY ===
                        output.accept(ModItems.STONE_HAMMER.get());
                        output.accept(ModItems.IRON_HAMMER.get());
                        output.accept(ModItems.STEEL_HAMMER.get());
                        output.accept(ModItems.TUNGSTEN_HAMMER.get());
                        output.accept(ModItems.TITANIUM_HAMMER.get());
                        output.accept(ModItems.IRIDIUM_HAMMER.get());

                        // === INGOTY ===
                        output.accept(ModItems.TIN_INGOT.get());
                        output.accept(net.minecraft.world.item.Items.COPPER_INGOT);
                        output.accept(ModItems.BRONZE_INGOT.get());
                        output.accept(ModItems.LEAD_INGOT.get());
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.SILVER_INGOT.get());
                        output.accept(ModItems.ALUMINUM_INGOT.get());
                        output.accept(ModItems.NICKEL_INGOT.get());
                        output.accept(ModItems.ZINC_INGOT.get());
                        output.accept(ModItems.ELECTRUM_INGOT.get());
                        output.accept(ModItems.CONSTANTAN_INGOT.get());
                        output.accept(ModItems.TUNGSTEN_INGOT.get());
                        output.accept(ModItems.TITANIUM_INGOT.get());
                        output.accept(ModItems.PLATINUM_INGOT.get());
                        output.accept(ModItems.CHROMIUM_INGOT.get());
                        output.accept(ModItems.COBALT_INGOT.get());
                        output.accept(ModItems.URANIUM_INGOT.get());
                        output.accept(ModItems.LITHIUM_INGOT.get());
                        output.accept(ModItems.IRIDIUM_INGOT.get());
                        output.accept(ModItems.INVAR_INGOT.get());
                        output.accept(ModItems.NICHROME_INGOT.get());
                        output.accept(ModItems.TUNGSTEN_CARBIDE_INGOT.get());
                        output.accept(ModItems.TITANIUM_ALLOY_INGOT.get());
                        output.accept(ModItems.SUPER_ALLOY_INGOT.get());

                        // === NUGGETS ===
                        output.accept(ModItems.TIN_NUGGET.get());
                        output.accept(ModItems.BRONZE_NUGGET.get());
                        output.accept(ModItems.LEAD_NUGGET.get());
                        output.accept(ModItems.STEEL_NUGGET.get());
                        output.accept(ModItems.SILVER_NUGGET.get());
                        output.accept(ModItems.ALUMINUM_NUGGET.get());
                        output.accept(ModItems.NICKEL_NUGGET.get());
                        output.accept(ModItems.ZINC_NUGGET.get());
                        output.accept(ModItems.ELECTRUM_NUGGET.get());
                        output.accept(ModItems.CONSTANTAN_NUGGET.get());
                        output.accept(ModItems.TUNGSTEN_NUGGET.get());
                        output.accept(ModItems.TITANIUM_NUGGET.get());
                        output.accept(ModItems.PLATINUM_NUGGET.get());
                        output.accept(ModItems.CHROMIUM_NUGGET.get());
                        output.accept(ModItems.COBALT_NUGGET.get());
                        output.accept(ModItems.URANIUM_NUGGET.get());
                        output.accept(ModItems.LITHIUM_NUGGET.get());
                        output.accept(ModItems.IRIDIUM_NUGGET.get());
                        output.accept(ModItems.INVAR_NUGGET.get());
                        output.accept(ModItems.NICHROME_NUGGET.get());
                        output.accept(ModItems.TUNGSTEN_CARBIDE_NUGGET.get());
                        output.accept(ModItems.TITANIUM_ALLOY_NUGGET.get());
                        output.accept(ModItems.SUPER_ALLOY_NUGGET.get());

                        // === ORES ===
                        output.accept(ModItems.TIN_ORE_BLOCK.get());
                        output.accept(ModItems.LEAD_ORE_BLOCK.get());
                        output.accept(ModItems.SILVER_ORE_BLOCK.get());
                        output.accept(ModItems.ALUMINUM_ORE_BLOCK.get());
                        output.accept(ModItems.NICKEL_ORE_BLOCK.get());
                        output.accept(ModItems.ZINC_ORE_BLOCK.get());
                        output.accept(ModItems.TUNGSTEN_ORE_BLOCK.get());
                        output.accept(ModItems.TITANIUM_ORE_BLOCK.get());
                        output.accept(ModItems.PLATINUM_ORE_BLOCK.get());
                        output.accept(ModItems.CHROMIUM_ORE_BLOCK.get());
                        output.accept(ModItems.COBALT_ORE_BLOCK.get());
                        output.accept(ModItems.URANIUM_ORE_BLOCK.get());
                        output.accept(ModItems.LITHIUM_ORE_BLOCK.get());
                        output.accept(ModItems.IRIDIUM_ORE_BLOCK.get());

                        // === DUSTS + CRUSHED ===
                        output.accept(ModItems.DUST_TIN.get());
                        output.accept(ModItems.DUST_LEAD.get());
                        output.accept(ModItems.DUST_SILVER.get());
                        output.accept(ModItems.DUST_IRON.get());
                        output.accept(ModItems.DUST_GOLD.get());
                        output.accept(ModItems.DUST_ALUMINUM.get());
                        output.accept(ModItems.DUST_NICKEL.get());
                        output.accept(ModItems.DUST_ZINC.get());
                        output.accept(ModItems.DUST_TUNGSTEN.get());
                        output.accept(ModItems.DUST_TITANIUM.get());
                        output.accept(ModItems.DUST_PLATINUM.get());
                        output.accept(ModItems.DUST_CHROMIUM.get());
                        output.accept(ModItems.DUST_COBALT.get());
                        output.accept(ModItems.DUST_URANIUM.get());
                        output.accept(ModItems.DUST_LITHIUM.get());
                        output.accept(ModItems.DUST_IRIDIUM.get());
                        output.accept(ModItems.COAL_COKE.get());
                        output.accept(ModItems.BIOCHAR.get());

                        // === GEARS ===
                        output.accept(ModItems.GEAR_COPPER.get());
                        output.accept(ModItems.GEAR_TIN.get());
                        output.accept(ModItems.GEAR_BRONZE.get());
                        output.accept(ModItems.GEAR_IRON.get());
                        output.accept(ModItems.GEAR_STEEL.get());
                        output.accept(ModItems.GEAR_ALUMINUM.get());
                        output.accept(ModItems.GEAR_TUNGSTEN.get());
                        output.accept(ModItems.GEAR_TITANIUM.get());
                        output.accept(ModItems.GEAR_PLATINUM.get());

                        // === PLATES ===
                        output.accept(ModItems.PLATE_COPPER.get());
                        output.accept(ModItems.PLATE_TIN.get());
                        output.accept(ModItems.PLATE_BRONZE.get());
                        output.accept(ModItems.PLATE_IRON.get());
                        output.accept(ModItems.PLATE_STEEL.get());
                        output.accept(ModItems.PLATE_ALUMINUM.get());
                        output.accept(ModItems.PLATE_NICKEL.get());
                        output.accept(ModItems.PLATE_TUNGSTEN.get());
                        output.accept(ModItems.PLATE_TITANIUM.get());
                        output.accept(ModItems.PLATE_PLATINUM.get());
                        output.accept(ModItems.PLATE_CHROMIUM.get());
                        output.accept(ModItems.PLATE_IRIDIUM.get());
                        output.accept(ModItems.PLATE_INVAR.get());
                        output.accept(ModItems.PLATE_NICHROME.get());
                        output.accept(ModItems.PLATE_TUNGSTEN_CARBIDE.get());

                        // === RODS ===
                        output.accept(ModItems.ROD_COPPER.get());
                        output.accept(ModItems.ROD_IRON.get());
                        output.accept(ModItems.ROD_STEEL.get());
                        output.accept(ModItems.ROD_BRONZE.get());
                        output.accept(ModItems.ROD_ALUMINUM.get());
                        output.accept(ModItems.ROD_TUNGSTEN.get());
                        output.accept(ModItems.ROD_TITANIUM.get());
                        output.accept(ModItems.ROD_PLATINUM.get());

                        // === ELECTRONICS ===
                        output.accept(ModItems.RESISTOR.get());
                        output.accept(ModItems.CAPACITOR.get());
                        output.accept(ModItems.TRANSISTOR.get());
                        output.accept(ModItems.SILICON_WAFER.get());
                        output.accept(ModItems.COPPER_WIRE.get());
                        output.accept(ModItems.INSULATED_COPPER_WIRE.get());
                        output.accept(ModItems.BASIC_CIRCUIT.get());
                        output.accept(ModItems.PROCESSOR.get());
                        output.accept(ModItems.ADVANCED_CIRCUIT.get());
                        output.accept(ModItems.ADVANCED_PROCESSOR.get());
                        output.accept(ModItems.QUANTUM_CHIP.get());
                        output.accept(ModItems.ELECTRIC_MOTOR.get());
                        output.accept(ModItems.MACHINE_PART.get());
                        output.accept(ModItems.STEEL_FRAME.get());

                        // === OTHER ITEMS ===
                        output.accept(ModItems.CRUDE_RUBBER.get());
                        output.accept(ModItems.MACHINE_CASING.get());
                        output.accept(ModItems.STEAM_CANISTER.get());
                        output.accept(ModItems.CREOSOTE.get());

                        // === STORAGE BLOCKS ===
                        output.accept(ModBlocks.TIN_BLOCK.get());
                        output.accept(ModBlocks.BRONZE_BLOCK.get());
                        output.accept(ModBlocks.LEAD_BLOCK.get());
                        output.accept(ModBlocks.STEEL_BLOCK.get());
                        output.accept(ModBlocks.SILVER_BLOCK.get());
                        output.accept(ModBlocks.ALUMINUM_BLOCK.get());
                        output.accept(ModBlocks.NICKEL_BLOCK.get());
                        output.accept(ModBlocks.ZINC_BLOCK.get());
                        output.accept(ModBlocks.ELECTRUM_BLOCK.get());
                        output.accept(ModBlocks.CONSTANTAN_BLOCK.get());
                        output.accept(ModBlocks.TUNGSTEN_BLOCK.get());
                        output.accept(ModBlocks.TITANIUM_BLOCK.get());
                        output.accept(ModBlocks.PLATINUM_BLOCK.get());
                        output.accept(ModBlocks.CHROMIUM_BLOCK.get());
                        output.accept(ModBlocks.COBALT_BLOCK.get());
                        output.accept(ModBlocks.URANIUM_BLOCK.get());
                        output.accept(ModBlocks.LITHIUM_BLOCK.get());
                        output.accept(ModBlocks.IRIDIUM_BLOCK.get());
                        output.accept(ModBlocks.INVAR_BLOCK.get());
                        output.accept(ModBlocks.NICHROME_BLOCK.get());
                        output.accept(ModBlocks.TUNGSTEN_CARBIDE_BLOCK.get());
                        output.accept(ModBlocks.TITANIUM_ALLOY_BLOCK.get());
                        output.accept(ModBlocks.SUPER_ALLOY_BLOCK.get());

                        // === FUNCTIONAL BLOCKS ===
                        output.accept(ModBlocks.MACHINE_FRAME.get());
                        output.accept(ModBlocks.COPPER_COIL.get());

                        // === MASZYNY ===
                        output.accept(ModBlocks.CRUSHING_TABLE.get());
                        output.accept(ModBlocks.ALLOY_SMELTER.get());
                        output.accept(ModBlocks.ELECTRIC_FURNACE.get());
                        output.accept(ModBlocks.MACERATOR.get());
                        output.accept(ModBlocks.GENERATOR.get());
                        output.accept(ModBlocks.COMPRESSOR.get());
                        output.accept(ModBlocks.STEAM_ENGINE.get());
                        output.accept(ModBlocks.WIRE_MILL.get());
                        output.accept(ModBlocks.ASSEMBLING_MACHINE.get());
                        output.accept(ModBlocks.MATTER_SCANNER.get());

                        // === KABLE ===
                        output.accept(ModBlocks.COPPER_CABLE.get());
                        output.accept(ModBlocks.GOLD_CABLE.get());
                        output.accept(ModBlocks.SUPER_CABLE.get());

                        // === PORADNIK ===
                        output.accept(ModItems.ENGINEERING_GUIDE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
