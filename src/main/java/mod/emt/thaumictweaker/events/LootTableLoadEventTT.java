package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.items.ItemsTC;

@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID)
public class LootTableLoadEventTT {

    @SubscribeEvent
    public static void onLivingDrops(LootTableLoadEvent event) {
        //Adds Apprentice's Ring to vanilla structure chests
        if(ConfigHandlerTT.apprentices_ring.apprenticesRingStructureLoot) {
            if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID) || event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
                LootPool main = event.getTable().getPool("main");
                main.addEntry(new LootEntryItem(new ItemStack(ItemsTC.baubles, 1, 3).getItem(), 4, 0, new LootFunction[0], new LootCondition[0], "loottable:apprentices_ring"));
            }

            if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE) || event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING) ||
                    event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)) {
                LootPool main = event.getTable().getPool("main");
                main.addEntry(new LootEntryItem(new ItemStack(ItemsTC.baubles, 1, 3).getItem(), 2, 0, new LootFunction[0], new LootCondition[0], "loottable:apprentices_ring"));
            }
        }
    }
}
