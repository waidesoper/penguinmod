 package pixlux.penguinmod.item;
 
 import net.minecraft.world.item.Item;
 import net.minecraft.world.item.ItemStack;
 import net.minecraft.world.item.Rarity;
 import net.minecraft.world.level.block.state.BlockState;
 import pixlux.penguinmod.PenguinmodModElements;
 import pixlux.penguinmod.PenguinmodModElements.ModElement.Tag;
 import pixlux.penguinmod.itemgroup.AmazingpenguinsItemGroup;
 import net.minecraftforge.registries.ObjectHolder;
 
 @Tag
 public class GoldenfeathersItem
   extends PenguinmodModElements.ModElement
 {
   @ObjectHolder("penguinmod:goldenfeathers")
   public static final Item block = null;
   public GoldenfeathersItem(PenguinmodModElements instance) {
     super(instance, 18);
   }
 
   
   public void initElements() {
     this.elements.items.add(() -> new ItemCustom());
   }
   
   public static class ItemCustom extends Item { public ItemCustom() {
       super((new Item.Properties()).tab(AmazingpenguinsItemGroup.tab).stacksTo(64).rarity(Rarity.EPIC));
       setRegistryName("goldenfeathers");
     }
 
     
     public int func_77619_b() {
       return 0;
     }
 
     
     public int func_77626_a(ItemStack itemstack) {
       return 0;
     }
 
     
     public float func_150893_a(ItemStack par1ItemStack, BlockState par2Block) {
       return 1.0F;
     } }
 
 }

