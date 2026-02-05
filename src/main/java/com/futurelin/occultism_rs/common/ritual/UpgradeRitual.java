package com.futurelin.occultism_rs.common.ritual;

import com.klikli_dev.occultism.common.blockentity.GoldenSacrificialBowlBlockEntity;
import com.klikli_dev.occultism.common.ritual.Ritual;
import com.klikli_dev.occultism.crafting.recipe.RitualRecipe;
import com.klikli_dev.occultism.util.ItemNBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class UpgradeRitual extends Ritual {

    public UpgradeRitual(RitualRecipe recipe) {
        super(recipe);
    }

    @Override
    public void finish(Level level, BlockPos goldenBowlPosition, GoldenSacrificialBowlBlockEntity blockEntity, @Nullable ServerPlayer castingPlayer, ItemStack activationItem) {
        super.finish(level, goldenBowlPosition, blockEntity, castingPlayer, activationItem);

        ItemStack copy = activationItem.copy();
        activationItem.shrink(1); //remove activation item.

        ItemStack base = blockEntity.consumedIngredients.get(0).copy();

        ((ServerLevel) level).sendParticles(ParticleTypes.LARGE_SMOKE, goldenBowlPosition.getX() + 0.5,
                goldenBowlPosition.getY() + 0.5, goldenBowlPosition.getZ() + 0.5, 1, 0, 0, 0, 0);

        ItemStack result = this.recipe.getResultItem(level.registryAccess()).copy();
        int baseDamageValue = base.isDamageableItem() ? base.getDamageValue() : 0;

        if (base.hasTag()) {
            CompoundTag baseTag = base.getTag();
            result.setTag(baseTag.copy());
        }

        if (result.isDamageableItem() && baseDamageValue > 0) {
            result.setDamageValue(baseDamageValue);
        }

        if (copy.hasTag() && copy.getTag().contains(ItemNBTUtil.SPIRIT_NAME_TAG)) {
            ItemNBTUtil.setBoundSpiritName(result, ItemNBTUtil.getBoundSpiritName(copy));
        }

        this.dropResult(level, goldenBowlPosition, blockEntity, castingPlayer, result);
    }
}
