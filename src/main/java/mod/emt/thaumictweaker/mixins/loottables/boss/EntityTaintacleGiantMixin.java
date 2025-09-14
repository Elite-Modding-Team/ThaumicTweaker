package mod.emt.thaumictweaker.mixins.loottables.boss;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
import thaumcraft.common.entities.monster.tainted.EntityTaintacle;

@Mixin(value = EntityTaintacleGiant.class, remap = false)
public abstract class EntityTaintacleGiantMixin extends EntityTaintacle {

    public EntityTaintacleGiantMixin(World par1World) {
        super(par1World);
    }

    //Need to override the loot table so dropFewItems fires correctly.
    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return null;
    }
}
