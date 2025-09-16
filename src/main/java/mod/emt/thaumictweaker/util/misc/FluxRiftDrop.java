package mod.emt.thaumictweaker.util.misc;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class FluxRiftDrop {
    private static final Random rand = new Random();

    protected ItemStack drop;
    protected int requiredSize;
    protected float chance;

    public FluxRiftDrop(ItemStack drop, int requiredSize, float chance) {
        this.drop = drop;
        this.requiredSize = MathHelper.clamp(requiredSize, 1, 10000);
        chance = MathHelper.clamp(chance, 0, 1.0f);
        this.chance = chance == 0 ? 1.0f : chance;
    }

    public boolean shouldDrop(int riftSize) {
        return riftSize >= this.requiredSize && rand.nextFloat() <= this.chance;
    }

    public ItemStack getDrop() {
        return drop.copy();
    }
}
