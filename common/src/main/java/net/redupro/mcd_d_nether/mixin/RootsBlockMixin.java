package net.redupro.mcd_d_nether.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.NetherRootsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NetherRootsBlock.class)
public class RootsBlockMixin {
    @ModifyReturnValue(method = "mayPlaceOn", at = @At("RETURN"))
    private boolean checkIsWartBlock(boolean original, BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return original || blockState.is(BlockTags.WART_BLOCKS);
    }
}
