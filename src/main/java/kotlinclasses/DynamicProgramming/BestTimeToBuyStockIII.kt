/*
 * *
 *  * Best Time to Buy and Sell Stock III.kt
 *  * Created by Rafsan Ahmad on 8/31/25, 12:16AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.max

class BestTimeToBuyStockIII {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
    /*You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before
you buy again).


Example 1:
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


Constraints:
1 <= prices.length <= 10^5
0 <= prices[i] <= 10^5
*/

    /*DP State Meaning

In this solution, the DP table dp[i][type][cap] represents the maximum profit we can achieve
starting from day i with:
type: The state of the transaction (whether we're in a "buy" state or "sell" state).
type = 0: We are currently considering buying the stock.
type = 1: We are currently considering selling the stock.
cap: The number of remaining transactions we can make (either 0, 1, or 2).
cap = 0: No remaining transactions left (this is a terminal state).
cap = 1: One more transaction remaining.
cap = 2: Two more transactions remaining (this is the initial state).

So the DP state dp[i][type][cap] answers the question:
"What is the maximum profit we can make starting from day i with cap transactions left and
currently in a type state (either 'buy' or 'sell')?"*/

    //time complexity is:
    //O(size), where size is the number of days in the prices array.
    //total space complexity is:
    //O(size) (for the dp table) + O(size) (for the recursion stack), which simplifies to O(size).
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val size = prices.size
        val dp: Array<Array<IntArray>> = Array(size) { Array(2) { IntArray(3) { -1 } } }
        val cap = 2

        //0--Buy, 1--Sell
        fun maxProfitDP(i: Int, cap: Int, type: Int, dp: Array<Array<IntArray>>): Int {
            if (i >= size || cap == 0) return 0

            if (dp[i][type][cap] != -1)
                return dp[i][type][cap]

            if (type == 0) {
                //Buy
                val buy = -prices[i] + maxProfitDP(i + 1, cap, 1, dp)
                val notBuy = maxProfitDP(i + 1, cap, 0, dp)

                dp[i][type][cap] = max(buy, notBuy)
            } else {
                //Sell
                val sell = prices[i] + maxProfitDP(i + 1, cap - 1, 0, dp)
                val notSell = maxProfitDP(i + 1, cap, 1, dp)

                dp[i][type][cap] = max(sell, notSell)
            }

            return dp[i][type][cap]

        }

        return maxProfitDP(0, cap, 0, dp)
    }


    fun maxProfitBottomUp(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val size = prices.size
        // dp[day][type][transactions left] -> maximum profit at the given state
        val dp = Array(size + 1) { Array(2) { IntArray(3) { 0 } } }

        val cap = 2  // Maximum allowed transactions

        // Initialize dp[size][0][k] = 0 and dp[size][1][k] = 0 for all k, since no profit
        // can be made after the last day
        for (k in 0..cap) {
            dp[size][0][k] = 0
            dp[size][1][k] = 0
        }

        // Bottom-up DP, iterating from day size-1 to day 0 (not 0..size)
        for (i in size - 1 downTo 0) {
            for (k in 1..cap) {
                // If we're in the "buy" state (type = 0), we have two choices:
                dp[i][0][k] = max(
                    -prices[i] + dp[i + 1][1][k],  // Buy today and move to "sell"
                    dp[i + 1][0][k]                // Skip today and stay in "buy"
                )

                // If we're in the "sell" state (type = 1), we have two choices:
                dp[i][1][k] = max(
                    prices[i] + dp[i + 1][0][k - 1],  // Sell today and reduce the transaction count
                    dp[i + 1][1][k]                  // Skip today and stay in "sell"
                )
            }
        }

        // Return the maximum profit starting from day 0, with the first transaction as "buy" (0)
        // and 2 allowed transactions
        return dp[0][0][cap]
    }
}

fun main() {
    val obj = BestTimeToBuyStockIII()
    println(obj.maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4)))
    println(obj.maxProfitBottomUp(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4)))
}