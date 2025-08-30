/*
 * *
 *  * Edit Distance.kt
 *  * Created by Rafsan Ahmad on 8/26/25, 5:39AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

class EditDistance {
    //https://leetcode.com/problems/edit-distance/description/
    /*Given two strings word1 and word2, return the minimum number of operations required to convert
    word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.*/

    /*What does dp[i][j] mean?
dp[i][j] = minimum edit distance to convert substring word1[i..] → word2[j..].
If i=0, j=0 → distance between the full strings.
If i=len1, j=2 → distance between "" and word2[2..] (just insert remaining).
If i=3, j=0 → distance between word1[3..] and full word2.
*/

    /*Intuition Behind DP
At any (i1, i2) we’re comparing word1[i1] and word2[i2]:
If they match → no operation needed, just skip both.
If they don’t match → we have three possible edits, and we choose the cheapest:

1. minDistanceHelper(i1, i2+1, ...) → Insert
We insert word2[i2] into word1.
Now the first character of word2 is matched (because we inserted it).
Word1 stays at i1 (unchanged), word2 advances to i2+1.

2. minDistanceHelper(i1+1, i2, ...) → Delete
We delete word1[i1].
Word1 moves forward to i1+1.
Word2 stays at i2 (we still need to match its current character).

3. minDistanceHelper(i1+1, i2+1, ...) → Replace
We replace word1[i1] with word2[i2].
Both characters are consumed.
Both indices move forward.

     */
    /*Time Complexity = O(len1 × len2)
Space Complexity = O(len1 × len2) for the DP memo table.
O(len1 + len2) recursion stack depth (worst case, if every call goes all the way).*/
    fun minDistance(word1: String, word2: String): Int {
        if (word1.isEmpty()) return word2.length
        if (word2.isEmpty()) return word1.length

        val len1 = word1.length
        val len2 = word2.length
        val dp: Array<IntArray> = Array(len1 + 1) { IntArray(len2 + 1) { -1 } }
        return minDistanceHelper(0, 0, word1, word2, dp)
    }

    fun minDistanceHelper(
        i1: Int, i2: Int, word1: String, word2: String, dp: Array<IntArray>
    ): Int {
        if (i1 >= word1.length) {
            return word2.length - i2
        }
        if (i2 >= word2.length) {
            return word1.length - i1
        }

        if (dp[i1][i2] != -1) return dp[i1][i2]

        //Word matched
        var distance = -1
        if (word1[i1] == word2[i2])
            distance = minDistanceHelper(i1 + 1, i2 + 1, word1, word2, dp)
        else {
            distance = 1 + minOf(
                minDistanceHelper(i1, i2 + 1, word1, word2, dp), //insert a char
                minDistanceHelper(i1 + 1, i2, word1, word2, dp), //delete a char
                minDistanceHelper(i1 + 1, i2 + 1, word1, word2, dp) //update a char
            )
        }

        dp[i1][i2] = distance
        return distance
    }

    fun minDistanceBottomUP(word1: String, word2: String): Int {
        if (word1.isEmpty()) return word2.length
        if (word2.isEmpty()) return word1.length

        val len1 = word1.length
        val len2 = word2.length

        // dp[i][j] = minimum edit distance between
        // first i chars of word1 and first j chars of word2
        val dp: Array<IntArray> = Array(len1 + 1) { IntArray(len2 + 1) { 0 } }

        // Base case: converting word1[0..i) to "" needs i deletions
        for (i in 0..len1) dp[i][0] = i

        // Base case: converting "" to word2[0..j) needs j insertions
        for (j in 0..len2) dp[0][j] = j

        for (i in 1..len1) {
            for (j in 1..len2) {
                if (word1[i - 1] == word2[j - 1]) {
                    // Characters match → carry over previous result
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    // Mismatch → take min of:
                    // delete (dp[i-1][j]), insert (dp[i][j-1]), replace (dp[i-1][j-1])
                    dp[i][j] = 1 + minOf(
                        dp[i - 1][j],     // delete
                        dp[i][j - 1],     // insert
                        dp[i - 1][j - 1]  // replace
                    )
                }
            }
        }

        // Final answer: distance between full word1 and full word2
        return dp[len1][len2]
    }
}

fun main() {
    val obj = EditDistance()
    //Input: word1 = "intention", word2 = "execution"
    println(obj.minDistance("intention", "execution"))
    println(obj.minDistanceBottomUP("intention", "execution"))
}