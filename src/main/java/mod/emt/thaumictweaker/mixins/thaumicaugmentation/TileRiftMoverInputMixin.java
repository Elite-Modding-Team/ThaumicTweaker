package mod.emt.thaumictweaker.mixins.thaumicaugmentation;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.entities.EntityFluxRift;
import thecodex6824.thaumicaugmentation.common.tile.TileRiftMoverInput;

@Mixin(value = TileRiftMoverInput.class, remap = false)
public class TileRiftMoverInputMixin {
    @Shadow protected EntityFluxRift rift;

    @Redirect(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/EntityFluxRift;setRiftSize(I)V",
                    remap = false
            ),
            remap = true
    )
    private void riftCollapseFixSetSizeMixin(EntityFluxRift instance, int size) {
        instance.setRiftSize(Math.max(2, size));
    }

    @ModifyExpressionValue(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/EntityFluxRift;getRiftSize()I",
                    remap = false
            ),
            remap = true
    )
    private int riftCollapseFixGetSizeMixin(int original) {
        return original <= 2 ? 0 : original;
    }

    @Inject(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lthecodex6824/thaumicaugmentation/api/tile/IRiftJar;setRift(Lthecodex6824/thaumicaugmentation/api/util/FluxRiftReconstructor;)V",
                    shift = At.Shift.AFTER,
                    remap = false
            ),
            remap = true
    )
    private void riftCollapseFixDestroyRiftMixin(CallbackInfo ci) {
        if(this.rift != null) {
            this.rift.setDead();
        }
    }
}
