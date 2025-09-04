package mod.emt.thaumictweaker.mixins.tiles;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.entities.EntityFluxRift;
import thaumcraft.common.tiles.crafting.TileVoidSiphon;

import java.util.List;

@Mixin(value = TileVoidSiphon.class, remap = false)
public class TileVoidSiphonMixin {
    @Shadow public int progress;

    @ModifyConstant(method = "update", constant = @Constant(intValue = 2000), remap = true)
    private int modifyProgressRequired(int constant) {
        return ConfigHandlerTT.void_siphon.progressRequired;
    }

    @Inject(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addBlockEvent(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;II)V",
                    shift = At.Shift.BY,
                    by = 2
            ),
            remap = true
    )
    private void wussModeSeedCreation(CallbackInfo ci, @Local(ordinal = 0) List<EntityFluxRift> rifts, @Local(ordinal = 0) LocalBooleanRef ref) {
        if(rifts.isEmpty() && ConfigHandlerTT.wuss_mode.wussModeVoidSiphon) {
            this.progress += 5;
            ref.set(true);
        }
    }
}
