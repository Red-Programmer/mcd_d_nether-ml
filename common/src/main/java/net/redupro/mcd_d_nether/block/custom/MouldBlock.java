package net.redupro.mcd_d_nether.block.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.redupro.mcd_d_nether.registry.McddnBlocks;

public class MouldBlock extends TallRootsBlock {
    public static final BooleanProperty CAP = BooleanProperty.create("cap");
    public static final BooleanProperty CORNER = BooleanProperty.create("corner");
    public static final BooleanProperty EDGE = BooleanProperty.create("edge");
    public static final BooleanProperty UPPER = BooleanProperty.create("upper");
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    protected static final VoxelShape SHAPE = Block.box(0.01, 0.01, 0.01, 16.0, 16.0, 16.0);
    protected static final VoxelShape SHAPE_HALF = Block.box(0.01, 0.0, 0.01, 16.0, 8.0, 16.0);
    protected static final Double[] SIDE_SHAPE = {0.0, 0.0, 12.0, 16.0, 16.0, 16.0};
    protected static final Double[] CORNER_SHAPE = {12.0, 8.0, 12.0, 16.0, 16.0, 16.0};
    protected static final Double[] EDGE_SHAPE = {0.0, 8.0, 12.0, 16.0, 16.0, 16.0};
    protected static final Double[] CORNER_EDGE_SHAPE = {12.0, 0.0, 12.0, 16.0, 16.0, 16.0};

    public MouldBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(CAP, false).setValue(CORNER, false).setValue(EDGE, false).setValue(UPPER, false).setValue(FACING, Direction.UP));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        boolean corner = state.getValue(CORNER);
        boolean edge = state.getValue(EDGE);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return SHAPE;
        } else if (state.getValue(CAP)) {
            if (facing == Direction.UP) {
                return SHAPE;
            } else if (facing == Direction.DOWN) {
                return SHAPE_HALF;
            }
            double heightOffset = state.getValue(UPPER) ? -8.0 : 0.0;
            int rotation = 0;
            if (facing == Direction.EAST) {
                rotation = 1;
            } else if (facing == Direction.SOUTH) {
                rotation = 2;
            } else if (facing == Direction.WEST) {
                rotation = 3;
            }
            if (edge && corner) {
                return createVoxelRotatable(CORNER_EDGE_SHAPE, rotation, heightOffset);
            } else if (edge) {
                return createVoxelRotatable(EDGE_SHAPE, rotation, heightOffset);
            } else if (corner){
                return createVoxelRotatable(CORNER_SHAPE, rotation, heightOffset);
            } else {
                return createVoxelRotatable(SIDE_SHAPE, rotation, heightOffset);
            }
        }
        return SHAPE_HALF;
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
        if (state.getValue(CAP)) {
            return isPartOf(state, direction) && !neighborState.is(this) ? Blocks.AIR.defaultBlockState() : state;
        } else {
            return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
    }

    private boolean isPartOf(BlockState state, Direction direction) {
        Direction facing = state.getValue(FACING);
        boolean corner = state.getValue(CORNER);
        boolean edge = state.getValue(EDGE);
        boolean upper = state.getValue(UPPER);
        boolean moon = state.is(McddnBlocks.MOONLIGHT_MOULD);
        if (corner && edge) {
            return direction == Direction.UP && !moon || direction == Direction.DOWN  || direction == facing.getClockWise() || direction == facing.getOpposite();
        } else if (corner) {
            if (upper) {
                return direction == Direction.DOWN  || direction == facing.getClockWise() || direction == facing.getOpposite();
            } else {
                return direction == Direction.UP  || direction == facing.getClockWise() || direction == facing.getOpposite();
            }
        } else if (edge) {
            if (upper) {
                return direction == Direction.DOWN  || direction == facing.getClockWise() || direction == facing.getCounterClockWise() || direction == facing.getOpposite();
            } else {
                return direction == Direction.UP  || direction == facing.getClockWise() || direction == facing.getCounterClockWise() || direction == facing.getOpposite();
            }
        } else {
            if (facing == Direction.UP) {
                return moon ? direction != Direction.DOWN : direction != Direction.DOWN && direction != Direction.UP;
            } else if (facing == Direction.DOWN) {
                return direction != Direction.UP;
            } else {
                return moon ? direction != facing : direction != facing && direction != Direction.UP;
            }
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        boolean mouldType = this.defaultBlockState().is(McddnBlocks.MOONLIGHT_MOULD);
        if(checkArea(world, pos, mouldType) && !state.getValue(CAP) && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            world.setBlockAndUpdate(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true));
            setLayer(world, pos, false, false);
            setLayer(world, pos.above(), true, false);
            if(mouldType) {
                setLayer(world, pos.above(2), false, true);
                world.setBlockAndUpdate(pos.above(2), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.DOWN));
            }
        }
        if (RandomSource.create().nextIntBetweenInclusive(0, 50) == 0) {
            BlockPos spawn = validPos(world, pos);
            if (spawn != null) {
                if (mouldType) {
                    world.setBlockAndUpdate(spawn, McddnBlocks.MOONLIGHT_MILDEW.defaultBlockState());
                } else {
                    world.setBlockAndUpdate(spawn, McddnBlocks.MIDNIGHT_MILDEW.defaultBlockState());
                }
            }
        }
    }

    public void setLayer(ServerLevel world, BlockPos pos, boolean middle, boolean top) {
        world.setBlockAndUpdate(pos.north(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.NORTH).setValue(EDGE, !middle).setValue(UPPER, top));
        world.setBlockAndUpdate(pos.east(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.EAST).setValue(EDGE, !middle).setValue(UPPER, top));
        world.setBlockAndUpdate(pos.south(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.SOUTH).setValue(EDGE, !middle).setValue(UPPER, top));
        world.setBlockAndUpdate(pos.west(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.WEST).setValue(EDGE, !middle).setValue(UPPER, top));
        world.setBlockAndUpdate(pos.north().west(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.NORTH).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top));
        world.setBlockAndUpdate(pos.east().north(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.EAST).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top));
        world.setBlockAndUpdate(pos.south().east(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.SOUTH).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top));
        world.setBlockAndUpdate(pos.west().south(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.WEST).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top));
    }
    public boolean checkArea(ServerLevel world, BlockPos pos, boolean mouldType) {
        BlockPos mutable = pos;
        boolean check = true;
        int layerCount = 2;
        if (mouldType) {
            layerCount = 3;
        }
        for (int i = 0; i < layerCount; i++) {
            check = check && isReplaceable(world.getBlockState(mutable.north()));
            check = check && isReplaceable(world.getBlockState(mutable.east()));
            check = check && isReplaceable(world.getBlockState(mutable.south()));
            check = check && isReplaceable(world.getBlockState(mutable.west()));
            check = check && isReplaceable(world.getBlockState(mutable.north().west()));
            check = check && isReplaceable(world.getBlockState(mutable.east().north()));
            check = check && isReplaceable(world.getBlockState(mutable.south().east()));
            check = check && isReplaceable(world.getBlockState(mutable.west().south()));
            mutable = mutable.above();
        }
        return check;
    }
    private boolean isReplaceable(BlockState state) {
        return state.canBeReplaced() && state.getFluidState().isEmpty();
    }


    public BlockPos validPos(ServerLevel world, BlockPos pos) {
        for (int i = 0; i < 4; i++) {
            int randX = RandomSource.create().nextIntBetweenInclusive(-8, 8);
            int randY = RandomSource.create().nextIntBetweenInclusive(-8, 8);
            int randZ = RandomSource.create().nextIntBetweenInclusive(-8, 8);
            if (world.getBlockState(pos.offset(randX, randY, randZ)).isAir() && world.getBlockState(pos.offset(randX, randY - 1, randZ)).is(BlockTags.NYLIUM)) {
                return pos.offset(randX, randY, randZ);
            }
        }
        return null;
    }

    public static boolean placeAt(LevelAccessor world, BlockState state, BlockPos pos) {
        if (checkAreaAt(world, pos, state.is(McddnBlocks.MOONLIGHT_MOULD))) {
            world.setBlock(pos.below(2), state.setValue(HALF, DoubleBlockHalf.LOWER), 2);
            world.setBlock(pos.below(), state.setValue(HALF, DoubleBlockHalf.UPPER), 2);
            world.setBlock(pos, state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true), 2);
            setLayerAt(world, pos.below(), false, false, state);
            setLayerAt(world, pos, true, false, state);
            if(state.is(McddnBlocks.MOONLIGHT_MOULD)) {
                setLayerAt(world, pos.above(), false, true, state);
                world.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.DOWN), 2);
            }
            return true;
        }
        return false;
    }

    public static void setLayerAt(LevelAccessor world, BlockPos pos, boolean middle, boolean top, BlockState state) {
        world.setBlock(pos.north(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.NORTH).setValue(EDGE, !middle).setValue(UPPER, top), 2);
        world.setBlock(pos.east(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.EAST).setValue(EDGE, !middle).setValue(UPPER, top), 2);
        world.setBlock(pos.south(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.SOUTH).setValue(EDGE, !middle).setValue(UPPER, top), 2);
        world.setBlock(pos.west(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.WEST).setValue(EDGE, !middle).setValue(UPPER, top), 2);
        world.setBlock(pos.north().west(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.NORTH).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top), 2);
        world.setBlock(pos.east().north(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.EAST).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top), 2);
        world.setBlock(pos.south().east(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.SOUTH).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top), 2);
        world.setBlock(pos.west().south(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(CAP, true).setValue(FACING, Direction.WEST).setValue(CORNER, true).setValue(EDGE, middle).setValue(UPPER, top), 2);
    }

    public static boolean checkAreaAt(LevelAccessor world, BlockPos pos, boolean mouldType) {
        BlockPos mutable = pos;
        boolean check = true;
        int layerCount = 2;
        if (mouldType) {
            layerCount = 3;
        }
        for (int i = 0; i < layerCount; i++) {
            check = check && world.getBlockState(mutable.north()).canBeReplaced();
            check = check && world.getBlockState(mutable.east()).canBeReplaced();
            check = check && world.getBlockState(mutable.south()).canBeReplaced();
            check = check && world.getBlockState(mutable.west()).canBeReplaced();
            check = check && world.getBlockState(mutable.north().west()).canBeReplaced();
            check = check && world.getBlockState(mutable.east().north()).canBeReplaced();
            check = check && world.getBlockState(mutable.south().east()).canBeReplaced();
            check = check && world.getBlockState(mutable.west().south()).canBeReplaced();
            mutable = mutable.above();
        }
        return check;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, CAP, CORNER, EDGE, UPPER, FACING);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }
    private VoxelShape createVoxelRotatable(Double[] shape, int rotation, double heightOffset) {
        double minX = shape[0];
        double minY = shape[1] + heightOffset;
        double minZ = shape[2];
        double maxX = shape[3];
        double maxY = shape[4] + heightOffset;
        double maxZ = shape[5];

        if (rotation == 0) {
            return Block.box(minX, minY, minZ, maxX, maxY, maxZ);
        } else if (rotation == 1) {
            return Block.box(16-maxZ, minY, minX, 16-minZ, maxY, maxX);
        } else if (rotation == 2) {
            return Block.box(16-maxX, minY, 16-maxZ, 16-minX, maxY, 16-minZ);
        } else {
            return Block.box(minZ, minY, 16-maxX, maxZ, maxY, 16-minX);
        }
    }
}