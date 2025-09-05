package mod.emt.thaumictweaker.config;

import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config." + ThaumicTweaker.MOD_ID + ":enhancements")
@Config(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_ID + "/" + ThaumicTweaker.MOD_NAME + " - Enhancements"
)
public class ConfigEnhancementsTT {
    @Config.Name("Adjust Arcane Bore Gui")
    @Config.Comment("Adjusts the Arcane Bore Gui position to match other inventories.")
    public static boolean adjustArcaneBoreGui = true;

    @Config.Name("Adjust Pech Trade Gui")
    @Config.Comment("Adjusts the Pech Trading Gui position to match other inventories.")
    public static boolean adjustPechGui = true;

    @Config.Name("Adjust Automated Crossbow Gui")
    @Config.Comment("Adjusts the Automated Crossbow and Advanced Automated Crossbow Gui position to match other inventories.")
    public static boolean adjustTurretGui = true;

    @Config.Name("Enable Focus Effects")
    @Config.Comment("Revamps the spell cast sounds of certain focus effects for better variety.")
    public static boolean enableFocusEffects = true;

    @Config.Name("Eldritch Crab Full Death Rotation")
    @Config.Comment("Makes Eldritch Crabs fully rotate on death to match the death animations of spiders, silverfish and endermites.")
    public static boolean eldritchCrabFullDeathRotation = true;
}
