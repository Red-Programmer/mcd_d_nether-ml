package net.redupro.mcd_d_nether;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class McddnClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block block : McddnCommonClient.transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }
    }
}
