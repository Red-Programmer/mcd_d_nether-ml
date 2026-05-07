package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.block.custom.NetherWallFlower;


public class NetherWallFlowerPatchFeature extends Feature<RandomPatchConfiguration> {
    public NetherWallFlowerPatchFeature(Codec<RandomPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        RandomPatchConfiguration randomPatchFeatureConfig = context.config();
        RandomSource random = context.random();
        BlockPos blockPos = context.origin();
        WorldGenLevel structureWorldAccess = context.level();

        int i = 0;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int j = randomPatchFeatureConfig.xzSpread() + 1;
        int k = randomPatchFeatureConfig.ySpread() + 1;
        int x;
        int y;
        int z;

        for (int l = 0; l < randomPatchFeatureConfig.tries(); l++) {
            x = random.nextInt(j) - random.nextInt(j);
            y = random.nextInt(k) - random.nextInt(k);
            z = random.nextInt(j) - random.nextInt(j);
            i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.NORTH), blockPos.offset(x, y, z + 1)) ? 1 : 0;
            i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.SOUTH), blockPos.offset(x, y, z - 1)) ? 1 : 0;
            i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.WEST), blockPos.offset(x + 1, y, z)) ? 1 : 0;
            i += NetherWallFlower.placeAt(structureWorldAccess, McddnBlocks.NETHER_WALL_FLOWER.defaultBlockState().setValue(NetherWallFlower.FACING, Direction.EAST), blockPos.offset(x - 1, y, z)) ? 1 : 0;
        }

        return i > 0;
    }
}
