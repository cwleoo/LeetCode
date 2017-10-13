/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 */

import java.util.*;

public class Q368LargestDivisibleSubset {
    
    // 1. O(n^3) backTracking: easy to implement, but TLE got. (accept with 689ms running time)
    public List<Integer> largestDivisibleSubset1(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> tempList = new ArrayList<>();
        backTrack(0, 1, nums, result, tempList);
        return result;
    }
    
    private void backTrack(int curr, int previous, int[] nums, List<Integer> result, List<Integer> tempList) {
        if(result.size() == nums.length) {
            return;
        }
        for(int i = curr; i < nums.length && result.size() < nums.length; i++) {
            if(nums[i] % previous == 0) {
                tempList.add(nums[i]);
                backTrack(i + 1, nums[i], nums, result, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
        if(tempList.size() > result.size()) {
            result.clear();
            result.addAll(tempList);
        }
    }
    
    // 2. O(n^2) dp
    /**
     * the item in dp array
     */
    private static class Multiple {
        int length;  // the length so far
        int prevIndex;  // previous index, inorder to reconstruct array later
        public Multiple(int length, int prevIndex) {
            this.length = length;
            this.prevIndex = prevIndex;
        }
    }
    
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int length = nums.length;
        Multiple[] multiples = new Multiple[length];
        multiples[0] = new Multiple(1, 0);
        int maxLength = 1;
        int lastIndex = 0;
        for(int i = 1; i < length; i++) {
            multiples[i] = new Multiple(1, i);
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] % nums[j] == 0 && multiples[j].length >= multiples[i].length) {
                    multiples[i].prevIndex = j;
                    multiples[i].length = multiples[j].length + 1;
                }
            }
            if(multiples[i].length > maxLength) {
                maxLength = multiples[i].length;
                lastIndex = i;
            }
        }
        List<Integer> result = new ArrayList<>(maxLength);
        for(int i = maxLength; i > 0; i--) {
            result.add(0, nums[lastIndex]);
            lastIndex = multiples[lastIndex].prevIndex;
        }
        return result;
    }
}