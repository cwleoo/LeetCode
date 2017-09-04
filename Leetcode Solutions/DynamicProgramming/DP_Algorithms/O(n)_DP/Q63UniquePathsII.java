/**
 * Follow up for "Unique Paths":
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 */

import java.util.*;

public class Q63UniquePathsII {
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int height = obstacleGrid.length;
        int width = obstacleGrid[0].length;
        int[][] cache = new int[height][width];
        for(int i = 0; i < height; i++) {
            if(obstacleGrid[i][0] == 1) {
                break;
            }
            cache[i][0] = 1;
        }
        for(int j = 0; j < width; j++) {
            if(obstacleGrid[0][j] == 1) {
                break;
            }
            cache[0][j] = 1;
        }
        for(int r = 1; r < height; r++) {
            for(int c = 1; c < width; c++) {
                if(obstacleGrid[r][c] == 1) {
                    continue;
                }
                cache[r][c] = cache[r][c - 1] + cache[r - 1][c];
            }
        }
        return cache[height - 1][width - 1];
    }
    
}