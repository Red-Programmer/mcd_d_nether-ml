package net.redupro.mcd_d_nether;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.redupro.mcd_d_nether.registry.McddnBlocks;

@Mod(Constants.MOD_ID)
public class McddnNeo {
    public McddnNeo(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::initRegistries);
        modEventBus.addListener(this::addPacks);
        modEventBus.addListener(this::addCreative);
    }

    private void initRegistries(RegisterEvent event) {
        McddnCommon.init();
    }
    private void addPacks(AddPackFindersEvent event) {
        event.addPackFinders(Identifier.parse("mcd_d_nether:resourcepacks/custom_fortress"), PackType.SERVER_DATA, Component.translatable("Custom Nether Fortress"), PackSource.BUILT_IN, false, Pack.Position.TOP);
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            for (Block block: McddnBlocks.buildingBlocks) {
                event.accept(block);
            }
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            for (Block block: McddnBlocks.naturalBlocks) {
                event.accept(block);
            }
        }
    }
}