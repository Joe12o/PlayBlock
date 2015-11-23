package com.skcraft.playblock.ui.widget;

import com.skcraft.playblock.ui.Widget;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

@SideOnly(Side.CLIENT)
public class SimpleTextField extends GuiTextField implements Widget {

    public enum Type { STRING, INTEGER, FLOATING_POINT }

    private Type type;

    public SimpleTextField(int x, int y, int w, int h, int maxLength) {
        this(x, y, w, h, maxLength, Type.STRING);
    }

    public SimpleTextField(int x, int y, int w, int h, int maxLength, Type type) {
        super(Minecraft.getMinecraft().fontRenderer, x, y, w, h);
        this.type = type;
        setMaxStringLength(maxLength);
        setFocused(false);
    }

    @Override
    public void draw(int mouseX, int mouseY, float dt) {
        drawTextBox();
    }

    @Override
    public void keyTyped(char c, int keyCode) {
        String oldText = getText();
        switch(type) {
            case STRING:
                textboxKeyTyped(c, keyCode);
                break;
            case INTEGER:
                try {
                    textboxKeyTyped(c, keyCode);
                    Integer.parseInt(getText());
                } catch(NumberFormatException e) {
                    setText(getText().length() != 0 ? oldText : "");
                }
                break;
            case FLOATING_POINT:
                try {
                    textboxKeyTyped(c, keyCode);
                    Float.parseFloat(getText());
                } catch(NumberFormatException e) {
                    setText(getText().length() != 0 ? oldText : "");
                }
                break;
        }
    }

    @Override
    public void mouseClick(int x, int y, int buttonClicked) {
        super.mouseClicked(x, y, buttonClicked);
    }
}
