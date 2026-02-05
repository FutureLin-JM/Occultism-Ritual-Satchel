package com.futurelin.occultism_rs.registry;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.container.RitualSatchelContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, OccultismRS.MOD_ID);

    public static final RegistryObject<MenuType<RitualSatchelContainer>> RITUAL_SATCHEL =
            CONTAINERS.register("ritual_satchel",
                    () -> IForgeMenuType.create(RitualSatchelContainer::createClientContainer));
}