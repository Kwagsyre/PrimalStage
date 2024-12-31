package com.nanokulon.primalstage.blocks;

import com.nanokulon.primalstage.PrimalStage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.state.property.Properties.LIT;

public interface HeatedBlock {
    default boolean isLit(World world, BlockPos pos) {
        var state = world.getBlockState(pos.down(1));
        if (state.isIn(PrimalStage.LIT_BLOCKS)) {
            if (state.contains(LIT))
                return state.get(LIT);
            else return true;
        } return false;
    }
}
