package com.futurelin.occultism_rs.preview;

import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;

public record PreviewData(Multiblock multiblock, BlockPos anchor, Rotation facing, boolean isAnchored) {
}
