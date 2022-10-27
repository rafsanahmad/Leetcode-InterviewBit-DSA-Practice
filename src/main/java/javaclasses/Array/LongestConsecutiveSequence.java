/*
 * * Longest Consecutive Sequence.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    //Leetcode 128
/*Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
*/
    //Time complexity  = o(nlogn)
    public int longestConsecutiveUsingSorting(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentStreak += 1;
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }
        return Math.max(longestStreak, currentStreak);
    }

    //Using Hashset
    //Time complexity= 0(n)
    public int longestConsecutive(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int longestStreak = 0;

        for (int num : hashSet) {
            if (!hashSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (hashSet.contains(currentNum + 1)) {
                    currentStreak += 1;
                    currentNum += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    //Using DFS
    static HashSet<Integer> set;
    static int count = 0;

    public int longestConsecutiveUsingDFS(final int[] A) {
        int result = 0;
        HashSet<Integer> visited = new HashSet<>();
        set = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }

        for (int i = 0; i < A.length; i++) {
            count = 0;
            dfs(A[i], visited);
            result = Math.max(result, count);
        }

        return result;
    }

    public void dfs(int value, HashSet<Integer> visited) {
        if (visited.contains(value)) return;

        visited.add(value);
        count++;

        if (set.contains(value + 1)) {
            dfs(value + 1, visited);
        }
        if (set.contains(value - 1)) {
            dfs(value - 1, visited);
        }
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence sequence = new LongestConsecutiveSequence();
        int[] ar = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(sequence.longestConsecutive(ar));
        System.out.println(sequence.longestConsecutiveUsingDFS(ar));
    }
}
