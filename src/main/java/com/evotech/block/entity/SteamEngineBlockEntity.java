package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.SteamEngineMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public class SteamEngineBlockEntity extends BaseMachineBlockEntity {
    private int burnTime = 0;
    private int maxBurnTime = 0;

    public SteamEngineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STEAM_ENGINE.get(), pos, state, 2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new SteamEngineMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, SteamEngineBlockEntity entity) {
        entity.tick();
    }

    private void tick() {
        if (level == null) return;
        boolean changed = false;

        if (burnTime > 0) {
            burnTime--;
            setBlockLit(true);
            changed = true;
            if (burnTime == 0) { setBlockLit(false); changed = true; }
        } else if (hasFuel()) {
            consumeFuel();
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
        return !fuel.isEmpty() && getBurnTime(fuel) > 0;
    }

    private void consumeFuel() {
        var fuel = itemHandler.getStackInSlot(0);
        maxBurnTime = getBurnTime(fuel);
        burnTime = maxBurnTime;
        fuel.shrink(1);
    }

    private int getBurnTime(ItemStack stack) {
        if (stack.is(Items.COAL) || stack.is(Items.CHARCOAL)) return 400;
        if (stack.is(com.evotech.init.ModItems.COAL_COKE.get())) return 800;
        if (stack.is(com.evotech.init.ModItems.BIOCHAR.get())) return 600;
        return 0;
    }

    public int getBurnProgress() { return maxBurnTime > 0 ? (int)((float)burnTime / maxBurnTime * 14) : 0; }
    public int getBurnTimeMax() { return maxBurnTime; }
}
