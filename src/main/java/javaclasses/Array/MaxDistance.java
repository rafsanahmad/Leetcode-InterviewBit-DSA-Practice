/*
 * * Max Distance.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MaxDistance {
    //https://www.interviewbit.com/problems/max-distance/
    /*Problem Description

Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].

Input Format
First and only argument is an integer array A.

Output Format
Return an integer denoting the maximum value of j - i;

Example Input
Input 1:
 A = [3, 5, 4, 2]
Example Output
Output 1:
 2

Example Explanation
Explanation 1:
 Maximum value occurs for pair (3, 4).
*/
    //Using Binary search - O(nlogn)
    public int maximumGap(final int[] A) {
        int n = A.length;
        int max[] = new int[n];
        max[n - 1] = A[n - 1];
        int ans = 0;
        for (int i = n - 2; i >= 0; i--)
            max[i] = Math.max(max[i + 1], A[i]);
        for (int i = 0; i < n - 1; i++) {
            int val = -1;
            int l = i + 1, r = n - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (max[mid] >= A[i]) {
                    val = mid;
                    l = mid + 1;
                } else r = mid - 1;
            }
            if (val != -1) ans = Math.max(ans, val - i);
        }
        return ans;
    }

    //Using Space - O(nlogn)
    public int maximumGapUsingMap(final int[] A) {
        int result = 0;
        int min = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(i, A[i]);
        }
        //Sort map
        Map<Integer, Integer> sorted = map
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
            int index = entry.getKey();
            min = Math.min(min, index);
            result = Math.max(result, index - min);
        }
        return result;
    }

    /*In O(n) approach, we only need a suffix max array. The logic goes like this:
1.> We use two pointers, one for traversing A elements and the other for suffmax array.
2.> For each A index, we find the farthest element (suffmax[j]) that is greater than A[i] by iterating the suffmax array.
3.> Then we take indices differences and store them in md=max(md,j-i).
4.> After this we are sure that for subsequent i’s we need to find such j which is farther than the previous
for getting a higher distance.*/

    //Using Space - O(n)
    public int maximumGapUsingSuffmax(final int[] A) {
        int len = A.length;
        int[] suffmax = new int[len];
        suffmax[len - 1] = A[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            suffmax[i] = Math.max(suffmax[i + 1], A[i]);
        }
        int i = 0, j = 0, result = 0;
        while (j < A.length && i < A.length) {
            if (suffmax[j] >= A[i]) {
                result = Math.max(result, j - i);
                j++;
            } else {
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ar = {3, 5, 4, 2};
        System.out.println(new MaxDistance().maximumGapUsingSuffmax(ar));
    }
}
