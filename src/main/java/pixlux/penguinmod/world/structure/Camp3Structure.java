 package pixlux.penguinmod.world.structure;
 import com.mojang.serialization.Codec;
 import java.util.Random;
 import net.minecraft.util.Mirror;
 import net.minecraft.util.RegistryKey;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.Rotation;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.registry.Registry;
 import net.minecraft.util.registry.LevelGenRegistries;
 import net.minecraft.world.ISeedReader;
 import net.minecraft.world.IServerLevel;
 import net.minecraft.world.Level;
 import net.minecraft.world.gen.ChunkGenerator;
 import net.minecraft.world.gen.GenerationStage;
 import net.minecraft.world.gen.Heightmap;
 import net.minecraft.world.gen.feature.ConfiguredFeature;
 import net.minecraft.world.gen.feature.Feature;
 import net.minecraft.world.gen.feature.IFeatureConfig;
 import net.minecraft.world.gen.feature.NoFeatureConfig;
 import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
 import net.minecraft.world.gen.feature.template.PlacementSettings;
 import net.minecraft.world.gen.feature.template.StructureProcessor;
 import net.minecraft.world.gen.feature.template.Template;
 import net.minecraft.world.gen.placement.IPlacementConfig;
 import net.minecraft.world.gen.placement.Placement;
 import net.minecraftforge.event.RegistryEvent;
 import net.minecraftforge.event.world.BiomeLoadingEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
 
 @EventBusSubscriber
 public class Camp3Structure {
   private static Feature<NoFeatureConfig> feature = null;
   private static ConfiguredFeature<?, ?> configuredFeature = null;
   
   @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
   private static class FeatureRegisterHandler { @SubscribeEvent
     public static void registerFeature(RegistryEvent.Register<Feature<?>> event) {
       Camp3Structure.feature = new Feature<NoFeatureConfig>(NoFeatureConfig.field_236558_a_)
         {
           public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
             int ci = pos.func_177958_n() >> 4 << 4;
             int ck = pos.func_177952_p() >> 4 << 4;
             RegistryKey<Level> dimensionType = world.func_201672_e().func_234923_W_();
             boolean dimensionCriteria = false;
             if (dimensionType == Level.field_234918_g_)
               dimensionCriteria = true; 
             if (!dimensionCriteria)
               return false; 
             if (random.nextInt(1000000) + 1 <= 500) {
               int count = random.nextInt(1) + 1;
               for (int a = 0; a < count; a++) {
                 int i = ci + random.nextInt(16);
                 int k = ck + random.nextInt(16);
                 int j = world.func_201676_a(Heightmap.Types.OCEAN_FLOOR_WG, i, k);
                 j--;
                 Rotation rotation = Rotation.values()[random.nextInt(3)];
                 Mirror mirror = Mirror.values()[random.nextInt(2)];
                 BlockPos spawnTo = new BlockPos(i + 0, j + 0, k + 0);
                 int x = spawnTo.func_177958_n();
                 int y = spawnTo.func_177956_o();
                 int z = spawnTo.func_177952_p();
                 
                 Template template = world.func_201672_e().func_184163_y().func_200220_a(new ResourceLocation("penguinmod", "camp3"));
                 if (template == null)
                   return false; 
                 template.func_237144_a_((IServerLevel)world, spawnTo, (new PlacementSettings())
                     .func_186220_a(rotation).func_189950_a(random).func_186214_a(mirror)
                     .func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a).func_186218_a(null).func_186222_a(false), random);
               } 
             } 
             
             return true;
           }
         };
       Camp3Structure.configuredFeature = Camp3Structure.feature.func_225566_b_((IFeatureConfig)IFeatureConfig.field_202429_e)
         .func_227228_a_(Placement.field_215022_h.func_227446_a_((IPlacementConfig)IPlacementConfig.field_202468_e));
       event.getRegistry().register(Camp3Structure.feature.setRegistryName("camp_3"));
       Registry.func_218322_a(LevelGenRegistries.field_243653_e, new ResourceLocation("penguinmod:camp_3"), Camp3Structure.configuredFeature);
     } }
   
   @SubscribeEvent
   public static void addFeatureToBiomes(BiomeLoadingEvent event) {
     boolean biomeCriteria = false;
     if ((new ResourceLocation("snowy_tundra")).equals(event.getName()))
       biomeCriteria = true; 
     if (!biomeCriteria)
       return; 
     event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES).add(() -> configuredFeature);
   }
 }


/* Location:              /home/waide/Downloads/Amazing+Penguins+1.16.5.jar!/pixlux/penguinmod/world/structure/Camp3Structure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
