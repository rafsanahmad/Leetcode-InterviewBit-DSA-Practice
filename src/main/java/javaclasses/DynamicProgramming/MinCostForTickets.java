/*
 * *
 *  * Minimum Cost For Tickets.java
 *  * Created by Rafsan Ahmad on 4/9/23, 4:46 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinCostForTickets {
    //https://leetcode.com/problems/minimum-cost-for-tickets/description/
    /*You have planned some train traveling one year in advance. The days of the year in which you will travel are
    given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.


Example 1:
Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.

Example 2:
Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.


Constraints:
1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000*/

    public int mincostTicketsRecursive(int[] days, int[] costs) {
        return minCostTicketHelper(days, costs, 0);
    }

    public int minCostTicketHelper(int[] days, int[] costs, int index) {
        int len = days.length;
        if (index >= len) return 0;

        int costDay = costs[0] + minCostTicketHelper(days, costs, index + 1);

        int i;
        for (i = index; i < len && days[i] < days[index] + 7; i++) ;
        int costWeek = costs[1] + minCostTicketHelper(days, costs, i);

        int j;
        for (j = index; j < len && days[j] < days[index] + 30; j++) ;
        int costMonth = costs[2] + minCostTicketHelper(days, costs, j);

        return min(costDay, costWeek, costMonth);
    }

    public int mincostTicketsTopDown(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        Arrays.fill(dp, -1);
        return mincostTicketsTopDownHelper(days, costs, 0, dp);
    }

    public int mincostTicketsTopDownHelper(int[] days, int[] costs, int index, int[] dp) {
        int len = days.length;
        if (index >= len) return 0;

        if (dp[index] != -1) return dp[index];
        int costDay = costs[0] + mincostTicketsTopDownHelper(days, costs, index + 1, dp);

        int i;
        for (i = index; i < len && days[i] < days[index] + 7; i++) ; //Dicard 7 days
        int costWeek = costs[1] + mincostTicketsTopDownHelper(days, costs, i, dp);

        int j;
        for (j = index; j < len && days[j] < days[index] + 30; j++) ; //Discard 30 days
        int costMonth = costs[2] + mincostTicketsTopDownHelper(days, costs, j, dp);

        return dp[index] = min(costDay, costWeek, costMonth);
    }

    public int min(int i, int j, int k) {
        return Integer.min(i, Integer.min(j, k));
    }

    public int mincostTicketsBottomUp(int[] days, int[] costs) {
        int max = days[days.length - 1];
        Set<Integer> tours = new HashSet<>();

        for (int i = 0; i < days.length; i++) tours.add(days[i]);
        int[] dp = new int[max + 1];
        dp[0] = 0;
        for (int i = 1; i <= max; i++) {
            if (!tours.contains(i)) {
                dp[i] = dp[i - 1];
                continue;
            }

            dp[i] = dp[i - 1] + costs[0]; //1 day
            dp[i] = Math.min(dp[i], costs[1] + dp[Math.max(i - 7, 0)]); //7 day
            dp[i] = Math.min(dp[i], costs[2] + dp[Math.max(i - 30, 0)]); //30 day
        }

        return dp[max];
    }

    public static void main(String[] args) {
        MinCostForTickets tickets = new MinCostForTickets();
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] cost = {2, 7, 15};
        System.out.println(tickets.mincostTicketsRecursive(days, cost));
        System.out.println(tickets.mincostTicketsTopDown(days, cost));
        System.out.println(tickets.mincostTicketsBottomUp(days, cost));
    }
}
