package mod.emt.thaumictweaker.util.misc;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Range;

public class PechTrade {
    public EnumPechType pechType;
    public int tradeLevel;
    public ItemStack tradeStack;

    public PechTrade(String pechType, @Range(from = 1, to = 5) int tradeLevel, ItemStack tradeStack) throws IllegalArgumentException {
        Preconditions.checkArgument(tradeLevel >= 1 && tradeLevel <= 5, "Trade level must be between 1 and 5");
        Preconditions.checkArgument(!tradeStack.isEmpty(), "Trade item cannot be null");
        this.pechType = EnumPechType.valueOf(pechType.toUpperCase());
        this.tradeLevel = tradeLevel;
        this.tradeStack = tradeStack;
    }
}
