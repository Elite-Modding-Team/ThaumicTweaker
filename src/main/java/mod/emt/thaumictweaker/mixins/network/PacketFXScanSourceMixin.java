package mod.emt.thaumictweaker.mixins.network;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.lib.network.fx.PacketFXScanSource;

@SideOnly(Side.CLIENT)
@Mixin(value = PacketFXScanSource.class, remap = false)
public class PacketFXScanSourceMixin {
    @ModifyConstant(method = "startScan", constant = @Constant(intValue = 4, ordinal = 1))
    private int modifyRadius(int range) {
        return ConfigTweaksTT.infusion_enchantments.soundingRadiusModifier;
    }
}
