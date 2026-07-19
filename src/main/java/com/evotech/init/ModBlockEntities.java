package com.evotech.init;

import com.evotech.EvoTech;
import com.evotech.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EvoTech.MOD_ID);

    public static final RegistryObject<BlockEntityType<AlloySmelterBlockEntity>> ALLOY_SMELTER =
            BLOCK_ENTITIES.register("alloy_smelter",
                    () -> BlockEntityType.Builder.of(AlloySmelterBlockEntity::new, ModBlocks.ALLOY_SMELTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<ElectricFurnaceBlockEntity>> ELECTRIC_FURNACE =
            BLOCK_ENTITIES.register("electric_furnace",
                    () -> BlockEntityType.Builder.of(ElectricFurnaceBlockEntity::new, ModBlocks.ELECTRIC_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MaceratorBlockEntity>> MACERATOR =
            BLOCK_ENTITIES.register("macerator",
                    () -> BlockEntityType.Builder.of(MaceratorBlockEntity::new, ModBlocks.MACERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> GENERATOR =
            BLOCK_ENTITIES.register("generator",
                    () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, ModBlocks.GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<CompressorBlockEntity>> COMPRESSOR =
            BLOCK_ENTITIES.register("compressor",
                    () -> BlockEntityType.Builder.of(CompressorBlockEntity::new, ModBlocks.COMPRESSOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<SteamEngineBlockEntity>> STEAM_ENGINE =
            BLOCK_ENTITIES.register("steam_engine",
                    () -> BlockEntityType.Builder.of(SteamEngineBlockEntity::new, ModBlocks.STEAM_ENGINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<WireMillBlockEntity>> WIRE_MILL =
            BLOCK_ENTITIES.register("wire_mill",
                    () -> BlockEntityType.Builder.of(WireMillBlockEntity::new, ModBlocks.WIRE_MILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<AssemblingMachineBlockEntity>> ASSEMBLING_MACHINE =
            BLOCK_ENTITIES.register("assembling_machine",
                    () -> BlockEntityType.Builder.of(AssemblingMachineBlockEntity::new, ModBlocks.ASSEMBLING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MatterScannerBlockEntity>> MATTER_SCANNER =
            BLOCK_ENTITIES.register("matter_scanner",
                    () -> BlockEntityType.Builder.of(MatterScannerBlockEntity::new, ModBlocks.MATTER_SCANNER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
