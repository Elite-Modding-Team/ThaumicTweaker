package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.armor.ItemRobeArmor;

@Mixin(value = ItemRobeArmor.class, remap = false)
public class ItemThaumaturgeRobesMixin {
    @ModifyReturnValue(method = "getVisDiscount", at = @At("RETURN"))
    private int modifyVisDiscountMixin(int original, @Local(argsOnly = true) ItemStack stack) {
        if(stack.getItem() == ItemsTC.clothChest) {
            return ConfigTweaksTT.equipment_tweaks.thaumaturge_robes.visDiscountChest;
        } else if(stack.getItem() == ItemsTC.clothLegs) {
            return ConfigTweaksTT.equipment_tweaks.thaumaturge_robes.visDiscountLeggings;
        } else if(stack.getItem() == ItemsTC.clothBoots) {
            return ConfigTweaksTT.equipment_tweaks.thaumaturge_robes.visDiscountBoots;
        }
        return original;
    }
}
