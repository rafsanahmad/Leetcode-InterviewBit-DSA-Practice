/*
 * *
 *  * Intersection Of Two Arrays II.java
 *  * Created by Rafsan Ahmad on 12/19/21, 2:27 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArraysII {
    //Leetcode 350
    /*Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result
    must appear as many times as it shows in both arrays and you may return the result in any order.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.

Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into
the memory at once?*/

    //Using HashMap
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        for (int i = 0; i < len1; i++) {
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < len2; i++) {
            map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);
        }

        if (map1.size() > map2.size()) {
            //Traverse map1
            for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
                int key = entry.getKey();
                int val1 = entry.getValue();
                if (map2.containsKey(key)) {
                    while (val1 > 0) {
                        int val2 = map2.get(key);
                        if (val2 > 0) {
                            list.add(key);
                            map2.put(key, map2.get(key) - 1);
                        } else {
                            break;
                        }
                        val1--;
                    }
                }
            }
        } else {
            //Traverse map2
            for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
                int key = entry.getKey();
                int val2 = entry.getValue();
                if (map1.containsKey(key)) {
                    while (val2 > 0) {
                        int val1 = map1.get(key);
                        if (val1 > 0) {
                            list.add(key);
                            map1.put(key, map1.get(key) - 1);
                        } else {
                            break;
                        }
                        val2--;
                    }
                }
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    //Using Single HashMap
    public int[] intersectOptimized(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                if (map.get(i) > 1) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.remove(i);
                }
                list.add(i);
            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        while (i < list.size()) {
            result[i] = list.get(i);
            i++;
        }

        return result;
    }

    //If arrays are sorted - use two pointers
    public int[] intersectSorted(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> list = new ArrayList<Integer>();
        int p1 = 0, p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;

            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        while (i < list.size()) {
            result[i] = list.get(i);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArraysII arrays = new IntersectionOfTwoArraysII();
        int[] arr1 = {1, 2, 2, 1};
        int[] arr2 = {2, 2};
        System.out.println(Arrays.toString(arrays.intersect(arr1, arr2)));
        System.out.println(Arrays.toString(arrays.intersectOptimized(arr1, arr2)));
        System.out.println(Arrays.toString(arrays.intersectSorted(arr1, arr2)));

        int[] arr3 = {4, 9, 5};
        int[] arr4 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(arrays.intersect(arr3, arr4)));
        System.out.println(Arrays.toString(arrays.intersectOptimized(arr3, arr4)));
        System.out.println(Arrays.toString(arrays.intersectSorted(arr1, arr2)));
    }
}
