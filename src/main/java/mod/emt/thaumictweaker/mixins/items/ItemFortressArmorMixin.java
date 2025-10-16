package mod.emt.thaumictweaker.mixins.items;

import com.google.common.collect.Multimap;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.armor.ItemFortressArmor;

import java.util.UUID;

@Mixin(value = ItemFortressArmor.class, remap = false)
public abstract class ItemFortressArmorMixin extends ItemArmor {
    public ItemFortressArmorMixin(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public @NotNull Multimap<String, AttributeModifier> getAttributeModifiers(@NotNull EntityEquipmentSlot slot, @NotNull ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if(ConfigTweaksTT.fortress_armor.enableKnockbackResistance) {
            UUID uuid = new UUID((getTranslationKey(stack) + slot).hashCode(), 0);
            if ((stack.getItem() == ItemsTC.fortressHelm && slot == EntityEquipmentSlot.HEAD) || (stack.getItem() == ItemsTC.fortressLegs && slot == EntityEquipmentSlot.LEGS)) {
                multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(uuid, "Armor modifier", 0.3, 0));
            } else if (stack.getItem() == ItemsTC.fortressChest && slot == EntityEquipmentSlot.CHEST) {
                multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(uuid, "Armor modifier", 0.4, 0));
            }
        }
        return multimap;
    }
}
