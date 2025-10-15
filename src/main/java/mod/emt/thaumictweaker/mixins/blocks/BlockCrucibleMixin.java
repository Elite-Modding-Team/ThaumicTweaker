package mod.emt.thaumictweaker.mixins.blocks;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.blocks.crafting.BlockCrucible;
import thaumcraft.common.tiles.crafting.TileCrucible;

@Mixin(value = BlockCrucible.class, remap = false)
public class BlockCrucibleMixin {
    @Redirect(
            method = "onBlockActivated",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/tiles/crafting/TileCrucible;attemptSmelt(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Lnet/minecraft/item/ItemStack;",
                    ordinal = 0,
                    remap = false
            ),
            remap = true
    )
    private ItemStack cancelCrucibleInteractionMixin(TileCrucible tileCrucible, ItemStack stack, String playerName) {
        if (ConfigTweaksTT.crucible.disableItemInteraction) {
            return stack;
        }
        return tileCrucible.attemptSmelt(stack, playerName);
    }
}
