# Special Mining Result Tweaker
Thaumic Tweaker includes scripting methods for adding or removing special mining results such as native clusters obtained via the Refining infusion enchantment.

---

## Importing
```groovy
import mods.thaumictweaker.SpecialMiningResult
```

---

## Adding Special Mining Results
|  Parameter  | Description                                                                            |
|:-----------:|:---------------------------------------------------------------------------------------|
| harvestDrop | The item dropped during the harvest event.                                             |
|   output    | The output item that will replace the drop if this special mining result is a success. |
|   chance    | The chance this special mining result will occur.                                      |

### CraftTweaker
```zenscript
//mods.thaumictweaker.SpecialMiningResult.add(IIngredient harvestDrop, IItemStack output, float chance);
mods.thaumictweaker.SpecialMiningResult.add(<ore:oreIron>, <thaumcraft:cluster:0>, 1.0);
mods.thaumictweaker.SpecialMiningResult.add(<ore:gemQuartz>, <thaumcraft:cluster:7>, 1.0);
```
### GroovyScript
```groovy
//mods.thaumictweaker.SpecialMiningResult.add(IIngredient harvestDrop, ItemStack output, float chance)
mods.thaumictweaker.SpecialMiningResult.add(ore('oreIron'), item('thaumcraft:cluster:0'), 1.0)
mods.thaumictweaker.SpecialMiningResult.add(ore('gemQuartz'), item('thaumcraft:cluster:7'), 1.0)
```

---

## Removing Special Mining Results
### CraftTweaker
```zenscript
//mods.thaumictweaker.SpecialMiningResult.removebyHarvestDrop(IIngredient harvestDrop);
mods.thaumictweaker.SpecialMiningResult.removebyHarvestDrop(<ore:oreIron>);

//mods.thaumictweaker.SpecialMiningResult.removeByOutput(IIngredient output);
mods.thaumictweaker.SpecialMiningResult.removeByOutput(<thaumcraft:cluster:0>);

//mods.thaumictweaker.SpecialMiningResult.removeAll();
mods.thaumictweaker.SpecialMiningResult.removeAll();
```
### GroovyScript
```groovy
//mods.thaumictweaker.SpecialMiningResult.removebyHarvestDrop(IIngredient harvestDrop)
mods.thaumictweaker.SpecialMiningResult.removebyHarvestDrop(ore('oreIron'))

//mods.thaumictweaker.SpecialMiningResult.removeByOutput(IIngredient output)
mods.thaumictweaker.SpecialMiningResult.removeByOutput(item('thaumcraft:cluster:0'))

//mods.thaumictweaker.SpecialMiningResult.removeAll()
mods.thaumictweaker.SpecialMiningResult.removeAll()
```
