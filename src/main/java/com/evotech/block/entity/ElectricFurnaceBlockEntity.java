package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.ElectricFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFurnaceBlockEntity extends BaseMachineBlockEntity {

    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECTRIC_FURNACE.get(), pos, state, 2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new ElectricFurnaceMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, ElectricFurnaceBlockEntity entity) {
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

        var inputSlot = itemHandler.getStackInSlot(0);
        if (inputSlot.isEmpty()) return false;

        var result = getSmeltingResult(inputSlot);
        if (result == null || result.isEmpty()) return false;

        var outputSlot = itemHandler.getStackInSlot(1);
        if (outputSlot.isEmpty()) return true;
        if (!outputSlot.is(result.getItem())) return false;
        if (outputSlot.getCount() + result.getCount() > outputSlot.getMaxStackSize()) return false;

        return true;
    }

    private void craftItem() {
        if (level == null) return;

        var inputSlot = itemHandler.getStackInSlot(0);
        var result = getSmeltingResult(inputSlot);
        if (result == null) return;

        var outputSlot = itemHandler.getStackInSlot(1);
        if (outputSlot.isEmpty()) {
            itemHandler.setStackInSlot(1, result.copy());
        } else {
            outputSlot.grow(result.getCount());
        }

        inputSlot.shrink(1);
    }

    private net.minecraft.world.item.ItemStack getSmeltingResult(net.minecraft.world.item.ItemStack stack) {
        if (level == null) return net.minecraft.world.item.ItemStack.EMPTY;

        var recipeManager = level.getRecipeManager();
        var inventory = new net.minecraft.world.SimpleContainer(1);
        inventory.setItem(0, stack);

        var recipes = recipeManager.getRecipeFor(net.minecraft.world.item.crafting.RecipeType.SMELTING, inventory, level);
        return recipes.map(r -> r.getResultItem(level.registryAccess())).orElse(net.minecraft.world.item.ItemStack.EMPTY);
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
