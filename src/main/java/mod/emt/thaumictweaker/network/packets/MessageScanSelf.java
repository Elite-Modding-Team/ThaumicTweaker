package mod.emt.thaumictweaker.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thaumcraft.api.research.ScanningManager;

public class MessageScanSelf implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class MessageHandler implements IMessageHandler<MessageScanSelf, IMessage> {
        @Override
        public IMessage onMessage(MessageScanSelf message, MessageContext ctx) {
            EntityPlayer entityPlayer = ctx.getServerHandler().player;
            ScanningManager.scanTheThing(entityPlayer, entityPlayer);
            return null;
        }
    }
}