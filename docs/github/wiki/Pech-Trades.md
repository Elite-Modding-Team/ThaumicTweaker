# Pech Trade Tweaker
Thaumic Tweaker includes scripting methods that can be used to modify Pech trades. 

---

## Importing
```groovy
import mods.thaumictweaker.PechTrades
```

---

## Adding Trades
| Parameter  | Description                                                                 |
|:----------:|:----------------------------------------------------------------------------|
|  pechType  | The Pech variant type. Value can be "MINER", "MAGE", "ARCHER", or "COMMON". |
| tradeLevel | The item trade level. Value must be between 1 and 5.                        |
| tradeItem  | The item that will be generated on trade.                                   |

### CraftTweaker
```zenscript
//mods.thaumictweaker.PechTrades.addTrade(String pechType, int tradeLevel, IItemStack tradeItem);
mods.thaumictweaker.PechTrades.addTrade("MINER", 1, <minecraft:diamond>);
```
### GroovyScript
```groovy
//mods.thaumictweaker.PechTrades.addTrade(String pechType, int tradeLevel, ItemStack tradeItem)
mods.thaumictweaker.PechTrades.addTrade("MINER", 1, item('minecraft:diamond'))
```

---

## Adding Valued Items
Valued items are items that have designated Pech Trade values. 

| Parameter | Description                                                    |
|:---------:|:---------------------------------------------------------------|
|   item    | The valued item. Item metadata and NBT data are not supported. |
| itemValue | The item value. Value must be greater than 0.                  |

> [!IMPORTANT]
> Valued Items do not support item metadata or NBT data.

### CraftTweaker
```zenscript
//mods.thaumictweaker.PechTrades.addValuedItem(IItemStack item, int itemValue);
mods.thaumictweaker.PechTrades.addValuedItem(<minecraft:ender_pearl>, 15);
```
### GroovyScript
```groovy
//mods.thaumictweaker.PechTrades.addValuedItem(ItemStack item, int itemValue)
mods.thaumictweaker.PechTrades.addValuedItem(item('minecraft:ender_pearl'), 15)
```

---

## Removing Trades
> [!IMPORTANT]
> Pechs must have at least one trade registered to each trade level. If a trade level is missing a value, Congrega Mystica will automatically populate the missing trade with scrap items.


### CraftTweaker
```zenscript
//mods.thaumictweaker.PechTrades.removeTrade(String pechType, IIngredient ingredient);
mods.thaumictweaker.PechTrades.removeTrade("MINER", <minecraft:blaze_rod>);

//mods.thaumictweaker.PechTrades.removeAllTrades(String pechType);
mods.thaumictweaker.PechTrades.removeAllTrades("MINER");

//mods.thaumictweaker.PechTrades.removeAllTrades();
mods.thaumictweaker.PechTrades.removeAllTrades();
```
### GroovyScript
```groovy
//mods.thaumictweaker.PechTrades.removeTrade(String pechType, IIngredient ingredient)
mods.thaumictweaker.PechTrades.removeTrade("MINER", item('minecraft:blaze_rod'))

//mods.thaumictweaker.PechTrades.removeAllTrades(String pechType)
mods.thaumictweaker.PechTrades.removeAllTrades("MINER")

//mods.thaumictweaker.PechTrades.removeAllTrades()
mods.thaumictweaker.PechTrades.removeAllTrades()
```
