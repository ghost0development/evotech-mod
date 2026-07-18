package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.CompressorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class CompressorBlockEntity extends BaseMachineBlockEntity {

    public CompressorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMPRESSOR.get(), pos, state, 2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new CompressorMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, CompressorBlockEntity entity) {
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
        var result = getCompressorResult(input);
        if (result == null || result.isEmpty()) return false;
        var outputSlot = itemHandler.getStackInSlot(1);
        if (outputSlot.isEmpty()) return true;
        if (!outputSlot.is(result.getItem())) return false;
        return outputSlot.getCount() + result.getCount() <= outputSlot.getMaxStackSize();
    }

    private void craftItem() {
        if (level == null) return;
        var input = itemHandler.getStackInSlot(0);
        var result = getCompressorResult(input);
        if (result == null) return;
        int inputCount = getInputCount(input);
        var outputSlot = itemHandler.getStackInSlot(1);
        if (outputSlot.isEmpty()) {
            itemHandler.setStackInSlot(1, result.copy());
        } else {
            outputSlot.grow(result.getCount());
        }
        input.shrink(inputCount);
    }

    private ItemStack getCompressorResult(ItemStack input) {
        int count = input.getCount();
        if (count < 4) return ItemStack.EMPTY;

        if (input.is(net.minecraft.world.item.Items.COPPER_INGOT))
            return new ItemStack(com.evotech.init.ModItems.PLATE_COPPER.get());
        if (input.is(net.minecraft.world.item.Items.IRON_INGOT))
            return new ItemStack(com.evotech.init.ModItems.PLATE_IRON.get());
        if (input.is(com.evotech.init.ModItems.TIN_INGOT.get()))
            return new ItemStack(com.evotech.init.ModItems.PLATE_TIN.get());
        if (input.is(com.evotech.init.ModItems.BRONZE_INGOT.get()))
            return new ItemStack(com.evotech.init.ModItems.PLATE_BRONZE.get());
        if (input.is(com.evotech.init.ModItems.STEEL_INGOT.get()))
            return new ItemStack(com.evotech.init.ModItems.PLATE_STEEL.get());
        if (input.is(com.evotech.init.ModItems.ALUMINUM_INGOT.get()))
            return new ItemStack(com.evotech.init.ModItems.PLATE_ALUMINUM.get());
        if (input.is(com.evotech.init.ModItems.NICKEL_INGOT.get()))
            return new ItemStack(com.evotech.init.ModItems.PLATE_NICKEL.get());

        return ItemStack.EMPTY;
    }

    private int getInputCount(ItemStack input) {
        return 4;
    }

    private void incrementProgress() {
        progress++;
        if (maxProgress == 0) maxProgress = 200;
    }

    private void resetProgress() { progress = 0; maxProgress = 0; }
}
