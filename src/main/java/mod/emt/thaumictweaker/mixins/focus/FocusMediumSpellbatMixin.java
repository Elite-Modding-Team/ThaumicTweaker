package mod.emt.thaumictweaker.mixins.focus;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.casters.FocusMedium;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusMediumSpellBat;

//Adds cast sound.
@Mixin(value = FocusMediumSpellBat.class, remap = false)
public abstract class FocusMediumSpellbatMixin extends FocusMedium {

    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    public void spellbatFocusSound(Trajectory trajectory, CallbackInfoReturnable<Boolean> cir) {
        try {
            if(ConfigEnhancementsTT.enableFocusEffects)
                this.getPackage().world.playSound(null, this.getPackage().getCaster().getPosition().up(), SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.PLAYERS, 0.45F, 1.0F + (float) (this.getPackage().getCaster().world.rand.nextGaussian() * 0.05F));
        } catch (Exception ignored) {
        }
    }

}
