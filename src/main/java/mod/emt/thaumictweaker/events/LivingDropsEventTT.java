package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.tainted.EntityTaintSeedPrime;

@Mod.EventBusSubscriber(modid = ThaumicTweaker.MOD_ID)
public class LivingDropsEventTT {

    // Drops are added through an event because they don't have proper loot tables setup.
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDrops(LivingDropsEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        // Eldritch Guardian - Eldritch Curiosity
        if (!entity.world.isRemote && entity.getRNG().nextDouble() <= ConfigHandlerTT.curiosity_tweaks.curioDropEldritchGuardian
                && entity instanceof EntityEldritchGuardian && entity.getAttackingEntity() instanceof EntityPlayer) {
            event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemsTC.curio, 1, 3)));
        }

        // Giant Taint Seed - Twisted Curiosity
        if (!entity.world.isRemote && entity.getRNG().nextDouble() <= ConfigHandlerTT.curiosity_tweaks.curioDropGiantTaintSeed
                && entity instanceof EntityTaintSeedPrime && entity.getAttackingEntity() instanceof EntityPlayer) {
            event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemsTC.curio, 1, 5)));
        }
    }
}
