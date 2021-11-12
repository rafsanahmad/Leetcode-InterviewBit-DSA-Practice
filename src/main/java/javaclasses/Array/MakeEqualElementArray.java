/*
 * *
 *  * Created by Rafsan Ahmad on 11/12/21, 5:30 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.List;

public class MakeEqualElementArray {
    //https://www.interviewbit.com/problems/make-equal-elements-array/
    /*Problem Description
Given an array of all positive integers and an element “x”.

You need to find out whether all array elements can be made equal or not by performing any of the 3 operations:
add x to any element in array, subtract x from any element from array, do nothing.

This operation can be performed only once on an element of array.

Problem Constraints
1<=|A|<=1e5
1<=A[i],x<=1e9

Input Format
First argument is array of integers .
Second argument is B which denotes the value of x.


Output Format
Return 1 if we can make all elements equal , otherwise return 0.


Example Input
Input 1:
A=[2,3,1]
X=1
Input 2:

A=[2,3,1]
X=2


Example Output
Ouput 1:
1
Ouput 2:

0


Example Explanation
Explanation 1:
WE can make all elements equal to 2.
Explanation 2:

There is no way to make all elements equal to 0.
*/

    //Brute Force - Time Limit Exceeded
    public int makeElementsEqual(int[] nums, int B) {
        int result = 0;
        List<List<Integer>> fullList = new ArrayList<>();
        int[] op = {0, B, -B};
        for (int i = 0; i < op.length; i++) {
            int opVal = op[i];
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                int val = opVal + nums[j];
                list.add(val);
            }
            fullList.add(list);
        }

        int count;
        List<Integer> first = fullList.get(0);
        for (int i = 0; i < first.size(); i++) {
            int val = first.get(i);
            count = 1;
            for (int k = 1; k < fullList.size(); k++) {
                List<Integer> inner = fullList.get(k);
                if (inner.contains(val)) {
                    ++count;
                } else {
                    break;
                }
            }
            if (count == fullList.size()) return 1;
        }
        return result;
    }

    public int makeElementsEqualOptimized(int[] nums, int B) {
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = Math.abs(nums[i] - nums[i + 1]);
            if (diff % B != 0)
                return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        MakeEqualElementArray array = new MakeEqualElementArray();
        int[] nums = {1, 2, 3};
        System.out.println(array.makeElementsEqual(nums, 1));
        int[] nums2 = {1, 5, 10, 4};
        System.out.println(array.makeElementsEqualOptimized(nums2, 4));
    }

}
