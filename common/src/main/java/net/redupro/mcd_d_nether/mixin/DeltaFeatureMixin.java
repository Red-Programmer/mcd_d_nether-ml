package net.redupro.mcd_d_nether.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.DeltaFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DeltaFeature.class)
public class DeltaFeatureMixin {
    @Definition(id = "isAir", method = "Lnet/minecraft/world/level/block/state/BlockState;isAir()Z")
    @Expression("?.isAir()")
    @ModifyExpressionValue(method = "isClear", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean isAsh(boolean original, LevelAccessor levelAccessor, BlockPos blockPos, DeltaFeatureConfiguration deltaFeatureConfiguration, @Local Direction direction) {
        return original || levelAccessor.getBlockState(blockPos.relative(direction)).is(McddnBlocks.ASH);
    }
}
