package com.evotech.block.entity;

import com.evotech.init.ModBlockEntities;
import com.evotech.menu.AssemblingMachineMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class AssemblingMachineBlockEntity extends BaseMachineBlockEntity {
    public AssemblingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ASSEMBLING_MACHINE.get(), pos, state, 3);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new AssemblingMachineMenu(containerId, playerInventory, this, this.data);
    }

    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, AssemblingMachineBlockEntity entity) {
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
        var in1 = itemHandler.getStackInSlot(0);
        var in2 = itemHandler.getStackInSlot(1);
        if (in1.isEmpty() && in2.isEmpty()) return false;
        var result = getAssemblyResult(in1, in2);
        if (result == null || result.isEmpty()) return false;
        var output = itemHandler.getStackInSlot(2);
        if (output.isEmpty()) return true;
        return output.is(result.getItem()) && output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private void craftItem() {
        var in1 = itemHandler.getStackInSlot(0);
        var in2 = itemHandler.getStackInSlot(1);
        var result = getAssemblyResult(in1, in2);
        if (result == null) return;
        var output = itemHandler.getStackInSlot(2);
        if (output.isEmpty()) itemHandler.setStackInSlot(2, result.copy());
        else output.grow(result.getCount());
        in1.shrink(1);
        in2.shrink(1);
    }

    private ItemStack getAssemblyResult(ItemStack in1, ItemStack in2) {
        if (in1.isEmpty() || in2.isEmpty()) return ItemStack.EMPTY;
        // Gear + Plate -> Machine Part
        if (isGear(in1) && isPlate(in2) || isGear(in2) && isPlate(in1))
            return new ItemStack(com.evotech.init.ModItems.MACHINE_PART.get());
        // Circuit + Wire -> Basic Circuit
        if (in1.is(com.evotech.init.ModItems.SILICON_WAFER.get()) && in2.is(com.evotech.init.ModItems.COPPER_WIRE.get()) ||
            in2.is(com.evotech.init.ModItems.SILICON_WAFER.get()) && in1.is(com.evotech.init.ModItems.COPPER_WIRE.get()))
            return new ItemStack(com.evotech.init.ModItems.BASIC_CIRCUIT.get());
        // Advanced Circuit: Basic Circuit + Redstone + Gold
        if ((in1.is(com.evotech.init.ModItems.BASIC_CIRCUIT.get()) && in2.is(net.minecraft.world.item.Items.REDSTONE)) ||
            (in2.is(com.evotech.init.ModItems.BASIC_CIRCUIT.get()) && in1.is(net.minecraft.world.item.Items.REDSTONE)))
            return new ItemStack(com.evotech.init.ModItems.ADVANCED_CIRCUIT.get());
        // Electric Motor: Iron Rod + Copper Wire + Gear
        if (in1.is(com.evotech.init.ModItems.ROD_IRON.get()) && in2.is(com.evotech.init.ModItems.COPPER_WIRE.get()) ||
            in2.is(com.evotech.init.ModItems.ROD_IRON.get()) && in1.is(com.evotech.init.ModItems.COPPER_WIRE.get()))
            return new ItemStack(com.evotech.init.ModItems.ELECTRIC_MOTOR.get());
        // Steel Frame: Steel Plate + Iron Rod
        if ((in1.is(com.evotech.init.ModItems.PLATE_STEEL.get()) && in2.is(com.evotech.init.ModItems.ROD_IRON.get())) ||
            (in2.is(com.evotech.init.ModItems.PLATE_STEEL.get()) && in1.is(com.evotech.init.ModItems.ROD_IRON.get())))
            return new ItemStack(com.evotech.init.ModItems.STEEL_FRAME.get());
        return ItemStack.EMPTY;
    }

    private boolean isGear(ItemStack s) {
        return s.is(com.evotech.init.ModItems.GEAR_COPPER.get()) || s.is(com.evotech.init.ModItems.GEAR_IRON.get()) ||
               s.is(com.evotech.init.ModItems.GEAR_STEEL.get()) || s.is(com.evotech.init.ModItems.GEAR_BRONZE.get()) ||
               s.is(com.evotech.init.ModItems.GEAR_TIN.get()) || s.is(com.evotech.init.ModItems.GEAR_ALUMINUM.get());
    }

    private boolean isPlate(ItemStack s) {
        return s.is(com.evotech.init.ModItems.PLATE_COPPER.get()) || s.is(com.evotech.init.ModItems.PLATE_IRON.get()) ||
               s.is(com.evotech.init.ModItems.PLATE_STEEL.get()) || s.is(com.evotech.init.ModItems.PLATE_BRONZE.get()) ||
               s.is(com.evotech.init.ModItems.PLATE_TIN.get()) || s.is(com.evotech.init.ModItems.PLATE_ALUMINUM.get());
    }

    private void incrementProgress() { progress++; if (maxProgress == 0) maxProgress = 300; }
    private void resetProgress() { progress = 0; maxProgress = 0; }
}
