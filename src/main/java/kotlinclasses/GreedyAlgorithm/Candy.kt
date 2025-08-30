/*
 * *
 *  * Candy.kt
 *  * Created by Rafsan Ahmad on 8/27/25, 1:07PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.GreedyAlgorithm

import kotlin.math.max

class Candy {
    //https://leetcode.com/problems/candy/description/
    /*There are n children standing in a line. Each child is assigned a rating value given in the
    integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.


Constraints:
n == ratings.length
1 <= n <= 2 * 10^4
0 <= ratings[i] <= 2 * 10^4*/

    fun candy(ratings: IntArray): Int {
        if (ratings.isEmpty()) return 0
        val len = ratings.size
        val candies = IntArray(len) { 1 }
        var result = 0

        for (i in 1 until len) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1
            }
        }

        for (i in len - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                /*Why max instead of direct assignment?
Because candies[i] might have already been set larger in the first (left → right) pass.
If we blindly overwrite it, we could destroy that earlier constraint.*/
                candies[i] = max(candies[i], candies[i + 1] + 1)
            }
        }

        for (i in candies.indices) {
            result += candies[i]
        }

        return result
    }


    fun candyApproach2(ratings: IntArray): Int {
        val size = ratings.size
        if (size == 0) return 0

        val left2right = IntArray(size) { 1 }  // initialize with 1
        val right2left = IntArray(size) { 1 }  // initialize with 1
        var sum = 0

        // Left → Right pass
        for (i in 1 until size) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1
            }
        }

        // Right → Left pass
        for (i in size - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1
            }
        }

        // Take max from both passes
        for (i in 0 until size) {
            sum += maxOf(left2right[i], right2left[i])
        }

        return sum
    }
}

fun main() {
    val obj = Candy()
    println(obj.candy(intArrayOf(1, 2, 2)))
    println(obj.candyApproach2(intArrayOf(1, 2, 2)))
}