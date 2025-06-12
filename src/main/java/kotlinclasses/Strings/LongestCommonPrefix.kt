/*
 * *
 *  * Longest Common Prefix.kt
 *  * Created by Rafsan Ahmad on 6/12/25, 11:48AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class LongestCommonPrefix {
    //https://leetcode.com/problems/longest-common-prefix/description/
    /*Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.


Constraints:
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters if it is non-empty.*/

    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""

        var result = StringBuilder()
        val first = strs[0]

        outer@
        for (i in 0..<first.length) {
            var j = 1
            while (j < strs.size) {
                val str = strs[j]
                if (i >= str.length || first[i] != str[i])
                    break@outer
                j++
            }

            result.append(first[i])
        }

        return result.toString()
    }
}

fun main() {
    val obj = LongestCommonPrefix()
    println(obj.longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    println(obj.longestCommonPrefix(arrayOf("dog", "racecar", "car")))
}