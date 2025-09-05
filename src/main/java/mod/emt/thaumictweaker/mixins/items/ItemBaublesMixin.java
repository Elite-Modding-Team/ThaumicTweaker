package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.common.items.baubles.ItemBaubles;

@Mixin(value = ItemBaubles.class, remap = false)
public class ItemBaublesMixin {
    @ModifyReturnValue(
            method = "getVisDiscount",
            at = @At("RETURN")
    )
    private int modifyVisDiscountMixin(int original, @Local(argsOnly = true) ItemStack stack) {
        //If Apprentice's Ring
        if(stack.getItemDamage() == 3) {
            return ConfigTweaksTT.apprentices_ring.visDiscount;
        }
        return original;
    }
}
