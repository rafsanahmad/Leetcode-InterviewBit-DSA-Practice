/*
 * * Remove Element From Array.java
 *  * Created by Rafsan Ahmad on 11/9/21, 2:32 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.TwoPointer;

import java.util.ArrayList;

public class RemoveElementFromArray {
    //https://www.interviewbit.com/problems/remove-element-from-array/
    //Leetcode 27
    /*Remove Element

Given an array and a value, remove all the instances of that value in the array.

Also return the number of elements left in the array after the operation.

It does not matter what is left beyond the expected length.

Example:

If array A is [4, 1, 1, 2, 1, 3]

and value elem is 1,

then new length is 3, and A is now [4, 2, 3]

Try to do it in less than linear additional space complexity.*/

    /*Using two pointer*/
    public int removeElement(ArrayList<Integer> list, int b) {
        int start = 0;
        int end = 0;
        int len = list.size();
        while (start < len && end < len) {
            int num = list.get(end);
            if (list.get(end) == b) {
                end++;
                continue;
            } else {
                list.set(start, num);
                start++;
            }
            end++;
        }
        return start;
    }

    public int removeElement(int[] nums, int val) {
        int start = 0;
        int end = 0;
        int len = nums.length;
        while (start < len && end < len) {
            int num = nums[end];
            if (nums[end] == val) {
                end++;
                continue;
            } else {
                nums[start] = num;
                start++;
            }
            end++;
        }
        return start;
    }

    public void printArray(int[] nums, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public void printList(ArrayList<Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RemoveElementFromArray array = new RemoveElementFromArray();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(0);
        list.add(4);
        list.add(2);
        int size = array.removeElement(list, 2);
        System.out.println("Size: " + size);
        System.out.print("Elements: ");
        array.printList(list, size);

        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int size2 = array.removeElement(nums, 2);
        System.out.println("Size: " + size2);
        System.out.print("Elements: ");
        array.printArray(nums, size2);
    }

}
