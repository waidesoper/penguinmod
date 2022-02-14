 package pixlux.penguinmod;
 
 import java.util.function.Supplier;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.entity.EntityType;
 import net.minecraft.world.item.Item;
 import net.minecraft.world.item.enchantment.Enchantment;
 import net.minecraft.world.level.block.Block;
 import net.minecraftforge.common.MinecraftForge;
 import net.minecraftforge.event.RegistryEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
 import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
 import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
 import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
 import net.minecraftforge.network.NetworkRegistry;
 import net.minecraftforge.network.simple.SimpleChannel;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

 @Mod("penguinmod")
 public class PenguinmodMod
 {
   public static final Logger LOGGER = LogManager.getLogger(PenguinmodMod.class);
   private static final String PROTOCOL_VERSION = "1";
   public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("penguinmod", "penguinmod"), () -> "1", "1"::equals, "1"::equals);
   public PenguinmodModElements elements;
   
   public PenguinmodMod() {
     this.elements = new PenguinmodModElements();
     FMLJavaModLoadingContext.get().getModEventBus().register(this);
     FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
     FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientLoad);
     MinecraftForge.EVENT_BUS.register(new PenguinmodModFMLBusEvents(this));
   }
   
   private void init(FMLCommonSetupEvent event) {
     this.elements.getElements().forEach(element -> element.init(event));
   }
   
   public void clientLoad(FMLClientSetupEvent event) {
     this.elements.getElements().forEach(element -> element.clientLoad(event));
   }
   
   @SubscribeEvent
   public void registerBlocks(RegistryEvent.Register<Block> event) {
     event.getRegistry().registerAll(this.elements.getBlocks().stream().map(Supplier::get).toArray(x$0 -> new Block[x$0]));
   }
   
   @SubscribeEvent
   public void registerItems(RegistryEvent.Register<Item> event) {
     event.getRegistry().registerAll(this.elements.getItems().stream().map(Supplier::get).toArray(x$0 -> new Item[x$0]));
   }
   
   @SubscribeEvent
   public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
     event.getRegistry().registerAll(this.elements.getEntities().stream().map(Supplier::get).toArray(x$0 -> new EntityType[x$0]));
   }
   
   @SubscribeEvent
   public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
     event.getRegistry().registerAll(this.elements.getEnchantments().stream().map(Supplier::get).toArray(x$0 -> new Enchantment[x$0]));
   }
   
   @SubscribeEvent
   public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
     this.elements.registerSounds(event);
   }
   
   private static class PenguinmodModFMLBusEvents {
     PenguinmodModFMLBusEvents(PenguinmodMod parent) {
       this.parent = parent;
     }
     private final PenguinmodMod parent;
     @SubscribeEvent
     public void serverLoad(FMLDedicatedServerSetupEvent event) {
       this.parent.elements.getElements().forEach(element -> element.serverLoad(event));
     }
   }
 }

