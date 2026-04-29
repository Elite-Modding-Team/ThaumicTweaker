package mod.emt.thaumictweaker.mixins.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.items.tools.ItemVoidSword;

@Mixin(value = ItemVoidSword.class, remap = false)
public class ItemVoidSwordMixin extends ItemSword {
    protected ItemVoidSwordMixin(ToolMaterial materialIn) {
        super(materialIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem() != newStack.getItem() || slotChanged;
    }
}
