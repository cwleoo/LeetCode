/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * Example 1:
 * 
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * Example 2:
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */

import java.util.*;

public class Q416PartitionEqualSubsetSum {
    
    // 1. O(n) dp O(n^2) run time
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        boolean[] canSumTo = new boolean[sum + 1];
        canSumTo[0] = true;
        for(int num : nums) {
            if(num == sum) return true;
            for(int i = sum; i > 0; i--) {
                if(i >= num) {
                    canSumTo[i] = canSumTo[i] || canSumTo[i - num];
                }
            }
        }
        return canSumTo[sum];
    }
    
    // 2. O(n^2) backTrack (TLE get because we dont use dp)
    public boolean canPartition2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        return backTrack(0, sum, nums);
    }
    
    private boolean backTrack(int curr, int sum, int[] nums) {
        if(sum == 0) {
            return true;
        }
        if(sum < 0) {
            return false;
        }
        for(int i = curr; i < nums.length; i++) {
            if(backTrack(i + 1, sum - nums[i], nums)) {
               return true; 
            }
        }
        return false;
    }
    
}