package net.yamiat.emberdark.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.yamiat.emberdark.block.ModBlocks;

public class CakobansLureGoal extends MoveToBlockGoal {
    protected final PathfinderMob mob;

    // Constructor
    public CakobansLureGoal(PathfinderMob mob, double speedModifier, int horizontalRange, int verticalRange) {
        super(mob, speedModifier, horizontalRange, verticalRange);
        this.mob = mob;

        // CRITICAL STEP: Tells the AI scanning algorithm where to start vertically.
        // A value of 0 or a positive number forces it to scan above/at its feet level
        // rather than starting its search below ground.
        this.verticalSearchStart = 0;
    }

    // This method scans the area and decides if a block matches the target criteria
    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        // Check if the block is loaded
        if (!level.hasChunkAt(pos)) {
            return false;
        }

        BlockState state = level.getBlockState(pos);

        // Example: Mob wants to go to a Diamond Block
        return state.is(ModBlocks.CAKOBANS_FINGER.get());
    }

    // Optional: Overriding tick if you want to perform actions while moving or when arrived
    @Override
    public void tick() {
        super.tick(); // Handles standard movement pathing to the block

        if (this.isReachedTarget()) {
            // Actions to execute when the mob successfully arrives at the block
            // For example: play an animation, eat the block, or damage it
        }
    }
}