/*
 * * Array Product.java
 *  * Created by Rafsan Ahmad on 12/3/21, 10:01 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;

public class ArrayProduct {
    /*[Problem Description]

You are given an integer array A of length n.

You can pick any 3 different positions x, y & z from the array.

Find the minimum & maximum possible value of |Ax * Ay * Az|

Here, |Ax * Ay * Az| stands for the absolute value of the product of array elements at position x, y & z.

Input Format

The first line contains a single integer n.
The second line contains n integers A1, A2, ..., AN separated by space.

Constraints

3 ≤ n ≤ 100000
-100 ≤ Ai ≤ 100
x, y & z should be different from each other

Output Format

Print two integers minimum & maximum value separated by space.

Sample Input 0

4
-1 1 1 2
Sample Output 0

1 2
Explanation 0

|A1 * A2 * A3| = |-1 * 1 * 1| = |-1| = 1
|A1 * A2 * A4| = |-1 * 1 * 2| = |-2| = 2
|A1 * A3 * A4| = |-1 * 1 * 2| = |-2| = 2
|A2 * A3 * A4| = |1 * 1 * 2| = |2| = 2

Here, minimum value is 1 and maximum value is 2

Sample Input 1
5
0 1 2 -2 -1

Sample Output 1
0 4

Sample Input 2
11
76 3 69 -26 -54 -79 -88 -72 43 -23 -95

Sample Output 2

1794 660440*/

    static int max = 0;
    static int min = 0;

    public static void findMinMax(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = Math.abs(arr[i]);
        }
        Arrays.sort(arr);
        // invalid input
        if (n <= 2) {
            return;
        }

        int maxFromLast = arr[n - 1] * arr[n - 2] * arr[n - 3];
        int maxFromFirst = arr[0] * arr[1] * arr[n - 1];

        int minFromLast = arr[n - 1] * arr[n - 2] * arr[0];
        int minFromFirst = arr[0] * arr[1] * arr[2];

        max = Math.max(maxFromFirst, maxFromLast);
        min = Math.min(minFromLast, minFromFirst);
    }

    public static void main(String[] args) {
        int[] arr = {76, 3, 69, -26, -54, -79, -88, -72, 43, -23, -95};
        findMinMax(arr);
        System.out.print(min + " ");
        System.out.print(max);
    }

}
