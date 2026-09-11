package net.yamiat.emberdark.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yamiat.emberdark.entity.ModEntities;

public class EntitySpawnerBlockEntity extends BlockEntity {
    private int spawnDelay = 100; // Spawns every 100 ticks (5 seconds)

    public EntitySpawnerBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.DEATHANT_NEST_BE.get(), pPos, pState);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;

        // Decrease delay or use a counter
        if (--spawnDelay <= 0) {
            spawnDelay = 1000; // Reset timer

            // Spawn your modded entity
            // Replace 'ModEntityTypes.CUSTOM_MOB.get()' with your registry object
            Entity entity = ModEntities.DEATHANT.get().create(level);
            if (entity != null) {
                // Position entity at the center-top of the spawner block
                entity.moveTo(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, level.random.nextFloat() * 360F, 0.0F);

                // Add the entity to the world
                level.addFreshEntity(entity);
            }
        }
    }
}