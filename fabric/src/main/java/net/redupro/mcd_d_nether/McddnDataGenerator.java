package net.redupro.mcd_d_nether;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.redupro.mcd_d_nether.datagen.McddnBlockTagProvider;
import net.redupro.mcd_d_nether.datagen.McddnLootTableProvider;
import net.redupro.mcd_d_nether.datagen.McddnRecipeProvider;

public class McddnDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(McddnLootTableProvider::new);
        pack.addProvider(McddnBlockTagProvider::new);
        pack.addProvider(McddnRecipeProvider::new);
	}
}
