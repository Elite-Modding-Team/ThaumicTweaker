# Porous Stone Tweaker
> [!IMPORTANT]
> Porous Stone Tweaker methods require Thaumic Tweaker's configuration setting `B:"Enable Porous Stone Tweaks"=true`

Adds and removes special item drops from Porous Stone.

---

## Importing
```groovy
import mods.thaumictweaker.PorousStone
```

---

## Adding Drops
### CraftTweaker
```zenscript
//mods.thaumictweaker.PorousStone.addDrop(IItemStack stack, int weight);
mods.thaumictweaker.PorousStone.addDrop(<minecraft:diamond>, 10);
```
### GroovyScript
```groovy
//mods.thaumictweaker.PorousStone.addDrop(ItemStack stack, int weight)
mods.thaumictweaker.PorousStone.addDrop(item('minecraft:diamond'), 10)
```

---

## Removing Drops
### CraftTweaker
```zenscript
//mods.thaumictweaker.PorousStone.removeDrop(IIngredient drop);
mods.thaumictweaker.PorousStone.removeDrop(<thaumcraft:crystal_essence>);
mods.thaumictweaker.PorousStone.removeDrop(<thaumcraft:cluster:*>);

//mods.thaumictweaker.PorousStone.removeAllDrops();
mods.thaumictweaker.PorousStone.removeAllDrops();
```
### GroovyScript
```groovy
//mods.thaumictweaker.PorousStone.removeDrop(IIngredient drop)
mods.thaumictweaker.PorousStone.removeDrop(item('thaumcraft:crystal_essence'))
mods.thaumictweaker.PorousStone.removeDrop(item('thaumcraft:cluster:*'))

//mods.thaumictweaker.PorousStone.removeAllDrops()
mods.thaumictweaker.PorousStone.removeAllDrops()
```
