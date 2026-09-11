package net.yamiat.emberdark.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Emberdark.MODID);

    public static final RegistryObject<BlockEntityType<EntitySpawnerBlockEntity>> DEATHANT_NEST_BE =
            BLOCK_ENTITIES.register("deathant_nest", () ->
                    BlockEntityType.Builder.of(EntitySpawnerBlockEntity::new, ModBlocks.DEATHANTNEST.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}