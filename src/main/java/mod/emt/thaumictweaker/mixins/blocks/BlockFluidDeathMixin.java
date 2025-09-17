package mod.emt.thaumictweaker.mixins.blocks;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import thaumcraft.common.blocks.misc.BlockFluidDeath;

@Mixin(value = BlockFluidDeath.class, remap = false)
public class BlockFluidDeathMixin {
    @ModifyArg(
            method = "onEntityCollision",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"
            ),
            index = 1
    )
    private float modifyDamageDealt(float amount) {
        return (float) (amount * ConfigTweaksTT.fluid_death.damageModifier);
    }
}
