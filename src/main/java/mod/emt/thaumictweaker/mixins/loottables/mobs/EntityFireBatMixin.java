package mod.emt.thaumictweaker.mixins.loottables.mobs;

import mod.emt.thaumictweaker.util.libs.LootTablesTT;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.entities.monster.EntityFireBat;

@Mixin(value = EntityFireBat.class, remap = false)
public abstract class EntityFireBatMixin extends EntityMob {
    public EntityFireBatMixin(World worldIn) {
        super(worldIn);
    }

    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return LootTablesTT.FIREBAT;
    }
}
