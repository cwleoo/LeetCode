/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.
 * 
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 * 
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2. 
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
 * Hence, player 1 will never be the winner and you need to return False.
 * 
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 */

import java.util.*;

public class Q486PredictTheWinner {
    
    // 1. minimax tree with O(n^2) DP cache
    public boolean PredictTheWinner1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return true;
        }
        int sumTotal = 0;
        for(int num : nums) {
            sumTotal += num;
        }
        int length = nums.length;
        int[][] subArray = new int[length][length];
        boolean canPick = true;
        return getSum(0, length - 1, subArray, nums, canPick) >= (sumTotal + 1) / 2;
    }
    
    private int getSum(int left, int right, int[][] subArray, int[] nums, boolean canPick) {
        if(left == right) {
            if(canPick) {
                return nums[left];
            } else {
                return 0;
            }
        }
        if(subArray[left][right] != 0) {
            return subArray[left][right];
        }
        int leftRemaining = getSum(left, right - 1, subArray, nums, !canPick);
        int rightRemaining = getSum(left + 1, right, subArray, nums, !canPick);
        if(canPick) {
            // minimax tree: if can pick this turn, then choose the maximum
            subArray[left][right] = Math.max(nums[left] + rightRemaining, nums[right] + leftRemaining); 
        } else {
            // if cannot pick this turn, then choose the minimum
            subArray[left][right] = Math.min(rightRemaining, leftRemaining);
        }
        return subArray[left][right];
    }
    
    // 2. optimized version: instead of choosing minimum in the odd level of minimax tree, we use minus to simulate this process
    public boolean PredictTheWinner2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return true;
        }
        int length = nums.length;
        int[][] subArray = new int[length][length];
        for(int s = length - 1; s >= 0; s--) {
            for(int e = s + 1; e < length; e++) {
                subArray[s][e] = Math.max(nums[s] - subArray[s + 1][e], nums[e] - subArray[s][e - 1]);
            }
        }
        return subArray[0][length - 1] >= 0;  // if >= 0 then first player get higher scores 
    }
    
    // 3. O(n) DP: since we only use subArray[s+1][e] and subArray[s][e - 1] line by line
    //             we can use 1D array and allow overlapping to previous line.
    public boolean PredictTheWinner(int[] nums) {
        if(nums == null || nums.length == 0) {
            return true;
        }
        int length = nums.length;
        int[] subArray = new int[length];
        for(int s = length - 1; s >= 0; s--) {
            for(int e = s + 1; e < length; e++) {
                subArray[e] = Math.max(nums[s] - subArray[e], nums[e] - subArray[e - 1]);
            }
        }
        return subArray[length - 1] >= 0;  // if >= 0 then first player get higher scores 
    }
    
}