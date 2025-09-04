package mod.emt.thaumictweaker.mixins;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusMediumBolt;
import thaumcraft.common.items.casters.foci.FocusMediumTouch;
import thaumcraft.common.lib.SoundsTC;

//Adds cast sound.
@Mixin(value = FocusMediumBolt.class, remap = false)
public abstract class FocusMediumBoltMixin extends FocusMediumTouch {

    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    public void boltFocusSound(Trajectory trajectory, CallbackInfoReturnable<Boolean> cir) {
        try {
            this.getPackage().world.playSound(null, this.getPackage().getCaster().getPosition().up(), SoundsTC.shock, SoundCategory.PLAYERS, 0.175F, 1.0F + (float) (this.getPackage().getCaster().world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

}
