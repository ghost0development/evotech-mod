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

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
