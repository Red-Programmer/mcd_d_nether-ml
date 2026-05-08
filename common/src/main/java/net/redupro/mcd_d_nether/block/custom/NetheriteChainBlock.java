package net.redupro.mcd_d_nether.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class NetheriteChainBlock extends ChainBlock {
    private static final Map<Direction.Axis, VoxelShape> SHAPES = Map.of(
            Direction.Axis.X, Block.box(0.0, 4.5, 4.5, 16.0, 11.5, 11.5),
            Direction.Axis.Y, Block.box(4.5, 0.0, 4.5, 11.5, 16.0, 11.5),
            Direction.Axis.Z, Block.box(4.5, 4.5, 0.0, 11.5, 11.5, 16.0));

    public NetheriteChainBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPES.get(blockState.getValue(AXIS));
    }
}
