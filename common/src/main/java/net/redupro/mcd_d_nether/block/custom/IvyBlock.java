package net.redupro.mcd_d_nether.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.redupro.mcd_d_nether.block.Ivy;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.block.enums.IvyPart;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class IvyBlock extends Block implements BonemealableBlock {
    public static final EnumProperty<IvyPart> IVY_PART = EnumProperty.create("ivy_part", IvyPart.class);
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0);
    private static final VoxelShape WEST_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0);
    private static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);

    public static final MapCodec<IvyBlock> CODEC =  simpleCodec(IvyBlock::new);
    public IvyBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(defaultBlockState().setValue(IVY_PART, IvyPart.TIP).setValue(FACING, Direction.NORTH).setValue(FRUIT, false));
    }
    @Override
    public MapCodec<IvyBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case NORTH -> NORTH_SHAPE;
            default -> EAST_SHAPE;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        if (ctx.getClickedFace() == Direction.UP || ctx.getClickedFace() == Direction.DOWN) {
            return null;
        } else {
            return this.defaultBlockState().setValue(FACING, ctx.getClickedFace());
        }
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader world,
            ScheduledTickAccess tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            RandomSource random
    ) {
        Direction facing = state.getValue(FACING);
        if ((state.getValue(IVY_PART).equals(IvyPart.LEFTB) || state.getValue(IVY_PART).equals(IvyPart.RIGHTB)) && !neighborState.is(this)) {
            if (direction == facing.getCounterClockWise()) {
                return Blocks.AIR.defaultBlockState();
            } else {
                return state;
            }
        } else {
            return state;
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.below();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    public static boolean growAt(LevelAccessor world, BlockPos pos, Direction facing, RandomSource random) {

        BlockPos wall = pos.relative(facing.getOpposite());
        BlockPos mutable = pos;
        BlockState stemState = McddnBlocks.CRIMSON_IVY.defaultBlockState().setValue(FACING, facing);
        int height = random.nextIntBetweenInclusive(32, 48);
        while (world.getBlockState(wall).isFaceSturdy(world, wall, facing) && world.isEmptyBlock(mutable) && height > 0) {
            mutable = mutable.above();
            wall = wall.above();
            height -= 1;
            if (world.getBlockState(wall).is(Blocks.CRIMSON_STEM)) {
                return false;
            }
        }
        if (height > 30) {
            return false;
        } else {
            boolean toggle = random.nextBoolean();
            mutable = mutable.below();
            world.setBlock(mutable, stemState.setValue(IVY_PART, IvyPart.TIP), 2);
            while (world.getBlockState(mutable.below()).isAir()) {
                mutable = mutable.below();
                if (world.getBlockState(mutable.relative(toggle ? facing.getClockWise() : facing.getCounterClockWise())).isAir()) {
                    world.setBlock(mutable, stemState.setValue(IVY_PART, toggle ? IvyPart.LEFT : IvyPart.RIGHT).setValue(FRUIT, random.nextBoolean()), 2);
                    world.setBlock(mutable.relative(toggle ? facing.getClockWise() : facing.getCounterClockWise()), stemState.setValue(IVY_PART, toggle ? IvyPart.LEFTB : IvyPart.RIGHTB).setValue(FRUIT, random.nextBoolean()), 2);
                } else {
                    world.setBlock(mutable, stemState, 2);
                }
                toggle = !toggle;
            }
            if (world.getBlockState(mutable.below()).is(Blocks.LAVA)) {
                world.setBlock(mutable, stemState, 2);
            } else {
                world.setBlock(mutable, stemState.setValue(IVY_PART, IvyPart.ROOT), 2);
            }
            if (world.getBlockState(mutable.relative(!toggle ? facing.getClockWise() : facing.getCounterClockWise())).is(McddnBlocks.CRIMSON_IVY)) {
                world.setBlock(mutable.relative(!toggle ? facing.getClockWise() : facing.getCounterClockWise()), Blocks.AIR.defaultBlockState(), 2);
            }
            return true;
        }
    }

    protected boolean canPlantOnTop(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.isFaceSturdy(world, pos, Direction.UP);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        Direction facing = state.getValue(FACING);
        if (state.getValue(IVY_PART).equals(IvyPart.TIP)) {
            if (world.getBlockState(pos.above().relative(facing.getOpposite())).isFaceSturdy(world, pos.above().relative(facing.getOpposite()), facing)) {
                if (world.getBlockState(pos.above()).canBeReplaced()) {
                    world.setBlockAndUpdate(pos.above(), state);
                    world.setBlockAndUpdate(pos, state.setValue(IVY_PART, world.getBlockState(pos.below()).is(McddnBlocks.CRIMSON_IVY) ? IvyPart.STEM : IvyPart.ROOT));
                }
            }
        } else if (state.getValue(IVY_PART).equals(IvyPart.STEM)) {
            BlockPos branchPos = pos.relative(facing.getCounterClockWise());
            boolean left = RandomSource.create().nextBoolean();
            if (left) {
                branchPos = pos.relative(facing.getClockWise());
            }
            if (world.getBlockState(branchPos).isAir()) {
                world.setBlockAndUpdate(branchPos, state.setValue(IVY_PART, left ? IvyPart.LEFTB : IvyPart.RIGHTB).setValue(FRUIT, RandomSource.create().nextBoolean()));
                world.setBlockAndUpdate(pos, state.setValue(IVY_PART, left ? IvyPart.LEFT : IvyPart.RIGHT).setValue(FRUIT, RandomSource.create().nextBoolean()));
            }
        }
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        return Ivy.pickFruit(player, state, world, pos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(FACING);
        if (state.getValue(IVY_PART).equals(IvyPart.LEFT) || state.getValue(IVY_PART).equals(IvyPart.RIGHT)) {
            return !state.getValue(FRUIT);
        } else if (state.getValue(IVY_PART).equals(IvyPart.STEM)) {
            return world.getBlockState(pos.relative(state.getValue(FACING).getCounterClockWise())).canBeReplaced() ||
                    world.getBlockState(pos.relative(state.getValue(FACING).getClockWise())).canBeReplaced();
        } else if (state.getValue(IVY_PART).equals(IvyPart.TIP)) {
            return world.getBlockState(pos.above()).canBeReplaced() && world.getBlockState(pos.above().relative(facing.getOpposite())).isFaceSturdy(world, pos.above().relative(facing.getOpposite()), facing);
        }
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        if (state.getValue(IVY_PART).equals(IvyPart.LEFT) || state.getValue(IVY_PART).equals(IvyPart.RIGHT)) {
            world.setBlock(pos, state.setValue(FRUIT, true), Block.UPDATE_CLIENTS);
        } else {
            randomTick(state, world, pos, random);
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IVY_PART, FACING, FRUIT);
    }
}
