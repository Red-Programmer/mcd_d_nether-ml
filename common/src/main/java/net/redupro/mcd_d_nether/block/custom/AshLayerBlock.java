package net.redupro.mcd_d_nether.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.redupro.mcd_d_nether.registry.McddnBlocks;

public class AshLayerBlock extends SnowLayerBlock {
    public AshLayerBlock(Properties properties) {
        super(properties);
    }
    @Override
    protected void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (level.getBlockState(blockPos.below()).is(Blocks.BASALT)) {
            if (level.getBlockState(blockPos.below()).getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y) {
                level.setBlock(blockPos.below(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
            }
        }
    }
    @Override
    protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {

    }
}
