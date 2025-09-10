package mod.emt.thaumictweaker.proxy;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import mod.emt.thaumictweaker.events.RunicShieldingHudHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        if(ConfigEnhancementsTT.wardingEffect) {
            MinecraftForge.EVENT_BUS.register(new RunicShieldingHudHandler());
        }
    }
}
