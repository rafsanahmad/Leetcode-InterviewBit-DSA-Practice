/*
 * *
 *  * Median of Two Sorted Array.java
 *  * Created by Rafsan Ahmad on 12/10/21, 9:09 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.BinarySearch;

import java.util.Arrays;

public class MedianTwoSortedArray {
    //Leetcode 4
    /*
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
    The overall run time complexity should be O(log (m+n)).

    Example 1:

    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
    Example 2:

    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
    Example 3:

    Input: nums1 = [0,0], nums2 = [0,0]
    Output: 0.00000
    Example 4:

    Input: nums1 = [], nums2 = [1]
    Output: 1.00000
    */

    public double findMedianSortedArraysNoob(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        double result = 0.0;
        int[] resultArr = new int[m + n];
        for (int i = 0; i < m; i++) {
            resultArr[i] = nums1[i];
        }
        for (int j = m; j < (m + n); j++) {
            resultArr[j] = nums2[j - m];
        }
        Arrays.sort(resultArr);
        int total = m + n;
        if (total % 2 == 0) {
            //take middle 2
            int index1 = total / 2;
            result = (double) ((resultArr[index1 - 1] + resultArr[index1]) / 2.0);
        } else {
            int index = total / 2;
            result = (double) (resultArr[index]);
        }
        return result;
    }

    //Optimum Approach
    //Steps of the Algorithm
    /*
1) Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[] respectively.
2) If m1 and m2 both are equal then we are done, and return m1 (or m2)
3) If m1 is greater than m2, then median is present in one of the below two
    subarrays.
a) From first element of ar1 to m1 (ar1[0...|_n/2_|])
b) From m2 to last element of ar2 (ar2[|_n/2_|...n-1])
4) If m2 is greater than m1, then median is present in one of the below two
    subarrays.
a) From m1 to last element of ar1 (ar1[|_n/2_|...n-1])
b) From first element of ar2 to m2 (ar2[0...|_n/2_|])
5) Repeat the above process until size of both the subarrays becomes 2.
6) If size of the two arrays is 2 then use below formula to get the median.
Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
*/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 0) {
            return (getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2)
                    + getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2 - 1)) / 2.0;
        } else {
            return getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2);
        }
    }

    private int getKth(int[] nums1, int i1, int j1, int[] nums2, int i2, int j2, int k) {
        if (j1 < i1) {
            return nums2[i2 + k];
        }
        if (j2 < i2) {
            return nums1[i1 + k];
        }

        if (k == 0) {
            return Math.min(nums1[i1], nums2[i2]);
        }

        int len1 = j1 - i1 + 1;
        int len2 = j2 - i2 + 1;

        int m1 = k * len1 / (len1 + len2);
        int m2 = k - m1 - 1;

        m1 += i1;
        m2 += i2;

        if (nums1[m1] < nums2[m2]) {
            k = k - (m1 - i1 + 1);
            j2 = m2;
            i1 = m1 + 1;
        } else {
            k = k - (m2 - i2 + 1);
            j1 = m1;
            i2 = m2 + 1;
        }

        return getKth(nums1, i1, j1, nums2, i2, j2, k);
    }

    public static void main(String[] args) {
        MedianTwoSortedArray array = new MedianTwoSortedArray();
        int[] ar1 = {1, 2};
        int[] ar2 = {3, 4};
        System.out.println(array.findMedianSortedArrays(ar1, ar2));
    }

}
