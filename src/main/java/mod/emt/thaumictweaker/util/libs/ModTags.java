package mod.emt.thaumictweaker.util.libs;

import mod.emt.thaumictweaker.config.ConfigOverhaulsTT;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import mod.emt.thaumictweaker.util.helpers.LogHelper;
import mod.emt.thaumictweaker.util.helpers.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.common.entities.construct.EntityOwnedConstruct;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModTags {
    private static final Map<Block, Integer> CRUCIBLE_HEAT_SOURCES = new LinkedHashMap<>();
    private static final Map<Class<? extends EntityCreature>, Integer> CHAMPION_MOBS = new HashMap<>();
    private static final Map<BiomeDictionary.Type, Integer> CHAMPION_BIOME_MODIFIER = new HashMap<>();

    public static boolean isCrucibleHeatSource(Block block, int meta) {
        if(CRUCIBLE_HEAT_SOURCES.containsKey(block)) {
            int val = CRUCIBLE_HEAT_SOURCES.get(block);
            return val == OreDictionary.WILDCARD_VALUE || val == meta;
        }
        return false;
    }

    public static int getChampionMobChance(Entity entity) {
        if(entity instanceof EntityCreature && !(entity instanceof EntityOwnedConstruct)) {
            if(!entity.isNonBoss() && !ConfigOverhaulsTT.championMobSettings.championBosses) {
                return -1;
            } else if(CHAMPION_MOBS.containsKey(entity.getClass())) {
                return CHAMPION_MOBS.get(entity.getClass());
            } else if(ConfigOverhaulsTT.championMobSettings.globalChampions) {
                return 0;
            }
        }
        return -1;
    }

    public static int getChampionBiomeModifier(Biome biome) {
        int max = 0;
        for(BiomeDictionary.Type type : CHAMPION_BIOME_MODIFIER.keySet()) {
            if(BiomeDictionary.hasType(biome, type)) {
                max = Math.max(max, CHAMPION_BIOME_MODIFIER.get(type));
            }
        }
        return max;
    }

    public static void syncConfig() {
        parseCrucibleHeatSources();
        parseChampionMobWhitelist();
        parseChampionBiomeModifiers();
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

    private static void parseChampionMobWhitelist() {
        CHAMPION_MOBS.clear();
        Pattern pattern = Pattern.compile("^(.+)=(\\d+)$");
        for(String configStr : ConfigOverhaulsTT.championMobSettings.championWhitelist) {
            try {
                Matcher matcher = pattern.matcher(configStr);
                if(matcher.find()) {
                    ResourceLocation loc = new ResourceLocation(matcher.group(1));
                    EntityEntry entry = RegistryHelper.getRegisteredEntity(loc);
                    if(entry != null) {
                        Class<? extends Entity> clazz = entry.getEntityClass();
                        if(EntityCreature.class.isAssignableFrom(clazz)) {
                            int chance = MathHelper.clamp(Integer.parseInt(matcher.group(2)), 0, 100);
                            CHAMPION_MOBS.put(clazz.asSubclass(EntityCreature.class), chance);
                        }
                    }
                }
            } catch(Exception e) {
                LogHelper.error("Invalid config string: " + configStr);
            }
        }
    }

    private static void parseChampionBiomeModifiers() {
        CHAMPION_BIOME_MODIFIER.clear();
        Pattern pattern = Pattern.compile("(.+)=(\\d+)");
        for(String configStr : ConfigOverhaulsTT.championMobSettings.biomeTypeModifier) {
            try {
                Matcher matcher = pattern.matcher(configStr);
                if (matcher.find()) {
                    String typeStr = matcher.group(1).toUpperCase();
                    for (BiomeDictionary.Type type : BiomeDictionary.Type.getAll()) {
                        if (type.getName().equals(typeStr)) {
                            int max = MathHelper.clamp(Integer.parseInt(matcher.group(2)), 0, 100);
                            CHAMPION_BIOME_MODIFIER.put(type, max);
                            break;
                        }
                    }

                }
            } catch (Exception e) {
                LogHelper.error("Invalid config string: " + configStr);
            }
        }
    }

    static {
        syncConfig();
    }
}
