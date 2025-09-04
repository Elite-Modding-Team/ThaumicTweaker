package mod.emt.thaumictweaker.mixins.containers;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import mod.emt.thaumictweaker.util.helpers.InventoryHelper;
import mod.emt.thaumictweaker.util.helpers.LogHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.container.ContainerResearchTable;
import thaumcraft.common.tiles.crafting.TileResearchTable;

@Mixin(value = ContainerResearchTable.class, remap = false)
public class ContainerResearchTableMixin {
    @Shadow public TileResearchTable tileEntity;

    @ModifyExpressionValue(
            method = "enchantItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/utils/InventoryUtils;isPlayerCarryingAmount(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;Z)Z",
                    remap = false
            ),
            remap = true
    )
    private boolean isItemInNearbyInventories(boolean original, @Local(ordinal = 0) TheorycraftCard card, @Local(ordinal = 0)ItemStack stack) {
        if(!original && ConfigHandlerTT.misc_tweaks.researchTablePulling) {
            ItemStack invStack = InventoryHelper.pullFromNearbyInventories(this.tileEntity.getWorld(), this.tileEntity.getPos(), 3, stack, true, true);
            return invStack.getCount() == stack.getCount();
        }
        return original;
    }

    @ModifyExpressionValue(
            method = "enchantItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/utils/InventoryUtils;consumePlayerItem(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;ZZ)Z",
                    remap = false
            ),
            remap = true
    )
    private boolean consumeFromNearbyInventories(boolean original, @Local(ordinal = 0) TheorycraftCard card, @Local(ordinal = 1) int index) {
        try {
            if (!original && ConfigHandlerTT.misc_tweaks.researchTablePulling) {
                ItemStack stack = card.getRequiredItems()[index];
                ItemStack extracted = InventoryHelper.pullFromNearbyInventories(this.tileEntity.getWorld(), this.tileEntity.getPos(), 3, stack, true, false);
                return stack.getCount() == extracted.getCount();
            }
        } catch (Exception e) {
            LogHelper.error("An error occurred attempting to withdraw items for research.");
            LogHelper.error(e);
        }
        return original;
    }
}
