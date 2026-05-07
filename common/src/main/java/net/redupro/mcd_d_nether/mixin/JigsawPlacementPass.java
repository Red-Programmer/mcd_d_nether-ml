package net.redupro.mcd_d_nether.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.redupro.mcd_d_nether.world.level.levelgen.structure.pools.AdvancedFeaturePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement$Placer")
public class JigsawPlacementPass {
    @WrapOperation(method = "tryPlacingChildren", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/shapes/Shapes;joinIsNotEmpty(Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/BooleanOp;)Z"))
    public boolean joinIsNotEmpty(VoxelShape first, VoxelShape second, BooleanOp op, Operation<Boolean> original, @Local(ordinal = 1) StructurePoolElement targetElement){
        if (targetElement instanceof AdvancedFeaturePoolElement){
            return false;
        }
        return original.call(first, second, op);
    }
}