package mod.emt.thaumictweaker.mixins.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.client.lib.events.RenderEventHandler;
import thaumcraft.common.items.tools.ItemThaumometer;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.utils.EntityUtils;

//Restores the slower scanning functionality from TC4.
@Mixin(value = ItemThaumometer.class, remap = false)
public class ItemThaumometerMixin extends Item {

    @Shadow
    protected RayTraceResult getRayTraceResultFromPlayerWild(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
        return null;
    }

    @Shadow
    private void updateAura(ItemStack stack, World world, EntityPlayerMP player) {
    }

    @Shadow
    public void doScan(World worldIn, EntityPlayer playerIn) {
    }

    @Shadow
    private void drawFX(final World worldIn, final EntityPlayer playerIn) {
    }

    @Inject(method = "onItemRightClick", at = @At("HEAD"), cancellable = true, remap = true)
    public void onItemRightClickInject(World world, EntityPlayer p, EnumHand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        ItemStack stack = p.getHeldItem(hand);

        if (!ConfigTweaksTT.misc_tweaks.legacyThaumometerScanning) {
            if (world.isRemote) {
                this.drawFX(world, p);
                p.world.playSound(p.posX, p.posY, p.posZ, SoundsTC.scan, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            } else {
                this.doScan(world, p);
            }

            cir.setReturnValue(new ActionResult<>(EnumActionResult.FAIL, stack));
        } else {
            p.setActiveHand(hand);
            cir.setReturnValue(new ActionResult<>(EnumActionResult.PASS, stack));
        }
    }

    @Override
    public void onUsingTick(@NotNull ItemStack stack, @NotNull EntityLivingBase entityLiving, int count) {
        if (!(entityLiving instanceof EntityPlayer) || !ConfigTweaksTT.misc_tweaks.legacyThaumometerScanning) return;
        if (entityLiving.world.isRemote) {
            if (count <= 1) {
                entityLiving.stopActiveHand();
                entityLiving.world.playSound(entityLiving.posX, entityLiving.posY, entityLiving.posZ, SoundsTC.scan, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            if (count % 2 == 0) {
                this.drawFX(entityLiving.world, (EntityPlayer) entityLiving);
                entityLiving.world.playSound(entityLiving.posX, entityLiving.posY, entityLiving.posZ, SoundsTC.ticks, SoundCategory.PLAYERS, 0.2F, 0.45F + entityLiving.world.rand.nextFloat() * 0.1F, false);
            }
        } else {
            if (count <= 1) {
                this.doScan(entityLiving.world, (EntityPlayer) entityLiving);
                if (!entityLiving.world.isRemote) ((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 5);
            }
        }
    }

    @Override
    public int getMaxItemUseDuration(@NotNull ItemStack itemstack) {
        return 20;
    }

    @Override
    public @NotNull EnumAction getItemUseAction(@NotNull ItemStack stack) {
        return EnumAction.NONE;
    }

    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true, remap = true)
    public void onUpdateInject(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected, CallbackInfo ci) {
        if (isSelected && !world.isRemote && entity.ticksExisted % 20 == 0 && entity instanceof EntityPlayerMP) {
            this.updateAura(stack, world, (EntityPlayerMP) entity);
        }

        if (isSelected && world.isRemote && entity.ticksExisted % 5 == 0 && entity instanceof EntityPlayer) {
            Entity target = EntityUtils.getPointedEntity(world, entity, 1.0, 16.0, 5.0F, true);

            if (target != null && ScanningManager.isThingStillScannable((EntityPlayer) entity, target)) {
                FXDispatcher.INSTANCE.scanHighlight(target);
            }

            RenderEventHandler.thaumTarget = target;
            RayTraceResult mop = this.getRayTraceResultFromPlayerWild(world, (EntityPlayer) entity, true);

            if (mop != null && ScanningManager.isThingStillScannable((EntityPlayer) entity, mop.getBlockPos())) {
                FXDispatcher.INSTANCE.scanHighlight(mop.getBlockPos());
            }
        }

        ci.cancel();
    }
}
