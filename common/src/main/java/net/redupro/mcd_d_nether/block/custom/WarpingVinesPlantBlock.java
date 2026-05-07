package net.redupro.mcd_d_nether.block.custom;

import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.WeepingVinesPlantBlock;
import net.redupro.mcd_d_nether.block.McddnBlocks;

public class WarpingVinesPlantBlock extends WeepingVinesPlantBlock {
    public WarpingVinesPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) McddnBlocks.WARPING_VINES;
    }
}
