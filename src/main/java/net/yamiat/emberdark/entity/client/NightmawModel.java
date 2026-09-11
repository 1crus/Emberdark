package net.yamiat.emberdark.entity.client;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.yamiat.emberdark.entity.animations.ModAnimationDefinitions;
import net.yamiat.emberdark.entity.custom.NightmawEntity;

public class NightmawModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart Nightmaw;
	private final ModelPart Body;
	private final ModelPart Neck;
	private final ModelPart Head;
	private final ModelPart LowerBeak;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightThigh;
	private final ModelPart RightCalve;
	private final ModelPart LeftThigh;
	private final ModelPart LeftCalve;

	public NightmawModel(ModelPart root) {
		this.Nightmaw = root.getChild("Nightmaw");
		this.Body = this.Nightmaw.getChild("Body");
		this.Neck = this.Body.getChild("Neck");
		this.Head = this.Neck.getChild("Head");
		this.LowerBeak = this.Head.getChild("LowerBeak");
		this.RightArm = this.Body.getChild("RightArm");
		this.LeftArm = this.Body.getChild("LeftArm");
		this.RightThigh = this.Nightmaw.getChild("RightThigh");
		this.RightCalve = this.RightThigh.getChild("RightCalve");
		this.LeftThigh = this.Nightmaw.getChild("LeftThigh");
		this.LeftCalve = this.LeftThigh.getChild("LeftCalve");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Nightmaw = partdefinition.addOrReplaceChild("Nightmaw", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition Body = Nightmaw.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -7.0F, -1.0F, 14.0F, 16.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-6.0F, -9.0F, -14.0F, 12.0F, 14.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -25.0F, 0.0F));

		PartDefinition Neck = Body.addOrReplaceChild("Neck", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, -14.0F));

		PartDefinition cube_r1 = Neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 75).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(60, 35).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -7.0F));

		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(72, 85).addBox(-1.0F, -6.0F, -5.0F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(98, 9).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8F, -11.1F, 0.5863F, 0.7161F, 0.4043F));

		PartDefinition cube_r4 = Head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(92, 53).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -7.0F, 0.1729F, 0.7741F, 0.1217F));

		PartDefinition cube_r5 = Head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(90, 22).addBox(-2.0F, -2.0F, -4.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 0.0F, -6.8F, 0.0173F, -0.3614F, 0.4215F));

		PartDefinition cube_r6 = Head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(28, 87).addBox(1.0F, -2.0F, -4.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 0.0F, -6.8F, 0.0173F, 0.3614F, -0.4215F));

		PartDefinition LowerBeak = Head.addOrReplaceChild("LowerBeak", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -5.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r7 = LowerBeak.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(92, 59).addBox(1.0F, 0.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 0.0F, -1.8F, -0.0173F, 0.3614F, 0.4215F));

		PartDefinition cube_r8 = LowerBeak.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(98, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.8F, -6.1F, -0.5863F, 0.7161F, -0.4043F));

		PartDefinition cube_r9 = LowerBeak.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(92, 85).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.1729F, 0.7741F, -0.1217F));

		PartDefinition cube_r10 = LowerBeak.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(92, 67).addBox(-2.0F, 0.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 0.0F, -1.8F, -0.0173F, -0.3614F, -0.4215F));

		PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(54, 100).addBox(-1.0F, -2.0F, -8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(92, 101).addBox(2.0F, -2.0F, -8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 0.0F, -10.0F));

		PartDefinition cube_r11 = RightArm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 102).addBox(0.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(100, 101).addBox(-2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -8.3F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r12 = RightArm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(98, 0).addBox(0.0F, -2.0F, -1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r13 = RightArm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(42, 93).addBox(0.0F, -2.0F, -1.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(98, 17).addBox(-1.0F, -2.0F, -8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(76, 99).addBox(-4.0F, -2.0F, -8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 0.0F, -10.0F));

		PartDefinition cube_r14 = LeftArm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(84, 99).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(68, 99).addBox(1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -8.3F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r15 = LeftArm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(96, 75).addBox(-3.0F, -2.0F, -1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r16 = LeftArm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(92, 91).addBox(-3.0F, -2.0F, -1.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition RightThigh = Nightmaw.addOrReplaceChild("RightThigh", CubeListBuilder.create().texOffs(66, 0).addBox(0.0F, -2.0F, -7.0F, 6.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -25.0F, 10.0F));

		PartDefinition cube_r17 = RightThigh.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(24, 67).addBox(1.0F, -2.0F, -6.0F, 4.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -1.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition RightCalve = RightThigh.addOrReplaceChild("RightCalve", CubeListBuilder.create().texOffs(14, 87).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 75).addBox(-3.0F, 9.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(96, 45).addBox(-1.0F, 11.0F, -7.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 15.0F, -6.0F));

		PartDefinition cube_r18 = RightCalve.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(96, 38).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 13.0F, -3.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r19 = RightCalve.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(96, 31).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 13.0F, -3.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition LeftThigh = Nightmaw.addOrReplaceChild("LeftThigh", CubeListBuilder.create().texOffs(60, 53).addBox(-6.0F, -2.0F, -7.0F, 6.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -25.0F, 10.0F));

		PartDefinition cube_r20 = LeftThigh.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 67).addBox(-5.0F, -2.0F, -6.0F, 4.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -1.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition LeftCalve = LeftThigh.addOrReplaceChild("LeftCalve", CubeListBuilder.create().texOffs(0, 87).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(66, 22).addBox(-3.0F, 9.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(48, 67).addBox(-1.0F, 11.0F, -7.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 15.0F, -6.0F));

		PartDefinition cube_r21 = LeftCalve.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(28, 96).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 13.0F, -3.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r22 = LeftCalve.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(54, 93).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 13.0F, -3.0F, 0.0F, -0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.run, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((NightmawEntity) entity).idleAnimationState, ModAnimationDefinitions.idle, ageInTicks, 1f);


	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.Head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.Head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Nightmaw.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Nightmaw;
	}
}