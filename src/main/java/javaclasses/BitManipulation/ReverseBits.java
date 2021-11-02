/*
 * *
 *  * Created by Rafsan Ahmad on 11/3/21, 2:23 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.BitManipulation;

public class ReverseBits {
    //https://www.interviewbit.com/problems/reverse-bits
    //Leetcode 190
    /*Problem Description

Reverse the bits of an 32 bit unsigned integer A.

Problem Constraints
0 <= A <= 2^32

Input Format
First and only argument of input contains an integer A.

Output Format
Return a single unsigned integer denoting the decimal value of reversed bits.

Example Input
Input 1:
 0
Input 2:
 3

Example Output
Output 1:
 0
Output 2:
 3221225472

Example Explanation
Explanation 1:
        00000000000000000000000000000000
=>      00000000000000000000000000000000

Explanation 2:
        00000000000000000000000000000011
=>      11000000000000000000000000000000

Note:

Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and
output will be given as a signed integer type. They should not affect your implementation, as the integer's
internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation.
Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents
the signed integer -1073741825.

*/
    public int reverseBits(int n) {
        String binary = Long.toBinaryString(Integer.toUnsignedLong(n) | 0x100000000L).substring(1);
        StringBuilder builder = new StringBuilder(binary);
        String reverse = builder.reverse().toString();
        Long decimal = Long.parseLong(reverse, 2);
        return decimal.intValue();
    }

    public long reverseBits(long n) {
        String binary = Long.toBinaryString(n | 0x100000000L).substring(1);
        StringBuilder builder = new StringBuilder(binary);
        String reverse = builder.reverse().toString();
        Long decimal = Long.parseLong(reverse, 2);
        return decimal;
    }

    public int binaryToInteger(String binary) {
        char[] numbers = binary.toCharArray();
        int result = 0;
        for (int i = numbers.length - 1; i >= 0; i--)
            if (numbers[i] == '1')
                result += Math.pow(2, (numbers.length - i - 1));
        return result;
    }

    public static void main(String[] args) {
        ReverseBits bits = new ReverseBits();
        System.out.println(bits.reverseBits(3));
        System.out.println(bits.reverseBits(3L));
    }
}
