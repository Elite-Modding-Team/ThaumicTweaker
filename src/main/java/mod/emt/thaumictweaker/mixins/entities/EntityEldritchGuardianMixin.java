package mod.emt.thaumictweaker.mixins.entities;

import mod.emt.thaumictweaker.config.ConfigWussModeTT;
import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.internal.IInternalMethodHandler;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;

@Mixin(value = EntityEldritchGuardian.class, remap = false)
public class EntityEldritchGuardianMixin {
    @Redirect(
            method = "attackEntityWithRangedAttack",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/api/internal/IInternalMethodHandler;addWarpToPlayer(Lnet/minecraft/entity/player/EntityPlayer;ILthaumcraft/api/capabilities/IPlayerWarp$EnumWarpType;)V"
            )
    )
    private void preventWarpGainMixin(IInternalMethodHandler instance, EntityPlayer player, int amount, IPlayerWarp.EnumWarpType enumWarpType) {
        if (!ConfigWussModeTT.preventEldritchGuardianWarpGain) {
            instance.addWarpToPlayer(player, amount, enumWarpType);
        }
    }

    @Redirect(
            method = "updateAITasks",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/EntityEldritchGuardian;getAbsorptionAmount()F"
            ),
            remap = true
    )
    private float getRunicShieldingOnUpdateMixin(EntityEldritchGuardian entity) {
        return (float) RunicShieldingHandler.getRunicShielding(entity);
    }

    @Redirect(
            method = "updateAITasks",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/EntityEldritchGuardian;setAbsorptionAmount(F)V"
            ),
            remap = true
    )
    private void setRunicShieldingOnUpdateMixin(EntityEldritchGuardian entity, float amount) {
        RunicShieldingHandler.setRunicShielding(entity, amount);
    }

    @Redirect(
            method = "onInitialSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/EntityEldritchGuardian;getAbsorptionAmount()F"
            ),
            remap = true
    )
    private float getRunicShieldingOnSpawnMixin(EntityEldritchGuardian entity) {
        return (float) RunicShieldingHandler.getRunicShielding(entity);
    }

    @Redirect(
            method = "onInitialSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/entities/monster/EntityEldritchGuardian;setAbsorptionAmount(F)V"
            ),
            remap = true
    )
    private void setRunicShieldingOnSpawnMixin(EntityEldritchGuardian entity, float amount) {
        RunicShieldingHandler.setRunicShielding(entity, amount);
    }
}
