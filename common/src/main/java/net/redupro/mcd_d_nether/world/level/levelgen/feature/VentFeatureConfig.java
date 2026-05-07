package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class VentFeatureConfig implements FeatureConfiguration {
    public static final Codec<VentFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("min_radius").forGetter(config -> config.minRadius),
                    Codec.INT.fieldOf("max_radius").forGetter(config -> config.maxRadius),
                    Codec.INT.fieldOf("max_length").forGetter(config -> config.maxLength),
                    Codec.INT.fieldOf("height").forGetter(config -> config.height),
                    Codec.INT.fieldOf("depth").forGetter(config -> config.depth),
                    BlockStateProvider.CODEC.fieldOf("fill").forGetter(config -> config.fill)
            ).apply(instance, VentFeatureConfig::new)
    );
    public final int minRadius;
    public final int maxRadius;
    public final int maxLength;
    public final int height;
    public final int depth;
    public final BlockStateProvider fill;
    public VentFeatureConfig(int minRadius, int maxRadius, int maxLength, int height, int depth, BlockStateProvider fill) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.maxLength = maxLength;
        this.height = height;
        this.depth = depth;
        this.fill = fill;
    }
}
