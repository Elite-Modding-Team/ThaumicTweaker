package mod.emt.thaumictweaker.mixins.misc;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
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
                ConfigHandlerTT.golem_tweaks.matWood.statHealth,
                ConfigHandlerTT.golem_tweaks.matWood.statArmor,
                ConfigHandlerTT.golem_tweaks.matWood.statDamage,
                ConfigHandlerTT.golem_tweaks.matWood.getMaterialStack(),
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
                ConfigHandlerTT.golem_tweaks.matIron.statHealth,
                ConfigHandlerTT.golem_tweaks.matIron.statArmor,
                ConfigHandlerTT.golem_tweaks.matIron.statDamage,
                ConfigHandlerTT.golem_tweaks.matIron.getMaterialStack(),
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
                ConfigHandlerTT.golem_tweaks.matClay.statHealth,
                ConfigHandlerTT.golem_tweaks.matClay.statArmor,
                ConfigHandlerTT.golem_tweaks.matClay.statDamage,
                ConfigHandlerTT.golem_tweaks.matClay.getMaterialStack(),
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
                ConfigHandlerTT.golem_tweaks.matBrass.statHealth,
                ConfigHandlerTT.golem_tweaks.matBrass.statArmor,
                ConfigHandlerTT.golem_tweaks.matBrass.statDamage,
                ConfigHandlerTT.golem_tweaks.matBrass.getMaterialStack(),
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
                ConfigHandlerTT.golem_tweaks.matThaumium.statHealth,
                ConfigHandlerTT.golem_tweaks.matThaumium.statArmor,
                ConfigHandlerTT.golem_tweaks.matThaumium.statDamage,
                ConfigHandlerTT.golem_tweaks.matThaumium.getMaterialStack(),
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
                ConfigHandlerTT.golem_tweaks.matVoid.statHealth,
                ConfigHandlerTT.golem_tweaks.matVoid.statArmor,
                ConfigHandlerTT.golem_tweaks.matVoid.statDamage,
                ConfigHandlerTT.golem_tweaks.matVoid.getMaterialStack(),
                compm, tags);
    }

}
