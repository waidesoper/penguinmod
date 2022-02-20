
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
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.common.MinecraftForge;
import pixlux.penguinmod.procedures.PenguinRightClickedOnEntityProcedure;
import pixlux.penguinmod.itemgroup.AmazingpenguinsItemGroup;
import pixlux.penguinmod.item.EarwarmerItem;
import pixlux.penguinmod.entity.renderer.EarpenguinRenderer;
import pixlux.penguinmod.PenguinmodModElements;

import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

@PenguinmodModElements.ModElement.Tag
public class EarpenguinEntity extends PenguinmodModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>of(CustomEntity::new, MobCategory.AMBIENT)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).sized(0.6f, 1f))
					.build("earpenguin").setRegistryName("earpenguin");

	public EarpenguinEntity(PenguinmodModElements instance) {
		super(instance, 11);
		FMLJavaModLoadingContext.get().getModEventBus().register(new EarpenguinRenderer.ModelRegisterHandler());
		FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
		elements.items.add(() -> new ForgeSpawnEggItem((Supplier<? extends EntityType<? extends Mob>>) entity, -16777216, -16711936, new Item.Properties().tab(AmazingpenguinsItemGroup.tab))
				.setRegistryName("earpenguin_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("snowy_tundra").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(entity, 20, 4, 5));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		SpawnPlacements.register(entity, SpawnPlacements.Type.NO_RESTRICTIONS,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
	}

	private static class EntityAttributesRegisterHandler {
		@SubscribeEvent
		public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
			AttributeSupplier.Builder ammma = Mob.createMobAttributes();
			ammma = ammma.add(Attributes.MOVEMENT_SPEED, 0.3);
			ammma = ammma.add(Attributes.MAX_HEALTH, 10);
			ammma = ammma.add(Attributes.ARMOR, 0);
			ammma = ammma.add(Attributes.ATTACK_DAMAGE, 0);
			event.put(entity, ammma.build());
		}
	}

	public static class CustomEntity extends TamableAnimal {
		public CustomEntity(PlayMessages.SpawnEntity packet, Level world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, Level world) {
			super(type, world);
			this.xpReward = 2;
			setNoAi(false);
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

		/*@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}*/

		protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropCustomDeathLoot(source, looting, recentlyHitIn);
			this.spawnAtLocation(new ItemStack(EarwarmerItem.helmet));
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
			double x = this.getX();
			double y = this.getY();
			double z = this.getZ();
			Entity entity = this;

			PenguinRightClickedOnEntityProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
					(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
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
	}
}
