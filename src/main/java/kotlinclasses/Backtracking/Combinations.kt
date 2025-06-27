/*
 * *
 *  * Combinations.kt
 *  * Created by Rafsan Ahmad on 6/27/25, 7:48PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Backtracking

class Combinations {
    //https://leetcode.com/problems/combinations/description/
    /*Given two integers n and k, return all possible combinations of k numbers chosen
    from the range [1, n].

You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same
combination.

Example 2:
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.


Constraints:
1 <= n <= 20
1 <= k <= n*/

    /*
    Time Complexity:
= O((n! / (k! * (n - k)!)) * k)

Space Complexity:
= O((n! / (k! * (n - k)!)) * k)
    */
    fun combine(n: Int, k: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        val list = mutableListOf<Int>()
        combineHelper(n, k, 1, list, result)
        return result
    }

    fun combineHelper(
        n: Int,
        k: Int,
        index: Int,
        list: MutableList<Int>,
        result: MutableList<List<Int>>
    ) {
        if (list.size == k) {
            result.add(ArrayList(list))
            return
        }

        for (i in index..n) {
            list.add(i)
            combineHelper(n, k, i + 1, list, result)
            list.removeAt(list.size - 1)
        }
    }
}

fun main() {
    val obj = Combinations()
    println(obj.combine(4, 2))
}