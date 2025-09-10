package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;

//@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID)
public class RenderHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void renderRunicOnTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            UtilsFX.sysPartialTicks = event.renderTickTime;
        } else {
            Minecraft mc = FMLClientHandler.instance().getClient();

            if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();
                long time = System.currentTimeMillis();

                if (player != null && mc.inGameHasFocus && !player.isCreative() && Minecraft.isGuiEnabled() /*&& PlayerRunicEvents.hasRunicHP(player) && PlayerRunicEvents.getRunicHP(player) > 0*/) {
                    renderRunicArmorBar(mc, event.renderTickTime, player, time);
                }
            }
        }
    }

    @SideOnly(value = Side.CLIENT)
    static void renderRunicArmorBar(Minecraft mc, float partialTicks, EntityPlayer player, long time) {
        //float total = (float)PlayerRunicEvents.getMaxRunic(player); // needs to be changed to reference player value for total runic armor value
        //float current = PlayerRunicEvents.getRunicHP(player);	// needs to be changed to reference player value for current runic armor value

        GlStateManager.pushMatrix();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        GlStateManager.clear(256);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.glDisableClientState(2929);
        GlStateManager.depthMask(false);
        GlStateManager.glEnableClientState(3042);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.glDisableClientState(3008); // TODO: Check if this line is needed

        int k = sr.getScaledWidth();
        int l = sr.getScaledHeight();

        GlStateManager.translate((float) (k / 2 - 91), (float) (l - 39), 0.0F);
        mc.renderEngine.bindTexture(ParticleEngine.particleTexture);
        GlStateManager.scale(4.0D, 4.0D, 4.0D);

        //float fill = current / total;
        int a = 0;

        /*while ((float) a < fill * 10.0f) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            UtilsFX.drawTexturedQuad(a * 8 / 4, 0, 160 / 4, 16 / 4, 9 / 4, 9 / 4, -90.0);
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5D, 0.5D, 0.5D);
            GlStateManager.color(1.0f, 0.75f, 0.24f, (MathHelper.sin(((float) player.ticksExisted / 4.0f + (float) a)) * 0.4f + 0.6f));
            UtilsFX.drawTexturedQuad(a * 16 / 4, 0, a * 16 / 4, 96 / 4, 16 / 4, 16 / 4, -90.0);
            GlStateManager.popMatrix();
            ++a;
        }*/

        GlStateManager.depthMask(true);
        GlStateManager.glEnableClientState(2929);
        GlStateManager.glDisableClientState(3042);
        GlStateManager.glEnableClientState(3008); // TODO: Check if this line is needed
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
    }
}
