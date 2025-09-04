package mod.emt.thaumictweaker.mixins.focus;

import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.casters.FocusMedium;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusMediumCloud;
import thaumcraft.common.lib.SoundsTC;

//Adds cast sound.
@Mixin(value = FocusMediumCloud.class, remap = false)
public abstract class FocusMediumCloudMixin extends FocusMedium {

    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    public void cloudFocusSound(Trajectory trajectory, CallbackInfoReturnable<Boolean> cir) {
        try {
            this.getPackage().world.playSound(null, this.getPackage().getCaster().getPosition().up(), SoundsTC.egscreech, SoundCategory.PLAYERS, 0.25F, 1.25F + (float) (this.getPackage().getCaster().world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

}
