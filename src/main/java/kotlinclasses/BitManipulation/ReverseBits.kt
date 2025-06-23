/*
 * *
 *  * Reverse Bits.kt
 *  * Created by Rafsan Ahmad on 6/22/25, 11:57AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BitManipulation

class ReverseBits {
    //https://leetcode.com/problems/reverse-bits/description/
    /*Reverse bits of a given 32 bits unsigned integer.

Note:

Note that in some languages, such as Java, there is no unsigned integer type. In this case, both
input and output will be given as a signed integer type. They should not affect your implementation
, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in
Example 2 above, the input represents the signed integer -3 and the output represents the signed
integer -1073741825.


Example 1:
Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned
integer 43261596, so return 964176192 which its binary representation is
00111001011110000010100101000000.

Example 2:
Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned
integer 4294967293, so return 3221225471 which its binary representation is
10111111111111111111111111111111.


Constraints:
The input must be a binary string of length 32

Follow up: If this function is called many times, how would you optimize it?*/

    /* The Goal of reverseBits()
You want to reverse the bits of a 32-bit integer.

Example:
Input:  00000010 (binary for 2)
Output: 01000000 (binary for 64)
You're taking the bits from right to left and inserting them left to right.

🔹 result = result shl 1 or lastBit
📌 Purpose: Move result one bit to the left, then add the current rightmost bit from input.
shl 1: Makes space for the new bit at the end.

or lastBit: Adds the extracted bit (0 or 1) into that space.

🔍 Example:
Let's say result = 00000000, lastBit = 1.

result shl 1 → 00000000
or lastBit  → 00000001 → result becomes 1
On next loop:

result = 00000001, and again:

shl 1 → 00000010
or 1  → 00000011
This builds the reversed bit pattern step by step.

🔹 input = input ushr 1
📌 Purpose: Move to the next bit in input from right to left.
You use:

input and 1 to get the last (rightmost) bit.

ushr 1 to remove that bit, shifting everything right by 1.

🧠 Why ushr and not shr?
ushr (Unsigned Shift Right) fills leftmost bits with 0

shr (Signed Shift Right) fills left with sign bit (1 if negative)

We don’t want sign extension when working with bits—this would corrupt the logic for negative
numbers.

Using shr would keep adding 1s on the left for negative numbers, like this:

val x = -1  // binary: 11111111...1111
x shr 1     // still: 11111111...1111 (sign bit keeps it negative!)
🔴 That ruins your bit reversal.

✅ ushr makes sure the bits move cleanly right, no sign involved.*/

    fun reverseBits(n: Int): Int {
        var input = n             // input = 4 → binary: 00000000 00000000 00000000 00000100
        var result = 0            // result starts as 0

        for (i in 0 until 32) {
            val lastBit = input and 1
            // Get last bit of input (input & 1). Initially: 00000100 & 00000001 = 0

            result = result shl 1 or lastBit
            // Shift result left by 1, then OR with lastBit.
            // On first iteration: 00000000 << 1 | 0 → 00000000

            input = input ushr 1
            // Unsigned shift input right by 1.
            // 00000100 >> 1 = 00000010
        }

        return result             // Final reversed bits as integer
    }
}

fun main() {
    val obj = ReverseBits()
    println(obj.reverseBits(4))
    println(obj.reverseBits(43261596))
}