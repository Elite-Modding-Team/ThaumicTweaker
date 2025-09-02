package mod.emt.thaumictweaker.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.emt.thaumictweaker.config.generics.GolemMaterialCategory;
import net.minecraftforge.common.config.Config;

//TODO: Remove before release
// @Config(modid = ThaumicTweaker.MOD_ID)
public class ConfigHandlerTT {
    @Config.Name("Golem Tweaks")
    public static GolemTweaksCategory golem_tweaks = new GolemTweaksCategory();
    @Config.Name("Miscellaneous Tweaks")
    public static MiscTweaksCategory misc_tweaks = new MiscTweaksCategory();
    @Config.Name("Vis Generator Tweaks")
    public static VisGeneratorCategory vis_generator = new VisGeneratorCategory();

    public static class GolemTweaksCategory {
        @Config.Name("Material: Greatwood")
        public GolemMaterialCategory matWood = new GolemMaterialCategory(6, 2, 1, "thaumcraft:plank_greatwood");
        @Config.Name("Material: Iron")
        public GolemMaterialCategory matIron = new GolemMaterialCategory(20, 8, 3, "thaumcraft:plate:1");
        @Config.Name("Material: Clay")
        public GolemMaterialCategory matClay = new GolemMaterialCategory(10, 4, 2, "minecraft:hardened_clay");
        @Config.Name("Material: Brass")
        public GolemMaterialCategory matBrass = new GolemMaterialCategory(10, 4, 2, "thaumcraft:plate:0");
        @Config.Name("Material: Thaumium")
        public GolemMaterialCategory matThaumium = new GolemMaterialCategory(24, 10, 4, "thaumcraft:plate:2");
        @Config.Name("Material: Void Metal")
        public GolemMaterialCategory matVoid = new GolemMaterialCategory(20, 6, 4, "thaumcraft:plate:3");
    }

    public static class MiscTweaksCategory {
        @Config.RequiresMcRestart
        @Config.Name("Disable Crafting Recipe Aspects")
        @Config.Comment("Disables Thaumcraft's dynamic aspect generation from crafting recipes.")
        public boolean disableRecipeAspectsCrafting = false;

        @Config.RequiresMcRestart
        @Config.Name("Disable Crucible Recipe Aspects")
        @Config.Comment("Disables Thaumcraft's dynamic aspect generation from crucible recipes.")
        public boolean disableRecipeAspectsCrucible = false;

        @Config.RequiresMcRestart
        @Config.Name("Disable Infusion Recipe Aspects")
        @Config.Comment("Disables Thaumcraft's dynamic aspect generation from infusion recipes.")
        public boolean disableRecipeAspectsInfusion = false;

        @Config.Name("Sky Scan Dimensions")
        @Config.Comment("")
        public int[] skyDimensions = new int[] {};
    }

    public static class VisGeneratorCategory {
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1, max = 10000000)
        @Config.Name("RF Capacity")
        @Config.Comment("The maximum amount of RF the Vis Capacitor can hold. This value also controls the amount of RF generated per point of Vis.")
        public int capacity = 1000;

        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1, max = 10000000)
        @Config.Name("Max RF Extraction")
        @Config.Comment("The maximum amount of RF that can be extracted from the Vis Capacitor per tick.")
        public int maxExtract = 20;
    }

    static {
        ConfigAnytime.register(ConfigHandlerTT.class);
    }
}
