/*
 * *
 *  * Maximum length of longest increasing contiguous subarray after deleting exactly one element from array.java
 *  * Created by Rafsan Ahmad on 7/23/22, 4:12 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

public class MaxLengthIncreasingSubarrayWithOneDeletion {
    /*Given an array arr[ ] of N integers. The task is to find the maximum length of the longest increasing contiguous
    subarray after removing exactly one element from the given array.
Examples :

Input : N = 5, arr[] = { 2, 4, 1, 5, 7 }
Output : 4
Explanation : After removing third element from the array, the array arr[ ] becomes [2, 4, 5, 7] . therefore, the length
 of longest increasing contiguous subarray is 4, which is  maximum.
Input :  N = 4, arr[] = { 2, 1, 3, 1 }
Output : 2
Explanation : We can either remove the second or first element from the array, and the array, arr[ ] becomes {1, 3, 1}
or {2, 3, 1}, and the length of longest increasing contiguous subarray is 2, which is maximum.


Approach: The task can be solved by keeping track of the longest increasing subsequence starting from index ‘i’, and also
 ending at index ‘i’.

1. Create two arrays front[ ] and back[ ] of size N and initialize both arrays with 1.
2. Calculate front[i] and back[i] for each i from 1 to N, where front[i] represents the maximum length of longest
increasing subsequence starting from the position i and back[i] represents the maximum length of longest increasing
subsequence ending at position i.
3. The array front[ ] can be calculated in order from left to right with the following condition : if(a[i] > a[i-1])
update front[i] = front[i-1] + 1, else continue .
4. In same way, array back[ ] can be calculated in order from right to
left with the following condition : if(a[i] < a[i+1]) update back[i] = back[i+1] + 1, else continue
5. Now calculate the ans, initialized with 1, with these two arrays.
Update ans if (a[i] < a[i+2]), it means (i+1)th element is getting deleted, same with back[] array.*/

    // Function to find the maximum length of longest increasing contiguous subarray after removing
    // exactly one element from array
    static int maxLenSubArray(int[] arr, int n) {
        // Creating front[] and back[] arrays
        int[] front = new int[n];
        int[] back = new int[n];
        for (int i = 0; i < n; i++) {
            front[i] = 1;
            back[i] = 1;
        }

        // Calculating the front[]
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                // Update front[i]
                front[i] = front[i - 1] + 1;
            }
        }

        // Calculating the back[]
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                // update back[i]
                back[i] = back[i + 1] + 1;
            }
        }

        // Store the length of longest increasing contiguous subarray
        int ans = 1;

        // Check whether first element is contributing in subarray or not
        boolean check_first = true;

        // Keep track of longest subarray when first element is removed
        int ans_first = 1;

        for (int i = 1; i < n; i++) {

            // front[i] == 1 means contribution of first element in max length subarray has ended
            if (front[i] == 1) {
                check_first = true;
            }

            ans_first = Math.max(ans_first, front[i]);
        }

        // First element contributes in the subarray
        if (!check_first) {
            ans_first -= 1;
        }

        // Check whether last element is contributing in subarray or not
        boolean check_last = true;

        // Keep track of longest subarray when last element is removed
        int ans_last = 1;

        for (int i = n - 2; i >= 0; i--) {

            // back[i] == 1 means contribution of last element in max
            // length subarray has ended
            if (back[i] == 1) {
                check_last = true;
            }
            ans_last = Math.max(ans_last, back[i]);
        }

        // Last element contributes in the subarray
        if (!check_last) {
            ans_last -= 1;
        }

        // Update ans
        ans = Math.max(ans_first, ans_last);

        // Calculating max length when
        // (i+1)th element is deleted
        for (int i = 0; i < n - 2; i++) {
            if (arr[i] < arr[i + 2]) {
                ans = Math.max(ans, front[i] + back[i + 2]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 1};
        int n = arr.length;
        System.out.println(maxLenSubArray(arr, n));
    }
}
