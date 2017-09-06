/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */

import java.util.*;

public class Q120Triangle {
    
    // 1. O(n) DP top-down approach: need another O(n) time to search the minimum
    public int minimumTotal1(List<List<Integer>> triangle) {
        if(triangle == null || triangle.isEmpty()) {
            return 0;
        }
        int size = triangle.size();
        List<Integer> sums = new ArrayList<>(size);
        sums.add(triangle.get(0).get(0));
        for(int i = 1; i < size; i++) {
            List<Integer> currentLine = triangle.get(i);
            int currSize = currentLine.size();
            sums.add(currentLine.get(currSize - 1) + sums.get(currSize - 2));
            for(int j = currSize - 2; j > 0; j--) {
                sums.set(j, currentLine.get(j) + Math.min(sums.get(j), sums.get(j - 1)));
            }
            sums.set(0, currentLine.get(0) + sums.get(0));
        }
        int minSum = Integer.MAX_VALUE;
        for(int sum : sums) {
            minSum = Math.min(sum, minSum);
        }
        return minSum;
    }
    
    // 2. O(n) extra space bottom-up manner. There is only one left when sum to top. The result will be the desired value
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.isEmpty()) {
            return 0;
        }
        
        int layerNum = triangle.size();
        
        // use List<Integer>
        /*List<Integer> minSum = new ArrayList(triangle.get(layerNum - 1));
        for(int i = layerNum - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                minSum.set(j, Math.min(minSum.get(j), minSum.get(j + 1)) + triangle.get(i).get(j));
            }
        }
        return minSum.get(0);*/
        
        // toArray(Integer[])
        List<Integer> lastLayer = triangle.get(layerNum - 1);
        Integer[] minSum = new Integer[lastLayer.size()];
        lastLayer.toArray(minSum);
        for(int i = layerNum - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                minSum[j] = Math.min(minSum[j], minSum[j + 1]) + triangle.get(i).get(j);
            }
        }
        return minSum[0];
    }
    
}