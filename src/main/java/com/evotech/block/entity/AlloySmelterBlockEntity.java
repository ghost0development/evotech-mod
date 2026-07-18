package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.AlloySmelterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class AlloySmelterBlockEntity extends BaseMachineBlockEntity {

    public AlloySmelterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_SMELTER.get(), pos, state, 3);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new AlloySmelterMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, AlloySmelterBlockEntity entity) {
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

        if (changed) {
            setChanged();
        }
    }

    private void setBlockLit(boolean lit) {
        if (level != null && level.getBlockState(worldPosition).getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT) != lit) {
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT, lit), 3);
        }
    }

    private boolean hasRecipe() {
        if (level == null) return false;

        var input1 = itemHandler.getStackInSlot(0);
        var input2 = itemHandler.getStackInSlot(1);
        if (input1.isEmpty() && input2.isEmpty()) return false;

        var result = getAlloyResult(input1, input2);
        if (result == null || result.isEmpty()) return false;

        var outputSlot = itemHandler.getStackInSlot(2);
        if (outputSlot.isEmpty()) return true;
        if (!outputSlot.is(result.getItem())) return false;
        if (outputSlot.getCount() + result.getCount() > outputSlot.getMaxStackSize()) return false;

        return true;
    }

    private void craftItem() {
        if (level == null) return;

        var input1 = itemHandler.getStackInSlot(0);
        var input2 = itemHandler.getStackInSlot(1);
        var result = getAlloyResult(input1, input2);
        if (result == null) return;

        var outputSlot = itemHandler.getStackInSlot(2);
        if (outputSlot.isEmpty()) {
            itemHandler.setStackInSlot(2, result.copy());
        } else {
            outputSlot.grow(result.getCount());
        }

        input1.shrink(1);
        input2.shrink(1);
    }

    private net.minecraft.world.item.ItemStack getAlloyResult(net.minecraft.world.item.ItemStack input1, net.minecraft.world.item.ItemStack input2) {
        if (level == null) return net.minecraft.world.item.ItemStack.EMPTY;

        var tinIngot = com.evotech.init.ModItems.TIN_INGOT.get();
        var copperIngot = net.minecraft.world.item.Items.COPPER_INGOT;
        var ironIngot = net.minecraft.world.item.Items.IRON_INGOT;
        var coalCoke = com.evotech.init.ModItems.COAL_COKE.get();

        // Bronze: 1 tin + 1 copper -> 1 bronze
        if (isMatch(input1, tinIngot) && isMatch(input2, copperIngot) ||
                isMatch(input1, copperIngot) && isMatch(input2, tinIngot)) {
            return new net.minecraft.world.item.ItemStack(com.evotech.init.ModItems.BRONZE_INGOT.get());
        }

        // Steel: 1 iron + 1 coal coke -> 1 steel
        if (isMatch(input1, ironIngot) && isMatch(input2, coalCoke) ||
                isMatch(input1, coalCoke) && isMatch(input2, ironIngot)) {
            return new net.minecraft.world.item.ItemStack(com.evotech.init.ModItems.STEEL_INGOT.get());
        }

        // Electrum: 1 gold + 1 silver -> 1 electrum
        if (isMatch(input1, net.minecraft.world.item.Items.GOLD_INGOT) && isMatch(input2, com.evotech.init.ModItems.SILVER_INGOT.get()) ||
                isMatch(input1, com.evotech.init.ModItems.SILVER_INGOT.get()) && isMatch(input2, net.minecraft.world.item.Items.GOLD_INGOT)) {
            return new net.minecraft.world.item.ItemStack(com.evotech.init.ModItems.ELECTRUM_INGOT.get());
        }

        // Constantan: 1 copper + 1 nickel -> 1 constantan
        if (isMatch(input1, net.minecraft.world.item.Items.COPPER_INGOT) && isMatch(input2, com.evotech.init.ModItems.NICKEL_INGOT.get()) ||
                isMatch(input1, com.evotech.init.ModItems.NICKEL_INGOT.get()) && isMatch(input2, net.minecraft.world.item.Items.COPPER_INGOT)) {
            return new net.minecraft.world.item.ItemStack(com.evotech.init.ModItems.CONSTANTAN_INGOT.get());
        }

        return net.minecraft.world.item.ItemStack.EMPTY;
    }

    private boolean isMatch(net.minecraft.world.item.ItemStack stack, net.minecraft.world.item.Item item) {
        return !stack.isEmpty() && stack.is(item) && stack.getCount() >= 1;
    }

    private void incrementProgress() {
        progress++;
        if (maxProgress == 0) {
            maxProgress = 200;
        }
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 0;
    }
}
