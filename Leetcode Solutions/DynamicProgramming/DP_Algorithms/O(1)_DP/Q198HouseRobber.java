/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */

import java.util.*;

public class Q198HouseRobber {
    
    // 1. O(n) space dp
    public int rob1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int length = nums.length;
        int[] money = new int[length];
        money[0] = nums[0];
        money[1] = Math.max(money[0], nums[1]);
        for(int i = 2; i < length; i++) {
            money[i] = Math.max(money[i - 2] + nums[i], money[i - 1]);
        }
        return money[length - 1];
    }
    
    // 2. O(1) space dp 
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int oneBefore = 0;
        int twoBefore = 0;
        int current = 0;
        for(int num : nums) {
            current = Math.max(twoBefore + num, oneBefore);
            twoBefore = oneBefore;
            oneBefore = current;
        }
        return current;
    }
    
}