/*
 * *
 *  * Maximum Meetings.java
 *  * Created by Rafsan Ahmad on 5/24/22, 1:38 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaximumMeetings {
    //https://www.codingninjas.com/codestudio/problems/maximum-meetings_1062658?leftPanelTab=0
    /*Problem Statement
You are given the schedule of N meetings with their start time Start[i] and end time End[i]. But you have only 1 meeting room.
So, you need to tell the meeting numbers you can organize in the given room, such that the number of meetings organized is
maximum.
Note:
The start time of one chosen meeting can’t be equal to the end time of the other chosen meeting. Also for the same end time,
 a meeting with a smaller index is preferred.
Input Format:
The first line contains an integer 'T' denoting the number of test cases or queries to be run.

The first line of each test case or query contains a single integers 'N' denoting the number of meetings.

The second line of each test case contains N single space-separated integers denoting the start time of N meetings respectively.

The third line of each test case contains N single space-separated integers denoting the end time of N meetings respectively.
Output Format:
For each test case, print the meeting numbers (Consider 1 based indexing) you organized in the given room, in the order in
which you organized them such that the number of meetings is maximum.
Note:
You do not need to print anything, it has already been taken care of. Just implement the given function.
Constraints:
1 <= T <= 5
1 <= N <= 10^5
0 <= Start[i] < End[i] <= 10^9

Time Limit: 1 sec

Sample Input 1:
2
6
1 3 0 5 8 5
2 4 6 7 9 9
3
1 1 1
4 5 9

Sample Output 1:
1 2 4 5
1

Explanation For Sample Input 1:
For test case 1:
You can organize a maximum of 4 meetings. Meeting number 1 from 1 to 2, Meeting number 2 from 3 to 4, Meeting number
4 from 5 to 7, and Meeting number 5 from 8 to 9.

For test case 2:
As all meetings have the same start time, you can organize only 1 meeting in the room.

Sample Input 2:
2
5
0 7 1 4 8
2 9 5 9 10
3
1 2 3
4 4 4
Sample Output 2:
1 2
1*/

    public static class Interval {
        int start;
        int end;
        int index;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e, int i) {
            start = s;
            end = e;
            index = i;
        }
    }

    public static List<Integer> maximumMeetings(int[] start, int[] end) {
        //Write your code here
        List<Interval> intervals = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            Interval interval = new Interval(start[i], end[i], i + 1);
            intervals.add(interval);
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end == o2.end) return o1.index - o2.index;
                else return o1.end - o2.end;
            }
        });
        if (intervals.size() < 1) return result;

        int prevEnd = intervals.get(0).end;
        result.add(intervals.get(0).index);

        for (int i = 1; i < intervals.size(); i++) {
            Interval curInterval = intervals.get(i);
            if (curInterval.start > prevEnd) {
                int curIndex = intervals.get(i).index;
                result.add(curIndex);
                prevEnd = curInterval.end;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        int[] start2 = {7, 9, 6, 2};
        int[] end2 = {19, 16, 17, 5};
        System.out.println(maximumMeetings(start, end));
        System.out.println(maximumMeetings(start2, end2));
    }
}
