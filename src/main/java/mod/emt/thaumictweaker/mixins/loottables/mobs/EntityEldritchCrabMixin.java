package mod.emt.thaumictweaker.mixins.loottables.mobs;

import mod.emt.thaumictweaker.util.libs.LootTablesTT;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.entities.monster.EntityEldritchCrab;

@Mixin(value = EntityEldritchCrab.class, remap = false)
public abstract class EntityEldritchCrabMixin extends EntityMob {
    public EntityEldritchCrabMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(method = "dropFewItems", at = @At("HEAD"), cancellable = true, remap = true)
    private void cancelDropFewItems(boolean flag, int fortune, CallbackInfo ci) {
        ci.cancel();
    }

    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return LootTablesTT.ELDRITCH_CRAB;
    }
}
