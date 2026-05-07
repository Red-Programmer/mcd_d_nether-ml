package net.redupro.mcd_d_nether.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.WeepingVinesFeature;
import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.block.custom.WartFluffBlock;

public class McddnHugeFungusFeature extends Feature<McddnHugeFungusFeatureConfig> {
	private static final float field_31507 = 0.06F;

	public McddnHugeFungusFeature(Codec<McddnHugeFungusFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<McddnHugeFungusFeatureConfig> context) {
		WorldGenLevel structureWorldAccess = context.level();
		BlockPos blockPos = context.origin();
		RandomSource random = context.random();
		ChunkGenerator chunkGenerator = context.chunkGenerator();
		McddnHugeFungusFeatureConfig mcddnHugeFungusFeatureConfig = context.config();
		Block block = mcddnHugeFungusFeatureConfig.validBaseBlock.getBlock();
		BlockPos blockPos2 = null;
		BlockState blockState = structureWorldAccess.getBlockState(blockPos.below());
		if (blockState.is(block)) {
			blockPos2 = blockPos;
		}

		if (blockPos2 == null) {
			return false;
		} else {
			int i = Mth.nextInt(random, 4, 13);
			if (random.nextInt(12) == 0) {
				i *= 2;
			}

			if (!mcddnHugeFungusFeatureConfig.planted) {
				int j = chunkGenerator.getGenDepth();
				if (blockPos2.getY() + i + 1 >= j) {
					return false;
				}
			}

			boolean bl = !mcddnHugeFungusFeatureConfig.planted && random.nextFloat() < 0.06F;
			structureWorldAccess.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_INVISIBLE);
			this.generateStem(structureWorldAccess, random, mcddnHugeFungusFeatureConfig, blockPos2, i, bl);
			this.generateHat(structureWorldAccess, random, mcddnHugeFungusFeatureConfig, blockPos2, i, bl);
			return true;
		}
	}

	private static boolean isReplaceable(WorldGenLevel world, BlockPos pos, McddnHugeFungusFeatureConfig config, boolean checkConfig) {
		if (world.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::canBeReplaced)) {
			return true;
		} else {
			return checkConfig && config.replaceableBlocks.test(world, pos);
		}
	}

	private void generateStem(WorldGenLevel world, RandomSource random, McddnHugeFungusFeatureConfig config, BlockPos pos, int stemHeight, boolean thickStem) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		BlockState[] blockState = {config.stemStateLayer1, config.stemStateLayer2, config.stemStateLayer3, config.stemState};
		int i = thickStem ? 1 : 0;
		int m;

		for (int j = -i; j <= i; j++) {
			for (int k = -i; k <= i; k++) {
				boolean bl = thickStem && Mth.abs(j) == i && Mth.abs(k) == i;

				for (int l = 0; l < stemHeight; l++) {
					m = Math.min(l, 3);
					mutable.setWithOffset(pos, j, l, k);
					if (isReplaceable(world, mutable, config, true)) {
						if (config.planted) {
							if (!world.getBlockState(mutable.below()).isAir()) {
								world.destroyBlock(mutable, true);
							}
							world.setBlock(mutable, blockState[m], Block.UPDATE_ALL);
						} else if (bl) {
							if (random.nextFloat() < 0.1F) {
									this.setBlock(world, mutable, blockState[m]);
							}
						} else {
							this.setBlock(world, mutable, blockState[m]);
						}
					}
				}
			}
		}
	}

	private void generateHat(WorldGenLevel world, RandomSource random, McddnHugeFungusFeatureConfig config, BlockPos pos, int hatHeight, boolean thickStem) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		boolean bl = config.hatState.is(Blocks.NETHER_WART_BLOCK);
		int i = Math.min(random.nextInt(1 + hatHeight / 3) + 5, hatHeight);
		int j = hatHeight - i;

		for (int k = j; k <= hatHeight; k++) {
			int l = k < hatHeight - random.nextInt(3) ? 2 : 1;
			if (i > 8 && k < j + 4) {
				l = 3;
			}

			if (thickStem) {
				l++;
			}

			for (int m = -l; m <= l; m++) {
				for (int n = -l; n <= l; n++) {
					boolean bl2 = m == -l || m == l;
					boolean bl3 = n == -l || n == l;
					boolean bl4 = !bl2 && !bl3 && k != hatHeight;
					boolean bl5 = bl2 && bl3;
					boolean bl6 = k < j + 3;
					mutable.setWithOffset(pos, m, k, n);
					if (isReplaceable(world, mutable, config, false)) {
						if (config.planted && !world.getBlockState(mutable.below()).isAir()) {
							world.destroyBlock(mutable, true);
						}

						if (bl6) {
							if (!bl4) {
								this.placeWithOptionalVines(world, random, mutable, config.hatState, bl);
							}
						} else if (bl4) {
							this.placeHatBlock(world, random, config, mutable, 0.1F, 0.2F, bl ? 0.1F : 0.0F);
						} else if (bl5) {
							this.placeHatBlock(world, random, config, mutable, 0.01F, 0.7F, bl ? 0.083F : 0.0F);
						} else {
							this.placeHatBlock(world, random, config, mutable, 5.0E-4F, 0.98F, bl ? 0.07F : 0.0F);
						}
					}
				}
			}
		}
		generateFluff(world, random, config, pos, hatHeight, j);
	}

	private void placeHatBlock(
			LevelAccessor world, RandomSource random, McddnHugeFungusFeatureConfig config, BlockPos.MutableBlockPos pos, float decorationChance, float generationChance, float vineChance
	) {
		if (random.nextFloat() < decorationChance) {
			this.setBlock(world, pos, config.decorationState);
		} else if (random.nextFloat() < generationChance) {
			this.setBlock(world, pos, config.hatState);
			if (random.nextFloat() < vineChance) {
				generateVines(pos, world, random);
			}
		}
	}

	private void generateFluff(WorldGenLevel world, RandomSource random, McddnHugeFungusFeatureConfig config, BlockPos pos, int endHeight, int startHeight) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		boolean fl1 = config.hatState.is(Blocks.WARPED_WART_BLOCK);
		boolean fl2 = config.hatState.is(Blocks.NETHER_WART_BLOCK);
		Block fluff = McddnBlocks.WARPED_WART_FLUFF;
		float weight = 0.2F;
		if (fl1 || fl2) {
			if (fl2) {
				fluff = McddnBlocks.NETHER_WART_FLUFF;
				weight = 0.1F;
			}
			for (int x = -3; x < 4; x++) {
				for (int y = startHeight; y <= endHeight; y++) {
					for (int z = -3; z < 4; z++) {
						mutable.setWithOffset(pos, x, y, z);
						boolean bl = world.getBlockState(mutable.above()).is(config.hatState.getBlock());
						if (world.getBlockState(mutable).is(config.hatState.getBlock())) {
							if (random.nextFloat() < weight) {
								if (world.getBlockState(mutable.north()).isAir() && world.getBlockState(mutable.north().below()).isAir() && z < 0) {
									this.setBlock(world, mutable.north(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.SOUTH).setValue(WartFluffBlock.HANGING, bl).setValue(WartFluffBlock.HALF, DoubleBlockHalf.UPPER));
									this.setBlock(world, mutable.north().below(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.SOUTH).setValue(WartFluffBlock.HALF, DoubleBlockHalf.LOWER));
								} else if (world.getBlockState(mutable.east()).isAir() && world.getBlockState(mutable.east().below()).isAir() && x > 0) {
									this.setBlock(world, mutable.east(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.WEST).setValue(WartFluffBlock.HANGING, bl).setValue(WartFluffBlock.HALF, DoubleBlockHalf.UPPER));
									this.setBlock(world, mutable.east().below(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.WEST).setValue(WartFluffBlock.HALF, DoubleBlockHalf.LOWER));
								} else if (world.getBlockState(mutable.south()).isAir() && world.getBlockState(mutable.south().below()).isAir() && z > 0) {
									this.setBlock(world, mutable.south(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.NORTH).setValue(WartFluffBlock.HANGING, bl).setValue(WartFluffBlock.HALF, DoubleBlockHalf.UPPER));
									this.setBlock(world, mutable.south().below(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.NORTH).setValue(WartFluffBlock.HALF, DoubleBlockHalf.LOWER));
								} else if (world.getBlockState(mutable.west()).isAir() && world.getBlockState(mutable.west().below()).isAir() && x < 0) {
									this.setBlock(world, mutable.west(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.EAST).setValue(WartFluffBlock.HANGING, bl).setValue(WartFluffBlock.HALF, DoubleBlockHalf.UPPER));
									this.setBlock(world, mutable.west().below(), fluff.defaultBlockState().setValue(WartFluffBlock.FACING, Direction.EAST).setValue(WartFluffBlock.HALF, DoubleBlockHalf.LOWER));
								}
							}
							if (fl1) {
								if (world.getBlockState(mutable.above()).isAir() && random.nextIntBetweenInclusive(1, 4) == 1) {
									this.setBlock(world, mutable.above(), Blocks.WARPED_ROOTS.defaultBlockState());
								}
								if (world.getBlockState(mutable.below()).isAir() || world.getBlockState(mutable.below()).is(Blocks.WARPED_ROOTS)) {
									this.setBlock(world, mutable.below(), McddnBlocks.WARPED_WART_HANGING.defaultBlockState());
								}
							}
						}
					}
				}
			}
		}
	}

	private void placeWithOptionalVines(LevelAccessor world, RandomSource random, BlockPos pos, BlockState state, boolean vines) {
		if (world.getBlockState(pos.below()).is(state.getBlock())) {
			this.setBlock(world, pos, state);
		} else if (random.nextFloat() < 0.15) {
			this.setBlock(world, pos, state);
			if (vines && random.nextInt(11) == 0) {
				generateVines(pos, world, random);
			}
		}
	}

	private static void generateVines(BlockPos pos, LevelAccessor world, RandomSource random) {
		BlockPos.MutableBlockPos mutable = pos.mutable().move(Direction.DOWN);
		if (world.isEmptyBlock(mutable)) {
			int i = Mth.nextInt(random, 1, 5);
			if (random.nextInt(7) == 0) {
				i *= 2;
			}

			int j = 23;
			int k = 25;
			WeepingVinesFeature.placeWeepingVinesColumn(world, random, mutable, i, 23, 25);
		}
	}
}
