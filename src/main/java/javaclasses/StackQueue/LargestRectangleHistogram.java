/*
 * *
 *  * Largest Rectangle In Histogram.java
 *  * Created by Rafsan Ahmad on 9/2/24, 3:05 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.StackQueue;

import java.util.Stack;

public class LargestRectangleHistogram {
    //res/histogram.jpg
    /*Given an array of integers heights representing the histogram's bar height where the width
    of each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.

The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:
Input: heights = [2,4]
Output: 4

Constraints:

1 <= heights.length <= 10^5
0 <= heights[i] <= 10^4

Solution 1: Brute Force Approach

Intuition: The intuition behind the approach is taking different bars and finding the maximum
width possible using the bar.
*/
    //Time Complexity: O(N*N)
    int largestareaBruteForce(int[] arr) {
        int maxArea = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                minHeight = Math.min(minHeight, arr[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /*Solution 2: Optimised Approach 1
Intuition: The intuition behind the approach is the same as finding the smaller element on both
sides but in an optimized way using the concept of the next greater element and the next smaller
element.*/

    int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int[] leftSmall = new int[n];
        int[] rightSmall = new int[n];
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }

            if (st.isEmpty()) leftSmall[i] = 0;
            else leftSmall[i] = st.peek() + 1;
            st.push(i);
        }

        // clear the stack to be re-used
        while (!st.isEmpty()) st.pop();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }

            if (st.isEmpty()) rightSmall[i] = n - 1;
            else rightSmall[i] = st.peek() - 1;

            st.push(i);
        }

        int maxA = 0;
        for (int i = 0; i < n; i++) {
            maxA = Math.max(maxA, heights[i] * (rightSmall[i] - leftSmall[i] + 1));
        }
        return maxA;
    }

    public static void main(String[] args) {
        LargestRectangleHistogram histogram = new LargestRectangleHistogram();
        int[] arr = {2, 1, 5, 6, 2, 3, 1};
        System.out.println(histogram.largestareaBruteForce(arr));
        System.out.println(histogram.largestRectangleArea(arr));
    }
}
