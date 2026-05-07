package net.redupro.mcd_d_nether;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.redupro.mcd_d_nether.block.McddnBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod(Constants.MOD_ID)
public class McddnNeo {
    public McddnNeo(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(McddnBlocks.WARPED_BLOSSOM);
            event.accept(McddnBlocks.WARPED_WART_FLUFF);
            event.accept(McddnBlocks.NETHER_WART_FLUFF);
            event.accept(McddnBlocks.WARPING_VINES);
            event.accept(McddnBlocks.WARPED_STALK);
            event.accept(McddnBlocks.CRIMSON_STALK);
            event.accept(McddnBlocks.SHY_SUCCULENT);
            event.accept(McddnBlocks.MOONLIGHT_MILDEW);
            event.accept(McddnBlocks.MIDNIGHT_MILDEW);
            event.accept(McddnBlocks.FLUORESCENT_FLOWER);
            event.accept(McddnBlocks.FLUORESCENT_FIG);
            event.accept(McddnBlocks.FLUORESCENT_FUNGUS);
            event.accept(McddnBlocks.FEELER_FLOWER);
            event.accept(McddnBlocks.FROG_FLOWER);
            event.accept(McddnBlocks.FUNGAL_FERN);
            event.accept(McddnBlocks.SIPHON_STALK);
            event.accept(McddnBlocks.NETHER_WALL_FLOWER);
            event.accept(McddnBlocks.BLOODTHORN_BLOSSOM);
            event.accept(McddnBlocks.FLUORESCENT_FLOWER_INV);
            event.accept(McddnBlocks.GASEOUS_GLOB);
            event.accept(McddnBlocks.OBSERVER_ORCHIDS);
            event.accept(McddnBlocks.STOUTSHROOM);
            event.accept(McddnBlocks.RED_BRUSH);
            event.accept(McddnBlocks.TEAL_BRUSH);
            event.accept(McddnBlocks.MOONLIGHT_MOULD);
            event.accept(McddnBlocks.MIDNIGHT_MOULD);
            event.accept(McddnBlocks.CRIMSON_IVY);
            event.accept(McddnBlocks.MIDNIGHT_WART_BLOCK);
            event.accept(McddnBlocks.SPORANGIUM);
            event.accept(McddnBlocks.TALL_SPORANGIUM);
            event.accept(McddnBlocks.WARPED_FILAMENT);
            event.accept(McddnBlocks.CRIMSON_FILAMENT);
            event.accept(McddnBlocks.CRIMSON_SPROUTS);
            event.accept(McddnBlocks.ASH);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(McddnBlocks.ORNATE_BLACKSTONE_TILES);
            event.accept(McddnBlocks.MOLDY_ORNATE_BLACKSTONE_TILES);
            event.accept(McddnBlocks.BLACKSTONE_TILES);
            event.accept(McddnBlocks.BLACKSTONE_TILE_SLAB);
            event.accept(McddnBlocks.BLACKSTONE_TILES_STAIRS);
            event.accept(McddnBlocks.MOLDY_BLACKSTONE_TILES);
            event.accept(McddnBlocks.SPALLING_BLACKSTONE_TILES);
            event.accept(McddnBlocks.POLISHED_NETHERRACK);
            event.accept(McddnBlocks.POLISHED_NETHERRACK_SLAB);
            event.accept(McddnBlocks.ORNATE_POLISHED_NETHERRACK);
            event.accept(McddnBlocks.NETHER_BRICK_PILLAR);
            event.accept(McddnBlocks.ORNATE_NETHER_TILES);
            event.accept(McddnBlocks.MOLDY_ORNATE_NETHER_TILES);
            event.accept(McddnBlocks.NETHER_TILES);
            event.accept(McddnBlocks.NETHER_TILE_SLAB);
            event.accept(McddnBlocks.NETHER_TILE_STAIRS);
            event.accept(McddnBlocks.MOLDY_NETHER_TILES);
            event.accept(McddnBlocks.SPALLING_NETHER_TILES);
            event.accept(McddnBlocks.SPALLING_NETHER_BRICKS);
            event.accept(McddnBlocks.POLISHED_RED_NETHERRACK);
            event.accept(McddnBlocks.POLISHED_RED_NETHERRACK_SLAB);
            event.accept(McddnBlocks.ORNATE_POLISHED_RED_NETHERRACK);
            event.accept(McddnBlocks.RED_NETHER_BRICK_PILLAR);
            event.accept(McddnBlocks.ORNATE_RED_NETHER_TILES);
            event.accept(McddnBlocks.MOLDY_ORNATE_RED_NETHER_TILES);
            event.accept(McddnBlocks.RED_NETHER_TILES);
            event.accept(McddnBlocks.RED_NETHER_TILE_SLAB);
            event.accept(McddnBlocks.RED_NETHER_TILE_STAIRS);
            event.accept(McddnBlocks.MOLDY_RED_NETHER_TILES);
            event.accept(McddnBlocks.SPALLING_RED_NETHER_TILES);
            event.accept(McddnBlocks.SPALLING_RED_NETHER_BRICKS);
            event.accept(McddnBlocks.POLISHED_DARK_NETHERRACK);
            event.accept(McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB);
            event.accept(McddnBlocks.DARK_NETHER_BRICKS);
            event.accept(McddnBlocks.DARK_NETHER_BRICK_SLAB);
            event.accept(McddnBlocks.DARK_NETHER_BRICK_STAIRS);
            event.accept(McddnBlocks.DARK_NETHER_BRICK_FENCE);
            event.accept(McddnBlocks.DARK_NETHER_BRICK_WALL);
            event.accept(McddnBlocks.BASALT_TILES);
            event.accept(McddnBlocks.BASALT_TILE_SLAB);
            event.accept(McddnBlocks.BASALT_TILE_STAIRS);
            event.accept(McddnBlocks.GILDED_BASALT_TILES);
            event.accept(McddnBlocks.SPALLING_BASALT_TILES);
            event.accept(McddnBlocks.ROUGH_NETHERRACK);
            event.accept(McddnBlocks.BONE_FENCE);
            event.accept(McddnBlocks.NETHERITE_BARS);
            event.accept(McddnBlocks.NETHERITE_SPIKE);
            event.accept(McddnBlocks.NETHERITE_CHAIN);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }


}