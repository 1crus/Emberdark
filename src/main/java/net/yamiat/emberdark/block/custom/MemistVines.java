package net.yamiat.emberdark.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.yamiat.emberdark.block.ModBlocks;

import java.util.function.ToIntFunction;

public class MemistVines extends CaveVinesPlantBlock {
    public MemistVines(Properties properties) {
        super(properties);
    }

@Override
    public void performBonemeal(ServerLevel p_220938_, RandomSource p_220939_, BlockPos p_220940_, BlockState p_220941_) {
        p_220938_.setBlock(p_220940_, p_220941_.setValue(BERRIES, Boolean.valueOf(false)), 2);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        // Cast or ensure your registered block is a GrowingPlantHeadBlock
        return (GrowingPlantHeadBlock) ModBlocks.MEMISTVINESNUB.get();
    }

}