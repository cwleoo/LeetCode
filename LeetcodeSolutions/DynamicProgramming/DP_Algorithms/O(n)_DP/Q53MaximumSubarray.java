/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * 
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */

import java.util.*;

public class Q53MaximumSubarray {
    
    // 1. divide and conquer O(nlogn)
    public int maxSubArray1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
    
    private int maxSubArrayHelper(int[] nums, int low, int high) {
        if(low == high) {
            return nums[low];
        } else {
            int mid = low + (high - low)/2;
            int leftSum = maxSubArrayHelper(nums, low, mid);
            int rightSum = maxSubArrayHelper(nums, mid + 1, high);
            int crossSum = maxCrossSubArray(nums, low, mid, high);
            if(leftSum >= rightSum && leftSum >= crossSum) {
                return leftSum;
            } else if(rightSum >= leftSum && rightSum >= crossSum) {
                return rightSum;
            } else {
                return crossSum;
            }
        }
    }
    
    private int maxCrossSubArray(int[] nums, int low, int mid, int high) {
        int rMax = nums[mid + 1];
        int rCurr = nums[mid + 1];
        for(int i = mid + 2; i <= high; i++) {
            rCurr += nums[i];
            if(rCurr > rMax) {
                rMax = rCurr;
            }
        }
        int lMax = nums[mid];
        int lCurr = nums[mid];
        for(int j = mid - 1; j >= low; j--) {
            lCurr += nums[j];
            if(lCurr > lMax) {
                lMax = lCurr;
            }
        }
        return lMax + rMax;
    }
    
    // 2. dp O(n)
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int result = nums[0];
        int[] sums = new int[length];
        sums[0] = nums[0];
        for(int i = 1; i < length; i++) {
            sums[i] = nums[i] + (sums[i - 1] > 0 ? sums[i - 1] : 0);
            result = Math.max(result, sums[i]);
        }
        return result;
    }
    
}