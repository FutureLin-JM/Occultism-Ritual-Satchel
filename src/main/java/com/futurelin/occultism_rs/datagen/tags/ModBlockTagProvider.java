package com.futurelin.occultism_rs.datagen.tags;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.registry.ModTags;
import com.klikli_dev.occultism.registry.OccultismBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OccultismRS.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Blocks.PENTACLE_MATERIALS)
                .addTag(BlockTags.CANDLES)
                .add(OccultismBlocks.GOLDEN_SACRIFICIAL_BOWL.get())
                .add(OccultismBlocks.SPIRIT_ATTUNED_CRYSTAL.get())
                .add(OccultismBlocks.SACRIFICIAL_BOWL.get())
                .add(Blocks.SKELETON_SKULL)
                .add(Blocks.WITHER_SKELETON_SKULL)
                .addOptionalTag(ModTags.Blocks.CHALK_GLYPHS)
                .replace(false);

        tag(ModTags.Blocks.CHALK_GLYPHS)
                .add(OccultismBlocks.CHALK_GLYPH_WHITE.get())
                .add(OccultismBlocks.CHALK_GLYPH_GOLD.get())
                .add(OccultismBlocks.CHALK_GLYPH_PURPLE.get())
                .add(OccultismBlocks.CHALK_GLYPH_RED.get())
                .replace(false);
    }
}
