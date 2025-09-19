package mod.emt.thaumictweaker.mixins.events;

import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.lib.events.EntityEvents;

@Mixin(value = EntityEvents.class, remap = false)
public class EntityEventsMixin {
    @Redirect(
            method = "entityHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;getAbsorptionAmount()F",
                    remap = true
            )
    )
    private static float getRunicShieldingMixin(EntityPlayer player) {
        return (float) RunicShieldingHandler.getRunicShielding(player);
    }

    @Redirect(
            method = "entityHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/monster/EntityMob;getAbsorptionAmount()F",
                    remap = true
            )
    )
    private static float getRunicShieldingMixin(EntityMob entity) {
        return (float) RunicShieldingHandler.getRunicShielding(entity);
    }
}
