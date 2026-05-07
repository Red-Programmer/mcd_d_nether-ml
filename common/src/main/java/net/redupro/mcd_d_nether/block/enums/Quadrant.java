package net.redupro.mcd_d_nether.block.enums;

import net.minecraft.util.StringRepresentable;

public enum Quadrant implements StringRepresentable {
    QUAD_1("quad_1"),
    QUAD_2("quad_2"),
    QUAD_3("quad_3"),
    QUAD_4("quad_4");

    private final String name;

    private Quadrant(final String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
