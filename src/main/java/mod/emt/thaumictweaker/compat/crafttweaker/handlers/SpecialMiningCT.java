package mod.emt.thaumictweaker.compat.crafttweaker.handlers;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.helpers.SpecialMiningHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenRegister
@ZenClass("mods." + ThaumicTweaker.MOD_ID + ".SpecialMiningResult")
public class SpecialMiningCT {
    @ZenMethod
    public static void add(IIngredient harvestDrop, IItemStack result, float chance) {
        SpecialMiningHelper.addSpecialMiningResult(CraftTweakerMC.getIngredient(harvestDrop), CraftTweakerMC.getItemStack(result), chance);
    }

    @ZenMethod
    public static void removeByHarvestDrop(IIngredient harvestDrop) {
        SpecialMiningHelper.removeSpecialMiningResultByInput(CraftTweakerMC.getIngredient(harvestDrop));
    }

    @ZenMethod
    public static void removeByOutput(IIngredient output) {
        SpecialMiningHelper.removeSpecialMiningResultByOutput(CraftTweakerMC.getIngredient(output));
    }

    @ZenMethod
    public static void removeAll() {
        SpecialMiningHelper.removeAllSpecialMiningResults();
    }

}
