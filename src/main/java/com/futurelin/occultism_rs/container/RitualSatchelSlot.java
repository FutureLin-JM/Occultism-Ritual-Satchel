package com.futurelin.occultism_rs.container;

import com.futurelin.occultism_rs.item.ritual_satchel.RitualSatchelItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class RitualSatchelSlot extends Slot {
    public RitualSatchelSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (stack.getItem() instanceof RitualSatchelItem) {
            return false;
        }

        return super.mayPlace(stack);
    }
}
