package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.util.McddnTags;

public class BigFortressCapFeature extends Feature<RotatableFeatureConfig> {
    public BigFortressCapFeature(Codec<RotatableFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RotatableFeatureConfig> featurePlaceContext) {
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin().below();
        BlockPos corner;
        boolean bigNeighbor = level.getBlockState(blockPos.below()).is(McddnTags.Blocks.ORNATE_BLACKSTONE_TILES);
        boolean patchMode = level.getBlockState(blockPos.below()).is(McddnTags.Blocks.BLACKSTONE_TILES) && !bigNeighbor;
        Direction direction = featurePlaceContext.config().direction;

        corner = blockPos.above(9).offset(direction.getCounterClockWise().getUnitVec3i().multiply(4));
        if (patchMode) {
            for (var j = 0; j < 4; j++) {
                BlockState blockState = j == 0 || j == 3 ? McddnBlocks.POLISHED_NETHERRACK.defaultBlockState() : Blocks.NETHER_BRICKS.defaultBlockState();
                drawLine(level, corner.above(j), blockState, direction.getClockWise(), 9);
            }
            drawLine(level, corner.below(), Blocks.NETHER_BRICKS.defaultBlockState(), direction.getClockWise(), 9);
            drawLine(level, corner.below(2), McddnBlocks.POLISHED_NETHERRACK.defaultBlockState(), direction.getClockWise(), 9);
            drawLine(level, corner.below(9), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.UP, 7);
            drawLine(level, corner.below(9).offset(direction.getClockWise().getUnitVec3i().multiply(8)), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.UP, 7);
            this.setBlock(level, corner.below(2), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
            this.setBlock(level, corner.below(2).offset(direction.getClockWise().getUnitVec3i().multiply(8)), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
            this.setBlock(level, corner.below(2).offset(direction.getClockWise().getUnitVec3i().multiply(4)), Blocks.CHISELED_NETHER_BRICKS.defaultBlockState());
        } else if (!bigNeighbor) {
            corner = corner.below(10).offset(direction.getOpposite().getUnitVec3i());
            drawLine(level, corner, McddnBlocks.POLISHED_NETHERRACK.defaultBlockState(), direction.getClockWise(), 9);
            corner = corner.above().offset(direction.getCounterClockWise().getUnitVec3i().multiply(2));
            if (level.getBlockState(blockPos.below(2).offset(direction.getOpposite().getUnitVec3i())).is(Blocks.CHISELED_NETHER_BRICKS)) {
                for (var j = 0; j < 5; j++) {
                    BlockState blockState = j == 4 ? McddnBlocks.POLISHED_NETHERRACK.defaultBlockState() : Blocks.NETHER_BRICKS.defaultBlockState();
                    drawLine(level, corner.above(j), blockState, direction.getClockWise(), 15);
                }
            }
        }
        return true;
    }

    private void drawLine(WorldGenLevel level, BlockPos blockPos, BlockState blockState, Direction direction, int length) {
        for (var i = 0; i < length; i++) {
            this.setBlock(level, blockPos.offset(direction.getUnitVec3i().multiply(i)), blockState);
        }
    }
}
