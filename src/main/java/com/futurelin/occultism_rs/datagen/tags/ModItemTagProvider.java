package com.futurelin.occultism_rs.datagen.tags;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.registry.ModTags;
import com.klikli_dev.occultism.registry.OccultismItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, OccultismRS.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        copy(ModTags.Blocks.PENTACLE_MATERIALS, ModTags.Items.PENTACLE_MATERIALS);
        tag(ModTags.Items.PENTACLE_MATERIALS)
                .addOptionalTag(ModTags.Items.TOOLS_CHALK)
                .replace(false);

        tag(ModTags.Items.TOOLS_CHALK)
                .add(OccultismItems.CHALK_GOLD.get())
                .add(OccultismItems.CHALK_WHITE.get())
                .add(OccultismItems.CHALK_RED.get())
                .add(OccultismItems.CHALK_PURPLE.get())
                .replace(false);
    }
}
