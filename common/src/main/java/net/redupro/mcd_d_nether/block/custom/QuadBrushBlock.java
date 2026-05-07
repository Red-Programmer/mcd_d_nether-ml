package net.redupro.mcd_d_nether.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.redupro.mcd_d_nether.block.enums.Quadrant;
import org.jetbrains.annotations.Nullable;

public class QuadBrushBlock extends Block {
    public static final EnumProperty<Quadrant> QUADRANT = EnumProperty.create("quadrant", Quadrant.class);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape EAST_SHAPE = Block.box(3.0, 0.0, 0.0, 6.0, 16.0, 16.0);
    private static final VoxelShape WEST_SHAPE = Block.box(10.0, 0.0, 0.0, 13.0, 16.0, 16.0);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 3.0, 16.0, 16.0, 6.0);
    private static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 10.0, 16.0, 16.0, 13.0);

    public static final MapCodec<QuadBrushBlock> CODEC = simpleCodec(QuadBrushBlock::new);

    public QuadBrushBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(QUADRANT, Quadrant.QUAD_1));
    }

    @Override
    public MapCodec<QuadBrushBlock> codec() {
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
        if(isPartOf(state, direction)) {
            return neighborState.is(this) ? state : Blocks.AIR.defaultBlockState();
        }
        return direction == Direction.DOWN ? Blocks.AIR.defaultBlockState() : state;
    }
    private boolean isPartOf(BlockState state, Direction direction) {
        Quadrant quadrant = state.getValue(QUADRANT);
        if(quadrant == Quadrant.QUAD_1) {
            return direction == state.getValue(FACING).getClockWise(Direction.Axis.Y) || direction == Direction.UP;
        } else if(quadrant == Quadrant.QUAD_2) {
            return direction == state.getValue(FACING).getCounterClockWise(Direction.Axis.Y) || direction == Direction.UP;
        } else if(quadrant == Quadrant.QUAD_3) {
            return direction == state.getValue(FACING).getCounterClockWise(Direction.Axis.Y) || direction == Direction.DOWN;
        } else {
            return direction == state.getValue(FACING).getClockWise(Direction.Axis.Y) || direction == Direction.DOWN;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos blockPos = ctx.getClickedPos();
        Level world = ctx.getLevel();
        Direction facing = ctx.getHorizontalDirection().getOpposite();
        BlockPos neighbor = blockPos.relative(facing.getClockWise(Direction.Axis.Y));
        if(world.getBlockState(blockPos.above()).canBeReplaced(ctx) && world.getBlockState(neighbor).canBeReplaced(ctx) && world.getBlockState(neighbor.above()).canBeReplaced(ctx)) {
            return this.defaultBlockState().setValue(FACING, facing).setValue(QUADRANT, Quadrant.QUAD_1);
        } else {
            return null;
        }
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        Direction facing = state.getValue(FACING);
        BlockPos offset = pos.relative(facing.getClockWise(Direction.Axis.Y));
        world.setBlock(offset, state.setValue(QUADRANT, Quadrant.QUAD_2), Block.UPDATE_ALL);
        world.setBlock(offset.above(), state.setValue(QUADRANT, Quadrant.QUAD_3), Block.UPDATE_ALL);
        world.setBlock(pos.above(), state.setValue(QUADRANT, Quadrant.QUAD_4), Block.UPDATE_ALL);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos offset = pos.relative(direction.getClockWise(Direction.Axis.Y)).below();
        return this.canPlaceOn(world, pos.below()) && this.canPlaceOn(world, offset);
    }

    private boolean canPlaceOn(BlockGetter world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isFaceSturdy(world, pos, Direction.UP);
    }

    public static void placeAt(LevelAccessor world, BlockState state, BlockPos pos, Direction facing, int flags) {
        BlockPos offset = pos.relative(facing.getClockWise(Direction.Axis.Y));
        world.setBlock(pos, state.setValue(QUADRANT, Quadrant.QUAD_1).setValue(FACING, facing), flags);
        world.setBlock(offset, state.setValue(QUADRANT, Quadrant.QUAD_2).setValue(FACING, facing), flags);
        world.setBlock(offset.above(), state.setValue(QUADRANT, Quadrant.QUAD_3).setValue(FACING, facing), flags);
        world.setBlock(pos.above(), state.setValue(QUADRANT, Quadrant.QUAD_4).setValue(FACING, facing), flags);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(QUADRANT, FACING);
    }
}
