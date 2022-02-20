package pixlux.penguinmod.entity.renderer;

import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import pixlux.penguinmod.item.HarpoonItem;

@OnlyIn(Dist.CLIENT)
public class HarpoonRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			EntityRenderers.register(HarpoonItem.arrow, ItemEntityRenderer::new);
		}
	}
}
