package net.redupro.mcd_d_nether.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class MildewBlock extends RootsBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_25;

    public MildewBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 25));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        Integer age = state.getValue(AGE);
        if (age == 0) {
            world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        } else if (age % 8 == 1) {
            world.setBlockAndUpdate(validPos(world, pos), state.setValue(AGE, age));
        }
        if (age > 0) {
            world.setBlockAndUpdate(pos, state.setValue(AGE, age - 1));
        }
    }

    public BlockPos validPos(ServerLevel world, BlockPos pos) {
        for (int i = 0; i < 4; i++) {
            int randX = RandomSource.create().nextIntBetweenInclusive(-4, 4);
            int randY = RandomSource.create().nextIntBetweenInclusive(-4, 4);
            int randZ = RandomSource.create().nextIntBetweenInclusive(-4, 4);
            if (world.getBlockState(pos.offset(randX, randY, randZ)).isAir() && world.getBlockState(pos.offset(randX, randY - 1, randZ)).is(BlockTags.NYLIUM)) {
                return pos.offset(randX, randY, randZ);
            }
        }
        return pos;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
