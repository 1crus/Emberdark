package net.yamiat.emberdark.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.entity.custom.AviarEntity;
import net.yamiat.emberdark.entity.custom.DeathAntEntity;
import net.yamiat.emberdark.entity.custom.NightmawEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Emberdark.MODID);

    public static final RegistryObject<EntityType<NightmawEntity>> NIGHTMAW = ENTITY_TYPES.register("nightmaw",
            () -> EntityType.Builder.<NightmawEntity>of(NightmawEntity::new, MobCategory.MONSTER)
                    .sized(2.5f, 2.0f)
                    .build("nightmaw")
    );

    public static final RegistryObject<EntityType<DeathAntEntity>> DEATHANT = ENTITY_TYPES.register("deathant",
            () -> EntityType.Builder.<DeathAntEntity>of(DeathAntEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)
                    .build("deathant")
    );

    public static final RegistryObject<EntityType<AviarEntity>> AVIAR = ENTITY_TYPES.register("aviar",
            () -> EntityType.Builder.<AviarEntity>of(AviarEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)
                    .build("aviar")
    );




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}