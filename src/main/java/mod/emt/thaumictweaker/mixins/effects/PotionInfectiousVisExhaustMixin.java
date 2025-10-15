package mod.emt.thaumictweaker.mixins.effects;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import mod.emt.thaumictweaker.util.libs.ModTags;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;

import java.util.List;

@Mixin(value = PotionInfectiousVisExhaust.class, remap = false)
public class PotionInfectiousVisExhaustMixin {
    @ModifyExpressionValue(
            method = "performEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;"
            ),
            remap = true
    )
    private List<EntityLivingBase> restrictInfectiousTargetsMixin(List<EntityLivingBase> targets) {
        targets.removeIf(ModTags::isEntityVisExhaustBlacklisted);
        return targets;
    }
}
