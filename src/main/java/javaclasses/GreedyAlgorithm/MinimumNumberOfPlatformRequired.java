/*
 * *
 *  * Minimum Number Of Platform Required.java
 *  * Created by Rafsan Ahmad on 2/5/22, 11:30 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.Arrays;

public class MinimumNumberOfPlatformRequired {
    //res/platform.png
    /*Problem Statement: We are given two arrays that represent the arrival and departure times of trains that stop at
    the platform. We need to find the minimum number of platforms needed at the railway station so that no train has
    to wait.

Examples 1:

Input: N=6,
arr[] = {9:00, 9:45, 9:55, 11:00, 15:00, 18:00}
dep[] = {9:20, 12:00, 11:30, 11:50, 19:00, 20:00}

Output:3
Explanation: There are at-most three trains at a time. The train at 11:00 arrived but the trains which had arrived
at 9:45 and 9:55 have still not departed. So, we need at least three platforms here.



Example 2:

Input Format: N=2,
arr[]={10:20,12:00}
dep[]={10:50,12:30}

Output: 1
Explanation: Before the arrival of the new train, the earlier train already departed. So, we don’t require multiple
platforms.*/

    static int findPlatform(int arr[], int dep[], int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int plat_needed = 1, result = 1;
        int i = 1, j = 0;

        while (i < n && j < n) {

            if (arr[i] <= dep[j]) {
                plat_needed++;
                i++;
            } else if (arr[i] > dep[j]) {
                plat_needed--;
                j++;
            }

            if (plat_needed > result)
                result = plat_needed;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {900, 945, 955, 1100, 1500, 1800};
        int[] dep = {920, 1200, 1130, 1150, 1900, 2000};
        int n = arr.length;
        int totalCount = findPlatform(arr, dep, n);
        System.out.println("Minimum number of Platforms required " + totalCount);
    }
}
