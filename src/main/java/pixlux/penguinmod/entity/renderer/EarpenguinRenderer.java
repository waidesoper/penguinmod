package pixlux.penguinmod.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import pixlux.penguinmod.entity.EarpenguinEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class EarpenguinRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(EarpenguinEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelfixedbrand_new_earpenguin(), 0.3f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("penguinmod:textures/fixedbrand_new_earpenguin.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.0.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelfixedbrand_new_earpenguin extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer leftwing;
		private final ModelRenderer rightwing;
		private final ModelRenderer leftleg;
		private final ModelRenderer rightleg;

		public Modelfixedbrand_new_earpenguin() {
			textureWidth = 32;
			textureHeight = 32;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(0, 16).addBox(-3.0F, -8.0F, -2.0F, 6.0F, 3.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 24).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 24).addBox(-3.0F, -5.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(10, 0).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			body.setTextureOffset(26, 13).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 16.0F, 1.0F);
			head.setTextureOffset(2, 1).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.setTextureOffset(18, 27).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.setTextureOffset(6, 0).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(10, 2).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(21, 15).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(23, 21).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(26, 9).addBox(-2.0F, -6.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(23, 3).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 0.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(23, 6).addBox(2.0F, -6.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
			leftwing = new ModelRenderer(this);
			leftwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			leftwing.setTextureOffset(10, 5).addBox(3.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			rightwing = new ModelRenderer(this);
			rightwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			rightwing.setTextureOffset(10, 5).addBox(-4.0F, -7.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			leftleg = new ModelRenderer(this);
			leftleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			leftleg.setTextureOffset(0, 0).addBox(1.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F, false);
			leftleg.setTextureOffset(5, 0).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
			rightleg = new ModelRenderer(this);
			rightleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			rightleg.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F, false);
			rightleg.setTextureOffset(5, 0).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
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

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

		}
	}

}