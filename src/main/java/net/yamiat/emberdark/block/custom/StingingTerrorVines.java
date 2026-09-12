package net.yamiat.emberdark.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yamiat.emberdark.block.ModBlocks;

public class StingingTerrorVines extends CaveVinesPlantBlock {
    public StingingTerrorVines(Properties properties) {
        super(properties);
    }

@Override
    public void performBonemeal(ServerLevel p_220938_, RandomSource p_220939_, BlockPos p_220940_, BlockState p_220941_) {
        p_220938_.setBlock(p_220940_, p_220941_.setValue(BERRIES, Boolean.valueOf(false)), 2);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        // Cast or ensure your registered block is a GrowingPlantHeadBlock
        return (GrowingPlantHeadBlock) ModBlocks.STINGINGTERRORVINESNUB.get();
    }

    static boolean hasGlowBerries(BlockState pState) {
        return false;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        // Run code only on the server side and ensure the entity can take status effects
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {

            // Check if the entity already has the poison effect to avoid spamming particles
            if (!livingEntity.hasEffect(MobEffects.POISON)) {

                // Apply Poison for 4 seconds (80 ticks) at amplifier 0 (Poison I)
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 80, 2));

            }
        }
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {

            // Check if the entity already has the poison effect to avoid spamming particles
            if (!livingEntity.hasEffect(MobEffects.CONFUSION)) {

                // Apply Poison for 4 seconds (80 ticks) at amplifier 0 (Poison I)
                livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 9));

            }
        }

        if (entity instanceof LivingEntity) {
            // Arguments: state, and a Vec3 representing speed multipliers for (X, Y, Z)
            // Sweet berry bushes use standard multipliers of (0.8D, 0.75D, 0.8D)
            entity.makeStuckInBlock(state, new Vec3(0.5D, 0.45D, 0.5D));
        }
        if (entity.xOld != entity.getX() || entity.zOld != entity.getZ()) {
            double distance = Math.abs(entity.getX() - entity.xOld) + Math.abs(entity.getZ() - entity.zOld);

            // Only hurt if they moved a noticeable amount
            if (distance > 0.003D) {
                // Use standard sweet berry bush damage type
                DamageSource genericDamage = level.damageSources().sweetBerryBush();
                entity.hurt(genericDamage, 5.0F); // 1.0F is half a heart
            }


            super.entityInside(state, level, pos, entity);
        }
    }



}