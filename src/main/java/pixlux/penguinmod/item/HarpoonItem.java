 package pixlux.penguinmod.item;
 
 import java.util.Random;
 import java.util.function.BiFunction;

 import net.minecraft.network.protocol.Packet;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.server.level.ServerPlayer;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.sounds.SoundSource;
 import net.minecraft.world.InteractionHand;
 import net.minecraft.world.InteractionResult;
 import net.minecraft.world.InteractionResultHolder;
 import net.minecraft.world.entity.Entity;
 import net.minecraft.world.entity.EntityType;
 import net.minecraft.world.entity.LivingEntity;
 import net.minecraft.world.entity.MobCategory;
 import net.minecraft.world.entity.player.Player;
 import net.minecraft.world.entity.projectile.AbstractArrow;
 import net.minecraft.world.item.*;
 import net.minecraft.world.level.Level;
 import net.minecraft.world.phys.EntityHitResult;
 import net.minecraftforge.network.NetworkHooks;
 import net.minecraftforge.network.PlayMessages;
 import pixlux.penguinmod.PenguinmodModElements;
 import pixlux.penguinmod.PenguinmodModElements.ModElement.Tag;
 import pixlux.penguinmod.entity.renderer.HarpoonRenderer;
 import pixlux.penguinmod.itemgroup.AmazingpenguinsItemGroup;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
 import net.minecraftforge.registries.ForgeRegistries;
 import net.minecraftforge.registries.ObjectHolder;
 
 @Tag
 public class HarpoonItem extends PenguinmodModElements.ModElement {
   @ObjectHolder("penguinmod:harpoon")
   public static final Item block = null;
   public static final EntityType arrow = (EntityType)EntityType.Builder.<ArrowCustomEntity>of(ArrowCustomEntity::new, MobCategory.MISC)
     .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
     .sized(0.5F, 0.5F).build("entitybulletharpoon").setRegistryName("entitybulletharpoon");
   public HarpoonItem(PenguinmodModElements instance) {
     super(instance, 29);
     FMLJavaModLoadingContext.get().getModEventBus().register(new HarpoonRenderer.ModelRegisterHandler());
   }
 
   
   public void initElements() {
     this.elements.items.add(() -> new ItemRanged());
     this.elements.entities.add(() -> arrow);
   }
   
   public static class ItemRanged extends Item { public ItemRanged() {
       super((new Item.Properties()).tab(AmazingpenguinsItemGroup.tab).defaultDurability(1500));
       setRegistryName("harpoon");
     }
 
     
     public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
       entity.swing(hand);
       ItemStack item = entity.getUseItem();
       return InteractionResultHolder.success(item);

     }
 
     
     public UseAnim getUseAnimation(ItemStack itemstack) {
       return UseAnim.CROSSBOW;
     }
 
     
     public int getUseDuration(ItemStack itemstack) {
       return 72000;
     }
 
     
     public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
       if (!world.isClientSide && entityLiving instanceof ServerPlayer) {
         ServerPlayer entity = (ServerPlayer)entityLiving;
         double x = entity.getX();
         double y = entity.getY();
         double z = entity.getZ();
         
         HarpoonItem.ArrowCustomEntity entityarrow = HarpoonItem.shoot(world, (LivingEntity)entity, world.getRandom(), 1.0F, 6.0D, 3);
         itemstack.hurtAndBreak(1, (LivingEntity)entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
           ((AbstractArrow)entityarrow).pickup = AbstractArrow.Pickup.DISALLOWED;
       } 
     } }
 
   
   @OnlyIn(value = Dist.CLIENT)
   public static class ArrowCustomEntity
     extends AbstractArrow {
     public ArrowCustomEntity(PlayMessages.SpawnEntity packet, Level world) {
       super(HarpoonItem.arrow, world);
     }
     
     public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, Level world) {
       super(type, world);
     }
     
     public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, Level world) {
       super(type, x, y, z, world);
     }
     
     public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, Level world) {
       super(type, entity, world);
     }


       @Override
     public Packet<?> getAddEntityPacket() {
       return NetworkHooks.getEntitySpawningPacket(this);
     }
 
     @Override
     @OnlyIn(Dist.CLIENT)
     public ItemStack getPickupItem() {
       return new ItemStack(Items.ARROW);
     }
 
     
     protected ItemStack getArrowStack() {
       return null;
     }
 
     @Override
     protected void onHitEntity(EntityHitResult entity) {
       super.onHitEntity(entity);
     }
 
     
     public void tick() {
       super.tick();
       double x = this.getX();
       double y = this.getY();
       double z = this.getZ();
       Level world = this.getLevel();
       Entity entity = this.getOwner();
       ArrowCustomEntity arrowCustomEntity = this;
       if (this.inGround)
         this.kill();
     }
   }
   
   public static ArrowCustomEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
     ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
     entityarrow.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, power * 2.0F, 0.0F);
     entityarrow.setSilent(true);
     entityarrow.setCritArrow(true);
     entityarrow.setBaseDamage(damage);
     entityarrow.setKnockback(knockback);
     world.addFreshEntity(entityarrow);
     double x = entity.getX();
     double y = entity.getY();
     double z = entity.getZ();
     world.playSound((Player)null, x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS
         .getValue(new ResourceLocation("")), SoundSource.PLAYERS, 1.0F, 1.0F / (random
         .nextFloat() * 0.5F + 1.0F) + power / 2.0F);
     return entityarrow;
   }
   
   public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
     ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.getLevel());
     double d0 = target.getY() + target.getEyeHeight() - 1.1D;
     double d1 = target.getX() - entity.getX();
     double d3 = target.getZ() - entity.getZ();
     entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 2.0F, 12.0F);
     entityarrow.setSilent(true);
     entityarrow.setBaseDamage(6.0D);
     entityarrow.setKnockback(3);
     entityarrow.setCritArrow(true);
     entity.level.addFreshEntity(entityarrow);
     double x = entity.getX();
     double y = entity.getY();
     double z = entity.getZ();
     entity.level.playSound((Player)null, x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS
         .getValue(new ResourceLocation("")), SoundSource.PLAYERS, 1.0F, 1.0F / ((new Random())
         .nextFloat() * 0.5F + 1.0F));
     return entityarrow;
   }
 }


