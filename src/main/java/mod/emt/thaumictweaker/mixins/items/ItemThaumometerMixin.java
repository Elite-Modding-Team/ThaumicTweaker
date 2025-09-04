package mod.emt.thaumictweaker.mixins.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.items.tools.ItemThaumometer;

@Mixin(value = ItemThaumometer.class, remap = false)
public class ItemThaumometerMixin {

    //No more excessive bobbing when scanning
    @Inject(method = "onItemRightClick", at = @At(value = "RETURN"), cancellable = true)
    public void stabilizeThaumometer(World world, EntityPlayer p, EnumHand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        cir.setReturnValue(new ActionResult<>(EnumActionResult.FAIL, p.getHeldItem(hand)));
    }
}
