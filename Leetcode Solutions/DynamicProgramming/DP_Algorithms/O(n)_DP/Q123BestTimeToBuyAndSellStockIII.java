/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

import java.util.*;

public class Q123BestTimeToBuyAndSellStockIII {
    
    // two pointer DP (leftView + rightView) with O(n) time O(n) extra space
    // profits[i] = leftProfit[0 ~ i] + rightProfit[i ~ (length - 1)] 
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int length = prices.length;
        int[] profits = new int[length];
        int leftProfit = 0;                // left view of cumulative profit
        int rightProfit = 0;               // right view of cumulative profit
        int buy = prices[0];               // the price when buy a stock
        int sell = prices[length - 1];     // the price when sell a stock
        int maxProfit = Integer.MIN_VALUE;
        
        // Caculate left view
        for(int i = 1; i < length; i++) {
            if(prices[i] > prices[i - 1]) {
                leftProfit = Math.max(leftProfit, prices[i] - buy);
            } else if(prices[i] < buy) {
                buy = prices[i];
            }
            profits[i] += leftProfit;
        }
        // Caculate right view and sum to corresponding profits, at the same time compare with maxProfit
        for(int j = length - 2; j >= 0; j--) {
            if(prices[j] < prices[j + 1]) {
                rightProfit = Math.max(rightProfit, sell - prices[j]);
            } else if(prices[j] > sell) {
                sell = prices[j];
            }
            profits[j] += rightProfit;
            maxProfit = Math.max(maxProfit, profits[j]);
        }
        return maxProfit;
    }
    
}