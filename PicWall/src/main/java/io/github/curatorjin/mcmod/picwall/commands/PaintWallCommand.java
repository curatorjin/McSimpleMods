package io.github.curatorjin.mcmod.picwall.commands;

import io.github.curatorjin.mcmod.picwall.utils.WallPainter;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * 生成迷宫的命令
 *
 * @author Curatorjin
 * @version 1.0
 */
public class PaintWallCommand extends CommandBase {
    /**
     * 唯一实例
     */
    private static PaintWallCommand instance;

    /**
     * 获取实例
     *
     * @return 唯一实例
     */
    public static PaintWallCommand getInstance() {
        if (instance == null) {
            instance = new PaintWallCommand();
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
        return "paintWall";
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
        return 0;
    }

    /**
     * 命令处理
     *
     * @param commandSender 命令发送者
     * @param args          命令参数
     */
    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {
        if (args.length != 1) {
            return;
        }
        World world = commandSender.getEntityWorld();
        if (commandSender instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) commandSender;
            System.out.println(playerMP.chunkCoordX);
            System.out.println(playerMP.chunkCoordY);
            System.out.println(playerMP.chunkCoordZ);
            int posX = MathHelper.floor_double(playerMP.posX);
            int posY = MathHelper.floor_double(playerMP.posY);
            int posZ = MathHelper.floor_double(playerMP.posZ);
            float rotationYaw = MathHelper.wrapAngleTo180_float(playerMP.rotationYaw);
            int[][] wall = WallPainter.getWall(args[0]);
            if (wall == null) {
                return;
            }
            if (rotationYaw >= -135 && rotationYaw < -45) {
                paint(world, wall, posX, posY - 2, posZ, 6);
            } else if (rotationYaw >= -45 && rotationYaw < 45) {
                paint(world, wall, posX, posY - 2, posZ, 8);
            } else if (rotationYaw >= 45 && rotationYaw < 135) {
                paint(world, wall, posX, posY - 2, posZ, 4);
            } else {
                paint(world, wall, posX, posY - 2, posZ, 2);
            }
        }
    }

    private void paint(World world, int[][] wall, int x, int y, int z, int direction) {
        switch (direction) {
            case 2:
                int startX = x - wall.length / 2;
                for (int imgX = 0; imgX < wall.length; imgX++) {
                    int[] imgPixelY = wall[imgX];
                    for (int imgY = 0; imgY < imgPixelY.length; imgY++) {
                        int meta = wall[imgX][imgY];
                        world.setBlock(startX + imgX, y + imgPixelY.length - imgY, z - 70,
                                Block.getBlockById(35), meta, 3);
                    }
                }
                break;
            case 4:
                int startZ = z + wall.length / 2;
                for (int imgX = 0; imgX < wall.length; imgX++) {
                    int[] imgPixelY = wall[imgX];
                    for (int imgY = 0; imgY < imgPixelY.length; imgY++) {
                        int meta = wall[imgX][imgY];
                        world.setBlock(x - 70, y + imgPixelY.length - imgY, startZ - imgX,
                                Block.getBlockById(35), meta, 3);
                    }
                }
                break;
            case 6:
                int startZ2 = z - wall.length / 2;
                for (int imgX = 0; imgX < wall.length; imgX++) {
                    int[] imgPixelY = wall[imgX];
                    for (int imgY = 0; imgY < imgPixelY.length; imgY++) {
                        int meta = wall[imgX][imgY];
                        world.setBlock(x + 70, y + imgPixelY.length - imgY, startZ2 + imgX,
                                Block.getBlockById(35), meta, 3);
                    }
                }
                break;
            case 8:
                int startX2 = x + wall.length / 2;
                for (int imgX = 0; imgX < wall.length; imgX++) {
                    int[] imgPixelY = wall[imgX];
                    for (int imgY = 0; imgY < imgPixelY.length; imgY++) {
                        int meta = wall[imgX][imgY];
                        world.setBlock(startX2 - imgX, y + imgPixelY.length - imgY, z + 70,
                                Block.getBlockById(35), meta, 3);
                    }
                }
                break;
            default:
                break;
        }
    }
}
