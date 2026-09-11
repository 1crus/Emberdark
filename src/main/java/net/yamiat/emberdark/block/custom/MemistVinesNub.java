package net.yamiat.emberdark.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.yamiat.emberdark.block.ModBlocks;

import java.util.function.ToIntFunction;

public class MemistVinesNub extends CaveVinesBlock implements BonemealableBlock {
    public MemistVinesNub(Properties properties) {
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
        level.setBlock(blockBelowPos, ModBlocks.MEMISTVINESNUB.get().defaultBlockState(), 2);

        // Step B: Change this current block into something else (e.g., a "stem" or "aged" version)
        // For this example, we change it to regular dirt, but you can change it to any BlockState
        BlockState changedState = ModBlocks.MEMISTVINES.get().defaultBlockState();
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
        if (blockAboveState.is(ModBlocks.MEMISTVINES.get())) {
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
        return ModBlocks.MEMISTVINES.get();
    }


}