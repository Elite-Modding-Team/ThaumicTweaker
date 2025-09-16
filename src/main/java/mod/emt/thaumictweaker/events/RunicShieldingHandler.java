package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

public class RunicShieldingHandler {
    public static final boolean ENABLE_NEW_RUNIC_SHIELDING = ConfigTweaksTT.runic_shielding.newRunicShielding;
    public static final String RUNIC_SHIELDING_NAME = "Runic shielding";
    public static final UUID RUNIC_SHIELDING_ID = UUID.fromString("a9722db2-1256-4788-897f-654386a703f0");

    public static final IAttribute RUNIC_SHIELDING = new RangedAttribute(null, ThaumicTweaker.MOD_ID + ":runic_shielding", 0, 0, Double.MAX_VALUE).setShouldWatch(true);

    @SubscribeEvent
    public void onEntityConstructed(EntityEvent.EntityConstructing event) {
        if(event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            registerAttribute(player.getAttributeMap(), RUNIC_SHIELDING);
        }
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().isEntityAlive()) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            float damage = event.getAmount();
            float shielding = (float) getRunicShielding(player);
            if(shielding > 0) {
                damage -= shielding;
                shielding = Math.max(0, shielding - event.getAmount());
                event.setAmount(damage);
                setRunicShielding(player, shielding);
            }
        }
    }

    public static double getRunicShielding(EntityPlayer player) {
        if(ENABLE_NEW_RUNIC_SHIELDING) {
            return player.getEntityAttribute(RUNIC_SHIELDING).getAttributeValue();
        } else {
            return player.getAbsorptionAmount();
        }
    }

    public static void setRunicShielding(EntityPlayer player, double shielding) {
        if(ENABLE_NEW_RUNIC_SHIELDING) {
            IAttributeInstance instance = player.getEntityAttribute(RUNIC_SHIELDING);
            instance.removeModifier(RUNIC_SHIELDING_ID);
            instance.applyModifier(new AttributeModifier(RUNIC_SHIELDING_ID, RUNIC_SHIELDING_NAME, shielding, 0));
        } else {
            player.setAbsorptionAmount((float) shielding);
        }
    }

    private static void registerAttribute(AbstractAttributeMap attributeMap, IAttribute attribute) {
        if(attributeMap.getAttributeInstance(attribute) == null) {
            attributeMap.registerAttribute(attribute);
        }
    }
}
