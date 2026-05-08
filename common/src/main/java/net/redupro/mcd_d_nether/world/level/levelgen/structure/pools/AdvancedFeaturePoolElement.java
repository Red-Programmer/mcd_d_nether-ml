package net.redupro.mcd_d_nether.world.level.levelgen.structure.pools;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.FeaturePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdvancedFeaturePoolElement extends FeaturePoolElement {
    public static final MapCodec<AdvancedFeaturePoolElement> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(PlacedFeature.CODEC.fieldOf("feature_north").forGetter(freeFeaturePoolElement -> freeFeaturePoolElement.feature_north), PlacedFeature.CODEC.fieldOf("feature_south").forGetter(freeFeaturePoolElement -> freeFeaturePoolElement.feature_south), PlacedFeature.CODEC.fieldOf("feature_east").forGetter(freeFeaturePoolElement -> freeFeaturePoolElement.feature_east), PlacedFeature.CODEC.fieldOf("feature_west").forGetter(freeFeaturePoolElement -> freeFeaturePoolElement.feature_west), projectionCodec())
                    .apply(instance, AdvancedFeaturePoolElement::new)
    );
    private Holder<PlacedFeature> feature_north;
    private Holder<PlacedFeature> feature_south;
    private Holder<PlacedFeature> feature_east;
    private Holder<PlacedFeature> feature_west;
    private @Nullable CompoundTag defaultJigsawNBT;

    protected AdvancedFeaturePoolElement(Holder<PlacedFeature> holder_north, Holder<PlacedFeature> holder_south, Holder<PlacedFeature> holder_east, Holder<PlacedFeature> holder_west, StructureTemplatePool.Projection projection) {
        super(holder_north, projection);
        this.feature_north = holder_north;
        this.feature_south = holder_south;
        this.feature_east = holder_east;
        this.feature_west = holder_west;
        this.defaultJigsawNBT = this.fillDefaultJigsawNBT();
    }
    private CompoundTag fillDefaultJigsawNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("name", "minecraft:wing");
        compoundTag.putString("final_state", "minecraft:air");
        compoundTag.putString("pool", "minecraft:empty");
        compoundTag.putString("target", "minecraft:empty");
        compoundTag.putString("joint", JigsawBlockEntity.JointType.ROLLABLE.getSerializedName());
        return compoundTag;
    }

    @Override
    public @NotNull List<StructureTemplate.StructureBlockInfo> getShuffledJigsawBlocks(
            StructureTemplateManager structureTemplateManager, BlockPos blockPos, Rotation rotation, RandomSource randomSource
    ) {
        List<StructureTemplate.StructureBlockInfo> list = Lists.<StructureTemplate.StructureBlockInfo>newArrayList();
        list.add(
                new StructureTemplate.StructureBlockInfo(
                        blockPos,
                        Blocks.JIGSAW.defaultBlockState().setValue(JigsawBlock.ORIENTATION, FrontAndTop.fromFrontAndTop(rotation.rotate(Direction.NORTH), Direction.UP)),
                        this.defaultJigsawNBT
                )
        );
        return list;
    }

    @Override
    public boolean place(
            StructureTemplateManager structureTemplateManager,
            WorldGenLevel worldGenLevel,
            StructureManager structureManager,
            ChunkGenerator chunkGenerator,
            BlockPos blockPos,
            BlockPos blockPos2,
            Rotation rotation,
            BoundingBox boundingBox,
            RandomSource randomSource,
            LiquidSettings liquidSettings,
            boolean bl
    ) {
        Holder<PlacedFeature> feature = switch (rotation) {
            case CLOCKWISE_90 -> this.feature_west;
            case CLOCKWISE_180 -> this.feature_north;
            case COUNTERCLOCKWISE_90 -> this.feature_east;
            default -> this.feature_south;
        };
        return feature.value().place(worldGenLevel, chunkGenerator, randomSource, blockPos);
    }
}
