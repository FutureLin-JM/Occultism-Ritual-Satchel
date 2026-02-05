package com.futurelin.occultism_rs.container;

import com.futurelin.occultism_rs.item.ritual_satchel.RitualSatchelItem;
import com.futurelin.occultism_rs.registry.ModContainers;
import com.klikli_dev.occultism.common.container.storage.SatchelInventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class RitualSatchelContainer extends AbstractContainerMenu {

    public static final int SATCHEL_SIZE = 4 * 9;
    protected Container satchelInventory;
    protected Inventory playerInventory;
    protected int selectedSlot;

    public RitualSatchelContainer(int id, Inventory playerInventory, Container satchelInventory, int selectedSlot) {
        super(ModContainers.RITUAL_SATCHEL.get(), id);
        this.satchelInventory = satchelInventory;
        this.playerInventory = playerInventory;
        this.selectedSlot = selectedSlot;

        this.setupSatchelSlots();
        this.setupPlayerInventorySlots();
        this.setupPlayerHotbar();
    }

    public static RitualSatchelContainer createClientContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
        final int selectedSlot = buffer.readVarInt();
        return new RitualSatchelContainer(id, playerInventory, new SimpleContainer(SATCHEL_SIZE), selectedSlot);
    }

    @Override
    public void broadcastChanges() {
        if (this.satchelInventory instanceof SatchelInventory) {
            ((SatchelInventory) this.satchelInventory).writeItemStack();
        }
        super.broadcastChanges();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index >= this.satchelInventory.getContainerSize()) {
                if (itemstack.getItem() instanceof RitualSatchelItem) return ItemStack.EMPTY;
            }

            if (index < this.satchelInventory.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.satchelInventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.satchelInventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.selectedSlot < 0 || this.selectedSlot >= player.getInventory().getContainerSize()) return false;
        ItemStack held = player.getInventory().getItem(this.selectedSlot);
        return held.getItem() instanceof RitualSatchelItem;
    }

    protected void setupPlayerInventorySlots() {
        int playerInventoryTop = 84;
        int playerInventoryLeft = 8;
        int hotbarSlots = 9;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                this.addSlot(new Slot(this.playerInventory, j + i * 9 + hotbarSlots, playerInventoryLeft + j * 18, playerInventoryTop + i * 18));
    }

    protected void setupPlayerHotbar() {
        int hotbarTop = 142;
        int hotbarLeft = 8;
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(this.playerInventory, i, hotbarLeft + i * 18, hotbarTop));
        }
    }

    protected void setupSatchelSlots() {
        int height = 4;
        int width = 9;
        int x = 8;
        int y = 8;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.addSlot(new RitualSatchelSlot(this.satchelInventory, j + i * width, x + j * 18, y + i * 18));
            }
        }
    }
}
