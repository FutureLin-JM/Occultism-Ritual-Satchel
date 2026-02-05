package com.futurelin.occultism_rs.registry;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.TranslationKeys;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.klikli_dev.occultism.registry.OccultismCreativeModeTabs.OCCULTISM;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OccultismRS.MOD_ID);

    public static final RegistryObject<CreativeModeTab> OCCULTISM_RS_TAB =
            CREATIVE_MODE_TABS.register("occultism_rs_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RITUAL_SATCHEL_T1.get()))
                    .title(Component.translatable(TranslationKeys.ITEM_GROUP))
                    .displayItems((pParameters, pOutput) -> {
                        ModItems.ITEMS.getEntries().forEach(item -> {
                            pOutput.accept(item.get());
                        });
                    })
                    .withTabsBefore(OCCULTISM.getKey())
                    .build());
}
