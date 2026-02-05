package com.futurelin.occultism_rs.registry;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.item.ritual_satchel.MultiBlockRitualSatchelItem;
import com.futurelin.occultism_rs.item.ritual_satchel.SingleBlockRitualSatchelItem;
import com.klikli_dev.occultism.common.item.DummyTooltipItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OccultismRS.MOD_ID);

    public static final RegistryObject<SingleBlockRitualSatchelItem> RITUAL_SATCHEL_T1 =
            ITEMS.register("ritual_satchel_t1",
                    () -> new SingleBlockRitualSatchelItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<MultiBlockRitualSatchelItem> RITUAL_SATCHEL_T2 =
            ITEMS.register("ritual_satchel_t2",
                    () -> new MultiBlockRitualSatchelItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T1 =
            ITEMS.register("ritual_dummy/craft_ritual_satchel_t1",
                    () -> new DummyTooltipItem(new Item.Properties()));
    public static final RegistryObject<Item> RITUAL_DUMMY_CRAFT_RITUAL_SATCHEL_T2 =
            ITEMS.register("ritual_dummy/craft_ritual_satchel_t2",
                    () -> new DummyTooltipItem(new Item.Properties()));
    public static final RegistryObject<Item> RITUAL_DUMMY_CRAFT_UPGRADE_RITUAL_SATCHEL =
            ITEMS.register("ritual_dummy/craft_upgrade_ritual_satchel",
                    () -> new DummyTooltipItem(new Item.Properties()));
}
