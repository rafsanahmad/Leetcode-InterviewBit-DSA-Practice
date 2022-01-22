/*
 * *
 *  * Find Missing Number.java
 *  * Created by Rafsan Ahmad on 1/22/22, 9:31 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.HashSet;
import java.util.Set;

public class FindMissingNumber {
    /*An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)],
    which means that exactly one element is missing.

Your goal is to find that missing element.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A, returns the value of the missing element.

For example, given array A such that:

  A[0] = 2
  A[1] = 3
  A[2] = 1
  A[3] = 5
the function should return 4, as it is the missing element.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
the elements of A are all distinct;
each element of array A is an integer within the range [1..(N + 1)].*/

    public int solution(int[] A) {
        int max = A.length + 1;

        Set<Integer> incompleteSet = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            incompleteSet.add(A[i]);
        }

        for (int i = 1; i < max + 1; i++) {
            if (!incompleteSet.contains(i)) {
                return (i);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindMissingNumber number = new FindMissingNumber();
        int[] nums = {2, 3, 1, 5};
        System.out.println(number.solution(nums));
    }

}
