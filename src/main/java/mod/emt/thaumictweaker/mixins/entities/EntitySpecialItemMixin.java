package mod.emt.thaumictweaker.mixins.entities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import thaumcraft.common.entities.EntitySpecialItem;

@Mixin(value = EntitySpecialItem.class, remap = false)
public abstract class EntitySpecialItemMixin extends EntityItem {
    public EntitySpecialItemMixin(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    /**
     * @author Invadermonky
     * @reason Fixes inconsistent floating behavior for Primordial Pearl when dropped from Thaumcraft bosses.
     */
    @Overwrite
    public void onUpdate() {
        super.onUpdate();
        this.motionX *= 0.9;
        this.motionY *= 0.9;
        this.motionZ *= 0.9;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 0 || pass == 1;
    }
}
