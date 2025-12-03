# Thaumic Tweaker Features
## Features Requests

### Wuss Mode
- Cheaper recipe for greater foci
- There might be a few other recipes (?)

### Mod Merging
- Merge [Thaumic Inventory Scanning](https://www.curseforge.com/minecraft/mc-mods/thaumcraft-inventory-scanning) - TC4 Research Port also has it included and possibly touched up some stuff
  - https://github.com/Wong-Innovations/TC4Research/commit/bbbadaab6a5ed3f0401525ad89ebcb1c14e89c14
- Merge [Thaumic JEI](https://www.curseforge.com/minecraft/mc-mods/thaumic-jei) (?) - Either will be merged or rewritten

## Feature Requests
- Golem blacklist and whitelist fails for certain types of blocks (not items), especially ones that are coded in the old way using metadata (like botanias living stone and living wood)
- Requesting items from golems manually sometimes fails
- Add Tab completion to TC commands (including research)
- Check if `ToolEvents#livingDrops()` is causing a dupe issue with replaced items and Collector infusion enchant (needs event priority LOWEST)
- Focal Manipulator scan and consume vis crystals from nearby inventories
- Perhaps make the crimson rites research only require a scanned copy of the book instead of destroying one; better for sharing in multiplayer.
- Tainted Soil always changes back to dirt, even if it was originally sand or some such. I don’t know if this applies to other tainted blocks like tainted logs or not.
- Taint can corrupt any mob that isn’t undead from what I know (apparently spellbats can be corrupted), so a tweak to ensure certain things can’t get tainted would be good.
- Tainted Sheep should have their wool coloured purple. Wool placed down that gets tainted should also turn purple.
- Allow Golems to activate Infusion Altar.

## Animations and Effects
- Hungry chest nomming animation doesn't play
- Silverwood leaves don't produce particles like they do in older thaumcraft versions. (Might be intentional to reduce lag)
- Overlay effect when a warp event happens (still exists, you can see it when you have Death Gaze active)
- Additionally, could research aid items placed in the world also be given a visual hint when they're accessible by a research table? Perhaps temphis runes floating around them, or maybe the enchanting table bookshelf runes particle effect?
- Tweak Thaumometer third-person holding position and animation (latter only if possible/reasonable); make the Thaumometer slightly bigger and with the lens facing the player model, and perhaps make the holding arm stick out too.
- Additional flair for the Brain in a Jar: in older versions tapping it would make the jar tilt slightly, so if you get some time (ha) you could reimplement that effect.
- Additional Thaumometer visual tweak to readd from Thaumcraft 4: make it so the player's hands can be seen holding the Thaumometer in first-person view, like with vanilla maps.

## Research Changes
- More research cards to add variety to the research game
- More ways to get fundamental theories - bookshelf research aid gives more fundamentals cards? Additional aids? New cards specifically for fundamentals?
- Research card orders have a habit of being really shitty, you might start in one category and immediately get a bunch of cards that counteract your immediate progress. For example, I started researching alchemy and spent some fairly expensive cards to do so, but then straight after I got a card to discard alchemy research or split the progress with another useless category.
- Research cards which require essentia should blacklist essentia the player hasn't discovered (i.e. scanned in an item), to prevent cases where you need stupidly rare essentia from some addon mod and there's no way to get it, so you have to scrap everything and start again.

## Requires Overhaul
- If you use a Plan + Break focus on blocks then immediately switch to the Primal Crusher (specifically only happens on this tool), it'll start to take durability from it to the point of breaking it (this ones pretty bad)
- If you put a gauntlet equipped with a Break focus on your offhand with an item infused with Destructive on the main hand, it will actually activate it when mining with the Break focus (doesn't occur in Creative)
- Make Thaumcraft's Research Table look for ore dictionaried Bookshelf
- Improve Hollow Hill spawning. For one, when spawning in snowy biomes they don't have snow generated on top of them. Additionally, changing them to spawn using more modern structure blocks instead of hard-coded spawns might also be a good idea. Finally, it could also be good to have the eerie biome appear around them
- Rework Burrowing infusion to function more like Botania's Terrasteel Axe

## Potential Issues
- Thaumonomicon Font - The Thaumonomicon used the unicode font in earlier Thaumcraft versions. This would be great to have as a config option without needing to have force unicode font on

