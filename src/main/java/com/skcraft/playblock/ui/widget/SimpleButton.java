package com.skcraft.playblock.ui.widget;

import com.skcraft.playblock.ui.Widget;
import com.skcraft.playblock.util.TextureRegion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class SimpleButton extends Gui implements Widget {

    private static final TextureRegion DEFAULT_TEXTURE = new TextureRegion(new ResourceLocation("textures/gui/widgets.png"), 0, 66, 200, 20);
    private static final TextureRegion DEFAULT_ACTIVE_TEXTURE = new TextureRegion(new ResourceLocation("textures/gui/widgets.png"), 0, 86, 200, 20);

    private TextureRegion texture, activeTexture;
    private Runnable action;
    private String text;
    private int x, y, w, h;

    public SimpleButton(String text, int x, int y, int w, int h, Runnable action) {
        this(text, x, y, w, h, action, DEFAULT_TEXTURE);
    }

    public SimpleButton(String text, int x, int y, int w, int h, Runnable action, TextureRegion texture) {
        this(text, x, y, w, h, action, texture, DEFAULT_ACTIVE_TEXTURE);
    }

    public SimpleButton(String text, int x, int y, int w, int h, Runnable action, TextureRegion texture, TextureRegion activeTexture) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.action = action;
        this.texture = texture;
        this.activeTexture = activeTexture;
    }

    @Override
    public void draw(int mouseX, int mouseY, float dt) {
        Minecraft mc = Minecraft.getMinecraft();
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        TextureRegion currentTexture;
        int textColor;
        if(isWithinBounds(mouseX, mouseY) && activeTexture != null) {
            currentTexture = activeTexture;
            textColor = 0xffffffa0;
        } else {
            currentTexture = texture;
            textColor = 0xffE0E0E0;
        }

        mc.getTextureManager().bindTexture(currentTexture.getTexture());
        drawTexturedModalRect(x, y, currentTexture.getX(), currentTexture.getY(), 6, currentTexture.getHeight());
        drawTexturedModalRect(x + w - 6, y, currentTexture.getX() + currentTexture.getWidth() - 6, currentTexture.getY(), 6, currentTexture.getHeight());

        int remainingSpace = w - 12;
        int currX = 6;
        while(remainingSpace > 0) {
            int fillAmount = Math.min(currentTexture.getWidth(), remainingSpace);
            drawTexturedModalRect(x + currX, y, currentTexture.getX() + 6, currentTexture.getY(), fillAmount, currentTexture.getHeight());
            currX += fillAmount;
            remainingSpace -= fillAmount;
        }

        mc.fontRenderer.drawString(text, x + w / 2 - mc.fontRenderer.getStringWidth(text) / 2, y + h / 2 - mc.fontRenderer.FONT_HEIGHT / 2, textColor, true);
    }

    private boolean isWithinBounds(int pX, int pY) {
        return pX >= x && pX <= x + w && pY >= y && pY <= y + h;
    }

    @Override
    public void mouseClick(int x, int y, int buttonClicked) {
        if(isWithinBounds(x, y) && buttonClicked == 0) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            action.run();
        }
    }

    @Override
    public void keyTyped(char c, int keyCode) {}
}
