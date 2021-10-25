/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

public class MaximumAbsoluteDifference {
    //https://www.interviewbit.com/problems/maximum-absolute-difference/
    /*You are given an array of N integers, A1, A2 ,…, AN. Return maximum value of f(i, j) for all
    1 ≤ i, j ≤ N.

f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.

For example,

A=[1, 3, -1]

f(1, 1) = f(2, 2) = f(3, 3) = 0
f(1, 2) = f(2, 1) = |1 - 3| + |1 - 2| = 3
f(1, 3) = f(3, 1) = |1 - (-1)| + |1 - 3| = 4
f(2, 3) = f(3, 2) = |3 - (-1)| + |2 - 3| = 5

So, we return 5.*/

    /*An efficient solution in O(n) time complexity can be worked out using the properties of absolute values.

f(i, j) = |A[i] – A[j]| + |i – j| can be written in 4 ways (Since we are looking at max value, we don’t
even care if the value becomes negative as long as we are also covering the max value in some way).


Case 1: A[i] > A[j] and i > j
|A[i] - A[j]| = A[i] - A[j]
|i -j| = i - j
hence, f(i, j) = (A[i] + i) - (A[j] + j)

Case 2: A[i] < A[j] and i < j
|A[i] - A[j]| = -(A[i]) + A[j]
|i -j| = -(i) + j
hence, f(i, j) = -(A[i] + i) + (A[j] + j)

Case 3: A[i] > A[j] and i < j
|A[i] - A[j]| = A[i] - A[j]
|i -j| = -(i) + j
hence, f(i, j) = (A[i] - i) - (A[j] - j)

Case 4: A[i] < A[j] and i > j
|A[i] - A[j]| = -(A[i]) + A[j]
|i -j| = i - j
hence, f(i, j) = -(A[i] - i) + (A[j] - j)
Note that cases 1 and 2 are equivalent and so are cases 3 and 4 and hence we can design our algorithm
only for two cases as it will cover all the possible cases.


1. Calculate the value of A[i] + i and A[i] – i for every element of the array while traversing through the array.
2. Then for the two equivalent cases, we find the maximum possible value. For that, we have to store minimum
and maximum values of expressions A[i] + i and A[i] – i for all i.
3. Hence the required maximum absolute difference is maximum of two values i.e. max((A[i] + i) – (A[j] + j))
and max((A[i] – i) – (A[j] – j)). These values can be found easily in linear time.
     a. For max((A[i] + i) – (A[j] + j)) Maintain two variables max1 and min1 which will store maximum and
     minimum values of A[i] + i respectively. max((A[i] + i) – (A[j] + j)) = max1 – min1
     b. For max((A[i] – i) – (A[j] – j)). Maintain two variables max2 and min2 which will store maximum and
     minimum values of A[i] – i respectively. max((A[i] – i) – (A[j] – j)) = max2 – min2*/

    public int maxArr(int[] array) {
        // max and min variables as described
        // in algorithm.
        int max1 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {

            // Updating max and min variables
            // as described in algorithm.
            max1 = Math.max(max1, array[i] + i);
            min1 = Math.min(min1, array[i] + i);
            max2 = Math.max(max2, array[i] - i);
            min2 = Math.min(min2, array[i] - i);
        }

        // Calculating maximum absolute difference.
        return Math.max(max1 - min1, max2 - min2);
    }

    public static void main(String[] args) {
        int[] ar = {1, 3, -1};
        System.out.println(new MaximumAbsoluteDifference().maxArr(ar));
    }

}
