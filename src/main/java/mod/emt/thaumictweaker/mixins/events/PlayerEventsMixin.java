package mod.emt.thaumictweaker.mixins.events;

import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.lib.events.PlayerEvents;

@Mixin(value = PlayerEvents.class, remap = false)
public class PlayerEventsMixin {
    @Redirect(
            method = "handleRunicArmor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;setAbsorptionAmount(F)V",
                    remap = true
            )
    )
    private static void setRunicShieldingMixin(EntityPlayer player, float amount) {
        RunicShieldingHandler.setRunicShielding(player, amount);
    }

    @Redirect(
            method = "handleRunicArmor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;getAbsorptionAmount()F",
                    remap = true
            )
    )
    private static float getRunicShieldingMixin(EntityPlayer player) {
        return (float) RunicShieldingHandler.getRunicShielding(player);
    }
}
