/**
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 * 
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 * 
 * Example 1:
 * Input: "a"
 * Output: 1
 * Explanation: Only the substring "a" of string "a" is in the string s.
 * 
 * Example 2:
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 * 
 * Example 3:
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 */

import java.util.*;

public class Q467UniqueSubstringsInWraparoundString {
    
    // 1. DP with two arrays: check each substring that starts with a...z, if we find a new one, we add one to result
    public int findSubstringInWraproundString1(String p) {
        if(p == null || p.length() == 0) {
            return 0;
        }
        int pLength = p.length();
        // keep track of the number of consecutive letters for each substrings which starts from a...z
        int[] consecutiveNumberFrom = new int[26];  
        consecutiveNumberFrom[p.charAt(0) - 'a'] = 1;
        // keep track of the number of legacy consecutive letters
        int[] consecutiveCount = new int[pLength];
        consecutiveCount[0] = 1;
        int result = 1;
        for(int i = 1; i < pLength; i++) {
            consecutiveCount[i] = isConsecutive(p.charAt(i - 1), p.charAt(i))
                                ? consecutiveCount[i - 1] + 1
                                : 1;
            for(int j = i - consecutiveCount[i] + 1; j <= i; j++) {
                if(consecutiveNumberFrom[p.charAt(j) - 'a'] < i - j + 1) {
                    result++;
                    consecutiveNumberFrom[p.charAt(j) - 'a']++;
                } else {
                    break;
                }
            }
        }
        return result;
    }
    
    // 2. Optimize from 1: we don't need to keep track of how many consecutive letters until current, i.e. consecutiveCount[]
    //    We can do this by only one integer
    public int findSubstringInWraproundString(String p) {
        if(p == null || p.length() == 0) {
            return 0;
        }
        // count[i] is the maximum unique substring that ends with a...z
        int[] count = new int[26];
        // keep track of the number of consecutive letters ended with current letter
        int consecutiveCount = 1;
        for(int i = 0; i < p.length(); i++) {
            if(i > 0 && isConsecutive(p.charAt(i - 1), p.charAt(i))) {
                consecutiveCount++;
            } else {
                consecutiveCount = 1;
            }
            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], consecutiveCount);
        }
        int result = 0;
        for(int singleCount : count) {
            result += singleCount;
        }
        return result;
    }
    
    private boolean isConsecutive(char previous, char current) {
        return current - previous == 1 || current - previous == -25;
    }
    
}