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

/*In this example, Let’s discuss how we can solve this problem using bucket sort and then we will write it’s java code.

Here are the following steps to solve this problem.

i) To find the most frequent elements, First we need to know the number and it’s count.
For this we can use HashMap to store the number and it’s count.

So, if the input array is {1, 1, 1, 2, 2, 3, 3, 3} then at the end of this operation map would look like this: 1 -> 3, 2 -> 2, 3 -> 3.

We also need to keep track of maximum frequency, so it’s value in this case would be 3.

Sorting algorithms and their time complexities

ii) Then in next step, create a list. At each index we can store multiple elements. It’s size would be maximum frequency + 1.

iii) Based on the frequency of a number, put the number in the appropriate bucket.
There might be more than one numbers with the same frequency, so we have to put them in a same bucket.

iv) Now, iterate over the bucket elements and print the k most frequent elements.

The time complexity of this approach is O(n).*/

/*
Find Top K Most Frequent Numbers in an Array
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentElements {

    public static int[] kMostFrequent(int[] nums, int k) {

        //Map which stores number and it's occurrence count
        Map<Integer, Integer> countMap = new HashMap<>();

        //Variable which stores maximum frequency of any number
        int maxFreq = 0;

        //Traverse an array
        for (int i = 0; i < nums.length; i++) {

            //Get the occurrence of current element and add 1
            int freq = countMap.getOrDefault(nums[i], 0) + 1;

            //put the elem and it's freq in a map
            countMap.put(nums[i], freq);

            //keep track of maximum occurrence
            maxFreq = Math.max(maxFreq, freq);
        }

        //Declare a bucket, which store multiple values
        List<Integer>[] bucket = new ArrayList[maxFreq + 1];

        for (int n : countMap.keySet()) {
            int freq = countMap.get(n);

            if (bucket[freq] == null)
                bucket[freq] = new ArrayList<>();

            bucket[freq].add(n);
        }

        int[] resultArr = new int[k];
        int count = 0;

        //Pick top k elements
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (int n : bucket[i]) {
                    resultArr[count++] = n;

                    if (count == k)
                        return resultArr;
                }
            }
        }

        return resultArr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 4, 4, 7, 7};
        int[] result = kMostFrequent(arr, 2);

        for (int elem : result) {
            System.out.print(elem + " ");
        }
    }
}

