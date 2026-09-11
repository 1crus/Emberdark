package net.yamiat.emberdark.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties INVESTEDPASTE = new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200), 1f).build();
}
