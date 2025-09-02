package mod.emt.thaumictweaker.util.helpers;

import mod.emt.thaumictweaker.util.misc.MiningResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.MathHelper;
import thaumcraft.common.lib.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SpecialMiningHelper {
    public static void addSpecialMiningResult(Ingredient harvestDrop, ItemStack output, float chance) {
        if(harvestDrop != null && harvestDrop != Ingredient.EMPTY && !output.isEmpty()) {
            chance = MathHelper.clamp(chance, 0, 1.0f);
            for(ItemStack stack : harvestDrop.getMatchingStacks()) {
                Utils.addSpecialMiningResult(stack, output, chance);
            }
        }
    }

    public static void addSpecialMiningResult(MiningResult miningResult) {
        addSpecialMiningResult(miningResult.harvestStack, miningResult.resultStack, miningResult.chance);
    }

    public static void removeSpecialMiningResultByInput(Ingredient input) {
        try {
            Utils.specialMiningResult.keySet().removeIf(list -> input.apply(new ItemStack((Item) list.get(0), 1, (int) list.get(1))));
            Utils.specialMiningChance.keySet().removeIf(list -> input.apply(new ItemStack((Item) list.get(0), 1, (int) list.get(1))));
        } catch (Exception e) {
            LogHelper.error("Failed to remove special mining result by input: " + input.toString());
            LogHelper.error(e);
        }
    }

    public static void removeSpecialMiningResultByOutput(Ingredient output) {
        List<ItemStack> stacks = new ArrayList<>();
        try {
            Utils.specialMiningResult.forEach((list, result) -> {
                if(output.apply(result)) {
                    stacks.add(new ItemStack((Item) list.get(0), 1, (int) list.get(1)));
                }
            });

            if (!stacks.isEmpty()) {
                Ingredient ingredient = Ingredient.fromStacks(stacks.toArray(new ItemStack[0]));
                removeSpecialMiningResultByInput(ingredient);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to remove special mining result by output: " + output.toString());
            LogHelper.error(e);
        }
    }

    public static void removeAllSpecialMiningResults() {
        Utils.specialMiningResult.clear();
        Utils.specialMiningChance.clear();
    }
}
