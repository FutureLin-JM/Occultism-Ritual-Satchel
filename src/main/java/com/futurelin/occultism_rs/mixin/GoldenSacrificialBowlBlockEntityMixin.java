package com.futurelin.occultism_rs.mixin;

import com.futurelin.occultism_rs.TranslationKeys;
import com.futurelin.occultism_rs.item.ritual_satchel.MultiBlockRitualSatchelItem;
import com.klikli_dev.occultism.common.blockentity.GoldenSacrificialBowlBlockEntity;
import com.klikli_dev.occultism.registry.OccultismSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GoldenSacrificialBowlBlockEntity.class, remap = false)
public abstract class GoldenSacrificialBowlBlockEntityMixin {
    @Inject(method = "activate", at = @At(
            value = "INVOKE",
            target = "Lcom/klikli_dev/occultism/common/blockentity/GoldenSacrificialBowlBlockEntity;helpWithRitual(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Z"),
            cancellable = true)
    private void addRitualSatchelFeedback(Level level, BlockPos pos, ServerPlayer serverPlayer, InteractionHand hand, Direction face, CallbackInfoReturnable<Boolean> cir) {
        ItemStack activationItem = serverPlayer.getItemInHand(hand);
        if (activationItem.getItem() instanceof MultiBlockRitualSatchelItem) {
            ((ServerLevel) level)
                    .sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                            10, 0.3, 0.3, 0.3, 0.03);

            level.playSound(null, pos, OccultismSounds.POOF.get(), SoundSource.PLAYERS, 1, 3);
            serverPlayer.displayClientMessage(Component.translatable(TranslationKeys.Put_In_Satchel), true);
            cir.setReturnValue(false);
        }
    }
}
