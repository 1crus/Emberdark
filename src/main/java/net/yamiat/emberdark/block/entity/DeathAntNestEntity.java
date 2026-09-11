package net.yamiat.emberdark.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class DeathAntNestEntity extends BlockEntity {
    private final List<CompoundTag> storedAnts = new ArrayList<>();

    public DeathAntNestEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ANT_NEST_BE.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        // Save stored ants data to NBT
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        // Load stored ants data from NBT
    }
}