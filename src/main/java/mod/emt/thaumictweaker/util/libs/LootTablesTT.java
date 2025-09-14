package mod.emt.thaumictweaker.util.libs;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTablesTT {
    //Entities that don't get loot tables
    //  Brainy Zombie - it's just a zombie with a brain drop
    //  Crimson Portal (Lesser) - better to add drops with the event
    //  Crimson Portal (Greater) - same as Crimson Portal
    //  Cultists - already have a designated loot table
    //  Pech - already has a loot table
    //  Spellbat - not a real entity
    //  Thaumic Slime - just a slime loot table with a flux crystal
    //  Wisp - would need a custom loot function to handle crystal types

    public static final ResourceLocation EMPTY = LootTableList.EMPTY;
}
