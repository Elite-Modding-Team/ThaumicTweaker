package mod.emt.thaumictweaker.compat.crafttweaker.handlers;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

@ZenRegister
@ZenClass("mods." + ThaumicTweaker.MOD_ID + ".Utils")
public class TweakerUtilsCT {
    @ZenMethod
    public static IItemStack addInfusionEnchantment(IItemStack item, String infusionEnchant, int level) {
        try {
            ItemStack stack = CraftTweakerMC.getItemStack(item);
            EnumInfusionEnchantment infusion = EnumInfusionEnchantment.valueOf(infusionEnchant);
            if(level <= 0) {
                String errString = "Invalid infusion enchantment level. Value must be greater than 0.";
                CraftTweakerAPI.logError(errString);
                throw new IllegalArgumentException(errString);
            } else if (level > infusion.maxLevel) {
                String errorStr = String.format("Invalid infusion enchantment level. %s cannot be larger than %d.", infusion, infusion.maxLevel);
                CraftTweakerAPI.logError(errorStr);
                throw new IllegalArgumentException(errorStr);
            }
            EnumInfusionEnchantment.addInfusionEnchantment(stack, infusion, level);
            return CraftTweakerMC.getIItemStack(stack);
        } catch (IllegalArgumentException e) {
            CraftTweakerAPI.logError("Invalid infusion enchantment name: " + infusionEnchant);
            throw new IllegalArgumentException(e);
        }
    }

}
