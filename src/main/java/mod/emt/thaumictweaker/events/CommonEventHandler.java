package mod.emt.thaumictweaker.events;

import baubles.api.BaublesApi;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.tainted.EntityTaintSeedPrime;

@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID)
public class CommonEventHandler {

    //Drops are added through an event because they don't have proper loot tables setup.
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDrops(LivingDropsEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        //Eldritch Guardian - Eldritch Curiosity
        if (!entity.world.isRemote && entity.getRNG().nextDouble() <= ConfigTweaksTT.curiosity_tweaks.curioDropEldritchGuardian
                && entity instanceof EntityEldritchGuardian && entity.getAttackingEntity() instanceof EntityPlayer) {
            event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemsTC.curio, 1, 3)));
        }

        //Giant Taint Seed - Twisted Curiosity
        if (!entity.world.isRemote && entity.getRNG().nextDouble() <= ConfigTweaksTT.curiosity_tweaks.curioDropGiantTaintSeed
                && entity instanceof EntityTaintSeedPrime && entity.getAttackingEntity() instanceof EntityPlayer) {
            event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemsTC.curio, 1, 5)));
        }
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        //Fixes the player being affected by a screen shake when no damage is taken
        EntityLivingBase entity = event.getEntityLiving();
        if (event.getSource() == DamageSource.FALL && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack boots = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (boots.getItem() == ItemsTC.travellerBoots || BaublesApi.isBaubleEquipped(player, ItemsTC.ringCloud) > 0) {
                float damage = event.getAmount();
                damage = Math.max(0.0F, damage);

                if (damage < 1.0F) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        //Adds Apprentice's Ring to vanilla structure chests
        if(ConfigTweaksTT.apprentices_ring.enableStructureLoot) {
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

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onLivingTickLate(LivingEvent.LivingUpdateEvent event) {
        if(!event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().ticksExisted % 200 == 0) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
            Biome biome = player.world.getBiome(player.getPosition());

            //Fixes bug caused when rewarding research with command locking players out of this research.
            if (knowledge.isResearchKnown("UNLOCKAUROMANCY@1")) {
                if (player.posY < (double)10.0F && !knowledge.isResearchKnown("m_deepdown")) {
                    knowledge.addResearch("m_deepdown");
                    knowledge.sync((EntityPlayerMP)player);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.translateToLocal("got.deepdown")), true);
                }

                if (player.posY > (double)player.getEntityWorld().getActualHeight() * 0.4 && !knowledge.isResearchKnown("m_uphigh")) {
                    knowledge.addResearch("m_uphigh");
                    knowledge.sync((EntityPlayerMP)player);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.translateToLocal("got.uphigh")), true);
                }
            }

            //Fixes Thaumcraft not granting players exploration research correctly
            if (!knowledge.isResearchKnown("m_finddesert") && BiomeDictionary.hasType(biome, BiomeDictionary.Type.HOT)) {
                knowledge.addResearch("m_finddesert");
                knowledge.sync((EntityPlayerMP) player);
                player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + net.minecraft.util.text.translation.I18n.translateToLocal("got.finddesert")), true);
            }
            if (!knowledge.isResearchKnown("m_findocean") && BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) {
                knowledge.addResearch("m_findocean");
                knowledge.sync((EntityPlayerMP) player);
                player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + net.minecraft.util.text.translation.I18n.translateToLocal("got.findocean")), true);
            }
        }
    }
}
