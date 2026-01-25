package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.armor.ItemGoggles;

@Mixin(value = ItemGoggles.class, remap = false)
public class ItemRevealingGogglesMixin {
    @ModifyReturnValue(method = "getVisDiscount", at = @At("RETURN"))
    private int modifyVisDiscountMixin(int original, @Local(argsOnly = true) ItemStack stack) {
        if(stack.getItem() == ItemsTC.goggles) {
            return ConfigTweaksTT.equipment_tweaks.goggles_revealing.visDiscountGoggles;
        }
        return original;
    }
}
