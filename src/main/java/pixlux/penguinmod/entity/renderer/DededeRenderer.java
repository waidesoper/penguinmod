package pixlux.penguinmod.entity.renderer;

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

import pixlux.penguinmod.entity.DededeEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class DededeRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			EntityRenderers.register(DededeEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modeldedede_penguin(), 0.3f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("penguinmod:textures/dedede_penguin.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.0.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modeldedede_penguin extends EntityModel<Entity> {
		private final ModelPart body;
		private final ModelPart head;
		private final ModelPart leftwing;
		private final ModelPart rightwing;
		private final ModelPart leftleg;
		private final ModelPart rightleg;

		public Modeldedede_penguin() {
			textureWidth = 32;
			textureHeight = 32;
			body = new ModelPart(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.texOffs(0, 9).addBox(-3.0F, -8.0F, -2.0F, 6.0F, 3.0F, 4.0F, 0.0F, false);
			body.texOffs(0, 26).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);
			body.texOffs(0, 6).addBox(-3.0F, -5.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);
			body.texOffs(0, 16).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			body.texOffs(18, 0).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);
			body.texOffs(28, 12).addBox(-2.0F, -7.0F, -2.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			body.texOffs(21, 10).addBox(-2.5F, -3.0F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			body.texOffs(0, 0).addBox(1.5F, -8.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			body.texOffs(0, 0).addBox(-2.5F, -8.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			body.texOffs(0, 22).addBox(-3.0F, -8.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			body.texOffs(0, 20).addBox(2.0F, -8.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			body.texOffs(20, 4).addBox(1.5F, -3.0F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			body.texOffs(28, 15).addBox(1.0F, -7.0F, -2.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			head = new ModelPart(this);
			head.setRotationPoint(0.0F, 16.0F, 1.0F);
			head.texOffs(2, 1).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.texOffs(2, 3).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.texOffs(1, 8).addBox(-1.0F, -4.5F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			head.texOffs(19, 22).addBox(-2.0F, -4.5F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			head.texOffs(19, 22).addBox(1.0F, -4.5F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			head.texOffs(5, 19).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			head.texOffs(12, 22).addBox(-0.5F, -6.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			head.texOffs(7, 19).addBox(-1.5F, -5.5F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			head.texOffs(23, 15).addBox(0.5F, -5.5F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			head.texOffs(0, 26).addBox(-1.5F, -5.0F, -2.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
			head.texOffs(0, 26).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
			head.texOffs(24, 22).addBox(-1.0F, -5.5F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			leftwing = new ModelPart(this);
			leftwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			leftwing.texOffs(26, 25).addBox(3.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			leftwing.texOffs(15, 22).addBox(3.5F, -4.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			leftwing.texOffs(17, 27).addBox(-4.5F, -4.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			leftwing.texOffs(25, 9).addBox(3.0F, -4.0F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			leftwing.texOffs(18, 18).addBox(3.0F, -4.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			leftwing.texOffs(17, 2).addBox(-4.0F, -4.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			leftwing.texOffs(12, 18).addBox(-4.0F, -4.0F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			rightwing = new ModelPart(this);
			rightwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			rightwing.texOffs(26, 25).addBox(-4.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			leftleg = new ModelPart(this);
			leftleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			leftleg.texOffs(1, 0).addBox(1.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F, false);
			leftleg.texOffs(5, 0).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			rightleg = new ModelPart(this);
			rightleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			rightleg.texOffs(1, 0).addBox(-2.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F, false);
			rightleg.texOffs(5, 0).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			leftwing.render(matrixStack, buffer, packedLight, packedOverlay);
			rightwing.render(matrixStack, buffer, packedLight, packedOverlay);
			leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
			rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

		}
	}

}
