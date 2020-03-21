package io.github.curatorjin.mcmod.maze.utils;

import io.github.curatorjin.mcmod.maze.beans.CellType;
import io.github.curatorjin.mcmod.maze.beans.MazeCell;

import java.util.*;
import java.util.function.Consumer;

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
        // 参数修正
        if (width % 2 == 0) {
            width += 1;
        }
        if (height % 2 == 0) {
            height += 1;
        }
        List<MazeCell> mazeMap = initMazeMap(width, height);
        Set<MazeCell> walls = genMaze(mazeMap, width, height);
        return convertMazeToArray(walls, width, height);
    }

    /**
     * 生成迷宫
     *
     * @param mazeMap 迷宫格子
     * @return 生成后的迷宫中的墙
     */
    private static Set<MazeCell> genMaze(List<MazeCell> mazeMap, int width, int height) {
        Random random = new Random();
        Set<MazeCell> visited = new HashSet<>();
        Set<MazeCell> restWalls = new HashSet<>();
        Iterator<MazeCell> devideIterator = mazeMap.iterator();
        devideIterator.forEachRemaining(mazeCell -> {
            if (mazeCell.getType() == CellType.WALL) {
                restWalls.add(mazeCell);
            }
        });
        MazeCell startPoint = new MazeCell(0, 0);
        visited.add(startPoint);
        List<MazeCell> processing = new LinkedList<>(getNeighborWalls(restWalls, startPoint));
        while (!processing.isEmpty()) {
            MazeCell cell = processing.remove(random.nextInt(processing.size()));
            for (MazeCell floor : cell.getNeighborFloors()) {
                if (!visited.contains(floor) && floor.isCellInMap(width, height)) {
                    visited.add(floor);
                    restWalls.remove(cell);
                    processing.addAll(getNeighborWalls(restWalls, floor));
                    break;
                }
            }
        }
        return restWalls;
    }

    /**
     * 获取相邻的墙
     *
     * @param knownWalls 已存在的所有墙
     * @param cell       需判断的格子
     * @return 相邻的墙集合
     */
    private static List<MazeCell> getNeighborWalls(Set<MazeCell> knownWalls, MazeCell cell) {
        List<MazeCell> result = new LinkedList<>();
        for (MazeCell c : cell.getNeighborCells()) {
            if (knownWalls.contains(c)) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * 地图初始化
     *
     * @param width  预计的地图宽度
     * @param height 预计的地图高度
     * @return 地图格子集合
     */
    private static List<MazeCell> initMazeMap(int width, int height) {
        List<MazeCell> result = new LinkedList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                MazeCell cell = new MazeCell(x, y);
                if (x % 2 != 0 || y % 2 != 0) {
                    cell.setType(CellType.WALL);
                }
                result.add(cell);
            }
        }
        return result;
    }

    /**
     * 将迷宫格子集合转化为数组
     *
     * @param walls  迷宫中的墙
     * @param width  地图宽
     * @param height 地图高
     * @return 迷宫数组
     */
    private static boolean[][] convertMazeToArray(Set<MazeCell> walls, int width, int height) {
        boolean[][] result = new boolean[width][height];
        for (MazeCell cell : walls) {
            result[cell.getX()][cell.getY()] = true;
        }
        return result;
    }
}
