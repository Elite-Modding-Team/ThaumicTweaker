package mod.emt.thaumictweaker.mixins.containers;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.client.gui.GuiPech;
import thaumcraft.common.entities.monster.EntityPech;

@SideOnly(Side.CLIENT)
@Mixin(GuiPech.class)
public abstract class GuiPechMixin extends GuiContainer {
    public GuiPechMixin(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    /**
     * @author Invadermonky
     * @reason Fixes item shader issue with Pech Trading
     */
    @Redirect(method = "drawGuiContainerBackgroundLayer", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", remap = false))
    private void gl11ColorMixin(float red, float green, float blue, float alpha) {
        GlStateManager.color(red, green, blue, alpha);
    }

    /**
     * @author Invadermonky
     * @reason Fixes item shader issue with Pech Trading
     */
    @Redirect(method = "drawGuiContainerBackgroundLayer", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", remap = false))
    private void glEnableBlendMixin(int cap) {
        GlStateManager.enableBlend();
    }

    /**
     * @author Invadermonky
     * @reason Fixes item shader issue with Pech Trading
     */
    @Redirect(method = "drawGuiContainerBackgroundLayer", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", remap = false))
    private void glDisableBlendMixin(int cap) {
        GlStateManager.disableBlend();
    }
}
