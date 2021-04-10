package javaclasses.Array;

/*Find top k frequent elements in an array of integers. Let’s first understand the problem statement and the we will solve this problem using multiple approaches.

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: arr = {3, 4, 4, 4, 7, 7}, k = 2

Output: {4, 7}

Two most frequent elements are 4 and 7.

Example 2:

Input: arr = {3}, k = 1

Output: {3}

NOTE:

* k is always valid, 1 ≤ k ≤ number of unique elements.

* The time complexity must be better than O(nlogn), where n is the array’s size.

* It’s guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.*/

/*The idea here is to first create a map of array element and it’s count. Now, we know each array element and it’s count. Next step is to sort the HashMap by it’s value and pick only k elements.*/


//K Most Frequent Elements by sorting HashMap

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopKFrequentElement {
    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> elemCountMap = new HashMap<>();

        for (int num : nums) {
            elemCountMap.put(num, elemCountMap.getOrDefault(num, 0) + 1);
        }

        //Sort by values and pick only top k elements
        List<Integer> result =
                elemCountMap.entrySet().stream()
                        .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                        .limit(k)
                        .map(i -> i.getKey())
                        .collect(Collectors.toList());

        int[] resultArr = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }

        return resultArr;
    }

    public static void main(String[] args) {

    }
}

