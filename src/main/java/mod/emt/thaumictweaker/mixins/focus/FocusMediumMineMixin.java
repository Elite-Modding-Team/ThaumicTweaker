package mod.emt.thaumictweaker.mixins.focus;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.casters.FocusMedium;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusMediumMine;
import thaumcraft.common.lib.SoundsTC;

//Adds cast sound.
@Mixin(value = FocusMediumMine.class, remap = false)
public abstract class FocusMediumMineMixin extends FocusMedium {

    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    public void mineFocusSound(Trajectory trajectory, CallbackInfoReturnable<Boolean> cir) {
        try {
            if(ConfigHandlerTT.misc_tweaks.enableFocusEffects)
                this.getPackage().world.playSound(null, this.getPackage().getCaster().getPosition().up(), SoundsTC.upgrade, SoundCategory.PLAYERS, 0.6F, 1.0F + (float) (this.getPackage().getCaster().world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

}
