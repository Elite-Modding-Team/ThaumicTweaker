package mod.emt.thaumictweaker.mixins.entities;

import mod.emt.thaumictweaker.config.ConfigWussModeTT;
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
}
