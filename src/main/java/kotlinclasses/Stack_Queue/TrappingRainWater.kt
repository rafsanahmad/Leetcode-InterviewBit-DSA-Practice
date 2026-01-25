/*
 *
 *  * TrappingRainWater.kt
 *  *
 *  * Created by Rafsan Ahmad on 01/24/26, 1:56 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.Stack_Queue

class TrappingRainWater {
    //https://leetcode.com/problems/trapping-rain-water/description/
    /*Given n non-negative integers representing an elevation map where the width of each bar is 1,
     compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1
Output: 6
Explanation: The above elevation map (black section) is represented by array
[0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:
n == height.length
1 <= n <= 2 * 10^4
0 <= height[i] <= 10^5*/

    /*Core Idea (Monotonic Decreasing Stack)
We maintain a stack of indices such that:
height[stack] is decreasing

When we find a bar higher than stack top, we’ve found a right boundary for trapped water.

Geometry
LEFT ---- MID ---- RIGHT
MID → valley bottom (popped from stack)
LEFT → new stack top after pop
RIGHT → current index i

Water trapped:
height = min(height[LEFT], height[RIGHT]) - height[MID]
width  = RIGHT - LEFT - 1
water  = height * width
Complexity:
Time	O(n)
Space	O(n)
*/
    fun trap(height: IntArray): Int {
        val stack = ArrayDeque<Int>() // stores indices
        var water = 0

        for (i in height.indices) {
            while (stack.isNotEmpty() && height[i] > height[stack.last()]) {
                val mid = stack.removeLast()

                // No left boundary
                if (stack.isEmpty()) break

                val left = stack.last()
                val width = i - left - 1
                val boundedHeight =
                    minOf(height[left], height[i]) - height[mid]

                water += width * boundedHeight
            }
            stack.addLast(i)
        }

        return water
    }
}

fun main() {
    val obj = TrappingRainWater()
    val height = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
    println(obj.trap(height)) // Output: 6
}