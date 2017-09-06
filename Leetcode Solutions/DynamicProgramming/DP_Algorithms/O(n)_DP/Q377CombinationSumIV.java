/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 * 
 * Example:
 * 
 * nums = [1, 2, 3]
 * target = 4
 * 
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 */

import java.util.*;

public class Q377CombinationSumIV {
    
    // 1. top-down dp
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return getCombinations(nums, target, memo);
    }
    
    private int getCombinations(int[] nums, int target, int[] memo) {
        if(memo[target] != -1) {
            return memo[target];
        }
        int result = 0;
        for(int num : nums) {
            if(num <= target) {
                result += getCombinations(nums, target - num, memo);
            }
        }
        memo[target] = result;
        return memo[target];
    }
    
    // 2. bottom-up dp: it is slower than top-down because it need to traverse every entry of memo, 
    //    while top-down only choose those real needed. 
    public int combinationSum42(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for(int i = 1; i < memo.length; i++) {
            for(int num : nums) {
                if(num <= i) {
                    memo[i] += memo[i - num];
                }
            }
        }
        return memo[target];
    }
    
}