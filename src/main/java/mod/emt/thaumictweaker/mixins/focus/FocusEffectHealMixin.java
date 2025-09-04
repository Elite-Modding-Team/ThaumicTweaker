package mod.emt.thaumictweaker.mixins.focus;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusEffectHeal;
import thaumcraft.common.lib.SoundsTC;

//Revamps cast sound and adds an impact sound.
@Mixin(value = FocusEffectHeal.class, remap = false)
public abstract class FocusEffectHealMixin extends FocusEffect {

    @Override
    public void onCast(final Entity caster) {
        try {
            if(ConfigHandlerTT.spell_effects.focusEffects)
                caster.world.playSound(null, caster.getPosition().up(), SoundsTC.wand, SoundCategory.PLAYERS, 0.825F, 3.0F + (float) (caster.world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    public void healFocusImpactSound(RayTraceResult target, Trajectory trajectory, float finalPower, int num, CallbackInfoReturnable<Boolean> cir) {
        try {
            if(ConfigHandlerTT.spell_effects.focusEffects)
                this.getPackage().world.playSound(null, target.hitVec.x, target.hitVec.y, target.hitVec.z, SoundsTC.wand, SoundCategory.PLAYERS, 0.525F, 0.7F + (float) (this.getPackage().getCaster().world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

}
