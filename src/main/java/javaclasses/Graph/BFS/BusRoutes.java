/*
 * *
 *  * Bus Routes.java
 *  * Created by Rafsan Ahmad on 1/13/24, 2:12 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph.BFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BusRoutes {
    //https://leetcode.com/problems/bus-routes/description/
    /*You are given an array routes representing bus routes where routes[i] is a bus route that the ith
    bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence
1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus
stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is
not possible.

Example 1:
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the
bus stop 6.

Example 2:
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1


Constraints:
1 <= routes.length <= 500.
1 <= routes[i].length <= 10^5
All the values of routes[i] are unique.
sum(routes[i].length) <= 10^5
0 <= routes[i][j] < 10^6
0 <= source, target < 10^6*/

    public int numBusesToDestination(int[][] routes, int source, int target) {
        int len = routes.length;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; ++i) {
            for (int j : routes[i]) {
                map.putIfAbsent(j, new HashSet<>());
                map.get(j).add(i);
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{source, 0});
        HashSet<Integer> visited = new HashSet<>();
        visited.add(source);
        boolean[] visitedRoutes = new boolean[len];

        while (!queue.isEmpty()) {
            int[] route = queue.poll();
            if (route[0] == target) return route[1];

            if (map.containsKey(route[0])) {
                for (int i : map.get(route[0])) {
                    if (visitedRoutes[i]) continue;
                    for (int j : routes[i]) {
                        if (!visited.contains(j)) {
                            visited.add(j);
                            queue.offer(new int[]{j, route[1] + 1});
                        }
                    }
                    visitedRoutes[i] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BusRoutes routes = new BusRoutes();
        int[][] arr = {{1, 2, 7}, {3, 6, 7}};
        int[][] arr2 = {{2}, {2, 8}};
        int[][] arr3 = {{24}, {3, 6, 11, 14, 22}, {1, 23, 24}, {0, 6, 14}, {1, 3, 8, 11, 20}};
        System.out.println(routes.numBusesToDestination(arr, 1, 6));
        System.out.println(routes.numBusesToDestination(arr2, 2, 8));
        System.out.println(routes.numBusesToDestination(arr3, 20, 8));
    }
}
