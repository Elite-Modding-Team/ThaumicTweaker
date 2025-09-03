package mod.emt.thaumictweaker.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.emt.thaumictweaker.config.generics.GolemMaterialCategory;
import net.minecraftforge.common.config.Config;

//TODO: Remove before release
// @Config(modid = ThaumicTweaker.MOD_ID)
public class ConfigHandlerTT {
    @Config.Name("Crimson Cult Robes Tweaks")
    public static CrimsonCultRobesCategory cult_robes = new CrimsonCultRobesCategory();
    @Config.Name("Curiosity Tweaks")
    public static CuriosityTweaksCategory curiosity_tweaks = new CuriosityTweaksCategory();
    @Config.Name("Golem Tweaks")
    public static GolemTweaksCategory golem_tweaks = new GolemTweaksCategory();
    @Config.Name("Miscellaneous Tweaks")
    public static MiscTweaksCategory misc_tweaks = new MiscTweaksCategory();
    @Config.Name("Primal Crusher Tweaks")
    public static PrimalCrusherCategory primal_crusher = new PrimalCrusherCategory();
    @Config.Name("Vis Generator Tweaks")
    public static VisGeneratorCategory vis_generator = new VisGeneratorCategory();
    @Config.Name("Void Thaumaturge Robes Tweaks")
    public static VoidRobesCategory void_robes = new VoidRobesCategory();
    @Config.Name("Void Siphon Tweaks")
    public static VoidSiphonCategory void_siphon = new VoidSiphonCategory();
    @Config.Name("Wuss Mode Tweaks")
    @Config.Comment("For those that want an easier time. NOTE: Wuss Mode does not need to be enabled for these tweaks to work.")
    public static WussModeCategory wuss_mode = new WussModeCategory();

    public static class CrimsonCultRobesCategory {
        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Helm Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Crimson Cult Hood.")
        public int visDiscountHelm = 1;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Robes Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Crimson Cult Robes.")
        public int visDiscountChest = 1;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Leggings Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Crimson Cult Leggings.")
        public int visDiscountLeggings = 1;
    }

    public static class CuriosityTweaksCategory {
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.Name("Eldritch Guardian Curiosity Chance")
        @Config.Comment("The chance for the Giant Taint Seed to drop an Eldritch Curiosity.")
        public double curioDropEldritchGuardian = 0.0D;

        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.Name("Giant Taint Seed Curiosity Chance")
        @Config.Comment("The chance for the Giant Taint Seed to drop a Twisted Curiosity.")
        public double curioDropGiantTaintSeed = 0.0D;
    }

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
        @Config.Comment("A list of dimension ids where the Thaumometer can be used to scan the sky to obtain research notes.")
        public int[] skyDimensions = new int[] {};
    }

    public static class PrimalCrusherCategory {
        @Config.RequiresMcRestart
        @Config.Name("Set Unbreakable")
        @Config.Comment("Makes the Primal Crusher unbreakable.")
        public boolean unbreakable = false;

        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1, max = 4)
        @Config.Name("Refining Level")
        @Config.Comment("The Refining infusion enchantment level added to the Primal Crusher by default")
        public int refiningLevel = 1;
    }

    public static class VisGeneratorCategory {
        @Config.RangeInt(min = 1, max = 10000000)
        @Config.Name("RF Capacity")
        @Config.Comment("The maximum amount of RF the Vis Capacitor can hold. This value also controls the amount of RF generated per point of Vis.")
        public int capacity = 1000;

        @Config.RangeInt(min = 1, max = 10000000)
        @Config.Name("Max RF Extraction")
        @Config.Comment("The maximum amount of RF that can be extracted from the Vis Capacitor per tick.")
        public int maxExtract = 20;
    }

    public static class VoidRobesCategory {
        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Helm Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Hood.")
        public int visDiscountHelm = 5;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Robes Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Robes.")
        public int visDiscountChest = 5;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Leggings Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Leggings.")
        public int visDiscountLeggings = 5;
    }

    public static class VoidSiphonCategory {
        @Config.RangeInt(min = 1, max = 100000)
        @Config.Name("Rift Power Required")
        @Config.Comment("Modifies the amount of rift power required to generate a Void Seed. Smaller values will increase the speed Void Seeds are created.")
        public int progressRequired = 2000;
    }

    public static class WussModeCategory {
        @Config.RequiresMcRestart
        @Config.Name("Arcane Bore Recipe")
        @Config.Comment("Makes the Arcane Bore recipe cheaper.")
        public boolean wussModeArcaneBoreRecipe = false;

        @Config.RequiresMcRestart
        @Config.Name("Primordial Pearl Recipe")
        @Config.Comment("Enables an infusion recipe to craft primordial pearls from void seeds.")
        public boolean wussModePrimordialPearlRecipe = false;

        @Config.Name("Void Siphon Wuss Mode")
        @Config.Comment("The Void Siphon no longer needs nearby rifts to create void seeds. It will passively accumulate progress over time.")
        public boolean wussModeVoidSiphon = false;
    }

    static {
        ConfigAnytime.register(ConfigHandlerTT.class);
    }
}
