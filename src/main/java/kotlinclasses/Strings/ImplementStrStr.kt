/*
 * *
 *  * Find the Index of the First Occurrence in a String.kt
 *  * Created by Rafsan Ahmad on 6/23/25, 1:41PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class ImplementStrStr {
    //https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
    /*Given two strings needle and haystack, return the index of the first occurrence of needle
    in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.

Example 2:
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.


Constraints:
1 <= haystack.length, needle.length <= 10^4
haystack and needle consist of only lowercase English characters.*/

    fun strStr(haystack: String, needle: String): Int {
        val hLen = haystack.length
        val nLen = needle.length

        if (nLen == 0) return 0
        if (nLen > hLen) return -1

        var i1 = 0   // index in haystack
        var i2 = 0   // index in needle
        var result = -1

        while (i1 < hLen) {
            if (haystack[i1] == needle[i2]) {
                if (i2 == 0) {
                    result = i1  // potential match start
                }
                i2++
                if (i2 == nLen) return result  // full match
            } else {
                if (i2 > 0) {
                    // mismatch after some matches, backtrack
                    i1 = result  // go back to one after start
                }
                i2 = 0
                result = -1
            }
            i1++
        }

        return -1
    }
}

fun main() {
    val obj = ImplementStrStr()
    println(obj.strStr("sadbutsad", "sad"))
    println(obj.strStr("leetcode", "leeto"))
}