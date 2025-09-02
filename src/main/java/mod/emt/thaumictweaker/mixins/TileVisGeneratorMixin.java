package mod.emt.thaumictweaker.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.tiles.devices.TileVisGenerator;

@Mixin(value = TileVisGenerator.class, remap = false)
public class TileVisGeneratorMixin {
    @Shadow protected int energy;

    @Redirect(
            method = "update",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"),
            remap = true
    )
    private int energyExtractedMixin(int energy, int maxExtract) {
        return Math.min(energy, ConfigHandlerTT.vis_generator.maxExtract);
    }

    @Inject(
            method = "recharge",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/tiles/devices/TileVisGenerator;markDirty()V",
                    remap = true
            )
    )
    private void modifyCapacityMixin(CallbackInfo ci, @Local(ordinal = 0) float vis) {
        this.energy = (int) (vis * ConfigHandlerTT.vis_generator.capacity);
    }
}
