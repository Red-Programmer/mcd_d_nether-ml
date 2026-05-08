package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.ticks.TickPriority;
import net.redupro.mcd_d_nether.util.McddnTags;

import static java.lang.Math.max;

public class VentFeature extends Feature<VentFeatureConfig> {
    public VentFeature(Codec<VentFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<VentFeatureConfig> featurePlaceContext) {
        RandomSource random = featurePlaceContext.random();
        BlockPos blockPos = featurePlaceContext.origin();
        WorldGenLevel level = featurePlaceContext.level();
        BlockState fill = featurePlaceContext.config().fill.getState(level, random, blockPos);
        float height = featurePlaceContext.config().height;
        float depth = featurePlaceContext.config().depth;
        float radius = nextFloatBetweenInclusive(featurePlaceContext.config().minRadius, featurePlaceContext.config().maxRadius, random);
        float length = nextFloatBetweenInclusive(featurePlaceContext.config().maxRadius, featurePlaceContext.config().maxLength, random);
        float rotation = nextFloatBetweenInclusive(0, 3.14F, random);

        while(!level.getBlockState(blockPos).is(McddnTags.Blocks.VENT_REPLACEABLES) || !level.getBlockState(blockPos.above()).isAir()) {
            blockPos = blockPos.below();
            if (blockPos.getY() < 32) return false;
        }
        for(int i = Mth.floor(-length); i <= Mth.ceil(length); i++) {
            for(int j = Mth.floor(-length); j <= Mth.ceil(length); j++) {
                float sample = sampleEllipse(i, j, radius, length, rotation);
                genColumn(level, blockPos.offset((int) i, 0, (int) j), fill, height, depth, sample);
            }
        }

        return true;
    }

    private void genColumn(WorldGenLevel level, BlockPos blockPos, BlockState fill, float height, float depth, float sample) {
        if(sample < 3.0F) {
            BlockPos.MutableBlockPos mutable = matchTerrain(level, blockPos);
            int steepness = max(0, blockPos.getY() - mutable.getY());
            steepness = steepness > 12 ? 0 : steepness;
            if(mutable.getY() >= 32 && mutable.getY() < level.getMaxY() - 10) {
                if (sample < 0.3F) {
                    this.setBlock(level, mutable.above((int) height), Blocks.AIR.defaultBlockState());
                    this.setBlock(level, mutable.below((int) -(height-1)), fill);
                    level.scheduleTick(mutable.below((int) -(height-1)), Fluids.LAVA, 0, TickPriority.VERY_HIGH);
                    this.setBlock(level, mutable.below((int) (1-(height-1))), Blocks.BLACKSTONE.defaultBlockState());
                } else if (sample < 1.0F) {
                    this.setBlock(level, mutable.above((int) height), Blocks.AIR.defaultBlockState());
                    this.setBlock(level, mutable.below((int) -(height-1)), Blocks.MAGMA_BLOCK.defaultBlockState());
                    this.setBlock(level, mutable.below((int) (1-(height-1))), Blocks.BLACKSTONE.defaultBlockState());
                } else if (sample < 2.5F) {
                    this.setBlock(level, mutable, Blocks.BLACKSTONE.defaultBlockState());
                    this.setBlock(level, mutable.below(), Blocks.BLACKSTONE.defaultBlockState());
                    for(int i = 1; i <= height + (steepness/2F); i++) {
                        this.setBlock(level, mutable.above(i), Blocks.BLACKSTONE.defaultBlockState());
                    }
                    removeUnstable(level, mutable);
                } else {
                    this.setBlock(level, mutable, Blocks.BLACKSTONE.defaultBlockState());
                    this.setBlock(level, mutable.below(), Blocks.BLACKSTONE.defaultBlockState());
                    removeUnstable(level, mutable);
                }
            }
        }
    }
    private void removeUnstable(WorldGenLevel level, BlockPos.MutableBlockPos mutable) {
        if(!level.getBlockState(mutable.above()).canSurvive(level, mutable.above())) {
            this.setBlock(level, mutable.above(), Blocks.AIR.defaultBlockState());
        }
        if(!level.getBlockState(mutable.above(2)).canSurvive(level, mutable.above())) {
            this.setBlock(level, mutable.above(2), Blocks.AIR.defaultBlockState());
        }
    }
    private BlockPos.MutableBlockPos matchTerrain(WorldGenLevel level, BlockPos blockPos) {
        BlockPos.MutableBlockPos mutable = blockPos.mutable();
        while(level.getBlockState(mutable.above()).is(McddnTags.Blocks.VENT_REPLACEABLES) && mutable.getY() < level.getMaxY() - 10) {
            replaceNeighborTerrain(level, mutable, Blocks.BLACKSTONE.defaultBlockState());
            mutable = mutable.move(Direction.UP);
        }
        while(!level.getBlockState(mutable).is(McddnTags.Blocks.VENT_REPLACEABLES) && mutable.getY() >= 32) {
            replaceNeighborTerrain(level, mutable, Blocks.BLACKSTONE.defaultBlockState());
            mutable = mutable.move(Direction.DOWN);
        }
        return mutable;
    }

    private void replaceNeighborTerrain(WorldGenLevel level, BlockPos blockPos, BlockState blockState) {
        for(Direction dir : Direction.values()) {
            if(level.getBlockState(blockPos.relative(dir)).is(McddnTags.Blocks.VENT_REPLACEABLES)) {
                this.setBlock(level, blockPos.relative(dir), blockState);
            }
        }
        if(level.getBlockState(blockPos).is(McddnTags.Blocks.VENT_REPLACEABLES)) {
            this.setBlock(level, blockPos, blockState);
        }
    }

    private float sampleEllipse(float x, float y, float radius, float length, float rotation) {
        return Mth.square(x * Mth.cos(rotation) + y * Mth.sin(rotation)) / Mth.square(length) + Mth.square(x * Mth.sin(rotation) - y * Mth.cos(rotation)) / Mth.square(radius);
    }

    private float nextFloatBetweenInclusive(float i, float j, RandomSource random) {
        return (random.nextFloat() * (j - i)) + i;
    }
}
