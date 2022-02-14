 package pixlux.penguinmod.itemgroup;
 
 import net.minecraft.world.item.CreativeModeTab;
 import net.minecraft.world.item.ItemStack;
 import pixlux.penguinmod.PenguinmodModElements;
 import pixlux.penguinmod.PenguinmodModElements.ModElement.Tag;
 import pixlux.penguinmod.item.PenguinfeatherItem;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 
 @Tag
 public class AmazingpenguinsItemGroup
   extends PenguinmodModElements.ModElement {
   public AmazingpenguinsItemGroup(PenguinmodModElements instance) {
     super(instance, 1);
   }
   public static CreativeModeTab tab;
   
   public void initElements() {
     tab = new CreativeModeTab("tabamazingpenguins")
       {
         @OnlyIn(Dist.CLIENT)
         @Override
         public ItemStack makeIcon() {
           return new ItemStack(PenguinfeatherItem.block);
         }


           @OnlyIn(Dist.CLIENT)
         public boolean hasSearchBar() {
           return false;
         }
       };
   }
 }

