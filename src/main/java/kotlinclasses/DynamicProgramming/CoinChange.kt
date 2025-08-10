/*
 * *
 *  * Coin Change.kt
 *  * Created by Rafsan Ahmad on 8/10/25, 6:29PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.min

class CoinChange {
    //https://leetcode.com/problems/coin-change/description/
    /*You are given an integer array coins representing coins of different denominations and an
    integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money
cannot be made up by any combination of the coins, return -1.

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

    /*Recursion Tree for coinChangeDP(coins, 3), coins = [1,2,5]
coinChangeDP(3) // amount = 3
 ├─ coin = 1 → coinChangeDP(2) // amount = 2
 │    ├─ coin = 1 → coinChangeDP(1) // amount = 1
 │    │    ├─ coin = 1 → coinChangeDP(0) // amount = 0 → return 0 coins
 │    │    │                 ↑ backtrack: 0 + 1 = 1 coin
 │    │    ├─ coin = 2 → coinChangeDP(-1) // amount = -1 → return -1 (impossible)
 │    │    └─ coin = 5 → coinChangeDP(-4) // amount = -4 → return -1 (impossible)
 │    │    → min coins for amount = 1 is 1
 │    ├─ coin = 2 → coinChangeDP(0) // amount = 0 → return 0 coins → backtrack: +1 = 1 coin
 │    └─ coin = 5 → coinChangeDP(-3) // amount = -3 → return -1 (impossible)
 │    → min coins for amount = 2 is 1
 │    ↑ backtrack: 1 + 1 = 2 coins for amount = 3 so far
 │
 ├─ coin = 2 → coinChangeDP(1) // amount = 1
 │    ├─ coin = 1 → coinChangeDP(0) // amount = 0 → return 0 coins → backtrack: +1 = 1 coin
 │    ├─ coin = 2 → coinChangeDP(-1) // amount = -1 → return -1 (impossible)
 │    └─ coin = 5 → coinChangeDP(-4) // amount = -4 → return -1 (impossible)
 │    → min coins for amount = 1 is 1
 │    ↑ backtrack: 1 + 1 = 2 coins for amount = 3
 │
 └─ coin = 5 → coinChangeDP(-2) // amount = -2 → return -1 (impossible)*/

    //Time complexity = O(amount × n), where n is the number of coins.
    //Space complexity = O(amount),
    // dp[amount] = min coins to make amount.
    fun coinChangeTopDown(coins: IntArray, amount: Int): Int {
        if (coins.isEmpty() || amount == 0) return 0
        val dp = IntArray(amount + 1) { Integer.MAX_VALUE }
        return coinChangeDP(coins, amount, dp)
    }

    fun coinChangeDP(coins: IntArray, amount: Int, dp: IntArray): Int {
        if (amount == 0) {
            return 0
        }

        if (amount < 0) return -1

        if (dp[amount] != Integer.MAX_VALUE) return dp[amount]

        var result = Integer.MAX_VALUE
        for (coin in coins) {
            val coinCount = coinChangeDP(coins, amount - coin, dp)
            if (coinCount != -1) {
                result = min(result, coinCount + 1)
            }
        }
        dp[amount] = if (result == Integer.MAX_VALUE) -1 else result
        return dp[amount]
    }

    //Time complexity = O(amount × n), where n is the number of coins.
    //Space complexity = O(amount),
    fun coinChange(coins: IntArray, amount: Int): Int {
        if (coins.isEmpty() || amount == 0) return 0
        // dp[i] = min coins to make amount i
        val dp = IntArray(amount + 1) { Integer.MAX_VALUE }
        dp[0] = 0 // Base case: 0 coins needed to make amount 0

        // Compute min coins required for all amounts from 1 to amount
        for (i in 1..amount) {
            for (coin in coins) {
                // If coin can be used (doesn't make amount negative) and previous state is reachable
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    // Update dp[i] with min coins by using this coin
                    dp[i] = min(dp[i], dp[i - coin] + 1)
                }
            }
        }

        // If dp[amount] is still Integer.MAX_VALUE, amount can't be formed
        return if (dp[amount] == Integer.MAX_VALUE) -1 else dp[amount]
    }
}

fun main() {
    val obj = CoinChange()
    val coins = intArrayOf(1, 2, 5)
    println(obj.coinChangeTopDown(coins, 11))
    println(obj.coinChange(coins, 11))
}