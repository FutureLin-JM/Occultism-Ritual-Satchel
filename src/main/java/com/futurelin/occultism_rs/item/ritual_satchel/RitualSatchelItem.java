package com.futurelin.occultism_rs.item.ritual_satchel;

import com.futurelin.occultism_rs.TranslationKeys;
import com.futurelin.occultism_rs.api.access.IChalkItemAccessor;
import com.futurelin.occultism_rs.container.RitualSatchelContainer;
import com.futurelin.occultism_rs.network.MessageSendPreviewedPentacle;
import com.futurelin.occultism_rs.network.Networking;
import com.futurelin.occultism_rs.preview.ModonomiconPreviewCompat;
import com.futurelin.occultism_rs.registry.ModItems;
import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import com.klikli_dev.modonomicon.multiblock.matcher.AnyMatcher;
import com.klikli_dev.modonomicon.multiblock.matcher.DisplayOnlyMatcher;
import com.klikli_dev.occultism.common.container.storage.SatchelInventory;
import com.klikli_dev.occultism.common.item.tool.ChalkItem;
import com.klikli_dev.occultism.util.ItemNBTUtil;
import com.klikli_dev.occultism.util.TextUtil;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class RitualSatchelItem extends Item {
    private final Map<UUID, PentacleData> targetPentacles = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());

    public RitualSatchelItem(Properties properties) {
        super(properties);
    }

    public Map<UUID, PentacleData> targetPentacles() {
        return this.targetPentacles;
    }

    public void setTargetPentacles(UUID player, ResourceLocation multiblock, BlockPos anchor, Rotation facing, BlockPos target, long timeWhenAdded) {
        this.targetPentacles.put(player, new PentacleData(multiblock, anchor, facing, target, timeWhenAdded));
    }

    protected void openMenu(ServerPlayer player, ItemStack stack) {
        int selectedSlot = player.getInventory().selected;

        NetworkHooks.openScreen(player, new SimpleMenuProvider((id, playerInventory, unused) -> {
            return new RitualSatchelContainer(id, playerInventory, this.getInventory(player, stack), selectedSlot);
        }, stack.getDisplayName()), buffer -> buffer.writeVarInt(selectedSlot));
    }

    public Container getInventory(ServerPlayer player, ItemStack stack) {
        return new SatchelInventory(stack, RitualSatchelContainer.SATCHEL_SIZE);
    }

    protected PlacementResult tryPlaceBlockForMatcher(UseOnContext context, Multiblock.SimulateResult targetMatcher) {
        if (targetMatcher.getStateMatcher().getType().equals(AnyMatcher.TYPE) || targetMatcher.getStateMatcher().getType().equals(DisplayOnlyMatcher.TYPE)) {
            return PlacementResult.ERROR_INVALID_MATCHER;
        }

        var statePredicate = targetMatcher.getStateMatcher().getStatePredicate();

        SatchelInventory inventory = new SatchelInventory(context.getItemInHand(), RitualSatchelContainer.SATCHEL_SIZE);

        if (context.getItemInHand().is(ModItems.RITUAL_SATCHEL_T1.get()) && !context.getLevel().getBlockState(context.getClickedPos().above()).canBeReplaced()) {
            return PlacementResult.ERROR_BLOCK_ABOVE_NOT_AIR;
        }

        if (context.getItemInHand().is(ModItems.RITUAL_SATCHEL_T2.get()) && !context.getLevel().getBlockState(context.getClickedPos()).canBeReplaced()) {
            return PlacementResult.ERROR_BLOCK_AT_POSITION_NOT_AIR;
        }

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            var isGlyph = false;

            BlockState blockStateToPlace = null;
            if (stack.getItem() instanceof BlockItem blockItem) {
                var block = blockItem.getBlock();
                var blockPlaceContext = blockItem.updatePlacementContext(new BlockPlaceContext(context));
                if (blockPlaceContext == null) {
                    continue;
                }

                blockStateToPlace = block.getStateForPlacement(blockPlaceContext);
            } else if (stack.getItem() instanceof ChalkItem chalkItem) {
                var accessor = (IChalkItemAccessor) chalkItem;
                var chalkBlock = accessor.getGlyphBlock();
                blockStateToPlace = chalkBlock.getStateForPlacement(new BlockPlaceContext(context));
                isGlyph = true;
            }

            if (blockStateToPlace == null) {
                continue;
            }

            if (statePredicate.test(context.getLevel(), targetMatcher.getWorldPosition(), blockStateToPlace)) {
                if (stack.getMaxDamage() - stack.getDamageValue() == 1) {
                    if (context.getItemInHand().is(ModItems.RITUAL_SATCHEL_T1.get())) {
                        return PlacementResult.ERROR_WILL_BREAK_ITEM;
                    }
                    continue;
                }
                if (isGlyph && !blockStateToPlace.canSurvive(context.getLevel(), targetMatcher.getWorldPosition().above())) {
                    if (context.getItemInHand().is(ModItems.RITUAL_SATCHEL_T1.get())) {
                        return PlacementResult.ERROR_GLYPH_CANNOT_SURVIVE;
                    }
                    continue;
                }

                stack.useOn(new UseOnContext(context.getLevel(), context.getPlayer(), context.getHand(), stack, context.getHitResult()));
                inventory.setItem(i, stack);
                inventory.writeItemStack();
                return PlacementResult.SUCCESS;
            }
        }
        return PlacementResult.ERROR_NO_MATCHING_BLOCK_FOUND;
    }

    protected InteractionResult useOnClientSide(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        var preview = ModonomiconPreviewCompat.getCurrentPreview();

        if (player == null) {
            return InteractionResult.PASS;
        }

        if (preview == null || !preview.isAnchored()) {
            player.displayClientMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_NO_PREVIEW_IN_WORLD).withStyle(ChatFormatting.YELLOW), true);
            return InteractionResult.PASS;
        }

        var simulation = preview.multiblock().simulate(level, preview.anchor(), preview.facing(), false, false);

        var targetMatcher = simulation.getSecond().stream().filter(p -> p.getWorldPosition().equals(pos)).findFirst();
        if (targetMatcher.isEmpty() || targetMatcher.get().getStateMatcher().getType() == AnyMatcher.TYPE || targetMatcher.get().getStateMatcher().getType() == DisplayOnlyMatcher.TYPE) {
            player.sendSystemMessage(Component.translatable(TranslationKeys.RITUAL_SATCHEL_NO_PREVIEW_BLOCK_TARGETED).withStyle(ChatFormatting.YELLOW));
            return InteractionResult.PASS;
        }

        Networking.sendToServer(new MessageSendPreviewedPentacle(preview.multiblock().getId(), preview.anchor(), preview.facing(), pos));

        return InteractionResult.SUCCESS;
    }

    protected abstract InteractionResult useOnServerSide(UseOnContext context);

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        SatchelInventory inventory = new SatchelInventory(stack, RitualSatchelContainer.SATCHEL_SIZE);

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (!item.isEmpty() && item.getMaxDamage() - item.getDamageValue() == 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) {
            return new InteractionResultHolder<>(InteractionResult.PASS, player.getItemInHand(hand));
        }

        if (!player.isShiftKeyDown()) {
            return new InteractionResultHolder<>(InteractionResult.PASS, player.getItemInHand(hand));
        }

        final ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            this.openMenu(serverPlayer, stack);
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        if (context.getHand() != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }

        if (context.getLevel().isClientSide && context.getPlayer().isShiftKeyDown()) {
            return InteractionResult.SUCCESS;
        }

        if (context.getPlayer() instanceof ServerPlayer serverPlayer && !context.getLevel().isClientSide() && context.getPlayer().isShiftKeyDown()) {
            this.openMenu(serverPlayer, context.getItemInHand());
            return InteractionResult.SUCCESS;
        }

        if (context.getLevel().isClientSide()) {
            return this.useOnClientSide(context);
        } else {
            return this.useOnServerSide(context);
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(Component.translatable(this.getDescriptionId() + ".tooltip",
                TextUtil.formatDemonName(ItemNBTUtil.getBoundSpiritName(pStack))));

        pTooltipComponents.add(Component.translatable(this.getDescriptionId() + ".auto_tooltip")
                .withStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
    }

    public enum PlacementResult {
        SUCCESS,
        ERROR_INVALID_MATCHER,
        ERROR_BLOCK_ABOVE_NOT_AIR,
        ERROR_BLOCK_AT_POSITION_NOT_AIR,
        ERROR_NO_MATCHING_BLOCK_FOUND,
        ERROR_GLYPH_CANNOT_SURVIVE,
        ERROR_WILL_BREAK_ITEM
    }
}
