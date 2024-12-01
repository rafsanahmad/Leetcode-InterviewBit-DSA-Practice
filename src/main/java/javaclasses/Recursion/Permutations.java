/*
 * * Permutations.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Recursion;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations
//Leetcode 46
/*
* Given an array nums of distinct integers, return all the possible permutations.
* You can return the answer in any order.
*
Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]*/
public class Permutations {
    //res/permutation_tree.jpg
    //res/permutation_tree_2.jpg

    private void recurPermute(int[] nums, List<Integer> perms, List<List<Integer>> ans, boolean[] freq) {
        if (perms.size() == nums.length) {
            ans.add(new ArrayList<>(perms));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!freq[i]) {
                freq[i] = true;
                perms.add(nums[i]);
                recurPermute(nums, perms, ans, freq);
                perms.remove(perms.size() - 1);
                freq[i] = false;
            }

        }
    }

    //Using Frequency array
    /*
     * Time Complexity: O(N! X N)
     * Space Complexity: O(N)
     * */
    public List<List<Integer>> permuteUsingFrequency(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> perms = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];
        recurPermute(nums, perms, ans, freq);
        return ans;
    }

    // Using memory efficient way
    /*
     * Time Complexity: O(N! X N)
     * Space Complexity: O(1)
     * */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        recurPermute(0, nums, result);
        return result;
    }

    private void recurPermute(int start, int[] nums, List<List<Integer>> result) {
        if (start == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            recurPermute(start + 1, nums, result);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Permutations perm = new Permutations();
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = perm.permute(arr);
        System.out.println(result);

        List<List<Integer>> result2 = perm.permuteUsingFrequency(arr);
        System.out.println(result2);
    }

}


