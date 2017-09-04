/**
 * A robot is located at the top-left corner of a m x n grid.
 * 
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
 * 
 * How many possible unique paths are there? (Note m and n will be at most 100)
 */

import java.util.*;

public class Q62UniquePaths {
    
    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0) {
            return 0;
        }
        int[][] pathsCache = new int[m][n];
        for(int i = 0; i < m; i++) {
            pathsCache[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            pathsCache[0][j] = 1;
        }
        for(int r = 1; r < m; r++) {
            for(int c = 1; c < n; c++) {
                pathsCache[r][c] = pathsCache[r][c - 1] + pathsCache[r - 1][c];
            }
        }
        return pathsCache[m-1][n-1];
    }
    
}