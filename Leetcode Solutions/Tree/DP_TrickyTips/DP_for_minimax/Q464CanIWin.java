/**
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 * 
 * What if we change the game so that players cannot re-use integers?
 * 
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 * 
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
 * 
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 * 
 * Example
 * 
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 
 * Output:
 * false
 * 
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */

import java.util.*;

public class Q464CanIWin {
    
    /* 
     * HashMap dp.
     * O(2^n) time: because in worst case, all possilbe status of used integer will be visit once, 
     *              and there are 2^n such possible cases.
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= maxChoosableInteger) {
            return true;
        }
        int totalSum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(totalSum < desiredTotal) {
            return false;
        }
        Map<Integer, Boolean> usedToOutcome = new HashMap<>();  // use an integer to describe the status of hasUsed[] array
        boolean[] hasUsed = new boolean[maxChoosableInteger + 1];
        return canIWinHelper(desiredTotal, usedToOutcome, hasUsed);
    }
    
    private boolean canIWinHelper(int desiredTotal, Map<Integer, Boolean> usedToOutcome, boolean[] hasUsed) {
        if(desiredTotal <= 0) {
            return false;
        }
        int key = format(hasUsed);   // get the integer representation of hasUsed[] boolean array
        if(!usedToOutcome.containsKey(key)) {
            // try each possible choice, if one choice lead to success, then I can win in the final.
            for(int i = 1; i < hasUsed.length; i++) {
                if(!hasUsed[i]) {
                    hasUsed[i] = true;
                    // if rival failed, i.e. "I" lose to reach "desiredTotal - i"
                    if(!canIWinHelper(desiredTotal - i, usedToOutcome, hasUsed)) {
                        usedToOutcome.put(key, true);
                        hasUsed[i] = false;
                        return true;
                    }
                    hasUsed[i] = false;
                }
            }
            usedToOutcome.put(key, false);
        }
        return usedToOutcome.get(key);
    }
    
    private int format(boolean[] hasUsed) {
        int key = 0;
        for(boolean used : hasUsed) {
            key <<= 1;
            if(used) {
                key |= 1;
            }
        }
        return key;
    }
    
}