package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.util.libs.ModTags;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.common.config.ModConfig;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.lib.utils.EntityUtils;

public class ChampionMobHandler {
    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        if(!ModConfig.CONFIG_WORLD.allowChampionMobs || event.getWorld().isRemote) return;

        int chance = ModTags.getChampionMobChance(event.getEntity());
        if(chance >= 0 && event.getEntity() instanceof EntityMob) {
            EntityMob entity = (EntityMob) event.getEntity();
            if(entity.getEntityAttribute(ThaumcraftApiHelper.CHAMPION_MOD).getAttributeValue() <= -1.0) {
                chance = event.getWorld().rand.nextInt(100) - chance;

                switch (event.getWorld().getDifficulty()) {
                    case EASY:
                        chance += 2;
                        break;
                    case HARD:
                        chance -= 2;
                }

                Biome biome = entity.world.getBiome(new BlockPos(entity));
                chance -= ModTags.getChampionBiomeModifier(biome);

                if(chance <= 0 && entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() >= 10.0) {
                    makeChampion(entity, false);
                }
            }
        }
    }

    public static void makeChampion(EntityMob entity, boolean persist) {
        try {
            if (entity.getEntityAttribute(ThaumcraftApiHelper.CHAMPION_MOD).getAttributeValue() > -1.0) {
                return;
            }
        } catch (Exception ignored) {
            return;
        }

        int type = entity.world.rand.nextInt(ChampionModifier.mods.length);
        if (entity instanceof EntityCreeper) {
            type = 0;
        }

        IAttributeInstance champion = entity.getEntityAttribute(ThaumcraftApiHelper.CHAMPION_MOD);
        champion.removeModifier(ChampionModifier.mods[type].attributeMod);
        champion.applyModifier(ChampionModifier.mods[type].attributeMod);

        if (!(entity instanceof EntityThaumcraftBoss)) {
            IAttributeInstance maxHealth = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
            maxHealth.removeModifier(EntityUtils.CHAMPION_HEALTH);
            maxHealth.applyModifier(EntityUtils.CHAMPION_HEALTH);

            IAttributeInstance attackDamage = entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
            attackDamage.removeModifier(EntityUtils.CHAMPION_DAMAGE);
            attackDamage.applyModifier(EntityUtils.CHAMPION_DAMAGE);

            entity.heal(entity.getMaxHealth());
            entity.setCustomNameTag(ChampionModifier.mods[type].getModNameLocalized() + " " + entity.getName());
        } else {
            ((EntityThaumcraftBoss)entity).generateName();
        }

        if (persist) {
            entity.enablePersistence();
        }

        switch (type) {
            case 0:
                IAttributeInstance movementSpeed = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                movementSpeed.removeModifier(EntityUtils.BOLDBUFF);
                movementSpeed.applyModifier(EntityUtils.BOLDBUFF);
                break;
            case 3:
                IAttributeInstance attackDamage = entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
                attackDamage.removeModifier(EntityUtils.MIGHTYBUFF);
                attackDamage.applyModifier(EntityUtils.MIGHTYBUFF);
                break;
            case 5:
                int baseHealth = (int)entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2;
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (float)baseHealth);
        }

    }
}
