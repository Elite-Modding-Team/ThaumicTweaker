package mod.emt.thaumictweaker.registry;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import mod.emt.thaumictweaker.util.helpers.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID)
public class RecipeRegistry {
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        registerInfusionRecipes();
    }

    private static void registerInfusionRecipes() {
        //Primal Crusher recipe should only be overwritten if the item is modified
        if(ConfigHandlerTT.primal_crusher.unbreakable || ConfigHandlerTT.primal_crusher.refiningLevel > 1) {
            ItemStack crusherStack = new ItemStack(ItemsTC.primalCrusher);
            EnumInfusionEnchantment.addInfusionEnchantment(crusherStack, EnumInfusionEnchantment.DESTRUCTIVE, 1);
            EnumInfusionEnchantment.addInfusionEnchantment(crusherStack, EnumInfusionEnchantment.REFINING, ConfigHandlerTT.primal_crusher.refiningLevel);
            if(ConfigHandlerTT.primal_crusher.unbreakable) {
                ItemHelper.setUnbreakable(crusherStack);
            }
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:PrimalCrusher"), new InfusionRecipe(
                    "PRIMALCRUSHER",
                    crusherStack,
                    6,
                    new AspectList().add(Aspect.EARTH, 75).add(Aspect.TOOL, 75).add(Aspect.ENTROPY, 50).add(Aspect.VOID, 50).add(Aspect.AVERSION, 50).add(Aspect.ELDRITCH, 50).add(Aspect.DESIRE, 50),
                    Ingredient.fromItem(ItemsTC.primordialPearl),
                    Ingredient.fromItem(ItemsTC.voidPick),
                    Ingredient.fromItem(ItemsTC.voidShovel),
                    Ingredient.fromItem(ItemsTC.elementalPick),
                    Ingredient.fromItem(ItemsTC.elementalShovel)
            ));
        }

        //Wuss Mode: Primordial Pearl Recipe
        if(ConfigHandlerTT.wuss_mode.wussModePrimordialPearlRecipe) {
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(ThaumicTweaker.MOD_ID, "primordial_pearl"), new InfusionRecipe(
                    "TT_PRIMORDIAL_PEARL",
                    new ItemStack(ItemsTC.primordialPearl, 1, 7),
                    2,
                    new AspectList().add(Aspect.AIR, 50).add(Aspect.EARTH, 50).add(Aspect.ENTROPY, 50).add(Aspect.FIRE, 50).add(Aspect.ORDER, 50).add(Aspect.WATER, 50),
                    Ingredient.fromItem(ItemsTC.voidSeed),
                    Ingredient.fromItem(ItemsTC.salisMundus),
                    ThaumcraftApiHelper.makeCrystal(Aspect.AIR, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EARTH, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.FIRE, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.ORDER, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.WATER, 1)
            ));
        }

    }
}
