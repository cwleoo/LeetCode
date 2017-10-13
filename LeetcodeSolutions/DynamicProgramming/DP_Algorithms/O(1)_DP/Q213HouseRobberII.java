/**
 * Note: This is an extension of House Robber.
 * 
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

import java.util.*;

public class Q213HouseRobberII {
    
    // 1. two O(n) space dp
    public int rob1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if(nums.length == 1) {
            return nums[0];
        }
        int[] withoutFirst = new int[len];
        int[] withoutSecond = new int[len];
        withoutFirst[0] = 0;
        withoutFirst[1] = nums[1];
        withoutSecond[0] = nums[0];
        withoutSecond[1] = nums[0];
        for(int i = 2; i < len; i++) {
            withoutFirst[i] = Math.max(withoutFirst[i - 1], withoutFirst[i - 2] + nums[i]);
            withoutSecond[i] = Math.max(withoutSecond[i - 1], withoutSecond[i - 2] + nums[i]);
        }
        return Math.max(withoutFirst[len - 1], withoutSecond[len - 2]);
    }
    
    // 2. optimal: only use O(1) extra space
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if(nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, len - 2), rob(nums, 1, len - 1));
    }
    
    private int rob(int[] nums, int low, int high) {
        int oneBefore = 0;
        int twoBefore = 0;
        int current = 0;
        for(int i = low; i <= high; i++) {
            current = Math.max(oneBefore, twoBefore + nums[i]);
            twoBefore = oneBefore;
            oneBefore = current;
        }
        return current;
    }
    
}