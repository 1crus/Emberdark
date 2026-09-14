package net.yamiat.emberdark.entity.client;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class AviarModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart main;
	private final ModelPart chest;
	private final ModelPart head;
	private final ModelPart leftwing;
	private final ModelPart leftleg;
	private final ModelPart rightwing;
	private final ModelPart rightleg;
	private final ModelPart tailfeathers;

	public AviarModel(ModelPart root) {
		this.main = root.getChild("main");
		this.chest = this.main.getChild("chest");
		this.head = this.chest.getChild("head");
		this.leftwing = this.chest.getChild("leftwing");
		this.leftleg = this.chest.getChild("leftleg");
		this.rightwing = this.chest.getChild("rightwing");
		this.rightleg = this.chest.getChild("rightleg");
		this.tailfeathers = this.chest.getChild("tailfeathers");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition chest = main.addOrReplaceChild("chest", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = chest.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -1.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition head = chest.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 12).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 26).addBox(0.0F, -7.0F, -2.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 38).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 38).addBox(-0.5F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, -1.7F));

		PartDefinition leftwing = chest.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(-2.0F, -10.0F, -2.0F));

		PartDefinition cube_r2 = leftwing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 0.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(30, 26).addBox(-1.5F, -2.0F, -2.0F, 1.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition leftleg = chest.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(12, 33).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(-1.0F, 4.0F, -2.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -3.0F, 0.0F));

		PartDefinition rightwing = chest.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(2.0F, -10.0F, -2.0F));

		PartDefinition cube_r3 = rightwing.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 0).addBox(1.0F, -2.0F, -2.0F, 0.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(0.5F, -2.0F, -2.0F, 1.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition rightleg = chest.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(8, 36).addBox(0.0F, -2.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 38).addBox(0.0F, 4.0F, -2.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 0.0F));

		PartDefinition tailfeathers = chest.addOrReplaceChild("tailfeathers", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 3.0F));

		PartDefinition cube_r4 = tailfeathers.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(18, 20).addBox(-3.0F, -2.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -1.1F, -1.0036F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}




	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {


	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return null;
	}
}