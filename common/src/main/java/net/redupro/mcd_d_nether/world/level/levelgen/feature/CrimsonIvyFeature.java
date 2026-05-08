package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.redupro.mcd_d_nether.block.custom.IvyBlock;

import java.util.ArrayList;

public class CrimsonIvyFeature extends Feature<NoneFeatureConfiguration> {
    public CrimsonIvyFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(Direction.NORTH);
        directions.add(Direction.EAST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.WEST);
        int selected = random.nextIntBetweenInclusive(1, directions.size()) - 1;
        Direction facing = directions.get(selected);
        while (world.getBlockState(blockPos).is(Blocks.LAVA)) {
            blockPos = blockPos.above();
        }
        if (world.isEmptyBlock(blockPos) && (world.getBlockState(blockPos.below()).isCollisionShapeFullBlock(world, blockPos) || world.getBlockState(blockPos.below()).is(Blocks.LAVA))) {
            while (directions.size() != 1 && !world.getBlockState(blockPos.relative(facing.getOpposite())).isFaceSturdy(world, blockPos.relative(facing.getOpposite()), facing)) {
                directions.remove(selected);
                selected = random.nextIntBetweenInclusive(1, directions.size()) - 1;
                facing = directions.get(selected);
            }
            if (directions.size() == 1) {
                facing = directions.getFirst();
                if (world.getBlockState(blockPos.relative(facing.getOpposite())).isFaceSturdy(world, blockPos.relative(facing.getOpposite()), facing)) {
                    if (ChunkPos.containing(blockPos).equals(ChunkPos.containing(blockPos.relative(facing.getOpposite())))) {
                        return IvyBlock.growAt(world, blockPos, facing, random);
                    }
                }
            }
        }
        return false;
    }
}
