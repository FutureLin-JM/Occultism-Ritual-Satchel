package com.futurelin.occultism_rs.preview;

import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import com.klikli_dev.modonomicon.client.render.MultiblockPreviewRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public final class ModonomiconPreviewCompat {
    private ModonomiconPreviewCompat() {
    }

    @Nullable
    public static PreviewData getCurrentPreview() {
        if (!MultiblockPreviewRenderer.hasMultiblock) {
            return null;
        }

        Multiblock multiblock = MultiblockPreviewRenderer.getMultiblock();
        if (multiblock == null) {
            return null;
        }

        BlockPos pos = MultiblockPreviewRenderer.getStartPos();
        Rotation facing = MultiblockPreviewRenderer.getFacingRotation();
        boolean anchored = MultiblockPreviewRenderer.isAnchored();

        if (pos == null || facing == null) {
            return null;
        }

        return new PreviewData(multiblock, pos, facing, anchored);
    }
}