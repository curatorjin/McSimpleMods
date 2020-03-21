package io.github.curatorjin.mcmod.maze.beans;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 迷宫的格子
 *
 * @author Curatorjin
 * @version 1.0
 */
public class MazeCell {
    /**
     * X坐标
     */
    private int x;

    /**
     * Y坐标
     */
    private int y;

    private CellType type = CellType.FLOOR;

    public MazeCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public List<MazeCell> getNeighborCells() {
        MazeCell[] mazeCells = {
                new MazeCell(x + 1, y),
                new MazeCell(x - 1, y),
                new MazeCell(x, y + 1),
                new MazeCell(x, y - 1)
        };
        return new LinkedList<>(Arrays.asList(mazeCells));
    }

    public List<MazeCell> getNeighborFloors() {
        List<MazeCell> result = new LinkedList<>();
        if ((this.x + this.y) % 2 != 0) {
            if (this.x % 2 != 0) {
                result.add(new MazeCell(x + 1, y));
                result.add(new MazeCell(x - 1, y));
            } else {
                result.add(new MazeCell(x, y + 1));
                result.add(new MazeCell(x, y - 1));
            }
        }
        return result;
    }

    public boolean isCellInMap(int mapWidth, int mapHeight) {
        return this.x < mapWidth && this.x >= 0 && this.y >= 0 && this.y < mapHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeCell mazeCell = (MazeCell) o;
        return x == mazeCell.x &&
                y == mazeCell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
