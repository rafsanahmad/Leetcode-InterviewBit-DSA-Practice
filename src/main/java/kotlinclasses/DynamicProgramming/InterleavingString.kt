/*
 * *
 *  * Interleaving String.kt
 *  * Created by Rafsan Ahmad on 8/23/25, 10:55PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

class InterleavingString {
    //https://leetcode.com/problems/interleaving-string/description/
    /*Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m
substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

Example 1:
a   a   b   c   c   d   b   b   c   a
|   |     \   \   /   /   /   /     /
|   |       \   \/   /   /   /     /
|   |         \ / \ /   /   /     /
|   |         / \ / \ /   /       /
|   |       /   / \ / \ /   \     /
|   |      /   /   /\  /\      \ /
|   |     /   /   /   /   \     / \
a   a   d   b   b   c   b   c   a   c

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

Example 3:
Input: s1 = "", s2 = "", s3 = ""
Output: true

Constraints:
0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.

Follow up: Could you solve it using only O(s2.length) additional memory space?*/

    /*What does dp[i][j] mean?
Here,
i = how many chars taken from s1
j = how many chars taken from s2
k = i + j = how many chars we have used in s3
So, dp[i][j] = whether s3[0..(i+j-1)] can be formed using first i chars of s1 and first j chars
of s2.*/

    //Time = O(len1 × len2)
    //Space = O(len1 × len2).
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        val len1 = s1.length
        val len2 = s2.length
        val len3 = s3.length
        if (len1 + len2 != len3) return false
        val memo = Array(len1 + 1) { Array<Boolean?>(len2 + 1) { null } }
        return isInterLeaveDP(0, 0, 0, s1, s2, s3, memo)
    }

    fun isInterLeaveDP(
        i: Int,
        j: Int,
        k: Int,
        s1: String,
        s2: String,
        s3: String,
        memo: Array<Array<Boolean?>>
    ): Boolean {
        if (i == s1.length && j == s2.length && k == s3.length) return true
        if (k >= s3.length) return false

        memo[i][j]?.let {
            return it
        }

        var result = false
        if (i < s1.length && s1[i] == s3[k]) {
            result = result || isInterLeaveDP(i + 1, j, k + 1, s1, s2, s3, memo)
        }
        if (j < s2.length && s2[j] == s3[k]) {
            result = result || isInterLeaveDP(i, j + 1, k + 1, s1, s2, s3, memo)
        }

        memo[i][j] = result
        return result
    }

    /*Why dp[0][0] = true?
dp[i][j] means:
👉 Can s3[0..(i+j-1)] be formed by first i chars of s1 and first j chars of s2?
dp[0][0] → use 0 chars from s1 and 0 chars from s2.
That forms the empty string.
And the empty string can always form the empty prefix of s3.
So we set dp[0][0] = true as the base case.*/

    fun isInterleaveBottomUp(s1: String, s2: String, s3: String): Boolean {
        val len1 = s1.length
        val len2 = s2.length
        val len3 = s3.length
        if (len1 + len2 != len3) return false
        val dp = Array(len1 + 1) { Array(len2 + 1) { false } }

        for (i in 0..len1) {
            for (j in 0..len2) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true
                    continue
                }
                if (i > 0 && s1[i - 1] == s3[i + j - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j]
                }
                if (j > 0 && s2[j - 1] == s3[i + j - 1]) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1]
                }
            }
        }
        return dp[len1][len2]
    }
}

fun main() {
    val obj = InterleavingString()
    //Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    println(obj.isInterleave("aabcc", "dbbca", "aadbbbaccc"))
    println(obj.isInterleaveBottomUp("aabcc", "dbbca", "aadbbbaccc"))
}