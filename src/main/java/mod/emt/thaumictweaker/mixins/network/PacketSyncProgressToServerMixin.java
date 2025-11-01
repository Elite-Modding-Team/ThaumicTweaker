package mod.emt.thaumictweaker.mixins.network;

import mod.emt.thaumictweaker.config.ConfigWussModeTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;
import thaumcraft.common.lib.network.playerdata.PacketSyncProgressToServer;

@Mixin(value = PacketSyncProgressToServer.class, remap = false)
public class PacketSyncProgressToServerMixin {
    @Redirect(
            method = "checkRequisites",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/api/research/ResearchStage;getObtain()[Ljava/lang/Object;"
            )
    )
    private Object[] skipItemMixin(ResearchStage instance) {
        return !ConfigWussModeTT.disableResearchRequirements.disableItemRequirements ? instance.getObtain() : null;
    }

    @Redirect(
            method = "checkRequisites",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/api/research/ResearchStage;getCraft()[Ljava/lang/Object;"
            )
    )
    private Object[] skipCraftsMixin(ResearchStage instance) {
        return !ConfigWussModeTT.disableResearchRequirements.disableCraftsRequirements ? instance.getCraft() : null;
    }

    @Redirect(
            method = "checkRequisites",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/api/research/ResearchStage;getResearch()[Ljava/lang/String;"
            )
    )
    private String[] skipResearchMixin(ResearchStage instance) {
        return !ConfigWussModeTT.disableResearchRequirements.disableTheoryRequirements ? instance.getResearch() : null;
    }

    @Redirect(
            method = "checkRequisites",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/api/research/ResearchStage;getKnow()[Lthaumcraft/api/research/ResearchStage$Knowledge;"
            )
    )
    private Knowledge[] skipKnowledgeMixin(ResearchStage instance) {
        return !ConfigWussModeTT.disableResearchRequirements.disableKnowledgeRequirements ? instance.getKnow() : null;
    }
}
