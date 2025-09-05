package mod.emt.thaumictweaker.mixins.blocks;

import mod.emt.thaumictweaker.config.ConfigHandlerTT;
import mod.emt.thaumictweaker.util.helpers.PorousStoneHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.blocks.basic.BlockStonePorous;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(value = BlockStonePorous.class, remap = false)
public class BlockStonePorousMixin {
    @Inject(method = "getDrops", at = @At("HEAD"), cancellable = true)
    private void getDropsMixin(IBlockAccess world, BlockPos pos, IBlockState state, int fortune, CallbackInfoReturnable<List<ItemStack>> cir) {
        if(ConfigHandlerTT.porous_stone.enablePorousStoneTweaks) {
            List<ItemStack> drops = new ArrayList<>();
            Random rand = world instanceof World ? ((World) world).rand : new Random();
            int configChance = ConfigHandlerTT.porous_stone.gravelDropModifier;
            if(rand.nextInt(configChance) + fortune  > configChance - 2) {
                for(int i = 0; i < ConfigHandlerTT.porous_stone.specialItemDrops; i++) {
                    drops.add(PorousStoneHelper.getBlockDrop(rand));
                }
            } else {
                drops.add(new ItemStack(Blocks.GRAVEL));
            }
            cir.setReturnValue(drops);
        }
    }
}
