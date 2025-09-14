package mod.emt.thaumictweaker.mixins.loottables.boss;

import mod.emt.thaumictweaker.util.libs.LootTablesTT;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;

@Mixin(value = EntityEldritchWarden.class, remap = false)
public abstract class EntityEldritchWardenMixin extends EntityMob {
    public EntityEldritchWardenMixin(World worldIn) {
        super(worldIn);
    }

    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return LootTablesTT.ELDRITCH_WARDEN;
    }
}
