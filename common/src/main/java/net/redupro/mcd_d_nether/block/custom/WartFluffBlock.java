package net.redupro.mcd_d_nether.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WartFluffBlock extends Block {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    private static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
    private static final VoxelShape WEST_SHAPE = Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
    private static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);

    public static final MapCodec<WartFluffBlock> CODEC = simpleCodec(WartFluffBlock::new);

    public WartFluffBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HANGING, false).setValue(HALF, DoubleBlockHalf.UPPER));
    }

    @Override
    public MapCodec<WartFluffBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case SOUTH -> NORTH_SHAPE;
            case WEST -> EAST_SHAPE;
            case NORTH -> SOUTH_SHAPE;
            default -> WEST_SHAPE;
        };
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
        if (direction == state.getValue(FACING) && doubleBlockHalf == DoubleBlockHalf.UPPER) {
            return Blocks.AIR.defaultBlockState();
        } else if (direction.getAxis() != Direction.Axis.Y
                    || doubleBlockHalf == DoubleBlockHalf.LOWER != (direction == Direction.UP)
                    || neighborState.is(this) && neighborState.getValue(HALF) != doubleBlockHalf) {
                return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(world, pos)
                        ? Blocks.AIR.defaultBlockState()
                        : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
            } else {
                return Blocks.AIR.defaultBlockState();
            }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HANGING, HALF);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos blockPos = ctx.getClickedPos();
        Level world = ctx.getLevel();
        Direction facing = ctx.getClickedFace();
        boolean hanging;
        if (ctx.getClickedFace() == Direction.UP || ctx.getClickedFace() == Direction.DOWN) {
            return null;
        } else {
            if (facing == Direction.NORTH) {
                hanging = world.getBlockState(blockPos.above().south()).isFaceSturdy(ctx.getLevel(), ctx.getClickedPos().above().south(), Direction.SOUTH);
            } else if (facing == Direction.EAST) {
                hanging = world.getBlockState(blockPos.above().west()).isFaceSturdy(ctx.getLevel(), ctx.getClickedPos().above().west(), Direction.WEST);
            } else if (facing == Direction.SOUTH) {
                hanging = world.getBlockState(blockPos.above().north()).isFaceSturdy(ctx.getLevel(), ctx.getClickedPos().above().north(), Direction.NORTH);
            } else {
                hanging = world.getBlockState(blockPos.above().east()).isFaceSturdy(ctx.getLevel(), ctx.getClickedPos().above().east(), Direction.EAST);
            }
            return blockPos.getY() > world.getMinBuildHeight() && world.getBlockState(blockPos.below()).canBeReplaced(ctx) ? this.defaultBlockState().setValue(FACING, facing.getOpposite()).setValue(HANGING, hanging) : null;
        }
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlock(pos.below(), state.setValue(HALF, DoubleBlockHalf.LOWER), Block.UPDATE_ALL);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        return this.canPlaceOn(world, pos.relative(direction), direction);
    }

    private boolean canPlaceOn(BlockGetter world, BlockPos pos, Direction side) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isFaceSturdy(world, pos, side.getOpposite());
    }

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (!world.isClientSide()) {
            if (player.isCreative()) {
                onBreakInCreative(world, pos, state, player);
            } else {
                dropResources(state, world, pos, null, player, player.getMainHandItem());
            }
        }

        return super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(world, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, tool);
    }

    protected static void onBreakInCreative(Level world, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
        if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
            BlockPos blockPos = pos.below();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.is(state.getBlock()) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockState2 = Blocks.AIR.defaultBlockState();
                world.setBlock(blockPos, blockState2, Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
                world.levelEvent(player, LevelEvent.PARTICLES_DESTROY_BLOCK, blockPos, Block.getId(blockState));
            }
        }
    }

    @Override
    protected long getSeed(BlockState blockState, BlockPos blockPos) {
        return Mth.getSeed(blockPos.getX(), blockPos.below(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), blockPos.getZ());
    }
}