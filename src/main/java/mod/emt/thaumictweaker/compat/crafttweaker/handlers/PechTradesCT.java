package mod.emt.thaumictweaker.compat.crafttweaker.handlers;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.helpers.PechHelper;
import mod.emt.thaumictweaker.util.misc.EnumPechType;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenRegister
@ZenClass("mods." + ThaumicTweaker.MOD_ID + ".PechTrades")
public class PechTradesCT {
    @ZenMethod
    public static void addTrade(String pechType, int tradeLevel, IItemStack stack) {
        try {
            EnumPechType type = EnumPechType.valueOf(pechType);
            if(tradeLevel > 5 || tradeLevel < 1) {
                CraftTweakerAPI.logError("Invalid Pech trade level. Values can only be 1 through 5");
                return;
            }
            PechHelper.addPechTrade(type, tradeLevel, CraftTweakerMC.getItemStack(stack));
        } catch (IllegalArgumentException e) {
            CraftTweakerAPI.logError("Invalid Pech type: " + pechType + ". Values can only be 'MINER', 'MAGE', or 'ARCHER'");
        }
    }

    @ZenMethod
    public static void addValuedItem(IItemStack item, int value) {
        ItemStack stack = CraftTweakerMC.getItemStack(item);
        if(value <= 0) {
            CraftTweakerAPI.logError("Item value must be greater than 0");
            throw new IllegalArgumentException("Item value must be greater than 0");
        }
        PechHelper.addValuedItem(stack, value);
    }

    @ZenMethod
    public static void removeTrade(String pechType, IIngredient ingredient) {
        try {
            EnumPechType type = EnumPechType.valueOf(pechType);
            PechHelper.removePechTrade(type, CraftTweakerMC.getIngredient(ingredient));
        } catch (IllegalArgumentException e) {
            CraftTweakerAPI.logError("Invalid Pech type: " + pechType + ". Values can only be 'MINER', 'MAGE', or 'ARCHER'");
        }
    }

    @ZenMethod
    public static void removeAllTrades(String pechType) {
        try {
            EnumPechType type = EnumPechType.valueOf(pechType);
            PechHelper.removeAllPechTrades(type);
        } catch (IllegalArgumentException e) {
            CraftTweakerAPI.logError("Invalid Pech type: " + pechType + ". Values can only be 'MINER', 'MAGE', 'ARCHER', or 'COMMON'");
        }
    }

    @ZenMethod
    public static void removeAllTrades() {
        PechHelper.removeAllPechTrades();
    }
}
