package com.futurelin.occultism_rs.registry;

import com.futurelin.occultism_rs.OccultismRS;
import com.klikli_dev.occultism.Occultism;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> PENTACLE_MATERIALS = makeBlockTag(new ResourceLocation(OccultismRS.MOD_ID, "pentacle_materials"));
        public static final TagKey<Block> CHALK_GLYPHS = makeBlockTag(new ResourceLocation(Occultism.MODID, "chalk_glyphs"));
    }

    public static class Items {
        public static final TagKey<Item> PENTACLE_MATERIALS = makeItemTag(new ResourceLocation(OccultismRS.MOD_ID, "pentacle_materials"));
        public static final TagKey<Item> TOOLS_CHALK = makeItemTag(new ResourceLocation(Occultism.MODID, "tools/chalk"));
    }

    public static TagKey<Item> makeItemTag(String id) {
        return makeItemTag(new ResourceLocation(id));
    }

    public static TagKey<Item> makeItemTag(ResourceLocation id) {
        return TagKey.create(Registries.ITEM, id);
    }

    public static TagKey<Block> makeBlockTag(String id) {
        return makeBlockTag(new ResourceLocation(id));
    }

    public static TagKey<Block> makeBlockTag(ResourceLocation id) {
        return TagKey.create(Registries.BLOCK, id);
    }
}
