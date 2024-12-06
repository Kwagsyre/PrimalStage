package com.nanokulon.primalstage.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface HeatedBlock {
    default boolean isLit(World world, BlockPos pos) {
        var state = world.getBlockState(pos.down(1));
        var block = state.getBlock();
        return block.equals(Blocks.FIRE) || (block.equals(Blocks.CAMPFIRE) && state.get(CampfireBlock.LIT));
    }
}
