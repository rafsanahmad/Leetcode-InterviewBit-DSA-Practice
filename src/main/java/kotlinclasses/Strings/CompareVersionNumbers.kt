/*
 * *
 *  * Compare Version Numbers.kt
 *  * Created by Rafsan Ahmad on 9/24/25, 1:34AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class CompareVersionNumbers {
    //https://leetcode.com/problems/compare-version-numbers/description/
    /*Given two version strings, version1 and version2, compare them. A version string consists of
    revisions separated by dots '.'. The value of the revision is its integer conversion ignoring
    leading zeros.

To compare version strings, compare their revision values in left-to-right order. If one of the
version strings has fewer revisions, treat the missing revision values as 0.

Return the following:
If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.


Example 1:
Input: version1 = "1.2", version2 = "1.10"
Output: -1

Explanation:
version1's second revision is "2" and version2's second revision is "10": 2 < 10, so
version1 < version2.

Example 2:
Input: version1 = "1.01", version2 = "1.001"
Output: 0

Explanation:
Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

Example 3:
Input: version1 = "1.0", version2 = "1.0.0.0"
Output: 0

Explanation:
version1 has less revisions, which means every missing revision are treated as "0".

Constraints:
1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.*/

    fun compareVersion(version1: String, version2: String): Int {
        val v1 = version1.split(".").map { it.toInt() }
        val v2 = version2.split(".").map { it.toInt() }
        val maxLength = maxOf(v1.size, v2.size)
        for (i in 0 until maxLength) {
            val num1 = v1.getOrElse(i) { 0 }
            val num2 = v2.getOrElse(i) { 0 }
            if (num1 > num2) return 1
            if (num1 < num2) return -1
        }
        return 0
    }

    fun compareVersionOptimized(v1: String, v2: String): Int {
        var i = 0
        var j = 0
        val n1 = v1.length
        val n2 = v2.length

        while (i < n1 || j < n2) {
            var num1 = 0
            while (i < n1 && v1[i] != '.') {
                num1 = num1 * 10 + (v1[i] - '0')
                i++
            }

            var num2 = 0
            while (j < n2 && v2[j] != '.') {
                num2 = num2 * 10 + (v2[j] - '0')
                j++
            }

            if (num1 > num2) return 1
            if (num1 < num2) return -1

            i++ // skip '.'
            j++ // skip '.'
        }
        return 0
    }
}

fun main() {
    val obj = CompareVersionNumbers()
    println(obj.compareVersion("1.2", "1.10"))
    println(obj.compareVersion("1.01", "1.001"))
    println(obj.compareVersionOptimized("1.2", "1.10"))
    println(obj.compareVersionOptimized("1.01", "1.001"))
}