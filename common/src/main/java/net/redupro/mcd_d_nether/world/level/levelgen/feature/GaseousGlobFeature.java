package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.block.custom.GlobBlock;


public class GaseousGlobFeature extends Feature<NoneFeatureConfiguration> {
    public GaseousGlobFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource random = context.random();
        BlockPos blockPos = context.origin();
        WorldGenLevel structureWorldAccess = context.level();

        int i = 0;

        if (random.nextIntBetweenInclusive(1, 10) < 7) {
            i += GlobBlock.placeAt(structureWorldAccess, McddnBlocks.GASEOUS_GLOB.defaultBlockState().setValue(GlobBlock.FACING, Direction.NORTH), blockPos.south()) ? 1 : 0;
        }
        if (random.nextIntBetweenInclusive(1, 10) < 7) {
            i += GlobBlock.placeAt(structureWorldAccess, McddnBlocks.GASEOUS_GLOB.defaultBlockState().setValue(GlobBlock.FACING, Direction.SOUTH), blockPos.north()) ? 1 : 0;
        }
        if (random.nextIntBetweenInclusive(1, 10) < 7) {
            i += GlobBlock.placeAt(structureWorldAccess, McddnBlocks.GASEOUS_GLOB.defaultBlockState().setValue(GlobBlock.FACING, Direction.WEST), blockPos.east()) ? 1 : 0;
        }
        if (random.nextIntBetweenInclusive(1, 10) < 7) {
            i += GlobBlock.placeAt(structureWorldAccess, McddnBlocks.GASEOUS_GLOB.defaultBlockState().setValue(GlobBlock.FACING, Direction.EAST), blockPos.west()) ? 1 : 0;
        }
        if (random.nextIntBetweenInclusive(1, 10) < 7 && i > 0) {
            i += GlobBlock.placeAt(structureWorldAccess, McddnBlocks.GASEOUS_GLOB.defaultBlockState().setValue(GlobBlock.FACING, Direction.DOWN), blockPos.above()) ? 1 : 0;
        }
        if (random.nextIntBetweenInclusive(1, 10) < 7) {
            i += GlobBlock.placeAt(structureWorldAccess, McddnBlocks.GASEOUS_GLOB.defaultBlockState().setValue(GlobBlock.FACING, Direction.UP), blockPos.below()) ? 1 : 0;
        }

        return i > 0;
    }
}
