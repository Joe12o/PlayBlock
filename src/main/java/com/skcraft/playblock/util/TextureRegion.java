package com.skcraft.playblock.util;

import net.minecraft.util.ResourceLocation;

public class TextureRegion {

    private ResourceLocation texture;
    private int x;
    private int y;
    private int w;
    private int h;

    public TextureRegion(String path, int x, int y) {
        this(new ResourceLocation(path), x, y);
    }

    public TextureRegion(String path, int x, int y, int w, int h) {
        this(new ResourceLocation(path), x, y, w, h);
    }

    public TextureRegion(ResourceLocation texture, int w, int h) {
        this(texture, 0, 0, w, h);
    }

    public TextureRegion(ResourceLocation texture, int x, int y, int w, int h) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }
}
