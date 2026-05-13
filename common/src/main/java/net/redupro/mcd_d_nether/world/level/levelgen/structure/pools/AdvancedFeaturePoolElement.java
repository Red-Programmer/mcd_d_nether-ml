package net.redupro.mcd_d_nether.world.level.levelgen.structure.pools;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
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
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.redupro.mcd_d_nether.registry.McddnStructurePoolElements;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class AdvancedFeaturePoolElement extends StructurePoolElement {
    public static final MapCodec<AdvancedFeaturePoolElement> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(PlacedFeature.CODEC.fieldOf("feature_north").forGetter(poolElement -> poolElement.feature_north), PlacedFeature.CODEC.fieldOf("feature_south").forGetter(poolElement -> poolElement.feature_south), PlacedFeature.CODEC.fieldOf("feature_east").forGetter(poolElement -> poolElement.feature_east), PlacedFeature.CODEC.fieldOf("feature_west").forGetter(poolElement -> poolElement.feature_west), projectionCodec())
                    .apply(instance, AdvancedFeaturePoolElement::new)
    );
    private static final Identifier DEFAULT_JIGSAW_NAME = Identifier.withDefaultNamespace("wing");
    private Holder<PlacedFeature> feature_north;
    private Holder<PlacedFeature> feature_south;
    private Holder<PlacedFeature> feature_east;
    private Holder<PlacedFeature> feature_west;
    private @Nullable CompoundTag defaultJigsawNBT;

    protected AdvancedFeaturePoolElement(Holder<PlacedFeature> holder_north, Holder<PlacedFeature> holder_south, Holder<PlacedFeature> holder_east, Holder<PlacedFeature> holder_west, StructureTemplatePool.Projection projection) {
        super(projection);
        this.feature_north = holder_north;
        this.feature_south = holder_south;
        this.feature_east = holder_east;
        this.feature_west = holder_west;
        this.defaultJigsawNBT = this.fillDefaultJigsawNBT();
    }
    private CompoundTag fillDefaultJigsawNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.store("name", Identifier.CODEC, DEFAULT_JIGSAW_NAME);
        compoundTag.putString("final_state", "minecraft:air");
        compoundTag.store("pool", JigsawBlockEntity.POOL_CODEC, Pools.EMPTY);
        compoundTag.store("target", Identifier.CODEC, JigsawBlockEntity.EMPTY_ID);
        compoundTag.store("joint", JigsawBlockEntity.JointType.CODEC, JigsawBlockEntity.JointType.ROLLABLE);
        return compoundTag;
    }

    @Override
    public Vec3i getSize(StructureTemplateManager structureTemplateManager, Rotation rotation) {
        return Vec3i.ZERO;
    }

    @Override
    public @NotNull List<StructureTemplate.JigsawBlockInfo> getShuffledJigsawBlocks(
            StructureTemplateManager structureTemplateManager, BlockPos blockPos, Rotation rotation, RandomSource randomSource
    ) {
        return List.of(
                StructureTemplate.JigsawBlockInfo.of(
                        new StructureTemplate.StructureBlockInfo(
                                blockPos,
                                Blocks.JIGSAW.defaultBlockState().setValue(JigsawBlock.ORIENTATION, FrontAndTop.fromFrontAndTop(rotation.rotate(Direction.NORTH), Direction.UP)),
                                this.defaultJigsawNBT
                        )
                )
        );
    }

    @Override
    public BoundingBox getBoundingBox(StructureTemplateManager structureTemplateManager, BlockPos blockPos, Rotation rotation) {
        Vec3i vec3i = this.getSize(structureTemplateManager, rotation);
        return new BoundingBox(
                blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos.getX() + vec3i.getX(), blockPos.getY() + vec3i.getY(), blockPos.getZ() + vec3i.getZ()
        );
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

    public StructurePoolElementType<?> getType() {
        return McddnStructurePoolElements.ADVANCED_FEATURE_POOL_ELEMENT;
    }

    public String toString() {
        return "Feature[" + String.valueOf(this.feature_north) + "]";
    }
}
