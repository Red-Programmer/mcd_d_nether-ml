package net.redupro.mcd_d_nether.block.enums;

import net.minecraft.util.StringRepresentable;

public enum IvyPart implements StringRepresentable {
    TIP("tip"),
    ROOT("root"),
    STEM("stem"),
    RIGHT("right"),
    LEFT("left"),
    RIGHTB("rightb"),
    LEFTB("leftb");

    private final String name;

    private IvyPart(final String name) {
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
