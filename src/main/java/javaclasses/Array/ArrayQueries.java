/*
 * *
 *  * Created by Rafsan Ahmad on 12/3/21, 8:05 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayQueries {
    /*[Problem Description]

An array ARR of size N is given.
Q queries will be asked.
In each query, two numbers A & B is given.
For each query, output how many numbers are there within the range of A & B (inclusive) in the given array.

Input Format

The first line contains a single integer N.
The second line contains N integers ARR1, ARR2, ..., ARRN separated by space.
The third line contains a single integer Q.
Each of the next Q lines contains two integers A & B.

Constraints

1 ≤ N ≤ 100000
1 ≤ Q ≤ 100000
0 ≤ ARRi ≤ 100
0 ≤ A ≤ B ≤ 100

Output Format

For each query, print the result in a single line.

Sample Input 0

10
1 20 2 15 10 2 1 25 11 29
5
1 5
5 10
10 15
15 20
10 22

Sample Output 0
4
1
3
2
4
*/
    public static int[] findInclusive(int[] arr, List<int[]> queries) {
        int[] result = new int[queries.size()];
        Arrays.sort(arr);
        int index = 0;
        int n = arr.length;
        for (int i = 0; i < queries.size(); i++) {
            int[] q = queries.get(i);
            int count = 0;
            count = upperIndex(arr, n, q[1]) -
                    lowerIndex(arr, n, q[0]) + 1;
            result[index++] = count;
        }
        return result;
    }

    // function to find first index >= start
    static int lowerIndex(int[] arr, int n, int start) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= start)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    // function to find last index <= end
    static int upperIndex(int[] arr, int n, int end) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= end)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right;
    }

    public static void main(String[] args) {
        int[] arr = {1, 20, 2, 15, 10, 2, 1, 25, 11, 29};
        List<int[]> queries = new ArrayList<>();
        queries.add(new int[]{1, 5});
        queries.add(new int[]{5, 10});
        queries.add(new int[]{10, 15});
        queries.add(new int[]{15, 20});
        queries.add(new int[]{10, 22});

        System.out.println(Arrays.toString(findInclusive(arr, queries)));
    }
}
