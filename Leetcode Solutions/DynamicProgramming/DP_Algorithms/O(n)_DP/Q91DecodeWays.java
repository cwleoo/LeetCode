/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * 
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 */

import java.util.*;

public class Q91DecodeWays {
    
    // 1. recursive dp.
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int len = s.length();
        int[] ways = new int[len + 1];
        for(int i = 0; i <= len; i++) {
            ways[i] = -1;
        }
        return numDecodings(ways, s, len);
    }
    
    private int numDecodings(int[] ways, String s, int n) {
        if(ways[n] != -1) {
            return ways[n];
        }
        if(n == 0 || n == 1) {
            ways[n] = 1;
            return ways[n];
        }
        String currUnit = s.substring(n - 2, n);  // currUnit = "xx"
        // handle corner case: what if currUnit contains '0'?
        int value = Integer.parseInt(currUnit);
        if(value == 0) {  // two char are both 0
            ways[n] = 0;
        } else if(currUnit.charAt(0) == '0') {  // the first char is 0
            ways[n] = numDecodings(ways, s, n - 1);
        } else if(currUnit.charAt(1) == '0') {  // the second char is 0
            if(value > 26) {
                ways[n] = 0;
            } else {
                ways[n] = numDecodings(ways, s, n - 2);
            }
        } else {  // neither are 0
            if(value > 26) {
                ways[n] = numDecodings(ways, s, n - 1);
            } else {
                ways[n] = numDecodings(ways, s, n - 1) + numDecodings(ways, s, n - 2);
            }
        }
        return ways[n];
    }
    
    // 2. iterative dp.
    public int numDecodings2(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int len = s.length();
        int[] ways = new int[len + 1];
        ways[len] = 1;
        ways[len - 1] = s.charAt(len - 1) == '0' ? 0 : 1;
        for(int i = len - 2; i >= 0; i--) {
            if(s.charAt(i) == '0') {
                continue;
            } else {
                ways[i] = Integer.parseInt(s.substring(i, i + 2)) > 26 ? ways[i + 1] : ways[i + 1] + ways[i + 2];
            }
        }
        return ways[0];
    }
    
}