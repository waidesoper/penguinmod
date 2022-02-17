
package pixlux.penguinmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
import pixlux.penguinmod.itemgroup.AmazingpenguinsItemGroup;
import pixlux.penguinmod.item.HarpoonItem;
import pixlux.penguinmod.entity.renderer.PoacherRenderer;
import pixlux.penguinmod.PenguinmodModElements;

import java.util.function.Supplier;

@PenguinmodModElements.ModElement.Tag
public class PoacherEntity extends PenguinmodModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>of(CustomEntity::new, MobCategory.AMBIENT)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new)
			.sized(0.6f, 1.8f)).build("poacher").setRegistryName("poacher");

	public PoacherEntity(PenguinmodModElements instance) {
		super(instance, 26);
		FMLJavaModLoadingContext.get().getModEventBus().register(new PoacherRenderer.ModelRegisterHandler());
		FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
		elements.items.add(() -> new ForgeSpawnEggItem((Supplier<? extends EntityType<? extends Mob>>) entity, -10079488, -16777216, new Item.Properties().tab(AmazingpenguinsItemGroup.tab))
				.setRegistryName("poacher_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("snowy_tundra").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_taiga_mountains").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_taiga_hills").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_taiga").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_mountains").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(entity, 2, 1, 3));
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
			ammma = ammma.add(Attributes.MAX_HEALTH, 20);
			ammma = ammma.add(Attributes.ARMOR, 5);
			ammma = ammma.add(Attributes.ATTACK_DAMAGE, 5);
			event.put(entity, ammma.build());
		}
	}

	public static class CustomEntity extends Monster implements RangedAttackMob {
		public CustomEntity(PlayMessages.SpawnEntity packet, Level world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, Level world) {
			super(type, world);
			this.xpReward = 6;
			setNoAi(false);
		}

		@Override
		public Packet<?> getAddEntityPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
			this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Player.class, false, false));
			this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, BigpenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, BrownpenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(8, new NearestAttackableTargetGoal(this, ChristmassweaterpenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(9, new NearestAttackableTargetGoal(this, EarpenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(10, new NearestAttackableTargetGoal(this, HumboldtpenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(11, new NearestAttackableTargetGoal(this, MinipenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(12, new NearestAttackableTargetGoal(this, MegallanicpenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(13, new NearestAttackableTargetGoal(this, PenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(14, new NearestAttackableTargetGoal(this, ReindeerpenjguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(15, new NearestAttackableTargetGoal(this, YelloweyepenguinEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(16, new NearestAttackableTargetGoal(this, Villager.class, false, false));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
/*				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}*/
			});
		}

		/*@Override
		public CreatureAttribute getCreatureAttribute() {
			Vindicator
			return CreatureAttribute.ILLAGER;
		}*/

		@Override
		public SoundEvent getAmbientSound() {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.pillager.ambient"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.piglin_brute.step")),
					0.15f, 1);
		}

		@Override
		public SoundEvent getHurtSound(DamageSource ds) {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.pillager.hurt"));
		}

		@Override
		public SoundEvent getDeathSound() {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.pillager.death"));
		}

		public void performRangedAttack(LivingEntity target, float flval) {
			HarpoonItem.shoot(this, target);
		}
	}
}
