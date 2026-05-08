package net.redupro.mcd_d_nether.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.redupro.mcd_d_nether.Constants;
import net.redupro.mcd_d_nether.world.level.levelgen.feature.*;

public class McddnFeatures {
    public static final McddnHugeFungusFeature MCDDN_HUGE_FUNGUS_FEATURE = register("mcddn_huge_fungus", new McddnHugeFungusFeature(McddnHugeFungusFeatureConfig.CODEC));
    public static final SimpleStackedFeature SIMPLE_STACKED_FEATURE = register("simple_stacked_feature", new SimpleStackedFeature(SimpleRandomFeatureConfiguration.CODEC));
    public static final AdvancedBlockFeature ADVANCED_BLOCK_FEATURE = register("advanced_block", new AdvancedBlockFeature(AdvancedBlockFeatureConfig.CODEC));
    public static final CrimsonIvyFeature CRIMSON_IVY_FEATURE = register("crimson_ivy", new CrimsonIvyFeature(NoneFeatureConfiguration.CODEC));
    public static final NetherWallFlowerFeature NETHER_WALL_FLOWER_FEATURE = register("nether_wall_flower", new NetherWallFlowerFeature(NoneFeatureConfiguration.CODEC));
    public static final GaseousGlobFeature GASEOUS_GLOB_FEATURE = register("gaseous_glob", new GaseousGlobFeature(NoneFeatureConfiguration.CODEC));
    public static final AshFeature ASH_FEATURE = register("ash", new AshFeature(NoneFeatureConfiguration.CODEC));
    public static final FortressCapFeature FORTRESS_CAP_FEATURE = register("fortress_cap", new FortressCapFeature(RotatableFeatureConfig.CODEC));
    public static final BigFortressCapFeature BIG_FORTRESS_CAP_FEATURE = register("big_fortress_cap", new BigFortressCapFeature(RotatableFeatureConfig.CODEC));
    public static final WarpingVinesFeature WARPING_VINES_FEATURE = register("warping_vines", new WarpingVinesFeature(NoneFeatureConfiguration.CODEC));
    public static final VentFeature VENT_FEATURE = register("vent", new VentFeature(VentFeatureConfig.CODEC));

    private static <V, T extends V> T register(String name, T feature) {
        return Registry.register(
                (Registry<V>) BuiltInRegistries.FEATURE,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, name),
                feature
        );
    }
    public static void init() {}
}
