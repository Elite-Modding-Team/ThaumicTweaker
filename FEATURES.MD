# Thaumic Tweaker Features
Thaumcraft tweaks for tweakers.

## Blocks
- Arcane Crafter - 

## Crafts
ThaumTweaks has a bunch of pretty random recipe changes. Some of them just reduce the cost of the craft but others actually increase the cost. Who fucking knows...
- Apprentice Ring - Add new apprentice ring recipe
- Purifying Bath Salts - Reduce aspect crafting cost
- Sanitizing Soap - Reduce aspect crafting cost


### Items (DONE)
- Filter Item - Used with arcane crafter
- Armor Materials
  - Thaumium - +1 defence, +4 toughness, 2x durability
  - Fortress - +1 armor, +3 toughness, Strength II, +100% knockback resistance, better damage reduction ratio, 3x durability
  - Void Metal - +4 armor, regen effect, 3x durability, 3x repair speed
  - Void Robe - Regeneration II effect, debuff cleansing, 75% knockback resistance, 4x durability, 4x repairing, better damage reduction ratio
- Weapon Materials
  - Elemental - +2 damage
  - Void Metal - +4 damage
  - Primal - +2 efficiency, 10x durability

## Features
### Mixins Armor (DONE)
- Primal Crusher mines a 5x5 area
- Fortress armor grants knockback resistance (chest = 0.4, rest = 0.3)
- Fortress armor set bonus (Strength modifier configurable)
- Fortress armor has additional armor, armor toughness, and magic damage protection
- Void Metal Armor repair significantly faster
- Void Metal Armor set bonus (Regeneration I)
- Void Robes can be recharged using Vis
- Void Robes repair significantly faster
- Void Robes set bonus
  - Head - drains stored vis to feed the player every 3 seconds
  - Chest - drains stored vis to heal every second
  - Legs - drains stored vis to cleanse Poison, Wither or Flux Taint
- Void Robes grant knockback resistance (all = 0.25)
- Void Robes grant a flat damage and magic resistance
- Void Robes grant a configurable vis discount (head = 8, rest = 7)

### Mixins Blocks (DONE)
- No fucking clue what ThaumTweaks does to the TileInfusionMatrix with MatrixMixin
- Pedestals have comparator output values
- Research Table can search and pull items from nearby inventories

### Mixins Events
### Mixins Golems (DONE)
- Golems can activate infusion altar
- Golems have a copy of the placing player's research knowledge

### Mixins Items (DONE)
- Increase the amount of vis restored by the Vis Amulet

## Feature Requests
- Don't know what all this does, but E2E:E dev requested these mixins be included
  - https://github.com/Krutoy242/Enigmatica2Expert-Extended/blob/master/scripts/mixin/thaumcraft.zs
- config to increase Porus Stone drop chances

## Stuff I have no idea what ThaumTweaks is doing
- RechargeHelperMixin - No fucking clue what it is trying to do
- BlockRechargePedestal - again, no fucking clue
- 