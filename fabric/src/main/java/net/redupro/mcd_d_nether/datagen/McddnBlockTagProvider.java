package net.redupro.mcd_d_nether.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.redupro.mcd_d_nether.registry.McddnBlocks;

import java.util.concurrent.CompletableFuture;

public class McddnBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public McddnBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.FENCES)
                .add(McddnBlocks.DARK_NETHER_BRICK_FENCE)
                .add(McddnBlocks.BONE_FENCE);
        valueLookupBuilder(BlockTags.WALLS)
                .add(McddnBlocks.DARK_NETHER_BRICK_WALL);
        valueLookupBuilder(BlockTags.CLIMBABLE)
                .add(McddnBlocks.CRIMSON_IVY)
                .add(McddnBlocks.WARPING_VINES)
                .add(McddnBlocks.WARPING_VINES_PLANT);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(McddnBlocks.ASH);
        valueLookupBuilder(BlockTags.GUARDED_BY_PIGLINS).add(McddnBlocks.GILDED_BASALT_TILES);
        valueLookupBuilder(BlockTags.INFINIBURN_NETHER).add(McddnBlocks.ROUGH_NETHERRACK);
        valueLookupBuilder(BlockTags.INFINIBURN_END).add(McddnBlocks.ROUGH_NETHERRACK);
        valueLookupBuilder(BlockTags.INFINIBURN_OVERWORLD).add(McddnBlocks.ROUGH_NETHERRACK);
        valueLookupBuilder(BlockTags.NETHER_CARVER_REPLACEABLES).add(McddnBlocks.ROUGH_NETHERRACK);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(McddnBlocks.BLACKSTONE_TILES)
                .add(McddnBlocks.BLACKSTONE_TILE_SLAB)
                .add(McddnBlocks.BLACKSTONE_TILES_STAIRS)
                .add(McddnBlocks.ORNATE_BLACKSTONE_TILES)
                .add(McddnBlocks.SPALLING_BLACKSTONE_TILES)
                .add(McddnBlocks.NETHER_BRICK_PILLAR)
                .add(McddnBlocks.NETHER_TILES)
                .add(McddnBlocks.NETHER_TILE_SLAB)
                .add(McddnBlocks.NETHER_TILE_STAIRS)
                .add(McddnBlocks.ORNATE_NETHER_TILES)
                .add(McddnBlocks.SPALLING_NETHER_BRICKS)
                .add(McddnBlocks.SPALLING_NETHER_TILES)
                .add(McddnBlocks.RED_NETHER_BRICK_PILLAR)
                .add(McddnBlocks.RED_NETHER_TILES)
                .add(McddnBlocks.RED_NETHER_TILE_SLAB)
                .add(McddnBlocks.RED_NETHER_TILE_STAIRS)
                .add(McddnBlocks.ORNATE_POLISHED_RED_NETHERRACK)
                .add(McddnBlocks.ORNATE_POLISHED_NETHERRACK)
                .add(McddnBlocks.ORNATE_RED_NETHER_TILES)
                .add(McddnBlocks.SPALLING_RED_NETHER_BRICKS)
                .add(McddnBlocks.SPALLING_RED_NETHER_TILES)
                .add(McddnBlocks.POLISHED_NETHERRACK)
                .add(McddnBlocks.POLISHED_NETHERRACK_SLAB)
                .add(McddnBlocks.POLISHED_RED_NETHERRACK)
                .add(McddnBlocks.POLISHED_RED_NETHERRACK_SLAB)
                .add(McddnBlocks.POLISHED_DARK_NETHERRACK)
                .add(McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB)
                .add(McddnBlocks.DARK_NETHER_BRICKS)
                .add(McddnBlocks.DARK_NETHER_BRICK_SLAB)
                .add(McddnBlocks.DARK_NETHER_BRICK_STAIRS)
                .add(McddnBlocks.DARK_NETHER_BRICK_FENCE)
                .add(McddnBlocks.DARK_NETHER_BRICK_WALL)
                .add(McddnBlocks.MOLDY_ORNATE_BLACKSTONE_TILES)
                .add(McddnBlocks.MOLDY_BLACKSTONE_TILES)
                .add(McddnBlocks.MOLDY_ORNATE_NETHER_TILES)
                .add(McddnBlocks.MOLDY_NETHER_TILES)
                .add(McddnBlocks.MOLDY_ORNATE_RED_NETHER_TILES)
                .add(McddnBlocks.MOLDY_RED_NETHER_TILES)
                .add(McddnBlocks.ROUGH_NETHERRACK)
                .add(McddnBlocks.ASHY_BASALT)
                .add(McddnBlocks.BONE_FENCE)
                .add(McddnBlocks.BASALT_TILES)
                .add(McddnBlocks.BASALT_TILE_SLAB)
                .add(McddnBlocks.BASALT_TILE_STAIRS)
                .add(McddnBlocks.GILDED_BASALT_TILES)
                .add(McddnBlocks.SPALLING_BASALT_TILES);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(McddnBlocks.WARPED_WART_FLUFF)
                .add(McddnBlocks.NETHER_WART_FLUFF)
                .add(McddnBlocks.MIDNIGHT_WART_BLOCK)
                .add(McddnBlocks.MIDNIGHT_MOULD)
                .add(McddnBlocks.MOONLIGHT_MOULD);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(McddnBlocks.WARPED_TRUNK);
    }
}
