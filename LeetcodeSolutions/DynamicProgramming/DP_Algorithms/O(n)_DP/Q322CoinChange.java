/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * 
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * 
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 * 
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */

import java.util.*;

public class Q322CoinChange {
    
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int result = -1;
        int[] changes = new int[amount + 1];
        changes[0] = 0;
        for(int i = 1; i <= amount; i++) {
            int minCoins = Integer.MAX_VALUE;
            for(int coin : coins) {
                if(coin <= i) {
                    if(changes[i - coin] >= 0) {
                        minCoins = Math.min(minCoins, changes[i - coin] + 1);
                    }
                }
            }
            changes[i] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        }
        return changes[amount];
    }
    
}