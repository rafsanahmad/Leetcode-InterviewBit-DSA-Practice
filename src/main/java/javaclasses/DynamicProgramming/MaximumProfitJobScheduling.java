/*
 * *
 *  * Maximum Profit in Job Scheduling.java
 *  * Created by Rafsan Ahmad on 7/30/23, 8:38 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MaximumProfitJobScheduling {
    //res/maximum_profit.png
    //https://leetcode.com/problems/maximum-profit-in-job-scheduling/
    /*We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i],
    obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take
such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at
time X.

Example 1:
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.


Example 2:
Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.

Example 3:
Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6


Constraints:
1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4*/

    public class Job {
        int start;
        int end;
        int profit;

        Job(int s, int e, int p) {
            this.start = s;
            this.end = e;
            this.profit = p;
        }
    }

    HashMap<Integer, Integer> map;

    public int findNextNonConflictingJob(List<Job> jobs, int j) {
        int low = j + 1;
        int high = jobs.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (jobs.get(mid).start >= jobs.get(j).end) {
                if (jobs.get(mid - 1).start >= jobs.get(j).end) {
                    high = mid - 1;
                } else return mid;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public int recJobSchedule(List<Job> jobs, int index) {
        if (index == jobs.size()) {
            return 0;
        }

        if (map.containsKey(index)) {
            return map.get(index);
        }

        int next = findNextNonConflictingJob(jobs, index);

        int include = jobs.get(index).profit;

        if (next != -1) {
            include += recJobSchedule(jobs, next);
        }

        int exclude = recJobSchedule(jobs, index + 1);

        int result = Math.max(include, exclude);

        map.put(index, result);

        return result;
    }


    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = profit.length;

        if (len == 0) return 0;

        map = new HashMap<>();
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Job job = new Job(startTime[i], endTime[i], profit[i]);
            jobs.add(job);
        }

        Collections.sort(jobs, new Comparator<>() {

            @Override
            public int compare(Job o1, Job o2) {
                return o1.start - o2.start;
            }

        });

        return recJobSchedule(jobs, 0);
    }

    public static void main(String[] args) {
        MaximumProfitJobScheduling jobScheduling = new MaximumProfitJobScheduling();
        int[] start = {1, 2, 3, 4, 6};
        int[] end = {3, 5, 10, 6, 9};
        int[] profit = {20, 20, 100, 70, 60};
        System.out.println(jobScheduling.jobScheduling(start, end, profit));
    }
}
