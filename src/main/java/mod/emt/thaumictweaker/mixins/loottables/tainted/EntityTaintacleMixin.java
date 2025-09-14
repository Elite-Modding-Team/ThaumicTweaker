package mod.emt.thaumictweaker.mixins.loottables.tainted;

import mod.emt.thaumictweaker.util.libs.LootTablesTT;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.entities.monster.tainted.EntityTaintacle;

@Mixin(value = EntityTaintacle.class, remap = false)
public abstract class EntityTaintacleMixin extends EntityMob {
    public EntityTaintacleMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(method = "dropFewItems", at = @At("HEAD"), cancellable = true, remap = true)
    private void cancelDropFewItems(boolean flag, int fortune, CallbackInfo ci) {
        ci.cancel();
    }

    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return LootTablesTT.TAINTACLE;
    }
}
