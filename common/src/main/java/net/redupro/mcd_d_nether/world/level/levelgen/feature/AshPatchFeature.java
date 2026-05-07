package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.redupro.mcd_d_nether.registry.McddnBlocks;


public class AshPatchFeature extends Feature<RandomPatchConfiguration> {
    public AshPatchFeature(Codec<RandomPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        RandomPatchConfiguration randomPatchFeatureConfig = context.config();
        RandomSource random = context.random();
        BlockPos blockPos = context.origin();
        WorldGenLevel world = context.level();

        int i = 0;
        BlockPos mutable;
        int j = randomPatchFeatureConfig.xzSpread() + 1;
        int k = randomPatchFeatureConfig.ySpread() + 1;
        int x;
        int y;
        int z;

        for (int l = 0; l < randomPatchFeatureConfig.tries(); l++) {
            x = random.nextInt(j) - random.nextInt(j);
            y = random.nextInt(k) - random.nextInt(k);
            z = random.nextInt(j) - random.nextInt(j);
            mutable = blockPos.offset(x, y, z);
            if ((world.getBlockState(mutable.below()).is(Blocks.BASALT) || (world.getBlockState(mutable.below()).is(McddnBlocks.ASHY_BASALT))) && world.getBlockState(mutable).is(Blocks.AIR)) {
                world.setBlock(mutable, McddnBlocks.ASH.defaultBlockState(), 2);
                world.setBlock(mutable.below(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
                i += 1;

                if (world.getBlockState(mutable.below().north()).is(Blocks.BASALT) && world.getBlockState(mutable.north()).is(Blocks.AIR) && new ChunkPos(blockPos).equals(new ChunkPos(blockPos.north()))) {
                    world.setBlock(mutable.below().north(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
                }
                if (world.getBlockState(mutable.below().south()).is(Blocks.BASALT) && world.getBlockState(mutable.south()).is(Blocks.AIR) && new ChunkPos(blockPos).equals(new ChunkPos(blockPos.south()))) {
                    world.setBlock(mutable.below().south(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
                }
                if (world.getBlockState(mutable.below().east()).is(Blocks.BASALT) && world.getBlockState(mutable.east()).is(Blocks.AIR) && new ChunkPos(blockPos).equals(new ChunkPos(blockPos.east()))) {
                    world.setBlock(mutable.below().east(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
                }
                if (world.getBlockState(mutable.below().west()).is(Blocks.BASALT) && world.getBlockState(mutable.west()).is(Blocks.AIR) && new ChunkPos(blockPos).equals(new ChunkPos(blockPos.west()))) {
                    world.setBlock(mutable.below().west(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
                }
            }
        }

        return i > 0;
    }
}
