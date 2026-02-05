package com.futurelin.occultism_rs.datagen.lang;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.TranslationKeys;
import com.futurelin.occultism_rs.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModZHCNProvider extends LanguageProvider {
    public ModZHCNProvider(PackOutput output) {
        super(output, OccultismRS.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.RITUAL_SATCHEL_T1.get(), "学徒仪式挎包");
        add(ModItems.RITUAL_SATCHEL_T2.get(), "匠心仪式挎包");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T1.get(), "仪式：合成学徒仪式挎包");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T2.get(), "仪式：合成匠心仪式挎包");
        add(ModItems.RITUAL_DUMMY_CRAFT_UPGRADE_RITUAL_SATCHEL.get(), "仪式：合成匠心仪式挎包");

        add(TranslationKeys.RITUAL_SATCHEL_NO_PREVIEW_IN_WORLD, "需要使用魔灵全典来预览五芒星阵。");
        add(TranslationKeys.RITUAL_SATCHEL_NO_PREVIEW_BLOCK_TARGETED, "需要将仪式挎包对准预览方块。");
        add(TranslationKeys.RITUAL_SATCHEL_NO_VALID_ITEM_IN_SATCHEL, "挎包中没有该预览方块的有效物品。");
        add(TranslationKeys.RITUAL_SATCHEL_BLOCK_ABOVE_NOT_AIR, "点击位置上方的方块空间不为空。");
        add(TranslationKeys.RITUAL_SATCHEL_BLOCK_AT_POSITION_NOT_AIR, "点击位置的方块空间不为空。");
        add(TranslationKeys.RITUAL_SATCHEL_INVALID_MATCHER, "无法为ANY或DISPLAY_ONLY类型的多方块匹配器放置方块");
        add(TranslationKeys.RITUAL_SATCHEL_GLYPH_CANNOT_SURVIVE, "无法在此处放置符文。");
        add(TranslationKeys.RITUAL_SATCHEL_WILL_BREAK_ITEM, "有东西快坏了，快去修一修！");
        add(TranslationKeys.Put_In_Satchel, "已将五芒星阵存入挎包");

        add(ModItems.RITUAL_SATCHEL_T1.get().getDescriptionId() + ".tooltip", "%s被束缚于此挎包中。");
        add(ModItems.RITUAL_SATCHEL_T1.get().getDescriptionId() + ".auto_tooltip", "基础的仪式挎包，可逐格布置仪式圆环。\n右击预览方块，来使用挎包内的物品放置对应方块。\nShift右击可打开挎包，将仪式材料放入其中。\n带有耐久度的物品将被使用到只剩1点耐久度，此时闪烁效果将会停止。\n");
        add(ModItems.RITUAL_SATCHEL_T2.get().getDescriptionId() + ".tooltip", "%s被束缚于此挎包中。");
        add(ModItems.RITUAL_SATCHEL_T2.get().getDescriptionId() + ".auto_tooltip", "高级的仪式挎包，可一次性布置整个仪式圆环。\n右击任意预览方块，来使用挎包内的物品放置所有预览方块。\nShift右击可打开挎包，将仪式材料放入其中。\n右击黄金仪式之碗，来移除仪式圆环并收集其材料。\n带有耐久度的物品将被使用到只剩1点耐久度，此时闪烁效果将会停止。\n");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T1.get().getDescriptionId() + ".tooltip", "将一个魔精束缚在挎包中，使召唤者能逐格构建五芒星阵。");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T2.get().getDescriptionId() + ".tooltip", "将一个火灵束缚在挎包中，使召唤者能一次性构建五芒星阵。");
        add(ModItems.RITUAL_DUMMY_CRAFT_UPGRADE_RITUAL_SATCHEL.get().getDescriptionId() + ".tooltip", "火灵会为召唤者升级学徒仪式挎包，使其一次性搭建五芒星阵。此配方可保留挎包内的物品。");

        add("ritual.occultism_rs.craft_ritual_satchel_t1.started", "开始进行仪式：合成学徒仪式挎包。");
        add("ritual.occultism_rs.craft_ritual_satchel_t1.interrupted", "仪式中断：合成学徒仪式挎包。");
        add("ritual.occultism_rs.craft_ritual_satchel_t1.finished", "仪式成功完成：合成学徒仪式挎包。");
        add("ritual.occultism_rs.craft_ritual_satchel_t1.conditions", "该仪式的部分条件仍未满足。");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.started", "开始进行仪式：合成匠心仪式挎包。");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.interrupted", "仪式中断：合成匠心仪式挎包。");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.finished", "仪式成功完成：合成匠心仪式挎包。");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.conditions", "该仪式的部分条件仍未满足。");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.started", "开始进行仪式：合成匠心仪式挎包。");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.interrupted", "仪式中断：合成匠心仪式挎包。");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.finished", "仪式成功完成：合成匠心仪式挎包。");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.conditions", "该仪式的部分条件仍未满足。");

        add(TranslationKeys.ITEM_GROUP, "神秘学 仪式挎包");
    }
}
