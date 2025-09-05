package mod.emt.thaumictweaker.config;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.generics.GolemMaterialCategory;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config." + ThaumicTweaker.MOD_ID + ":tweaks")
@Config(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_ID + "/" + ThaumicTweaker.MOD_NAME + " - Tweaks"
)
public class ConfigTweaksTT {
    @Config.Name("Apprentice's Ring Tweaks")
    public static ApprenticesRingCategory apprentices_ring = new ApprenticesRingCategory();
    @Config.Name("Crimson Cult Robes Tweaks")
    public static CrimsonCultRobesCategory cult_robes = new CrimsonCultRobesCategory();
    @Config.Name("Curiosity Tweaks")
    public static CuriosityTweaksCategory curiosity_tweaks = new CuriosityTweaksCategory();
    @Config.Name("Flux Pollution Tweaks")
    public static FluxPollutionCategory flux_pollution = new FluxPollutionCategory();
    @Config.Name("Golem Tweaks")
    public static GolemTweaksCategory golem_tweaks = new GolemTweaksCategory();
    @Config.Name("Miscellaneous Tweaks")
    public static MiscTweaksCategory misc_tweaks = new MiscTweaksCategory();
    @Config.Name("Pech Tweaks")
    public static PechCategory pech_tweaks = new PechCategory();
    @Config.Name("Porous Stone Tweaks")
    public static PorousStoneCategory porous_stone = new PorousStoneCategory();
    @Config.Name("Primal Crusher Tweaks")
    public static PrimalCrusherCategory primal_crusher = new PrimalCrusherCategory();
    @Config.Name("Reseearch Table Tweaks")
    public static ResearchTableCategory research_table = new ResearchTableCategory();
    @Config.Name("Vis Generator Tweaks")
    public static VisGeneratorCategory vis_generator = new VisGeneratorCategory();
    @Config.Name("Void Thaumaturge Robes Tweaks")
    public static VoidRobesCategory void_robes = new VoidRobesCategory();
    @Config.Name("Void Siphon Tweaks")
    public static VoidSiphonCategory void_siphon = new VoidSiphonCategory();

    public static class ApprenticesRingCategory {
        @Config.RequiresMcRestart
        @Config.Name("Apprentice's Ring Structure Loot")
        @Config.Comment("Allows the Apprentice's Ring to be rarely found in some vanilla structure chests.")
        public boolean enableStructureLoot = false;

        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Apprentice's Ring Vis Discount")
        @Config.Comment("The Vis discount granted by the Apprentice's Ring.")
        public int visDiscount = 5;
    }

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
        @Config.Comment("The chance for an Eldritch Guardian to drop an Eldritch Curiosity.")
        public double curioDropEldritchGuardian = 0.0D;

        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.Name("Giant Taint Seed Curiosity Chance")
        @Config.Comment("The chance for a Giant Taint Seed to drop a Twisted Curiosity.")
        public double curioDropGiantTaintSeed = 0.0D;
    }

    public static class FluxPollutionCategory {
        @Config.RangeInt(min = 1, max = 10000)
        @Config.Name("Essentia Mirror Instability Decay Rate")
        @Config.Comment("The time, in ticks, between each instability decay operation of the Essentia Mirror. Smaller values will result in less flux.")
        public int essentiaMirrorInstabilityDecay = 100;

        @Config.RangeInt(min = 1, max = 10000)
        @Config.Name("Mirror Instability Decay Rate")
        @Config.Comment("The time, in ticks, between each instability decay operation of the Mirror. Smaller values will result in less flux.")
        public int mirrorInstabilityDecay = 100;
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
        @Config.Name("Alchemical Brass Requires Copper")
        @Config.Comment("Enables alchemical brass ingots to require copper instead of iron to create.")
        public boolean alchemicalBrassCopperRecipe = false;

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

        @Config.RequiresMcRestart
        @Config.Name("Legacy Thaumometer Scanning")
        @Config.Comment("Restores the original scanning functionality from 1.7.10.")
        public boolean legacyThaumometerScanning = false;

        @Config.Name("Sky Scan Dimensions")
        @Config.Comment("A list of dimension ids where the Thaumometer can be used to scan the sky to obtain research notes.")
        public int[] skyDimensions = new int[] {};
    }

    public static class PechCategory {
        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Item Value Divisor")
        @Config.Comment("")
        public int itemValueDivisor = 2;

        @Config.RangeInt(min = 1, max = 500)
        @Config.Name("Max Trade Value")
        @Config.Comment("The maximum trade value any item can have when offered to a Pech trader.")
        public int maxTradeValue = 32;
    }

    public static class PorousStoneCategory {
        @Config.RequiresMcRestart
        @Config.Name("Enable Porous Stone Tweaks")
        @Config.Comment("Enable Porous Stone drop tweaks. CraftTweaker and GroovyScript methods require this option set to true.")
        public boolean enablePorousStoneTweaks = true;

        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Gravel Drop Modifier")
        @Config.Comment("The chance that gravel will drop when harvesting porous stone. Special item drops have a ((1 + fortune) / gravelModifier) percent chance of occurring.")
        public int gravelDropModifier = 15;

        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Special Item Drops")
        @Config.Comment("The number of items dropped when a special item drop occurs.")
        public int specialItemDrops = 1;
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

    public static class ResearchTableCategory {
        @Config.Name("Research Item Pulling")
        @Config.Comment("Allows the Research Table to use and consume items from nearby inventories for research. Table will search up to 3 blocks away from the Research Table for inventories.")
        public boolean researchTablePulling = false;
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













}
