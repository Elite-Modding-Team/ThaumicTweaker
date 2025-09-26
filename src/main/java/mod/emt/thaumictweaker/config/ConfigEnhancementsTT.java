package mod.emt.thaumictweaker.config;

import com.cleanroommc.configanytime.ConfigAnytime;
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

    @Config.Name("Enable Research Subtitles")
    @Config.Comment
            ({
                    "Enables support for additional subtitle text located underneath the research name in the Thaumonomicon.",
                    "If no subtitle text is found nothing will be shown.",
                    "Should you want to add your own text, you can add the translation key to a .lang file. The translation",
                    "key is normally the research title key ending with '.subtitle'. For example, the subtitle language key",
                    "for Warp research would be \"research.WARP.title.subtitle\"."
            })
    public static boolean enableResearchSubtitles = true;
    
    @Config.Name("Enable Warp Research Subtitles")
    @Config.Comment
    ({
            "Enables support for additional subtitle text for forbidden knowledge research entries.",
            "The subtitle will display how dangerous the warp for that research is."
    })
    public static boolean enableWarpResearchSubtitles = true;

    @Config.Name("Suppress Creative Warp Events")
    @Config.Comment("Suppresses Warp Events for players in Creative mode.")
    public static boolean suppressCreativeWarpEvents = true;

    @Config.RequiresMcRestart
    @Config.Name("Warding Effect")
    @Config.Comment("Adds an effect on top of a player's heath bar whenever Runic Warding is active.")
    public static boolean wardingEffect = true;

    static {
        ConfigAnytime.register(ConfigEnhancementsTT.class);
    }
}
