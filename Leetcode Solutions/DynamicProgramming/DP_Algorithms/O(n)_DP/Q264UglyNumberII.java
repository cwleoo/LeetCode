/**
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 */

import java.util.*;

public class Q264UglyNumberII {
    
    // 3 pointers dynamic programming
    public int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int ugly2 = 2, ugly3 = 3, ugly5 = 5;
        int index2 = 0, index3 = 0, index5 = 0;
        /*
         * one cache, during each time of merge,
         * compare (2 * ugly2, 3 * ugly3, 5 * ugly5) and pick the smallest one
         * each ugly number has its own index of ugly array.
         */
        for(int i = 1; i < n; i++) {
            int min = Math.min(Math.min(ugly2, ugly3), ugly5);
            uglyNums[i] = min;
            // update each ugly number if it equals to min, namely, find next bigger one 
            // it is necessary because min may equals to more than one candidates e.g. 6 = 2 x 3
            if(min == ugly2) {
                ugly2 = 2 * uglyNums[++index2];
            }
            if(min == ugly3) {
                ugly3 = 3 * uglyNums[++index3];
            }
            if(min == ugly5) {
                ugly5 = 5 * uglyNums[++index5];
            }
        }
        return uglyNums[n-1];
    }
    
}