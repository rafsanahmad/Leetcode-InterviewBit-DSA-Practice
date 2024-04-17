/*
 * *
 *  * Combination Sum.java
 *  * Created by Rafsan Ahmad on 1/28/23, 6:10 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    //Combination Sum :https://leetcode.com/problems/combination-sum/
    /*Given an array of distinct integers candidates and a target integer target, return a list of all unique
    combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
frequency
 of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150
combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
*/

    /*
    Time complexity is O(N^target) where N is a length of candidates array.
    Space complexity is O(target).
    */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackCombinationSum(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrackCombinationSum(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                backtrackCombinationSum(list, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //Combination Sum II(can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/
    /*Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
    candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]*/
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackCombinationSum2(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrackCombinationSum2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrackCombinationSum2(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] arr = {2, 3, 6, 7};
        System.out.println(combinationSum.combinationSum(arr, 7));

        int[] arr2 = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum.combinationSum2(arr2, 8));
    }
}
