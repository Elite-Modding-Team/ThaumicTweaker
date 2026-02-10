package mod.emt.thaumictweaker.mixins.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiScreen.class)
public interface GuiScreenAccessor {

    @Invoker("renderToolTip")
    void invokeRenderToolTip(ItemStack stack, int x, int y);
}
