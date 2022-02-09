/*
 * *
 *  * Meeting Rooms II.java
 *  * Created by Rafsan Ahmad on 2/6/22, 2:01 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    /*Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
    find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Example 2:
Input: [[7,10],[2,4]]
Output:1

Thoughts:

Sort the interval by the start time
Using the priority queue to use the end time as the order to sort the used classroom
pop the earliest ending meeting room, check if the time ends earlier than the start time of current class being
scheduled, if earlier, merge the interval by setting the poped intervals'end time as the current intervals' scheduled
end time, push the current interval into pq as making a new room.
return the size of the pq as the result*/

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        // sort the arrays by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        // sort the heap by end time (scheduled meeting)
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });

        pq.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            Interval earliest = pq.poll();
            Interval curInterval = intervals[i];

            if (earliest.end <= curInterval.start) {
                earliest.end = curInterval.end; // merge (use the same room)
            } else {
                pq.offer(curInterval); // schedule a new room
            }

            pq.offer(earliest);
        }

        return pq.size();
    }

    public static void main(String[] args) {
        MeetingRoomsII roomsII = new MeetingRoomsII();
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(15, 20);
        intervals[1] = new Interval(0, 30);
        intervals[2] = new Interval(5, 10);
        System.out.println(roomsII.minMeetingRooms(intervals));
    }
}
