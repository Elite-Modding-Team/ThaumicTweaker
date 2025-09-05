package mod.emt.thaumictweaker.mixins.focus;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusEffectExchange;
import thaumcraft.common.lib.SoundsTC;

//Revamps cast sound and adds an impact sound.
@Mixin(value = FocusEffectExchange.class, remap = false)
public abstract class FocusEffectExchangeMixin extends FocusEffect {

    @Override
    public void onCast(final Entity caster) {
        try {
            if(ConfigEnhancementsTT.enableFocusEffects)
                caster.world.playSound(null, caster.getPosition().up(), SoundsTC.hhoff, SoundCategory.PLAYERS, 0.8F, 0.45F + (float) (caster.world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    public void exchangeFocusImpactSound(RayTraceResult target, Trajectory trajectory, float finalPower, int num, CallbackInfoReturnable<Boolean> cir) {
        try {
            if(ConfigEnhancementsTT.enableFocusEffects)
                this.getPackage().world.playSound(null, target.hitVec.x, target.hitVec.y, target.hitVec.z, SoundsTC.hhon, SoundCategory.PLAYERS, 0.8F, 0.85F + (float) (this.getPackage().getCaster().world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

}
