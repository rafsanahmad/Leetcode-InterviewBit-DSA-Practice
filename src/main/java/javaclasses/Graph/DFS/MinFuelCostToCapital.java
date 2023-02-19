/*
 * *
 *  * Minimum Fuel Cost to Report to the Capital.java
 *  * Created by Rafsan Ahmad on 2/17/23, 6:23 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinFuelCostToCapital {
    //https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
    /*There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n
    cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0.
    You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional
    road connecting cities ai and bi.

There is a meeting for the representatives of each city. The meeting is in the capital city.

There is a car in each city. You are given an integer seats that indicates the number of seats in each car.

A representative can use the car in their city to travel or change the car and ride with another representative.
The cost of traveling between two cities is one liter of fuel.

Return the minimum number of liters of fuel to reach the capital city.


Example 1:
      1
     |
     0
   /   \
  2     3
Input: roads = [[0,1],[0,2],[0,3]], seats = 5
Output: 3
Explanation:
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative2 goes directly to the capital with 1 liter of fuel.
- Representative3 goes directly to the capital with 1 liter of fuel.
It costs 3 liters of fuel at minimum.
It can be proven that 3 is the minimum number of liters of fuel needed.

Example 2:
          3
         /
        2
       /
      1
      |
     0
   /   \
  4     5
 /
6

Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
Output: 7
Explanation:
- Representative2 goes directly to city 3 with 1 liter of fuel.
- Representative2 and representative3 go together to city 1 with 1 liter of fuel.
- Representative2 and representative3 go together to the capital with 1 liter of fuel.
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative5 goes directly to the capital with 1 liter of fuel.
- Representative6 goes directly to city 4 with 1 liter of fuel.
- Representative4 and representative6 go together to the capital with 1 liter of fuel.
It costs 7 liters of fuel at minimum.
It can be proven that 7 is the minimum number of liters of fuel needed.

Example 3:
Input: roads = [], seats = 1
Output: 0
Explanation: No representatives need to travel to the capital city.

Constraints:

1 <= n <= 10^5
roads.length == n - 1
roads[i].length == 2
0 <= ai, bi < n
ai != bi
roads represents a valid tree.
1 <= seats <= 10^5
*/

    long totalFuel;

    public long minimumFuelCost(int[][] roads, int seats) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        int len = roads.length;

        for (int i = 0; i < len + 1; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] road : roads) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }

        dfsRoads(map, seats, -1, 0);
        return totalFuel;
    }

    public long dfsRoads(HashMap<Integer, List<Integer>> map, int seats, int parent, int currNode) {
        int people = 1;

        if (!map.containsKey(currNode)) return people;

        for (int i : map.get(currNode)) {
            if (i == parent) continue;
            people += dfsRoads(map, seats, currNode, i);
        }

        if (currNode != 0) {
            totalFuel += Math.ceil((double) people / seats);
        }

        return people;
    }

    public static void main(String[] args) {
        MinFuelCostToCapital costToCapital = new MinFuelCostToCapital();
        int[][] roads = {{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}};
        System.out.println(costToCapital.minimumFuelCost(roads, 2));
    }
}
