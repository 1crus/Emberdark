package net.yamiat.emberdark.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;


public class CognitiveGrassBlock extends Block {
    public CognitiveGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 1. First, check if this block itself is choked out (like grass dying under stone)
        if (!level.isEmptyBlock(pos.above()) && level.getBlockState(pos.above()).isSolidRender(level, pos.above())) {
            // Optional: Turn this block back into dirt if it's choked out
            level.setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
            return;
        }

        // 2. Select a random neighboring target position
        BlockPos targetPos = pos.offset(
                random.nextInt(3) - 1,
                random.nextInt(5) - 3,
                random.nextInt(3) - 1
        );

        // 3. Check if the target is dirt AND the block directly above the target is open air
        BlockPos blockAboveTarget = targetPos.above();

        if (level.getBlockState(targetPos).is(Blocks.OBSIDIAN) && level.isEmptyBlock(blockAboveTarget)) {
            level.setBlockAndUpdate(targetPos, this.defaultBlockState());
        }
    }}