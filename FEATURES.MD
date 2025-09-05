# Thaumic Tweaker Features
Thaumcraft tweaks for tweakers.

## Containers
- ContainerArcaneBore
  - Shift gui down to match vanilla chest
- ContainerPech
  - Shift gui down to match vanilla chest
- ContainerTurretAdvanced
  - Shift gui down to match vanilla chest
- ContainerTurretBasic
  - Shift gui down to match vanilla chest

### Items (DONE)
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

### Mixins Blocks (DONE)
- Pedestals have comparator output values

### Mixins Golems (DONE)
- Golems can activate infusion altar
- Golems have a copy of the placing player's research knowledge

## Feature Requests
- Don't know what all this does, but E2E:E dev requested these mixins be included
  - https://github.com/Krutoy242/Enigmatica2Expert-Extended/blob/master/scripts/mixin/thaumcraft.zs
- Runic Shielding Overhaul?
