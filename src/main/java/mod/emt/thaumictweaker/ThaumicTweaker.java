package mod.emt.thaumictweaker;

import mod.emt.thaumictweaker.commands.CommandBaseTT;
import mod.emt.thaumictweaker.proxy.CommonProxy;
import mod.emt.thaumictweaker.util.helpers.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_NAME,
        version = ThaumicTweaker.VERSION,
        acceptedMinecraftVersions = ThaumicTweaker.MC_VERSION,
        dependencies = ThaumicTweaker.DEPENDENCIES
)
public class ThaumicTweaker {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.VERSION;
    public static final String MC_VERSION = "[1.12.2]";
    public static final String DEPENDENCIES = "required-after:thaumcraft" +
            ";required-after:mixinbooter@[10.2,)" +
            ";required-after:configanytime@[3.0,)";

    public static final String CLIENT_PROXY = "mod.emt." + MOD_ID + ".proxy.ClientProxy";
    public static final String COMMON_PROXY = "mod.emt." + MOD_ID + ".proxy.CommonProxy";

    @Mod.Instance(MOD_ID)
    public static ThaumicTweaker instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Starting " + MOD_NAME);
        proxy.preInit();
        LogHelper.debug("Finished preInit phase.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        LogHelper.debug("Finished init phase.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
        LogHelper.debug("Finished postInit phase.");
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandBaseTT());
    }
}
