package net.redupro.mcd_d_nether.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class WarpedTrunk extends Block {
    public static final IntegerProperty LAYER = IntegerProperty.create("layer", 1, 3);
    public WarpedTrunk(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(LAYER, 1));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYER);
    }
}
