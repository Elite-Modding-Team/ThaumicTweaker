package mod.emt.thaumictweaker.mixins;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.tiles.crafting.TileVoidSiphon;

@Mixin(value = TileVoidSiphon.class, remap = false)
public class TileVoidSiphonMixin {
    @ModifyConstant(method = "update", constant = @Constant(intValue = 2000), remap = true)
    private int modifyProgressRequired(int constant) {
        return ConfigHandlerTT.void_siphon.progressRequired;
    }
}
