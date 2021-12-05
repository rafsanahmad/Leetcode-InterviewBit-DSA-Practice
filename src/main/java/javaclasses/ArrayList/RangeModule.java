/*
 * * Range Module.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class RangeModule {
    //Leetcode 715
    /* Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented
    as half-open intervals and query about them.

A half-open interval [left, right) denotes all the real numbers x where left <= x < right.

Implement the RangeModule class:

RangeModule() Initializes the object of the data structure.
void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval.
Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval
[left, right) that are not already tracked.
boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently
being tracked, and false otherwise.
void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval
 [left, right).


Example 1:

Input
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
Output
[null, null, null, true, false, true]

Explanation
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove
operation)*/

    List<int[]> ranges;

    public RangeModule() {
        ranges = new ArrayList();
    }

    public void addRange(int left, int right) {
        if (ranges.isEmpty()) {
            int[] range = {left, right};
            ranges.add(range);
        } else {
            //check for overlap
            for (int i = 0; i < ranges.size(); i++) {
                int[] arr = ranges.get(i);
                if (arr[0] <= left && arr[1] >= right) {
                    arr[0] = left;
                    arr[1] = right;
                    ranges.set(i, arr);
                } else {
                    //No overlap
                    int[] range = {left, right};
                    ranges.add(range);
                }
            }
        }
    }

    public boolean queryRange(int left, int right) {
        for (int i = 0; i < ranges.size(); i++) {
            int[] arr = ranges.get(i);
            if (arr[0] <= left && arr[1] >= right) {
                //range found
                return true;
            }
        }
        return false;
    }

    public void removeRange(int left, int right) {
        for (int i = 0; i < ranges.size(); i++) {
            int[] arr = ranges.get(i);
            if (arr[0] < left && arr[1] > right) {
                //range found
                ranges.remove(arr);
                //Create range
                int l1 = arr[0];
                int r1 = left;
                int[] range = {l1, r1};
                ranges.add(range);
                int l2 = right;
                int r2 = arr[1];
                int[] range2 = {l2, r2};
                ranges.add(range2);
                break;
            }
        }
    }

    public static void main(String[] args) {
        RangeModule module = new RangeModule();
        /*module.addRange(10, 20);
        module.removeRange(14, 16);
        module.queryRange(10, 14);
        module.queryRange(13, 15);
        module.queryRange(16, 17);*/
        module.addRange(1,5);
        System.out.println(module.queryRange(1, 4));
        module.addRange(3, 5);
        module.addRange(1, 2);
        module.removeRange(1, 2);
        module.removeRange(5, 9);
        System.out.println(module.queryRange(6, 7));
        module.addRange(5, 6);
        System.out.println(module.queryRange(1, 3));
        module.removeRange(1, 8);
    }
}
