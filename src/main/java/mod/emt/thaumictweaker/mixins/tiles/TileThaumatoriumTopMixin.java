package mod.emt.thaumictweaker.mixins.tiles;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.tiles.crafting.TileThaumatorium;
import thaumcraft.common.tiles.crafting.TileThaumatoriumTop;

@Mixin(value = TileThaumatoriumTop.class, remap = false)
public class TileThaumatoriumTopMixin {
    //TODO: Move this fix into ThaumcraftFix at some point in the future.

    @Shadow public TileThaumatorium thaumatorium;

    /**
     * @author Invadermonky
     * @reason Fixes a NullPointerException crash when the {@link TileThaumatoriumTop#isEmpty()} method is called
     *         prior to {@link TileThaumatoriumTop#thaumatorium} being initialized.
     */
    @Inject(method = "isEmpty", at = @At("HEAD"), cancellable = true)
    private void fixNPECrashMixin(CallbackInfoReturnable<Boolean> cir) {
        if(this.thaumatorium == null) {
            cir.setReturnValue(false);
        }
    }
}
