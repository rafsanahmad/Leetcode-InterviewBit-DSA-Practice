/*
 * *
 *  * Two City Scheduling.java
 *  * Created by Rafsan Ahmad on 12/7/21, 2:30 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class TwoCityScheduling {
    /*A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti],
    the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.

Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.

Example 1:

Input: costs = [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation:
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.

Example 2:
Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
Output: 1859

Example 3:
Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
Output: 3086*/

    /*Thus the technique here, sort the list first by the B-A cost, then
    the beginning of the list (smallest values of "relative cost to send to B") are the ones you would rather send to B
    and the ones at the end you'd rather send to A.*/
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (a[1] - a[0]) - (b[1] - b[0]);
            }
        });
        int cost = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            cost += costs[i][1] + costs[costs.length - i - 1][0];
        }
        return cost;
    }

    public static void main(String[] args) {
        TwoCityScheduling cityScheduling = new TwoCityScheduling();
        int[][] arr = {
                {10, 20},
                {30, 200},
                {400, 50},
                {30, 20}
        };
        System.out.println(cityScheduling.twoCitySchedCost(arr));
    }

}
