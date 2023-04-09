/*
 * *
 *  * CoinChange.java
 *  * Created by Rafsan Ahmad on 4/9/23, 6:23 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

import java.util.Arrays;

public class CoinChange {
    //https://leetcode.com/problems/coin-change/
    /*You are given an integer array coins representing coins of different denominations and an integer amount
    representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

Constraints:
1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4*/

    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        long[] dp = new long[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return (int) dp[amount];
        }
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] arr = {1, 2, 5};
        System.out.println(coinChange.coinChange(arr, 11));
    }
}
