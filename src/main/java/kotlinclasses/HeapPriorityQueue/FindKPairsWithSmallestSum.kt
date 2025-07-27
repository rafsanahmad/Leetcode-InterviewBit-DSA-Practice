/*
 * *
 *  * Find K Pairs with Smallest Sums.kt
 *  * Created by Rafsan Ahmad on 7/26/25, 3:00PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HeapPriorityQueue

import java.util.PriorityQueue

class FindKPairsWithSmallestSum {
    //https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
    /*You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer
    k.

Define a pair (u, v) which consists of one element from the first array and one element from the
second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.



Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Example 2:
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]


Constraints:
1 <= nums1.length, nums2.length <= 10^5
-10^9 <= nums1[i], nums2[i] <= 10^9
nums1 and nums2 both are sorted in non-decreasing order.
1 <= k <= 10^4
k <= nums1.length * nums2.length*/

    /* Summary of Key Logic:
Use a min-heap to always extract the pair with the smallest sum.
Start with all nums1[i] paired with nums2[0], then explore nums2[1], nums2[2]... as needed.
This approach avoids generating all combinations, making it efficient in time and memory.
*/

    /*Time Complexity:
Initial heap creation: O(n1) where n1 = nums1.size
In the while loop:
Each heap operation (poll + add) takes O(log n1)
We do this at most k times
Total Time Complexity: O(n1 + k log n1)
(Assuming nums1 has fewer or equal elements compared to nums2)

Space Complexity:
Heap stores up to n1 elements → O(n1)
Result list stores up to k pairs → O(k)
Total Space Complexity:O(n1 + k)
*/
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        // Edge case: if any array is empty or k is 0, return an empty list
        if (nums1.isEmpty() || nums2.isEmpty() || k == 0) return emptyList()

        val result: MutableList<List<Int>> = mutableListOf()

        // Min-heap to store pairs (i, j) based on the sum nums1[i] + nums2[j]
        val pq = PriorityQueue<Pair<Int, Int>> { a, b ->
            val sum1 = nums1[a.first] + nums2[a.second]
            val sum2 = nums1[b.first] + nums2[b.second]
            sum1.compareTo(sum2) // Compare by sum to maintain min-heap
        }

        // Initialize heap with all pairs (i, 0), meaning nums1[i] paired with nums2[0]
        // This fixes nums2[0] and starts exploring all combinations with nums1
        for (i in nums1.indices) {
            pq.add(Pair(i, 0))
        }

        // Extract k smallest sum pairs
        while (result.size < k && pq.isNotEmpty()) {
            val (i, j) = pq.poll()  // Get the current smallest sum pair
            result.add(listOf(nums1[i], nums2[j]))  // Add the actual values to result

            // If possible, push the next pair (i, j+1) to explore nums2 further for current nums1[i]
            if (j + 1 < nums2.size) {
                pq.add(Pair(i, j + 1))
            }
        }

        return result
    }
}

fun main() {
    val obj = FindKPairsWithSmallestSum()
    val nums1 = intArrayOf(1, 2, 4)
    val nums2 = intArrayOf(3, 5, 7)
    val k = 3

    println(obj.kSmallestPairs(nums1, nums2, k))
}