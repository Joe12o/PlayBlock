package com.skcraft.playblock.ui.widget;

import com.skcraft.playblock.ui.Widget;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

@SideOnly(Side.CLIENT)
public class Label extends Gui implements Widget {

    public static final int DEFAULT_COLOR = 0xff999999;

    private String text;
    private int x, y;
    private int color;

    public Label(String text, int x, int y) {
        this(text, x, y, DEFAULT_COLOR);
    }

    public Label(String text, int x, int y, int color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw(int mouseX, int mouseY, float dt) {
        Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, color);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void mouseClick(int x, int y, int buttonClicked) {}

    @Override
    public void keyTyped(char c, int keyCode) {}
}
