/*
 * * Next Permutation.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;

//Leetcode 31
/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

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
*

//Algorithm: res/next-permutation-algorithm.svg

1. Initial sequence: 0,1,2,5,3,3,0
2. FInd longest non-increasing suffix: 0,1,2,[5,3,3,0]
3. Indentify pivot: 0,1,[2],5,3,3,0
4. Find rightmost successor to pivot in the suffix: 0,1,[2],5,3,[3],0
5. Swap the pivot: 0,1,3,[5,3,2,0]
6. Reverse the suffix: 0,1,3,0,2,3,5
7. Done

*/

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        // pivot is the element just before the non-increasing (weakly decreasing) suffix

        int pivot = indexOfLastPeak(nums) - 1;
        // paritions nums into [prefix pivot suffix]
        if (pivot != -1) {
            int nextPrefix = lastIndexOfGreater(nums, nums[pivot]); // in the worst case it's suffix[0]
            // next prefix must exist because pivot < suffix[0],
            // otherwise pivot would be part of suffix
            swap(nums, pivot, nextPrefix); // this minimizes the change in prefix
        }

        reverseSuffix(nums, pivot + 1); // reverses the whole list if there was no pivot
    }

    /**
     * Find the last element which is a peak.
     * In case there are multiple equal peaks, return the first of those.
     *
     * @return first index of last peak
     */
    int indexOfLastPeak(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) return i;
        }
        return 0;
    }

    /**
     * @return last index where the {@code num > threshold} or -1 if not found
     */
    int lastIndexOfGreater(int[] nums, int threshold) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > threshold) return i;
        }
        return -1;
    }

    /**
     * Reverse numbers starting from an index till the end.
     */
    void reverseSuffix(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        NextPermutation perm = new NextPermutation();
        int[] arr = {1, 1, 5, 2};
        perm.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }

}
