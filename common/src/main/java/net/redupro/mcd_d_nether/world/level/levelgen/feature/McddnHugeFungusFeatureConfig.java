package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class McddnHugeFungusFeatureConfig implements FeatureConfiguration {
	public static final Codec<McddnHugeFungusFeatureConfig> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
				BlockState.CODEC.fieldOf("valid_base_block").forGetter(config -> config.validBaseBlock),
				BlockState.CODEC.fieldOf("stem_state").forGetter(config -> config.stemState),
				BlockState.CODEC.fieldOf("hat_state").forGetter(config -> config.hatState),
				BlockState.CODEC.fieldOf("decor_state").forGetter(config -> config.decorationState),
				BlockPredicate.CODEC.fieldOf("replaceable_blocks").forGetter(config -> config.replaceableBlocks),
				Codec.BOOL.fieldOf("planted").orElse(false).forGetter(config -> config.planted),
				BlockState.CODEC.fieldOf("stem_state_layer_1").forGetter(config -> config.stemStateLayer1),
				BlockState.CODEC.fieldOf("stem_state_layer_2").forGetter(config -> config.stemStateLayer2),
				BlockState.CODEC.fieldOf("stem_state_layer_3").forGetter(config -> config.stemStateLayer3)
			)
			.apply(instance, McddnHugeFungusFeatureConfig::new)
	);
	public final BlockState validBaseBlock;
	public final BlockState stemState;
	public final BlockState hatState;
	public final BlockState decorationState;
	public final BlockPredicate replaceableBlocks;
	public final boolean planted;
	public final BlockState stemStateLayer1;
	public final BlockState stemStateLayer2;
	public final BlockState stemStateLayer3;

	public McddnHugeFungusFeatureConfig(
		BlockState validBaseBlock, BlockState stemState, BlockState hatState, BlockState decorationState, BlockPredicate replaceableBlocks, boolean planted, BlockState stemStateLayer1, BlockState stemStateLayer2, BlockState stemStateLayer3
	) {
		this.validBaseBlock = validBaseBlock;
		this.stemState = stemState;
		this.hatState = hatState;
		this.decorationState = decorationState;
		this.replaceableBlocks = replaceableBlocks;
		this.planted = planted;
		this.stemStateLayer1 = stemStateLayer1;
		this.stemStateLayer2 = stemStateLayer2;
		this.stemStateLayer3 = stemStateLayer3;
	}
}
