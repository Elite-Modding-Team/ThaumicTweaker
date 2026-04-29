package mod.emt.thaumictweaker.mixins.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.items.tools.ItemCrimsonBlade;

@Mixin(value = ItemCrimsonBlade.class, remap = false)
public class ItemCrimsonBladeMixin extends ItemSword {
    protected ItemCrimsonBladeMixin(ToolMaterial materialIn) {
        super(materialIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem() != newStack.getItem() || slotChanged;
    }
}
