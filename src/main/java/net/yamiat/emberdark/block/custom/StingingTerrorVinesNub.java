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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yamiat.emberdark.block.ModBlocks;

public class StingingTerrorVinesNub extends CaveVinesBlock implements BonemealableBlock {
    public StingingTerrorVinesNub(Properties properties) {
        super(properties);
    }


    // 1. Check if the block can be bone-mealed

    @Override
    public boolean isValidBonemealTarget(net.minecraft.world.level.LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        BlockPos blockBelowPos = pos.below();
        BlockState blockBelowState = level.getBlockState(blockBelowPos);

        // Target can be bonemealed if the space below is empty/replaceable
        return blockBelowState.canBeReplaced();
    }

    // 2. Determine if the bone-meal succeeds (can use a random chance if desired)
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true; // 100% success rate
    }

    // 3. Perform the actual growth and self-transformation
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos blockBelowPos = pos.below();

        // Step A: Grow this block into the position below
        // Replace 'this.defaultBlockState()' with a different block state if the bottom part should look different
        level.setBlock(blockBelowPos, ModBlocks.STINGINGTERRORVINESNUB.get().defaultBlockState(), 2);

        // Step B: Change this current block into something else (e.g., a "stem" or "aged" version)
        // For this example, we change it to regular dirt, but you can change it to any BlockState
        BlockState changedState = ModBlocks.STINGINGTERRORVINES.get().defaultBlockState();
        level.setBlock(pos, changedState, 2);
    }

    @Override
    public boolean canSurvive(BlockState state, net.minecraft.world.level.LevelReader level, BlockPos pos) {
        BlockPos blockAbovePos = pos.above();
        BlockState blockAboveState = level.getBlockState(blockAbovePos);

        // 1. Check if the block above is this vine
        if (blockAboveState.is(this)) {
            return true;
        }

        // 2. Check if the block above is the block it transforms into (e.g., DIRT)
        if (blockAboveState.is(ModBlocks.STINGINGTERRORVINES.get())) {
            return true;
        }

        if (blockAboveState.is(ModBlocks.CAKOBANS_FINGERS_LEAVES.get())) {
            return true;
        }

        // 3. Check if the block above has a solid, sturdy bottom face (like Stone, Wood, etc.)
        return blockAboveState.isFaceSturdy(level, blockAbovePos, net.minecraft.core.Direction.DOWN);
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.STINGINGTERRORVINES.get();
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