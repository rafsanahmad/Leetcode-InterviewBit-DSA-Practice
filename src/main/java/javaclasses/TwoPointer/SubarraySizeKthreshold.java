/*
 * *
 *  * Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold.java
 *  * Created by Rafsan Ahmad on 4/7/22, 1:20 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.TwoPointer;

public class SubarraySizeKthreshold {
    //Leetcode 1343
    /*Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and
    average greater than or equal to threshold.

Example 1:
Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
Output: 3
Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of
size 3 have averages less than 4 (the threshold).

Example 2:
Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
Output: 6
Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
*/

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int start = 0;
        int sum = 0;
        int len = arr.length;
        int result = 0;

        for (int end = 0; end < len; end++) {
            sum = sum + arr[end];

            if (end >= k - 1) {
                if (start > 0) sum = sum - arr[start - 1];
                if (sum / k >= threshold) {
                    result++;
                }
                start++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SubarraySizeKthreshold subarraySizeKthreshold = new SubarraySizeKthreshold();
        int[] arr2 = {11, 13, 17, 23, 29, 31, 7, 5, 2, 3};
        System.out.println(subarraySizeKthreshold.numOfSubarrays(arr2, 3, 5));
    }
}
