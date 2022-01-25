/*
 * *
 *  * Minimum Number Of Filters.java
 *  * Created by Rafsan Ahmad on 1/24/22, 1:53 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Codility;

import java.util.Arrays;

public class MinimumNumberOfFilters {

    //Minimum filters required to reduce polution by half which is produced in Array A
    public int minimumNumberOfFilters(int[] A) {
        int len = A.length;
        int minFilters = 0;
        int totalPollution = 0;
        for (int i = 0; i < len; i++) {
            totalPollution += A[i];
        }
        double reducedPollution = totalPollution;

        double[] polutions = Arrays.stream(A).asDoubleStream().toArray();
        while (reducedPollution > totalPollution / 2.0) {
            int maxIndex = maxIndex(polutions);
            double p = polutions[maxIndex] / 2;
            polutions[maxIndex] = p;
            reducedPollution = getSum(polutions);
            minFilters++;
        }

        return minFilters;
    }

    public int maxIndex(double[] array) {
        int maxAt = 0;
        for (int i = 0; i < array.length; i++) {
            maxAt = array[i] > array[maxAt] ? i : maxAt;
        }
        return maxAt;
    }

    public double getSum(double[] arr) {
        double total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        return total;
    }

    public static void main(String[] args) {
        MinimumNumberOfFilters filters = new MinimumNumberOfFilters();
        int[] nums = {5, 19, 8, 1};
        int[] nums2 = {1, 0, 2};
        int[] nums3 = {1, 1};
        System.out.println(filters.minimumNumberOfFilters(nums));
        System.out.println(filters.minimumNumberOfFilters(nums2));
        System.out.println(filters.minimumNumberOfFilters(nums3));
    }
}
