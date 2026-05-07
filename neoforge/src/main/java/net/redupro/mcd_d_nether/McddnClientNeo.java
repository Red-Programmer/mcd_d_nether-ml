package net.redupro.mcd_d_nether;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class McddnClientNeo {
    @SubscribeEvent
    static void init(FMLClientSetupEvent event) {
        //for (Block block : McddnCommonClient.transparentBlocks) {
            //ItemBlockRenderTypes.setRenderLayer(block, ChunkSectionLayer.CUTOUT);
        //}
    }
}
