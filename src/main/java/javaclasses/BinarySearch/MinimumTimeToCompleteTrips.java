/*
 * *
 *  *  Minimum Time to Complete Trips.java
 *  * Created by Rafsan Ahmad on 3/11/23, 12:58 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.BinarySearch;

public class MinimumTimeToCompleteTrips {
    //https://leetcode.com/problems/minimum-time-to-complete-trips/
    /*You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.

Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip.
 Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.

You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. Return the
minimum time required for all buses to complete at least totalTrips trips.

Example 1:

Input: time = [1,2,3], totalTrips = 5
Output: 3
Explanation:
- At time t = 1, the number of trips completed by each bus are [1,0,0].
  The total number of trips completed is 1 + 0 + 0 = 1.
- At time t = 2, the number of trips completed by each bus are [2,1,0].
  The total number of trips completed is 2 + 1 + 0 = 3.
- At time t = 3, the number of trips completed by each bus are [3,1,1].
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.
Example 2:

Input: time = [2], totalTrips = 1
Output: 2
Explanation:
There is only one bus, and it will complete its first trip at t = 2.
So the minimum time needed to complete 1 trip is 2.


Constraints:

1 <= time.length <= 10^5
1 <= time[i], totalTrips <= 10^7*/

    public long minimumTime(int[] time, int totalTrips) {
        long result = -1;

        long left = 1, right = 100000000000001L; //Upper bound - 10^7 * 10^7

        while (left <= right) {
            long mid = left + (right - left) / 2; //Avoid overflow
            long curr_trips = 0;
            for (int i = 0; i < time.length; i++) {
                curr_trips += mid / time[i];
            }

            if (curr_trips >= totalTrips) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    //Time complexity: O(NLogK)
    public long minimumTimeOptimized(int[] time, int totalTrips) {
        long low = Long.MAX_VALUE;
        long high = 0;
        for (int i = 0; i < time.length; i++) {
            low = Math.min(low, time[i]);
        }

        high = totalTrips * low;

        while (low < high) {
            long mid = low + (high - low) / 2;
            if (isValidTrip(mid, totalTrips, time)) {
                high = mid;
            } else
                low = mid + 1;
        }
        return low;
    }

    public boolean isValidTrip(long current, int totalTrips, int[] time) {
        long trips = 0;
        for (int i = 0; i < time.length; i++) {
            trips += current / time[i];
        }

        if (trips >= totalTrips)
            return true;

        return false;
    }

    public static void main(String[] args) {
        MinimumTimeToCompleteTrips trips = new MinimumTimeToCompleteTrips();
        int[] arr = {2, 4, 6, 7, 9, 10, 14};
        System.out.println(trips.minimumTime(arr, 8));
        System.out.println(trips.minimumTimeOptimized(arr, 8));
    }
}
