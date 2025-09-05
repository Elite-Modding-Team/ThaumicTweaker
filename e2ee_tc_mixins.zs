#modloaded thaumcraft
#loader mixin

import mixin.CallbackInfo;
import mixin.CallbackInfoReturnable;
import native.java.util.ArrayList;
import native.net.minecraft.init.SoundEvents;
import native.net.minecraft.item.ItemStack;
import native.net.minecraft.util.SoundCategory;
import native.net.minecraft.world.DimensionType;
import native.net.minecraft.world.WorldProvider;
import native.net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import native.thaumcraft.api.golems.parts.GolemMaterial;
import native.thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
import native.thaumcraft.common.lib.utils.Utils;

#mixin {targets: "thaumcraft.common.blocks.misc.BlockFluidDeath"}
zenClass MixinBlockFluidDeath {
    #mixin ModifyArg
    #{
    #    method: "func_180634_a",
    #    at: {
    #        value: "INVOKE",
    #        target: "Lnet/minecraft/entity/Entity;func_70097_a(Lnet/minecraft/util/DamageSource;F)Z"
    #    }
    #}
    function increaseDamage(value as float) as float {
        return value * 5.0f + 15.0f;
    }
}

#mixin {targets: "thaumcraft.common.tiles.devices.TileLampFertility"}
zenClass MixinTileLampFertility {
    #mixin ModifyConstant {method: "updateAnimals", constant: {intValue: 5}}
    function speedUpFluxSelfCleansing(value as int) as int {
        return 1;
    }
}
