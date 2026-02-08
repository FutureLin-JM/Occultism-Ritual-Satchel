package com.futurelin.occultism_rs.container;

import com.futurelin.occultism_rs.item.ritual_satchel.RitualSatchelItem;
import com.futurelin.occultism_rs.registry.ModTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class RitualSatchelSlot extends Slot {
    public RitualSatchelSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (stack.getItem() instanceof RitualSatchelItem) {
            return false;
        }

        if (stack.is(ModTags.Items.PENTACLE_MATERIALS)) {
            return super.mayPlace(stack);
        }

        if (stack.getItem() instanceof BlockItem) {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            if (block.defaultBlockState().is(ModTags.Blocks.PENTACLE_MATERIALS)) {
                return super.mayPlace(stack);
            }
        }

        return false;
    }
}
