package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;


public class SimpleStackedFeature extends Feature<SimpleRandomFeatureConfiguration> {

    public SimpleStackedFeature(Codec<SimpleRandomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleRandomFeatureConfiguration> context) {
        RandomSource random = context.random();
        SimpleRandomFeatureConfiguration simpleRandomFeatureConfig = context.config();
        WorldGenLevel structureWorldAccess = context.level();
        BlockPos blockPos = context.origin();
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        boolean ret = false;
        int max_i = simpleRandomFeatureConfig.features.size();
        for (int i = 0; i < max_i; i++) {
            ret |= simpleRandomFeatureConfig.features.get(i).value().place(structureWorldAccess, chunkGenerator, random, blockPos);
        }
        return ret;
    }
}
