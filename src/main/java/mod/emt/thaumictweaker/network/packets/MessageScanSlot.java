package mod.emt.thaumictweaker.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thaumcraft.api.research.ScanningManager;

public class MessageScanSlot implements IMessage {
    public int slotNumber;

    public MessageScanSlot() {
    }

    public MessageScanSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        slotNumber = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(slotNumber);
    }

    public static class MessageHandler implements IMessageHandler<MessageScanSlot, IMessage> {
        @Override
        public IMessage onMessage(MessageScanSlot message, MessageContext ctx) {
            EntityPlayer entityPlayer = ctx.getServerHandler().player;
            Container container = entityPlayer.openContainer;
            if (container != null && message.slotNumber >= 0 && message.slotNumber < container.inventorySlots.size()) {
                Slot slot = container.inventorySlots.get(message.slotNumber);
                if (!slot.getStack().isEmpty() && slot.canTakeStack(entityPlayer) && !(slot instanceof SlotCrafting)) {
                    ItemStack itemStack = slot.getStack();
                    ScanningManager.scanTheThing(entityPlayer, itemStack);
                }
            }
            return null;
        }
    }
}