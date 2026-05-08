package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.util.McddnTags;

public class FortressCapFeature extends Feature<RotatableFeatureConfig> {
    public FortressCapFeature(Codec<RotatableFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RotatableFeatureConfig> featurePlaceContext) {
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource random = featurePlaceContext.random();
        Direction direction = featurePlaceContext.config().direction;
        if (level.getBlockState(blockPos.below()).is(McddnTags.Blocks.BLACKSTONE_TILES)) {
            return false;
        }
        switch (random.nextIntBetweenInclusive(1, 4)) {
            case 1:
                template1(level, blockPos, direction, random);
                break;
            case 2:
                template2(level, blockPos, direction, random);
                break;
            case 3:
                template3(level, blockPos, direction, random);
                break;
            case 4:
                template4(level, blockPos, direction, random);
                break;
        }
        if (random.nextIntBetweenInclusive(1, 5) == 1) {
            placeHole(level, blockPos.relative(direction.getOpposite(), 2), direction, random);
        }
        return true;
    }

    private void template1(WorldGenLevel level, BlockPos blockPos, Direction direction, RandomSource random) {
        placeBaseWall(level, blockPos, direction, random);
        placeWindow(level, blockPos, direction, Blocks.CHISELED_NETHER_BRICKS.defaultBlockState());
        blockPos = blockPos.offset(direction.getOpposite().getNormal()).below();
        if (random.nextFloat() < 0.5F) {
            placeLamp(level, blockPos.above(5).offset(direction.getOpposite().getNormal()), direction);
        }
        blockPos = blockPos.offset(direction.getOpposite().getNormal());
        blockPos = blockPos.above();
        this.setBlock(level, blockPos.above(3).offset(direction.getCounterClockWise().getNormal().multiply(2)), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
        this.setBlock(level, blockPos.above(3).offset(direction.getClockWise().getNormal().multiply(2)), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
        drawLine(level, blockPos.offset(direction.getCounterClockWise().getNormal().multiply(2)), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.UP, 3);
        drawLine(level, blockPos.offset(direction.getClockWise().getNormal().multiply(2)), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.UP, 3);
    }
    private void template2(WorldGenLevel level, BlockPos blockPos, Direction direction, RandomSource random) {
        placeBaseWall(level, blockPos, direction, random);
        BlockState fence = Blocks.NETHER_BRICK_FENCE.defaultBlockState().setValue(CrossCollisionBlock.NORTH, true).setValue(CrossCollisionBlock.EAST, true).setValue(CrossCollisionBlock.WEST, true);
        fence = fence.rotate(direction == Direction.NORTH ? Rotation.NONE : direction == Direction.SOUTH ? Rotation.CLOCKWISE_180 : direction == Direction.EAST ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90);
        drawLine(level, blockPos.above(1).relative(direction.getCounterClockWise(), 2).relative(direction.getOpposite(), 2), fence, direction.getClockWise(), 5);
        drawLine(level, blockPos.above(1).relative(direction.getCounterClockWise(), 2).relative(direction.getOpposite(), 1), fence.rotate(Rotation.CLOCKWISE_180), direction.getClockWise(), 5);
        drawLine(level, blockPos.above(2).relative(direction.getCounterClockWise(), 2).relative(direction.getOpposite(), 1), McddnBlocks.POLISHED_NETHERRACK.defaultBlockState(), direction.getClockWise(), 5);
        drawLine(level, blockPos.relative(direction.getCounterClockWise(), 2).relative(direction.getOpposite(), 1), McddnBlocks.POLISHED_NETHERRACK.defaultBlockState(), direction.getClockWise(), 5);
        drawLine(level, blockPos.above(2).relative(direction.getClockWise(), 4).relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.DOWN, 3);
        drawLine(level, blockPos.above(2).relative(direction.getClockWise(), 3).relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.DOWN, 3);
        drawLine(level, blockPos.above(2).relative(direction.getClockWise(), -3).relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.DOWN, 3);
        drawLine(level, blockPos.above(2).relative(direction.getClockWise(), -4).relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.DOWN, 3);
        this.setBlock(level, blockPos.above(3).relative(direction.getClockWise(), 4).relative(direction.getOpposite(), 2), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
        this.setBlock(level, blockPos.above(3).relative(direction.getClockWise(), -4).relative(direction.getOpposite(), 2), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
    }
    private void template3(WorldGenLevel level, BlockPos blockPos, Direction direction, RandomSource random) {
        placeBaseWall(level, blockPos, direction, random);
        drawLine(level, blockPos.above(0).relative(direction.getCounterClockWise(), 4).relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), direction.getClockWise(), 9);
        drawLine(level, blockPos.above(1).relative(direction.getCounterClockWise(), 4).relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), direction.getClockWise(), 9);
        drawLine(level, blockPos.above(2).relative(direction.getCounterClockWise(), 4).relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), direction.getClockWise(), 9);

        placeWindow(level, blockPos.above(2), direction, Blocks.CHISELED_NETHER_BRICKS.defaultBlockState());
        placeWindow(level, blockPos.relative(direction.getClockWise(), 2), direction, Blocks.CHISELED_NETHER_BRICKS.defaultBlockState());
        placeWindow(level, blockPos.relative(direction.getClockWise(), -2), direction, Blocks.CHISELED_NETHER_BRICKS.defaultBlockState());

        this.setBlock(level, blockPos.above(3).relative(direction.getClockWise(), 4).relative(direction.getOpposite(), 2), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
        this.setBlock(level, blockPos.above(3).relative(direction.getClockWise(), -4).relative(direction.getOpposite(), 2), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
    }
    private void template4(WorldGenLevel level, BlockPos blockPos, Direction direction, RandomSource random) {
        placeBaseWall(level, blockPos, direction, random);
        drawLine(level, blockPos.relative(direction.getOpposite(), 2), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.UP, 3);
        drawLine(level, blockPos.relative(direction.getOpposite(), 2).relative(direction.getClockWise(), 4), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.UP, 3);
        drawLine(level, blockPos.relative(direction.getOpposite(), 2).relative(direction.getClockWise(), -4), McddnBlocks.NETHER_BRICK_PILLAR.defaultBlockState(), Direction.UP, 3);
        this.setBlock(level, blockPos.above(3).relative(direction.getOpposite(), 2), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
        this.setBlock(level, blockPos.above(3).relative(direction.getOpposite(), 2).relative(direction.getClockWise(), 4), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
        this.setBlock(level, blockPos.above(3).relative(direction.getOpposite(), 2).relative(direction.getClockWise(), -4), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());

        placeWindow(level, blockPos.above(random.nextIntBetweenInclusive(0, 1)).relative(direction.getClockWise(), 2), direction, McddnBlocks.POLISHED_NETHERRACK.defaultBlockState());
        placeWindow(level, blockPos.above(random.nextIntBetweenInclusive(0, 1)).relative(direction.getClockWise(), -2), direction, McddnBlocks.POLISHED_NETHERRACK.defaultBlockState());
    }

    private void drawLine(WorldGenLevel level, BlockPos blockPos, BlockState blockState, Direction direction, int length) {
        for (var i = 0; i < length; i++) {
            this.setBlock(level, blockPos.offset(direction.getNormal().multiply(i)), blockState);
        }
    }
    private void placeHole(WorldGenLevel level, BlockPos blockPos, Direction direction, RandomSource random) {
        drawLine(level, blockPos.relative(direction.getClockWise(), -1).below(2), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 3);
        drawLine(level, blockPos.relative(direction.getClockWise(), -3).below(1), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 7);
        drawLine(level, blockPos.relative(direction.getClockWise(), -3), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 7);
        drawLine(level, blockPos.relative(direction.getClockWise(), -3).above(1), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 7);
        drawLine(level, blockPos.relative(direction.getClockWise(), -2).above(2), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 6);
        this.setBlock(level, blockPos.above(2).relative(direction.getClockWise(), random.nextIntBetweenInclusive(-2, 3)), McddnBlocks.NETHERITE_CHAIN.defaultBlockState());

        drawLine(level, blockPos.relative(direction.getClockWise(), -2).relative(direction).below(3), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 4);
        drawLine(level, blockPos.relative(direction.getClockWise(), -2).relative(direction).below(2), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 4);
        drawLine(level, blockPos.relative(direction.getClockWise(), -2).relative(direction).below(1), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 3);
        drawLine(level, blockPos.relative(direction.getClockWise(), -2).relative(direction), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 3);
        this.setBlock(level, blockPos.relative(direction.getClockWise(), -2).relative(direction).above(1), Blocks.AIR.defaultBlockState());

        drawLine(level, blockPos.relative(direction.getClockWise(), -3).relative(direction.getOpposite(), 1).below(), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 6);
        drawLine(level, blockPos.relative(direction.getClockWise(), -2).relative(direction.getOpposite(), 2).below(), Blocks.AIR.defaultBlockState(), direction.getClockWise(), 5);
        this.setBlock(level, blockPos.relative(direction.getClockWise(), -1).relative(direction.getOpposite(), 3).below(), Blocks.AIR.defaultBlockState());
    }
    private void placeLamp(WorldGenLevel level, BlockPos blockPos, Direction direction) {
        BlockPos mutable = blockPos;
        this.setBlock(level, mutable, Blocks.BLACKSTONE.defaultBlockState());
        mutable = mutable.offset(direction.getOpposite().getNormal());
        BlockState wallState = Blocks.POLISHED_BLACKSTONE_BRICK_WALL.defaultBlockState().setValue(WallBlock.UP, false).setValue(WallBlock.NORTH_WALL, WallSide.LOW).setValue(WallBlock.SOUTH_WALL, WallSide.LOW);
        this.setBlock(level, mutable, direction.getAxis() == Direction.Axis.X ? wallState.rotate(Rotation.CLOCKWISE_90) : wallState);
        mutable = mutable.offset(direction.getOpposite().getNormal());
        wallState = wallState.setValue(WallBlock.UP, true).setValue(WallBlock.SOUTH_WALL, WallSide.NONE);
        wallState = switch (direction) {
            case NORTH -> wallState;
            case SOUTH -> wallState.rotate(Rotation.CLOCKWISE_180);
            case EAST -> wallState.rotate(Rotation.CLOCKWISE_90);
            default -> wallState.rotate(Rotation.COUNTERCLOCKWISE_90);
        };
        this.setBlock(level, mutable, wallState);
        this.setBlock(level, mutable.below(), Blocks.CHAIN.defaultBlockState());
        this.setBlock(level, mutable.below(2), Blocks.REDSTONE_LAMP.defaultBlockState().setValue(RedstoneLampBlock.LIT, true));
        this.setBlock(level, mutable.below(3), Blocks.POLISHED_BLACKSTONE_BUTTON.defaultBlockState().setValue(ButtonBlock.POWERED, true).setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.CEILING));
    }
    private void placeWindow(WorldGenLevel level, BlockPos blockPos, Direction direction, BlockState top) {
        blockPos = blockPos.relative(direction.getOpposite(), 2);
        BlockState fence = Blocks.NETHER_BRICK_FENCE.defaultBlockState().setValue(CrossCollisionBlock.NORTH, true).setValue(CrossCollisionBlock.EAST, true).setValue(CrossCollisionBlock.WEST, true);
        fence = fence.rotate(direction == Direction.NORTH ? Rotation.NONE : direction == Direction.SOUTH ? Rotation.CLOCKWISE_180 : direction == Direction.EAST ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90);
        drawLine(level, blockPos, fence, Direction.UP, 2);
        drawLine(level, blockPos.relative(direction, 1), fence.rotate(Rotation.CLOCKWISE_180), Direction.UP, 2);
        drawLine(level, blockPos.below(), McddnBlocks.POLISHED_NETHERRACK.defaultBlockState(), direction, 2);
        drawLine(level, blockPos.above(2), top, direction, 2);
    }
    private void placeBaseWall(WorldGenLevel level, BlockPos blockPos, Direction direction, RandomSource random){
        BlockPos corner = blockPos.below(3).offset(direction.getOpposite().getNormal()).offset(direction.getCounterClockWise().getNormal().multiply(4));
        for (var j = 0; j < 16; j++) {
            BlockState blockState = j == 0 || j == 7 || j == 12 || j == 15 ? McddnBlocks.POLISHED_NETHERRACK.defaultBlockState() : Blocks.NETHER_BRICKS.defaultBlockState();
            drawLine(level, corner.above(j), blockState, direction.getClockWise(), 9);
        }
        for (var i = 0; i < 9; i++) {
            if (!level.getBlockState(corner.above(24).offset(direction.getClockWise().getNormal().multiply(i))).isAir()) {
                drawLine(level, corner.above(23).offset(direction.getClockWise().getNormal().multiply(i)), Blocks.NETHER_BRICKS.defaultBlockState(), Direction.DOWN, random.nextIntBetweenInclusive(1, 3));
            }
        }
        drawLine(level, corner.above(12).offset(direction.getOpposite().getNormal()), McddnBlocks.ORNATE_NETHER_TILES.defaultBlockState(), direction.getClockWise(), 9);
        corner = corner.offset(direction.getOpposite().getNormal()).above(2);
        for (var j = 0; j < 5; j++) {
            BlockState blockState = j == 4 ? McddnBlocks.POLISHED_NETHERRACK.defaultBlockState() : Blocks.NETHER_BRICKS.defaultBlockState();
            drawLine(level, corner.above(j), blockState, direction.getClockWise(), 9);
        }
        corner = corner.offset(direction.getOpposite().getNormal());
        drawLine(level, corner.offset(direction.getClockWise().getNormal().multiply(2)), McddnBlocks.ORNATE_NETHER_TILES.defaultBlockState(), direction.getClockWise(), 5);
        corner = corner.offset(direction.getOpposite().getNormal());
        if (level.getBlockState(corner.offset(direction.getClockWise().getNormal().multiply(4))).is(McddnTags.Blocks.BLACKSTONE_TILES)) {
            drawLine(level, corner.offset(direction.getClockWise().getNormal().multiply(3)), McddnBlocks.ORNATE_BLACKSTONE_TILES.defaultBlockState(), direction.getClockWise(), 3);
        }
        BlockState fence = Blocks.NETHER_BRICK_FENCE.defaultBlockState().setValue(CrossCollisionBlock.EAST, true).setValue(CrossCollisionBlock.WEST, true);
        fence = direction.getAxis() == Direction.Axis.X ? fence.rotate(Rotation.CLOCKWISE_90) : fence;
        this.setBlock(level, blockPos.above(5).relative(direction.getOpposite()), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
        this.setBlock(level, blockPos.above(6).relative(direction.getOpposite()), fence);
        this.setBlock(level, blockPos.above(7).relative(direction.getOpposite()), fence);
        this.setBlock(level, blockPos.above(8).relative(direction.getOpposite()), McddnBlocks.ORNATE_POLISHED_NETHERRACK.defaultBlockState());
    }
}
