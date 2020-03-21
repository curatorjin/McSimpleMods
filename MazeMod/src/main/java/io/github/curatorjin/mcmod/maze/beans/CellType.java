package io.github.curatorjin.mcmod.maze.beans;

/**
 * 迷宫的格子类型
 *
 * @author Curatorjin
 * @version 1.0
 */
public enum CellType {
    /**
     * 地板 - 0
     */
    FLOOR(0),

    /**
     * 墙 - 1
     */
    WALL(1);

    private int code;

    public int getCode() {
        return code;
    }

    CellType(int code) {
        this.code = code;
    }
}
