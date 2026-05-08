package net.redupro.mcd_d_nether.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WartHangingBlock extends Block {
    VoxelShape SHAPE = Block.box(0,15,0,16,16,16);
    public WartHangingBlock(Properties properties) {
        super(properties);
    }
    @Override
    protected boolean skipRendering(BlockState blockState, BlockState blockState2, Direction direction) {
        return blockState2.is(this) || super.skipRendering(blockState, blockState2, direction);
    }
    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        if(world.getBlockState(pos.above()).is(Blocks.WARPED_WART_BLOCK)) {
            return state;
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }
}
