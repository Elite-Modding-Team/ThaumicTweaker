package mod.emt.thaumictweaker.mixins.client.gui;

import mod.emt.thaumictweaker.config.ConfigWussModeTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.client.gui.GuiResearchPage;

import java.util.Arrays;

@Mixin(value = GuiResearchPage.class, remap = false)
public class GuiResearchPageMixin {
    @Shadow boolean hasAllRequisites;
    @Shadow boolean[] hasItem;
    @Shadow boolean[] hasCraft;
    @Shadow boolean[] hasResearch;
    @Shadow boolean[] hasKnow;

    @Inject(method = "checkRequisites", at = @At("TAIL"))
    private void checkRequisitesMixin(CallbackInfo ci) {
        if(!this.hasAllRequisites) {
            boolean hasCrafts = true;
            boolean hasItems = true;
            boolean hasKnowledge = true;
            boolean hasTheory = true;
            if (this.hasCraft != null) {
                if(ConfigWussModeTT.disableResearchRequirements.disableCraftsRequirements) {
                    Arrays.fill(this.hasCraft, true);
                } else {
                    for (boolean has : this.hasCraft) {
                        if (!has) {
                            hasCrafts = false;
                            break;
                        }
                    }
                }
            }
            if (this.hasItem != null) {
                if(ConfigWussModeTT.disableResearchRequirements.disableItemRequirements) {
                    Arrays.fill(this.hasItem, true);
                } else {
                    for (boolean has : this.hasItem) {
                        if (!has) {
                            hasItems = false;
                            break;
                        }
                    }
                }
            }
            if (this.hasKnow != null) {
                if(ConfigWussModeTT.disableResearchRequirements.disableKnowledgeRequirements) {
                    Arrays.fill(this.hasKnow, true);
                } else {
                    for(boolean has : this.hasKnow) {
                        if(!has) {
                            hasKnowledge = false;
                            break;
                        }
                    }
                }
            }
            if (this.hasResearch != null) {
                if(ConfigWussModeTT.disableResearchRequirements.disableTheoryRequirements) {
                    Arrays.fill(this.hasResearch, true);
                } else {
                    for(boolean has : this.hasResearch) {
                        if(!has) {
                            hasTheory = false;
                            break;
                        }
                    }
                }
            }
            if (hasCrafts && hasItems && hasKnowledge && hasTheory) {
                this.hasAllRequisites = true;
            }
        }
    }
}
