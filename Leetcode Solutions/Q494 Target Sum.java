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