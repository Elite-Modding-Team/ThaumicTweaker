package mod.emt.thaumictweaker.mixins.loottables.boss;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;

@Mixin(value = EntityThaumcraftBoss.class, remap = false)
public abstract class EntityThaumcraftBossMixin extends EntityMob {
    public EntityThaumcraftBossMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(method = "dropFewItems", at = @At("HEAD"), cancellable = true, remap = true)
    private void cancelDropFewItems(boolean flag, int fortune, CallbackInfo ci) {
        ci.cancel();
    }

    @Override
    public @Nullable EntityItem entityDropItem(ItemStack stack, float offsetY) {
        if (!stack.isEmpty()) {
            EntityItem entity;
            if (stack.getItem() == ItemsTC.primordialPearl) {
                entity = new EntitySpecialItem(this.world, this.posX, this.posY + offsetY, this.posZ, stack);
                entity.motionX = 0.0;
                entity.motionY = 0.1;
                entity.motionZ = 0.0;
            } else {
                entity = new EntityItem(this.world, this.posX, this.posY + offsetY, this.posZ, stack);
            }

            entity.setDefaultPickupDelay();
            entity.setNoDespawn();
            if (this.captureDrops) {
                this.capturedDrops.add(entity);
            } else {
                this.world.spawnEntity(entity);
            }

            return entity;
        }
        return null;
    }
}
