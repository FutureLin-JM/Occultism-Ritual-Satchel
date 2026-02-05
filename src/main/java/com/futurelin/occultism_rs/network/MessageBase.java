package com.futurelin.occultism_rs.network;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

public abstract class MessageBase implements IMessage {
    protected MessageBase() {
    }

    @OnlyIn(Dist.CLIENT)
    public void onClientReceived(Minecraft minecart, Player player, NetworkEvent.Context context) {
    }

    public void onServerReceived(MinecraftServer minecraftServer, ServerPlayer player, NetworkEvent.Context context) {
    }
}
