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
    @Config.Name("Enable Focus Effects")
    @Config.Comment("Revamps the spell cast sounds of certain focus effects for better variety.")
    public static boolean enableFocusEffects = true;

    @Config.Name("Enable Goggled Armor")
    @Config.Comment("Allows the Goggles of Revealing to be attached to the Thaumium and Void Metal Helmets with infusion.")
    public static boolean enableGoggledArmor = true;

    @Config.Name("Enable Arcane Pedestal Comparator")
    @Config.Comment("Adds comparator output values to the Arcane Pedestal.")
    public static boolean enablePedestalComparator = true;

    @Config.Name("Enable Improved Commands")
    @Config.Comment({
            "Rewrites the `/thaumcraft` and `/tc` commands so they have additional features and support tab",
            "completion. The commands themselves are unchanged."
    })
    public static boolean enableImprovedCommands = true;

    @Config.Name("Enable Research Aid Tooltips")
    @Config.Comment("Adds tooltips to items that can be used as research aids.")
    public static boolean enableResearchAidTooltips = true;

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

    @Config.Name("Inventory Scanning")
    public static InventoryScanningCategory inventoryScanning = new InventoryScanningCategory();

    @Config.Name("Suppress Creative Warp Events")
    @Config.Comment("Suppresses Warp Events for players in Creative mode.")
    public static boolean suppressCreativeWarpEvents = true;

    @Config.RequiresMcRestart
    @Config.Name("Warding Effect")
    @Config.Comment("Adds an effect on top of a player's heath bar whenever Runic Warding is active.")
    public static boolean wardingEffect = true;

    public static class InventoryScanningCategory {
        @Config.RequiresMcRestart
        @Config.Name("Enable Inventory Scanning")
        @Config.Comment({
                "Enables Thaumcraft inventory scanning when hovering over an inventory item while holding a Thaumometer",
                "in the cursor. This feature will automatically disable itself when the Thaumic Inventory Scanning mod",
                "is loaded."
        })
        public boolean enableInventoryScanning = true;

        @Config.Name("Render Aspects")
        @Config.Comment("Renders item aspects when hovering over previously scanned items with the Thaumometer in the cursor.")
        public boolean renderAspects = true;

        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Scan Time")
        @Config.Comment("The amount of time, in ticks, it takes to scan an item with inventory scanning.")
        public int timeToScan = 30;
    }

    static {
        ConfigAnytime.register(ConfigEnhancementsTT.class);
    }
}
