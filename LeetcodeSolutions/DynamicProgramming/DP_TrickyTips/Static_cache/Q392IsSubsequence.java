/**
 * Given a string s and a string t, check if s is subsequence of t.
 * 
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * 
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 * 
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 * 
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 */

import java.util.*;

public class Q392IsSubsequence {
    
    // 1. straight forward 18ms O(n) solution
    public boolean isSubsequence(String s, String t) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        return isSubstring(s, 0, t, 0);
    }
    
    private boolean isSubstring(String s, int sCurr, String t, int tCurr) {
        if(sCurr == s.length()) {
            return true;
        }
        char target = s.charAt(sCurr);
        for(int i = tCurr; i < t.length(); i++) {
            if(t.charAt(i) == target) {
                return isSubstring(s, sCurr + 1, t, i + 1);
            }
        }
        return false;
    }
    
    // 2. refined version
    public boolean isSubsequence2(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
    }
    
    // 3. if there is one big T while a lots of S, then use binarySearch + dp O(Mlog?) per S.
    @SuppressWarnings("unchecked")
    private static ArrayList<Integer>[] indexArray = new ArrayList[26];
    static {
        for(int i = 0; i < 26; i++) {
            indexArray[i] = new ArrayList<>();
        }
    }
    
    private static int position = 0;
    
    public boolean isSubsequence3(String s, String t) {
        int currPosition = 0;
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            int value = binarySearch(indexArray[curr - 'a'], currPosition);
            if(value == -1) {
                if(position < t.length()) {
                    for(int j = position; j < t.length(); j++) {
                        indexArray[t.charAt(j) - 'a'].add(j);
                        if(t.charAt(j) == curr) {
                            position = j + 1;
                            currPosition = position;
                            break;
                        }
                    }
                    if(currPosition != position) return false;
                    continue;
                } else {
                    return false;
                }
            } else {
                currPosition = value + 1;
            }
        }
        return true;
    }
    
    // for exercise: self-defined binarySearch() method. Use Collections.binarySearch() instead.
    private int binarySearch(ArrayList<Integer> index, int target) {
        if(index.isEmpty()) {
            return -1;
        }
        int low = 0;
        int high = index.size() - 1;
        while(low < high) {
            int mid = low + (high - low)/2;
            int value = index.get(mid);
            if(value == target) {
                return value;
            } else if(value < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return index.get(low) > target ? index.get(low) : -1;
    }
    
}