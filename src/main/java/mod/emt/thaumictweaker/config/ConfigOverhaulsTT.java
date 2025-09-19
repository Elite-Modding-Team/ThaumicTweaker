package mod.emt.thaumictweaker.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config." + ThaumicTweaker.MOD_ID + ":overhauls")
@Config(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_ID + "/" + ThaumicTweaker.MOD_NAME + " - Overhauls"
)
public class ConfigOverhaulsTT {
    @Config.RequiresMcRestart
    @Config.Name("Loot Table Overhaul")
    @Config.Comment("Changes Thaumcraft mobs and bosses so loot is generated from loot tables instead of hardcoded drops.")
    public static boolean lootTableOverhaul = false;

    @Config.RequiresMcRestart
    @Config.Name("Runic Shielding Overhaul")
    @Config.Comment("Enables Runic Shielding overhaul, replacing absorption hearts with a new shield system.")
    public static boolean runicShieldingOverhaul = false;

    static {
        ConfigAnytime.register(ConfigTweaksTT.class);
    }
}
