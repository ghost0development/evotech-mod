package com.evotech.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ModTier {

    public static final Tier FLINT = new SimpleTier(1, 200, 4.0F, 1.0F, 5,
            () -> Ingredient.of(net.minecraft.world.item.Items.FLINT));
    public static final Tier COPPER = new SimpleTier(2, 300, 5.0F, 1.5F, 8,
            () -> Ingredient.of(net.minecraft.world.item.Items.COPPER_INGOT));
    public static final Tier BRONZE = new SimpleTier(2, 500, 6.0F, 2.0F, 10,
            () -> Ingredient.of(com.evotech.init.ModItems.BRONZE_INGOT.get()));
    public static final Tier STEEL = new SimpleTier(3, 800, 7.0F, 2.5F, 12,
            () -> Ingredient.of(com.evotech.init.ModItems.STEEL_INGOT.get()));

    private static class SimpleTier implements Tier {
        private final int level;
        private final int uses;
        private final float speed;
        private final float attackDamageBonus;
        private final int enchantmentValue;
        private final java.util.function.Supplier<Ingredient> repairIngredient;

        public SimpleTier(int level, int uses, float speed, float attackDamageBonus, int enchantmentValue,
                          java.util.function.Supplier<Ingredient> repairIngredient) {
            this.level = level;
            this.uses = uses;
            this.speed = speed;
            this.attackDamageBonus = attackDamageBonus;
            this.enchantmentValue = enchantmentValue;
            this.repairIngredient = repairIngredient;
        }

        @Override public int getUses() { return uses; }
        @Override public float getSpeed() { return speed; }
        @Override public float getAttackDamageBonus() { return attackDamageBonus; }
        @Override public int getLevel() { return level; }
        @Override public int getEnchantmentValue() { return enchantmentValue; }

        @Override
        public TagKey<Block> getTag() {
            return BlockTags.MINEABLE_WITH_PICKAXE;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return repairIngredient.get();
        }
    }
}
