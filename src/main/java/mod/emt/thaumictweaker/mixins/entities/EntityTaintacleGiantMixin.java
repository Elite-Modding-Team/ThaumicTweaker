package mod.emt.thaumictweaker.mixins.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.lib.utils.EntityUtils;

@Mixin(value = EntityTaintacleGiant.class, remap = false)
public class EntityTaintacleGiantMixin {
    @Redirect(
            method = "onInitialSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/utils/EntityUtils;makeChampion(Lnet/minecraft/entity/monster/EntityMob;Z)V",
                    remap = false
            ),
            remap = true
    )
    private void makeTaintacleChampionMixin(EntityMob entity, boolean persist) {
        try {
            if (entity.getEntityAttribute(ThaumcraftApiHelper.CHAMPION_MOD).getAttributeValue() > (double)-2.0F) {
                return;
            }
        } catch (Exception var7) {
            return;
        }

        int type = entity.world.rand.nextInt(ChampionModifier.mods.length);

        IAttributeInstance mod = entity.getEntityAttribute(ThaumcraftApiHelper.CHAMPION_MOD);
        mod.removeModifier(ChampionModifier.mods[type].attributeMod);
        mod.applyModifier(ChampionModifier.mods[type].attributeMod);

        IAttributeInstance healthAttribute = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        healthAttribute.removeModifier(EntityUtils.CHAMPION_HEALTH);
        healthAttribute.applyModifier(EntityUtils.CHAMPION_HEALTH);

        IAttributeInstance attackDamage = entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        attackDamage.removeModifier(EntityUtils.CHAMPION_DAMAGE);
        attackDamage.applyModifier(EntityUtils.CHAMPION_DAMAGE);

        entity.setHealth(entity.getMaxHealth());
        entity.setCustomNameTag(ChampionModifier.mods[type].getModNameLocalized() + " " + entity.getName());
        entity.enablePersistence();

        switch (type) {
            case 0:
                IAttributeInstance movementSpeed = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                movementSpeed.removeModifier(EntityUtils.BOLDBUFF);
                movementSpeed.applyModifier(EntityUtils.BOLDBUFF);
                break;
            case 3:
                attackDamage.removeModifier(EntityUtils.MIGHTYBUFF);
                attackDamage.applyModifier(EntityUtils.MIGHTYBUFF);
                break;
            case 5:
                int warding = (int)entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2;
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (float)warding);
        }
    }
}
