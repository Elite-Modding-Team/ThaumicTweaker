package mod.emt.thaumictweaker.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import thaumcraft.api.ThaumcraftInvHelper.InvFilter;
import thaumcraft.common.lib.utils.InventoryUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class InventoryHelper {
    public static Set<IItemHandler> getNearbyInventories(World world, BlockPos origin, int searchRadius) {
        Set<IItemHandler> handlers = new HashSet<>();
        for(int x = -searchRadius; x <= searchRadius; x++) {
            for(int y = -searchRadius; y <= searchRadius; y++) {
                for(int z = -searchRadius; z <= searchRadius; z++) {
                    if(x == 0 && y == 0 && z == 0)
                        continue;

                    BlockPos checkPos = origin.add(x, y, z);
                    TileEntity tile = world.getTileEntity(checkPos);
                    if(tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
                        handlers.add(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
                    }
                }
            }
        }
        //Thaumcraft is 'tarded and returns null capabilities after successful hasCapability check.
        handlers.removeIf(Objects::isNull);
        return handlers;
    }

    public static ItemStack pullFromNearbyInventories(World world, BlockPos origin, int searchRadius, ItemStack stack, boolean oreDict, boolean simulate) {
        if(stack.isEmpty() || searchRadius <= 0)
            return stack;

        ItemStack copy = stack.copy();
        int count = copy.getCount();
        InvFilter filter = new InvFilter(false, !stack.hasTagCompound(), oreDict, false).setRelaxedNBT();
        for(IItemHandler handler : getNearbyInventories(world, origin, searchRadius)) {
            for(int slot = 0; slot < handler.getSlots(); slot++) {
                ItemStack slotStack = handler.getStackInSlot(slot);
                //Thaumcraft checks are dumb. Make sure the first item stack is the one primary stack with the second stack being the one compared against it.
                if (!slotStack.isEmpty() && (InventoryUtils.checkEnchantedPlaceholder(slotStack, stack) || InventoryUtils.areItemStacksEqual(slotStack, stack, filter))) {
                    ItemStack extracted = handler.extractItem(slot, count, simulate);
                    count -= extracted.getCount();
                    if(count <= 0)
                        break;
                }
            }
        }
        if(count <= 0) {
            return copy;
        } else {
            if(count >= stack.getCount()) {
                return ItemStack.EMPTY;
            } else {
                copy.setCount(copy.getCount() - count);
                return copy;
            }
        }
    }
}
