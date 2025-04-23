/*
 * *
 *  * Random Pick With Weight.kt
 *  * Created by Rafsan Ahmad on 4/23/25, 3:40PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Math

import kotlinclasses.Math.RandomPickWithWeight.Solution
import kotlin.random.Random

class RandomPickWithWeight {
    /*You are given a 0-indexed array of positive integers w where w[i] describes the weight of
    the ith index.

You need to implement the function pickIndex(), which randomly picks an index in the range
[0, w.length - 1] (inclusive) and returns it. The probability of picking an index
i is w[i] / sum(w).

For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25
(i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).


Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.


Constraints:

1 <= w.length <= 10^4
1 <= w[i] <= 10^5
pickIndex will be called at most 10^4 times.*/

    /*
 Steps:
 1.Use cumulative weights as boundaries.
 2.Pick a random number in the total range.
 3.Use binary search to find which weight bucket it lands in.
 4.Bucket size = weight ⇒ higher weight = higher chance.

Use accumulated freq array to get idx.
w[] = {2,5,3,4} => prefixSum[] = {2,7,10,14}
then get random val random.nextInt(14)+1, idx is in range [1,14]

idx in [1,2] return 0
idx in [3,7] return 1
idx in [8,10] return 2
idx in [11,14] return 3*/
    class Solution(w: IntArray) {
        private var prefixSum: IntArray

        init {
            for (i in 1..<w.size) w[i] += w[i - 1]
            this.prefixSum = w
        }

        fun pickIndex(): Int {
            val total = prefixSum.last()
            //Pick random number between 1 & total(exclusive, so added +1)
            val idx = Random.nextInt(1, total + 1)
            val len = prefixSum.size
            var left = 0
            var right = len - 1
            // search position
            while (left < right) {
                val mid = left + (right - left) / 2
                if (prefixSum[mid] == idx) return mid
                else if (prefixSum[mid] < idx) left = mid + 1
                else right = mid
            }
            return left
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * var obj = Solution(w)
     * var param_1 = obj.pickIndex()
     */

}

fun main(args: Array<String>) {
    val solution = Solution(intArrayOf(2, 5, 3, 4))
    println(solution.pickIndex())
    println(solution.pickIndex())
    println(solution.pickIndex())
    println(solution.pickIndex())
    println(solution.pickIndex())
}