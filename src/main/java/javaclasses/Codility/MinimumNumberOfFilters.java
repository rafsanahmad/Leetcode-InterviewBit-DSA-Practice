/*
 * *
 *  * Minimum Number Of Filters.java
 *  * Created by Rafsan Ahmad on 1/24/22, 1:53 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Codility;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumNumberOfFilters {
    /*Problem Description — “Minimum Filters to Halve Pollution”

A company operates several factories, each producing a certain amount of pollution every day.
You are given an array A of integers, where A[i] represents the amount of pollution emitted by
the i-th factory.

To reduce the total pollution, the company can install filters.
Each filter, when installed on a factory, reduces that factory’s pollution by half
(i.e., A[i] = A[i] / 2).

A filter can be installed multiple times on the same factory — every new filter halves whatever
pollution remains.

Your task is to determine the minimum number of filters that must be installed (on any factories,
possibly multiple on the same one) in order to reduce the total pollution by at least half.

Example
Example 1:
A = [5, 19, 8, 1]

Explanation:
Total pollution initially = 5 + 19 + 8 + 1 = 33
We need to reduce it to ≤ 16.5

Steps:
Apply filter to factory with 19 → becomes 9.5 → total = 23.5
Apply another filter to 9.5 → becomes 4.75 → total = 18.25
Apply filter to factory with 8 → becomes 4 → total = 14.25 (≤ 16.5)

Minimum filters = 3

Function Signature
public int minimumNumberOfFilters(int[] A)

Input:
A is an array of integers (1 ≤ A[i] ≤ 10⁴)

1 ≤ N = A.length ≤ 100,000

Output:
Return the minimum number of filters required to reduce total pollution by at least half.*/

    //Time  = O(k × n)
    //Space = O(n)
    /*n → number of factories (A.length)
k → number of filters applied (depends on data)*/
    public int minimumNumberOfFilters(int[] A) {
        int len = A.length;
        int minFilters = 0;
        int totalPollution = 0;
        for (int i = 0; i < len; i++) {
            totalPollution += A[i];
        }
        double reducedPollution = totalPollution;

        double[] pollutions = Arrays.stream(A).asDoubleStream().toArray();
        while (reducedPollution > totalPollution / 2.0) {
            int maxIndex = maxIndex(pollutions);
            double p = pollutions[maxIndex] / 2;
            pollutions[maxIndex] = p;
            reducedPollution = getSum(pollutions);
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


    /*Optimized (Max-Heap) version:
    Time  = O(n + k × log n)
    Space = O(n)
    */
    public int minimumNumberOfFiltersOptimized(int[] A) {
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        double total = 0;
        for (int a : A) {
            total += a;
            maxHeap.add((double) a);
        }

        double target = total / 2.0;
        double reduced = 0;
        int filters = 0;

        while (reduced < target) {
            double max = maxHeap.poll();
            double half = max / 2.0;
            reduced += half;
            maxHeap.add(half);
            filters++;
        }

        return filters;
    }

    public static void main(String[] args) {
        MinimumNumberOfFilters filters = new MinimumNumberOfFilters();
        int[] nums = {5, 19, 8, 1};
        int[] nums2 = {1, 0, 2};
        int[] nums3 = {1, 1};
        System.out.println(filters.minimumNumberOfFilters(nums));
        System.out.println(filters.minimumNumberOfFilters(nums2));
        System.out.println(filters.minimumNumberOfFilters(nums3));

        System.out.println(filters.minimumNumberOfFiltersOptimized(nums));
        System.out.println(filters.minimumNumberOfFiltersOptimized(nums2));
        System.out.println(filters.minimumNumberOfFiltersOptimized(nums3));
    }
}
