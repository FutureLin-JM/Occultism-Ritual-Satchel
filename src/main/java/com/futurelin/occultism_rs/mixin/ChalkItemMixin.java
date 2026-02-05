package com.futurelin.occultism_rs.mixin;

import com.futurelin.occultism_rs.api.access.IChalkItemAccessor;
import com.klikli_dev.occultism.common.block.ChalkGlyphBlock;
import com.klikli_dev.occultism.common.item.tool.ChalkItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

@Mixin(ChalkItem.class)
public abstract class ChalkItemMixin implements IChalkItemAccessor {

    @Shadow(remap = false)
    Supplier<ChalkGlyphBlock> glyphBlock;

    @Override
    @Unique
    public ChalkGlyphBlock getGlyphBlock() {
        return this.glyphBlock != null ? this.glyphBlock.get() : null;
    }
}
