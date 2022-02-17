 package pixlux.penguinmod.entity;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.function.Supplier;

 import net.minecraft.core.BlockPos;
 import net.minecraft.network.protocol.Packet;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.server.level.ServerLevel;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.InteractionHand;
 import net.minecraft.world.InteractionResult;
 import net.minecraft.world.damagesource.DamageSource;
 import net.minecraft.world.entity.*;
 import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
 import net.minecraft.world.entity.ai.attributes.Attributes;
 import net.minecraft.world.entity.ai.goal.*;
 import net.minecraft.world.entity.monster.Monster;
 import net.minecraft.world.entity.player.Player;
 import net.minecraft.world.item.Item;
 import net.minecraft.world.item.ItemStack;
 import net.minecraft.world.item.Items;
 import net.minecraft.world.item.SpawnEggItem;
 import net.minecraft.world.level.Level;
 import net.minecraft.world.level.biome.MobSpawnSettings;
 import net.minecraft.world.level.block.state.BlockState;
 import net.minecraft.world.level.levelgen.Heightmap;
 import net.minecraftforge.common.ForgeSpawnEggItem;
 import net.minecraftforge.event.ForgeEventFactory;
 import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
 import net.minecraftforge.network.NetworkHooks;
 import net.minecraftforge.network.PlayMessages;
 import pixlux.penguinmod.PenguinmodModElements;
 import pixlux.penguinmod.PenguinmodModElements.ModElement.Tag;
 import pixlux.penguinmod.entity.renderer.YelloweyepenguinRenderer;
 import pixlux.penguinmod.item.PenguinfeatherItem;
 import pixlux.penguinmod.itemgroup.AmazingpenguinsItemGroup;
 import pixlux.penguinmod.procedures.PenguinRightClickedOnEntityProcedure;
 import net.minecraftforge.common.MinecraftForge;
 import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
 import net.minecraftforge.event.world.BiomeLoadingEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
 import net.minecraftforge.registries.ForgeRegistries;
 
 @Tag
 public class YelloweyepenguinEntity extends PenguinmodModElements.ModElement {
   public static EntityType entity = (EntityType)EntityType.Builder.<CustomEntity>of(CustomEntity::new, MobCategory.AMBIENT)
     .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).sized(0.6F, 1.0F)
     .build("yelloweyepenguin").setRegistryName("yelloweyepenguin");

   public YelloweyepenguinEntity(PenguinmodModElements instance) {
     super(instance, 25);
     FMLJavaModLoadingContext.get().getModEventBus().register(new YelloweyepenguinRenderer.ModelRegisterHandler());
     FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
     MinecraftForge.EVENT_BUS.register(this);
   }
 
   @Override
   public void initElements() {
     this.elements.entities.add(() -> entity);
     this.elements.items.add(() -> (Item)(new ForgeSpawnEggItem((Supplier<? extends EntityType<? extends Mob>>) entity, -16777216, -205, (new Item.Properties()).tab(AmazingpenguinsItemGroup.tab))).setRegistryName("yelloweyepenguin_spawn_egg"));
   }
 
   
   @SubscribeEvent
   public void addFeatureToBiomes(BiomeLoadingEvent event) {
     boolean biomeCriteria = false;
     if ((new ResourceLocation("snowy_tundra")).equals(event.getName()))
       biomeCriteria = true; 
     if (!biomeCriteria)
       return; 
     event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(entity, 25, 4, 7));
   }
 
   
   public void init(FMLCommonSetupEvent event) {
     SpawnPlacements.register(entity, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
   }
   private static class EntityAttributesRegisterHandler { private EntityAttributesRegisterHandler() {}
     
     @SubscribeEvent
     public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
         AttributeSupplier.Builder ammma = Mob.createMobAttributes();
       ammma = ammma.add(Attributes.MOVEMENT_SPEED, 0.3D);
       ammma = ammma.add(Attributes.MAX_HEALTH, 10.0D);
       ammma = ammma.add(Attributes.ARMOR, 0.0D);
       ammma = ammma.add(Attributes.ATTACK_DAMAGE, 0.0D);
       event.put(YelloweyepenguinEntity.entity, ammma.build());
     } }
 
   
   public static class CustomEntity extends TamableAnimal {
     public CustomEntity(PlayMessages.SpawnEntity packet, Level world) {
       this(YelloweyepenguinEntity.entity, world);
     }
     
     public CustomEntity(EntityType<CustomEntity> type, Level world) {
       super(type, world);
       this.xpReward = 2;
       setNoAi(false);
     }

     @Override
     public Packet<?> getAddEntityPacket() {
       return NetworkHooks.getEntitySpawningPacket((Entity)this);
     }
 
     @Override
     protected void registerGoals() {
       super.registerGoals();
       this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
       this.goalSelector.addGoal(2, new FollowParentGoal(this, 0.8D));
       this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
       this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
       this.goalSelector.addGoal(5, new RandomSwimmingGoal(this,1.0D, 1));
       this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
       this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
       this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
       this.goalSelector.addGoal(9, new RandomSwimmingGoal(this, 1.0D, 1));
     }
 

     public AttributeSupplier.Builder createAttributes() {
       return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
     }
     
     protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
       super.dropCustomDeathLoot(source, looting, recentlyHitIn);
       this.spawnAtLocation(new ItemStack(PenguinfeatherItem.block));
     }
 
     
     public SoundEvent getAmbientSound() {
       return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("penguinmod:quiet"));
     }
 
     
     public void playStepSound(BlockPos pos, BlockState blockIn) {
       this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.step")), 0.15F, 1.0F);
     }
 
 
     
     public SoundEvent getHurtSound(DamageSource ds) {
       return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.hurt"));
     }
 
     
     public SoundEvent getDeathSound() {
       return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.death"));
     }
 
     
     public boolean hurt(DamageSource source, float amount) {
       if (source == DamageSource.FALL)
         return false;
       if (source == DamageSource.DROWN)
           return false;
       if (source == DamageSource.FREEZE)
         return false; 
       return super.hurt(source, amount);
     }
 
     
     public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
       ItemStack itemstack = sourceentity.getItemInHand(hand);
       InteractionResult retval = InteractionResult.sidedSuccess(!this.level.isClientSide());
       Item item = itemstack.getItem();
       if (itemstack.getItem() instanceof SpawnEggItem) {
         retval = super.mobInteract(sourceentity, hand);
       } else if (!this.level.isClientSide()) {
         
         retval = ((this.isTame() && this.isOwnedBy(sourceentity)) || this.isBreedingItem(itemstack)) ? InteractionResult.sidedSuccess(!this.level.isClientSide()) : InteractionResult.PASS;
       
       }
       else if (this.isTame()) {
         if (this.isOwnedBy(sourceentity)) {
           if (item.isEdible() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
             this.usePlayerItem(sourceentity, hand, itemstack);
             this.heal(item.getFoodProperties().getNutrition());
             retval = InteractionResult.sidedSuccess(!this.level.isClientSide());
           } else if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
             this.usePlayerItem(sourceentity, hand, itemstack);
             this.heal(4.0F);
             retval = InteractionResult.sidedSuccess(!this.level.isClientSide());
           } else {
             retval = super.mobInteract(sourceentity, hand);
           } 
         }
       } else if (this.isEdible(itemstack)) {
         this.usePlayerItem(sourceentity, hand, itemstack);
         if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, sourceentity)) {
           this.setOwnerUUID(sourceentity.getUUID());
           this.level.broadcastEntityEvent(this, (byte)7);
         } else {
           this.level.broadcastEntityEvent(this, (byte)6);
         } 
         this.setPersistenceRequired();
         retval = InteractionResult.sidedSuccess(!this.level.isClientSide());
       } else {
         retval = super.mobInteract(sourceentity, hand);
         if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
           this.setPersistenceRequired();
         }
       } 
       double x = this.getX();
       double y = this.getY();
       double z = this.getZ();
       CustomEntity customEntity = this;
       
       Map<String, Object> $_dependencies = new HashMap<>();
       $_dependencies.put("entity", customEntity);
       PenguinRightClickedOnEntityProcedure.executeProcedure($_dependencies);
       
       return retval;
     }
 
     
     public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageable) {
       CustomEntity retval = (CustomEntity) entity.create( serverLevel);
       retval.spawnChildFromBreeding(serverLevel, retval);
       
       return (AgeableMob)retval;
     }
 
     
     public boolean isBreedingItem(ItemStack stack) {
       if (stack == null)
         return false; 
       if (Items.COD == stack.getItem())
         return true; 
       if (Items.SALMON == stack.getItem())
         return true; 
       return false;
     }
   }
 }


/* Location:              /home/waide/Downloads/Amazing+Penguins+1.16.5.jar!/pixlux/penguinmod/entity/YelloweyepenguinEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
