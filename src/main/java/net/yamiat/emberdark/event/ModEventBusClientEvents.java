package net.yamiat.emberdark.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.entity.ModEntities;
import net.yamiat.emberdark.entity.client.*;

@Mod.EventBusSubscriber(modid = Emberdark.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.NIGHTMAW_LAYER, NightmawModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DEATHANT_LAYER, DeathAntModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.NIGHTMAW.get(), NightmawRenderer::new);
        event.registerEntityRenderer(ModEntities.DEATHANT.get(), DeathAntRenderer::new);
    }

}
