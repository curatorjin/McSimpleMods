package io.github.curatorjin.mcmod.picwall.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 图形像素化处理类
 *
 * @author Curatorjin
 * @version 1.0
 */
public abstract class WallPainter {
    private static final int[][] COLOR_MAP = new int[][]{{233, 236, 236}, {240, 118, 19},
            {189, 68, 179}, {58, 175, 217}, {248, 198, 39}, {112, 185, 25}, {237, 141, 172},
            {62, 68, 71}, {142, 142, 134}, {21, 137, 145}, {121, 42, 172}, {53, 57, 157},
            {114, 71, 40}, {84, 109, 27}, {161, 39, 34}, {20, 21, 25}};

    public static int[][] getWall(String filePath) {
        Path imgPath = Paths.get(filePath);
        File file = imgPath.toFile();
        if (!file.exists()) {
            return null;
        }
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            int[][] resultMap = new int[width][height];
            int minx = bi.getMinX();
            int miny = bi.getMinY();
            System.out.println("width=" + width + ",height=" + height + ".");
            System.out.println("minx=" + minx + ",miniy=" + miny + ".");
            for (int i = minx; i < width; i++) {
                for (int j = miny; j < height; j++) {
                    int pixel = bi.getRGB(i, j);
                    int[] point = transferInt2RGB(pixel);
                    resultMap[i][j] = getClosestBlockIndex(point);
                }
            }
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int[] transferInt2RGB(int pixel) {
        int[] rgb = new int[3];
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);
        return rgb;
    }

    private static int getClosestBlockIndex(int[] input) {
        if (input.length != 3) {
            return 0;
        }
        double minDistance = Double.MAX_VALUE;
        int resultIndex = 0;
        for (int i = 0; i < COLOR_MAP.length; i++) {
            int[] point = COLOR_MAP[i];
            double distance = Math.pow(point[0] - input[0], 2) + Math.pow(point[1] - input[1], 2) +
                    Math.pow(point[2] - input[2], 2);
            if (distance < minDistance) {
                minDistance = distance;
                resultIndex = i;
            }
        }
        return resultIndex;
    }
}
