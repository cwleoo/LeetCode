/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * 
 *     Example 1:
 *     Input: nums is [1, 1, 1, 1, 1], S is 3. 
 *     Output: 5
 *     Explanation: 
 *
 *         -1+1+1+1+1 = 3
 *         +1-1+1+1+1 = 3
 *         +1+1-1+1+1 = 3
 *         +1+1+1-1+1 = 3
 *         +1+1+1+1-1 = 3
 *
 *    There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 *    Note:
 *    1. The length of the given array is positive and will not exceed 20.
 *    2. The sum of elements in the given array will not exceed 1000.
 *    3. Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

import java.util.*;

public class Solution {
    
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(S > sum) {
            return 0;
        }
        int diff = sum - S;
        // the diff must be even, else no such sum exists.
        if(diff % 2 == 1) {
            return 0;
        }
        // diff/2 is the total sum of all negative elements
        diff /= 2;
        int[] counts = new int[diff + 1];
        counts[0] = 1; // if diff == 0, then there must exists as least one solution, that is sum of all elements
        for(int num : nums) {
            for(int i = diff; i >= num; i--) {
                counts[i] += counts[i - num];
            }
        }
        return counts[diff];
    }

}