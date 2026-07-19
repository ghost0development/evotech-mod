package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.MatterScannerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class MatterScannerBlockEntity extends BaseMachineBlockEntity {
    public MatterScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MATTER_SCANNER.get(), pos, state, 2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new MatterScannerMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, MatterScannerBlockEntity entity) {
        entity.tick();
    }

    private void tick() {
        if (level == null) return;
        boolean changed = false;
        if (hasRecipe()) {
            incrementProgress(); setBlockLit(true); changed = true;
            if (progress >= maxProgress) { craftItem(); resetProgress(); }
        } else if (progress > 0) { resetProgress(); setBlockLit(false); changed = true; }
        if (changed) setChanged();
    }

    private void setBlockLit(boolean lit) {
        if (level != null && level.getBlockState(worldPosition).getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT) != lit) {
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT, lit), 3);
        }
    }

    private boolean hasRecipe() {
        var input = itemHandler.getStackInSlot(0);
        if (input.isEmpty()) return false;
        var result = getScanResult(input);
        if (result == null || result.isEmpty()) return false;
        var output = itemHandler.getStackInSlot(1);
        if (output.isEmpty()) return true;
        return output.is(result.getItem()) && output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private void craftItem() {
        var input = itemHandler.getStackInSlot(0);
        var result = getScanResult(input);
        if (result == null) return;
        var output = itemHandler.getStackInSlot(1);
        if (output.isEmpty()) itemHandler.setStackInSlot(1, result.copy());
        else output.grow(result.getCount());
        input.shrink(1);
    }

    private ItemStack getScanResult(ItemStack input) {
        // Triple output for all ores - Matter Scanner is the ultimate ore processor
        if (input.is(com.evotech.init.ModItems.TIN_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_TIN_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_TIN.get(), 3);
        if (input.is(com.evotech.init.ModItems.LEAD_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_LEAD_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_LEAD.get(), 3);
        if (input.is(com.evotech.init.ModItems.SILVER_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_SILVER_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_SILVER.get(), 3);
        if (input.is(com.evotech.init.ModItems.ALUMINUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_ALUMINUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_ALUMINUM.get(), 3);
        if (input.is(com.evotech.init.ModItems.NICKEL_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_NICKEL_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_NICKEL.get(), 3);
        if (input.is(com.evotech.init.ModItems.ZINC_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_ZINC_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_ZINC.get(), 3);
        if (input.is(com.evotech.init.ModItems.TUNGSTEN_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_TUNGSTEN.get(), 3);
        if (input.is(com.evotech.init.ModItems.TITANIUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_TITANIUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_TITANIUM.get(), 3);
        if (input.is(com.evotech.init.ModItems.PLATINUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_PLATINUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_PLATINUM.get(), 3);
        if (input.is(com.evotech.init.ModItems.CHROMIUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_CHROMIUM.get(), 3);
        if (input.is(com.evotech.init.ModItems.COBALT_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_COBALT_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_COBALT.get(), 3);
        if (input.is(com.evotech.init.ModItems.URANIUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_URANIUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_URANIUM.get(), 3);
        if (input.is(com.evotech.init.ModItems.LITHIUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_LITHIUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_LITHIUM.get(), 3);
        if (input.is(com.evotech.init.ModItems.IRIDIUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_IRIDIUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_IRIDIUM.get(), 3);
        if (input.is(net.minecraft.world.item.Items.IRON_ORE) || input.is(net.minecraft.world.item.Items.DEEPSLATE_IRON_ORE))
            return new ItemStack(com.evotech.init.ModItems.DUST_IRON.get(), 3);
        if (input.is(net.minecraft.world.item.Items.GOLD_ORE) || input.is(net.minecraft.world.item.Items.DEEPSLATE_GOLD_ORE))
            return new ItemStack(com.evotech.init.ModItems.DUST_GOLD.get(), 3);
        return ItemStack.EMPTY;
    }

    private void incrementProgress() { progress++; if (maxProgress == 0) maxProgress = 100; }
    private void resetProgress() { progress = 0; maxProgress = 0; }
}
