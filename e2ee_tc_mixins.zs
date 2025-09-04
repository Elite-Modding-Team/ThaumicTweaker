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

/*
Buff [Porous Stone] output
Before this change, [Porous Stone] dropped something not [Gravel] with 7% chance. Each `Fortune` level increased this chance for about 1%.
Now, base chance is 20% and each `Fortune` level increasing it for about 20%.

Default beneficial drop formula:
nextInt(15) + fortune > 13
*/
#mixin {targets: "thaumcraft.common.blocks.basic.BlockStonePorous"}
zenClass MixinBlockStonePorous {
    #mixin ModifyConstant {method: "getDrops", constant: {intValue: 15}}
    function modifyDropCount0(value as int) as int {
        return 5;
    }

    #mixin ModifyConstant {method: "getDrops", constant: {intValue: 13}}
    function modifyDropCount1(value as int) as int {
        return 3;
    }
}

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

#mixin {targets: "thaumcraft.common.tiles.devices.TileMirror"}
zenClass MixinTileMirror {
    #mixin ModifyConstant {method: "checkInstability", constant: {intValue: 100}}
    function speedUpFluxSelfCleansing(value as int) as int {
        return 5;
    }
}

#mixin {targets: "thaumcraft.common.tiles.devices.TileMirrorEssentia"}
zenClass MixinTileMirrorEssentia {
    #mixin ModifyConstant {method: "checkInstability", constant: {intValue: 100}}
    function speedUpFluxSelfCleansing(value as int) as int {
        return 5;
    }
}
