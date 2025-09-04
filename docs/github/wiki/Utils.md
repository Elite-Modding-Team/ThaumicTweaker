# Utils
Miscellaneous utility tweaker methods.

---

## Importing
```groovy
import mods.thaumictweaker.Utils
```

---

## Infusion Enchantments
> [!NOTE]
> CraftTweaker Only

Apply Infusion Enchantments to IItemStacks.

|  Property   | Description                                                                                             |
|:-----------:|:--------------------------------------------------------------------------------------------------------|
|    item     | The IItemStack being enchanted.                                                                         |
| enchantment | The Infusion Enchantment being applied. See table below for a list of valid infusion enchantment names. |
|    level    | The Infusion Enchantment level. See table below for valid levels for each enchantment.                  |

> [!IMPORTANT]
> Infusion enchantment names are case sensitive. You must match one of the names listed in the table below.

```zenscript
//mods.thaumictweaker.Utils.addInfusionEnchantment(IItemStack item, String enchantment, int level);
mods.thaumictweaker.Utils.addInfusionEnchantment(<minecraft:iron_pickaxe>, REFINING, 4);
mods.thaumictweaker.Utils.addInfusionEnchantment(<minecraft:diamond_sword>, ARCING, 2);

//This method returns an IItemStack, so you can use it inside other methods.
recipes.addShapeless(Utils.addInfusionEnchantment(<minecraft:iron_pickaxe>, REFINING, 4), [
    <minecraft:iron_pickaxe>, <ore:ingotGold>
]);
```

| Enchantment Name |         Viable Items         | Max Level | Description                                                                                                                                                          |
|:----------------:|:----------------------------:|:---------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|    COLLECTOR     | axe, pickaxe, shovel, weapon |     1     | Harvested and dropped items will float towards the player.                                                                                                           |
|   DESTRUCTIVE    |     axe, pickaxe, shovel     |     1     | Harvesting a block with this tool causes it to break all 8 surrounding blocks.                                                                                       |
|    BURROWING     |         axe, pickaxe         |     1     | When harvesting a tree, the furthest block will be harvested instead of the one you are trying to break.                                                             |
|     SOUNDING     |           pickaxe            |     4     | Sneak + right-click on a block to send out a sounding pulse, revealing all nearby hidden ores.                                                                       |
|     REFINING     |           pickaxe            |     4     | Adds a chance when harvesting ores for the ore to transform into a Native Cluster. Higher ranks increase the chance of this occurring.                               |
|      ARCING      |            weapon            |     4     | When striking an enemy, a bolt of wind arcs to nearby enemies dealing half weapon damage. Higher ranks increase the number of arcs and range.                        |
|     ESSENCE      |            weapon            |     5     | When a creature is slain, it has a chance of dropping some of its essence in crystal form. Higher ranks increase the chance and number of crystals dropped.          |
|    VISBATTERY    |          chargable           |     3     | **NOT IMPLEMENTED**                                                                                                                                                  |
|    VISCHARGE     |          chargable           |     1     | **NOT IMPLEMENTED**                                                                                                                                                  |
|      SWIFT       |            boots             |     4     | **NOT IMPLEMENTED**                                                                                                                                                  |
|      AGILE       |             legs             |     1     | **NOT IMPLEMENTED**                                                                                                                                                  |
|     INFESTED     |            chest             |     1     | **NOT IMPLEMENTED**                                                                                                                                                  |
|    LAMPLIGHT     |     axe, pickaxe, shovel     |     1     | When breaking a block, places an invisible and intangible source of light if the light level is below 10.                                                            |
|    VOIDFLAME     |            weapon            |     4     | (Requires Thaumic Wonders Unofficial) Gives weapons the ability to apply a true damage dot that reduces healing received by 50%. Higher ranks increase dot duration. |

