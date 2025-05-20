/*
 * *
 *  * Best Time to Buy and Sell Stock II.kt
 *  * Created by Rafsan Ahmad on 5/20/25, 11:27PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.max

class BestTimeToBuySellStockII {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    /*You are given an integer array prices where prices[i] is the price of a given stock on the
    ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of
the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the
maximum profit of 0.


Constraints:

1 <= prices.length <= 3 * 10^4
0 <= prices[i] <= 10^4*/

    fun maxProfit(prices: IntArray): Int {
        val len = prices.size
        if (len <= 1) return 0
        val dp = Array(len) { IntArray(2) { -1 } }

        //Type=1-Buy, Type=0-Sell
        return maxProfitHelper(prices, 0, 1, dp)
    }

    //Bottom up approach
    fun maxProfitHelper(prices: IntArray, index: Int, type: Int, dp: Array<IntArray>): Int {
        if (index == prices.size) return 0
        if (dp[index][type] != -1) return dp[index][type]

        var maxProfit = 0
        //Buy
        if (type == 1) {
            val buy = -prices[index] + maxProfitHelper(prices, index + 1, 0, dp)
            val notBuy = maxProfitHelper(prices, index + 1, 1, dp)
            maxProfit = max(buy, notBuy)
        } else {
            val sell = prices[index] + maxProfitHelper(prices, index + 1, 1, dp)
            val notSell = maxProfitHelper(prices, index + 1, 0, dp)
            maxProfit = max(sell, notSell)
        }
        dp[index][type] = maxProfit
        return maxProfit
    }

    //Top down - Tabulation approach
    fun maxProfitTopDown(prices: IntArray): Int {
        val len = prices.size
        if (len <= 1) return 0

        val dp = Array(len + 1) { IntArray(2) }

        // 0 → holding/buy mode, 1 → not holding/sell mode
        dp[0][0] = -prices[0] // Bought on day 0
        dp[0][1] = 0          // No transaction, no profit

        for (i in 1..len) {
            val price = prices[i - 1]

            // Holding stock (i.e., buy mode)
            dp[i][0] = max(
                dp[i - 1][0],      // continue holding
                dp[i - 1][1] - price // buy today
            )

            // Not holding stock (i.e., sell mode)
            dp[i][1] = max(
                dp[i - 1][1],      // do nothing
                dp[i - 1][0] + price // sell today
            )
        }

        return dp[len][1] // Max profit at end after selling
    }
}

fun main(args: Array<String>) {
    val bestTimeToBuyStockII = BestTimeToBuySellStockII()
    val prices = intArrayOf(7, 1, 5, 3, 6, 4)
    println(bestTimeToBuyStockII.maxProfit(prices))
    println(bestTimeToBuyStockII.maxProfitTopDown(prices))
}