/**
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") ¡ú false
 * isMatch("aa","aa") ¡ú true
 * isMatch("aaa","aa") ¡ú false
 * isMatch("aa", "a*") ¡ú true
 * isMatch("aa", ".*") ¡ú true
 * isMatch("ab", ".*") ¡ú true
 * isMatch("aab", "c*a*b") ¡ú true
 */

import java.util.*;

public class Q10RegularExpressionMatching {
    
    // 1. straight-forward + backTrack (Too complex logic)
    
    // 2. 2D array dp: status[r][c] = true means s[0..r-1] matches p[0..c-1]
    // also accept '*' as a character
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        if(p.isEmpty()) return s.isEmpty();
        int height = s.length() + 1;
        int width = p.length() + 1;
        boolean[][] status = new boolean[height][width];
        status[0][0] = true;
        // no need to initialize status[r][0] as false
        // initialize status[0][c], enable first 3 position of '*' in "a*b*c*def*g*" to be true
        for(int c = 2; c < width; c++) {
            if(p.charAt(c - 1) == '*' && status[0][c - 2]) {
                status[0][c] = true;
            }
        }
        // check if s[0~r-1] matches p[0~c-1]
        for(int r = 1; r < height; r++) {
            int asteriskCount = 0;  // the count of continued '*'
            for(int c = 1; c < width; c++) {
                if(p.charAt(c - 1) != '*') { // no continued '*', reset count
                    asteriskCount = 0;
                }
                if((s.charAt(r - 1) == p.charAt(c - 1) && s.charAt(r - 1) != '*') // if a non-'*' character matches
                   || p.charAt(c - 1) == '.') { // '.' matches any character
                    status[r][c] = status[r - 1][c - 1];
                } 
                if(p.charAt(c - 1) == '*') {
                    if(c == 1 || p.charAt(c - 2) == '*') { // only count continued '*' or if the first character is '*'
                        asteriskCount++;
                    }
                    if(asteriskCount == 1) { // we encounter a character '*', not a functional '*'
                        if(s.charAt(r - 1) == '*') {
                            status[r][c] = status[r - 1][c - 1];
                        }
                        continue;
                    } else {  // encounter a functional '*'
                        asteriskCount = 0;  // reset asteriskCount
                        if(s.charAt(r - 1) != p.charAt(c - 2) && p.charAt(c - 2) != '.') { // a* counts as empty
                            status[r][c] = status[r][c - 2];
                        } else {
                            status[r][c] = status[r - 1][c]  // a* counts as multiple a
                                        || status[r][c - 1]  // a* counts as a single a
                                        || status[r][c - 2]; // a* counts as empty
                        }
                    }
                }
            }
        }
        return status[height - 1][width - 1];
    }
    
}