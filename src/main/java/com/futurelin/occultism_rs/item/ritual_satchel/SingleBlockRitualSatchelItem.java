package com.futurelin.occultism_rs.item.ritual_satchel;

import com.futurelin.occultism_rs.TranslationKeys;
import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class SingleBlockRitualSatchelItem extends RitualSatchelItem {

    public SingleBlockRitualSatchelItem(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useOnServerSide(UseOnContext context) {
        var targetPentacle = this.targetPentacles().get(context.getPlayer().getUUID());

        if (targetPentacle == null || targetPentacle.timeWhenAdded() < context.getLevel().getGameTime() - 5) {
            return InteractionResult.FAIL;
        }

        if (!targetPentacle.target().equals(context.getClickedPos())) {
            return InteractionResult.FAIL;
        }

        var multiblock = ModonomiconAPI.get().getMultiblock(targetPentacle.multiblock());
        var simulation = multiblock.simulate(context.getLevel(), targetPentacle.anchor(), targetPentacle.facing(), false, false);

        var targetMatcher = simulation.getSecond().stream().filter(p -> p.getWorldPosition().equals(context.getClickedPos())).findFirst();
        if (targetMatcher.isEmpty()) {
            return InteractionResult.FAIL;
        }

        return switch (this.tryPlaceBlockForMatcher(context, targetMatcher.get())) {
            case SUCCESS -> InteractionResult.SUCCESS;
            case ERROR_NO_MATCHING_BLOCK_FOUND -> {
                context.getPlayer().displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_NO_VALID_ITEM_IN_SATCHEL).withStyle(ChatFormatting.YELLOW), true);
                yield InteractionResult.FAIL;
            }
            case ERROR_BLOCK_ABOVE_NOT_AIR -> {
                context.getPlayer().displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_BLOCK_ABOVE_NOT_AIR).withStyle(ChatFormatting.YELLOW), true);
                yield InteractionResult.FAIL;
            }
            case ERROR_BLOCK_AT_POSITION_NOT_AIR -> {
                context.getPlayer().displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_BLOCK_AT_POSITION_NOT_AIR).withStyle(ChatFormatting.YELLOW), true);
                yield InteractionResult.FAIL;
            }
            case ERROR_INVALID_MATCHER -> {
                context.getPlayer().displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_INVALID_MATCHER).withStyle(ChatFormatting.YELLOW), true);
                yield InteractionResult.FAIL;
            }
            case ERROR_GLYPH_CANNOT_SURVIVE -> {
                context.getPlayer().displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_GLYPH_CANNOT_SURVIVE).withStyle(ChatFormatting.YELLOW), true);
                yield InteractionResult.FAIL;
            }
            case ERROR_WILL_BREAK_ITEM -> {
                context.getPlayer().displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_WILL_BREAK_ITEM).withStyle(ChatFormatting.YELLOW), true);
                yield InteractionResult.FAIL;
            }
            default -> InteractionResult.FAIL;
        };
    }
}
