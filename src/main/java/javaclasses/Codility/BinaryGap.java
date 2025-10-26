/*
 * *
 *  * Binary Gap.java
 *  * Created by Rafsan Ahmad on 1/21/22, 12:14 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Codility;

import java.util.Stack;

public class BinaryGap {
    //https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
    /*A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by
    ones at both ends in the binary representation of N.

For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary
representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary
representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no
binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

Write a function:

class Solution { public int solution(int N); }

that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N
doesn't contain a binary gap.

For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its
longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation
'100000' and thus no binary gaps.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..2,147,483,647].*/
    public int solution(int N) {
        String str = Integer.toBinaryString(N);
        int len = str.length();
        int binaryGap = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == '1' && stack.isEmpty()) {
                stack.push(i);
            } else if (ch == '1') {
                int start = stack.pop();
                int diff = (i - start) - 1;
                binaryGap = Math.max(diff, binaryGap);
                if (binaryGap > len / 2) return binaryGap;
                stack.push(i);
            }
        }
        return binaryGap;
    }

    public static void main(String[] args) {
        BinaryGap gap = new BinaryGap();
        System.out.println(gap.solution(32));
        System.out.println(gap.solution(1041));
        System.out.println(gap.solution(328));
    }
}
