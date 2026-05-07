package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.redupro.mcd_d_nether.block.custom.MouldBlock;
import net.redupro.mcd_d_nether.block.custom.QuadBrushBlock;

import java.util.Arrays;

public class AdvancedBlockFeature extends Feature<AdvancedBlockFeatureConfig> {
    public AdvancedBlockFeature(Codec<AdvancedBlockFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<AdvancedBlockFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        AdvancedBlockFeatureConfig config = context.config();
        BlockState quadId = config.quadState.getBlock().defaultBlockState();
        Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        Direction facing = directions[random.nextIntBetweenInclusive(0, 3)];
        BlockPos offset = blockPos.relative(facing.getClockWise(Direction.Axis.Y));
        if (quadId.canSurvive(world, blockPos)) {
            if (quadId.getBlock() instanceof QuadBrushBlock) {
                if (world.getBlockState(blockPos.above()).canBeReplaced() && world.getBlockState(offset).canBeReplaced() && world.getBlockState(offset.above()).canBeReplaced()) {
                    if (world.getBlockState(offset.below()).is(Blocks.WARPED_NYLIUM)) {
                        BlockState[] states = {
                                world.getBlockState(blockPos.relative(facing.getOpposite())),
                                world.getBlockState(offset.relative(facing.getOpposite())),
                                world.getBlockState(blockPos.above().relative(facing.getOpposite())),
                                world.getBlockState(offset.above().relative(facing.getOpposite()))
                        };
                        if (Arrays.stream(states).limit(4).anyMatch(s -> s.is(Blocks.NETHERRACK) ||
                                        s.getBlock() instanceof QuadBrushBlock && s.trySetValue(QuadBrushBlock.FACING, facing) == s)) {
                            QuadBrushBlock.placeAt(world, quadId, blockPos, facing, 2);
                            return true;
                        }
                    }
                }
            }
            if (quadId.getBlock() instanceof MouldBlock) {
                if (world.getBlockState(blockPos.above()).canBeReplaced()) {
                    return MouldBlock.placeAt(world, quadId, blockPos.above(2));
                }
            }
        }
        return false;
    }
}
