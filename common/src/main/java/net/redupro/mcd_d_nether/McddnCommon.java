package net.redupro.mcd_d_nether;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.redupro.mcd_d_nether.block.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.redupro.mcd_d_nether.world.level.levelgen.feature.*;
import net.redupro.mcd_d_nether.world.level.levelgen.structure.pools.AdvancedFeaturePoolElement;

public class McddnCommon {
    public static final Identifier MCDDN_HUGE_FUNGUS_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "mcddn_huge_fungus");
    public static final Identifier STACKED_FEATURE_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "simple_stacked_feature");
    public static final Identifier ADVANCED_BLOCK_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "advanced_block");
    public static final Identifier IVY_FEATURE_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "crimson_ivy");
    public static final Identifier NETHER_WALL_FLOWER_PATCH_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "nether_wall_flower_patch");
    public static final Identifier GASEOUS_GLOB_PATCH_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "gaseous_glob_patch");
    public static final Identifier ASH_PATCH_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "ash_patch");
    public static final Identifier FORTRESS_CAP_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "fortress_cap");
    public static final Identifier BIG_FORTRESS_CAP_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "big_fortress_cap");
    public static final Identifier WARPING_VINES_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "warping_vines");
    public static final Identifier VENT_ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "vent");

    public static final McddnHugeFungusFeature MCDDN_HUGE_FUNGUS_FEATURE = new McddnHugeFungusFeature(McddnHugeFungusFeatureConfig.CODEC);
    public static final SimpleStackedFeature SIMPLE_STACKED_FEATURE = new SimpleStackedFeature(SimpleRandomFeatureConfiguration.CODEC);
    public static final AdvancedBlockFeature ADVANCED_BLOCK_FEATURE = new AdvancedBlockFeature(AdvancedBlockFeatureConfig.CODEC);
    public static final CrimsonIvyFeature CRIMSON_IVY_FEATURE = new CrimsonIvyFeature(NoneFeatureConfiguration.CODEC);
    public static final NetherWallFlowerPatchFeature NETHER_WALL_FLOWER_PATCH_FEATURE = new NetherWallFlowerPatchFeature(RandomPatchConfiguration.CODEC);
    public static final GaseousGlobPatchFeature GASEOUS_GLOB_PATCH_FEATURE = new GaseousGlobPatchFeature(RandomPatchConfiguration.CODEC);
    public static final AshPatchFeature ASH_PATCH_FEATURE = new AshPatchFeature(RandomPatchConfiguration.CODEC);
    public static final FortressCapFeature FORTRESS_CAP_FEATURE = new FortressCapFeature(RotatableFeatureConfig.CODEC);
    public static final BigFortressCapFeature BIG_FORTRESS_CAP_FEATURE = new BigFortressCapFeature(RotatableFeatureConfig.CODEC);
    public static final WarpingVinesFeature WARPING_VINES_FEATURE = new WarpingVinesFeature(NoneFeatureConfiguration.CODEC);
    public static final VentFeature VENT_FEATURE = new VentFeature(VentFeatureConfig.CODEC);

    public static void init() {
        Registry.register(BuiltInRegistries.FEATURE, MCDDN_HUGE_FUNGUS_ID, MCDDN_HUGE_FUNGUS_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, STACKED_FEATURE_ID, SIMPLE_STACKED_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, ADVANCED_BLOCK_ID, ADVANCED_BLOCK_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, IVY_FEATURE_ID, CRIMSON_IVY_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, NETHER_WALL_FLOWER_PATCH_ID, NETHER_WALL_FLOWER_PATCH_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, GASEOUS_GLOB_PATCH_ID, GASEOUS_GLOB_PATCH_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, ASH_PATCH_ID, ASH_PATCH_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, FORTRESS_CAP_ID, FORTRESS_CAP_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, BIG_FORTRESS_CAP_ID, BIG_FORTRESS_CAP_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, WARPING_VINES_ID, WARPING_VINES_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, VENT_ID, VENT_FEATURE);

        Registry.register(BuiltInRegistries.STRUCTURE_POOL_ELEMENT, Identifier.fromNamespaceAndPath(Constants.MOD_ID, "advanced_feature_pool_element"), (StructurePoolElementType<AdvancedFeaturePoolElement>) () -> AdvancedFeaturePoolElement.CODEC);
    }
}