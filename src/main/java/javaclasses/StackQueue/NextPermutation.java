/*
 * * Next Permutation.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.Arrays;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order
(i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.



Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
* */

//Leetcode 31
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        int i = len - 2;

        //Find the peak of the last ASC order
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //Method 2
    public void nextPermutation2(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return;

        int i = 1;
        int lastInc = -1;
        while (i < n) {    //Find the peak of last ASC order
            if (nums[i] > nums[i - 1])
                lastInc = i;
            i += 1;
        }

        if (lastInc == -1) {   //Check if array is DSC
            for (i = 0; i < n / 2; ++i)
                swap(nums, i, n - i - 1);
            return;
        }
        //Find element in the range (nums[lastInc-1] to nums[lastInc]) to the right
        int mn = nums[lastInc];
        int index = lastInc;
        for (i = lastInc; i < n; ++i) {
            if (nums[i] > nums[lastInc - 1] && nums[i] < nums[index]) {
                index = i;
            }
        }
        swap(nums, lastInc - 1, index);

        partSort(nums, lastInc, n - 1);
    }

    // Function to sort the elements of the array
    // from index a to index b
    void partSort(int[] arr, int a, int b) {
        // Variables to store start and end
        // of the index range
        int l = Math.min(a, b);
        int r = Math.max(a, b);

        // Sort the subarray from arr[l] to
        // arr[r]
        Arrays.sort(arr, l, r + 1);
    }

    public static void main(String[] args) {

        NextPermutation perm = new NextPermutation();
        int[] arr = {1, 1, 5, 2};
        perm.nextPermutation(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
