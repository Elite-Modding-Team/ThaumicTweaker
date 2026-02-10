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
import org.lwjgl.opengl.GL12;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.common.lib.SoundsTC;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// TODO: Need to clean this up at some point
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID, value = Side.CLIENT)
public class InventoryScanningHandler {
    private static final int SCAN_TICKS = 25;
    private static final int SOUND_TICKS = 3;

    private static final int INVENTORY_PLAYER_X = 26;
    private static final int INVENTORY_PLAYER_Y = 8;
    private static final int INVENTORY_PLAYER_WIDTH = 52;
    private static final int INVENTORY_PLAYER_HEIGHT = 70;

    private static boolean missingMessageSent;

    private static Slot mouseSlot;
    private static Slot lastScannedSlot;
    private static int ticksHovered;
    private static Object currentScan;
    private static boolean isHoveringPlayer;

    private static boolean isHoldingThaumometer() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) {
            return false;
        }

        ItemStack mouseItem = mc.player.inventory.getItemStack();
        return !mouseItem.isEmpty() && mouseItem.getItem() == ItemsTC.thaumometer;
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer entityPlayer = mc.player;
        if (entityPlayer != null) {
            if (!missingMessageSent) {
                //entityPlayer.sendStatusMessage(new TextComponentTranslation("tcinventoryscan:serverNotInstalled"), false);
                missingMessageSent = true;
            }
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
    public static void onTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() == ItemsTC.thaumometer) {
            event.getToolTip().add(TextFormatting.GOLD + I18n.format("misc.thaumictweaker.scanning_available"));
            if (GuiScreen.isShiftKeyDown()) {
                String[] lines = I18n.format("misc.thaumictweaker.scanning_description").split("\\\\n");
                for (String line : lines) {
                    event.getToolTip().add(TextFormatting.DARK_AQUA + line);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTooltipPostText(RenderTooltipEvent.PostText event) {
        if (isHoldingThaumometer() && !GuiScreen.isShiftKeyDown()) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.currentScreen instanceof GuiContainer && !ScanningManager.isThingStillScannable(mc.player, event.getStack())) {
                renderAspectsInGui((GuiContainer) mc.currentScreen, mc.player, event.getStack(), 0, event.getX(), event.getY());
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

    private static boolean renderAspectsInGuiHasErrored;
    private static Object hudHandlerInstance;
    private static Method renderAspectsInGuiMethod;

    @SuppressWarnings("unchecked")
    private static void renderAspectsInGui(GuiContainer guiContainer, EntityPlayer player, ItemStack itemStack, int d, int x, int y) {
        if (renderAspectsInGuiHasErrored) {
            return;
        }

        if (hudHandlerInstance == null) {
            try {
                Class renderEventHandler = Class.forName("thaumcraft.client.lib.events.RenderEventHandler");
                Object instance = renderEventHandler.getField("INSTANCE").get(null);
                hudHandlerInstance = renderEventHandler.getField("hudHandler").get(instance);
                Class hudHandler = Class.forName("thaumcraft.client.lib.events.HudHandler");
                renderAspectsInGuiMethod = hudHandler.getMethod("renderAspectsInGui", GuiContainer.class, EntityPlayer.class, ItemStack.class, int.class, int.class, int.class);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException e) {
                renderAspectsInGuiHasErrored = true;
                e.printStackTrace();
                return;
            }
        }

        try {
            renderAspectsInGuiMethod.invoke(hudHandlerInstance, guiContainer, player, itemStack, d, x, y);
        } catch (IllegalAccessException | InvocationTargetException e) {
            renderAspectsInGuiHasErrored = true;
            e.printStackTrace();
        }
    }

    private static boolean drawTagHasErrored;
    private static Method drawTagMethod;

    @SuppressWarnings("unchecked")
    private static void drawTag(int x, int y, Aspect aspect, float amount, int bonus, double zLevel) {
        if (drawTagHasErrored) {
            return;
        }

        if (drawTagMethod == null) {
            try {
                Class utilsFX = Class.forName("thaumcraft.client.lib.UtilsFX");
                drawTagMethod = utilsFX.getMethod("drawTag", int.class, int.class, Aspect.class, float.class, int.class, double.class);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                drawTagHasErrored = true;
                e.printStackTrace();
                return;
            }
        }

        try {
            drawTagMethod.invoke(null, x, y, aspect, amount, bonus, zLevel);
        } catch (IllegalAccessException | InvocationTargetException e) {
            drawTagHasErrored = true;
            e.printStackTrace();
        }
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
                    drawTag(x, y, aspect, aspectList.getAmount(aspect), 0, gui.zLevel);
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
        sb.append(I18n.format("misc.thaumictweaker.scanning"));
        if (progress >= 0.75f) {
            sb.append("...");
        } else if (progress >= 0.5f) {
            sb.append("..");
        } else if (progress >= 0.25f) {
            sb.append(".");
        }
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        float oldZLevel = gui.zLevel;
        gui.zLevel = 300;
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(sb.toString(), mouseX, mouseY - 30, 0xFFFFFFFF);
        gui.zLevel = oldZLevel;
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }

    private static boolean isHoveringPlayer(GuiContainer gui, int mouseX, int mouseY) {
        return gui instanceof GuiInventory && mouseX >= gui.getGuiLeft() + INVENTORY_PLAYER_X && mouseX < gui.getGuiLeft() + INVENTORY_PLAYER_X + INVENTORY_PLAYER_WIDTH && mouseY >= gui.getGuiTop() + INVENTORY_PLAYER_Y && mouseY < gui.getGuiTop() + INVENTORY_PLAYER_Y + INVENTORY_PLAYER_HEIGHT;
    }
}
