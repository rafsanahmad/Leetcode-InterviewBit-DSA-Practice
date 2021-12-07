/*
 * * Reach a Number.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

public class ReachNumber {
    //Leetcode 754
    /*You are standing at position 0 on an infinite number line. There is a destination at position
    target.

You can make some number of moves numMoves so that:

On each move, you can either go left or right.
During the ith move (starting from i == 1 to i == numMoves), you take i steps in the chosen direction.
Given the integer target, return the minimum number of moves required (i.e., the minimum numMoves) to
reach the destination.

Example 1:
Input: target = 2
Output: 3
Explanation:
On the 1st move, we step from 0 to 1 (1 step).
On the 2nd move, we step from 1 to -1 (2 steps).
On the 3rd move, we step from -1 to 2 (3 steps).

Example 2:
Input: target = 3
Output: 2
Explanation:
On the 1st move, we step from 0 to 1 (1 step).
On the 2nd move, we step from 1 to 3 (2 steps).*/

    int reachNumber(int target) {
        int sum = 0;
        int k = 0;
        target = Math.abs(target);
        while (sum < target) {
            k++;
            sum += k;
        }

        if ((sum - target) % 2 == 0) {
            return k;
        } else {
            if ((k % 2) == 0) {
                //Even
                return k + 1;
            } else {
                //odd
                return k + 2;
            }
        }
    }

    public static void main(String[] args) {
        ReachNumber number = new ReachNumber();
        System.out.println(number.reachNumber(2));
    }
}
