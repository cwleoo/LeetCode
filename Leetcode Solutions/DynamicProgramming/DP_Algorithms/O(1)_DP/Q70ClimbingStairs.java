/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */

import java.util.*;

public class Q70ClimbingStairs {
    
    public int climbStairs(int n) {
        if(n < 1) {
            return 0;
        }
        int result = 1;
        int oneStepToTop = 1;
        int twoStepToTop = 1;
        for(int i = 2; i <= n; i++) {
            result = oneStepToTop + twoStepToTop;
            twoStepToTop = oneStepToTop;
            oneStepToTop = result;
        }
        return result;
    }
    
}