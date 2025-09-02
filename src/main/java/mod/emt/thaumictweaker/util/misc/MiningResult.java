package mod.emt.thaumictweaker.util.misc;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class MiningResult {
    public final Ingredient harvestStack;
    public final ItemStack resultStack;
    public final float chance;

    public MiningResult(Ingredient harvestStack, ItemStack resultStack, float chance) {
        this.harvestStack = harvestStack;
        this.resultStack = resultStack;
        this.chance = chance;
    }
}
