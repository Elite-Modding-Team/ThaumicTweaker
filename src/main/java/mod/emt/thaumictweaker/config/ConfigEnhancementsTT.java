package mod.emt.thaumictweaker.config;

import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config." + ThaumicTweaker.MOD_ID + ":enhancements")
/* TODO: Remove before release
@Config(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_ID + "/" + ThaumicTweaker.MOD_ID + " - Enhancements"
)
 */
public class ConfigEnhancementsTT {
    @Config.Name("Enable Focus Effects")
    @Config.Comment("Revamps the spell cast sounds of certain focus effects for better variety.")
    public static boolean enableFocusEffects = true;

    @Config.Name("Eldritch Crab Full Death Rotation")
    @Config.Comment("Makes Eldritch Crabs fully rotate on death to match the death animations of spiders, silverfish and endermites.")
    public static boolean eldritchCrabFullDeathRotation = true;
}
