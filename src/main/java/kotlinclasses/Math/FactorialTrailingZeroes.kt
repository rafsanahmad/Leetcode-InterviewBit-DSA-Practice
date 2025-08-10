/*
 * *
 *  * Factorial Trailing Zeroes.kt
 *  * Created by Rafsan Ahmad on 8/8/25, 8:29PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Math

class FactorialTrailingZeroes {
    //https://leetcode.com/problems/factorial-trailing-zeroes/description/
    /*Given an integer n, return the number of trailing zeroes in n!.

Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

Example 1:
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Example 3:
Input: n = 0
Output: 0


Constraints:
0 <= n <= 10^4

Follow up: Could you write a solution that works in logarithmic time complexity?*/

    /*Why choose 5:
Trailing zeros come from 10 = 2 × 5.
In n!, there are always more 2’s than 5’s,
so the number of 5’s determines the number of zeros.

Why count += num / divisor:
num / divisor counts how many numbers are divisible by the current power of 5.
Each such number contributes at least one factor of 5 to n!.

Why divisor *= 5:
Some numbers have more than one 5 (e.g., 25 = 5×5, 125 = 5×5×5).
Multiplying divisor by 5 lets us count extra 5’s from those higher powers.

Example: n = 100
divisor = 5   → 100 / 5   = 20 → count = 20
divisor = 25  → 100 / 25  = 4  → count = 24
divisor = 125 → 100 / 125 = 0  → stop
Result: 24 trailing zeros
*/

    //Time: O(log₅ n) → O(log n)
    //Space: O(1)
    fun trailingZeroes(n: Int): Int {
        if (n == 0) return 0
        val num = n
        var count = 0
        var divisor = 5
        while (num / divisor >= 1) {
            count += num / divisor
            divisor = divisor * 5
        }

        return count
    }
}

fun main() {
    val obj = FactorialTrailingZeroes()
    println(obj.trailingZeroes(100))
}