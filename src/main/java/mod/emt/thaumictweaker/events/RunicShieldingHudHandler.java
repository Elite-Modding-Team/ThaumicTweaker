package mod.emt.thaumictweaker.events;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.events.PlayerEvents;

@SideOnly(Side.CLIENT)
public class RunicShieldingHudHandler {
    @SubscribeEvent
    public void RenderRunicShielding(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        EntityPlayerSP player = mc.player;

        if(player == null) {
            return;
        }

        if(!mc.gameSettings.hideGUI && mc.playerController.gameIsSurvivalOrAdventure()) {
            if(event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
                renderRunicArmorBar(mc, scaledResolution, player);
            }
        }
    }

    private void renderRunicArmorBar(Minecraft mc, ScaledResolution scaledResolution, EntityPlayer player) {
        float total = this.getTotalRunicArmor(player);
        float current = (float) RunicShieldingHandler.getRunicShielding(player);
        boolean drawFull = !ConfigTweaksTT.runic_shielding.newRunicShielding;

        if(total > 0 && current > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.matrixMode(GL11.GL_PROJECTION);
            GlStateManager.loadIdentity();
            GlStateManager.ortho(0.0D, scaledResolution.getScaledWidth_double(), scaledResolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
            GlStateManager.matrixMode(GL11.GL_MODELVIEW);
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, 0.0F, -2000.0F);
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.disableAlpha();

            int k = scaledResolution.getScaledWidth();
            int l = scaledResolution.getScaledHeight();

            GlStateManager.translate((float) (k / 2 - 91), (float) (l - 39), 0.0F);
            mc.renderEngine.bindTexture(ParticleEngine.particleTexture);
            GlStateManager.scale(4.0D, 4.0D, 4.0D);

            float hearts = Math.min(current, 10);
            this.drawOverlay(player.ticksExisted, drawFull ? 10.0f : hearts);

            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.popMatrix();
        }
        mc.renderEngine.bindTexture(Gui.ICONS);
    }

    private void drawOverlay(int ticks, float distance) {
        int a = 0;
        while((float) a < distance) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            UtilsFX.drawTexturedQuad((float) (a * 8) / 4, 0, (float) 160 / 4, (float) 16 / 4, (float) 9 / 4, (float) 9 / 4, -90.0);
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5D, 0.5D, 0.5D);
            GlStateManager.color(1.0f, 0.75f, 0.24f, (MathHelper.sin(((float) ticks / 4.0f + (float) a)) * 0.4f + 0.6f));
            UtilsFX.drawTexturedQuad((float) (a * 16) / 4, 0, (float) (a * 16) / 4, (float) 96 / 4, (float) 16 / 4, (float) 16 / 4, -90.0);
            GlStateManager.popMatrix();
            ++a;
        }
    }

    private int getTotalRunicArmor(EntityPlayer player) {
        int total = 0;
        for(ItemStack armor : player.inventory.armorInventory) {
            total += PlayerEvents.getRunicCharge(armor);
        }

        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);

        for(int i = 0; i < baubles.getSlots(); i++) {
            total += PlayerEvents.getRunicCharge(baubles.getStackInSlot(i));
        }
        return total;
    }
}
