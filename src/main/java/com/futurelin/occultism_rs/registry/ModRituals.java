package com.futurelin.occultism_rs.registry;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.common.ritual.UpgradeRitual;
import com.klikli_dev.occultism.Occultism;
import com.klikli_dev.occultism.common.ritual.RitualFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRituals {
    public static final DeferredRegister<RitualFactory> RITUAL_FACTORIES =
            DeferredRegister.create(new ResourceLocation(Occultism.MODID, "ritual_factory"), OccultismRS.MOD_ID);

    public static final RegistryObject<RitualFactory> UPGRADE_RITUAL =
            RITUAL_FACTORIES.register("upgrade",
                    () -> new RitualFactory(UpgradeRitual::new));
}
