package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class AdvancedBlockFeatureConfig implements FeatureConfiguration {
    public static final Codec<AdvancedBlockFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                BlockState.CODEC.fieldOf("quad_state").forGetter(config -> config.quadState)
            ).apply(instance, AdvancedBlockFeatureConfig::new)
    );
    public final BlockState quadState;

    public AdvancedBlockFeatureConfig(BlockState quadState) {
        this.quadState = quadState;
    }
}
