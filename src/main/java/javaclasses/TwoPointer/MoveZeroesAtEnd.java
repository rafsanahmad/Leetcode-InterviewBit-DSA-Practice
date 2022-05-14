/*
 * *
 *  * Move Zeroes To End.java
 *  * Created by Rafsan Ahmad on 5/12/22, 12:55 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoveZeroesAtEnd {
    /*Problem Statement
Given an unsorted array of integers, you have to move the array elements in a way such that all the zeroes are transferred
to the end, and all the non-zero elements are moved to the front. The non-zero elements must be ordered in their order
of appearance.
For example, if the input array is: [0, 1, -2, 3, 4, 0, 5, -27, 9, 0], then the output array must be:
[1, -2, 3, 4, 5, -27, 9, 0, 0, 0].
Expected Complexity: Try doing it in O(n) time complexity and O(1) space complexity. Here, ‘n’ is the size of the array.
Input format :
The first line of input contains a single integer ‘T’ representing the number of test cases.

The first line of each test case contains a single integer ‘N’ representing the size of the array. The second line of
each test case contains ‘N’ integers representing the elements of the array.
Output Format :
For each test case, modify the input array and print the output in a new line
Note :
You don’t need to print anything. It has already been taken care of. Just implement the given function.
Constraints :
1 <= T <= 50
1 <= N <= 10^6
-10000 <= A[i] <= 10000

Where ‘T’ is the number of test cases, ‘N’ is the size of the array, A[i] is the value of the element present at the ith index.
Time Limit:1sec

Sample Input 1:
2
7
2 0 4 1 3 0 28
5
0 0 0 0 1
Sample Output 1:
2 4 1 3 28 0 0
1 0 0 0 0

The Explanation For Sample Output 1 :
In the first testcase, All the zeros are moved towards the end of the array, and the non-zero elements are pushed towards
the left, maintaining their order with respect to the original array.

In the second testcase, All zero are moved towards the end, hence the only non-zero element i.e 1 is in the starting of
the array

Sample Input 2:
2
5
0 3 0 2 0
4
0 0 0 0
Sample Output 2:
3 2 0 0 0
0 0 0 0
*/

    public static void pushZerosAtEnd(ArrayList<Integer> arr) {
        // Write your code here.
        int size = arr.size();
        int left = 0;
        int right = 1;

        while (left < size && right < size) {
            if (arr.get(left) == 0) {
                while (right < size && arr.get(right) == 0) {
                    right++;
                }
                //SWAP
                if (right < size) {
                    int temp = arr.get(left);
                    arr.set(left, arr.get(right));
                    arr.set(right, temp);
                }
            }
            left++;
            right++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 4, 1, 3, 0, 28};
        int[] arr2 = {0, 0, 0, 0, 1};
        int[] arr3 = {0, 3, 0, 2, 0};
        int[] arr4 = {0, 0, 0, 0};
        int[] arr5 = {0, 1, -2, 3, 4, 0, 5, -27, 9, 0};
        List<Integer> list = Arrays.stream(arr5).boxed().collect(Collectors.toList());

        ArrayList<Integer> arrayList = new ArrayList<>(list);
        pushZerosAtEnd(arrayList);
        System.out.println(arrayList);
    }
}
