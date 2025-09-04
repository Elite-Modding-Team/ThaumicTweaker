package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.armor.ItemVoidRobeArmor;

@Mixin(value = ItemVoidRobeArmor.class, remap = false)
public class ItemVoidRobeArmorMixin {
    @ModifyReturnValue(
            method = "getVisDiscount",
            at = @At("RETURN")
    )
    private int modifyVisDiscountMixin(int original, @Local(argsOnly = true) ItemStack stack) {
        if(stack.getItem() == ItemsTC.voidRobeHelm) {
            return ConfigHandlerTT.void_robes.visDiscountHelm;
        } else if(stack.getItem() == ItemsTC.voidRobeChest) {
            return ConfigHandlerTT.void_robes.visDiscountChest;
        } else if(stack.getItem() == ItemsTC.voidRobeLegs) {
            return ConfigHandlerTT.void_robes.visDiscountLeggings;
        }
        return original;
    }
}
