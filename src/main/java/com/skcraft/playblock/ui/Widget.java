package com.skcraft.playblock.ui;

public interface Widget {

    void draw(int mouseX, int mouseY, float dt);
    void mouseClick(int x, int y, int buttonClicked);
    void keyTyped(char c, int keyCode);

}
