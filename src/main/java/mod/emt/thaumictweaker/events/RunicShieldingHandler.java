package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigOverhaulsTT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.*;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

public class RunicShieldingHandler {
    public static final boolean ENABLE_NEW_RUNIC_SHIELDING = ConfigOverhaulsTT.runicShieldingOverhaul;
    public static final String RUNIC_SHIELDING_NAME = "Runic shielding";
    public static final UUID RUNIC_SHIELDING_ID = UUID.fromString("a9722db2-1256-4788-897f-654386a703f0");

    public static final IAttribute RUNIC_SHIELDING = new RangedAttribute(null, ThaumicTweaker.MOD_ID + ":runic_shielding", 0, 0, Double.MAX_VALUE).setShouldWatch(true);

    @SubscribeEvent
    public void onEntityConstructed(EntityEvent.EntityConstructing event) {
        if(event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase player = (EntityLivingBase) event.getEntity();
            registerAttribute(player.getAttributeMap(), RUNIC_SHIELDING);
        }
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        if(event.getEntityLiving() instanceof EntityLivingBase && event.getEntityLiving().isEntityAlive()) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            float damage = event.getAmount();
            float shielding = (float) getRunicShielding(entityLiving);
            if(shielding > 0) {
                damage = Math.max(0, damage - shielding);
                shielding = Math.max(0, shielding - event.getAmount());
                event.setAmount(damage);
                setRunicShielding(entityLiving, shielding);
            }
        }
    }

    public static double getRunicShielding(EntityLivingBase entityLiving) {
        if(ENABLE_NEW_RUNIC_SHIELDING) {
            registerAttribute(entityLiving.getAttributeMap(), RUNIC_SHIELDING);
            return entityLiving.getEntityAttribute(RUNIC_SHIELDING).getAttributeValue();
        } else {
            return entityLiving.getAbsorptionAmount();
        }
    }

    public static void setRunicShielding(EntityLivingBase entityLiving, double shielding) {
        if(ENABLE_NEW_RUNIC_SHIELDING) {
            registerAttribute(entityLiving.getAttributeMap(), RUNIC_SHIELDING);
            IAttributeInstance instance = entityLiving.getEntityAttribute(RUNIC_SHIELDING);
            instance.removeModifier(RUNIC_SHIELDING_ID);
            instance.applyModifier(new AttributeModifier(RUNIC_SHIELDING_ID, RUNIC_SHIELDING_NAME, shielding, 0));
        } else {
            entityLiving.setAbsorptionAmount((float) shielding);
        }
    }

    private static void registerAttribute(AbstractAttributeMap attributeMap, IAttribute attribute) {
        if(attributeMap.getAttributeInstance(attribute) == null) {
            attributeMap.registerAttribute(attribute);
        }
    }
}
