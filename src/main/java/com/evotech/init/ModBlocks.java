package com.evotech.init;

import com.evotech.EvoTech;
import com.evotech.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EvoTech.MOD_ID);

    // === RUDY ===
    public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = BLOCKS.register("deepslate_tin_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> LEAD_ORE = BLOCKS.register("lead_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = BLOCKS.register("deepslate_lead_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = BLOCKS.register("deepslate_aluminum_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> NICKEL_ORE = BLOCKS.register("nickel_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_NICKEL_ORE = BLOCKS.register("deepslate_nickel_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> ZINC_ORE = BLOCKS.register("zinc_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = BLOCKS.register("deepslate_zinc_ore",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));

    // === BLOKI MAGAZYNOWE ===
    public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> BRONZE_BLOCK = BLOCKS.register("bronze_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("lead_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> ALUMINUM_BLOCK = BLOCKS.register("aluminum_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> NICKEL_BLOCK = BLOCKS.register("nickel_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> ZINC_BLOCK = BLOCKS.register("zinc_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> ELECTRUM_BLOCK = BLOCKS.register("electrum_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> CONSTANTAN_BLOCK = BLOCKS.register("constantan_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    // === BLOKI FUNKCJONALNE ===
    public static final RegistryObject<Block> MACHINE_FRAME = BLOCKS.register("machine_frame",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> COPPER_COIL = BLOCKS.register("copper_coil",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(4.0F, 4.0F).sound(SoundType.METAL)));

    // === MASZYNY ===
    public static final RegistryObject<Block> CRUSHING_TABLE = BLOCKS.register("crushing_table",
            () -> new CrushingTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ALLOY_SMELTER = BLOCKS.register("alloy_smelter",
            () -> new AlloySmelterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(4.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> ELECTRIC_FURNACE = BLOCKS.register("electric_furnace",
            () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(4.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MACERATOR = BLOCKS.register("macerator",
            () -> new MaceratorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(4.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> GENERATOR = BLOCKS.register("generator",
            () -> new GeneratorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(4.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> COMPRESSOR = BLOCKS.register("compressor",
            () -> new CompressorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(4.0F).sound(SoundType.METAL)));

    // === KABLE ===
    public static final RegistryObject<Block> COPPER_CABLE = BLOCKS.register("copper_cable",
            () -> new CableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.3F).sound(SoundType.WOOL)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
