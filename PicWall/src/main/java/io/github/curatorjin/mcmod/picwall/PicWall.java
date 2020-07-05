package io.github.curatorjin.mcmod.picwall;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import io.github.curatorjin.mcmod.picwall.commands.PaintWallCommand;

@Mod(modid = PicWall.MODID, version = PicWall.VERSION)
public class PicWall {
    public static final String MODID = "PicWall";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(PaintWallCommand.getInstance());
    }

}
