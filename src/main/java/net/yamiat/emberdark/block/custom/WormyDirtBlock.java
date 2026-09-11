package net.yamiat.emberdark.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;


public class WormyDirtBlock extends Block {
    public WormyDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Check conditions for spreading (e.g., light level if needed)
        BlockPos targetPos = pos.offset(
                random.nextInt(3) - 1,
                random.nextInt(5) - 3,
                random.nextInt(3) - 1
        );

        // Example: Spread onto standard dirt blocks
        if (level.getBlockState(targetPos).is(Blocks.COARSE_DIRT)) {
            level.setBlockAndUpdate(targetPos, this.defaultBlockState());
        }
    }
}