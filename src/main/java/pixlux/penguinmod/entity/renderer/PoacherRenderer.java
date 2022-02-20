package pixlux.penguinmod.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelPart;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import pixlux.penguinmod.entity.PoacherEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class PoacherRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			EntityRenderers.register(PoacherEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelpoacher(), 0.6f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("penguinmod:textures/poacher.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.0.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelpoacher extends EntityModel<Entity> {
		private final ModelPart head;
		private final ModelPart hat;
		private final ModelPart nose;
		private final ModelPart body;
		private final ModelPart left_arm;
		private final ModelPart right_arm;
		private final ModelPart left_leg;
		private final ModelPart right_leg;

		public Modelpoacher() {
			textureWidth = 64;
			textureHeight = 64;
			head = new ModelPart(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
			head.texOffs(54, 15).addBox(-5.0F, -8.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
			head.texOffs(54, 16).addBox(4.0F, -8.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
			head.texOffs(58, 0).addBox(-5.0F, -12.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head.texOffs(44, 0).addBox(-4.0F, -12.0F, -1.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);
			head.texOffs(58, 0).addBox(4.0F, -12.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			hat = new ModelPart(this);
			hat.setRotationPoint(0.0F, 24.0F, 0.0F);
			nose = new ModelPart(this);
			nose.setRotationPoint(0.0F, -2.0F, 0.0F);
			nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			body = new ModelPart(this);
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
			body.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);
			left_arm = new ModelPart(this);
			left_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
			left_arm.texOffs(40, 46).addBox(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
			right_arm = new ModelPart(this);
			right_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
			right_arm.texOffs(40, 46).addBox(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			left_leg = new ModelPart(this);
			left_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
			left_leg.texOffs(0, 22).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
			right_leg = new ModelPart(this);
			right_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
			right_leg.texOffs(0, 22).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			hat.render(matrixStack, buffer, packedLight, packedOverlay);
			nose.render(matrixStack, buffer, packedLight, packedOverlay);
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}

}
