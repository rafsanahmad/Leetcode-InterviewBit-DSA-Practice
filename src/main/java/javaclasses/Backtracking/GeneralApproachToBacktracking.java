/*
 * *
 *  * General Approach To Backtracking.java
 *  * Created by Rafsan Ahmad on 1/28/23, 1:22 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralApproachToBacktracking {
    /*This structure might apply to many other backtracking questions, but here I am just going to demonstrate Subsets,
    Permutations, and Combination Sum.*/


    //Subsets : https://leetcode.com/problems/subsets/
/*Given an integer array nums of unique elements, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]*/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackSubsets(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrackSubsets(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrackSubsets(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //Subsets II(contains duplicates) :https://leetcode.com/problems/subsets-ii/
    /*Given an integer array nums that may contain duplicates, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
Input: nums = [0]
Output: [[],[0]]
*/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtracksubsetsWithDup(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtracksubsetsWithDup(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtracksubsetsWithDup(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //Permutations :https://leetcode.com/problems/permutations/
    /*Given an array nums of distinct integers, return all the possible permutations.
    You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]
*/
    /*
    Permutation for given [1,2,3]
    [1,2,3]
    [1,3,2]
    [2,1,3]
    [2,3,1]
    [3,1,2]
    [3,2,1]
    Time complexity
    N!
    for example given N = 5
    intially  _ _ _ _ _
    first dash - any one of the 5 numbers so, 5 chances
    second dash - any of the 4 remaining numbers so, 4 chances
                 ( one number already chosen for first dash )
    ...
    so 5x4x3x2x1 = 120 ( 5! )
    */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrackPermute(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrackPermute(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrackPermute(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //Permutations II(contains duplicates) :https://leetcode.com/problems/permutations-ii/
    /*Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations
    in any order.

Example 1:
Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]

Example 2:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]*/

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        //Arrays.sort(nums);
        backtrackPermuteUnique(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrackPermuteUnique(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrackPermuteUnique(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //Combinations
    /*Given two integers n and k, return all possible combinations of k numbers chosen from the
    range [1, n].

You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same
combination.

Example 2:
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.*/

    /*
    Time complexity
    N! / (N−k)! k!
    for example given N = 4, k = 2
    given 2 spots and 4 numbers
    _ _
    first dash - any one of the 4 numbers so, 4chances
    second dash - any of the 3remaining numbers so, 3chances
                 ( one number already chosen for first dash )
    4*3 = 12 = (4*3*2*1)/(2*1) = N!/(N-K)!
    but there are repetitions in this way (1,2) and (2,1) are counted.
    (Eliminate repetitions of k numbers)
    so to eliminate repetitions we need to find out the number of ways in which k can be arranged
    for eg k = 2
    _ _ ( 2*1 = 2! ) which is nothing but permuation of K = K!
    To Eliminate this ( N!/(N-K)! ) / K!
    N! / (N−k)! k!
    */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 0) return result;
        combineHelper(result, new ArrayList<>(), 1, k, n);
        return result;
    }

    public void combineHelper(List<List<Integer>> result,
                              List<Integer> tempList, int index, int k, int n) {
        if (index > n + 1) return;

        if (tempList.size() == k) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = index; i <= n; i++) {
            tempList.add(i);
            combineHelper(result, tempList, i + 1, k, n);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        GeneralApproachToBacktracking approach = new GeneralApproachToBacktracking();
        int[] arr = {1, 2, 3};
        System.out.println(approach.subsets(arr));

        int[] arr2 = {1, 2, 2};
        System.out.println(approach.subsetsWithDup(arr2));

        System.out.println(approach.permute(arr));

        int[] arr3 = {1, 1, 2};
        System.out.println(approach.permuteUnique(arr3));

        System.out.println(approach.combine(4, 2));
    }
}
