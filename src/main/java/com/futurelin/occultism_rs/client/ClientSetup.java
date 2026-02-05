package com.futurelin.occultism_rs.client;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.client.gui.RitualSatchelScreen;
import com.futurelin.occultism_rs.registry.ModContainers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OccultismRS.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModContainers.RITUAL_SATCHEL.get(), RitualSatchelScreen::new);
        });
    }
}
