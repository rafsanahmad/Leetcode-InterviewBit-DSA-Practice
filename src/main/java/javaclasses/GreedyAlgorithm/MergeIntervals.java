/*
 * *
 *  * Merge Intervals.java
 *  * Created by Rafsan Ahmad on 12/5/21, 8:44 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MergeIntervals {
    //Leetcode 56
    /*Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
    and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.*/

    public int[][] merge(int[][] intervals) {
        //sort based on first number in the intervals
        Collections.sort(Arrays.asList(intervals), new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        ArrayList<int[]> merged = new ArrayList<>();
        int count = 0;
        for (int[] interval : intervals) {

            if (merged.isEmpty() || merged.get(count - 1)[1] < interval[0]) {
                //if the list of merged intervals is empty or if the current interval
                //does not overlap with previous interval - append it
                merged.add(interval);
                ++count;
            } else {
                //there is a overlap, so merge the current and previous interval
                merged.get(count - 1)[1] = Math.max(merged.get(count - 1)[1], interval[1]);
            }
        }
        int[][] resultArray = merged.toArray(new int[merged.size()][]);
        return resultArray;
    }

    public static void main(String[] args) {
        MergeIntervals intervals = new MergeIntervals();
        int[][] arr = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        int[][] result = intervals.merge(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.print("[");
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.print("]");
        }
    }
}
