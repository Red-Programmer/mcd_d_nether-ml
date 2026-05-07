package net.redupro.mcd_d_nether;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.redupro.mcd_d_nether.block.McddnBlocks;

public class McddnFabric implements ModInitializer {
    @Override
	public void onInitialize() {
        McddnCommon.init();
        ResourceLoader.registerBuiltinPack(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "custom_fortress"), FabricLoader.getInstance().getModContainer(Constants.MOD_ID).orElseThrow(), Component.translatable("Custom Nether Fortress"), PackActivationType.DEFAULT_ENABLED);

        Constants.LOGGER.info("Registering Mod Blocks for " + Constants.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.accept(McddnBlocks.WARPED_BLOSSOM);
            fabricItemGroupEntries.accept(McddnBlocks.WARPED_WART_FLUFF);
            fabricItemGroupEntries.accept(McddnBlocks.NETHER_WART_FLUFF);
            fabricItemGroupEntries.accept(McddnBlocks.WARPING_VINES);
            fabricItemGroupEntries.accept(McddnBlocks.WARPED_STALK);
            fabricItemGroupEntries.accept(McddnBlocks.CRIMSON_STALK);
            fabricItemGroupEntries.accept(McddnBlocks.SHY_SUCCULENT);
            fabricItemGroupEntries.accept(McddnBlocks.MOONLIGHT_MILDEW);
            fabricItemGroupEntries.accept(McddnBlocks.MIDNIGHT_MILDEW);
            fabricItemGroupEntries.accept(McddnBlocks.FLUORESCENT_FLOWER);
            fabricItemGroupEntries.accept(McddnBlocks.FLUORESCENT_FIG);
            fabricItemGroupEntries.accept(McddnBlocks.FLUORESCENT_FUNGUS);
            fabricItemGroupEntries.accept(McddnBlocks.FEELER_FLOWER);
            fabricItemGroupEntries.accept(McddnBlocks.FROG_FLOWER);
            fabricItemGroupEntries.accept(McddnBlocks.FUNGAL_FERN);
            fabricItemGroupEntries.accept(McddnBlocks.SIPHON_STALK);
            fabricItemGroupEntries.accept(McddnBlocks.NETHER_WALL_FLOWER);
            fabricItemGroupEntries.accept(McddnBlocks.BLOODTHORN_BLOSSOM);
            fabricItemGroupEntries.accept(McddnBlocks.FLUORESCENT_FLOWER_INV);
            fabricItemGroupEntries.accept(McddnBlocks.GASEOUS_GLOB);
            fabricItemGroupEntries.accept(McddnBlocks.OBSERVER_ORCHIDS);
            fabricItemGroupEntries.accept(McddnBlocks.STOUTSHROOM);
            fabricItemGroupEntries.accept(McddnBlocks.RED_BRUSH);
            fabricItemGroupEntries.accept(McddnBlocks.TEAL_BRUSH);
            fabricItemGroupEntries.accept(McddnBlocks.MOONLIGHT_MOULD);
            fabricItemGroupEntries.accept(McddnBlocks.MIDNIGHT_MOULD);
            fabricItemGroupEntries.accept(McddnBlocks.CRIMSON_IVY);
            fabricItemGroupEntries.accept(McddnBlocks.MIDNIGHT_WART_BLOCK);
            fabricItemGroupEntries.accept(McddnBlocks.SPORANGIUM);
            fabricItemGroupEntries.accept(McddnBlocks.TALL_SPORANGIUM);
            fabricItemGroupEntries.accept(McddnBlocks.WARPED_FILAMENT);
            fabricItemGroupEntries.accept(McddnBlocks.CRIMSON_FILAMENT);
            fabricItemGroupEntries.accept(McddnBlocks.CRIMSON_SPROUTS);
            fabricItemGroupEntries.accept(McddnBlocks.ASH);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.accept(McddnBlocks.ORNATE_BLACKSTONE_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.MOLDY_ORNATE_BLACKSTONE_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.BLACKSTONE_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.BLACKSTONE_TILE_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.BLACKSTONE_TILES_STAIRS);
            fabricItemGroupEntries.accept(McddnBlocks.MOLDY_BLACKSTONE_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.SPALLING_BLACKSTONE_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.POLISHED_NETHERRACK);
            fabricItemGroupEntries.accept(McddnBlocks.POLISHED_NETHERRACK_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.ORNATE_POLISHED_NETHERRACK);
            fabricItemGroupEntries.accept(McddnBlocks.NETHER_BRICK_PILLAR);
            fabricItemGroupEntries.accept(McddnBlocks.ORNATE_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.MOLDY_ORNATE_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.NETHER_TILE_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.NETHER_TILE_STAIRS);
            fabricItemGroupEntries.accept(McddnBlocks.MOLDY_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.SPALLING_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.SPALLING_NETHER_BRICKS);
            fabricItemGroupEntries.accept(McddnBlocks.POLISHED_RED_NETHERRACK);
            fabricItemGroupEntries.accept(McddnBlocks.POLISHED_RED_NETHERRACK_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.ORNATE_POLISHED_RED_NETHERRACK);
            fabricItemGroupEntries.accept(McddnBlocks.RED_NETHER_BRICK_PILLAR);
            fabricItemGroupEntries.accept(McddnBlocks.ORNATE_RED_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.MOLDY_ORNATE_RED_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.RED_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.RED_NETHER_TILE_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.RED_NETHER_TILE_STAIRS);
            fabricItemGroupEntries.accept(McddnBlocks.MOLDY_RED_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.SPALLING_RED_NETHER_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.SPALLING_RED_NETHER_BRICKS);
            fabricItemGroupEntries.accept(McddnBlocks.POLISHED_DARK_NETHERRACK);
            fabricItemGroupEntries.accept(McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.DARK_NETHER_BRICKS);
            fabricItemGroupEntries.accept(McddnBlocks.DARK_NETHER_BRICK_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.DARK_NETHER_BRICK_STAIRS);
            fabricItemGroupEntries.accept(McddnBlocks.DARK_NETHER_BRICK_FENCE);
            fabricItemGroupEntries.accept(McddnBlocks.DARK_NETHER_BRICK_WALL);
            fabricItemGroupEntries.accept(McddnBlocks.BASALT_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.BASALT_TILE_SLAB);
            fabricItemGroupEntries.accept(McddnBlocks.BASALT_TILE_STAIRS);
            fabricItemGroupEntries.accept(McddnBlocks.GILDED_BASALT_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.SPALLING_BASALT_TILES);
            fabricItemGroupEntries.accept(McddnBlocks.ROUGH_NETHERRACK);
            fabricItemGroupEntries.accept(McddnBlocks.BONE_FENCE);
            fabricItemGroupEntries.accept(McddnBlocks.NETHERITE_BARS);
            fabricItemGroupEntries.accept(McddnBlocks.NETHERITE_SPIKE);
            fabricItemGroupEntries.accept(McddnBlocks.NETHERITE_CHAIN);
        });
    }
}