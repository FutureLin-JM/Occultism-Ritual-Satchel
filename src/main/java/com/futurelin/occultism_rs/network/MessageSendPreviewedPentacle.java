package com.futurelin.occultism_rs.network;

import com.futurelin.occultism_rs.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.network.NetworkEvent;

public class MessageSendPreviewedPentacle extends MessageBase {
    public ResourceLocation multiblock;
    public BlockPos anchor;
    public Rotation facing;
    public BlockPos target;

    public MessageSendPreviewedPentacle(FriendlyByteBuf buf) {
        this.decode(buf);
    }

    public MessageSendPreviewedPentacle(ResourceLocation multiblock, BlockPos anchor, Rotation facing, BlockPos target) {
        this.multiblock = multiblock;
        this.anchor = anchor;
        this.facing = facing;
        this.target = target;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.multiblock);
        buf.writeBlockPos(this.anchor);
        buf.writeEnum(this.facing);
        buf.writeBlockPos(this.target);
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.multiblock = buf.readResourceLocation();
        this.anchor = buf.readBlockPos();
        this.facing = buf.readEnum(Rotation.class);
        this.target = buf.readBlockPos();
    }

    @Override
    public void onServerReceived(MinecraftServer minecraftServer, ServerPlayer player, NetworkEvent.Context context) {
        ModItems.RITUAL_SATCHEL_T1.get().setTargetPentacles(player.getUUID(), this.multiblock, this.anchor, this.facing, this.target, player.level().getGameTime());
        ModItems.RITUAL_SATCHEL_T2.get().setTargetPentacles(player.getUUID(), this.multiblock, this.anchor, this.facing, this.target, player.level().getGameTime());
    }
}
