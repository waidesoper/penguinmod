
package pixlux.penguinmod.entity;


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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ForgeMod;
import pixlux.penguinmod.itemgroup.AmazingpenguinsItemGroup;
import pixlux.penguinmod.item.GoldenfeathersItem;
import pixlux.penguinmod.entity.renderer.BigpenguinRenderer;
import pixlux.penguinmod.PenguinmodModElements;

import java.util.function.Supplier;

@PenguinmodModElements.ModElement.Tag
public class BigpenguinEntity extends PenguinmodModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>of(CustomEntity::new, MobCategory.CREATURE)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).sized(0.6f, 1f))
					.build("bigpenguin").setRegistryName("bigpenguin");

	public BigpenguinEntity(PenguinmodModElements instance) {
		super(instance, 17);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BigpenguinRenderer.ModelRegisterHandler());
		FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
		elements.items.add(() -> new ForgeSpawnEggItem((Supplier<? extends EntityType<? extends Mob>>) entity, -16777216, -3355648, new Item.Properties().tab(AmazingpenguinsItemGroup.tab))
				.setRegistryName("bigpenguin_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("snowy_mountains").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_tundra").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(entity, 20, 3, 4));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		SpawnPlacements.register(entity, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos.below()).getMaterial() == Material.SNOW && world.getMaxLocalRawBrightness(pos, 0) > 8));
	}

	private static class EntityAttributesRegisterHandler {
		@SubscribeEvent
		public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
			AttributeSupplier.Builder ammma = Mob.createMobAttributes();
			ammma = ammma.add(Attributes.MOVEMENT_SPEED, 0.3);
			ammma = ammma.add(Attributes.MAX_HEALTH, 100);
			ammma = ammma.add(Attributes.ARMOR, 0);
			ammma = ammma.add(Attributes.ATTACK_DAMAGE, 0);
			ammma = ammma.add(ForgeMod.SWIM_SPEED.get(), 0.3);
			event.put(entity, ammma.build());
		}
	}

	public static class CustomEntity extends TamableAnimal {
		public CustomEntity(PlayMessages.SpawnEntity packet, Level world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, Level world) {
			super(type, world);
			xpReward = 3;
			setNoAi(false);
		/*	this.setPathfindingMalus(BlockPathTypes.WATER, 0);
			this.moveControl = new MoveControl(this) {
				@Override
				public void tick() {
					if (CustomEntity.this.isInWater())
						CustomEntity.this.setDeltaMovement(CustomEntity.this.getDeltaMovement().add(0, 0.005, 0));
					if (this.operation == MoveControl.Operation.MOVE_TO && !CustomEntity.this.getNavigation().isStuck()) {
						double dx = this.wantedX - CustomEntity.this.getX();
						double dy = this.wantedY - CustomEntity.this.getY();
						double dz = this.wantedZ - CustomEntity.this.getZ();
						float f = (float) (Mth.atan2(dz, dx) * (double) (180 / Mth.PI)) - 90;
						float f1 = (float) (this.speedModifier * CustomEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
						CustomEntity.this.yBodyRot = this.rotlerp(CustomEntity.this.yBodyRot, f, 10);
						CustomEntity.this.setYBodyRot(CustomEntity.this.yBodyRot);
						CustomEntity.this.setYHeadRot(CustomEntity.this.yBodyRot);
						if (CustomEntity.this.isInWater()) {
							CustomEntity.this.setSpeed((float) CustomEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
							float f2 = -(float) (Mth.atan2(dy, Mth.sqrt((float)(dx * dx + dz * dz))) * (180F / Mth.PI));
							f2 = Mth.clamp(Mth.wrapDegrees(f2), -85, 85);
							CustomEntity.this.yBodyRotO = this.rotlerp(CustomEntity.this.yBodyRotO, f2, 5);
							float f3 = Mth.cos(CustomEntity.this.yBodyRotO * (float) (Mth.PI / 180.0));
							CustomEntity.this.(f3 * f1);
							CustomEntity.this.setMoveVertical((float) (f1 * dy));
						} else {
							CustomEntity.this.setAIMoveSpeed(f1 * 0.05F);
						}
					} else {
						CustomEntity.this.setAIMoveSpeed(0);
						CustomEntity.this.setMoveVertical(0);
						CustomEntity.this.setMoveForward(0);
					}
				}
			};
			this.navigator = new SwimmerPathNavigator(this, this.level);*/
		}

		@Override
		public Packet<?> getAddEntityPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new BreedGoal(this, 1));
			this.goalSelector.addGoal(2, new FollowParentGoal(this, 0.8));
			this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
			this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
			this.goalSelector.addGoal(5, new RandomSwimmingGoal(this,1,1));
			this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1, (float) 10, (float) 2, false));
			this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1));
			this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
			this.goalSelector.addGoal(9, new RandomSwimmingGoal(this,1,1));
		}

		@Override
		protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropCustomDeathLoot(source, looting, recentlyHitIn);
			this.spawnAtLocation(new ItemStack(GoldenfeathersItem.block));
		}

		@Override
		public SoundEvent getAmbientSound() {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("penguinmod:quiet"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.step")), 0.15f,
					1);
		}

		@Override
		public SoundEvent getHurtSound(DamageSource ds) {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.hurt"));
		}

		@Override
		public SoundEvent getDeathSound() {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.death"));
		}

		@Override
		public boolean hurt(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.FREEZE)
				return false;
			return super.hurt(source, amount);
		}

		@Override
		public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
			ItemStack itemstack = sourceentity.getItemInHand(hand);
			InteractionResult retval = InteractionResult.sidedSuccess(!this.level.isClientSide);
			Item item = itemstack.getItem();
			if (itemstack.getItem() instanceof SpawnEggItem) {
				retval = super.mobInteract(sourceentity, hand);
			} else if (!this.level.isClientSide) {
				retval = (this.isTame() && this.isOwnedBy(sourceentity) || this.isBreedingItem(itemstack))
						? InteractionResult.sidedSuccess(!this.level.isClientSide)
						: InteractionResult.PASS;
			} else {
				if (this.isTame()) {
					if (this.isOwnedBy(sourceentity)) {
						if (item.isEdible() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.usePlayerItem(sourceentity, hand, itemstack);
							this.heal((float) item.getFoodProperties().getNutrition());
							retval = InteractionResult.sidedSuccess(!this.level.isClientSide);
						} else if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.usePlayerItem(sourceentity, hand, itemstack);
							this.heal(4);
							retval = InteractionResult.sidedSuccess(!this.level.isClientSide);
						} else {
							retval = super.mobInteract(sourceentity, hand);
						}
					}
				} else if (this.isBreedingItem(itemstack)) {
					this.usePlayerItem(sourceentity, hand, itemstack);
					if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
						this.setOwnerUUID(sourceentity.getUUID());
						this.level.broadcastEntityEvent(this, (byte) 7);
					} else {
						this.level.broadcastEntityEvent(this, (byte) 6);
					}
					this.setPersistenceRequired();
					retval = InteractionResult.sidedSuccess(!this.level.isClientSide);
				} else {
					retval = super.mobInteract(sourceentity, hand);
					if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME)
						this.setPersistenceRequired();
				}
			}
			return retval;
		}

		@Override
		public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageable) {
			CustomEntity retval = (CustomEntity) entity.create(serverLevel);
			retval.spawnChildFromBreeding(serverLevel, retval);
			return retval;
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

		@Override
		public boolean canBreatheUnderwater() {
			return true;
		}

	}
}
