package net.redupro.mcd_d_nether.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeepingVinesBlock;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import org.jetbrains.annotations.NotNull;

public class WarpingVinesBlock extends WeepingVinesBlock {
    public WarpingVinesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull Block getBodyBlock() {
        return McddnBlocks.WARPING_VINES_PLANT;
    }
}
