/*
 *  * Remove Duplicate From SortedList.java
 *  * Created by Rafsan Ahmad on 11/7/21, 12:37 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicateFromSortedList_II {
    //https://www.interviewbit.com/problems/remove-duplicates-from-sorted-array-ii
    /*Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates in place such that each element can appear atmost twice and return
the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

Note that even though we want you to return the new length, make sure to change the original array as well in
place

For example,

Given input array A = [1,1,1,2],

Your function should return length = 3, and A is now [1,1,2].*/

    public int removeDuplicates(ArrayList<Integer> list) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> tempList = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            int val = list.get(i);
            int count = map.getOrDefault(val, 0) + 1;
            map.put(val, count);
            if (count > 2) {
                tempList.remove(Integer.valueOf(val));
            }
        }
        list.clear();
        list.addAll(tempList);
        return list.size();
    }

    //Using two pointer
    public int removeDuplicatesOptimized(ArrayList<Integer> list) {
        int start = 0;
        int end = 0;
        int n = list.size();
        while (end < n) {
            int num = list.get(end);
            int count = 0;
            while (end < n && list.get(end) == num) {
                end++;
                count++;
            }
            for (int i = 0; i < Math.min(count, 2); i++) {
                list.set(start++, num);
            }
        }
        //Remove unwanted elements from last
        while (n > start) {
            list.remove(n - 1);
            n--;
        }
        return start;
    }

    //Leetcode 80
    /*Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that
    each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result
be placed in the first part of the array nums. More formally, if there are k elements after removing the
duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave
beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1)
extra memory.

Example 1:

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3
respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3
and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).*/

    public int removeDuplicates(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> tempList = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            int val = list.get(i);
            int count = map.getOrDefault(val, 0) + 1;
            map.put(val, count);
            if (count > 2) {
                tempList.remove(Integer.valueOf(val));
            }
        }
        //nums = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            nums[i] = tempList.get(i);
        }
        return tempList.size();
    }

    public int removeDuplicatesOptimized(int[] nums) {
        int start = 0;
        int end = 0;
        int len = nums.length;
        while (end < len) {
            int num = nums[end];
            int count = 0;
            while (end < len && nums[end] == num) {
                end++;
                count++;
            }
            //2 is added as the question allowed twice duplicate
            for (int i = 0; i < Math.min(count, 2); i++) {
                nums[start] = num;
                start++;
            }
        }
        return start;
    }

    void printArray(int length, int[] arr) {
        System.out.print("Elements: ");
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RemoveDuplicateFromSortedList_II removeDuplicate = new RemoveDuplicateFromSortedList_II();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        removeDuplicate.removeDuplicatesOptimized(list);
        System.out.println("Elements: " + list.toString());
        System.out.println("Size: " + list.size());

        int[] nums = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2};
        int length = removeDuplicate.removeDuplicates(nums);
        removeDuplicate.printArray(length, nums);
        System.out.println("Size: " + length);

        //Optimized
        int[] nums2 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2};
        int length2 = removeDuplicate.removeDuplicatesOptimized(nums2);
        removeDuplicate.printArray(length2, nums2);
        System.out.println("Size: " + length);
    }
}
