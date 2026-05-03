package mod.emt.thaumictweaker.mixins.thaumicaugmentation;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thecodex6824.thaumicaugmentation.common.tile.TileImpetusDiffuser;

@Mixin(value = TileImpetusDiffuser.class, remap = false)
public class TileImpetusDiffuserMixin {
    @ModifyConstant(method = "update", constant = @Constant(intValue = 2000), remap = true)
    private int modifySiphonProgressMixin(int constant) {
        return ConfigTweaksTT.void_siphon.progressRequired;
    }
}
