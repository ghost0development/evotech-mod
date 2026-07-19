package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.WireMillMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class WireMillBlockEntity extends BaseMachineBlockEntity {
    public WireMillBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WIRE_MILL.get(), pos, state, 2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new WireMillMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, WireMillBlockEntity entity) {
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
        var result = getWireResult(input);
        if (result == null || result.isEmpty()) return false;
        var output = itemHandler.getStackInSlot(1);
        if (output.isEmpty()) return true;
        return output.is(result.getItem()) && output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private void craftItem() {
        var input = itemHandler.getStackInSlot(0);
        var result = getWireResult(input);
        if (result == null) return;
        var output = itemHandler.getStackInSlot(1);
        if (output.isEmpty()) itemHandler.setStackInSlot(1, result.copy());
        else output.grow(result.getCount());
        input.shrink(1);
    }

    private ItemStack getWireResult(ItemStack input) {
        if (input.is(net.minecraft.world.item.Items.COPPER_INGOT)) return new ItemStack(com.evotech.init.ModItems.COPPER_WIRE.get(), 2);
        if (input.is(net.minecraft.world.item.Items.GOLD_INGOT)) return new ItemStack(com.evotech.init.ModItems.COPPER_WIRE.get(), 3);
        if (input.is(com.evotech.init.ModItems.ALUMINUM_INGOT.get())) return new ItemStack(com.evotech.init.ModItems.COPPER_WIRE.get(), 2);
        return ItemStack.EMPTY;
    }

    private void incrementProgress() { progress++; if (maxProgress == 0) maxProgress = 150; }
    private void resetProgress() { progress = 0; maxProgress = 0; }
}
