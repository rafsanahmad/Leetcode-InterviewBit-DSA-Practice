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


    public int coinChangeTopDown(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        return coinChangeRec(coins, amount, coins.length - 1, dp);
    }

    public int coinChangeRec(int[] coins, int amount, int index, int[][] dp) {
        if (amount == 0)
            return 0;
        if (amount < 0 || index < 0)
            return -1;

        if (dp[index][amount] != Integer.MAX_VALUE) {
            return dp[index][amount];
        }

        int result = Integer.MAX_VALUE;
        //Take the current coin
        int take = coinChangeRec(coins, amount - coins[index], index, dp);
        if (take != -1) {
            result = Math.min(result, take + 1);
        }

        //Do not take current coin
        int doNotTake = coinChangeRec(coins, amount, index - 1, dp);
        if (doNotTake != -1) {
            result = Math.min(result, doNotTake);
        }

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        return dp[index][amount] = result;
    }


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

    //Coin change II
    /*You are given an integer array coins representing coins of different denominations and
    an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot
be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1
*/

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return changeRec(amount, coins, coins.length - 1, dp);
    }

    public int changeRec(int amount, int[] coins, int index, int[][] dp) {
        if (amount == 0)
            return 1;

        if (amount < 0 || index < 0)
            return 0;


        if (dp[index][amount] != -1)
            return dp[index][amount];

        int take = changeRec(amount - coins[index], coins, index, dp);
        int doNotTake = changeRec(amount, coins, index - 1, dp);

        return dp[index][amount] = take + doNotTake;
    }

    public int changeBottomUp(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
                dp[i][j] += dp[i - 1][j];
            }
        }

        return dp[len][amount];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] arr = {1, 2, 5};
        System.out.println(coinChange.coinChange(arr, 11));
        System.out.println(coinChange.coinChangeTopDown(arr, 11));

        System.out.println(coinChange.change(5, arr));
        System.out.println(coinChange.changeBottomUp(5, arr));
    }
}
