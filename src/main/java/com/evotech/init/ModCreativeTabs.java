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
                    .icon(() -> new ItemStack(ModItems.STEEL_INGOT.get()))
                    .displayItems((params, output) -> {
                        // === INGOTY ===
                        output.accept(ModItems.TIN_INGOT.get());
                        output.accept(ModItems.BRONZE_INGOT.get());
                        output.accept(ModItems.LEAD_INGOT.get());
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.SILVER_INGOT.get());
                        output.accept(ModItems.ALUMINUM_INGOT.get());
                        output.accept(ModItems.NICKEL_INGOT.get());
                        output.accept(ModItems.ZINC_INGOT.get());
                        output.accept(ModItems.ELECTRUM_INGOT.get());
                        output.accept(ModItems.CONSTANTAN_INGOT.get());

                        // === NUGGET ===
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

                        // === RUDY ===
                        output.accept(ModItems.TIN_ORE_BLOCK.get());
                        output.accept(ModItems.LEAD_ORE_BLOCK.get());
                        output.accept(ModItems.SILVER_ORE_BLOCK.get());
                        output.accept(ModItems.ALUMINUM_ORE_BLOCK.get());
                        output.accept(ModItems.NICKEL_ORE_BLOCK.get());
                        output.accept(ModItems.ZINC_ORE_BLOCK.get());

                        // === PYŁY / MATERIAŁY ===
                        output.accept(ModItems.CRUSHED_TIN.get());
                        output.accept(ModItems.CRUSHED_LEAD.get());
                        output.accept(ModItems.CRUSHED_SILVER.get());
                        output.accept(ModItems.CRUSHED_ALUMINUM.get());
                        output.accept(ModItems.CRUSHED_NICKEL.get());
                        output.accept(ModItems.CRUSHED_ZINC.get());
                        output.accept(ModItems.DUST_TIN.get());
                        output.accept(ModItems.DUST_LEAD.get());
                        output.accept(ModItems.DUST_SILVER.get());
                        output.accept(ModItems.DUST_IRON.get());
                        output.accept(ModItems.DUST_GOLD.get());
                        output.accept(ModItems.DUST_ALUMINUM.get());
                        output.accept(ModItems.DUST_NICKEL.get());
                        output.accept(ModItems.DUST_ZINC.get());
                        output.accept(ModItems.COAL_COKE.get());
                        output.accept(ModItems.BIOCHAR.get());

                        // === KOŁA ZĘBATE ===
                        output.accept(ModItems.GEAR_COPPER.get());
                        output.accept(ModItems.GEAR_TIN.get());
                        output.accept(ModItems.GEAR_BRONZE.get());
                        output.accept(ModItems.GEAR_IRON.get());
                        output.accept(ModItems.GEAR_STEEL.get());
                        output.accept(ModItems.GEAR_ALUMINUM.get());

                        // === PŁYTY ===
                        output.accept(ModItems.PLATE_COPPER.get());
                        output.accept(ModItems.PLATE_TIN.get());
                        output.accept(ModItems.PLATE_BRONZE.get());
                        output.accept(ModItems.PLATE_IRON.get());
                        output.accept(ModItems.PLATE_STEEL.get());
                        output.accept(ModItems.PLATE_ALUMINUM.get());
                        output.accept(ModItems.PLATE_NICKEL.get());

                        // === PRĘTY ===
                        output.accept(ModItems.ROD_COPPER.get());
                        output.accept(ModItems.ROD_IRON.get());
                        output.accept(ModItems.ROD_STEEL.get());
                        output.accept(ModItems.ROD_BRONZE.get());
                        output.accept(ModItems.ROD_ALUMINUM.get());

                        // === ELEKTRONIKA ===
                        output.accept(ModItems.BASIC_CIRCUIT.get());
                        output.accept(ModItems.ADVANCED_CIRCUIT.get());
                        output.accept(ModItems.SILICON_WAFER.get());
                        output.accept(ModItems.COPPER_WIRE.get());
                        output.accept(ModItems.INSULATED_COPPER_WIRE.get());

                        // === NARZĘDZIA ===
                        output.accept(ModItems.FLINT_PICKAXE.get());
                        output.accept(ModItems.FLINT_AXE.get());
                        output.accept(ModItems.FLINT_SHOVEL.get());
                        output.accept(ModItems.FLINT_HOE.get());
                        output.accept(ModItems.FLINT_SWORD.get());
                        output.accept(ModItems.COPPER_PICKAXE.get());
                        output.accept(ModItems.COPPER_AXE.get());
                        output.accept(ModItems.COPPER_SHOVEL.get());
                        output.accept(ModItems.COPPER_HOE.get());
                        output.accept(ModItems.COPPER_SWORD.get());
                        output.accept(ModItems.BRONZE_PICKAXE.get());
                        output.accept(ModItems.BRONZE_AXE.get());
                        output.accept(ModItems.BRONZE_SHOVEL.get());
                        output.accept(ModItems.BRONZE_HOE.get());
                        output.accept(ModItems.BRONZE_SWORD.get());
                        output.accept(ModItems.STEEL_PICKAXE.get());
                        output.accept(ModItems.STEEL_AXE.get());
                        output.accept(ModItems.STEEL_SHOVEL.get());
                        output.accept(ModItems.STEEL_HOE.get());
                        output.accept(ModItems.STEEL_SWORD.get());

                        // === PANCERZ ===
                        output.accept(ModItems.COPPER_HELMET.get());
                        output.accept(ModItems.COPPER_CHESTPLATE.get());
                        output.accept(ModItems.COPPER_LEGGINGS.get());
                        output.accept(ModItems.COPPER_BOOTS.get());
                        output.accept(ModItems.BRONZE_HELMET.get());
                        output.accept(ModItems.BRONZE_CHESTPLATE.get());
                        output.accept(ModItems.BRONZE_LEGGINGS.get());
                        output.accept(ModItems.BRONZE_BOOTS.get());
                        output.accept(ModItems.STEEL_HELMET.get());
                        output.accept(ModItems.STEEL_CHESTPLATE.get());
                        output.accept(ModItems.STEEL_LEGGINGS.get());
                        output.accept(ModItems.STEEL_BOOTS.get());

                        // === MŁOTY ===
                        output.accept(ModItems.STONE_HAMMER.get());
                        output.accept(ModItems.IRON_HAMMER.get());
                        output.accept(ModItems.STEEL_HAMMER.get());

                        // === PRZEWODNIK ===
                        output.accept(ModItems.ENGINEERING_GUIDE.get());

                        // === BLOKI MAGAZYNOWE ===
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

                        // === BLOKI FUNKCJONALNE ===
                        output.accept(ModBlocks.MACHINE_FRAME.get());
                        output.accept(ModBlocks.COPPER_COIL.get());

                        // === MASZYNY ===
                        output.accept(ModBlocks.CRUSHING_TABLE.get());
                        output.accept(ModBlocks.ALLOY_SMELTER.get());
                        output.accept(ModBlocks.ELECTRIC_FURNACE.get());
                        output.accept(ModBlocks.MACERATOR.get());
                        output.accept(ModBlocks.GENERATOR.get());
                        output.accept(ModBlocks.COMPRESSOR.get());
                        output.accept(ModBlocks.COPPER_CABLE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
