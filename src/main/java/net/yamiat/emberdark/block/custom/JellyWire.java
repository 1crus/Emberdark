package net.yamiat.emberdark.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class JellyWire extends VineBlock {
    public JellyWire(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return true;
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        // Run code only on the server side and ensure the entity can take status effects
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {

            // Check if the entity already has the poison effect to avoid spamming particles
            if (!livingEntity.hasEffect(MobEffects.DIG_SLOWDOWN)) {

                // Apply Poison for 4 seconds (80 ticks) at amplifier 0 (Poison I)
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 500, 9));

            }
        }
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {

            // Check if the entity already has the poison effect to avoid spamming particles
            if (!livingEntity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {

                // Apply Poison for 4 seconds (80 ticks) at amplifier 0 (Poison I)
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500, 9));

            }
        }
    }
}
