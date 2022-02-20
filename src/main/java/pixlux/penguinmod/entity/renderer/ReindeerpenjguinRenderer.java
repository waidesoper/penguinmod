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

import pixlux.penguinmod.entity.ReindeerpenjguinEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ReindeerpenjguinRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			EntityRenderers.register(ReindeerpenjguinEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelfixedbrand_new_reindeerpenguin(), 0.3f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("penguinmod:textures/fixedbrand_new_reindeerpenguin.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.0.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelfixedbrand_new_reindeerpenguin extends EntityModel<Entity> {
		private final ModelPart body;
		private final ModelPart head;
		private final ModelPart leftwing;
		private final ModelPart rightwing;
		private final ModelPart leftleg;
		private final ModelPart rightleg;

		public Modelfixedbrand_new_reindeerpenguin() {
			textureWidth = 32;
			textureHeight = 32;
			body = new ModelPart(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.texOffs(0, 9).addBox(-3.0F, -8.0F, -2.0F, 6.0F, 3.0F, 4.0F, 0.0F, false);
			body.texOffs(0, 23).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);
			body.texOffs(0, 6).addBox(-3.0F, -5.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);
			body.texOffs(10, 0).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			body.texOffs(25, 24).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);
			head = new ModelPart(this);
			head.setRotationPoint(0.0F, 16.0F, 1.0F);
			head.texOffs(2, 1).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.texOffs(2, 3).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.texOffs(6, 0).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
			head.texOffs(10, 2).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			head.texOffs(23, 9).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			head.texOffs(19, 17).addBox(3.0F, -10.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(29, 16).addBox(1.0F, -10.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(28, 15).addBox(-2.0F, -10.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(27, 17).addBox(-4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(18, 17).addBox(-5.0F, -8.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(22, 19).addBox(3.0F, -8.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(30, 14).addBox(2.0F, -9.0F, 0.0F, 1.0F, 3.0F, 0.0F, 0.0F, false);
			head.texOffs(18, 18).addBox(-3.0F, -9.0F, 0.0F, 1.0F, 3.0F, 0.0F, 0.0F, false);
			head.texOffs(17, 18).addBox(1.0F, -6.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(18, 19).addBox(-2.0F, -6.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			head.texOffs(22, 2).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			leftwing = new ModelPart(this);
			leftwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			leftwing.texOffs(10, 5).addBox(3.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			rightwing = new ModelPart(this);
			rightwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			rightwing.texOffs(10, 6).addBox(-4.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			leftleg = new ModelPart(this);
			leftleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			leftleg.texOffs(0, 0).addBox(1.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F, false);
			leftleg.texOffs(5, 0).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			rightleg = new ModelPart(this);
			rightleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			rightleg.texOffs(0, 0).addBox(-2.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F, false);
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
