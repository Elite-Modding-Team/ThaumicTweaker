package mod.emt.thaumictweaker.util.helpers;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeHelper {
    /** The maximum distance that will be searched when looking for tree blocks to break. */
    private static final int TREE_BLOCK_RANGE = 32;
    /** The maximum number of leaf blocks that can be considered part of the tree structure. */
    private static final int LEAF_BLOCK_RANGE = 3;

    /**
     * Determines if the passed position is part of a tree structure.
     *
     * @param world the world object
     * @param pos the origin position
     * @return true if the passed position is part of a tree structure
     */
    public static boolean isTreeStructure(World world, BlockPos pos) {
        if(isWoodBlock(world, pos)) {
            for(int height = 0; height < TREE_BLOCK_RANGE; height++) {
                for(int x = -1; x <= 1; x++) {
                    for(int z = -1; z <= 1; z++) {
                        if(isTreeLeafBlock(world, pos.add(x, height, z))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets a queue of all wood blocks associated with this tree structure.
     *
     * @param world
     * @param origin
     * @param interactFace
     * @return
     */
    public static Queue<BlockPos> getTreeStructure(World world, BlockPos origin, EnumFacing interactFace) {
        Queue<BlockPos> treeStructure = new ArrayDeque<>();

        int height = 0;
        BlockPos checkPos = origin;
        while(origin != null && height < 32) {
            height++;
        }

        return treeStructure;
    }

    public static boolean isWoodBlock(World world, BlockPos pos) {
        return isWoodBlock(world.getBlockState(pos));
    }

    public static boolean isWoodBlock(IBlockState state) {
        return state.getBlock() instanceof BlockLog;
    }

    public static boolean isTreeLeafBlock(World world, BlockPos pos) {
        return isTreeLeafBlock(world.getBlockState(pos));
    }

    public static boolean isTreeLeafBlock(IBlockState state) {
        //Tree leaves are decayable, but not currently decaying
        return state.getBlock() instanceof BlockLeaves && state.getValue(BlockLeaves.DECAYABLE) && !state.getValue(BlockLeaves.CHECK_DECAY);
    }
}
