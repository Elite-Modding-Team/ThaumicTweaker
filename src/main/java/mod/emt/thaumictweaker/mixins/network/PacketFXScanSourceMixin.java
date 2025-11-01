package mod.emt.thaumictweaker.mixins.network;

import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import mod.emt.thaumictweaker.util.libs.ModTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.lib.network.fx.PacketFXScanSource;

@SideOnly(Side.CLIENT)
@Mixin(value = PacketFXScanSource.class, remap = false)
public class PacketFXScanSourceMixin {
    @ModifyConstant(method = "startScan", constant = @Constant(intValue = 4, ordinal = 1))
    private int modifyRadius(int range) {
        return ConfigTweaksTT.infusion_enchantments.soundingRadiusModifier;
    }

    @Inject(
            method = "getOreColor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/oredict/OreDictionary;getOreName(I)Ljava/lang/String;",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void getSoundingColorOverrideMixin(World world, BlockPos pos, CallbackInfoReturnable<Integer> cir, @Local(name = "id") int oreId) {
        int override = ModTags.getSoundingColorOverride(oreId);
        if(override > -1) {
            cir.setReturnValue(override);
        }
    }
}
