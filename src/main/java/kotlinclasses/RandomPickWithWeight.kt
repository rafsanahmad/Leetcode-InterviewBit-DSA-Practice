package kotlinclasses

import java.util.Arrays


//Leetcode 528
/*You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].
pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3],
the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).

More formally, the probability of picking index i is w[i] / sum(w).

Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.*/


class RandomPickWithWeight {

    lateinit var arr: IntArray
    var counter = 0

    fun RandomPickWithWeight(w: IntArray) {
        arr = IntArray(w.size)
        counter += arr[0]
        arr[0] = w[0]
        for (i in 1..w.size) {
            arr[i] = arr[i - 1] + w[i]
            counter += w[i]
        }
    }

    fun pickIndex(): Int {
        var idx = (1..this.counter).random()
        var res = Arrays.binarySearch(arr, idx)
        return res
    }

}