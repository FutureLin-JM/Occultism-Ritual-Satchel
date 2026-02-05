package com.futurelin.occultism_rs.datagen;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OccultismRS.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RITUAL_SATCHEL_T1.get());
        basicItem(ModItems.RITUAL_SATCHEL_T2.get());

        ritualSatchelItem(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T1.get());
        ritualSatchelItem(ModItems.RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T2.get());
        ritualSatchelItem(ModItems.RITUAL_DUMMY_CRAFT_UPGRADE_RITUAL_SATCHEL.get());
    }

    @Override
    public ItemModelBuilder basicItem(ResourceLocation item) {
        String path = item.getPath();
        String builderPath = path.contains("/") ? item.getNamespace() + ":" + "item/" + path : path;
        return getBuilder(builderPath)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + path));
    }

    public ItemModelBuilder ritualSatchelItem(Item item) {
        return ritualSatchelItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    public ItemModelBuilder ritualSatchelItem(ResourceLocation item) {
        String path = item.getPath();
        String builderPath = path.contains("/") ? item.getNamespace() + ":" + "item/" + path : path;
        return getBuilder(builderPath)
                .parent(new ModelFile.UncheckedModelFile("occultism:item/ritual_dummy"));
    }

}
