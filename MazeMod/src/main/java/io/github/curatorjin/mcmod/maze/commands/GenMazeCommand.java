package io.github.curatorjin.mcmod.maze.commands;

import io.github.curatorjin.mcmod.maze.utils.MazeGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

/**
 * 生成迷宫的命令
 *
 * @author Curatorjin
 * @version 1.0
 */
public class GenMazeCommand extends CommandBase {
    /**
     * 唯一实例
     */
    private static GenMazeCommand instance;

    /**
     * 获取实例
     *
     * @return 唯一实例
     */
    public static GenMazeCommand getInstance() {
        if (instance == null) {
            instance = new GenMazeCommand();
        }
        return instance;
    }

    /**
     * 获取命令名
     *
     * @return 命令名
     */
    @Override
    public String getCommandName() {
        return "genMaze";
    }

    /**
     * 获取命令用法
     *
     * @param sender 发送者
     * @return 命令用法
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // 0 代表任何人都能用
    }

    /**
     * 命令处理
     *
     * @param commandSender 命令发送者
     * @param args          命令参数
     */
    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {
        World world = commandSender.getEntityWorld();
        boolean[][] map = MazeGenerator.generateMaze(50, 50);
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y]) {
                    world.setBlock(x, 4, y, Block.getBlockById(1));
                    world.setBlock(x, 5, y, Block.getBlockById(1));
                } else {
                    world.setBlock(x, 4, y, Block.getBlockById(0));
                    world.setBlock(x, 5, y, Block.getBlockById(0));
                }
                world.setBlock(x, 6, y, Block.getBlockById(89));
                world.setBlock(x, 7, y, Block.getBlockById(1));
            }
        }
        for (int i = -1; i < 52; i++) {
            for (int j = 4; j < 8; j++) {
                world.setBlock(-1, j, i, Block.getBlockById(1));
                world.setBlock(i, j, -1, Block.getBlockById(1));
                world.setBlock(i, j, 51, Block.getBlockById(1));
                world.setBlock(51, j, i, Block.getBlockById(1));
            }
        }
        world.setBlock(-1, 5, 0, Block.getBlockById(0));
        world.setBlock(-1, 4, 0, Block.getBlockById(0));
        world.setBlock(51, 4, 50, Block.getBlockById(0));
        world.setBlock(51, 5, 50, Block.getBlockById(0));
    }
}
