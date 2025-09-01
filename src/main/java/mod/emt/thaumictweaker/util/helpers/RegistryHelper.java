package mod.emt.thaumictweaker.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegistryHelper {
    public static ItemStack getConfigStack(String configString) {
        if(configString.isEmpty())
            return ItemStack.EMPTY;

        try {
            String[] split = configString.split(":");
            if (split.length == 2 || split.length == 3) {
                ResourceLocation loc = new ResourceLocation(split[0], split[1]);
                int meta = split.length == 3 ? Integer.parseInt(split[2]) : 0;

                Block block = getRegisteredBlock(loc);
                if (block != Blocks.AIR) {
                    return new ItemStack(block, 1, meta);
                }

                Item item = getRegisteredItem(loc);
                if (item != Items.AIR) {
                    return new ItemStack(item, 1, meta);
                }
            }
        } catch (NumberFormatException exception) {
            LogHelper.error("Invalid configuration string: %s" + configString);
        }
        return ItemStack.EMPTY;
    }

    public static Item getRegisteredItem(ResourceLocation location) {
        Item item = ForgeRegistries.ITEMS.getValue(location);
        return item != null ? item : Items.AIR;
    }

    public static Block getRegisteredBlock(ResourceLocation location) {
        Block block = ForgeRegistries.BLOCKS.getValue(location);
        return block != null ? block : Blocks.AIR;
    }
}
