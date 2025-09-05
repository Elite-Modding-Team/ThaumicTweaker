package mod.emt.thaumictweaker.compat.crafttweaker.handlers;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.helpers.PorousStoneHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenRegister
@ZenClass("mods." + ThaumicTweaker.MOD_ID + ".PorousStone")
public class PorousStoneCT {
    @ZenMethod
    public static void addDrop(IItemStack stack, int weight) {
        if(weight <= 0) {
            CraftTweakerAPI.logError("Drop weight value must be greater than 0");
            throw new IllegalArgumentException("Drop weight value must be greater than 0");
        }
        PorousStoneHelper.addDrop(CraftTweakerMC.getItemStack(stack), weight);
    }

    @ZenMethod
    public static void removeDrop(IIngredient ingredient) {
        PorousStoneHelper.removeDrop(CraftTweakerMC.getIngredient(ingredient));
    }

    @ZenMethod
    public static void removeAllDrops() {
        PorousStoneHelper.removeAllDrops();
    }
}
