/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 */

import java.util.*;

public class Q139WordBreak {
    
    /*
     * Use dp to mark and move. It is necessary because:
     * 1. there are overlapping subproblems for checking each substrings;
     * 2. It is optimal sustructure. 
     *    The solution can be constructed efficiently from optimal solutions of its subproblems,
     *    because we traverse the string in one direction and each subproblem is optimal and independent.
     */
    // 1. 12ms dp without advanced cache of all true values
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len];
        Set<String> words = new HashSet<>(wordDict);
        dp[len - 1] = words.contains(s.substring(len - 1));
        for(int i = len - 2; i >= 0; i--) {
            if(words.contains(s.substring(i))) {
                dp[i] = true;
                //System.out.printf("%d", i);
                continue;
            }
            for(int j = i + 1; j < len; j++) {
                dp[i] = dp[j] && words.contains(s.substring(i, j));
                if(dp[i]) break;
            }
        }
        return dp[0];
    }
    
    // 2. plus advanced cache: will promote run time if dictionary is extremely large
    //    but will need more time to add each valid index to List.
    public boolean wordBreak2(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] canMatch = new boolean[length];
        Set<String> words = new HashSet<>(wordDict);
        List<Integer> matchedIndices = new ArrayList<>();
        for(int i = 1; i <= length; i++) {
            if(words.contains(s.substring(0, i))) {
                canMatch[i - 1] = true;
            } else {
                for(int index : matchedIndices) {
                    if(words.contains(s.substring(index + 1, i))) {
                        canMatch[i - 1] = true;
                        break;
                    }
                }
            }
            if(canMatch[i - 1]) {
                matchedIndices.add(i - 1);
            }
        }
        return canMatch[length - 1];
    }
    
}