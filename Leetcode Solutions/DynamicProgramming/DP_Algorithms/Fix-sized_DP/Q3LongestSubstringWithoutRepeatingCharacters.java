/**
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.*;

class Q3LongestSubstringWithoutRepeatingCharacters {
    
    // O(n) time two pointers solution [O(256) space for cache]
    // Here if "characters" are not necessary be "ASCII" characters, we need a HashMap to cache them.
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        int result = 0;
        int[] indices = new int[256];
        Arrays.fill(indices, -1);
        int prevEndPosition = -1;  // the end position of the previous valid substring
        for(int i = 0; i < s.length(); i++) {
            int character = s.charAt(i);
            if(indices[character] != -1 && prevEndPosition < indices[character]) {
                prevEndPosition = indices[s.charAt(i)];
            }
            result = Math.max(result, i - prevEndPosition);
            indices[character] = i;
        }
        return result;
    }
    
}