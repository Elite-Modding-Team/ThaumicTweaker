package mod.emt.thaumictweaker.mixins;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.container.ContainerResearchTable;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.crafting.TileResearchTable;

@Mixin(value = ContainerResearchTable.class, remap = false)
public class ContainerResearchTableMixin {
    @Shadow public TileResearchTable tileEntity;

    //TODO: This doesn't seem to work. Check with another mixin loaded to see if it is fixed.

    @Redirect(
            method = "enchantItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/utils/InventoryUtils;isPlayerCarryingAmount(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;Z)Z",
                    remap = false
            ),
            remap = true
    )
    private boolean isItemInAdjacentInventory(EntityPlayer player, ItemStack stack, boolean ore) {
        if(true) {//TODO: Config
            return InventoryUtils.consumeItemsFromAdjacentInventoryOrPlayer(this.tileEntity.getWorld(), this.tileEntity.getPos(), player, true, stack);
        } else {
            return InventoryUtils.isPlayerCarryingAmount(player, stack, ore);
        }
    }

    @Redirect(
            method = "enchantItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/utils/InventoryUtils;consumePlayerItem(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;ZZ)Z",
                    remap = false
            ),
            remap = true
    )
    private boolean consumeItemMixin(EntityPlayer player, ItemStack stack, boolean noCheck, boolean ore) {
        if(true) {//TODO: Config
            return InventoryUtils.consumeItemsFromAdjacentInventoryOrPlayer(this.tileEntity.getWorld(), this.tileEntity.getPos(), player, false, stack);
        } else {
            return InventoryUtils.consumePlayerItem(player, stack, noCheck, ore);
        }
    }
}
