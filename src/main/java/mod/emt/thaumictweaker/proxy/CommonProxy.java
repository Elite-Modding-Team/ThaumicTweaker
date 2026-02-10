package mod.emt.thaumictweaker.proxy;

import mod.emt.thaumictweaker.compat.crafttweaker.CrafttweakerTT;
import mod.emt.thaumictweaker.config.ConfigOverhaulsTT;
import mod.emt.thaumictweaker.events.ChampionMobHandler;
import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import mod.emt.thaumictweaker.network.PacketHandlerTT;
import mod.emt.thaumictweaker.util.libs.ModIds;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit() {
        if(ModIds.crafttweaker.isLoaded) MinecraftForge.EVENT_BUS.register(new CrafttweakerTT());

        if(ConfigOverhaulsTT.championMobOverhaul) MinecraftForge.EVENT_BUS.register(new ChampionMobHandler());
        if(ConfigOverhaulsTT.runicShieldingOverhaul) MinecraftForge.EVENT_BUS.register(new RunicShieldingHandler());
    }

    public void init() {
        PacketHandlerTT.init();
    }

    public void postInit() {}
}
