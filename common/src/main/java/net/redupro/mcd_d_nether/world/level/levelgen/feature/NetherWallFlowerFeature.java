package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.block.custom.NetherWallFlower;


public class NetherWallFlowerFeature extends Feature<NoneFeatureConfiguration> {
    public NetherWallFlowerFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        WorldGenLevel structureWorldAccess = context.level();

        int i = 0;

        i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.NORTH), blockPos.south()) ? 1 : 0;
        i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.SOUTH), blockPos.north()) ? 1 : 0;
        i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.WEST), blockPos.east()) ? 1 : 0;
        i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.EAST), blockPos.west()) ? 1 : 0;
        if (i > 0 && random.nextFloat() > 0.75 && structureWorldAccess.getBlockState(blockPos).is(Blocks.WARPED_NYLIUM) && structureWorldAccess.getBlockState(blockPos.above()).isAir()) {
            structureWorldAccess.setBlock(blockPos.above(), McddnBlocks.FUNGAL_FERN.defaultBlockState(), 2);
        }

        return i > 0;
    }
}
