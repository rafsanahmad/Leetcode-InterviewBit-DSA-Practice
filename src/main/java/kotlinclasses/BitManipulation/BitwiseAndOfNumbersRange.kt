/*
 * *
 *  * Bitwise AND of Numbers Range.kt
 *  * Created by Rafsan Ahmad on 8/19/25, 3:21AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BitManipulation

class BitwiseAndOfNumbersRange {
    //https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
    /*Given two integers left and right that represent the range [left, right], return the bitwise
    AND of all numbers in this range, inclusive.

Example 1:
5 = 101 (binary)
6 = 110
7 = 111
------------
AND = 100 (which is 4)

Input: left = 5, right = 7
Output: 4

Example 2:
Input: left = 0, right = 0
Output: 0

Example 3:
Input: left = 1, right = 2147483647
Output: 0


Constraints:
0 <= left <= right <= 2^31 - 1*/

    /*Intuition
👉 The idea:

The only bits that survive the AND across the entire range [left, right] are the common left prefix
bits.
Any bit that differs between left and right (at some position) will eventually flip in the range,
so that bit becomes 0.

So we keep shifting both left and right rightwards until they become equal.
That equal number is the common prefix.
Then we shift it back left to its correct place.

🔹 Walkthrough with left = 5, right = 7

Initial:
m = 5 (101), n = 7 (111), count = 0

First iteration:
m = 5 >> 1 = 10 (2)
n = 7 >> 1 = 11 (3)
count = 1

Second iteration:
m = 2 >> 1 = 1
n = 3 >> 1 = 1
count = 2

Now m == n == 1. Stop loop.
This 1 is the common prefix.

Shift back:
result = m << count = 1 << 2 = 100 (binary) = 4

🔹 Why don’t we check all values in the range?

Because the difference between left and right tells us which bits will flip:
In [5, 7], the last 2 bits are not stable (they change across 5, 6, 7).
Only the highest bit (100) is the same in both ends → so the AND result is 100.
Shifting right until m == n is basically chopping off the unstable (changing) bits.

⚡ That’s why the algorithm is much faster:
Instead of iterating through the whole range (O(n)),
It just shifts until equal (O(log n)).*/

    /*Time Complexity
In each iteration we right shift both m and n.
Loop continues until m == n.
Maximum number of shifts = number of bits in Int (at most 32).
So time complexity = O(log(max(n, m))), bounded by O(32) = O(1) for fixed-size integers.
space complexity = O(1).
*/
    fun rangeBitwiseAnd(left: Int, right: Int): Int {
        var m = left
        var n = right
        var result = 0
        var count = 0
        while (m != n) {
            m = m shr 1
            n = n shr 1
            count++
        }
        result = m shl count
        return result
    }
}

fun main() {
    val obj = BitwiseAndOfNumbersRange()
    val left = 5
    val right = 7
    println(obj.rangeBitwiseAnd(left, right))
}