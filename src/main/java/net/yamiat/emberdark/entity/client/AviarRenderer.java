package net.yamiat.emberdark.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.entity.custom.AviarEntity;

import java.util.UUID;

public class AviarRenderer extends MobRenderer<AviarEntity, AviarModel<AviarEntity>> {
    public AviarRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AviarModel<>(pContext.bakeLayer(ModModelLayers.AVIAR_LAYER)), 0.2F);
    }



    @Override
    public ResourceLocation getTextureLocation(AviarEntity pEntity) {
        return new ResourceLocation(Emberdark.MODID, "textures/entity/aviarcogconceal.png");
    }


}
