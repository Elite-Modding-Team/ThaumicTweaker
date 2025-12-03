package mod.emt.thaumictweaker.mixins.entities;

import mod.emt.thaumictweaker.util.helpers.FluxRiftHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.entities.EntityFluxRift;

@Mixin(value = EntityFluxRift.class, remap = false)
public abstract class EntityFluxRiftMixin extends Entity {
    @Shadow int maxSize;

    public EntityFluxRiftMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(
            method = "completeCollapse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendToAllAround(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;Lnet/minecraftforge/fml/common/network/NetworkRegistry$TargetPoint;)V"
            )
    )
    private void addRiftDropsMixin(CallbackInfo ci) {
        NonNullList<ItemStack> drops = FluxRiftHelper.getCollapsingRiftDrops(this.maxSize);
        for(ItemStack drop : drops) {
            if(!drop.isEmpty()) {
                this.entityDropItem(drop, 0);
            }
        }
    }
}
