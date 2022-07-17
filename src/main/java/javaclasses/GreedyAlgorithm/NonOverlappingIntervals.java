/*
 * *
 *  * Non-overlapping Intervals.java
 *  * Created by Rafsan Ahmad on 7/17/22, 8:20 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

    /*Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals
     you need to remove to make the rest of the intervals non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Constraints:
1 <= intervals.length <= 10^5
intervals[i].length == 2
-5 * 10^4 <= starti < endi <= 5 * 10^4*/

    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) return 0;
        // sort intervals by end time
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int end = intervals[0][1];
        int result = 0;
        for (int i = 1; i < len; i++) {
            int[] curInterval = intervals[i];
            if (curInterval[0] < end) {
                //Non overlapping
                result++;
            } else {
                //Overlap occurs, update end
                end = curInterval[1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NonOverlappingIntervals intervals = new NonOverlappingIntervals();
        int[][] arr = {
                {1, 2},
                {2, 3},
                {1, 3},
                {3, 4}
        };

        int[][] arr2 = {
                {2, 3},
                {4, 5},
                {6, 7},
                {2, 4},
                {3, 6}
        };
        System.out.println(intervals.eraseOverlapIntervals(arr));
        System.out.println(intervals.eraseOverlapIntervals(arr2));
    }
}
