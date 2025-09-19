package mod.emt.thaumictweaker.mixins.misc;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.lib.utils.EntityUtils;

@Mixin(value = EntityUtils.class, remap = false)
public class EntityUtilsMixin {
    /**
     * @author Invadermonky
     * @reason Fixes Primordial Pearls spawning 3-4 blocks in the air after enemy is slain.
     */
    @Inject(method = "entityDropSpecialItem", at = @At("HEAD"))
    private static void modifyDropHeightMixin(Entity entity, ItemStack stack, float dropheight, CallbackInfoReturnable<EntityItem> cir, @Local(argsOnly = true) LocalFloatRef ref) {
        ref.set(0);
    }

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
