package com.evotech.menu;

import com.evotech.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class WireMillMenu extends AbstractContainerMenu {
    private final BlockEntity blockEntity;
    private final ContainerData data;

    public WireMillMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public WireMillMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.WIRE_MILL.get(), containerId);
        this.blockEntity = blockEntity;
        this.data = data;
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(ih -> {
            this.addSlot(new SlotItemHandler(ih, 0, 56, 35));
            this.addSlot(new SlotItemHandler(ih, 1, 116, 35));
        });
        addDataSlots(data);
    }

    public boolean isCrafting() { return data.get(0) > 0; }
    public int getScaledProgress() {
        int p = data.get(0), mp = data.get(1);
        return mp != 0 && p != 0 ? p * 24 / mp : 0;
    }

    private final int VANILLA_SLOT_COUNT = 36;
    private final int TE_FIRST = VANILLA_SLOT_COUNT;
    private final int TE_COUNT = 2;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot s = slots.get(index);
        if (!s.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = s.getItem(), copy = stack.copy();
        if (index < VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(stack, TE_FIRST, TE_FIRST + TE_COUNT, false)) return ItemStack.EMPTY;
        } else if (index >= TE_FIRST) {
            if (!moveItemStackTo(stack, 0, VANILLA_SLOT_COUNT, false)) return ItemStack.EMPTY;
        } else return ItemStack.EMPTY;
        if (stack.getCount() == 0) s.set(ItemStack.EMPTY);
        else s.setChanged();
        s.onTake(player, stack);
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), player, blockEntity.getBlockState().getBlock());
    }

    private void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; i++) for (int l = 0; l < 9; l++)
            this.addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
    }
    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; i++) this.addSlot(new Slot(inv, i, 8 + i * 18, 142));
    }
}
