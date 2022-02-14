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

import pixlux.penguinmod.entity.MinipenguinEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class MinipenguinRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(MinipenguinEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modellittle_penguin(), 0.3f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("penguinmod:textures/goodlittle_penguin_texture.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.0.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modellittle_penguin extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer leftwing;
		private final ModelRenderer rightwing;
		private final ModelRenderer leftleg;
		private final ModelRenderer rightleg;

		public Modellittle_penguin() {
			textureWidth = 32;
			textureHeight = 32;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(0, 21).addBox(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(0, 27).addBox(-2.5F, -4.0F, -2.0F, 5.0F, 2.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(0, 7).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			body.setTextureOffset(-1, 2).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 16.0F, 1.0F);
			head.setTextureOffset(2, 1).addBox(-2.0F, 2.0F, -3.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.setTextureOffset(18, 10).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
			head.setTextureOffset(1, 8).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(0, 8).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(10, 12).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(8, 0).addBox(-1.0F, 2.0F, -5.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			leftwing = new ModelRenderer(this);
			leftwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			leftwing.setTextureOffset(22, 22).addBox(2.5F, -4.0F, -1.5F, 1.0F, 3.0F, 2.0F, 0.0F, false);
			rightwing = new ModelRenderer(this);
			rightwing.setRotationPoint(0.0F, 24.0F, 0.0F);
			rightwing.setTextureOffset(26, 0).addBox(-3.5F, -4.0F, -1.5F, 1.0F, 3.0F, 2.0F, 0.0F, false);
			leftleg = new ModelRenderer(this);
			leftleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			leftleg.setTextureOffset(1, 0).addBox(1.0F, 0.0F, -4.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
			rightleg = new ModelRenderer(this);
			rightleg.setRotationPoint(0.0F, 24.0F, 1.0F);
			rightleg.setTextureOffset(1, 0).addBox(-2.0F, 0.0F, -4.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
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