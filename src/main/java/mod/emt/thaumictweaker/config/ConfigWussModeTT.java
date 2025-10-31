package mod.emt.thaumictweaker.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config." + ThaumicTweaker.MOD_ID + ":wuss_mode")
@Config(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_ID + "/" + ThaumicTweaker.MOD_NAME + " - Wuss Mode"
)
public class ConfigWussModeTT {
    @Config.RequiresMcRestart
    @Config.Name("Cheaper Arcane Bore")
    @Config.Comment("Makes the Arcane Bore recipe cheaper.")
    public static boolean cheaperArcaneBore = false;

    @Config.RequiresMcRestart
    @Config.Name("Cheaper Bath Salts")
    @Config.Comment("Makes the Bath Salts recipe cheaper.")
    public static boolean cheaperBathSalts = false;

    @Config.RequiresMcRestart
    @Config.Name("Cheaper Sanitizing Soap")
    @Config.Comment("Makes the Sanitizing Soap recipe cheaper.")
    public static boolean cheaperSanitizingSoap = false;

    @Config.RequiresMcRestart
    @Config.Name("Cheaper Workbench Charger")
    @Config.Comment("Makes the Workbench Charger recipe cheaper.")
    public static boolean cheaperWorkbenchCharger = false;

    @Config.Name("Research Requirements")
    @Config.Comment("Toggles to enable or disable various research requirements.")
    public static WussModeResearch disableResearch = new WussModeResearch();

    @Config.Name("Prevent Eldritch Guardian Warp Gain")
    @Config.Comment("Prevents the player from gaining warp whenever an Eldritch Guardian spawns nearby in line of sight.")
    public static boolean preventEldritchGuardianWarpGain = false;

    @Config.RequiresMcRestart
    @Config.Name("Primordial Pearl Creation")
    @Config.Comment("Enables an infusion recipe to create 1 durability Primordial Pearls from void seeds.")
    public static boolean primordialPearlCreationRecipe = false;

    @Config.RequiresMcRestart
    @Config.Name("Primordial Pearl Growing")
    @Config.Comment("Enables an infusion enchantment recipe that can increase Primordial Pearl durability.")
    public static boolean primordialPearlGrowingRecipe = false;

    @Config.Name("Void Siphon Wuss Mode")
    @Config.Comment("The Void Siphon no longer needs nearby rifts to create void seeds. It will passively accumulate progress over time.")
    public static boolean wussModeVoidSiphon = false;

    public static class WussModeResearch {
        @Config.Name("Disable Crafting Requirements")
        @Config.Comment("Disables all Crafting requirements to progress research.")
        public boolean disableCraftsRequirements = false;
        @Config.Name("Disable Item Requirements")
        @Config.Comment("Disables all Item requirements to progress research.")
        public boolean disableItemRequirements = false;
        @Config.Name("Disable Knowledge Requirements")
        @Config.Comment("Disables all Knowledge/Observation requirements to progress research.")
        public boolean disableKnowledgeRequirements = false;
        @Config.Name("Disable Theory Requirements")
        @Config.Comment("Disables all Theory requirements to progress research.")
        public boolean disableTheoryRequirements = false;
    }

    static {
        ConfigAnytime.register(ConfigWussModeTT.class);
    }
}
