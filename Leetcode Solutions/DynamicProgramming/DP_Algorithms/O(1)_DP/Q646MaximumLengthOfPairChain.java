/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * 
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 * 
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 * 
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * 
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */

import java.util.*;

public class Q646MaximumLengthOfPairChain {
    
    // O(nlogn) time O(1) space dp solution: 32ms 
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        //Arrays.sort(pairs, ((a, b) -> a[0] - b[0]));   // java8 fashion
        int chainLength = 1;
        int prevIndex = 0;
        for(int i = 1; i < pairs.length; i++) {
            if(pairs[i][0] > pairs[prevIndex][1]) {
                chainLength++;
                prevIndex = i;
            } else if(pairs[i][1] < pairs[prevIndex][1]) {
                prevIndex = i;
            }
        }
        return chainLength;
    }
    
}