package com.skcraft.playblock.client;

import com.skcraft.playblock.SharedConfiguration;
import com.skcraft.playblock.SharedRuntime;
import com.skcraft.playblock.player.MediaManager;
import com.skcraft.playblock.ui.screen.GuiProjector;
import com.skcraft.playblock.projector.RenderProjector;
import com.skcraft.playblock.projector.TileEntityProjector;
import com.skcraft.playblock.queue.ExposedQueue;
import com.skcraft.playblock.ui.screen.GuiQueue;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Client-side initialization.
 */
public class ClientRuntime extends SharedRuntime {

    private MediaManager manager;
    private SharedConfiguration options;

    /**
     * Get the media manager.
     *
     * @return the media manager
     */
    public MediaManager getMediaManager() {
        return manager;
    }

    /**
     * Gets the client options.
     *
     * @return the client options.
     */
    public SharedConfiguration getClientOptions() {
        return options;
    }

    @Override
    public void load(FMLInitializationEvent event) {
        super.load(event);
        options = new SharedConfiguration("PlayBlockSettings.txt");
        manager = new MediaManager();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityProjector.class, new RenderProjector(manager));

        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        FMLCommonHandler.instance().bus().register(new KeyHandler());

        getClientOptions().save();
    }

    @Override
    public void serverStopping(FMLServerStoppingEvent event) {
        super.serverStopping(event);

        manager.releaseAll();
    }

    @Override
    public void showProjectorGui(EntityPlayer player, TileEntityProjector tileEntity) {
        FMLClientHandler.instance().displayGuiScreen(player, new GuiProjector(tileEntity));
    }

    @Override
    public void showRemoteGui(EntityPlayer player, ExposedQueue queuable) {
        FMLClientHandler.instance().displayGuiScreen(player, new GuiQueue(queuable));
    }

}
