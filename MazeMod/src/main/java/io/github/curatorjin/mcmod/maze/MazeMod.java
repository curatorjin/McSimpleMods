package io.github.curatorjin.mcmod.maze;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import io.github.curatorjin.mcmod.maze.commands.GenMazeCommand;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MazeMod.MODID, version = MazeMod.VERSION)
public class MazeMod {
    public static final String MODID = "Maze";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code

        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(GenMazeCommand.getInstance());
    }

}
