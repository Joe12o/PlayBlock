package com.skcraft.playblock.ui;

import com.skcraft.playblock.util.TextureRegion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class PlayBlockGui extends GuiScreen {

    private List<Widget> widgets = new ArrayList<>();

    @Nullable protected TextureRegion background;
    protected int bgLeft = 0, bgTop = 0;

    public PlayBlockGui() {
        this(null);
    }

    public PlayBlockGui(@Nullable TextureRegion bg) {
        background = bg;
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        widgets.clear();
        if (background != null) {
            bgLeft = (width - background.getWidth()) / 2;
            bgTop = (height - background.getHeight()) / 2;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float dt) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        if(background != null) {
            bgLeft = (width - background.getWidth()) / 2;
            bgTop = (height - background.getHeight()) / 2;
            mc.getTextureManager().bindTexture(background.getTexture());
            drawTexturedModalRect(bgLeft, bgTop, background.getX(), background.getY(), background.getWidth(), background.getHeight());
        }

        widgets.forEach((w) -> w.draw(mouseX, mouseY, dt));
        super.drawScreen(mouseX, mouseY, dt);
    }

    @Override
    public void mouseClicked(int x, int y, int buttonClicked) {
        super.mouseClicked(x, y, buttonClicked);
        for(Widget w : widgets) {
            w.mouseClick(x, y, buttonClicked);
        }
    }

    @Override
    public void keyTyped(char c, int keyCode) {
        super.keyTyped(c, keyCode);
        for(Widget w : widgets) {
            w.keyTyped(c, keyCode);
        }
    }

    protected void addWidget(Widget widget) {
        if(widget != null) {
            widgets.add(widget);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
}
