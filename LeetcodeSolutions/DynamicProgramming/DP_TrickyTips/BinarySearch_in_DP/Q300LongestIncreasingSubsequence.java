/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 */

import java.util.*;

public class Q300LongestIncreasingSubsequence {
    
    // 1. O(n^2) dp
    public int lengthOfLIS1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int size = nums.length;
        int[] lengthMemo = new int[size];
        lengthOfLISHelper(nums, lengthMemo, size - 1);
        int maxLength = 0;
        for(int i = 0; i < size; i++) {
            maxLength = Math.max(maxLength, lengthMemo[i]);
        }
        return maxLength;
    }
    
    private int lengthOfLISHelper(int[] nums, int[] lengthMemo, int index) {
        if(lengthMemo[index] != 0) {
            return lengthMemo[index];
        }
        if(index == 0) {
            lengthMemo[0] = 1;
            return lengthMemo[0];
        }
        int maxLength = 0;
        for(int i = 0; i < index; i++) {
            int iLength = lengthOfLISHelper(nums, lengthMemo, i);
            if(nums[i] < nums[index]) {
                maxLength = Math.max(maxLength, iLength + 1);
            }
        }
        lengthMemo[index] = maxLength == 0 ? 1 : maxLength;
        return lengthMemo[index];
    }
    
    // 2. O(n^2) iteration dp 30ms
    public int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int maxLength = 0;
        int[] lengthsOfLIS = new int[length];
        for(int i = 0; i < length; i++) {
            int currentMax = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] < nums[i]) {
                    currentMax = Math.max(currentMax, lengthsOfLIS[j] + 1);
                }
            }
            lengthsOfLIS[i] = currentMax;
            maxLength = Math.max(maxLength, lengthsOfLIS[i]);
        }
        return maxLength;
    }
    
    // 3. O(nlogn) dp: use binary search to count elements which is in order.
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] helper = new int[nums.length];
        int maxLength = 0;
        int index = 0;
        for(int num : nums) {
            
            /* use binarySearch can decide whether a new element is in order.
             * if it is in order, i.e. after insertion the new length is beyond the previous length, we can simply add one to result;
             * if not, then the desired position will be replaced by new element and the result will keep the same. */ 
            index = Arrays.binarySearch(helper, 0, maxLength, num);
            
            if(index < 0) {
                index = -index - 1;
            }
            helper[index] = num;
            if(index == maxLength) {
                maxLength++;
            }
        }
        return maxLength;
    }
    
}