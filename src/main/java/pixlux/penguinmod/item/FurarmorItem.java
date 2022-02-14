 package pixlux.penguinmod.item;
 
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.entity.Entity;
 import net.minecraft.world.entity.EquipmentSlot;
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
 public class FurarmorItem extends PenguinmodModElements.ModElement {
   @ObjectHolder("penguinmod:furarmor_helmet")
   public static final Item helmet = null;
   @ObjectHolder("penguinmod:furarmor_chestplate")
   public static final Item body = null;
   @ObjectHolder("penguinmod:furarmor_leggings")
   public static final Item legs = null;
   @ObjectHolder("penguinmod:furarmor_boots")
   public static final Item boots = null;
   public FurarmorItem(PenguinmodModElements instance) {
     super(instance, 5);
   }
 
   
   public void initElements() {
     ArmorMaterial armormaterial = new ArmorMaterial()
       {
         public int getDurabilityForSlot(EquipmentSlot slot) {
           (new int[4])[0] = 13; (new int[4])[1] = 15; (new int[4])[2] = 16; (new int[4])[3] = 11; return (new int[4])[slot.getIndex()] * 25;
         }
 
         
         public int getDefenseForSlot(EquipmentSlot slot) {
           (new int[4])[0] = 1; (new int[4])[1] = 3; (new int[4])[2] = 4; (new int[4])[3] = 2; return (new int[4])[slot.getIndex()];
         }
 
         
         public int getEnchantmentValue() {
           return 19;
         }
 
         
         public SoundEvent getEquipSound() {
           return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather"));
         }
 
         
         public Ingredient getRepairIngredient() {
           return Ingredient.of(new ItemStack(PenguinfeatherItem.block));
         }
 
         
         @OnlyIn(Dist.CLIENT)
         public String getName() {
           return "furarmor";
         }
 
         
         public float getToughness() {
           return 0.1F;
         }
 
         
         public float getKnockbackResistance() {
           return 0.0F;
         }
       };
     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlot.HEAD, (new Item.Properties()).tab(AmazingpenguinsItemGroup.tab))
         {
           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
             return "penguinmod:textures/models/armor/newdiamond__new_layer_" + ((slot == EquipmentSlot.LEGS) ? "2" : "1") + ".png";
           }
         }).setRegistryName("furarmor_helmet"));
     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlot.CHEST, (new Item.Properties()).tab(AmazingpenguinsItemGroup.tab))
         {
           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
             return "penguinmod:textures/models/armor/newdiamond__new_layer_" + ((slot == EquipmentSlot.LEGS) ? "2" : "1") + ".png";
           }
         }).setRegistryName("furarmor_chestplate"));
     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlot.LEGS, (new Item.Properties()).tab(AmazingpenguinsItemGroup.tab))
         {
           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
             return "penguinmod:textures/models/armor/newdiamond__new_layer_" + ((slot == EquipmentSlot.LEGS) ? "2" : "1") + ".png";
           }
         }).setRegistryName("furarmor_leggings"));
     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlot.FEET, (new Item.Properties()).tab(AmazingpenguinsItemGroup.tab))
         {
           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
             return "penguinmod:textures/models/armor/newdiamond__new_layer_" + ((slot == EquipmentSlot.LEGS) ? "2" : "1") + ".png";
           }
         }).setRegistryName("furarmor_boots"));
   }
 }


/* Location:              /home/waide/Downloads/Amazing+Penguins+1.16.5.jar!/pixlux/penguinmod/item/FurarmorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
