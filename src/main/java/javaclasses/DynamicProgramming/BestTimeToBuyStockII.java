/*
 * *
 *  * Best Time To Buy And Sell Stock II.java
 *  * Created by Rafsan Ahmad on 5/18/24, 4:51 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.DynamicProgramming;

import java.util.Arrays;

public class BestTimeToBuyStockII {
    //Leetcode 122
    /*You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you
buy again).

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions
at the same time.
You must sell before buying again.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e., max profit = 0.*/

    //Dynamic programming - Top Down approach
    /*
    Time Complexity: O(N*2)
Reason: There are N*2 states therefore at max ‘N*2’ new problems will be solved and we are
running a for loop for ‘N’ times to calculate the total sum

Space Complexity: O(N*2) + O(N)
Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).
    */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        int[][] dp = new int[len + 1][2];
        for (int i = 0; i < len; i++)
            Arrays.fill(dp[i], -1);
        return maxProfitHelper(prices, 0, 1, dp);
    }

    // type = 1 = Buy, 0 = Sell
    public int maxProfitHelper(int[] prices, int index, int type, int[][] dp) {
        if (index == prices.length) {
            return 0;
        }

        if (dp[index][type] != -1) return dp[index][type];

        int maxProfit;
        if (type == 1) {
            //Buy
            int buy = -prices[index] + maxProfitHelper(prices, index + 1, 0, dp);
            int notBuy = maxProfitHelper(prices, index + 1, 1, dp);
            maxProfit = Math.max(buy, notBuy);
        } else {
            //Sell
            int sell = prices[index] + maxProfitHelper(prices, index + 1, 1, dp);
            int notSell = maxProfitHelper(prices, index + 1, 0, dp);
            maxProfit = Math.max(sell, notSell);
        }

        return dp[index][type] = maxProfit;
    }

    //Dynamic programming - Tabulation approach
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        int[][] dp = new int[len + 1][2];

        dp[len][0] = 0;
        dp[len][1] = 0;

        int profit;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                if (j == 0) {
                    //Buy
                    int buy = -prices[i] + dp[i + 1][1];
                    int notBuy = dp[i + 1][0];
                    profit = Math.max(buy, notBuy);
                } else {
                    //Sell
                    int sell = prices[i] + dp[i + 1][0];
                    int notSell = dp[i + 1][1];
                    profit = Math.max(sell, notSell);
                }

                dp[i][j] = profit;
            }
        }

        return dp[0][0];
    }

/*Time complexity : O(n). Single pass.
Space complexity : O(1). Constant space required.*/

    //Using Peak/Valley
    public int maxProfit3(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peek = prices[0];
        int maxprofit = 0;

        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];

            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peek = prices[i];

            maxprofit += peek - valley;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        BestTimeToBuyStockII stock = new BestTimeToBuyStockII();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(stock.maxProfit1(prices));
        System.out.println(stock.maxProfit2(prices));
        System.out.println(stock.maxProfit3(prices));
    }
}
