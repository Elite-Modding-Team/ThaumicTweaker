package mod.emt.thaumictweaker.mixins.tiles;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.util.libs.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.common.tiles.crafting.TileCrucible;

@Mixin(value = TileCrucible.class, remap = false)
public class TileCrucibleMixin {
    @ModifyExpressionValue(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;"
            ),
            remap = true
    )
    private Material isValidHeatSourceMixin(Material original, @Local(ordinal = 0)IBlockState state) {
        if(original != Material.LAVA) {
            Block block = state.getBlock();
            int meta = block.getMetaFromState(state);
            if(ModTags.isCrucibleHeatSource(block, meta)) {
                return Material.LAVA;
            }
        }
        return original;
    }
}
