package mod.emt.thaumictweaker.mixins.entities;

import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;

@Mixin(value = EntityEldritchWarden.class, remap = false)
public class EntityEldritchWardenMixin {
    @Redirect(
            method = "updateAITasks",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/boss/EntityEldritchWarden;getAbsorptionAmount()F"
            ),
            remap = true
    )
    private float getRunicShieldingOnUpdateMixin(EntityEldritchWarden entity) {
        return (float) RunicShieldingHandler.getRunicShielding(entity);
    }

    @Redirect(
            method = "updateAITasks",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/boss/EntityEldritchWarden;setAbsorptionAmount(F)V"
            ),
            remap = true
    )
    private void setRunicShieldingOnUpdateMixin(EntityEldritchWarden entity, float amount) {
        RunicShieldingHandler.setRunicShielding(entity, amount);
    }

    @Redirect(
            method = "onInitialSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/boss/EntityEldritchWarden;getAbsorptionAmount()F"
            ),
            remap = true
    )
    private float getRunicShieldingOnSpawnUpdateMixin(EntityEldritchWarden entity) {
        return (float) RunicShieldingHandler.getRunicShielding(entity);
    }

    @Redirect(
            method = "onInitialSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/boss/EntityEldritchWarden;setAbsorptionAmount(F)V"
            ),
            remap = true
    )
    private void setRunicShieldingOnSpawnMixin(EntityEldritchWarden entity, float amount) {
        RunicShieldingHandler.setRunicShielding(entity, amount);
    }

    @Redirect(
            method = "attackEntityFrom",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/boss/EntityEldritchWarden;getAbsorptionAmount()F"
            ),
            remap = true
    )
    private float getRunicShieldingOnAttackMixin(EntityEldritchWarden entity) {
        return (float) RunicShieldingHandler.getRunicShielding(entity);
    }
}
