package com.evotech.menu;

import com.evotech.init.ModBlockEntities;
import com.evotech.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class SteamEngineMenu extends AbstractContainerMenu {
    private final BlockEntity blockEntity;
    private final ContainerData data;

    public SteamEngineMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }

    public SteamEngineMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.STEAM_ENGINE.get(), containerId);
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
    public int getBurnLeft() { return data.get(2); }
    public int getScaledBurnLeft() {
        int bt = data.get(2), mbt = data.get(3);
        return mbt != 0 && bt != 0 ? bt * 14 / mbt : 0;
    }

    private static final int VANILLA_SLOT_COUNT = 36;
    private final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_SLOT_COUNT;
    private final int TE_INVENTORY_SLOT_COUNT = 2;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copy = sourceStack.copy();
        if (index >= 0 && index < VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) return ItemStack.EMPTY;
        } else if (index >= TE_INVENTORY_FIRST_SLOT_INDEX) {
            if (!moveItemStackTo(sourceStack, 0, VANILLA_SLOT_COUNT, false)) return ItemStack.EMPTY;
        } else return ItemStack.EMPTY;
        if (sourceStack.getCount() == 0) sourceSlot.set(ItemStack.EMPTY);
        else sourceSlot.setChanged();
        sourceSlot.onTake(player, sourceStack);
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
