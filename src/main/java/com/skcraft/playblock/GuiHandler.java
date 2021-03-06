package com.skcraft.playblock;

import com.skcraft.playblock.client.GuiOptions;
import com.skcraft.playblock.projector.GuiProjector;
import com.skcraft.playblock.projector.TileEntityProjector;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int PROJECTOR = 0;
    public static final int OPTIONS = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case PROJECTOR:
                TileEntity tile = world.getTileEntity(x, y, z);
                if (tile instanceof TileEntityProjector) {
                    return new GuiProjector((TileEntityProjector) tile);
                }
                break;
            case OPTIONS:
                return new GuiOptions(PlayBlock.getClientRuntime().getMediaManager());
        }
        return null;
    }

}
