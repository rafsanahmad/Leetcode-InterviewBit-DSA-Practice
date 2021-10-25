/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

/*Given an array of size n, Write a code to find majority element in an array.

What is Majority Element?

The majority element is the element that appears more than n/2 times where n is the size of an array.

NOTE: For this problem you can assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input : [3, 2, 3]

Output: 3

The size of the array is 3 and the element which occurs more than 1 is the majority element. So three is the output.

Example 2:

Input: [2, 2, 1, 1, 1, 2, 2]

Output: 2

*/

/*Using Boyer-Moore majority vote algorithm we can find the majority element without using any extra space.

This algorithm finds the majority element if it exists. The first step is to find the candidate for majority element. Then next step is to verify whether this candidate element is the majority element or not.


 
In this problem, It is already given that majority element always exist in the input array. So we don’t need to verify them. To start with this algorithm we need two variables one for count and other for candidate. When count is zero the current element is the candidate element.

Initially, the value of count is zero and candidate is null. When we start traversing an array the first element is the candidate element. Now, we have to compare candidate element with element present at current index. If it is same then we have to increment the count else we have to decrement it. When the value of count is zero, consider the current element as the candidate element. After complete traversal of an array we get the majority element.

The time complexity of this approach is O(n) and it’s space complexity is O(1).
*/

//Find Majority Element using Boyer-Moore Majority Vote Algorithm

public class MajorityElement2 {
    public static Integer majorityElement(int[] nums) {

        int count = 0;
        Integer candidate = null;

        for (int i = 0; i < nums.length; i++) {

            if (count == 0) {
                candidate = nums[i];
            }

            count += (candidate == nums[i]) ? 1 : -1;
        }

        return candidate;
    }
}
