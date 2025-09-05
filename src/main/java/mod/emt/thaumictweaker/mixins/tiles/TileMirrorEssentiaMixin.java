package mod.emt.thaumictweaker.mixins.tiles;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.tiles.devices.TileMirrorEssentia;

@Mixin(value = TileMirrorEssentia.class, remap = false)
public class TileMirrorEssentiaMixin {
    @ModifyConstant(method = "checkInstability", constant = @Constant(intValue = 100))
    private int modifyInstabilityDecay(int constant) {
        return ConfigHandlerTT.flux_pollution.essentiaMirrorInstabilityDecay;
    }
}
