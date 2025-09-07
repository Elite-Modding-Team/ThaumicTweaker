# Thaumic Tweaker Features
Thaumcraft tweaks for tweakers.

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
- The sanity checker's interface will bug out if you put it on your offhand while a gauntlet is in your main hand. Apparently the Thaumometer has the same issue
- If you use a Plan + Break focus on blocks then immediately switch to the Primal Crusher (specifically only happens on this tool), it'll start to take durability from it to the point of breaking it (this ones pretty bad)
- If you put a gauntlet equipped with a Break focus on your offhand with an item infused with Destructive on the main hand, it will actually activate it when mining with the Break focus (doesn't occur in Creative)
- Golem blacklist and whitelist fails for certain types of blocks (not items), especially ones that are coded in the old way using metadata (like botanias living stone and living wood)
- Requesting items from golems manually sometimes fails
- Water frozen by the Frost focus doesn't gradually crack and turn back into water like it should, it tends to get stuck.
- Unequiping spells doesn't work if you sneak-focus button in midair or while floating in water.
- Golem Use seals act weirdly in some cases, for instance I couldn't get a golem to fill up a bucket of water from a water source.