/*
 * * Trapping Rain Water.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.TwoPointer;

public class TrappingRainWater {
    //Leetcode 42
    //res/rainwatertrap.png
    /*
    * Given n non-negative integers representing an elevation map where the width of each bar is 1,
    * compute how much water it can trap after raining.

    Example 1:
    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
    * In this case, 6 units of rain water (blue section) are being trapped.
    Example 2:

    Input: height = [4,2,0,3,2,5]
    Output: 9
 */

    /*Quick Recap of the Intuition in the comments:
Always process the smaller side → that side’s max is the limiting wall.
If current bar is taller/equal → update boundary.
If current bar is shorter → trap water (difference between boundary and bar height).
*/
    public int trap(int[] height) {
        // Edge case: empty input → no water trapped
        if (height == null || height.length == 0) {
            return 0;
        }

        int len = height.length;
        int result = 0;     // total water trapped
        int left = 0;       // left pointer
        int right = len - 1;// right pointer
        int leftMax = 0;    // highest wall seen so far from the left
        int rightMax = 0;   // highest wall seen so far from the right

        // Process until pointers meet
        while (left < right) {
            // Decide which side to process:
            // Always move the smaller wall pointer,
            // because that side is the bottleneck for water.
            if (height[left] <= height[right]) {
                // Left side is smaller (or equal) → leftMax decides water level here
                if (height[left] >= leftMax) {
                    // Found a new taller/equal wall on the left → update leftMax
                    leftMax = height[left];
                } else {
                    // Current bar is shorter → it traps water up to leftMax
                    result += leftMax - height[left];
                }
                left++; // Move inward from left
            } else { // height[right] < height[left]
                // Right side is smaller → rightMax decides water level here
                if (height[right] >= rightMax) {
                    // Found a new taller/equal wall on the right → update rightMax
                    rightMax = height[right];
                } else {
                    // Current bar is shorter → it traps water up to rightMax
                    result += rightMax - height[right];
                }
                right--; // Move inward from right
            }
        }

        return result;
    }

    //Using Pre-computed left/right max
    //Input: [4,2,0,3,2,5]
    //L=[4,4,4,4,4,5]
    //R=[5,5,5,5,5,5]
    public int trap2(int[] height) {
        if (height.length == 0) return 0;

        int result = 0;
        int size = height.length;
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];

        int curMax = 0;
        for (int i = 0; i < size; i++) {
            curMax = Math.max(curMax, height[i]);
            leftMax[i] = curMax;
        }

        curMax = 0;
        for (int j = size - 1; j >= 0; j--) {
            curMax = Math.max(curMax, height[j]);
            rightMax[j] = curMax;
        }

        for (int i = 0; i < size; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (min > height[i]) {
                result += min - height[i];
            }
        }

        return result;
    }


    public static void main(String[] args) {
        TrappingRainWater water = new TrappingRainWater();
        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(water.trap(height));
        System.out.println(water.trap2(height));
    }
}
