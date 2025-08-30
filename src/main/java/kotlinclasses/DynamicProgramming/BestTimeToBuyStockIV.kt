/*
 * *
 *  * Best Time to Buy and Sell Stock IV.kt
 *  * Created by Rafsan Ahmad on 8/31/25, 12:47AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.max

class BestTimeToBuyStockIV {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description
    /*You are given an integer array prices where prices[i] is the price of a given stock on the
    ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy
at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock
before you buy again).

Example 1:
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.


Constraints:
1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000*/

    //time complexity is:
    //O(size), where size is the number of days in the prices array.
    //total space complexity is:
    //O(size) (for the dp table) + O(size) (for the recursion stack), which simplifies to O(size).
    fun maxProfit(k: Int, prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val size = prices.size
        val dp: Array<Array<IntArray>> = Array(size) { Array(2) { IntArray(k + 1) { -1 } } }

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

        return maxProfitDP(0, k, 0, dp)
    }


    fun maxProfitBottomUP(k: Int, prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val size = prices.size
        val dp: Array<Array<IntArray>> = Array(size + 1) {
            Array(2) { IntArray(k + 1) { 0 } }
        }

        //i == size is the base case - no profit for last day - 0
        //Starts from size-1
        for (i in size - 1 downTo 0) {
            for (j in 1..k) {
                //0 -> Buy
                val buy = -prices[i] + dp[i + 1][1][j]
                val notBuy = dp[i + 1][0][j]
                dp[i][0][j] = max(buy, notBuy)

                //1 -> Sell
                //reduce cap - j-1
                val sell = prices[i] + dp[i + 1][0][j - 1]
                val notSell = dp[i + 1][1][j]
                dp[i][1][j] = max(sell, notSell)

            }
        }

        return dp[0][0][k]
    }
}

fun main() {
    val obj = BestTimeToBuyStockIV()
    val arr = intArrayOf(3, 2, 6, 5, 0, 3)
    println(obj.maxProfit(2, arr))
    println(obj.maxProfitBottomUP(2, arr))
}