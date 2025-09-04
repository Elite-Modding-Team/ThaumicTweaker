package mod.emt.thaumictweaker.mixins.focus;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusEffectFlux;

//Adds an impact sound.
@Mixin(value = FocusEffectFlux.class, remap = false)
public abstract class FocusEffectFluxMixin extends FocusEffect {

    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    public void fluxFocusImpactSound(RayTraceResult target, Trajectory trajectory, float finalPower, int num, CallbackInfoReturnable<Boolean> cir) {
        try {
            if(ConfigHandlerTT.misc_tweaks.enableFocusEffects)
                this.getPackage().world.playSound(null, target.hitVec.x, target.hitVec.y, target.hitVec.z, SoundEvents.BLOCK_CHORUS_FLOWER_DEATH, SoundCategory.PLAYERS, 1.5F, 2.0F + (float) (this.getPackage().getCaster().world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

}
