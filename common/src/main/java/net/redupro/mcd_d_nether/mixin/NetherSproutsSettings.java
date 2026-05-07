package net.redupro.mcd_d_nether.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Blocks.class)
public abstract class NetherSproutsSettings {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE:LAST", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;offsetType(Lnet/minecraft/world/level/block/state/BlockBehaviour$OffsetType;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;"), slice=@Slice(to=@At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;NETHER_SPROUTS:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static BlockBehaviour.OffsetType editOffsetType(BlockBehaviour.OffsetType offsetType) {
        return BlockBehaviour.OffsetType.XYZ;
    }
}