package mod.emt.thaumictweaker.mixins.entities;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.entities.monster.EntityPech;

@Mixin(value = EntityPech.class, remap = false)
public class EntityPechMixin {
    @ModifyConstant(method = "getValue", constant = @Constant(intValue = 32))
    private int modifyMaxItemValue(int constant) {
        return ConfigHandlerTT.pech_tweaks.maxTradeValue;
    }

    @ModifyConstant(method = "getValue", constant = @Constant(intValue = 2))
    private int modifyItemValueDivisor(int constant) {
        return ConfigHandlerTT.pech_tweaks.itemValueDivisor;
    }

}
