/*
 * *
 *  * Minimum Number Of Refueling Stops.java
 *  * Created by Rafsan Ahmad on 2/27/22, 12:58 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.PriorityQueue;

public class MinimumNumberOfRefuelingStops {
    //Leetcode 871
    /*A car travels from a starting position to a destination which is target miles east of the starting position.

There are gas stations along the way. The gas stations are represented as an array stations where stations[i]
= [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has
fueli liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of
gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the
gas from the station into the car.

Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach
the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the
destination with 0 fuel left, it is still considered to have arrived.


Example 1:
Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.

Example 2:
Input: target = 100, startFuel = 1, stations = [[10,100]]
Output: -1
Explanation: We can not reach the target (or even the first gas station).

Example 3:
Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
Output: 2
Explanation: We start with 10 liters of fuel.
We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
We made 2 refueling stops along the way, so we return 2.*/

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int ans = 0, prev = 0;
        int tank = startFuel;
        for (int[] station : stations) {
            int location = station[0];
            int capacity = station[1];
            tank -= location - prev;
            while (!pq.isEmpty() && tank < 0) {  // must refuel in past
                tank += pq.poll();
                ans++;
            }

            if (tank < 0) return -1;
            pq.offer(capacity);
            prev = location;
        }

        // Repeat body for station = (target, inf)
        {
            tank -= target - prev;
            while (!pq.isEmpty() && tank < 0) {
                tank += pq.poll();
                ans++;
            }
            if (tank < 0) return -1;
        }

        return ans;
    }

    public static void main(String[] args) {
        MinimumNumberOfRefuelingStops stops = new MinimumNumberOfRefuelingStops();
        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        System.out.println(stops.minRefuelStops(100, 10, stations));  //2

        int[][] stations2 = {};
        System.out.println(stops.minRefuelStops(1, 1, stations2)); // 0
        System.out.println(stops.minRefuelStops(100, 1, stations2)); // -1

        int[][] stations3 = {{50, 50}};
        System.out.println(stops.minRefuelStops(100, 50, stations3)); //1

        int[][] stations4 = {{25, 25}, {50, 25}, {75, 25}};
        System.out.println(stops.minRefuelStops(100, 25, stations4)); //3

    }
}
