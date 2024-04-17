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
Algorithm:
1. First, we have to make sure that the arr1[] is the smaller array. If not by default, we will
just swap the arrays. Our main goal is to consider the smaller array as arr1[].
2. Calculate the length of the left half: left = (n1+n2+1) / 2.
3. Place the 2 pointers i.e. low and high: Initially, we will place the pointers.
The pointer low will point to 0 and the high will point to n1(i.e. The size of arr1[]).
4. Calculate the ‘mid1’ i.e. x and ‘mid2’ i.e. left-x: Now, inside the loop, we will calculate the value of ‘mid1’ using the following formula:
mid1 = (low+high) // 2 ( ‘//’ refers to integer division)
mid2 = left-mid1
5. Calculate l1, l2, r1, and r2: Generally,
l1 = arr1[mid1-1]
l2 = arr2[mid2-1]
r1 = arr1[mid1]
r2 = arr2[mid2]
6. The possible values of ‘mid1’ and ‘mid2’ might be 0 and n1 and n2 respectively.
So, to handle these cases, we need to store some default values for these four variables.
The default value for l1 and l2 will be INT_MIN and for r1 and r2, it will be INT_MAX.
7. Eliminate the halves based on the following conditions:
If l1 <= r2 && l2 <= r1: We have found the answer.
If (n1+n2) is odd: Return the median = max(l1, l2).
Otherwise: Return median = (max(l1, l2)+min(r1, r2)) / 2.0

8. If l1 > r2: This implies that we have considered more elements from arr1[] than necessary.
So, we have to take less elements from arr1[] and more from arr2[].
In such a scenario, we should try smaller values of x. To achieve this, we will eliminate the
right half (high = mid1-1).
9. If l2 > r1: This implies that we have considered more elements from arr2[] than necessary.
So, we have to take less elements from arr2[] and more from arr1[]. In such a scenario,
we should try bigger values of x. To achieve this, we will eliminate the left half (low = mid1+1).
10. Finally, outside the loop, we will include a dummy return statement just to avoid warnings
or errors.
The steps from 4-6 will be inside a loop and the loop will continue until low crosses high.
*/

    /*
    Time Complexity: O(log(min(n1,n2))), where n1 and n2 are the sizes of two given arrays.
    Reason: We are applying binary search on the range [0, min(n1, n2)].
    Space Complexity: O(1) as no extra space is used.
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        //if n1 is bigger swap the arrays:
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int n = n1 + n2; //total length
        int left = (n1 + n2 + 1) / 2; //length of left half
        //apply binary search:
        int low = 0, high = n1;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = left - mid1;
            //calculate l1, l2, r1 and r2;
            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1) return Math.max(l1, l2);
                else return ((double) (Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            } else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return 0; //dummy statement
    }

    public static void main(String[] args) {
        MedianTwoSortedArray array = new MedianTwoSortedArray();
        int[] ar1 = {1, 2};
        int[] ar2 = {3, 4};
        System.out.println(array.findMedianSortedArrays(ar1, ar2));

        int[] a = {1, 4, 7, 10, 12};
        int[] b = {2, 3, 6, 15};
        System.out.println(array.findMedianSortedArrays(a, b));
    }

}
