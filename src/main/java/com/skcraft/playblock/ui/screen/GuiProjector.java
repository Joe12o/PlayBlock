package com.skcraft.playblock.ui.screen;

import com.skcraft.playblock.PlayBlock;
import com.skcraft.playblock.projector.TileEntityProjector;
import com.skcraft.playblock.ui.PlayBlockGui;
import com.skcraft.playblock.ui.widget.Label;
import com.skcraft.playblock.ui.widget.SimpleButton;
import com.skcraft.playblock.ui.widget.SimpleTextField;
import com.skcraft.playblock.util.StringUtils;
import com.skcraft.playblock.util.TextureRegion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

/**
 * The GUI for the projector.
 */
@SideOnly(Side.CLIENT)
public class GuiProjector extends PlayBlockGui {

    private TileEntityProjector projector;
    private SimpleTextField uriField;
    private SimpleTextField widthField, heightField;
    private SimpleTextField triggerField, fadeField;

    public GuiProjector(TileEntityProjector projector) {
        super(new TextureRegion("playblock:textures/gui/projector_bg.png", 247, 165));
        this.projector = projector;
    }

    @Override
    public void initGui() {
        super.initGui();

        addWidget(new SimpleButton(StringUtils.translate("gui.done"), bgLeft + 160, bgTop + 125, 80, 20, () -> {
            try {
                projector.getOptions().sendUpdate(uriField.getText(), Float.parseFloat(widthField.getText()),
                        Float.parseFloat(heightField.getText()), Float.parseFloat(triggerField.getText()),
                        Float.parseFloat(fadeField.getText()));
            } catch(NumberFormatException e) {
                PlayBlock.log(Level.WARN, "Failed to send projector settings update!");
            }
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
        }));

        addWidget(new SimpleButton("X", bgLeft + 220, bgTop + 14, 17, 20, () -> {
            uriField.setText("");
            uriField.setFocused(true);
        }));

        addWidget(new Label(StringUtils.translate("options.url"), bgLeft + 10, bgTop + 20));

        addWidget(uriField = new SimpleTextField(bgLeft + 60, bgTop + 17, 157, fontRendererObj.FONT_HEIGHT + 4, 250));

        addWidget(new Label(StringUtils.translate("options.screenSize"), bgLeft + 10, bgTop + 40));

        addWidget(widthField = new SimpleTextField(bgLeft + 60, bgTop + 37, 50, fontRendererObj.FONT_HEIGHT + 4, 8,
                SimpleTextField.Type.FLOATING_POINT));

        addWidget(new Label("x", bgLeft + 117, bgTop + 40));

        addWidget(heightField = new SimpleTextField(bgLeft + 130, bgTop + 37, 50, fontRendererObj.FONT_HEIGHT + 4, 8,
                SimpleTextField.Type.FLOATING_POINT));

        addWidget(new Label(StringUtils.translate("options.turnOn"), bgLeft + 10, bgTop + 60));

        addWidget(triggerField = new SimpleTextField(bgLeft + 60, bgTop + 57, 50, fontRendererObj.FONT_HEIGHT + 4, 8,
                SimpleTextField.Type.FLOATING_POINT));

        addWidget(new Label(StringUtils.translate("options.blocksAway"), bgLeft + 117, bgTop + 60));

        addWidget(new Label(StringUtils.translate("options.turnOff"), bgLeft + 10, bgTop + 80));

        addWidget(fadeField = new SimpleTextField(bgLeft + 60, bgTop + 77, 50, fontRendererObj.FONT_HEIGHT + 4, 8,
                SimpleTextField.Type.FLOATING_POINT));

        addWidget(new Label(StringUtils.translate("options.blocksAway"), bgLeft + 117, bgTop + 80));

        addWidget(new Label("TEST VERSION - skcraft.com", bgLeft + 10, bgTop + 132));

        uriField.setText(projector.getMediaPlayer().getUri());
        widthField.setText(Float.toString(projector.getMediaPlayer().getWidth()));
        heightField.setText(Float.toString(projector.getMediaPlayer().getHeight()));
        triggerField.setText(Float.toString(projector.getRange().getTriggerRange()));
        fadeField.setText(Float.toString(projector.getRange().getFadeRange()));
    }
}
