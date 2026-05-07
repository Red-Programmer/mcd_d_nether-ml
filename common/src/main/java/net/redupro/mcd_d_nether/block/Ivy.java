package net.redupro.mcd_d_nether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.redupro.mcd_d_nether.block.custom.IvyBlock;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public interface Ivy {
    BooleanProperty FRUIT = IvyBlock.FRUIT;

    static InteractionResult pickFruit(@Nullable Entity picker, BlockState state, Level world, BlockPos pos) {
        if ((Boolean)state.getValue(FRUIT)) {
            Block.popResource(world, pos, new ItemStack(McddnBlocks.CRIMSON_IVY, 1));
            float f = Mth.randomBetween(world.random, 0.8F, 1.2F);
            world.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            BlockState blockState = state.setValue(FRUIT, false);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(picker, blockState));
            return InteractionResult.SUCCESS;
        } else if ((Boolean)world.getBlockState(pos.above()).is(state.getBlock())) {
            if ((Boolean)world.getBlockState(pos.above()).getValue(IvyBlock.FRUIT)) {
                Block.popResource(world, pos.above(), new ItemStack(McddnBlocks.CRIMSON_IVY, 1));
                float f = Mth.randomBetween(world.random, 0.8F, 1.2F);
                world.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
                BlockState blockState = state.setValue(FRUIT, false);
                world.setBlock(pos.above(), blockState, Block.UPDATE_CLIENTS);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos.above(), GameEvent.Context.of(picker, blockState));
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    static ToIntFunction<BlockState> getLuminanceSupplier(int luminance) {
        return state -> state.getValue(IvyBlock.FRUIT) ? luminance : 0;
    }
}
