package mod.emt.thaumictweaker.util.helpers;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemGenericEssentiaContainer;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.consumables.ItemPhial;

public class AspectContainerHelper {
    public static ItemStack setItemContainerAspects(ItemStack stack, AspectList aspectList) {
        if(stack.getItem() instanceof ItemGenericEssentiaContainer) {
            ((ItemGenericEssentiaContainer) stack.getItem()).setAspects(stack, aspectList);
        }
        return stack;
    }

    public static ItemStack createAspectCrystal(Aspect aspect, int aspectAmount) {
        ItemStack stack = new ItemStack(ItemsTC.crystalEssence);
        return setItemContainerAspects(stack, new AspectList().add(aspect, aspectAmount));
    }

    public static ItemStack createAspectPhial(Aspect aspect) {
        return ItemPhial.makeFilledPhial(aspect);
    }
}
