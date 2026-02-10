package mod.emt.thaumictweaker.network;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.network.packets.HandlerScanSelf;import mod.emt.thaumictweaker.network.packets.HandlerScanSlot;import mod.emt.thaumictweaker.network.packets.MessageScanSelf;import mod.emt.thaumictweaker.network.packets.MessageScanSlot;import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandlerTT {
    public static SimpleNetworkWrapper INSTANCE;

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(HandlerScanSlot.class, MessageScanSlot.class, id++, Side.SERVER);
        INSTANCE.registerMessage(HandlerScanSelf.class, MessageScanSelf.class, id++, Side.SERVER);
    }

    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ThaumicTweaker.MOD_ID);
    }
}
