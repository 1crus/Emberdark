package net.yamiat.emberdark.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.entity.custom.NightmawEntity;

public class NightmawRenderer extends MobRenderer<NightmawEntity, NightmawModel<NightmawEntity>> {
    public NightmawRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NightmawModel<>(pContext.bakeLayer(ModModelLayers.NIGHTMAW_LAYER)), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(NightmawEntity pEntity) {
        return new ResourceLocation(Emberdark.MODID, "textures/entity/nightmaw.png");
    }


}
