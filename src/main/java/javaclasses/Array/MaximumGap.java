/*
 * * Maximum Gap.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

public class MaximumGap {
    //Leetcode 164
    /*Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
    If the array contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

Example 2:
Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.*/

    /*We all know that, merge, heap or quick sorting algorithm can achieve no better than O(n Log n) time complexity.
    But for linear time requirement, bucket sort, counting sort and radix sort are often good options.

For this specific problem, bucket sort is not a good option because it usually works well on uniform distributions.
Otherwise, in each bucket, the insertion sort may cause heavy burden on time complexity. Counting sort, has time
complexity O(n + k), where k is the range of the elements. But when k > n^2, it is worse than the common sorting
algorithms. So we use the radix sort for solving this problem.

The idea of radix sort is as follows:

Sort elements according to each digit (from lower to higher).
For each digit, use counting sort.

For example, A = [36 504 79 98 8 55 2 113].

The Radix sorting goes like following steps:

Sort 1st digit:
A = [2, 113, 504, 55, 36, 98, 8, 79]

Sort 2nd digit:
A = [2, 504, 8, 113, 36, 55, 79, 98]

Sort 3rd digit:
A = [2, 8, 36, 55, 79, 98, 113, 504]

Done.

To sort array according to each digit, counting sort is used. Note that, we only need to have a array of size 10 to
store the frequencies of elements. This is because we only consider and sort a single digit in each iteration of
Radix sort.

The general form of counting sort is:
(1) Count the frequencies of each elements.  (count[a[i]] ++, this also considers the duplicates)
(2) Get the index of each number in the output array. (count[i]+= count[i-1])
(3) Put numbers in order. (output[count[a[i]] = a[i], count[a[i]]-- to handle the duplicates)

For radix sort, there is minor modifications, details see the code below.
*/
    int getMax(int[] nums) {
        int m = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }
        return m;
    }

    void countSort(int[] nums, int nd) {
        int n = nums.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(nums[i] / nd) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(nums[i] / nd) % 10] - 1] = nums[i];
            count[(nums[i] / nd) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            nums[i] = output[i];
        }
    }

    void radixsort(int[] nums) {
        int max_n = getMax(nums);
        for (int nd = 1; max_n / nd > 0; nd *= 10) {
            countSort(nums, nd);
        }
    }

    int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        radixsort(nums);
        int res = Math.abs(nums[1] - nums[0]);
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > res) {
                res = Math.abs(nums[i] - nums[i - 1]);
            }
        }
        return res;
    }


    //Using Bucket
    /*We can use a bucket-sort like algorithm to solve this problem in time of O(n) and space O(n).
    The basic idea is to project each element of the array to an array of buckets. Each bucket tracks
    the maximum and minimum elements. Finally, scanning the bucket list, we can get the maximum gap.

    The key part is to get the interval:
From: interval * (num[i] - min) = 0 and interval * (max -num[i]) = n
interval = num.length / (max - min)
*/

    class Bucket {
        int low;
        int high;

        public Bucket() {
            low = -1;
            high = -1;
        }
    }

    public int maximumGapUsingBucket(int[] num) {
        if (num == null || num.length < 2) {
            return 0;
        }

        int max = num[0];
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            max = Math.max(max, num[i]);
            min = Math.min(min, num[i]);
        }

        // initialize an array of buckets
        Bucket[] buckets = new Bucket[num.length + 1]; //project to (0 - n)
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }

        double interval = (double) num.length / (max - min);
        //distribute every number to a bucket array
        for (int i = 0; i < num.length; i++) {
            int index = (int) ((num[i] - min) * interval);

            if (buckets[index].low == -1) {
                buckets[index].low = num[i];
                buckets[index].high = num[i];
            } else {
                buckets[index].low = Math.min(buckets[index].low, num[i]);
                buckets[index].high = Math.max(buckets[index].high, num[i]);
            }
        }

        //scan buckets to find maximum gap
        int result = 0;
        int prev = buckets[0].high;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].low != -1) {
                result = Math.max(result, buckets[i].low - prev);
                prev = buckets[i].high;
            }

        }

        return result;
    }


    public static void main(String[] args) {
        int[] ar = {3, 6, 9, 1};
        System.out.println(new MaximumGap().maximumGapUsingBucket(ar));
    }
}
