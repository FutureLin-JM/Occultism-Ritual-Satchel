package com.futurelin.occultism_rs.datagen;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.datagen.lang.ModENUSProvider;
import com.futurelin.occultism_rs.datagen.lang.ModZHCNProvider;
import com.futurelin.occultism_rs.datagen.tags.ModBlockTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = OccultismRS.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeClient(), new ModENUSProvider(packOutput));
        generator.addProvider(event.includeClient(), new ModZHCNProvider(packOutput));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
    }
}
