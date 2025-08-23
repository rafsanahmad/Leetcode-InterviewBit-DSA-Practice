/*
 * *
 *  * IPO.kt
 *  * Created by Rafsan Ahmad on 8/21/25, 4:03AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HeapPriorityQueue

import java.util.PriorityQueue

class IPO {
    //https://leetcode.com/problems/ipo/description/
    /*Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture
    Capital, LeetCode would like to work on some projects to increase its capital before the IPO.
    Since it has limited resources, it can only finish at most k distinct projects before the IPO.
    Help LeetCode design the best way to maximize its total capital after finishing at most k distinct
    projects.

You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of
capital[i] is needed to start it.

Initially, you have w capital. When you finish a project, you will obtain its pure profit and the
profit will be added to your total capital.

Pick a list of at most k distinct projects from given projects to maximize your final capital, and
return the final maximized capital.

The answer is guaranteed to fit in a 32-bit signed integer.


Example 1:
Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum
capital. Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.

Example 2:
Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6


Constraints:
1 <= k <= 10^5
0 <= w <= 10^9
n == profits.length
n == capital.length
1 <= n <= 10^5
0 <= profits[i] <= 10^4
0 <= capital[i] <= 10^9*/

    /*Time Complexity:
Inserting all projects into minCapital → O(n log n)
Each project moves at most once into maxProfit → O(n log n)
We perform at most k polls from maxProfit. Each poll → O(log n)
So overall:
O(n log n + k log n) = O((n + k) log n)

Space Complexity:
Two heaps store up to n projects each → O(n)
Extra variables = O(1)
So total = O(n) space.
*/
    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        if (profits.isEmpty() || capital.isEmpty())
            return 0

        // max-heap based on profit (highest profit first)
        val maxProfit = PriorityQueue<IntArray> { a, b -> b[1] - a[1] }

        // min-heap based on capital (lowest capital required first)
        val minCapital = PriorityQueue<IntArray> { a, b -> a[0] - b[0] }

        // load all projects into minCapital: [capitalNeeded, profit]
        for (i in capital.indices) {
            minCapital.add(intArrayOf(capital[i], profits[i]))
        }

        var maxCapital = w  // current available capital
        var total = k       // max number of projects we can pick

        while (total > 0) {
            // Move all projects that we can afford (capital ≤ maxCapital) into maxProfit
            while (!minCapital.isEmpty() && minCapital.peek()[0] <= maxCapital) {
                maxProfit.add(minCapital.poll())
            }

            // If no affordable project → break
            if (maxProfit.isEmpty()) break

            // Pick the project with highest profit
            maxCapital += maxProfit.poll()[1]
            total--
        }

        return maxCapital
    }
}

fun main() {
    val obj = IPO()
    val profits = intArrayOf(1, 2, 3)
    val capital = intArrayOf(0, 1, 2)
    println(obj.findMaximizedCapital(3, 0, profits, capital))
}
