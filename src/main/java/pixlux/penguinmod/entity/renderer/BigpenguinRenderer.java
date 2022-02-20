package pixlux.penguinmod.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelPart;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import pixlux.penguinmod.entity.BigpenguinEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class BigpenguinRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			EntityRenderers.register(BigpenguinEntity.entity, renderManager -> {
				return new EntityRenderer(renderManager, new Modelrockhopper_penguin(), 0.3f) {

					@Override
					public ResourceLocation getTextureLocation(Entity p_114482_) {
						return new ResourceLocation("penguinmod:textures/rockhopper_penguin.png");
					}

				};
			});
		}
	}

	// Made with Blockbench 4.0.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelrockhopper_penguin extends EntityModel<Entity> {
		private final ModelPart body;
		private final ModelPart head;
		private final ModelPart leftwing;
		private final ModelPart rightwing;
		private final ModelPart leftleg;
		private final ModelPart rightleg;

		public Modelrockhopper_penguin() {
			MeshDefinition meshDefinition = new MeshDefinition();
			PartDefinition partDefinition = meshDefinition.getRoot();
			PartDefinition bodyPartDef = partDefinition.addOrReplaceChild("Body", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-3.0F, -8.0F, -2.0F, 3.0F, 3.0F, 4.0F, false)
				.texOffs(0, 25).addBox(0.0F, -8.0F, -2.0F, 3.0F, 3.0F, 4.0F,  false)
				.texOffs(0, 19).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 2.0F, 4.0F,  false)
				.texOffs(12, 26).addBox(-3.0F, -5.0F, -2.0F, 6.0F, 2.0F, 4.0F, false)
				.texOffs(26, 28).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 1.0F, 1.0F,  false)
				.texOffs(-1, 31).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 0.0F, 1.0F,  false),
				PartPose.offset(0.0F, 24.0F, 0.0F));

			PartDefinition headPartDef = partDefinition.addOrReplaceChild("Head", CubeListBuilder.create()
				.texOffs(2, 1).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 3.0F,  false)
				.texOffs(2, 3).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 3.0F,  false)
				.texOffs(6, 10).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 1.0F, 1.0F, false)
				.texOffs(19, 27).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 1.0F, 1.0F, false)
				.texOffs(26, 0).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 1.0F, 1.0F, false)
				.texOffs(23, 12).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 1.0F, 2.0F, false),
				PartPose.offset(0.0F, 16.0F, 1.0F));

			PartDefinition lWingDef = partDefinition.addOrReplaceChild("Leftwing", CubeListBuilder.create()
				.texOffs(26, 17).addBox(3.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F, false),
				PartPose.offset(0.0F, 24.0F, 0.0F));

			PartDefinition rWingDef = partDefinition.addOrReplaceChild("Rightwing", CubeListBuilder.create()
				.texOffs(26, 18).addBox(-4.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F,false),
				PartPose.offset(0.0F, 24.0F, 0.0F));

			PartDefinition lLegDef = partDefinition.addOrReplaceChild("Leftleg", CubeListBuilder.create()
				.texOffs(1, 0).addBox(1.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, false)
				.texOffs(23, 6).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false),
				PartPose.offset(0.0F, 24.0F, 1.0F));

			PartDefinition rLegDef = partDefinition.addOrReplaceChild("Rightleg", CubeListBuilder.create()
				.texOffs(1, 0).addBox(-2.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, false)
				.texOffs(28, 8).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false),
				PartPose.offset(0.0F, 24.0F, 1.0F));

			LayerDefinition layerDefinition = LayerDefinition.create(meshDefinition,32, 32);
			ModelPart root = layerDefinition.bakeRoot();
			this.body = root.getChild("Body");
			this.head = root.getChild("Head");
			this.leftwing = root.getChild("Leftwing");
			this.rightwing = root.getChild("Rightwing");
			this.leftleg = root.getChild("Leftleg");
			this.rightleg = root.getChild("RightLeg");
		}


		@Override
		public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
								   float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			leftwing.render(matrixStack, buffer, packedLight, packedOverlay);
			rightwing.render(matrixStack, buffer, packedLight, packedOverlay);
			leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
			rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
		}


		public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
			modelRenderer.xRot = x;
			modelRenderer.yRot = y;
			modelRenderer.zRot = z;
		}

		@Override
		public void setupAnim(Entity e, float f, float f1, float f2, float f3, float f4) {

		}
	}

}
