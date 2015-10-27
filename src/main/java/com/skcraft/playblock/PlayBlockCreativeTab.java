package com.skcraft.playblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PlayBlockCreativeTab extends CreativeTabs {

    public static final PlayBlockCreativeTab tab = new PlayBlockCreativeTab();

    public PlayBlockCreativeTab() {
        super("tabPlayBlock");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return SharedRuntime.itemRemote;
    }

}
