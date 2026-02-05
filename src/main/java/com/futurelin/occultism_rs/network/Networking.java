package com.futurelin.occultism_rs.network;

import com.futurelin.occultism_rs.OccultismRS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {
    private static final String PROTOCOL_VERSION = "1.0";

    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(OccultismRS.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void register() {

        INSTANCE.registerMessage(nextID(),
                MessageSendPreviewedPentacle.class,
                MessageSendPreviewedPentacle::encode,
                MessageSendPreviewedPentacle::new,
                ModPacketHandler::handle
        );
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
