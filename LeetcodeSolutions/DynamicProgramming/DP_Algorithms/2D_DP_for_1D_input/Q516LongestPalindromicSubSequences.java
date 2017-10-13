/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * Input: "bbbab"
 * Output: 4
 * One possible longest palindromic subsequence is "bbbb".
 * 
 * Example 2:
 * Input: "cbbd"
 * Output: 2
 * One possible longest palindromic subsequence is "bb".
 */

import java.util.*;

public class Q516LongestPalindromicSubSequences {
    
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        int[][] cache = new int[s.length()][s.length()];
        return getPalindromeLength(s, 0, s.length() - 1, cache);
    }
    
    private int getPalindromeLength(String s, int left, int right, int[][] cache) {
        if(left > right) {
            return 0;
        }
        if(cache[left][right] != 0) {
            return cache[left][right];
        }
        if(left == right) {
            cache[left][right] = 1;
            return 1;
        }
        if(s.charAt(left) == s.charAt(right)) {
            cache[left][right] = getPalindromeLength(s, left + 1, right - 1, cache) + 2;
        }else {
            cache[left][right] = Math.max(getPalindromeLength(s, left + 1, right, cache), getPalindromeLength(s, left, right - 1, cache));
        }
        return cache[left][right];
    }
    
}