package mod.emt.thaumictweaker.mixins.tiles;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.tiles.devices.TileVisGenerator;

@Mixin(value = TileVisGenerator.class, remap = false)
public class TileVisGeneratorMixin {
    @ModifyConstant(method = "update", constant = @Constant(intValue = 20), remap = true)
    private int modifyMaxExtract(int constant) {
        return ConfigTweaksTT.vis_generator.maxExtract;
    }

    @ModifyConstant(method = "recharge", constant = @Constant(floatValue = 1000.0F))
    private float modifyCapacity(float capacity) {
        return ConfigTweaksTT.vis_generator.capacity;
    }
}
