package net.redupro.mcd_d_nether.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class NetherWallFlower extends Block {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape EAST_SHAPE = Block.box(0.0, 9.0, 0.0, 8.0, 15.0, 16.0);
    private static final VoxelShape WEST_SHAPE = Block.box(8.0, 9.0, 0.0, 16.0, 15.0, 16.0);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 9.0, 0.0, 16.0, 15.0, 8.0);
    private static final VoxelShape NORTH_SHAPE = Block.box(0.0, 9.0, 8.0, 16.0, 15.0, 16.0);

    public static final MapCodec<NetherWallFlower> CODEC = simpleCodec(NetherWallFlower::new);

    public NetherWallFlower(Properties settings) {
        super(settings);
    }

    @Override
    public MapCodec<NetherWallFlower> codec() {
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
        if(direction == state.getValue(FACING)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return state;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        if (ctx.getClickedFace() == Direction.UP || ctx.getClickedFace() == Direction.DOWN) {
            return null;
        } else {
            return this.defaultBlockState().setValue(FACING, ctx.getClickedFace().getOpposite());
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        return this.canPlaceOn(world, pos.relative(direction), direction);
    }

    private boolean canPlaceOn(BlockGetter world, BlockPos pos, Direction side) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isFaceSturdy(world, pos, side);
    }

    public static boolean placeAt(LevelAccessor world, BlockState state, BlockPos pos) {
        if (new ChunkPos(pos).equals(new ChunkPos(pos.relative(state.getValue(FACING))))) {
            if (world.getBlockState(pos).is(Blocks.AIR) && !world.getBlockState(pos.below()).isCollisionShapeFullBlock(world, pos.below())) {
                if (world.getBlockState(pos.relative(state.getValue(FACING))).is(Blocks.NETHERRACK) || world.getBlockState(pos.relative(state.getValue(FACING))).is(Blocks.WARPED_NYLIUM)) {
                    if (world.getBlockState(pos.below()) == state ||
                            world.getBlockState(pos.above()) == state ||
                            world.getBlockState(pos.north()) == state ||
                            world.getBlockState(pos.south()) == state ||
                            world.getBlockState(pos.east()) == state ||
                            world.getBlockState(pos.west()) == state) {
                        return false;
                    } else {
                        world.setBlock(pos, state, 2);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
