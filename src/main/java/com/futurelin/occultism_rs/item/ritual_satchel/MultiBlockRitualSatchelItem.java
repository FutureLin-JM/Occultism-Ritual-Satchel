package com.futurelin.occultism_rs.item.ritual_satchel;

import com.futurelin.occultism_rs.TranslationKeys;
import com.futurelin.occultism_rs.container.RitualSatchelContainer;
import com.futurelin.occultism_rs.registry.ModTags;
import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.multiblock.matcher.AnyMatcher;
import com.klikli_dev.modonomicon.multiblock.matcher.DisplayOnlyMatcher;
import com.klikli_dev.occultism.common.container.storage.SatchelInventory;
import com.klikli_dev.occultism.registry.OccultismBlocks;
import com.klikli_dev.occultism.registry.OccultismRecipes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.stream.Collectors;

public class MultiBlockRitualSatchelItem extends RitualSatchelItem {

    public MultiBlockRitualSatchelItem(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useOnClientSide(UseOnContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos()).is(OccultismBlocks.GOLDEN_SACRIFICIAL_BOWL.get())) {
            return InteractionResult.SUCCESS;
        }
        return super.useOnClientSide(context);
    }

    protected InteractionResult collectPentacle(UseOnContext context) {
        var pentacles = context.getLevel().getRecipeManager().getAllRecipesFor(OccultismRecipes.RITUAL_TYPE.get()).stream()
                               .collect(Collectors.toMap(
                                       r -> r.getPentacle().getId(),
                                       r -> r.getPentacle(),
                                       (existing, replacement) -> existing
                               )).values().stream()
                               .map(pentacle -> Pair.of(pentacle, pentacle.validate(context.getLevel(), context.getClickedPos())))
                               .filter(p -> p.getSecond() != null)
                               .toList();

        SatchelInventory satchelInventory = new SatchelInventory(context.getItemInHand(), RitualSatchelContainer.SATCHEL_SIZE);
        IItemHandler inventory = new InvWrapper(satchelInventory);


        for (var pentacle : pentacles) {
            var simulation = pentacle.getFirst().simulate(context.getLevel(), context.getClickedPos(), pentacle.getSecond(), false, false);

            for (var targetMatcher : simulation.getSecond()) {
                if (targetMatcher.getStateMatcher().getType().equals(AnyMatcher.TYPE) || targetMatcher.getStateMatcher().getType().equals(DisplayOnlyMatcher.TYPE)) {
                    continue;
                }

                var blockState = context.getLevel().getBlockState(targetMatcher.getWorldPosition());

                if (blockState.isAir()) {
                    continue;
                }

                if (!blockState.is(ModTags.Blocks.PENTACLE_MATERIALS)) {
                    continue;
                }

                var blockEntity = context.getLevel().getBlockEntity(targetMatcher.getWorldPosition());

                var drops = Block.getDrops(blockState, (ServerLevel) context.getLevel(), targetMatcher.getWorldPosition(), blockEntity, null, ItemStack.EMPTY);

                context.getLevel().setBlock(targetMatcher.getWorldPosition(), Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);

                for (var drop : drops) {
                    var remainder = ItemHandlerHelper.insertItemStacked(inventory, drop, false);
                    if (!remainder.isEmpty()) {
                        ItemHandlerHelper.giveItemToPlayer(context.getPlayer(), remainder);
                    }
                }
            }
        }

        satchelInventory.writeItemStack();
        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useOnServerSide(UseOnContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos()).is(OccultismBlocks.GOLDEN_SACRIFICIAL_BOWL.get())) {
            return this.collectPentacle(context);
        }

        var targetPentacle = this.targetPentacles().get(context.getPlayer().getUUID());
        if (targetPentacle == null || targetPentacle.timeWhenAdded() < context.getLevel().getGameTime() - 5) {
            return InteractionResult.FAIL;
        }

        if (!targetPentacle.target().equals(context.getClickedPos())) {
            return InteractionResult.FAIL;
        }

        var multiblock = ModonomiconAPI.get().getMultiblock(targetPentacle.multiblock());
        var simulation = multiblock.simulate(context.getLevel(), targetPentacle.anchor(), targetPentacle.facing(), false, false);

        boolean placedAnything = false;
        for (var targetMatcher : simulation.getSecond()) {
            var localContext = new UseOnContext(context.getPlayer(), context.getHand(),
                    new BlockHitResult(targetMatcher.getWorldPosition().getCenter(), context.getClickedFace(), targetMatcher.getWorldPosition().above(), false));
            if (this.tryPlaceBlockForMatcher(localContext, targetMatcher) == PlacementResult.SUCCESS) {
                placedAnything = true;
            }
        }

        if (!placedAnything) {
            context.getPlayer().displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_NO_VALID_ITEM_IN_SATCHEL).withStyle(ChatFormatting.YELLOW), true);
            return InteractionResult.FAIL;
        }

        return InteractionResult.SUCCESS;
    }
}
