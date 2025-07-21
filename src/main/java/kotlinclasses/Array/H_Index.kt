/*
 * *
 *  * H-Index.kt
 *  * Created by Rafsan Ahmad on 7/18/25, 7:24PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Array

class H_Index {
    //https://leetcode.com/problems/h-index/description/
    /*Given an array of integers citations where citations[i] is the number of citations a
    researcher received for their ith paper, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value
of h such that the given researcher has published at least h papers that have each been cited
at least h times.


Example 1:
Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no
more than 3 citations each, their h-index is 3.


Example 2:
Input: citations = [1,3,1]
Output: 1

Constraints:
n == citations.length
1 <= n <= 5000
0 <= citations[i] <= 1000*/

    fun hIndex(citations: IntArray): Int {
        val citationCount = IntArray(1001) { 0 }

        for (i in 0..1000) {
            for (j in citations.indices) {
                if (citations[j] >= i) {
                    citationCount[i]++
                }
            }
        }

        for (i in 1000 downTo 0) {
            if (citationCount[i] >= i) {
                return i
            }
        }

        return 0
    }


    fun hIndexOptimized(citations: IntArray): Int {
        val n = citations.size
        val count = IntArray(n + 1) // count[i] = number of papers with i citations (capped at n)

        for (c in citations) {
            //All citations ≥ n are grouped into one bucket, count[n]++
            //the maximum possible h-index is n —> you can't have an h-index greater than
            // the number of papers you have!
            if (c >= n) count[n]++
            else count[c]++
        }

        var total = 0
        for (i in n downTo 0) {
            total += count[i]
            //Are there at least i papers that have ≥ i citations?
            if (total >= i) return i
        }

        return 0
    }
}

fun main() {
    val obj = H_Index()
    val arr = intArrayOf(3, 0, 6, 1, 5)
    println(obj.hIndex(arr))
    println(obj.hIndexOptimized(arr))
}