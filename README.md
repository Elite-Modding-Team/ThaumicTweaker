# ThaumicTweaker

[![Requires MixinBooter](https://img.shields.io/badge/Requires-MixinBooter-3498db.svg?labelColor=34495e&style=for-the-badge)](https://www.curseforge.com/minecraft/mc-mods/mixin-booter)
[![Requires ConfigAnytime](https://img.shields.io/badge/Requires-ConfigAnytime-3498db.svg?labelColor=34495e&style=for-the-badge)](https://www.curseforge.com/minecraft/mc-mods/configanytime)

Tweaks for Thaumcraft 6. All the Thaumcraft tweaks a tweaker could want.

---

## Fixes
- Fixed Boots of the Traveler and Cloud Ring screen shake and damage sound when taking zero fall damage
- Fixes Caster's Gauntlet Gui rendering in the incorrect location if gauntlet is held in the offhand
- Fixed Caster's Gauntlet Gui causing rendering issues when gauntlet is in mainhand and Thaumometer or Sanity Checker is held in the offhand
- Fixed Exploration Research missing or not being granted to players under specific circumstances
- Fixed Flux Rifts not collapsing when fully drained by Void Siphons
- Fixed FXPollution packet causing a client-side crash when handling large values
- Fixed Giant Taintacles spawning with reduced health
- Fixed Giant Taintacles spamming errors when being rendered
- Fixed Golems voiding held items when interacting with Use Seals with "Can use empty hand" enabled
- Fixed Pech trading GUI hovered item tooltip issue (made glowing items turn black)
- Fixed Primal Crusher re-equipping when repairing
- Fixed Primordial Pearl item movement and spawn location if dropped by bosses
- Fixed Research Table shift-click interaction for Paper and Scribing Tools
- Fixed Thaumatorium causing a crash when interacting with some item insertion/extraction methods
- Fixed Thaumometer displaying particles when stored in first hotbar slot

---

## Enhancements
**NOTE:** All enhancements listed in this category are enabled by default. 

- **Focus Effects:** 
  - Revamps the cast sounds of certain focus effects to provide better variety (Break, Earth, Exchange, Frost, Heal, Rift)
  - Adds impact sounds to various focus effects that lack any (Break, Earth, Exchange, Fire, Flux , Frost, Heal, Rift)
  - Makes several focus mediums play additional cast sounds to differentiate them (Bolt, Cloud, Mine, Spellbat)
- **Gui Enhancements:**
  - Adjusted Pech Trade, Arcane Bore, and Advanced/Basic Automated Crossbow Gui position to match other inventories
  - Add Runic Warding effect over health bar whenever warding is active
  - Add subtitles support to Thaumonomicon research entries
  - Add subtitles support to Forbidden Knowledge research entries
- **Misc Enhancements:**
  - Add comparator output values to Arcane Pedestal
  - Suppress Warp Events while player is in Creative mode

---

## Overhauls
**NOTE:** All overhauls listed in this category are disabled by default.

- **Champion Mob Overhaul:**
  - Enables the Champion Mob system from previous Thaumcraft versions
- **Loot Table Overhaul:**
  - Changes most hardcoded mob drops into loot tables that can be modified using loot table tweakers. A full list of loot tables can be found [here](https://github.com/Elite-Modding-Team/ThaumicTweaker/tree/main/src/main/resources/assets/thaumictweaker/loot_tables/entity).
- **Runic Shielding Overhaul:**
  - Changes Runic Shielding to use its own shielding system instead of using Minecraft's Absorption. This also fixes a few issues where Thaumcraft would reset absorption granted by other effects.

---

## CraftTweaker/GroovyScript
**NOTE:** Scripting methods and their uses can be found on the mod [wiki](https://github.com/Elite-Modding-Team/ThaumicTweaker/wiki).

- **Collapsing Flux Rift Drops:**
  - Add additional items that drop when collapsing Flux Rifts using CraftTweaker or GroovyScript
- **Pech Trades:**
  - Add and remove Pech trades using CraftTweaker or GroovyScript
  - Add Pech valued items using CraftTweaker or GroovyScript
- **Porous Stone Tweaks:**
  - Add or remove Porous Stone special item drops using CraftTweaker or GroovyScript
- **Special Mining Result Tweaks:**
  - Add and remove special mining drops using CraftTweaker or GroovyScript

---

## General Tweaks
**NOTE:** All tweaks listed in this category are disabled by default or use Thaumcraft default values. 

- **Apprentice's Ring Tweaks:**
  - Allow the Apprentice's Ring to be looted from some vanilla structures
  - Modify the Vis Discount granted by the Apprentice's Ring
- **Champion Mob Tweaks:**
  - Add unimplemented Thaumcraft Champion mob content
  - Define what mobs can become champions as well as the chance specific mobs can become champions
- **Crucible Tweaks:**
  - Add additional blocks that can heat the Crucible
- **Cult Robes Tweaks:**
  - Modify the Vis discount granted by each piece of armor
- **Curiosity Tweaks:**
  - Ancient Curiosity, Arcane Curiosity, and Preserved Curiosity can be obtained from Pech trades
  - Eldritch Curiosity drops from Eldritch Guardians
  - Twisted Curiosity drops from Giant Taint Seeds
- **Flux Pollution Tweaks:**
  - Adjust the amount of flux generated by the Essentia Mirror
  - Adjust the amount of flux generated by the Mirror
- **Golem Material Tweaks:**
  - Change golem material stats such as armor, health, damage, and crafting material
- **Liquid Death Tweaks:**
  - Increase or decrease the amount of damage done by Liquid Death
- **Infusion Enchantment Tweaks:**
  - Adjust the chance Refining infusion will drop ore clusters
  - Allow the Refining infusion cluster drop chance to be affected by Fortune
  - Change the radius the Sounding infusion will detect ores
- **Pech Trade Tweaks:** 
  - Change Pech item value calculation
- **Porous Stone Tweaks:**
  - Change the chance special items will drop when harvesting Porous Stone
  - Increase the number of special items that drop when harvesting Porous Stone
- **Primal Crusher Tweaks:**
  - Modify Primal Crusher Refining infusion enchantment level
  - Make Primal Crusher Unbreakable
- **Research Table Tweaks:**
  - Research table can use and consume items from nearby inventories for research
  - Increase the vertical search area when the Research Table looks for nearby research aids
- **Vis Generator Tweaks:** 
  - Change the amount of RF generated per point of Vis and the RF output per tick
- **Void Siphon Tweaks:** 
  - Increase Void Seed creation speed
- **Void Thaumaturge Robes Tweaks:** 
  - Modify the Vis discount granted by each piece of armor
- **Miscellaneous Tweaks:**
  - Allow Thaumometer sky scanning from non-overworld dimensions
  - Change Alchemical brass recipe to use copper instead of iron
  - Disable Thaumcraft's item aspect calculation based on item crafting recipe 
  - Restores Legacy Thaumometer scanning functionality from 1.7.10


---

## Wuss Mode Tweaks
**NOTE:** All tweaks listed in this category are disabled by default. Wuss Mode Tweaks are designed to reduce the cost of certain recipes or allow players to bypass certain mechanics.

- **Recipes:**
  - **Arcane Bore:** Makes the Arcane Bore recipe cheaper
  - **Bath Salts:** Makes the Bath Salts recipe cheaper
  - **Sanitizing Soap:** Makes the Sanitizing Soap recipe cheaper
  - **Workbench Charger:** Makes the Workbench Charger recipe cheaper
- **Eldritch Guardian:** 
  - Removes the warp gain when Eldritch Guardians spawn in line of sight of the player
- **Primordial Pearl Recipes:**
  - New Infusion recipe that creates a 1 durability Primordial Pearl
  - New Infusion recipe that increases Primordial Pearl durability
- **Void Siphon:**
  - Void siphon no longer requires a nearby rift and will slowly accumulate progress over time

---

## Superseded Addons
These Thaumcraft addons are not compatible with this mod.
- [Thaumcraft 6 Enhanced](https://www.curseforge.com/minecraft/mc-mods/thaumcraft-6-enhanced)
- [ThaumTweaks](https://www.curseforge.com/minecraft/mc-mods/thaumtweaks)
