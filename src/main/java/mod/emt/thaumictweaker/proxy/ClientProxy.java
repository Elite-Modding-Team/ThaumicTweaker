package mod.emt.thaumictweaker.proxy;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import mod.emt.thaumictweaker.events.InventoryScanningHandler;
import mod.emt.thaumictweaker.events.RunicShieldingHudHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
        if(ConfigEnhancementsTT.inventoryScanning.enableInventoryScanning)
            MinecraftForge.EVENT_BUS.register(InventoryScanningHandler.class);
        if(ConfigEnhancementsTT.wardingEffect)
            MinecraftForge.EVENT_BUS.register(new RunicShieldingHudHandler());
    }
}
