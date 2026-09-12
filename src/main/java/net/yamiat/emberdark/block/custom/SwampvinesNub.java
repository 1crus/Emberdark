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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.yamiat.emberdark.block.ModBlocks;

import java.util.List;

public class SwampvinesNub extends CaveVinesBlock implements BonemealableBlock {
    public SwampvinesNub(Properties properties) {
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
        level.setBlock(blockBelowPos, ModBlocks.SWAMPVINESNUB.get().defaultBlockState(), 2);

        // Step B: Change this current block into something else (e.g., a "stem" or "aged" version)
        // For this example, we change it to regular dirt, but you can change it to any BlockState
        BlockState changedState = ModBlocks.SWAMPVINES.get().defaultBlockState();
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
        if (blockAboveState.is(ModBlocks.SWAMPVINES.get())) {
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
        return ModBlocks.SWAMPVINES.get();
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        // Run only on the server side to prevent desync
        if (!level.isClientSide()) {

            // Trigger only if a LivingEntity (Player or Mob) enters the block space
            if (entity instanceof LivingEntity) {

                // Define the area of effect (e.g., a radius of 5 blocks around the block)
                double radius = 5.0;
                AABB areaOfEffect = new AABB(pos).inflate(radius);

                // Fetch all living entities inside that bounding box
                List<LivingEntity> entitiesAround = level.getEntitiesOfClass(LivingEntity.class, areaOfEffect);

                // Apply the poison effect to everyone in range
                for (LivingEntity nearbyEntity : entitiesAround) {

                    // Duration is in ticks (20 ticks = 1 second). This sets it for 5 seconds.
                    // Amplifier is the effect level (0 = Poison I)
                    nearbyEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
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




}