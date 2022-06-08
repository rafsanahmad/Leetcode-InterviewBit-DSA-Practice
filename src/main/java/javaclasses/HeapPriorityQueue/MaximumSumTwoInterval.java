/*
 * *
 *  * Maximum sum of at most two non-overlapping intervals in a list of Intervals.java
 *  * Created by Rafsan Ahmad on 6/8/22, 10:03 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumSumTwoInterval {
    /*Given an array interval of length N, where each element represents three values, i.e. {startTime, endTime, value}.
    The task is to find the maximum sum of values of at most two non-overlapping intervals.

Example:

Input: interval[] = [[1, 3, 2], [4, 5, 2], [2, 4, 3]]
Output: 4
Explanation: Select interval 1 and 2 (as third interval is overlapping). Therefore, maximum value is 2 + 2 = 4.

Input: interval[] = [[1, 3, 2], [4, 5, 2], [1, 5, 5]]
Output: 5
Explanation: As intervals 1 and 2 are non-overlapping but their value will be 2 + 2 = 4. So, instead of 1 and 2, only 3
can be selected with a value of 5.


Approach: This problem can be solved with the help of a priority queue. To solve this problem, follow the below steps:

1. Sort the given array interval w.r.t. startTime. If startTime of two intervals are the same then sort it on the basis of
endTime.
2. Store the pair of {endTime, value} in the priority queue and sort on the basis of endTime.
3. Traverse the given array and calculate the maximum value for all events whose endTime is smaller than the startTime of
the present interval and store it in variable max.
4. Now, update the ans, after each traversal as, ans= Math.max(ans, max + interval[i][2]).
5. Return ans as the final answer to this problem.
*/

    // Function to find maximum value of at most two non-overlapping intervals
    public static int maxTwoNonOverLapping(int[][] interval) {
        // Sorting the given array on the basis of startTime
        Arrays.sort(interval,
                (a, b) -> (a[0] == b[0]) ? a[1] - b[1]
                        : a[0] - b[0]);

        // Initializing Priority Queue
        // which stores endTime
        // and value and sort
        // on the basis of endTime
        PriorityQueue<int[]> pq
                = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initializing max and ans variables
        int max = 0;
        int ans = 0;

        // Traversing the given array
        for (int[] e : interval) {
            while (!pq.isEmpty()) {

                // If endTime from priority queue is greater than startTime of traversing interval then break the loop
                if (pq.peek()[0] >= e[0])
                    break;
                int[] qu = pq.remove();

                // Updating max variable
                max = Math.max(max, qu[1]);
            }

            // Updating ans variable
            ans = Math.max(ans, max + e[2]);
            pq.add(new int[]{e[1], e[2]});
        }

        // Returning ans
        return ans;
    }

    public static void main(String[] args) {
        int[][] interval
                = {{1, 3, 2}, {4, 5, 2}, {1, 5, 5}};
        int maxValue = maxTwoNonOverLapping(interval);
        System.out.println(maxValue);
    }
}
