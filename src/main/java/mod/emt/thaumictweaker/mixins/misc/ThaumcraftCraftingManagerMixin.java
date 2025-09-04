package mod.emt.thaumictweaker.mixins.misc;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

import java.util.ArrayList;

@Mixin(value = ThaumcraftCraftingManager.class, remap = false)
public class ThaumcraftCraftingManagerMixin {
    @Inject(method = "generateTagsFromCrucibleRecipes", at = @At("HEAD"), cancellable = true)
    private static void generateTagsFromCrucibleRecipesMixin(ItemStack stack, ArrayList<String> history, CallbackInfoReturnable<AspectList> cir) {
        if(ConfigHandlerTT.misc_tweaks.disableRecipeAspectsCrucible)
            cir.cancel();
    }

    @Inject(method = "generateTagsFromInfusionRecipes", at = @At("HEAD"), cancellable = true)
    private static void generateTagsFromInfusionRecipesMixin(ItemStack stack, ArrayList<String> history, CallbackInfoReturnable<AspectList> cir) {
        if(ConfigHandlerTT.misc_tweaks.disableRecipeAspectsInfusion)
            cir.cancel();
    }

    @Inject(method = "generateTagsFromRecipes", at = @At("HEAD"), cancellable = true)
    private static void generateTagsFromCraftingRecipesMixin(ItemStack stack, ArrayList<String> history, CallbackInfoReturnable<AspectList> cir) {
        if(ConfigHandlerTT.misc_tweaks.disableRecipeAspectsCrafting)
            cir.cancel();
    }

}
