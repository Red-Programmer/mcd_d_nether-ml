package net.redupro.mcd_d_nether;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.redupro.mcd_d_nether.registry.McddnBlocks;

public class McddnFabric implements ModInitializer {
    @Override
	public void onInitialize() {
        McddnCommon.init();
        addCreative();
        ResourceManagerHelper.registerBuiltinResourcePack(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "custom_fortress"), FabricLoader.getInstance().getModContainer(Constants.MOD_ID).orElseThrow(), Component.translatable("Custom Nether Fortress"), ResourcePackActivationType.DEFAULT_ENABLED);
    }

    private void addCreative() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            for (Block block: McddnBlocks.buildingBlocks) {
                fabricItemGroupEntries.accept(block);
            }
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(fabricItemGroupEntries -> {
            for (Block block: McddnBlocks.naturalBlocks) {
                fabricItemGroupEntries.accept(block);
            }
        });
    }
}