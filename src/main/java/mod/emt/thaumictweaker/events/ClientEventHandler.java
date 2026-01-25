package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IGoggles;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.TheorycraftManager;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID, value = Side.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if(ConfigEnhancementsTT.enableResearchAidTooltips) {
            for(String aidKey : TheorycraftManager.aids.keySet()) {
                ITheorycraftAid aid = TheorycraftManager.aids.get(aidKey);
                if(aid.getAidObject() instanceof Block) {
                    Block block = Block.getBlockFromItem(stack.getItem());
                    if(block == aid.getAidObject()) {
                        event.getToolTip().add(I18n.format("tooltip.thaumictweaker:research_aid.tooltip"));
                        return;
                    }
                } else if(aid.getAidObject() instanceof ItemStack) {
                    if(ItemStack.areItemsEqualIgnoreDurability(stack, (ItemStack) aid.getAidObject())) {
                        event.getToolTip().add(I18n.format("tooltip.thaumictweaker:research_aid.tooltip"));
                        return;
                    }
                }
            }
        }

        if(ConfigEnhancementsTT.enableRevealingTooltip) {
            if(stack.getItem() instanceof IGoggles && ((IGoggles) stack.getItem()).showIngamePopups(stack, event.getEntityPlayer())) {
                boolean addTooltip = true;
                String revealing = I18n.format("item.goggles.name");
                for(int i = 1; i < event.getToolTip().size(); i++) {
                    String tooltip = event.getToolTip().get(i);
                    if(tooltip.contains(revealing)) {
                        addTooltip = false;
                        break;
                    }
                }
                if(addTooltip) {
                    if(event.getToolTip().size() == 1) {
                        event.getToolTip().add(TextFormatting.DARK_PURPLE + revealing);
                    } else {
                        event.getToolTip().add(1, TextFormatting.DARK_PURPLE + revealing);
                    }
                }
            }
        }
    }
}
