/*
 * *
 *  * Best Time to Buy and Sell Stock III.java
 *  * Created by Rafsan Ahmad on 9/2/24, 3:37 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.DynamicProgramming;

import java.util.Arrays;

public class BestTimeToBuyStockIII {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
    /*You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock
before you buy again).

Example 1:
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple
 transactions at the same time. You must sell before buying again.

Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


Constraints:

1 <= prices.length <= 10^5
0 <= prices[i] <= 10^5
*/

    //0=buy, 1=sell
    int maxProfitHelper(int[] Arr, int n, int ind, int buy, int cap, int[][][] dp) {
        if (ind == n || cap == 0)
            return 0;

        //Already calculated
        if (dp[ind][buy][cap] != -1)
            return dp[ind][buy][cap];

        int profit = 0;

        if (buy == 0) {
            // We can buy the stock
            profit = Math.max(maxProfitHelper(Arr, n, ind + 1, 0, cap, dp),
                    -Arr[ind] + maxProfitHelper(Arr, n, ind + 1, 1, cap, dp));
        }

        if (buy == 1) {
            // We can sell the stock
            //If sell happens reduce cap by 1 as transaction completes
            profit = Math.max(maxProfitHelper(Arr, n, ind + 1, 1, cap, dp),
                    Arr[ind] + maxProfitHelper(Arr, n, ind + 1, 0, cap - 1, dp));
        }

        return dp[ind][buy][cap] = profit;
    }

    int maxProfit(int[] prices) {
        int n = prices.length;

        // Creating a 3D dp array of size [n][2][3]
        //n=Prices index out of length
        //2 -> 0 or 1 (Buy or Sell)
        //3 -> max 2 transaction
        int[][][] dp = new int[n][2][3];

        // Initialize the dp array with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // Calculate and return the maximum profit
        return maxProfitHelper(prices, n, 0, 0, 2, dp);
    }

    public static void main(String[] args) {
        BestTimeToBuyStockIII buyStockII = new BestTimeToBuyStockIII();
        int[] arr = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(buyStockII.maxProfit(arr));
    }
}
