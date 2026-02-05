package com.futurelin.occultism_rs.datagen.lang;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.TranslationKeys;
import com.futurelin.occultism_rs.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModENUSProvider extends LanguageProvider {
    public ModENUSProvider(PackOutput output) {
        super(output, OccultismRS.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.RITUAL_SATCHEL_T1.get(), "Apprentice Ritual Satchel");
        add(ModItems.RITUAL_SATCHEL_T2.get(), "Artisanal Ritual Satchel");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T1.get(), "Ritual: Craft Apprentice Ritual Satchel");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T2.get(), "Ritual: Craft Artisanal Ritual Satchel");
        add(ModItems.RITUAL_DUMMY_CRAFT_UPGRADE_RITUAL_SATCHEL.get(), "Ritual: Craft Artisanal Ritual Satchel");

        add(TranslationKeys.RITUAL_SATCHEL_NO_PREVIEW_IN_WORLD, " You need to preview a pentacle using the Dictionary of Spirits.");
        add(TranslationKeys.RITUAL_SATCHEL_NO_PREVIEW_BLOCK_TARGETED, "You need to aim the ritual satchel at a preview block.");
        add(TranslationKeys.RITUAL_SATCHEL_NO_VALID_ITEM_IN_SATCHEL, "There is no valid item in the satchel for this previewed block.");
        add(TranslationKeys.RITUAL_SATCHEL_BLOCK_ABOVE_NOT_AIR, "The block above the clicked position is not empty.");
        add(TranslationKeys.RITUAL_SATCHEL_BLOCK_AT_POSITION_NOT_AIR, "The block at the clicked position is not empty.");
        add(TranslationKeys.RITUAL_SATCHEL_INVALID_MATCHER, "Cannot place a block for an ANY or DISPLAY_ONLY multiblock matcher");
        add(TranslationKeys.RITUAL_SATCHEL_GLYPH_CANNOT_SURVIVE, "Cannot place a glyph here.");
        add(TranslationKeys.RITUAL_SATCHEL_WILL_BREAK_ITEM, "Some item is breaking, repair it!");
        add(TranslationKeys.Put_In_Satchel, "Stored pentacles in the satchel");

        add(ModItems.RITUAL_SATCHEL_T1.get().getDescriptionId() + ".tooltip", "%s is bound to this satchel.");
        add(ModItems.RITUAL_SATCHEL_T1.get().getDescriptionId() + ".auto_tooltip", "A basic ritual satchel that can place ritual circles block by block.\nRight-Click a preview block to place it out of the satchel.\nShift-Right-Click to open the satchel and add ritual ingredients.\nAn item with durability will be used until only 1 durability remains, which will stop the glint effect.\n");
        add(ModItems.RITUAL_SATCHEL_T2.get().getDescriptionId() + ".tooltip", "%s is bound to this satchel.");
        add(ModItems.RITUAL_SATCHEL_T2.get().getDescriptionId() + ".auto_tooltip", "An improved ritual satchel that can place an entire ritual circle at once.\nRight-Click any preview block to place all preview blocks out of the satchel.\nShift-Right-Click to open the satchel and add ritual ingredients.\nRight-Click a Golden Bowl to remove the ritual circle and collect the ingredients.\nAn item with durability will be used until only 1 durability remains, which will stop the glint effect.\n");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T1.get().getDescriptionId() + ".tooltip", "Binds a Foliot into a satchel to build pentacles step-by-step for the summoner.");
        add(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T2.get().getDescriptionId() + ".tooltip", "Binds an Afrit into a satchel to build pentacles all at once for the summoner.");
        add(ModItems.RITUAL_DUMMY_CRAFT_UPGRADE_RITUAL_SATCHEL.get().getDescriptionId() + ".tooltip", "An Afrit will upgrade the apprentice ritual satchel to build pentacles all at once for the summoner. This recipe keep the items inside the satchel.");

        add("ritual.occultism_rs.craft_ritual_satchel_t1.started", "Starting the ritual: Craft Apprentice Ritual Satchel.");
        add("ritual.occultism_rs.craft_ritual_satchel_t1.interrupted", "Interruption in the ritual: Craft Apprentice Ritual Satchel.");
        add("ritual.occultism_rs.craft_ritual_satchel_t1.finished", "Ritual completed successfully: Craft Apprentice Ritual Satchel.");
        add("ritual.occultism_rs.craft_ritual_satchel_t1.conditions", "Not all requirements for this ritual are met.");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.started", "Starting the ritual: Craft Artisanal Ritual Satchel.");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.interrupted", "Interruption in the ritual: Craft Artisanal Ritual Satchel.");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.finished", "Ritual completed successfully: Craft Artisanal Ritual Satchel.");
        add("ritual.occultism_rs.craft_ritual_satchel_t2.conditions", "Not all requirements for this ritual are met.");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.started", "Starting the ritual: Craft Artisanal Ritual Satchel.");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.interrupted", "Interruption in the ritual: Craft Artisanal Ritual Satchel.");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.finished", "Ritual completed successfully: Craft Artisanal Ritual Satchel.");
        add("ritual.occultism_rs.craft_upgrade_ritual_satchel.conditions", "Not all requirements for this ritual are met.");


        add(TranslationKeys.ITEM_GROUP, "Occultism Ritual Satchel");
    }
}
