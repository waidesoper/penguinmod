 package pixlux.penguinmod.procedures;
 
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.server.level.ServerPlayer;
 import net.minecraft.world.entity.Entity;
 import net.minecraft.world.entity.player.Player;
 import net.minecraft.world.level.Level;
 import pixlux.penguinmod.PenguinmodMod;
 import net.minecraft.advancements.Advancement;
 import net.minecraft.advancements.AdvancementProgress;
 import net.minecraftforge.event.entity.player.PlayerInteractEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
 
 
 
 public class PenguinRightClickedOnEntityProcedure
 {
   @EventBusSubscriber
   private static class GlobalTrigger
   {
     @SubscribeEvent
     public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
       Entity entity = event.getTarget();
       Player sourceentity = event.getPlayer();
       if (event.getHand() != sourceentity.getUsedItemHand()) {
         return;
       }
       double i = event.getPos().getX();
       double j = event.getPos().getY();
       double k = event.getPos().getZ();
       Level world = event.getWorld();
       Map<String, Object> dependencies = new HashMap<>();
       dependencies.put("x", Double.valueOf(i));
       dependencies.put("y", Double.valueOf(j));
       dependencies.put("z", Double.valueOf(k));
       dependencies.put("world", world);
       dependencies.put("entity", entity);
       dependencies.put("sourceentity", sourceentity);
       dependencies.put("event", event);
       PenguinRightClickedOnEntityProcedure.executeProcedure(dependencies);
     } }
   
   public static void executeProcedure(Map<String, Object> dependencies) {
     if (dependencies.get("entity") == null) {
       if (!dependencies.containsKey("entity"))
         PenguinmodMod.LOGGER.warn("Failed to load dependency entity for procedure PenguinRightClickedOnEntity!"); 
       return;
     } 
     Entity entity = (Entity)dependencies.get("entity");
     if (entity instanceof ServerPlayer) {
       
       Advancement _adv = entity.createCommandSourceStack().getAdvancement(new ResourceLocation("penguinmod:petthepenguin"));
       AdvancementProgress _ap = ((ServerPlayer)entity).getAdvancements().getOrStartProgress(_adv);
       if (!_ap.isDone()) {
         Iterator<String> _iterator = _ap.getRemainingCriteria().iterator();
         while (_iterator.hasNext()) {
           String _criterion = _iterator.next();
           ((ServerPlayer)entity).getAdvancements().award(_adv, _criterion);
         } 
       } 
     } 
   }
 }

