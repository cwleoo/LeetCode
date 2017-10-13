/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
 * 
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * 
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * 
 * Note:
 * 1. The length of the array won't exceed 10,000.
 * 2. You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */

import java.util.*;

public class Q523ContinuousSubarraySum {
    
    // 1. O(n^2) time sums DP 57ms
    public boolean checkSubarraySum1(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int length = nums.length;
        int[] sums = new int[length + 1];
        sums[0] = 0;
        for(int i = 1; i <= length; i++) {
            sums[i] = nums[i - 1] + sums[i - 1];
        }
        for(int i = 0; i < length - 1; i++) {
            for(int j = i + 2; j <= length; j++) {
                if(k == 0 && sums[j] - sums[i] == 0) {
                    return true;
                } else if(k != 0 && (sums[j] - sums[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // 2. O(n) time: use hash map to cache (remainder, index) 13ms
    //    if two same remainders are spotted, and their indices difference is greater than 1, then return true
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> remainderToIndex = new HashMap<>();
        remainderToIndex.put(0, -1);  // handle the corner case: if first several elements sum to k
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(k != 0) sum %= k;
            if(remainderToIndex.containsKey(sum)) {
                if(remainderToIndex.get(sum) + 1 < i) {
                    return true;
                }
            } else {
                remainderToIndex.put(sum, i);
            }
        }
        return false;
    }
    
}