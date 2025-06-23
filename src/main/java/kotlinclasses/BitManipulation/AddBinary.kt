/*
 * *
 *  * Add Binary.kt
 *  * Created by Rafsan Ahmad on 6/21/25, 9:24PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BitManipulation

class AddBinary {
    //https://leetcode.com/problems/add-binary/description/
    /*Given two binary strings a and b, return their sum as a binary string.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"


Constraints:
1 <= a.length, b.length <= 10^4
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.*/

    /*Intuition / Bit Manipulation Explained:
bitA xor bitB xor carry: gives the current bit of the result (0 or 1).
Carry is set when any two or more bits are 1 — we calculate it using:
(a AND b) OR (b AND carry) OR (a AND carry)

Keep building the result string from the least significant bit to the most significant,
then reverse it at the end.

Why use XOR (^) for sum in binary?
In binary addition:

A	B	Carry In   Sum	Carry Out
0	0	   0	    0	    0
0	1	   0	    1	    0
1	0	   0	    1	    0
1	1	   0	    0	    1
1	1	   1	    1	    1
1	0	   1	    0	    1
0	1	   1	    0	    1
0	0	   1	    1	    0

So you can see:
The sum bit is like a "parity" — it's 1 only when an odd number of 1s among a, b, and carry exist.
That’s exactly what XOR does.

*/
    fun addBinary(a: String, b: String): String {
        val strBuilder = StringBuilder()
        var i = a.length - 1
        var j = b.length - 1
        var carry = 0

        while (i >= 0 || j >= 0 || carry > 0) {
            val bitA = if (i >= 0) a[i] - '0' else 0
            val bitB = if (j >= 0) b[j] - '0' else 0

            val sum = bitA xor bitB xor carry
            strBuilder.append(sum)
            carry = (bitA and bitB) or (bitA and carry) or (bitB and carry)

            i--
            j--
        }

        return strBuilder.reverse().toString()
    }
}

fun main() {
    val obj = AddBinary()
    println(obj.addBinary("1010", "1011"))
}