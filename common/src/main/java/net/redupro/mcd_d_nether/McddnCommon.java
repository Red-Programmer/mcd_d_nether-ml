package net.redupro.mcd_d_nether;

import net.redupro.mcd_d_nether.registry.McddnBlocks;
import net.redupro.mcd_d_nether.registry.McddnFeatures;
import net.redupro.mcd_d_nether.registry.McddnStructurePoolElements;

public class McddnCommon {
    public static void init() {
        McddnBlocks.init();
        McddnFeatures.init();
        McddnStructurePoolElements.init();
    }
}