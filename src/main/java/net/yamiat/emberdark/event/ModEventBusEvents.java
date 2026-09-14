package net.yamiat.emberdark.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.entity.ModEntities;
import net.yamiat.emberdark.entity.custom.AviarEntity;
import net.yamiat.emberdark.entity.custom.DeathAntEntity;
import net.yamiat.emberdark.entity.custom.NightmawEntity;

@Mod.EventBusSubscriber(modid = Emberdark.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NIGHTMAW.get(), NightmawEntity.createAttributes().build());
        event.put(ModEntities.DEATHANT.get(), DeathAntEntity.createAttributes().build());
        event.put(ModEntities.AVIAR.get(), AviarEntity.createAttributes().build());
    }
}
