package mod.emt.thaumictweaker.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.armor.ItemCultistRobeArmor;

@Mixin(value = ItemCultistRobeArmor.class, remap = false)
public class ItemCutlistRobeArmorMixin {
    @ModifyReturnValue(method = "getVisDiscount", at = @At("RETURN"))
    private int modifyVisDiscountMixin(int original, @Local(argsOnly = true) ItemStack stack) {
        if(stack.getItem() == ItemsTC.crimsonRobeHelm) {
            return ConfigHandlerTT.cult_robes_tweaks.visDiscountHelm;
        } else if(stack.getItem() == ItemsTC.crimsonRobeChest) {
            return ConfigHandlerTT.cult_robes_tweaks.visDiscountChest;
        } else if(stack.getItem() == ItemsTC.crimsonRobeLegs) {
            return ConfigHandlerTT.cult_robes_tweaks.visDiscountLeggings;
        }
        return original;
    }
}
