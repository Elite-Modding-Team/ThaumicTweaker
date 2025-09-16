package mod.emt.thaumictweaker.util.helpers;

import mod.emt.thaumictweaker.util.misc.FluxRiftDrop;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;

public class FluxRiftHelper {
    private static final List<FluxRiftDrop> BONUS_DROPS = new ArrayList<>();

    public static void addCollapsingRiftDrop(FluxRiftDrop drop) {
        BONUS_DROPS.add(drop);
    }

    public static void addCollapsingRiftDrop(ItemStack stack, int riftSize, float chance) {
        addCollapsingRiftDrop(new FluxRiftDrop(stack, riftSize, chance));
    }

    public static NonNullList<ItemStack> getCollapsingRiftDrops(int riftSize) {
        NonNullList<ItemStack> drops = NonNullList.create();
        for(FluxRiftDrop drop : BONUS_DROPS) {
            if(drop.shouldDrop(riftSize)) {
                drops.add(drop.getDrop());
            }
        }
        return drops;
    }

    public static void clearDrops() {
        BONUS_DROPS.clear();
    }
}
