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

/*Here are the following steps –

i) First we have to create a map of number and it’s count using HashMap.

ii) Then, we can traverse the map and check the element which has count greater than n/2.
 
The time complexity of this approach is O(n) and it’s space complexity is O(n).*/

//Majority Element using HashMap

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public Integer majorityElementUsingMap(int[] nums) {

        int majorityCount = nums.length / 2;

        //Declare map
        Map<Integer, Integer> elemCountMap = new HashMap<>();

        //Traverse an array, to count number and it's occurrences
        for (int i = 0; i < nums.length; i++) {

            //Element is key and it occurrences is it's value
            elemCountMap.put(nums[i], elemCountMap.getOrDefault(nums[i], 0) + 1);
        }

    /*
     Traverse a map and check the element which has count greater than n/2.
    */

        for (Map.Entry<Integer, Integer> record : elemCountMap.entrySet()) {

            if (record.getValue() > majorityCount) {
                return record.getKey();
            }
        }

        return -1;
    }

    public static void main(String[] args) {

    }
}