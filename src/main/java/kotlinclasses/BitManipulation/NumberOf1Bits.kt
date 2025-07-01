/*
 * *
 *  * Number of 1 Bits.kt
 *  * Created by Rafsan Ahmad on 6/28/25, 1:06AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BitManipulation

class NumberOf1Bits {
    //https://leetcode.com/problems/number-of-1-bits/description/
    /*Given a positive integer n, write a function that returns the number of set bits in
    its binary representation (also known as the Hamming weight).


Example 1:
Input: n = 11
Output: 3

Explanation:
The input binary string 1011 has a total of three set bits.

Example 2:
Input: n = 128
Output: 1
Explanation:
The input binary string 10000000 has a total of one set bit.

Example 3:
Input: n = 2147483645
Output: 30
Explanation:
The input binary string 1111111111111111111111111111101 has a total of thirty set bits.


Constraints:
1 <= n <= 2^31 - 1


Follow up: If this function is called many times, how would you optimize it?
*/

    /*Purpose of num = num ushr 1:
This line performs an unsigned right shift on num — it removes the last (rightmost) bit of
the number by shifting all bits one position to the right and inserting a 0 on the left.*/

    /*Example: n = 128
Binary of 128 is:
00000000 00000000 00000000 10000000

Only the 8th bit from the right is 1, the rest are 0.

Step-by-step:
Iteration	num (binary)	num and 1	bitCount	After num ushr 1
0	        10000000 (128)	    0	        0	       01000000 (64)
1	        01000000 (64)	    0	        0	       00100000 (32)
2	        00100000 (32)	    0	        0	       00010000 (16)
3	        00010000 (16)	    0	        0	       00001000 (8)
4	        00001000 (8)	    0	        0	       00000100 (4)
5	        00000100 (4)	    0	        0	       00000010 (2)
6	        00000010 (2)	    0	        0	       00000001 (1)
7	        00000001 (1)	    1 ✅	    1	       00000000 (0)
8-32	    all 0s	            0	        1	       remains 0

✅ Final Result:
bitCount = 1 → There is only one 1 in the binary representation of 128, which is correct.*/

    fun hammingWeight(n: Int): Int {
        var bitCount = 0
        var num = n
        for (i in 0..32) {
            val lastBit = num and 1
            if (lastBit == 1)
                bitCount++

            num = num ushr 1
        }
        return bitCount
    }
}

fun main() {
    val obj = NumberOf1Bits()
    println(obj.hammingWeight(128))
}