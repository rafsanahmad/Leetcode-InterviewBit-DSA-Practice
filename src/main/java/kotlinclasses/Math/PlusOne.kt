/*
 * *
 *  * Plus One.kt
 *  * Created by Rafsan Ahmad on 6/5/25, 2:33PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Math

class PlusOne {
    //https://leetcode.com/problems/plus-one/
    /*You are given a large integer represented as an integer array digits, where each digits[i]
    is the ith digit of the integer. The digits are ordered from most significant to least
    significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

Example 1:
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

Example 2:
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].

Example 3:
Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].


Constraints:
1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.*/

    fun plusOne(digits: IntArray): IntArray {
        val len = digits.size
        var resultArr = IntArray(len)

        var carry = 0
        for (i in digits.indices) {
            if (digits[i] != 9) break
            if (i == digits.size - 1)
                resultArr = IntArray(len + 1)
        }

        var ind = resultArr.size - 1
        for (i in len - 1 downTo 0) {
            var sum = digits[i] + carry
            if (i == len - 1) {
                sum += 1 //plus one
            }
            carry = sum / 10
            resultArr[ind--] = sum % 10
        }

        if (carry > 0) resultArr[ind] = carry

        return resultArr
    }


    fun plusOneVersion2(digits: IntArray): IntArray {
        val n = digits.size

        for (i in n - 1 downTo 0) {
            if (digits[i] < 9) {
                digits[i]++
                return digits
            }
            digits[i] = 0
        }

        val result = IntArray(n + 1)
        result[0] = 1
        return result
    }
}

fun main() {
    val obj = PlusOne()
    println(obj.plusOne(intArrayOf(9, 9, 9, 9)).contentToString())
    println(obj.plusOne(intArrayOf(1, 0, 1, 0)).contentToString())
    println(obj.plusOneVersion2(intArrayOf(9, 9, 9, 9)).contentToString())
    println(obj.plusOneVersion2(intArrayOf(1, 0, 1, 0)).contentToString())
}