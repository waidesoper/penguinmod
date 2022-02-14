 package pixlux.penguinmod;
 
 import java.lang.annotation.Retention;
 import java.lang.annotation.RetentionPolicy;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import java.util.function.BiConsumer;
 import java.util.function.Function;
 import java.util.function.Supplier;
 import net.minecraft.network.FriendlyByteBuf;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.entity.EntityType;
 import net.minecraft.world.item.Item;
 import net.minecraft.world.item.enchantment.Enchantment;
 import net.minecraft.world.level.block.Block;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.event.RegistryEvent;
 import net.minecraftforge.fml.ModList;
 import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
 import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
 import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
 import net.minecraftforge.forgespi.language.ModFileScanData;
 import net.minecraftforge.network.NetworkEvent;

 public class PenguinmodModElements
 {
   public final List<ModElement> elements = new ArrayList<>();
   public final List<Supplier<Block>> blocks = new ArrayList<>();
   public final List<Supplier<Item>> items = new ArrayList<>();
   public final List<Supplier<EntityType<?>>> entities = new ArrayList<>();
   public final List<Supplier<Enchantment>> enchantments = new ArrayList<>();
   public static Map<ResourceLocation, SoundEvent> sounds = new HashMap<>();
   private int messageID;

   public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
     for (Map.Entry<ResourceLocation, SoundEvent> sound : sounds.entrySet())
       event.getRegistry().register((sound.getValue()).setRegistryName(sound.getKey()));
   }
   public PenguinmodModElements() { this.messageID = 0; sounds.put(new ResourceLocation("penguinmod", "penguinnoises"), new SoundEvent(new ResourceLocation("penguinmod", "penguinnoises"))); sounds.put(new ResourceLocation("penguinmod", "penguinnoises2.0"), new SoundEvent(new ResourceLocation("penguinmod", "penguinnoises2.0"))); sounds.put(new ResourceLocation("penguinmod", "quiet"), new SoundEvent(new ResourceLocation("penguinmod", "quiet"))); try { ModFileScanData modFileInfo = ModList.get().getModFileById("penguinmod").getFile().getScanResult(); Set<ModFileScanData.AnnotationData> annotations = modFileInfo.getAnnotations(); for (ModFileScanData.AnnotationData annotationData : annotations) { if (annotationData.annotationType().getClassName().equals(ModElement.Tag.class.getName())) { Class<?> clazz = Class.forName(annotationData.annotationType().getClassName()); if (clazz.getSuperclass() == ModElement.class)
             this.elements.add((ModElement) clazz.getConstructor(new Class[] { getClass() }).newInstance(new Object[] { this }));  }  }  }
     catch (Exception e) { e.printStackTrace(); }
      Collections.sort(this.elements); this.elements.forEach(ModElement::initElements); } public <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) { PenguinmodMod.PACKET_HANDLER.registerMessage(this.messageID, messageType, encoder, decoder, messageConsumer);
     this.messageID++; }
 
   
   public List<ModElement> getElements() {
     return this.elements;
   }
   
   public List<Supplier<Block>> getBlocks() {
     return this.blocks;
   }
   
   public List<Supplier<Item>> getItems() {
     return this.items;
   }
   
   public List<Supplier<EntityType<?>>> getEntities() {
     return this.entities;
   }
   
   public List<Supplier<Enchantment>> getEnchantments() {
     return this.enchantments;
   }
   
   public static class ModElement
     implements Comparable<ModElement> {
     protected final PenguinmodModElements elements;
     protected final int sortid;
     
     public ModElement(PenguinmodModElements elements, int sortid) {
       this.elements = elements;
       this.sortid = sortid;
     }
 
     
     public void initElements() {}
 
     
     public void init(FMLCommonSetupEvent event) {}
 
     
     public void serverLoad(FMLDedicatedServerSetupEvent event) {}
 
     
     @OnlyIn(Dist.CLIENT)
     public void clientLoad(FMLClientSetupEvent event) {}
 
     
     public int compareTo(ModElement other) {
       return this.sortid - other.sortid;
     }
     
     @Retention(RetentionPolicy.RUNTIME)
     public static @interface Tag {}
   }
   
   @Retention(RetentionPolicy.RUNTIME)
   public static @interface Tag {}
 }