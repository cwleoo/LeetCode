/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
 * 
 * Return all such possible sentences.
 * 
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 */

import java.util.*;

public class Q140WordBreakII {
    
    // 1. 2D dp based on s.length() + backTrack.
    //    TLE occurs when s is too large, so we need to check whether s is breakable before go into next step.
    //    O(n^2) dp space O(n^2) time. 14ms
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        int length = s.length();
        Set<String> words = new HashSet<>(wordDict);
        if(!hasSolution(s, words, length)) {
            return result;
        }
        boolean[][] canMatch = new boolean[length][length + 1];
        boolean hasSolution = false;
        // generate the canMatch[r][c] -> wordToIndex.containsKey(s.substring(r, c))
        for(int r = 0; r < length; r++) {
            for(int c = r + 1; c <= length; c++) {
                canMatch[r][c] = words.contains(s.substring(r, c));
                if(c == length && canMatch[r][c]) {
                    hasSolution = true;
                }
            }
        }
        // if no solution exists, return directly
        if(!hasSolution) {
            return result;
        }
        List<String> tempList = new ArrayList<>();
        backTrack(0, canMatch, length, s, words, result, tempList);
        return result;
    }
    
    // check whether has solution (refer to Q139 wordBreak I)
    private boolean hasSolution(String s, Set<String> words, int length) {
        boolean[] dp = new boolean[length];
        dp[length - 1] = words.contains(s.substring(length - 1));
        for(int i = length - 2; i >= 0; i--) {
            if(words.contains(s.substring(i))) {
                dp[i] = true;
                continue;
            }
            for(int j = i + 1; j < length; j++) {
                dp[i] = dp[j] && words.contains(s.substring(i, j));
                if(dp[i]) break;
            }
        }
        return dp[0];
    }
    
    // use canMatch[][] and backTracking to build sentences
    private void backTrack(int from, boolean[][] canMatch, int length, String s, Set<String> words, List<String> result, List<String> tempList) {
        if(from == length) {
            StringBuilder sentence = new StringBuilder();
            for(String word : tempList) {
                sentence.append(word).append(" ");
            }
            sentence.deleteCharAt(sentence.length() - 1);
            result.add(sentence.toString());
            return;
        }
        for(int to = from + 1; to <= length; to++) {
            if(canMatch[from][to]) {
                tempList.add(s.substring(from, to));
                backTrack(to, canMatch, length, s, words, result, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    
    // 2. use map to dp, it do not need to check validity because if no solution, it will return empty list very quickly.
    //    14ms. Same performance as above.
    public List<String> wordBreak2(String s, List<String> wordDict) {
        return wordBreak(s, wordDict, new HashMap<String, List<String>>());
    }       

    // DFS function returns an array including all substrings derived from s.
    private List<String> wordBreak(String s, List<String> wordDict, HashMap<String, List<String>>map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> res = new ArrayList<>();     
        if (s.length() == 0) {  // to distinguish the end status from "no solution"
            res.add("");
            return res;
        }               
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = wordBreak(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) 
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
            }
        }       
        map.put(s, res);
        return res;
    }
    
}