/*
 * *
 *  * Find Pair With Given Difference.java
 *  * Created by Rafsan Ahmad on 12/25/21, 3:02 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindPairWithGivenDifference {
    /*Given an unsorted integer array, print all pairs with a given difference k in it.

For example,

Input:

arr = [1, 5, 2, 2, 2, 5, 5, 4]
k = 3

Output:

(2, 5) and (1, 4)
Practice this problem

A naive solution would be to consider every pair in a given array and return if the desired difference is found.
The time complexity of this solution would be O(n2), where n is the size of the input.

We can use a set to solve this problem in linear time. The idea is to insert each array element arr[i] into a set.
We also check if element (arr[i] - diff) or (arr[i] + diff) already exists in the set or not.
If the element is seen before, print the pair (arr[i], arr[i] - diff) or (arr[i] + diff, arr[i]).*/

    // Function to find a pair with the given difference in the array.
    // This method handles duplicates in the array
    public static void findPair(int[] A, int diff) {
        // sort array in ascending order
        Arrays.sort(A);

        // take an empty set
        Set<Integer> set = new HashSet<>();

        // do for every array element
        for (int i = 0; i < A.length; i++) {
            // to avoid printing duplicates (skip adjacent duplicates)
            while (i + 1 < A.length && A[i] == A[i + 1]) {
                i++;
            }

            // check if pair with the given difference `(A[i], A[i]-diff)` exists
            if (set.contains(A[i] - diff)) {
                System.out.println("(" + A[i] + ", " + (A[i] - diff) + ")");
            }

            // check if pair with the given difference `(A[i]+diff, A[i])` exists
            if (set.contains(A[i] + diff)) {
                System.out.println("(" + (A[i] + diff) + ", " + A[i] + ")");
            }

            // insert the current element into the set
            set.add(A[i]);
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 5, 2, 2, 2, 5, 5, 4};
        int diff = -3;

        findPair(A, diff);
    }
}
