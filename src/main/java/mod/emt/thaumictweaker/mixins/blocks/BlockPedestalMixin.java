package mod.emt.thaumictweaker.mixins.blocks;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.blocks.BlockTCTile;
import thaumcraft.common.blocks.devices.BlockPedestal;
import thaumcraft.common.tiles.crafting.TilePedestal;

@Mixin(value = BlockPedestal.class, remap = false)
public abstract class BlockPedestalMixin extends BlockTCTile {
    public BlockPedestalMixin(Material mat, Class tc, String name) {
        super(mat, tc, name);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasComparatorInputOverride(@NotNull IBlockState state) {
        return ConfigEnhancementsTT.enablePedestalComparator;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getComparatorInputOverride(@NotNull IBlockState blockState, @NotNull World worldIn, @NotNull BlockPos pos) {
        if(ConfigEnhancementsTT.enablePedestalComparator) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof TilePedestal) {
                return ItemHandlerHelper.calcRedstoneFromInventory(new InvWrapper((TilePedestal) tile));
            }
        }
        return 0;
    }
}
