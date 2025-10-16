package mod.emt.thaumictweaker.mixins.client.gui;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.client.gui.GuiResearchBrowser;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
@Mixin(value = GuiResearchBrowser.class, remap = false)
public class GuiResearchBrowserMixin {
    @Shadow private ResearchEntry currentHighlight;

    @Inject(
            method = "genResearchBackgroundFixedPost",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/ArrayList;add(Ljava/lang/Object;)Z",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void addResearchSubtitleMixin(int mx, int my, float par3, int locX, int locY, CallbackInfo ci, @Local(ordinal = 0)ArrayList<String> text) {
        if(ConfigEnhancementsTT.enableResearchSubtitles) {
            String subTitle = this.currentHighlight.getName() + ".subtitle";
            if(I18n.hasKey(subTitle)) {
                text.add("@@" + TextFormatting.WHITE + I18n.format(subTitle) + TextFormatting.RESET);
            }
        }

        if(ConfigEnhancementsTT.enableWarpResearchSubtitles) {
            if(currentHighlight.getStages() != null) {
                int warp = 0;
                for(ResearchStage stage : currentHighlight.getStages()) {
                    warp += stage.getWarp();
                }
                if(warp > 0) {
                    String warpStr = "tc.forbidden.level." + Math.min(5, warp);
                    text.add("@@" + TextFormatting.DARK_PURPLE + I18n.format("subtitle.thaumictweaker.forbidden", I18n.format(warpStr)) + TextFormatting.RESET);
                }
            }
        }
    }

    @ModifyExpressionValue(
            method = "drawScreen",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/input/Mouse;getDWheel()I",
                    remap = false
            ),
            remap = true
    )
    private int disableZoomMixin(int original) {
        return ConfigTweaksTT.thaumonomicon.disableThaumonomiconZoom ? 0 : original;
    }
}
