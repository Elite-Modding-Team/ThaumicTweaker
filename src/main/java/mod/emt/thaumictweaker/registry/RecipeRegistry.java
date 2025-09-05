package mod.emt.thaumictweaker.registry;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import mod.emt.thaumictweaker.config.ConfigWussModeTT;
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
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID)
public class RecipeRegistry {
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        registerArcaneRecipes();
        registerCrucibleRecipes();
        registerInfusionRecipes();
    }

    private static void registerArcaneRecipes() {
        //Wuss Mode: Workbench Charger
        if(ConfigWussModeTT.cheaperWorkbenchCharger) {
            ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:workbenchcharger"), new ShapedArcaneRecipe(
                    new ResourceLocation(""),
                    "WORKBENCHCHARGER",
                    150,
                    new AspectList().add(Aspect.AIR, 2).add(Aspect.ORDER, 2),
                    new ItemStack(BlocksTC.arcaneWorkbenchCharger),
                    " R ", "W W", "I I",
                    'I', "ingotIron",
                    'R', new ItemStack(ItemsTC.visResonator),
                    'W', new ItemStack(BlocksTC.plankGreatwood)
            ));
        }
    }

    private static void registerCrucibleRecipes() {
        //Alchemical Brass with Copper
        if(ConfigTweaksTT.misc_tweaks.alchemicalBrassCopperRecipe) {
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:brassingot"), new CrucibleRecipe(
                    "METALLURGY@1",
                    new ItemStack(ItemsTC.ingots, 1, 2),
                    "ingotCopper",
                    new AspectList().add(Aspect.TOOL, 5)
            ));
        }

        //Wuss Mode: Bath Salts
        if(ConfigWussModeTT.cheaperBathSalts) {
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:BathSalts"), new CrucibleRecipe(
                    "BATHSALTS",
                    new ItemStack(ItemsTC.bathSalts),
                    new ItemStack(ItemsTC.salisMundus),
                    new AspectList().add(Aspect.MIND, 30).add(Aspect.LIFE, 30)
            ));
        }

        //Wuss Mode: Sanitizing Soap
        if(ConfigWussModeTT.cheaperSanitizingSoap) {
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SaneSoap"), new CrucibleRecipe(
                    "SANESOAP",
                    new ItemStack(ItemsTC.sanitySoap),
                    new ItemStack(BlocksTC.fleshBlock),
                    new AspectList().add(Aspect.MIND, 40).add(Aspect.ORDER, 40).add(Aspect.LIFE, 40)
            ));
        }
    }

    private static void registerInfusionRecipes() {
        //Primal Crusher recipe should only be overwritten if the item is modified
        if(ConfigTweaksTT.primal_crusher.unbreakable || ConfigTweaksTT.primal_crusher.refiningLevel > 1) {
            ItemStack crusherStack = new ItemStack(ItemsTC.primalCrusher);
            EnumInfusionEnchantment.addInfusionEnchantment(crusherStack, EnumInfusionEnchantment.DESTRUCTIVE, 1);
            EnumInfusionEnchantment.addInfusionEnchantment(crusherStack, EnumInfusionEnchantment.REFINING, ConfigTweaksTT.primal_crusher.refiningLevel);
            if(ConfigTweaksTT.primal_crusher.unbreakable) {
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

        //Wuss Mode: Arcane Bore
        if(ConfigWussModeTT.cheaperArcaneBore) {
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ArcaneBore"), new InfusionRecipe(
                    "ARCANEBORE",
                    new ItemStack(ItemsTC.turretPlacer, 1, 2),
                    2,
                    new AspectList().add(Aspect.ENERGY, 25).add(Aspect.EARTH, 25).add(Aspect.MECHANISM, 50).add(Aspect.VOID, 25).add(Aspect.MOTION, 25),
                    Ingredient.fromItem(ItemsTC.turretPlacer),
                    new ItemStack(BlocksTC.plankGreatwood),
                    new ItemStack(BlocksTC.plankGreatwood),
                    Ingredient.fromItem(ItemsTC.mechanismSimple),
                    "plateBrass",
                    Ingredient.fromItem(ItemsTC.thaumiumPick),
                    Ingredient.fromItem(ItemsTC.morphicResonator)
            ));
        }

        //Wuss Mode: Primordial Pearl
        if(ConfigWussModeTT.primordialPearlCreationRecipe) {
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(ThaumicTweaker.MOD_ID, "primordial_pearl_creation"), new InfusionRecipe(
                    "VOIDSIPHON",
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

        if(ConfigWussModeTT.primordialPearlGrowingRecipe) {
            for(int i = 0; i < 7; i++) {
                ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(ThaumicTweaker.MOD_ID, "primordial_pearl_growth_" + i), new InfusionRecipe(
                        "VOIDSIPHON",
                        new ItemStack(ItemsTC.primordialPearl, 1, i),
                        2,
                        new AspectList().add(Aspect.AIR, 50).add(Aspect.EARTH, 50).add(Aspect.ENTROPY, 50).add(Aspect.FIRE, 50).add(Aspect.ORDER, 50).add(Aspect.WATER, 50),
                        Ingredient.fromStacks(new ItemStack(ItemsTC.primordialPearl, 1, i + 1)),
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
}
