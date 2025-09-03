package mod.emt.thaumictweaker.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;

public class ItemHelper {
    public static void setUnbreakable(ItemStack stack) {
        stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
    }
}
