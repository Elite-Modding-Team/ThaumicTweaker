package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.mixins.client.gui.GuiScreenAccessor;
import mod.emt.thaumictweaker.network.PacketHandlerTT;
import mod.emt.thaumictweaker.network.packets.MessageScanSelf;
import mod.emt.thaumictweaker.network.packets.MessageScanSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.lib.events.RenderEventHandler;
import thaumcraft.common.lib.SoundsTC;

//TODO: Make this entire thing instanced so it can be toggled in the config
// TODO: Need to clean this up at some point
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID, value = Side.CLIENT)
public class InventoryScanningHandler {
    //TODO: Make the time configurable
    private static final int SCAN_TICKS = 25;
    private static final int SOUND_TICKS = 3;

    private static final int INVENTORY_PLAYER_X = 26;
    private static final int INVENTORY_PLAYER_Y = 8;
    private static final int INVENTORY_PLAYER_WIDTH = 52;
    private static final int INVENTORY_PLAYER_HEIGHT = 70;

    private static Slot mouseSlot;
    private static Slot lastScannedSlot;
    private static int ticksHovered;
    private static Object currentScan;
    private static boolean isHoveringPlayer;

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack hoverStack = event.getItemStack();
        if (hoverStack.getItem() == ItemsTC.thaumometer) {
            event.getToolTip().add(TextFormatting.GOLD + I18n.format("tooltip.thaumictweaker:scanning.info"));
            if (GuiScreen.isShiftKeyDown()) {
                String[] lines = I18n.format("tooltip.thaumictweaker:scanning.desc").split("\\\\n");
                for (String line : lines) {
                    event.getToolTip().add(TextFormatting.DARK_AQUA + line);
                }
            }
        }
        EntityPlayer player = event.getEntityPlayer();
        if(player != null && !hoverStack.isEmpty()) {
            ItemStack heldStack = event.getEntityPlayer().inventory.getItemStack();
            if(heldStack.getItem() == ItemsTC.thaumometer) {
                boolean isScannable = ScanningManager.isThingStillScannable(player, hoverStack);
                event.getToolTip().add(
                        (isScannable ? TextFormatting.GREEN : TextFormatting.RED) +
                        I18n.format("tooltip.thaumictweaker:scanning_available." + (isScannable ? "true" : "false"))
                );
            }
        }
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer entityPlayer = mc.player;
        if (entityPlayer != null) {
            return;
        }

        if (isHoldingThaumometer()) {
            if ((isHoveringPlayer && currentScan != null) || (mouseSlot != null && !mouseSlot.getStack().isEmpty() && mouseSlot.canTakeStack(entityPlayer) && mouseSlot != lastScannedSlot && !(mouseSlot instanceof SlotCrafting))) {
                ticksHovered++;

                if (currentScan == null) {
                    currentScan = mouseSlot.getStack();
                }

                if (ScanningManager.isThingStillScannable(entityPlayer, currentScan)) {
                    if (ticksHovered > SOUND_TICKS && ticksHovered % 4 == 0) {
                        entityPlayer.world.playSound(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, SoundsTC.scan, SoundCategory.NEUTRAL, 0.2f, 0.45f + entityPlayer.world.rand.nextFloat() * 0.1f, false);
                    }

                    if (ticksHovered >= SCAN_TICKS) {
                        if (currentScan instanceof EntityPlayer) {
                            PacketHandlerTT.INSTANCE.sendToServer(new MessageScanSelf());
                        } else {
                            PacketHandlerTT.INSTANCE.sendToServer(new MessageScanSlot(mouseSlot.slotNumber));
                        }
                        ticksHovered = 0;
                        lastScannedSlot = mouseSlot;
                        currentScan = null;
                    }
                } else {
                    currentScan = null;
                    lastScannedSlot = mouseSlot;
                }
            }
        } else {
            ticksHovered = 0;
            currentScan = null;
            lastScannedSlot = null;
        }
    }

    @SubscribeEvent
    public static void onTooltipPostText(RenderTooltipEvent.PostText event) {
        if (isHoldingThaumometer() && !GuiScreen.isShiftKeyDown()) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.currentScreen instanceof GuiContainer && !ScanningManager.isThingStillScannable(mc.player, event.getStack())) {
                RenderEventHandler.hudHandler.renderAspectsInGui((GuiContainer) mc.currentScreen, mc.player, event.getStack(), 0, event.getX(), event.getY());
            }
        }
    }

    @SubscribeEvent
    public static void onDrawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (event.getGui() instanceof GuiContainer && !(event.getGui() instanceof GuiContainerCreative)) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer entityPlayer = mc.player;
            boolean oldHoveringPlayer = isHoveringPlayer;
            isHoveringPlayer = isHoveringPlayer((GuiContainer) event.getGui(), event.getMouseX(), event.getMouseY());
            if (!isHoveringPlayer) {
                Slot oldMouseSlot = mouseSlot;
                mouseSlot = ((GuiContainer) event.getGui()).getSlotUnderMouse();
                if (oldMouseSlot != mouseSlot) {
                    ticksHovered = 0;
                    currentScan = null;
                }
            }
            if (oldHoveringPlayer != isHoveringPlayer) {
                ticksHovered = 0;
                if (isHoveringPlayer) {
                    currentScan = entityPlayer;
                    if (!ScanningManager.isThingStillScannable(entityPlayer, currentScan)) {
                        currentScan = null;
                    }
                }
            }

            ItemStack mouseItem = entityPlayer.inventory.getItemStack();
            if (!mouseItem.isEmpty() && mouseItem.getItem() == ItemsTC.thaumometer) {
                if (mouseSlot != null && !mouseSlot.getStack().isEmpty()) {
                    if (currentScan != null) {
                        renderScanningProgress(event.getGui(), event.getMouseX(), event.getMouseY(), ticksHovered / (float) SCAN_TICKS);
                    }
                    ((GuiScreenAccessor) event.getGui()).invokeRenderToolTip(mouseSlot.getStack(), event.getMouseX(), event.getMouseY());
                } else if (isHoveringPlayer) {
                    if (currentScan != null) {
                        renderScanningProgress(event.getGui(), event.getMouseX(), event.getMouseY(), ticksHovered / (float) SCAN_TICKS);
                    }
                    if (!ScanningManager.isThingStillScannable(entityPlayer, entityPlayer)) {
                        renderPlayerAspects(event.getGui(), event.getMouseX(), event.getMouseY());
                    }
                }
            }
        }
    }

    private static boolean isHoldingThaumometer() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) {
            return false;
        }

        ItemStack mouseItem = mc.player.inventory.getItemStack();
        return !mouseItem.isEmpty() && mouseItem.getItem() == ItemsTC.thaumometer;
    }

    private static void renderPlayerAspects(GuiScreen gui, int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        GlStateManager.color(1f, 1f, 1f, 1f);
        GL11.glPushAttrib(1048575);
        GlStateManager.disableLighting();
        int x = mouseX + 17;
        int y = mouseY + 7 - 33;
        EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
        AspectList aspectList = AspectHelper.getEntityAspects(entityPlayer);
        if (aspectList != null && aspectList.size() > 0) {
            GlStateManager.disableDepth();
            Aspect[] sortedAspects = aspectList.getAspectsSortedByAmount();
            for (Aspect aspect : sortedAspects) {
                if (aspect != null) {
                    UtilsFX.drawTag(x, y, aspect, aspectList.getAmount(aspect), 0, gui.zLevel);
                    x += 18;
                }
            }
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
        }
        GL11.glPopAttrib();
        GlStateManager.popMatrix();
    }

    private static void renderScanningProgress(GuiScreen gui, int mouseX, int mouseY, float progress) {
        StringBuilder sb = new StringBuilder("\u00a76");
        sb.append(I18n.format("tooltip.thaumictweaker:scanning"));
        if (progress >= 0.75f) {
            sb.append("...");
        } else if (progress >= 0.5f) {
            sb.append("..");
        } else if (progress >= 0.25f) {
            sb.append(".");
        }
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        float oldZLevel = gui.zLevel;
        gui.zLevel = 300;
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(sb.toString(), mouseX, mouseY - 30, 0xFFFFFFFF);
        gui.zLevel = oldZLevel;
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }

    private static boolean isHoveringPlayer(GuiContainer gui, int mouseX, int mouseY) {
        return gui instanceof GuiInventory
                && mouseX >= gui.getGuiLeft() + INVENTORY_PLAYER_X
                && mouseX < gui.getGuiLeft() + INVENTORY_PLAYER_X + INVENTORY_PLAYER_WIDTH
                && mouseY >= gui.getGuiTop() + INVENTORY_PLAYER_Y
                && mouseY < gui.getGuiTop() + INVENTORY_PLAYER_Y + INVENTORY_PLAYER_HEIGHT;
    }
}
