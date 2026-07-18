package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.GeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public class GeneratorBlockEntity extends BaseMachineBlockEntity {

    private int burnTime = 0;
    private int maxBurnTime = 0;

    public GeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GENERATOR.get(), pos, state, 1);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new GeneratorMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, GeneratorBlockEntity entity) {
        entity.tick();
    }

    private void tick() {
        if (level == null) return;
        boolean changed = false;

        if (burnTime > 0) {
            burnTime--;
            setBlockLit(true);
            changed = true;
            if (burnTime == 0) {
                setBlockLit(false);
                changed = true;
            }
        } else if (hasFuel()) {
            burnFuel();
            setBlockLit(true);
            changed = true;
        }

        if (changed) setChanged();
    }

    private void setBlockLit(boolean lit) {
        if (level != null && level.getBlockState(worldPosition).getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT) != lit) {
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT, lit), 3);
        }
    }

    private boolean hasFuel() {
        var fuel = itemHandler.getStackInSlot(0);
        if (fuel.isEmpty()) return false;
        int burn = getBurnTime(fuel);
        return burn > 0;
    }

    private void burnFuel() {
        var fuel = itemHandler.getStackInSlot(0);
        maxBurnTime = getBurnTime(fuel);
        burnTime = maxBurnTime;
        fuel.shrink(1);
    }

    private int getBurnTime(ItemStack stack) {
        if (stack.is(Items.COAL) || stack.is(Items.CHARCOAL)) return 200;
        if (stack.is(com.evotech.init.ModItems.COAL_COKE.get())) return 400;
        if (stack.is(com.evotech.init.ModItems.BIOCHAR.get())) return 300;
        return 0;
    }

    public int getBurnTime() { return burnTime; }
    public int getMaxBurnTime() { return maxBurnTime; }

    @Override
    protected void saveAdditional(net.minecraft.nbt.CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("burnTime", burnTime);
        tag.putInt("maxBurnTime", maxBurnTime);
    }

    @Override
    public void load(net.minecraft.nbt.CompoundTag tag) {
        super.load(tag);
        burnTime = tag.getInt("burnTime");
        maxBurnTime = tag.getInt("maxBurnTime");
    }
}
