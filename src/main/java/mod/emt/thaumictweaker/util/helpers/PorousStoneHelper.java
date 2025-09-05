package mod.emt.thaumictweaker.util.helpers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.WeightedRandom;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ModConfig;

import java.util.ArrayList;
import java.util.Random;

public class PorousStoneHelper {
    public static ArrayList<WeightedRandomLoot> porousStoneDrops = new ArrayList<>();

    public static ItemStack getBlockDrop(Random rand) {
        return WeightedRandom.getRandomItem(rand, porousStoneDrops).item.copy();
    }

    public static void addDrop(ItemStack stack, int weight) {
        weight = Math.max(1, weight);
        porousStoneDrops.add(new WeightedRandomLoot(stack, weight));
    }

    public static void removeDrop(Ingredient ingredient) {
        porousStoneDrops.removeIf(drop -> ingredient.apply(drop.item));
    }

    public static void removeAllDrops() {
        porousStoneDrops.clear();
    }

    public static void initDrops() {
        removeAllDrops();
        for(Aspect aspect : Aspect.getCompoundAspects()) {
            int amount = aspect == Aspect.FLUX ? 100 : (aspect.isPrimal() ? 20 : 1);
            addDrop(AspectContainerHelper.createAspectCrystal(aspect, amount), 1);
        }

        addDrop(new ItemStack(ItemsTC.amber), 20);
        addDrop(new ItemStack(ItemsTC.clusters, 1, 0), 20);
        addDrop(new ItemStack(ItemsTC.clusters, 1, 1), 10);
        addDrop(new ItemStack(ItemsTC.clusters, 1, 6), 10);
        if (ModConfig.foundCopperIngot) {
            addDrop(new ItemStack(ItemsTC.clusters, 1, 2), 10);
        }

        if (ModConfig.foundTinIngot) {
            addDrop(new ItemStack(ItemsTC.clusters, 1, 3), 10);
        }

        if (ModConfig.foundSilverIngot) {
            addDrop(new ItemStack(ItemsTC.clusters, 1, 4), 8);
        }

        if (ModConfig.foundLeadIngot) {
            addDrop(new ItemStack(ItemsTC.clusters, 1, 5), 10);
        }

        addDrop(new ItemStack(Items.DIAMOND), 2);
        addDrop(new ItemStack(Items.EMERALD), 4);
        addDrop(new ItemStack(Items.REDSTONE), 8);
        addDrop(new ItemStack(Items.PRISMARINE_CRYSTALS), 3);
        addDrop(new ItemStack(Items.PRISMARINE_SHARD), 3);
        addDrop(new ItemStack(Items.CLAY_BALL), 30);
        addDrop(new ItemStack(Items.QUARTZ), 15);
    }

    static {
        initDrops();
    }
}
