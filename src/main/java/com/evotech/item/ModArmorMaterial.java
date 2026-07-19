package com.evotech.item;

import com.evotech.EvoTech;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterial implements ArmorMaterial {
    COPPER("copper", 15, new int[]{2, 5, 6, 2}, 8,
            SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(
            net.minecraft.world.item.Items.COPPER_INGOT)),
    BRONZE("bronze", 20, new int[]{2, 5, 6, 2}, 10,
            SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(
            com.evotech.init.ModItems.BRONZE_INGOT.get())),
    STEEL("steel", 30, new int[]{3, 6, 8, 3}, 12,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> Ingredient.of(
            com.evotech.init.ModItems.STEEL_INGOT.get())),
    TUNGSTEN("tungsten", 40, new int[]{3, 7, 9, 3}, 8,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.2F, () -> Ingredient.of(
            com.evotech.init.ModItems.TUNGSTEN_INGOT.get())),
    TITANIUM("titanium", 55, new int[]{4, 8, 10, 4}, 10,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.3F, () -> Ingredient.of(
            com.evotech.init.ModItems.TITANIUM_INGOT.get())),
    IRIDIUM("iridium", 70, new int[]{5, 9, 11, 5}, 14,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 5.0F, 0.4F, () -> Ingredient.of(
            com.evotech.init.ModItems.IRIDIUM_INGOT.get()));

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final java.util.function.Supplier<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
                     SoundEvent sound, float toughness, float knockbackResistance,
                     java.util.function.Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override public int getDurabilityForType(ArmorItem.Type type) {
        return MAX_DAMAGE_ARRAY[type.ordinal()] * durabilityMultiplier;
    }
    @Override public int getDefenseForType(ArmorItem.Type type) {
        return slotProtections[type.ordinal()];
    }
    @Override public int getEnchantmentValue() { return enchantmentValue; }
    @Override public SoundEvent getEquipSound() { return sound; }
    @Override public Ingredient getRepairIngredient() { return repairIngredient.get(); }
    @Override public String getName() { return new ResourceLocation(EvoTech.MOD_ID, name).toString(); }
    @Override public float getToughness() { return toughness; }
    @Override public float getKnockbackResistance() { return knockbackResistance; }
}
