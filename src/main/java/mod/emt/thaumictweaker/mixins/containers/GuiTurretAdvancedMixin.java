package mod.emt.thaumictweaker.mixins.containers;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.client.gui.GuiTurretAdvanced;

@SideOnly(Side.CLIENT)
@Mixin(GuiTurretAdvanced.class)
public class GuiTurretAdvancedMixin {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 232))
    private int textureYSizeMixin(int constant) {
        return ConfigEnhancementsTT.adjustTurretGui ? 166 : constant;
    }
}
