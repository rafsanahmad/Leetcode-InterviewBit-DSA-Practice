package javaclasses.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    //Leetcode 300
    /*Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without
changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1*/

    //Time Complexity = O(n^2)
    public int lis(final int[] A) {
        int result = 0;
        int len = A.length;
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 1);
        /* Compute optimized LIS values in bottom up manner */
        for (int i = 1; i < len; i++)
            for (int j = 0; j < i; j++)
                if (A[i] > A[j] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

    //Using Binary Search
    //Time Complexity = O(nlogn)
    public int lisOptimized(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if (list.size() == 0 || num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                int i = 0;
                int j = list.size() - 1;
                while (i < j) {
                    int mid = (i + j) / 2;
                    if (list.get(mid) < num) {
                        i = mid + 1;
                    } else {
                        j = mid;
                    }
                }
                list.set(i, num);
            }
        }
        return list.size();
    }


    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lis.lis(arr));
        System.out.println(lis.lisOptimized(arr));
    }

}
