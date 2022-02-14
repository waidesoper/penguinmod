 package pixlux.penguinmod.item;

 import com.mojang.blaze3d.vertex.PoseStack;
 import com.mojang.blaze3d.vertex.VertexConsumer;
 import net.minecraft.client.model.EntityModel;
 import net.minecraft.client.model.HumanoidModel;
 import net.minecraft.client.model.geom.ModelPart;
 import net.minecraft.client.model.geom.PartPose;
 import net.minecraft.client.model.geom.builders.CubeListBuilder;
 import net.minecraft.client.model.geom.builders.LayerDefinition;
 import net.minecraft.client.model.geom.builders.MeshDefinition;
 import net.minecraft.client.model.geom.builders.PartDefinition;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.entity.Entity;
 import net.minecraft.world.entity.EquipmentSlot;
 import net.minecraft.world.entity.LivingEntity;
 import net.minecraft.world.item.ArmorItem;
 import net.minecraft.world.item.ArmorMaterial;
 import net.minecraft.world.item.Item;
 import net.minecraft.world.item.ItemStack;
 import net.minecraft.world.item.crafting.Ingredient;
 import pixlux.penguinmod.PenguinmodModElements;
 import pixlux.penguinmod.PenguinmodModElements.ModElement.Tag;
 import pixlux.penguinmod.itemgroup.AmazingpenguinsItemGroup;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.registries.ForgeRegistries;
 import net.minecraftforge.registries.ObjectHolder;
 
 @Tag
 public class CrownItem extends PenguinmodModElements.ModElement {
   @ObjectHolder("penguinmod:crown_helmet")
   public static final Item helmet = null;
   @ObjectHolder("penguinmod:crown_chestplate")
   public static final Item body = null;
   @ObjectHolder("penguinmod:crown_leggings")
   public static final Item legs = null;
   @ObjectHolder("penguinmod:crown_boots")
   public static final Item boots = null;
   public CrownItem(PenguinmodModElements instance) {
     super(instance, 18);
   }
 
   
   public void initElements() {
     ArmorMaterial armormaterial = new ArmorMaterial()
       {
         public int getDurabilityForSlot(EquipmentSlot slot) {
           (new int[4])[0] = 13; (new int[4])[1] = 15; (new int[4])[2] = 16; (new int[4])[3] = 11; return (new int[4])[slot.getIndex()] * 25;
         }
 
         
         public int getDefenseForSlot(EquipmentSlot slot) {
           (new int[4])[0] = 2; (new int[4])[1] = 5; (new int[4])[2] = 6; (new int[4])[3] = 4; return (new int[4])[slot.getIndex()];
         }
 
         
         public int getEnchantmentValue() {
           return 20;
         }
 
         
         public SoundEvent getEquipSound() {
           return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron"));
         }
 
         
         public Ingredient getRepairIngredient() {
           return Ingredient.of(new ItemStack(GoldenfeathersItem.block));
         }
 
         
         @OnlyIn(Dist.CLIENT)
         public String getName() {
           return "crown";
         }
 
         
         public float getToughness() {
           return 0.0F;
         }
 
         
         public float getKnockbackResistance() {
           return 0.0F;
         }
       };
     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlot.HEAD, (new Item.Properties()).tab(AmazingpenguinsItemGroup.tab))
         {
           @OnlyIn(Dist.CLIENT)
           public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel)
           {
             HumanoidModel armorModel = new HumanoidModel((new CrownItem.Modelcrownnew()).crown);
             return armorModel;
           }
 
           
           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
             return "penguinmod:textures/crown.png";
           }
         }).setRegistryName("crown_helmet"));
   }
   
   public static class Modelcrownnew
     extends EntityModel<Entity> {
     private final ModelPart crown;
     
     public Modelcrownnew() {
       this.crown = createBodyLayer().bakeRoot().getChild("Head");

     }

       public static LayerDefinition createBodyLayer() {
           MeshDefinition meshDefinition = new MeshDefinition();
           PartDefinition partDefinition = meshDefinition.getRoot();
           PartDefinition Head = partDefinition.addOrReplaceChild("Head", CubeListBuilder.create()
                   .texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 1.0F, 1.0F, 8.0F, false)
                   .texOffs(0, 0).addBox(-5.0F, -8.0F, 4.0F, 10.0F, 1.0F, 1.0F, false)
                   .texOffs(0, 0).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 1.0F, 1.0F, false)
                   .texOffs(0, 0).addBox(4.0F, -8.0F, -4.0F, 1.0F, 1.0F, 8.0F, false)
                   .texOffs(0, 0).addBox(-5.0F, -9.0F, -5.0F, 1.0F, 1.0F, 0.0F, false)
                   .texOffs(0, 0).addBox(-1.0F, -10.0F, -5.0F, 2.0F, 2.0F, 0.0F, false)
                   .texOffs(0, 0).addBox(4.0F, -9.0F, -5.0F, 1.0F, 1.0F, 0.0F, false)
                   .texOffs(0, 0).addBox(2.0F, -9.0F, -5.0F, 1.0F, 1.0F, 0.0F, false)
                   .texOffs(0, 0).addBox(1.0F, -11.0F, -5.0F, 1.0F, 1.0F, 0.0F, false)
                   .texOffs(0, 0).addBox(-3.0F, -9.0F, -5.0F, 1.0F, 1.0F, 0.0F, false)
                   .texOffs(0, 0).addBox(-2.0F, -11.0F, -5.0F, 1.0F, 1.0F, 0.0F, false),
                   PartPose.offset(0.0F, 24.0F, 0.0F));

           return LayerDefinition.create(meshDefinition,64,64);
       }

       public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
           this.crown.render(matrixStack, buffer, packedLight, packedOverlay);
       }

       public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
           modelRenderer.xRot = x;
           modelRenderer.yRot = y;
           modelRenderer.zRot = z;
       }

       public void setupAnim(Entity e, float f, float f1, float f2, float f3, float f4) {}
   }
 }

