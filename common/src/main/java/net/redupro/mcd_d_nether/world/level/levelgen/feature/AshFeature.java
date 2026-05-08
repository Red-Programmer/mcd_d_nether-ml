package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.redupro.mcd_d_nether.registry.McddnBlocks;


public class AshFeature extends Feature<NoneFeatureConfiguration> {
    public AshFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos blockPos = context.origin();
        WorldGenLevel world = context.level();

        int i = 0;
        if ((world.getBlockState(blockPos.below()).is(Blocks.BASALT) || (world.getBlockState(blockPos.below()).is(McddnBlocks.ASHY_BASALT))) && world.getBlockState(blockPos).is(Blocks.AIR)) {
            world.setBlock(blockPos, McddnBlocks.ASH.defaultBlockState(), 2);
            world.setBlock(blockPos.below(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
            i += 1;

            if (world.getBlockState(blockPos.below().north()).is(Blocks.BASALT) && world.getBlockState(blockPos.north()).is(Blocks.AIR) && ChunkPos.containing(blockPos).equals(ChunkPos.containing(blockPos.north()))) {
                world.setBlock(blockPos.below().north(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
            }
            if (world.getBlockState(blockPos.below().south()).is(Blocks.BASALT) && world.getBlockState(blockPos.south()).is(Blocks.AIR) && ChunkPos.containing(blockPos).equals(ChunkPos.containing(blockPos.south()))) {
                world.setBlock(blockPos.below().south(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
            }
            if (world.getBlockState(blockPos.below().east()).is(Blocks.BASALT) && world.getBlockState(blockPos.east()).is(Blocks.AIR) && ChunkPos.containing(blockPos).equals(ChunkPos.containing(blockPos.east()))) {
                world.setBlock(blockPos.below().east(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
            }
            if (world.getBlockState(blockPos.below().west()).is(Blocks.BASALT) && world.getBlockState(blockPos.west()).is(Blocks.AIR) && ChunkPos.containing(blockPos).equals(ChunkPos.containing(blockPos.west()))) {
                world.setBlock(blockPos.below().west(), McddnBlocks.ASHY_BASALT.defaultBlockState(), 2);
            }
        }

        return i > 0;
    }
}
