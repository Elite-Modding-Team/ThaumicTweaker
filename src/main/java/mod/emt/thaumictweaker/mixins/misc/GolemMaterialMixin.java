package mod.emt.thaumictweaker.mixins.misc;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.parts.GolemMaterial;
import thaumcraft.common.golems.GolemProperties;

@Mixin(value = GolemProperties.class, remap = false)
public class GolemMaterialMixin {
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/lang/String;[Ljava/lang/String;Lnet/minecraft/util/ResourceLocation;IIIILnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;[Lthaumcraft/api/golems/EnumGolemTrait;)Lthaumcraft/api/golems/parts/GolemMaterial;",
                    ordinal = 0
            )
    )
    private static GolemMaterial golemMaterialWoodMixin(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags) {
        return new GolemMaterial(
                key, research, texture, itemColor,
                ConfigTweaksTT.golem_tweaks.matWood.statHealth,
                ConfigTweaksTT.golem_tweaks.matWood.statArmor,
                ConfigTweaksTT.golem_tweaks.matWood.statDamage,
                ConfigTweaksTT.golem_tweaks.matWood.getMaterialStack(),
                compm, tags);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/lang/String;[Ljava/lang/String;Lnet/minecraft/util/ResourceLocation;IIIILnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;[Lthaumcraft/api/golems/EnumGolemTrait;)Lthaumcraft/api/golems/parts/GolemMaterial;",
                    ordinal = 1
            )
    )
    private static GolemMaterial golemMaterialIronMixin(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags) {
        return new GolemMaterial(
                key, research, texture, itemColor,
                ConfigTweaksTT.golem_tweaks.matIron.statHealth,
                ConfigTweaksTT.golem_tweaks.matIron.statArmor,
                ConfigTweaksTT.golem_tweaks.matIron.statDamage,
                ConfigTweaksTT.golem_tweaks.matIron.getMaterialStack(),
                compm, tags);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/lang/String;[Ljava/lang/String;Lnet/minecraft/util/ResourceLocation;IIIILnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;[Lthaumcraft/api/golems/EnumGolemTrait;)Lthaumcraft/api/golems/parts/GolemMaterial;",
                    ordinal = 2
            )
    )
    private static GolemMaterial golemMaterialClayMixin(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags) {
        return new GolemMaterial(
                key, research, texture, itemColor,
                ConfigTweaksTT.golem_tweaks.matClay.statHealth,
                ConfigTweaksTT.golem_tweaks.matClay.statArmor,
                ConfigTweaksTT.golem_tweaks.matClay.statDamage,
                ConfigTweaksTT.golem_tweaks.matClay.getMaterialStack(),
                compm, tags);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/lang/String;[Ljava/lang/String;Lnet/minecraft/util/ResourceLocation;IIIILnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;[Lthaumcraft/api/golems/EnumGolemTrait;)Lthaumcraft/api/golems/parts/GolemMaterial;",
                    ordinal = 3
            )
    )
    private static GolemMaterial golemMaterialBrassMixin(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags) {
        return new GolemMaterial(
                key, research, texture, itemColor,
                ConfigTweaksTT.golem_tweaks.matBrass.statHealth,
                ConfigTweaksTT.golem_tweaks.matBrass.statArmor,
                ConfigTweaksTT.golem_tweaks.matBrass.statDamage,
                ConfigTweaksTT.golem_tweaks.matBrass.getMaterialStack(),
                compm, tags);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/lang/String;[Ljava/lang/String;Lnet/minecraft/util/ResourceLocation;IIIILnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;[Lthaumcraft/api/golems/EnumGolemTrait;)Lthaumcraft/api/golems/parts/GolemMaterial;",
                    ordinal = 4
            )
    )
    private static GolemMaterial golemMaterialThaumiumMixin(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags) {
        return new GolemMaterial(
                key, research, texture, itemColor,
                ConfigTweaksTT.golem_tweaks.matThaumium.statHealth,
                ConfigTweaksTT.golem_tweaks.matThaumium.statArmor,
                ConfigTweaksTT.golem_tweaks.matThaumium.statDamage,
                ConfigTweaksTT.golem_tweaks.matThaumium.getMaterialStack(),
                compm, tags);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/lang/String;[Ljava/lang/String;Lnet/minecraft/util/ResourceLocation;IIIILnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;[Lthaumcraft/api/golems/EnumGolemTrait;)Lthaumcraft/api/golems/parts/GolemMaterial;",
                    ordinal = 5
            )
    )
    private static GolemMaterial golemMaterialVoidMixin(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags) {
        return new GolemMaterial(
                key, research, texture, itemColor,
                ConfigTweaksTT.golem_tweaks.matVoid.statHealth,
                ConfigTweaksTT.golem_tweaks.matVoid.statArmor,
                ConfigTweaksTT.golem_tweaks.matVoid.statDamage,
                ConfigTweaksTT.golem_tweaks.matVoid.getMaterialStack(),
                compm, tags);
    }

}
