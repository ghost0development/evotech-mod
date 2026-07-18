package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.MaceratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public class MaceratorBlockEntity extends BaseMachineBlockEntity {

    public MaceratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MACERATOR.get(), pos, state, 2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new MaceratorMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, MaceratorBlockEntity entity) {
        entity.tick();
    }

    private void tick() {
        if (level == null) return;
        boolean changed = false;

        if (hasRecipe()) {
            incrementProgress();
            setBlockLit(true);
            changed = true;
            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        } else {
            if (progress > 0) {
                resetProgress();
                setBlockLit(false);
                changed = true;
            }
        }
        if (changed) setChanged();
    }

    private void setBlockLit(boolean lit) {
        if (level != null && level.getBlockState(worldPosition).getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT) != lit) {
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT, lit), 3);
        }
    }

    private boolean hasRecipe() {
        if (level == null) return false;
        var input = itemHandler.getStackInSlot(0);
        if (input.isEmpty()) return false;
        var result = getMaceratorResult(input);
        if (result == null || result.isEmpty()) return false;
        var outputSlot = itemHandler.getStackInSlot(1);
        if (outputSlot.isEmpty()) return true;
        if (!outputSlot.is(result.getItem())) return false;
        return outputSlot.getCount() + result.getCount() <= outputSlot.getMaxStackSize();
    }

    private void craftItem() {
        if (level == null) return;
        var input = itemHandler.getStackInSlot(0);
        var result = getMaceratorResult(input);
        if (result == null) return;
        var outputSlot = itemHandler.getStackInSlot(1);
        if (outputSlot.isEmpty()) {
            itemHandler.setStackInSlot(1, result.copy());
        } else {
            outputSlot.grow(result.getCount());
        }
        input.shrink(1);
    }

    private ItemStack getMaceratorResult(ItemStack input) {
        if (input.is(com.evotech.init.ModItems.TIN_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_TIN_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_TIN.get(), 2);
        if (input.is(com.evotech.init.ModItems.LEAD_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_LEAD_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_LEAD.get(), 2);
        if (input.is(com.evotech.init.ModItems.SILVER_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_SILVER_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_SILVER.get(), 2);
        if (input.is(com.evotech.init.ModItems.ALUMINUM_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_ALUMINUM_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_ALUMINUM.get(), 2);
        if (input.is(com.evotech.init.ModItems.NICKEL_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_NICKEL_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_NICKEL.get(), 2);
        if (input.is(com.evotech.init.ModItems.ZINC_ORE_BLOCK.get()) || input.is(com.evotech.init.ModBlocks.DEEPSLATE_ZINC_ORE.get().asItem()))
            return new ItemStack(com.evotech.init.ModItems.DUST_ZINC.get(), 2);
        if (input.is(Items.IRON_ORE) || input.is(Items.DEEPSLATE_IRON_ORE))
            return new ItemStack(com.evotech.init.ModItems.DUST_IRON.get(), 2);
        if (input.is(Items.GOLD_ORE) || input.is(Items.DEEPSLATE_GOLD_ORE))
            return new ItemStack(com.evotech.init.ModItems.DUST_GOLD.get(), 2);
        if (input.is(Items.COAL_ORE) || input.is(Items.DEEPSLATE_COAL_ORE))
            return new ItemStack(Items.COAL, 2);
        return ItemStack.EMPTY;
    }

    private void incrementProgress() {
        progress++;
        if (maxProgress == 0) maxProgress = 200;
    }

    private void resetProgress() { progress = 0; maxProgress = 0; }
}
