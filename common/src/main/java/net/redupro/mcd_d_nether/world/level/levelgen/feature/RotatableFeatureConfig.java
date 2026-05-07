package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class RotatableFeatureConfig implements FeatureConfiguration {
    public static final Codec<RotatableFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(Direction.CODEC.fieldOf("rotation").forGetter(config -> config.direction)
            ).apply(instance, RotatableFeatureConfig::new)
    );
    public final Direction direction;

    public RotatableFeatureConfig(Direction direction) {
        this.direction = direction;
    }
}
