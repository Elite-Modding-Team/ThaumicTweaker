package mod.emt.thaumictweaker.mixins.misc;

import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import net.minecraft.entity.monster.EntityMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.lib.utils.EntityUtils;

@Mixin(value = EntityUtils.class, remap = false)
public class EntityUtilsMixin {
    @Redirect(
            method = "makeChampion",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/monster/EntityMob;getAbsorptionAmount()F",
                    remap = true
            )
    )
    private static float getRunicShieldingMixin(EntityMob entityMob) {
        return (float) RunicShieldingHandler.getRunicShielding(entityMob);
    }

    @Redirect(
            method = "makeChampion",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/monster/EntityMob;setAbsorptionAmount(F)V",
                    remap = true
            )
    )
    private static void setRunicShieldingMixin(EntityMob entityMob, float amount) {
        RunicShieldingHandler.setRunicShielding(entityMob, amount);
    }
}
