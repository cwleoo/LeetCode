/**
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 * 
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 * 
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * 
 * Note:
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 * 
 * Example 1:
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * Output: 4
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are ¡°10,¡±0001¡±,¡±1¡±,¡±0¡±
 * 
 * Example 2:
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 */

import java.util.*;

public class Q474OnesAndZeroes {
    
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs == null || strs.length == 0) {
            return 0;
        }
        // maxNum[r][c] means how many string can be built with r 0s and c 1s
        int[][] maxNum = new int[m + 1][n + 1];
        for(String str : strs) {
            int zeroes = 0;
            int ones = 0;
            // count ones and zeroes
            for(char ch : str.toCharArray()) {
                if(ch == '0') {
                    zeroes++;
                } else {
                    ones++;
                }
            }
            // update maxNum array
            for(int r = m; r >= zeroes; r--) {
                for(int c = n; c >= ones; c--) {
                    maxNum[r][c] = Math.max(maxNum[r][c], maxNum[r - zeroes][c - ones] + 1);
                }
            }
        }
        return maxNum[m][n];
    }
    
}