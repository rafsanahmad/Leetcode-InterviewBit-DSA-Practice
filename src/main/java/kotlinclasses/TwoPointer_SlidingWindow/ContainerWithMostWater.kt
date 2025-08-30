/*
 * *
 *  * Container With Most Water.kt
 *  * Created by Rafsan Ahmad on 8/2/25, 4:21AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

class ContainerWithMostWater {
    //https://leetcode.com/problems/container-with-most-water/description/
    //res/container_with_most_water.jpg
    /*You are given an integer array height of length n. There are n vertical lines drawn such that
     the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the
most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case,
the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1


Constraints:
n == height.length
2 <= n <= 10^5
0 <= height[i] <= 10^4*/

    /*At any step:
area = min(height[left], height[right]) * (right - left)
You're trying to maximize this area.
The width (right - left) will always shrink as you move either pointer.
The only hope of increasing area is if you can get a taller height, because width is going down.
*/
    //Time Complexity: O(n)
    fun maxArea(height: IntArray): Int {
        if (height.isEmpty()) return 0
        var result = Integer.MIN_VALUE
        var left = 0
        var right = height.size - 1

        while (left < right) {
            val w = right - left
            val h = minOf(height[left], height[right])
            val area = w * h
            result = maxOf(result, area)
            if (height[left] < height[right]) {
                left++ // Move left to try a taller line on the left
            } else {
                right-- // Move right to try a taller line on the right
            }
        }

        return result
    }
}

fun main() {
    val obj = ContainerWithMostWater()
    val arr = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
    println(obj.maxArea(arr))
}