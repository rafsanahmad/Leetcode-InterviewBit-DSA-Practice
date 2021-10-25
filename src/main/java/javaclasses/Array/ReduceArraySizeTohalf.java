/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReduceArraySizeTohalf {
    //Leetcode 1338
    /*You are given an integer array arr. You can choose a set of integers and remove all
    the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.

Example 1:
Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has
size greater than half of the size of the old array.

Example 2:
Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.

Example 3:
Input: arr = [1,9]
Output: 1

Example 4:
Input: arr = [1000,1000,3,7]
Output: 1

Example 5:
Input: arr = [1,2,3,4,5,6,7,8,9,10]
Output: 5*/

    public int minSetSize(int[] arr) {
        int result = 0;
        int len = arr.length;
        HashMap<Integer, Integer> countMap = new HashMap<>(); //Key = Integer value ,value = count
        for (int i = 0; i < len; i++) {
            int value = arr[i];
            countMap.put(value, countMap.getOrDefault(value, 0) + 1);
        }

        Map<Integer, Integer> sortedMap =
                countMap.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            int value = entry.getValue();
            int key = entry.getKey();
            if (len > arr.length / 2) {
                len = len - value;
                ++result;
            } else {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        ReduceArraySizeTohalf reduceArraySizeTohalf = new ReduceArraySizeTohalf();
        System.out.println(reduceArraySizeTohalf.minSetSize(arr));
    }

}
