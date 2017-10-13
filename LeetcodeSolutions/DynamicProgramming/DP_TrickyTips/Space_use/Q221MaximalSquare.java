/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Return 4.
 */

import java.util.*;

public class Q221MaximalSquare {
    
    // 1. O(n^2) space dp
    public int maximalSquare1(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        int maxSize = 0;
        int[][] squareSize = new int[height][width];
        for(int i = 0; i < height; i++) {
            squareSize[i][width - 1] = matrix[i][width - 1] - '0';
            maxSize = Math.max(maxSize, squareSize[i][width - 1]);
        }
        for(int j = 0; j < width; j++) {
            squareSize[height - 1][j] = matrix[height - 1][j] - '0';
            maxSize = Math.max(maxSize, squareSize[height - 1][j]);
        }
        for(int r = height - 2; r >= 0; r--) {
            for(int c = width - 2; c >= 0; c--) {
                decideSize(matrix, squareSize, r, c);
                maxSize = Math.max(maxSize, squareSize[r][c]);
            }
        }
        return maxSize * maxSize;
    }
    
    private void decideSize(char[][] matrix, int[][] squareSize, int r, int c) {
        if(matrix[r][c] == '0') {
            squareSize[r][c] = 0;
        } else {
            int preMin = Math.min(squareSize[r + 1][c], Math.min(squareSize[r][c + 1], squareSize[r + 1][c + 1]));
            squareSize[r][c] = preMin + 1;
        }
    }
    
    // 2. O(n) space dp: since we only need two rows (less space but slightly increase running time)
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        int[] prev = new int[width];
        int[] curr = new int[width];
        int maxSize = 0;
        for(int i = 0; i < width; i++) {
            prev[i] = matrix[0][i] - '0';
            maxSize = Math.max(maxSize, prev[i]);
        }
        for(int r = 1; r < height; r++) {
            curr[0] = matrix[r][0] - '0';
            maxSize = Math.max(maxSize, curr[0]);
            for(int c = 1; c < width; c++) {
                if(matrix[r][c] == '1') {
                    curr[c] = Math.min(prev[c - 1], Math.min(prev[c], curr[c - 1])) + 1;
                    maxSize = Math.max(maxSize, curr[c]);
                }
            }
            swap(prev, curr);
            Arrays.fill(curr, 0);
        }
        return maxSize * maxSize;
    }
    
    private void swap(int[] prev, int[] curr) {
        for(int i = 0; i < prev.length; i++) {
            int temp = prev[i];
            prev[i] = curr[i];
            curr[i] = temp;
        }
    }
    
}