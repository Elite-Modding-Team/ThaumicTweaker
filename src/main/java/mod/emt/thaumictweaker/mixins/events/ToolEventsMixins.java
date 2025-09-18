package mod.emt.thaumictweaker.mixins.events;

import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import thaumcraft.common.lib.events.ToolEvents;

@Mixin(value = ToolEvents.class, remap = false)
public class ToolEventsMixins {
    @ModifyVariable(method = "harvestBlockEvent", name = "chance", ordinal = 0, at = @At("STORE"))
    private static float modifyDropChanceMixin(float chance, @Local(ordinal = 0) ItemStack heldStack, @Local(ordinal = 0) int refining) {
        refining = (refining - 1)  + ConfigTweaksTT.infusion_enchantments.refiningBaseIncrease;
        if(refining > 0) {
            int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, heldStack);
            chance = (float) ((refining * ConfigTweaksTT.infusion_enchantments.refiningClusterChance) + (fortune * ConfigTweaksTT.infusion_enchantments.refiningFortuneBonus));
        }
        return chance;
    }
}
