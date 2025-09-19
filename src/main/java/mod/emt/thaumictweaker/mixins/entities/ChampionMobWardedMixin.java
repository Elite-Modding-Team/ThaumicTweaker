package mod.emt.thaumictweaker.mixins.entities;

import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.entities.monster.mods.ChampionModWarded;

@Mixin(value = ChampionModWarded.class, remap = false)
public class ChampionMobWardedMixin {
    @Redirect(
            method = "performEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityLivingBase;getAbsorptionAmount()F",
                    remap = true
            )
    )
    private float getRunicShieldingMixin(EntityLivingBase entity) {
        return (float) RunicShieldingHandler.getRunicShielding(entity);
    }

    @Redirect(
            method = "performEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityLivingBase;setAbsorptionAmount(F)V",
                    remap = true
            )
    )
    private void setRunicShieldingMixin(EntityLivingBase entity, float amount) {
        RunicShieldingHandler.setRunicShielding(entity, amount);
    }
}
