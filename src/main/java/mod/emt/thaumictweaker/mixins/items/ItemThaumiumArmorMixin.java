package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.items.IGoggles;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.armor.ItemThaumiumArmor;

import java.util.List;

@Mixin(value = ItemThaumiumArmor.class, remap = false)
public abstract class ItemThaumiumArmorMixin extends ItemArmor implements IGoggles {
    public ItemThaumiumArmorMixin(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
    }

    @ModifyReturnValue(method = "getArmorTexture", at = @At("RETURN"))
    private String getGoggledArmorTextureMixin(String original, @Local(argsOnly = true) ItemStack stack) {
        if(this.thaumicTweaker$isRevealing(stack)) {
            return ThaumicTweaker.MOD_ID + ":" + "textures/models/armor/revealing_thaumium_helmet.png";
        }
        return original;
    }

    @Override
    public boolean showIngamePopups(ItemStack stack, EntityLivingBase entityLivingBase) {
        return this.thaumicTweaker$isRevealing(stack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flagIn) {
        if(this.thaumicTweaker$isRevealing(stack)) {
            tooltip.add(TextFormatting.DARK_PURPLE + I18n.format("item.goggles.name"));
        }
    }

    @Unique
    private boolean thaumicTweaker$isRevealing(ItemStack stack) {
        return ConfigEnhancementsTT.enableGoggledArmor
                && stack.getItem() == ItemsTC.thaumiumHelm
                && stack.getTagCompound() != null
                && stack.getTagCompound().hasKey("goggles");
    }
}
