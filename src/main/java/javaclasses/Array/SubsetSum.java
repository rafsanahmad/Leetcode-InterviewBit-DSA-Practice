/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubsetSum {
    /*Given a list(Arr) of N integers, print sums of all subsets in it. Output should be printed in increasing order of sums.

Example 1:

Input:
N = 2
Arr = [2, 3]
Output:
0 2 3 5
Explanation:
When no elements is taken then Sum = 0.
When only 2 is taken then Sum = 2.
When only 3 is taken then Sum = 3.
When element 2 and 3 are taken then
Sum = 2+3 = 5.
Example 2:

Input:
N = 3
Arr = [5, 2, 1]
Output:
0 1 2 3 5 6 7 8
Your Task:
You don't need to read input or print anything.
Your task is to complete the function subsetSum() which takes a list/vector and an integer N as an input parameter and return the list/vector of all the subset sums in increasing order.

Expected Time Complexity: O(2N)
Expected Auxiliary Space: O(2N)

Constraints:
1 <= N <= 15
0 <= Arr[i] <= 10000*/

    void recursionCall(int index, int sum, int[] arr, int len, List<Integer> subSets) {
        if (index == len) {
            subSets.add(sum);
            return;
        }
        //Pick the element
        recursionCall(index + 1, sum + arr[index], arr, len, subSets);

        //Do-not pick the element
        recursionCall(index + 1, sum, arr, len, subSets);
    }

    public Object[] subsetSums(int[] arr) {
        int len = arr.length;
        List<Integer> sumSubset = new ArrayList<>();
        recursionCall(0, 0, arr, len, sumSubset);
        Collections.sort(sumSubset);
        return sumSubset.toArray();
    }

    public static void main(String[] args) {
        int[] ar = {5,2,1};
        SubsetSum subsetSum = new SubsetSum();
        System.out.println(Arrays.toString(subsetSum.subsetSums(ar)));
    }
}
