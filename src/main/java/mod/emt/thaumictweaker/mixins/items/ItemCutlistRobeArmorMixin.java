package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
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
            return ConfigTweaksTT.cult_robes.visDiscountHelm;
        } else if(stack.getItem() == ItemsTC.crimsonRobeChest) {
            return ConfigTweaksTT.cult_robes.visDiscountChest;
        } else if(stack.getItem() == ItemsTC.crimsonRobeLegs) {
            return ConfigTweaksTT.cult_robes.visDiscountLeggings;
        }
        return original;
    }
}
