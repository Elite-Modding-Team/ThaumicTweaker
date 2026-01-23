package mod.emt.thaumictweaker.mixins.misc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.entities.monster.mods.ChampionModifier;

@Mixin(value = ChampionModifier.class, remap = false)
public class ChampionModifierMixin {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 2))
    private int modifyAmplifier(int constant) {
        return 1;
    }
}
