package net.redupro.mcd_d_nether.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.redupro.mcd_d_nether.block.McddnBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
    @Inject(method = "onPlace", at = @At("HEAD"))
    private void warpedWartBlock(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl, CallbackInfo ci) {
        if (blockState.is(Blocks.WARPED_WART_BLOCK)) {
            if (level.getBlockState(blockPos.below()).isAir()) {
                level.setBlock(blockPos.below(), McddnBlocks.WARPED_WART_HANGING.defaultBlockState(), 2);
            }
        }
    }
}
