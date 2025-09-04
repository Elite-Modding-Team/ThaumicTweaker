package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import mod.emt.thaumictweaker.util.helpers.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.items.tools.ItemPrimalCrusher;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

import java.util.Set;

@Mixin(value = ItemPrimalCrusher.class, remap = false)
public abstract class ItemPrimalCrusherMixin extends ItemTool {
    protected ItemPrimalCrusherMixin(ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(materialIn, effectiveBlocksIn);
    }

    @Redirect(
            method = "getSubItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/enchantment/EnumInfusionEnchantment;addInfusionEnchantment(Lnet/minecraft/item/ItemStack;Lthaumcraft/common/lib/enchantment/EnumInfusionEnchantment;I)V",
                    ordinal = 1,
                    remap = false
            ),
            remap = true
    )
    private void modifyRefiningLevelMixin(ItemStack stack, EnumInfusionEnchantment infusionEnchant, int level) {
        EnumInfusionEnchantment.addInfusionEnchantment(stack, infusionEnchant, ConfigHandlerTT.primal_crusher.refiningLevel);
    }

    @Inject(
            method = "getSubItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/enchantment/EnumInfusionEnchantment;addInfusionEnchantment(Lnet/minecraft/item/ItemStack;Lthaumcraft/common/lib/enchantment/EnumInfusionEnchantment;I)V",
                    ordinal = 1,
                    remap = false
            ),
            remap = true
    )
    private void setUnbreakableMixin(CreativeTabs tab, NonNullList<ItemStack> items, CallbackInfo ci, @Local(ordinal = 0) ItemStack stack) {
        if(ConfigHandlerTT.primal_crusher.unbreakable) {
            ItemHelper.setUnbreakable(stack);
        }
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        if(ConfigHandlerTT.primal_crusher.unbreakable) {
            return;
        }
        super.setDamage(stack, damage);
    }
}
