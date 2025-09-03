package mod.emt.thaumictweaker.util.helpers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import mod.emt.thaumictweaker.util.misc.EnumPechType;
import mod.emt.thaumictweaker.util.misc.PechTrade;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.MathHelper;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.EntityPech;

import javax.annotation.Nullable;
import java.util.*;

@SuppressWarnings("rawtypes")
public class PechHelper {
    private static final Map<EnumPechType, List<ItemStack>> fallbackTrades = ImmutableMap.copyOf(new HashMap<EnumPechType, List<ItemStack>>() {{
        put(EnumPechType.MINER, Lists.newArrayList(
                new ItemStack(ItemsTC.clusters, 1, 0),
                new ItemStack(BlocksTC.saplingGreatwood),
                new ItemStack(Items.EXPERIENCE_BOTTLE),
                new ItemStack(ItemsTC.thaumiumPick),
                new ItemStack(BlocksTC.saplingSilverwood),
                new ItemStack(ItemsTC.curio, 1, 2),
                new ItemStack(ItemsTC.curio, 1, 1)
        ));
        put(EnumPechType.MAGE, Lists.newArrayList(
                ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY),
                ThaumcraftApiHelper.makeCrystal(Aspect.FLUX),
                new ItemStack(Items.EXPERIENCE_BOTTLE),
                new ItemStack(ItemsTC.clothChest),
                new ItemStack(ItemsTC.pechWand),
                new ItemStack(ItemsTC.curio, 1, 0)
        ));
        put(EnumPechType.ARCHER, Lists.newArrayList(
                new ItemStack(BlocksTC.candles.get(EnumDyeColor.byDyeDamage(0))),
                new ItemStack(Items.COMPASS),
                new ItemStack(Items.EXPERIENCE_BOTTLE),
                new ItemStack(Items.SPECTRAL_ARROW),
                new ItemStack(ItemsTC.curio, 1, 4)
        ));
        put(EnumPechType.COMMON, Lists.newArrayList(
                new ItemStack(Items.STICK),
                new ItemStack(Items.STICK),
                new ItemStack(Items.STICK),
                new ItemStack(Items.STICK),
                new ItemStack(Items.STICK)
        ));
    }});

    public static void addPechTrade(EnumPechType pechType, int trustLevel, ItemStack stack) {
        ArrayList<List> trades = getPechTrades(pechType);
        if(trades == null) {
            getPechTrades().put(pechType.ordinal(), new ArrayList<>());
            addPechTrade(pechType, trustLevel, stack);
        } else {
            trades.add(Arrays.asList(trustLevel, stack));
        }
    }

    public static void addPechTrade(PechTrade trade) {
        addPechTrade(trade.pechType, trade.tradeLevel, trade.tradeStack);
    }

    public static void addValuedItem(ItemStack stack, int value) {
        if(!stack.isEmpty() && stack.getItem() != Items.AIR && value > 0) {
            value = MathHelper.clamp(value, 1, 500);
            EntityPech.valuedItems.put(Item.getIdFromItem(stack.getItem()), value);
        }
    }

    public static void removePechTrade(EnumPechType pechType, Ingredient ingredient) {
        try {
            ArrayList<List> trades = getPechTrades(pechType);
            if(trades != null) {
                trades.removeIf(list -> ingredient.apply((ItemStack) list.get(1)));
            }
        } catch (Exception e) {
            LogHelper.error("Failed to remove pech trade. Thaumcraft is terrible and I hate it.");
        }
    }

    public static void removeAllPechTrades(EnumPechType pechType) {
        ArrayList<List> trades = getPechTrades().get(pechType.ordinal());
        if(trades != null) {
            trades.clear();
        }
    }

    public static void removeAllPechTrades() {
        for(EnumPechType type : EnumPechType.values()) {
            removeAllPechTrades(type);
        }
    }

    public static EnumPechType getPechType(String pechType) {
        try {
            return EnumPechType.valueOf(pechType.toUpperCase());
        } catch (IllegalArgumentException e) {
            LogHelper.error(e);
            return EnumPechType.MINER;
        }
    }

    public static void verifyTrades() {
        for(EnumPechType pechType : EnumPechType.values()) {
            ArrayList<List> trades = getPechTrades(pechType);
            if(trades == null)
                continue;

            for(int tradeLevel = 1; tradeLevel <= 5; tradeLevel++) {
                boolean needsFallback = true;
                try {
                    for (List tradeItem : trades) {
                        if ((int) tradeItem.get(0) == tradeLevel) {
                            needsFallback = false;
                            break;
                        }
                    }
                } catch (Exception ignored) {}
                if(needsFallback) {
                    trades.add(getFallbackTrade(pechType, tradeLevel));
                }
            }
        }
    }

    private static HashMap<Integer, ArrayList<List>> getPechTrades() {
        return EntityPech.tradeInventory;
    }

    @Nullable
    private static  ArrayList<List> getPechTrades(EnumPechType pechType) {
        return getPechTrades().get(pechType.ordinal());
    }

    private static List getFallbackTrade(EnumPechType pechType, int tradeLevel) {
        tradeLevel = MathHelper.clamp(tradeLevel, 1, 5);
        return Arrays.asList(tradeLevel, fallbackTrades.get(pechType).get(tradeLevel - 1));
    }
}
