package mod.emt.thaumictweaker.util.libs;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import mod.emt.thaumictweaker.util.helpers.LogHelper;
import mod.emt.thaumictweaker.util.helpers.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModTags {
    private static final Map<Block, Integer> CRUCIBLE_HEAT_SOURCES = new LinkedHashMap<>();

    public static boolean isCrucibleHeatSource(Block block, int meta) {
        if(CRUCIBLE_HEAT_SOURCES.containsKey(block)) {
            int val = CRUCIBLE_HEAT_SOURCES.get(block);
            return val == OreDictionary.WILDCARD_VALUE || val == meta;
        }
        return false;
    }

    public static void syncConfig() {
        parseCrucibleHeatSources();
    }

    private static void parseCrucibleHeatSources() {
        CRUCIBLE_HEAT_SOURCES.clear();
        Pattern pattern = Pattern.compile("^(.+?):(.+?):?(\\d*)$");
        for(String string : ConfigTweaksTT.crucible.crucibleHeatSources) {
            try {
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    String modId = matcher.group(1);
                    String itemId = matcher.group(2);
                    String metaStr = matcher.group(3);
                    int meta = metaStr != null && !metaStr.isEmpty() ? Integer.parseInt(metaStr) : OreDictionary.WILDCARD_VALUE;
                    Block block = RegistryHelper.getRegisteredBlock(new ResourceLocation(modId, itemId));
                    if (block != Blocks.AIR) {
                        CRUCIBLE_HEAT_SOURCES.put(block, meta);
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                LogHelper.error("Invalid configuration string: " + string);
            }
        }
    }

    static {
        syncConfig();
    }
}
