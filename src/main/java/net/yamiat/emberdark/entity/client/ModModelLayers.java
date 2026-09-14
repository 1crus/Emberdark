package net.yamiat.emberdark.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.yamiat.emberdark.Emberdark;

public class ModModelLayers {
    public static final ModelLayerLocation NIGHTMAW_LAYER = new ModelLayerLocation(
            new ResourceLocation(Emberdark.MODID, "nightmaw_layer"), "main");
    public static final ModelLayerLocation DEATHANT_LAYER = new ModelLayerLocation(
            new ResourceLocation(Emberdark.MODID, "deathant_layer"), "main");
    public static final ModelLayerLocation AVIAR_LAYER = new ModelLayerLocation(
            new ResourceLocation(Emberdark.MODID, "aviar_layer"), "main");
}
