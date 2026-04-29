package mod.emt.thaumictweaker.mixins.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.items.tools.ItemVoidShovel;

import java.util.List;

@Mixin(value = ItemVoidShovel.class, remap = false)
public class ItemVoidShovelMixin extends ItemSpade {
    protected ItemVoidShovelMixin(ToolMaterial materialIn) {
        super(materialIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem() != newStack.getItem() || slotChanged;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@NotNull ItemStack stack, World world, List<String> tooltip, @NotNull ITooltipFlag flag) {
        tooltip.add(new TextComponentTranslation("enchantment.special.sapless").setStyle(new Style().setColor(TextFormatting.GOLD)).getFormattedText());
        super.addInformation(stack, world, tooltip, flag);
    }
}
