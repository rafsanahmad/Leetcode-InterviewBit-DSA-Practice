/*
 * *
 *  * TestClass.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:57PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Practice

import javaclasses.Tree.TreeNode
import java.util.ArrayDeque
import java.util.Arrays
import java.util.Queue
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
                    min = min(min, (abs((x1 - x2)) * abs((y1 - y2))))
                }
            }
        }
        return if (min == Int.MAX_VALUE) 0 else min
    }

    fun mergeAlternately(word1: String, word2: String): String {
        var len1 = word1.length
        var len2 = word2.length

        val builder = StringBuilder()
        var (i, j) = Pair(0, 0)
        while (len1 > 0 && len2 > 0) {
            builder.append(word1.get(i))
            builder.append(word2.get(j))
            i++
            j++
            len1--
            len2--
        }

        if (len1 > len2) {
            builder.append(word1.substring(i))
        } else {
            builder.append(word2.substring(j))
        }

        return builder.toString()
    }

    fun threeSum(nums: IntArray): List<List<Int>> {
        Arrays.sort(nums)
        val result: MutableList<List<Int>> = mutableListOf()

        for (i in nums.indices) {
            var left = i + 1
            var right = nums.size - 1

            if (i > 0 && nums[i] == nums[i - 1])
                continue

            while (left < right) {
                if (right < nums.size - 1 && nums[right] == nums[right + 1]) {
                    right--
                    continue
                }

                if (nums[i] + nums[left] + nums[right] == 0) {
                    result.add(listOf(nums[i], nums[left], nums[right]))
                    left++
                    right--
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++
                } else {
                    right--
                }
            }
        }

        return result
    }

    fun rightSideView(root: TreeNode?): List<Int> {
        val list: MutableList<Int> = ArrayList()

        if (root == null) return list

        val queue: Queue<TreeNode> = ArrayDeque()
        queue.add(root)

        while (!queue.isEmpty()) {
            val size = queue.size
            var i = 0

            while (i < size) {
                val curr = queue.poll()
                if (i == size - 1) list.add(curr.`val`)

                if (curr.left != null) queue.add(curr.left)
                if (curr.right != null) queue.add(curr.right)

                i++
            }
        }

        return list
    }

    fun countPalindromicSubsequence(s: String): Int {
        val first: IntArray = IntArray(26) { -1 }
        val last: IntArray = IntArray(26) { -1 }
        var ans = 0
        for (i in s.indices) {
            val index = s[i] - 'a'
            if (first[index] == -1) first[index] = i

            last[index] = i
        }

        for (i in 0..25) {
            if (first[i] == -1) continue
            val charBetween: HashSet<Char> = hashSetOf()

            for (j in first[i] + 1 until last[i]) {
                charBetween.add(s[j])
            }

            ans += charBetween.size
        }
        return ans
    }
}

fun main() {
    val testClass = TestClass()
    val nums1 = intArrayOf(3, 4, 5, 8)
    //println(testClass.largestInteger(nums1, 4))
    val points = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 3),
        intArrayOf(3, 1),
        intArrayOf(3, 3),
        intArrayOf(4, 1),
        intArrayOf(4, 3)
    )

    //println(testClass.minAreaRect(points))
    //println(testClass.mergeAlternately("abcd", "pq"))
    val list = intArrayOf(-1, 0, 1, 2, -1, -4)
    //println(testClass.threeSum(list))
    println(testClass.countPalindromicSubsequence("aabca"))
    println(testClass.countPalindromicSubsequence("bbcbaba"))
}