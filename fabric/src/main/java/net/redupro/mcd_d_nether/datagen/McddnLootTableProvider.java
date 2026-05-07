package net.redupro.mcd_d_nether.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.block.custom.*;
import net.redupro.mcd_d_nether.block.enums.Quadrant;

import java.util.concurrent.CompletableFuture;

public class McddnLootTableProvider extends FabricBlockLootTableProvider {
    public McddnLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(McddnBlocks.FLUORESCENT_FLOWER);
        dropSelf(McddnBlocks.FLUORESCENT_FLOWER_INV);
        dropSelf(McddnBlocks.FLUORESCENT_FIG);
        dropSelf(McddnBlocks.WARPED_BLOSSOM);
        dropSelf(McddnBlocks.SHY_SUCCULENT);
        dropSelf(McddnBlocks.MOONLIGHT_MILDEW);
        dropSelf(McddnBlocks.MIDNIGHT_MILDEW);
        dropSelf(McddnBlocks.FLUORESCENT_FUNGUS);
        dropSelf(McddnBlocks.FEELER_FLOWER);
        dropSelf(McddnBlocks.FROG_FLOWER);
        dropSelf(McddnBlocks.FUNGAL_FERN);
        dropSelf(McddnBlocks.SIPHON_STALK);
        dropSelf(McddnBlocks.NETHER_WALL_FLOWER);
        dropSelf(McddnBlocks.BLOODTHORN_BLOSSOM);
        dropSelf(McddnBlocks.GASEOUS_GLOB);
        dropSelf(McddnBlocks.OBSERVER_ORCHIDS);
        dropSelf(McddnBlocks.STOUTSHROOM);
        dropSelf(McddnBlocks.MIDNIGHT_WART_BLOCK);
        dropSelf(McddnBlocks.SPORANGIUM);
        dropSelf(McddnBlocks.CRIMSON_SPROUTS);
        dropSelf(McddnBlocks.BLACKSTONE_TILES);
        add(McddnBlocks.BLACKSTONE_TILE_SLAB, createSlabItemTable(McddnBlocks.BLACKSTONE_TILE_SLAB));
        dropSelf(McddnBlocks.BLACKSTONE_TILES_STAIRS);
        dropSelf(McddnBlocks.ORNATE_BLACKSTONE_TILES);
        dropSelf(McddnBlocks.SPALLING_BLACKSTONE_TILES);
        dropSelf(McddnBlocks.NETHER_BRICK_PILLAR);
        dropSelf(McddnBlocks.NETHER_TILES);
        add(McddnBlocks.NETHER_TILE_SLAB, createSlabItemTable(McddnBlocks.NETHER_TILE_SLAB));
        dropSelf(McddnBlocks.NETHER_TILE_STAIRS);
        dropSelf(McddnBlocks.ORNATE_POLISHED_NETHERRACK);
        dropSelf(McddnBlocks.ORNATE_NETHER_TILES);
        dropSelf(McddnBlocks.SPALLING_NETHER_BRICKS);
        dropSelf(McddnBlocks.SPALLING_NETHER_TILES);
        dropSelf(McddnBlocks.RED_NETHER_BRICK_PILLAR);
        dropSelf(McddnBlocks.RED_NETHER_TILES);
        add(McddnBlocks.RED_NETHER_TILE_SLAB, createSlabItemTable(McddnBlocks.RED_NETHER_TILE_SLAB));
        dropSelf(McddnBlocks.RED_NETHER_TILE_STAIRS);
        dropSelf(McddnBlocks.ORNATE_POLISHED_RED_NETHERRACK);
        dropSelf(McddnBlocks.ORNATE_RED_NETHER_TILES);
        dropSelf(McddnBlocks.SPALLING_RED_NETHER_BRICKS);
        dropSelf(McddnBlocks.SPALLING_RED_NETHER_TILES);
        dropSelf(McddnBlocks.POLISHED_NETHERRACK);
        add(McddnBlocks.POLISHED_NETHERRACK_SLAB, createSlabItemTable(McddnBlocks.POLISHED_NETHERRACK_SLAB));
        dropSelf(McddnBlocks.POLISHED_RED_NETHERRACK);
        add(McddnBlocks.POLISHED_RED_NETHERRACK_SLAB, createSlabItemTable(McddnBlocks.POLISHED_RED_NETHERRACK_SLAB));
        dropSelf(McddnBlocks.POLISHED_DARK_NETHERRACK);
        add(McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB, createSlabItemTable(McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB));
        dropSelf(McddnBlocks.DARK_NETHER_BRICKS);
        add(McddnBlocks.DARK_NETHER_BRICK_SLAB, createSlabItemTable(McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB));
        dropSelf(McddnBlocks.DARK_NETHER_BRICK_STAIRS);
        dropSelf(McddnBlocks.DARK_NETHER_BRICK_FENCE);
        dropSelf(McddnBlocks.DARK_NETHER_BRICK_WALL);
        dropSelf(McddnBlocks.MOLDY_ORNATE_BLACKSTONE_TILES);
        dropSelf(McddnBlocks.MOLDY_BLACKSTONE_TILES);
        dropSelf(McddnBlocks.MOLDY_ORNATE_NETHER_TILES);
        dropSelf(McddnBlocks.MOLDY_NETHER_TILES);
        dropSelf(McddnBlocks.MOLDY_ORNATE_RED_NETHER_TILES);
        dropSelf(McddnBlocks.MOLDY_RED_NETHER_TILES);
        dropSelf(McddnBlocks.ROUGH_NETHERRACK);
        dropSelf(McddnBlocks.BONE_FENCE);
        dropSelf(McddnBlocks.BASALT_TILES);
        add(McddnBlocks.BASALT_TILE_SLAB, createSlabItemTable(McddnBlocks.BASALT_TILE_SLAB));
        dropSelf(McddnBlocks.BASALT_TILE_STAIRS);
        dropSelf(McddnBlocks.GILDED_BASALT_TILES);
        dropSelf(McddnBlocks.SPALLING_BASALT_TILES);
        add(McddnBlocks.POTTED_WARPED_BLOSSOM, createPotFlowerItemTable(McddnBlocks.WARPED_BLOSSOM));
        add(McddnBlocks.POTTED_CRIMSON_SPROUTS, createPotFlowerItemTable(McddnBlocks.CRIMSON_SPROUTS));
        add(McddnBlocks.WARPED_STALK, block -> this.createSinglePropConditionTable(block, TallRootsBlock.HALF, DoubleBlockHalf.LOWER));
        add(McddnBlocks.CRIMSON_STALK, block -> this.createSinglePropConditionTable(block, TallRootsBlock.HALF, DoubleBlockHalf.LOWER));
        add(McddnBlocks.WARPED_WART_FLUFF, block -> this.createSinglePropConditionTable(block, WartFluffBlock.HALF, DoubleBlockHalf.LOWER));
        add(McddnBlocks.NETHER_WART_FLUFF, block -> this.createSinglePropConditionTable(block, WartFluffBlock.HALF, DoubleBlockHalf.LOWER));
        add(McddnBlocks.TALL_SPORANGIUM, block -> this.createSinglePropConditionTable(block, TallRootsBlock.HALF, DoubleBlockHalf.LOWER));
        add(McddnBlocks.WARPED_FILAMENT, block -> this.createSinglePropConditionTable(block, TallRootsBlock.HALF, DoubleBlockHalf.LOWER));
        add(McddnBlocks.CRIMSON_FILAMENT, block -> this.createSinglePropConditionTable(block, TallRootsBlock.HALF, DoubleBlockHalf.LOWER));
        add(McddnBlocks.ASHY_BASALT, createSingleItemTable(Items.BASALT));
        add(McddnBlocks.WARPED_TRUNK, createSingleItemTable(Items.WARPED_STEM));
        this.add(McddnBlocks.RED_BRUSH, block -> this.createSinglePropConditionTable(block, QuadBrushBlock.QUADRANT, Quadrant.QUAD_1));
        this.add(McddnBlocks.TEAL_BRUSH, block -> this.createSinglePropConditionTable(block, QuadBrushBlock.QUADRANT, Quadrant.QUAD_1));
        this.add(McddnBlocks.CRIMSON_IVY, block -> this.customDropsWithProperty(block, block, IvyBlock.FRUIT, true));
    }

    protected LootTable.Builder customDropsWithProperty(Block block, Block drop, Property<Boolean> property, boolean value) {
        return LootTable.lootTable()
                .withPool(
                        this.applyExplosionCondition(
                                drop,
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(drop)
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value)))
                                        )
                        )
                );
    }
}
