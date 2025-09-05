package mod.emt.thaumictweaker.mixins.misc;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.lib.research.ScanSky;

@Mixin(value = ScanSky.class, remap = false)
public class ScanSkyMixin {
    @Redirect(
            method = "checkThing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/WorldProvider;getDimensionType()Lnet/minecraft/world/DimensionType;",
                    remap = true
            )
    )
    private DimensionType validSkyDimensionMixin(WorldProvider provider) {
        for(int dimId : ConfigTweaksTT.misc_tweaks.skyDimensions) {
            if(dimId == provider.getDimension()) {
                return DimensionType.OVERWORLD;
            }
        }
        return provider.getDimensionType();
    }
}
