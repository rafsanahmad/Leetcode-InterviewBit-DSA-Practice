/*
 * *
 *  * Count Div.java
 *  * Created by Rafsan Ahmad on 1/23/22, 8:25 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

public class CountDiv {
    /*Write a function:

class Solution { public int solution(int A, int B, int K); }

that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible
by K, i.e.:

{ i : A ≤ i ≤ B, i mod K = 0 }

For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible
 by 2 within the range [6..11], namely 6, 8 and 10.

Write an efficient algorithm for the following assumptions:

A and B are integers within the range [0..2,000,000,000];
K is an integer within the range [1..2,000,000,000];
A ≤ B.*/

    public int solution(int A, int B, int K) {
        int count = 0;
        if (A % K == 0) count++;
        if (A != B) {
            int firstDivided = A / K;
            int secondDivided = B / K;
            count += secondDivided - firstDivided;
        }
        return count;
    }

    public static void main(String[] args) {
        CountDiv div = new CountDiv();
        System.out.println(div.solution(0, 0, 1));
        System.out.println(div.solution(6, 11, 1));
        System.out.println(div.solution(6, 11, 2));
        System.out.println(div.solution(6, 11, 3));
        System.out.println(div.solution(6, 11, 4));
    }
}
