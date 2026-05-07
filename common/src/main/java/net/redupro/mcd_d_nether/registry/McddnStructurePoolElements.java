package net.redupro.mcd_d_nether.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.redupro.mcd_d_nether.Constants;
import net.redupro.mcd_d_nether.world.level.levelgen.structure.pools.AdvancedFeaturePoolElement;

public class McddnStructurePoolElements {
    public static final StructurePoolElementType<AdvancedFeaturePoolElement> ADVANCED_FEATURE_POOL_ELEMENT = register("advanced_feature_pool_element", (StructurePoolElementType<AdvancedFeaturePoolElement>) () -> AdvancedFeaturePoolElement.CODEC);

    private static <V, T extends V> T register(String name, T feature) {
        return Registry.register(
                (Registry<V>) BuiltInRegistries.STRUCTURE_POOL_ELEMENT,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, name),
                feature
        );
    }
    public static void init() {}
}
