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

### Mixins Golems (DONE)
- Golems can activate infusion altar
- Golems have a copy of the placing player's research knowledge

### Wuss Mode
- Ability to disable research entirely
- Cheaper recipe for greater foci

### Mod Merging
- Merge [Thaumic Inventory Scanning](https://www.curseforge.com/minecraft/mc-mods/thaumcraft-inventory-scanning) - TC4 Research Port also has it included and possibly touched up some stuff
  - https://github.com/Wong-Innovations/TC4Research/commit/bbbadaab6a5ed3f0401525ad89ebcb1c14e89c14

## Feature Requests
- Don't know what all this does, but E2E:E dev requested these mixins be included
  - https://github.com/Krutoy242/Enigmatica2Expert-Extended/blob/master/scripts/mixin/thaumcraft.zs
- If you use a Plan + Break focus on blocks then immediately switch to the Primal Crusher (specifically only happens on this tool), it'll start to take durability from it to the point of breaking it (this ones pretty bad)
- If you put a gauntlet equipped with a Break focus on your offhand with an item infused with Destructive on the main hand, it will actually activate it when mining with the Break focus (doesn't occur in Creative)
- Golem blacklist and whitelist fails for certain types of blocks (not items), especially ones that are coded in the old way using metadata (like botanias living stone and living wood)
- Requesting items from golems manually sometimes fails
- Add Tab completion to TC commands (including research)
- Check if `ToolEvents#livingDrops()` is causing a dupe issue with replaced items and Collector infusion enchant (needs event priority LOWEST)
- Focal Manipulator scan and consume vis crystals from nearby inventories
- Hungry chest nomming animation doesn't play
- Silverwood leaves don't produce particles like they do in older thaumcraft versions. (Might be intentional to reduce lag)
- Make Thaumcraft's Research Table look for ore dictionaried Bookshelf
- Thaumonomicon Font - The Thaumonomicon used the unicode font in earlier Thaumcraft versions. This would be great to have as a config option without needing to have force unicode font on
- Option to disable zooming in and out on the Thaumonomicon
- Overlay effect when a warp event happens (still exists, you can see it when you have Death Gaze active)
- More research cards to add variety to the research game
- "More ways to get fundamental theories - bookshelf research aid gives more fundamentals cards? Additional aids? New cards specifically for fundamentals?"
- "Research card orders have a habit of being really shitty, you might start in one category and immediately get a bunch of cards that counteract your immediate progress. For example, I started researching alchemy and spent some fairly expensive cards to do so, but then straight after I got a card to discard alchemy research or split the progress with another useless category."
- "Research cards which require essentia should blacklist essentia the player hasn't discovered (i.e. scanned in an item), to prevent cases where you need stupidly rare essentia from some addon mod and there's no way to get it, so you have to scrap everything and start again."
- Perhaps make the crimson rites research only require a scanned copy of the book instead of destroying one; better for sharing in multiplayer.
- "Additionally, could research aid items placed in the world also be given a visual hint when they're accessible by a research table? Perhaps temphis runes floating around them, or maybe the enchanting table bookshelf runes particle effect?"
- Tweak Thaumometer third-person holding position and animation (latter only if possible/reasonable); make the Thaumometer slightly bigger and with the lens facing the player model, and perhaps make the holding arm stick out too.
- Additional flair for the Brain in a Jar: in older versions tapping it would make the jar tilt slightly, so if you get some time (ha) you could reimplement that effect.
- Additional Thaumometer visual tweak to readd from Thaumcraft 4: make it so the player's hands can be seen holding the Thaumometer in first-person view, like with vanilla maps.
- Tainted Soil always changes back to dirt, even if it was originally sand or some such. I don’t know if this applies to other tainted blocks like tainted logs or not.
- Taint can corrupt any mob that isn’t undead from what I know (apparently spellbats can be corrupted), so a tweak to ensure certain things can’t get tainted would be good.
- Tainted Sheep should have their wool coloured purple. Wool placed down that gets tainted should also turn purple.
- Improve Hollow Hill spawning. For one, when spawning in snowy biomes they don't have snow generated on top of them. Additionally, changing them to spawn using more modern structure blocks instead of hard-coded spawns might also be a good idea. Finally, it could also be good to have the eerie biome appear around them
- Rework Burrowing infusion to function more like Botania's Terrasteel Axe
