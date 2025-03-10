/*
 * *
 *  * TestClass.kt
 *  * Created by Rafsan Ahmad on 3/10/25, 1:54 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class TestClass {
    fun largestInteger(nums: IntArray, k: Int): Int {
        val freq = HashMap<Int, Int>()

        // Count frequency of each number
        for (num in nums) {
            freq[num] = freq.getOrDefault(num, 0) + 1
        }

        var ans = -1
        var uniqueLargest = -1
        var largest = -1

        // Find uniqueLargest and largest elements
        for (num in nums) {
            if (freq[num] == 1) {
                uniqueLargest = max(uniqueLargest.toDouble(), num.toDouble()).toInt()
            }
            largest = max(largest.toDouble(), num.toDouble()).toInt()
        }

        if (k == 1) return uniqueLargest
        if (k == nums.size) return largest

        // Check first and last elements
        if (freq[nums[0]] == 1) ans = max(ans.toDouble(), nums[0].toDouble()).toInt()
        if (freq[nums[nums.size - 1]] == 1) ans =
            max(ans.toDouble(), nums[nums.size - 1].toDouble()).toInt()

        return ans
    }

    fun minAreaRect(points: Array<IntArray>): Int {
        val map: MutableMap<Int, MutableSet<Int>> = HashMap()
        for (p in points) {
            if (!map.containsKey(p[0])) {
                map[p[0]] = HashSet()
            }
            map[p[0]]!!.add(p[1])
        }
        var min = Int.MAX_VALUE

        for (p1 in points) {
            for (p2 in points) {
                val x1 = p1[0]
                val y1 = p1[1] // Coordinates of Point A

                val x2 = p2[0]
                val y2 = p2[1] // Coordinates of Point B

                if (x1 == x2 || y1 == y2) {
                    continue
                }
                if (map[x1]!!.contains(y2) && map[x2]!!.contains(y1)) { // find other two points
                    min = min(
                        min.toDouble(),
                        (abs((x1 - x2).toDouble()) * abs((y1 - y2).toDouble())).toDouble()
                    ).toInt()
                }
            }
        }
        return if (min == Int.MAX_VALUE) 0 else min
    }
}

fun main() {
    val testClass = TestClass()
    val nums1 = intArrayOf(3, 4, 5, 8)
    println(testClass.largestInteger(nums1, 4))
    //System.out.println(solution.largestInteger(nums1, 4));
    val points = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 3),
        intArrayOf(3, 1),
        intArrayOf(3, 3),
        intArrayOf(4, 1),
        intArrayOf(4, 3)
    )

    println(testClass.minAreaRect(points))
}