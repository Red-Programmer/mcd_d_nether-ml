package net.redupro.mcd_d_nether;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.redupro.mcd_d_nether.registry.McddnBlocks;

public class McddnFabric implements ModInitializer {
    @Override
	public void onInitialize() {
        McddnCommon.init();
        addCreative();
        ResourceLoader.registerBuiltinPack(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "custom_fortress"), FabricLoader.getInstance().getModContainer(Constants.MOD_ID).orElseThrow(), Component.translatable("Custom Nether Fortress"), PackActivationType.DEFAULT_ENABLED);
    }

    private void addCreative() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            for (Block block: McddnBlocks.buildingBlocks) {
                fabricItemGroupEntries.accept(block);
            }
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(fabricItemGroupEntries -> {
            for (Block block: McddnBlocks.naturalBlocks) {
                fabricItemGroupEntries.accept(block);
            }
        });
    }
}