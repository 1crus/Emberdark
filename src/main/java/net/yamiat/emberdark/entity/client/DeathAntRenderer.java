package net.yamiat.emberdark.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.entity.custom.DeathAntEntity;
import net.yamiat.emberdark.entity.custom.NightmawEntity;

public class DeathAntRenderer extends MobRenderer<DeathAntEntity, DeathAntModel<DeathAntEntity>> {
    public DeathAntRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DeathAntModel<>(pContext.bakeLayer(ModModelLayers.DEATHANT_LAYER)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(DeathAntEntity pEntity) {
        return new ResourceLocation(Emberdark.MODID, "textures/entity/deathant.png");
    }


}
