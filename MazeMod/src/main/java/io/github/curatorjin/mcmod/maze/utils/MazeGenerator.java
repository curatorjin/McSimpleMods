package io.github.curatorjin.mcmod.maze.utils;

/**
 * 迷宫生成器
 * author: curatorjin
 */
public abstract class MazeGenerator {
    /**
     * 生成迷宫方法
     *
     * @param width  迷宫的宽度
     * @param height 迷宫的高度
     * @return 迷宫数组
     */
    public static boolean[][] generateMaze(int width, int height) {
        if (width % 2 == 0) {
            width += 1;
        }
        if (height % 2 == 0) {
            height += 1;
        }
        boolean[][] result = new boolean[width][height];
        return result;
    }
}
