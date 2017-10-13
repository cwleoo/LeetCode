/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 */

import java.util.*;

public class Q64MinimumPathSum {
    
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int height = grid.length;
        int width = grid[0].length;
        int[][] cache = new int[height][width];
        cache[height - 1][width - 1] = grid[height - 1][width - 1];
        for(int i = height - 2; i >= 0; i--) {
            cache[i][width - 1] = grid[i][width - 1] + cache[i + 1][width - 1];
        }
        for(int j = width - 2; j >= 0; j--) {
            cache[height - 1][j] = grid[height - 1][j] + cache[height - 1][j + 1];
        }
        for(int r = height - 2; r >= 0; r--) {
            for(int c = width - 2; c >= 0; c--) {
                cache[r][c] = grid[r][c] + Math.min(cache[r][c + 1], cache[r + 1][c]);
            }
        }
        return cache[0][0];
    }
    
}