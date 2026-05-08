package net.redupro.mcd_d_nether.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.redupro.mcd_d_nether.registry.McddnBlocks;

import java.util.concurrent.CompletableFuture;

public class McddnRecipeProvider extends FabricRecipeProvider {

    public McddnRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                //shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKSTONE_TILES.asItem(), 4).pattern("##").pattern("##").define('#', Items.POLISHED_BLACKSTONE).unlockedBy(getHasName(Items.POLISHED_BLACKSTONE), has(Items.POLISHED_BLACKSTONE));

                //Polished Blackstone
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILES.asItem(), Items.POLISHED_BLACKSTONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILE_SLAB.asItem(), Items.POLISHED_BLACKSTONE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILES_STAIRS.asItem(), Items.POLISHED_BLACKSTONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILES.asItem(), Items.BLACKSTONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILE_SLAB.asItem(), Items.BLACKSTONE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILES_STAIRS.asItem(), Items.BLACKSTONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILE_SLAB.asItem(), McddnBlocks.BLACKSTONE_TILES.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILES_STAIRS.asItem(), McddnBlocks.BLACKSTONE_TILES.asItem());
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BLACKSTONE_TILE_SLAB.asItem(), Ingredient.of(McddnBlocks.BLACKSTONE_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.BLACKSTONE_TILES.asItem()), has(McddnBlocks.BLACKSTONE_TILES.asItem()))
                        .save(output);
                stairBuilder(McddnBlocks.BLACKSTONE_TILES_STAIRS.asItem(), Ingredient.of(McddnBlocks.BLACKSTONE_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.BLACKSTONE_TILES.asItem()), has(McddnBlocks.BLACKSTONE_TILES.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.SPALLING_BLACKSTONE_TILES.asItem(), McddnBlocks.BLACKSTONE_TILES.asItem());
                shapeless(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.MOLDY_BLACKSTONE_TILES.asItem())
                        .requires(McddnBlocks.BLACKSTONE_TILES.asItem())
                        .requires(McddnBlocks.NETHER_WART_FLUFF.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_BLACKSTONE_TILES.asItem(), 2)
                        .pattern("##")
                        .pattern("##")
                        .define('#', McddnBlocks.BLACKSTONE_TILE_SLAB.asItem())
                        .unlockedBy(getHasName(McddnBlocks.BLACKSTONE_TILE_SLAB.asItem()), has(McddnBlocks.BLACKSTONE_TILE_SLAB.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_BLACKSTONE_TILES.asItem(), Items.BLACKSTONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_BLACKSTONE_TILES.asItem(), Items.POLISHED_BLACKSTONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_BLACKSTONE_TILES.asItem(), McddnBlocks.BLACKSTONE_TILES.asItem());
                shapeless(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.MOLDY_ORNATE_BLACKSTONE_TILES.asItem())
                        .requires(McddnBlocks.ORNATE_BLACKSTONE_TILES.asItem())
                        .requires(McddnBlocks.NETHER_WART_FLUFF.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);

                //Polished Netherrack
                polishedBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_NETHERRACK.asItem(), Ingredient.of(Items.NETHERRACK))
                        .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_NETHERRACK_SLAB.asItem(), Ingredient.of(McddnBlocks.POLISHED_NETHERRACK.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_NETHERRACK.asItem()), has(McddnBlocks.POLISHED_NETHERRACK.asItem()))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_POLISHED_NETHERRACK.asItem(), 2)
                        .pattern("##")
                        .pattern("##")
                        .define('#', McddnBlocks.POLISHED_NETHERRACK_SLAB.asItem())
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_NETHERRACK_SLAB), has(McddnBlocks.POLISHED_NETHERRACK_SLAB))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_NETHERRACK_SLAB.asItem(), McddnBlocks.POLISHED_NETHERRACK.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_POLISHED_NETHERRACK.asItem(), McddnBlocks.POLISHED_NETHERRACK.asItem());
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_NETHER_TILES.asItem(), 2)
                        .pattern("##")
                        .pattern("##")
                        .define('#', McddnBlocks.NETHER_TILE_SLAB.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_TILE_SLAB.asItem()), has(McddnBlocks.NETHER_TILE_SLAB.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_NETHER_TILES.asItem(), McddnBlocks.POLISHED_NETHERRACK.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_NETHER_TILES.asItem(), McddnBlocks.NETHER_TILES.asItem());
                shapeless(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.MOLDY_ORNATE_NETHER_TILES.asItem())
                        .requires(McddnBlocks.ORNATE_NETHER_TILES.asItem())
                        .requires(McddnBlocks.NETHER_WART_FLUFF.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);
                polishedBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_TILES.asItem(), Ingredient.of(McddnBlocks.POLISHED_NETHERRACK.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_NETHERRACK.asItem()), has(McddnBlocks.POLISHED_NETHERRACK.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_TILES.asItem(), McddnBlocks.POLISHED_NETHERRACK.asItem());
                shapeless(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.MOLDY_NETHER_TILES.asItem())
                        .requires(McddnBlocks.NETHER_TILES.asItem())
                        .requires(McddnBlocks.NETHER_WART_FLUFF.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.SPALLING_NETHER_TILES.asItem(), McddnBlocks.NETHER_TILES.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_TILE_SLAB.asItem(), McddnBlocks.NETHER_TILES.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_TILE_SLAB.asItem(), McddnBlocks.POLISHED_NETHERRACK.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_TILE_STAIRS.asItem(), McddnBlocks.NETHER_TILES.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_TILE_STAIRS.asItem(), McddnBlocks.POLISHED_NETHERRACK.asItem());
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_TILE_SLAB.asItem(), Ingredient.of(McddnBlocks.NETHER_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.NETHER_TILES.asItem()), has(McddnBlocks.NETHER_TILES.asItem()))
                        .save(output);
                stairBuilder(McddnBlocks.NETHER_TILE_STAIRS.asItem(), Ingredient.of(McddnBlocks.NETHER_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.NETHER_TILES.asItem()), has(McddnBlocks.NETHER_TILES.asItem()))
                        .save(output);

                //Polished Red Netherrack
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_RED_NETHERRACK.asItem(), 2)
                        .pattern("#W")
                        .pattern("W#")
                        .define('#', Items.NETHERRACK)
                        .define('W', Items.NETHER_WART)
                        .unlockedBy(getHasName(Items.NETHER_WART), has(Items.NETHER_WART))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_RED_NETHERRACK_SLAB.asItem(), Ingredient.of(McddnBlocks.POLISHED_RED_NETHERRACK.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_RED_NETHERRACK.asItem()), has(McddnBlocks.POLISHED_RED_NETHERRACK.asItem()))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_POLISHED_RED_NETHERRACK.asItem(), 2)
                        .pattern("##")
                        .pattern("##")
                        .define('#', McddnBlocks.POLISHED_RED_NETHERRACK_SLAB.asItem())
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_RED_NETHERRACK_SLAB), has(McddnBlocks.POLISHED_RED_NETHERRACK_SLAB))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_RED_NETHERRACK_SLAB.asItem(), McddnBlocks.POLISHED_RED_NETHERRACK.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_POLISHED_RED_NETHERRACK.asItem(), McddnBlocks.POLISHED_RED_NETHERRACK.asItem());
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_RED_NETHER_TILES.asItem(), 2)
                        .pattern("##")
                        .pattern("##")
                        .define('#', McddnBlocks.RED_NETHER_TILE_SLAB.asItem())
                        .unlockedBy(getHasName(McddnBlocks.RED_NETHER_TILE_SLAB.asItem()), has(McddnBlocks.RED_NETHER_TILE_SLAB.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_RED_NETHER_TILES.asItem(), McddnBlocks.POLISHED_RED_NETHERRACK.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ORNATE_RED_NETHER_TILES.asItem(), McddnBlocks.RED_NETHER_TILES.asItem());
                shapeless(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.MOLDY_ORNATE_RED_NETHER_TILES.asItem())
                        .requires(McddnBlocks.ORNATE_RED_NETHER_TILES.asItem())
                        .requires(McddnBlocks.NETHER_WART_FLUFF.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);
                polishedBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.RED_NETHER_TILES.asItem(), Ingredient.of(McddnBlocks.POLISHED_RED_NETHERRACK.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_RED_NETHERRACK.asItem()), has(McddnBlocks.POLISHED_RED_NETHERRACK.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.RED_NETHER_TILES.asItem(), McddnBlocks.POLISHED_RED_NETHERRACK.asItem());
                shapeless(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.MOLDY_RED_NETHER_TILES.asItem())
                        .requires(McddnBlocks.RED_NETHER_TILES.asItem())
                        .requires(McddnBlocks.NETHER_WART_FLUFF.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.SPALLING_RED_NETHER_TILES.asItem(), McddnBlocks.RED_NETHER_TILES.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.RED_NETHER_TILE_SLAB.asItem(), McddnBlocks.RED_NETHER_TILES.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.RED_NETHER_TILE_SLAB.asItem(), McddnBlocks.POLISHED_RED_NETHERRACK.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.RED_NETHER_TILE_STAIRS.asItem(), McddnBlocks.RED_NETHER_TILES.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.RED_NETHER_TILE_STAIRS.asItem(), McddnBlocks.POLISHED_RED_NETHERRACK.asItem());
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.RED_NETHER_TILE_SLAB.asItem(), Ingredient.of(McddnBlocks.RED_NETHER_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.RED_NETHER_TILES.asItem()), has(McddnBlocks.RED_NETHER_TILES.asItem()))
                        .save(output);
                stairBuilder(McddnBlocks.RED_NETHER_TILE_STAIRS.asItem(), Ingredient.of(McddnBlocks.RED_NETHER_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.RED_NETHER_TILES.asItem()), has(McddnBlocks.RED_NETHER_TILES.asItem()))
                        .save(output);

                //Polished Dark Netherrack
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_DARK_NETHERRACK.asItem(), 8)
                        .pattern("###")
                        .pattern("#B#")
                        .pattern("###")
                        .define('#', McddnBlocks.POLISHED_NETHERRACK.asItem())
                        .define('B', Items.BLACK_DYE)
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_NETHERRACK.asItem()), has(McddnBlocks.POLISHED_NETHERRACK.asItem()))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB.asItem(), Ingredient.of(McddnBlocks.POLISHED_DARK_NETHERRACK.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.POLISHED_DARK_NETHERRACK.asItem()), has(McddnBlocks.POLISHED_DARK_NETHERRACK.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.POLISHED_DARK_NETHERRACK_SLAB.asItem(), McddnBlocks.POLISHED_DARK_NETHERRACK.asItem(), 2);

                //Dark Nether Bricks
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.DARK_NETHER_BRICKS.asItem(), 8)
                        .pattern("###")
                        .pattern("#B#")
                        .pattern("###")
                        .define('#', Items.NETHER_BRICKS)
                        .define('B', Items.BLACK_DYE)
                        .group("dark_nether_bricks")
                        .unlockedBy(getHasName(Items.NETHER_BRICKS), has(Items.NETHER_BRICKS))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.SPALLING_NETHER_BRICKS.asItem(), Items.NETHER_BRICKS);
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.NETHER_BRICK_PILLAR.asItem(), 2)
                        .pattern("#")
                        .pattern("#")
                        .define('#', Items.NETHER_BRICKS)
                        .unlockedBy(getHasName(Items.NETHER_BRICKS), has(Items.NETHER_BRICKS))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.DARK_NETHER_BRICK_SLAB.asItem(), Ingredient.of(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.DARK_NETHER_BRICKS.asItem()), has(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .save(output);
                stairBuilder(McddnBlocks.DARK_NETHER_BRICK_STAIRS.asItem(), Ingredient.of(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.DARK_NETHER_BRICKS.asItem()), has(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .save(output);
                fenceBuilder(McddnBlocks.DARK_NETHER_BRICK_FENCE.asItem(), Ingredient.of(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.DARK_NETHER_BRICKS.asItem()), has(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .save(output);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.DARK_NETHER_BRICK_WALL.asItem(), Ingredient.of(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.DARK_NETHER_BRICKS.asItem()), has(McddnBlocks.DARK_NETHER_BRICKS.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.DARK_NETHER_BRICK_SLAB.asItem(), McddnBlocks.DARK_NETHER_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.DARK_NETHER_BRICK_STAIRS.asItem(), McddnBlocks.DARK_NETHER_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.DARK_NETHER_BRICK_WALL.asItem(), McddnBlocks.DARK_NETHER_BRICKS.asItem());

                //basalt
                polishedBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BASALT_TILES.asItem(), Ingredient.of(Items.POLISHED_BASALT))
                        .unlockedBy(getHasName(Items.POLISHED_BASALT), has(Items.POLISHED_BASALT))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BASALT_TILE_SLAB.asItem(), Ingredient.of(McddnBlocks.BASALT_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.BASALT_TILES.asItem()), has(McddnBlocks.BASALT_TILES.asItem()))
                        .save(output);
                stairBuilder(McddnBlocks.BASALT_TILE_STAIRS.asItem(), Ingredient.of(McddnBlocks.BASALT_TILES.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.BASALT_TILES.asItem()), has(McddnBlocks.BASALT_TILES.asItem()))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BASALT_TILES.asItem(), Items.POLISHED_BASALT);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.SPALLING_BASALT_TILES.asItem(), McddnBlocks.BASALT_TILES.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BASALT_TILE_SLAB.asItem(), McddnBlocks.BASALT_TILES.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BASALT_TILE_SLAB.asItem(), Items.POLISHED_BASALT, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BASALT_TILE_STAIRS.asItem(), McddnBlocks.BASALT_TILES.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.BASALT_TILE_STAIRS.asItem(), Items.POLISHED_BASALT);
                shaped(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.GILDED_BASALT_TILES.asItem(), 8)
                        .pattern("###")
                        .pattern("#G#")
                        .pattern("###")
                        .define('#', McddnBlocks.BASALT_TILES.asItem())
                        .define('G', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(McddnBlocks.BASALT_TILES.asItem()), has(McddnBlocks.BASALT_TILES.asItem()))
                        .save(output);


                //misc
                shapeless(RecipeCategory.BUILDING_BLOCKS, McddnBlocks.ROUGH_NETHERRACK.asItem())
                        .requires(Items.NETHERRACK)
                        .requires(McddnBlocks.NETHER_WART_FLUFF.asItem())
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);
                polishedBuilder(RecipeCategory.BUILDING_BLOCKS, Items.NETHER_WART_BLOCK, Ingredient.of(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.NETHER_WART_FLUFF.asItem()), has(McddnBlocks.NETHER_WART_FLUFF.asItem()))
                        .save(output);
                polishedBuilder(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_WART_BLOCK, Ingredient.of(McddnBlocks.WARPED_WART_FLUFF.asItem()))
                        .unlockedBy(getHasName(McddnBlocks.WARPED_WART_FLUFF.asItem()), has(McddnBlocks.WARPED_WART_FLUFF.asItem()))
                        .save(output);

                //Dyes
                shapeless(RecipeCategory.MISC, Items.RED_DYE)
                        .requires(McddnBlocks.OBSERVER_ORCHIDS.asItem())
                        .group("red_dye")
                        .unlockedBy(getHasName(McddnBlocks.OBSERVER_ORCHIDS.asItem()), has(McddnBlocks.OBSERVER_ORCHIDS.asItem()))
                        .save(output);
                shapeless(RecipeCategory.MISC, Items.ORANGE_DYE)
                        .requires(McddnBlocks.WARPED_BLOSSOM.asItem())
                        .group("orange_dye")
                        .unlockedBy(getHasName(McddnBlocks.WARPED_BLOSSOM.asItem()), has(McddnBlocks.WARPED_BLOSSOM.asItem()))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
