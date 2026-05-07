package net.redupro.mcd_d_nether.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.redupro.mcd_d_nether.block.McddnBlocks;
import net.redupro.mcd_d_nether.util.McddnTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BasaltColumnsFeature.class)
public class BasaltColumnsFeatureMixin {
    @ModifyReturnValue(method = "canPlaceAt", at = @At("RETURN:FIRST"))
    private static boolean isPlaceInTag(boolean original, LevelAccessor levelAccessor, int i, BlockPos.MutableBlockPos mutableBlockPos) {
        BlockState blockState = levelAccessor.getBlockState(mutableBlockPos.move(Direction.DOWN));
        return original && !blockState.is(McddnTags.Blocks.BASALT_CANNOT_PLACE_ON);
    }

    @Definition(id = "contains", method = "Lcom/google/common/collect/ImmutableList;contains(Ljava/lang/Object;)Z")
    @Expression("?.contains(?)")
    @ModifyExpressionValue(method = "findAir", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean isFoundInTag(boolean original, @Local BlockState blockState) {
        return original || blockState.is(McddnTags.Blocks.BASALT_CANNOT_PLACE_ON);
    }

    @Inject(method = "placeColumn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/BasaltColumnsFeature;setBlock(Lnet/minecraft/world/level/LevelWriter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", shift = At.Shift.AFTER))
    private void replaceAshy(LevelAccessor levelAccessor, int i, BlockPos blockPos, int j, int k, CallbackInfoReturnable<Boolean> cir, @Local BlockPos.MutableBlockPos mutableBlockPos) {
        if (levelAccessor.getBlockState(mutableBlockPos.below()).is(McddnBlocks.ASH) || levelAccessor.getBlockState(mutableBlockPos.below()).is(McddnBlocks.ASHY_BASALT)){
            levelAccessor.setBlock(mutableBlockPos.below(), Blocks.BASALT.defaultBlockState(), 3);
            if (levelAccessor.getBlockState(mutableBlockPos.below(2)).is(McddnBlocks.ASHY_BASALT)){
                levelAccessor.setBlock(mutableBlockPos.below(2), Blocks.BASALT.defaultBlockState(), 3);
            }
        }
    }
}
