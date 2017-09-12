/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * 
 * Example:
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */

import java.util.*;

public class Q309BestTimeToBuyAndSellStockWithCooldown {
    
    // 1. O(n) space dp, state machine
    /*  __
     * /  \     rest
     * \->[rest]<---[sell]
     *      \         ^
     *    buy\       /sell
     *        \     /
     *         >[buy]<-
     *             /   \
     *             \___/
     * rest[i] = max(rest[i - 1], sell[i - 1])
     * buy[i] = max(rest[i - 1] - prices[i], buy[i - 1])
     * sell[i] = buy[i - 1] + prices[i]
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int length = prices.length;
        int[] rest = new int[length];
        int[] buy = new int[length];
        int[] sell = new int[length];
        rest[0] = 0;
        buy[0] = -prices[0];
        sell[0] = Integer.MIN_VALUE;  // desired to buy stock
        for(int i = 1; i < length; i++) {
            rest[i] = Math.max(rest[i - 1], sell[i - 1]);
            buy[i] = Math.max(buy[i - 1], rest[i - 1] - prices[i]);
            sell[i] = buy[i - 1] + prices[i];
        }
        return Math.max(sell[length - 1], rest[length - 1]);
    }
    
    // 2. O(1) space dp, optimized from state machine algorithm:
    /* Since rest[i] = sell[i - 1], substitute in and eliminate rest[] term, we get:
     * 
     * buy[i] = max(sell[i-2]-price, buy[i-1])
     * sell[i] = max(buy[i-1]+price, sell[i-1])
     * 
     * Also, since we need only (i - 1) and (i - 2) to deduce i, we can use O(1) space to optimize space use.
     */
    public int maxProfit1(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int sell = 0, preSell = 0, buy = Integer.MIN_VALUE, preBuy;
        for (int price : prices) { // price is today's price, which determines the profit of today +price/-price
            preBuy = buy;
            // whether buy today depends on whether yesterday rest is better or yesterday buy is better
            buy = Math.max(preSell - price, preBuy);
            preSell = sell;
            // whether sell today depends on whether today sell is better or yesterday has sold (today rest)
            sell = Math.max(preBuy + price, preSell);
        }
        return sell;
    }
    
}