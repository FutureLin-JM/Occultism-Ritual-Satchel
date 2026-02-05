package com.futurelin.occultism_rs.item.ritual_satchel;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;

public record PentacleData(ResourceLocation multiblock, BlockPos anchor, Rotation facing, BlockPos target,
                           long timeWhenAdded) {
}
