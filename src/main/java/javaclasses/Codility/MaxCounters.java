/*
 * *
 *  * Max Counters.java
 *  * Created by Rafsan Ahmad on 1/23/22, 1:53 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Codility;

import java.util.Arrays;

public class MaxCounters {
    /*You are given N counters, initially set to 0, and you have two possible operations on them:

increase(X) − counter X is increased by 1,
max counter − all counters are set to the maximum value of any counter.
A non-empty array A of M integers is given. This array represents consecutive operations:

if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
if A[K] = N + 1 then operation K is max counter.
For example, given integer N = 5 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the values of the counters after each consecutive operation will be:

    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)
The goal is to calculate the value of every counter after all operations.

Write a function:

class Solution { public int[] solution(int N, int[] A); }

that, given an integer N and a non-empty array A consisting of M integers, returns a sequence of
integers representing the values of the counters.

Result array should be returned as an array of integers.

For example, given:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the function should return [3, 2, 2, 4, 2], as explained above.

Write an efficient algorithm for the following assumptions:

N and M are integers within the range [1..100,000];
each element of array A is an integer within the range [1..N + 1].*/

    //Time Limit Exceeded
    //Naive approach → whenever maxCounter appears, update all N counters → O(N × M)
    public int[] solution(int N, int[] A) {
        int len = A.length;
        int[] result = new int[N];
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (A[i] == N + 1) {
                Arrays.fill(result, max);
            } else {
                int index = A[i];
                int val = result[index - 1] + 1;
                max = Math.max(val, max);
                result[index - 1] = val;
            }
        }
        return result;
    }

    /*Key Optimization Idea: “Lazy update” using lastMax
Instead of updating all counters immediately when maxCounter happens,
we remember the last global max in a variable — lastMax.*/
    //Time Complexity: O(N + M)
    public int[] solutionOptimized(int N, int[] A) {
        int max = 0;
        int lastMax = 0;
        int[] counters = new int[N];

        for (int k = 0; k < A.length; k++) {
            if (A[k] >= 1 && A[k] <= N) {
                counters[A[k] - 1] = Math.max(counters[A[k] - 1], lastMax);
                counters[A[k] - 1] += 1;
                max = Math.max(max, counters[A[k] - 1]);
            } else if (A[k] == N + 1) {
                lastMax = max;
            }
        }

        /*Counters that were not touched after the last maxCounter
still have old, smaller values, even though logically they should be at least lastMax.*/
        for (int i = 0; i < counters.length; i++) {
            counters[i] = Math.max(counters[i], lastMax);
        }

        return counters;
    }

    public static void main(String[] args) {
        MaxCounters counters = new MaxCounters();
        int[] arr = {3, 4, 4, 6, 1, 4, 4};
        System.out.println(Arrays.toString(counters.solution(5, arr)));
        System.out.println(Arrays.toString(counters.solutionOptimized(5, arr)));
    }
}
