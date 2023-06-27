/*
 * *
 *  * KthLargestElementInStream.java
 *  * Created by Rafsan Ahmad on 6/27/23, 5:08 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.PriorityQueue;

public class KthLargestElementInStream {
    //https://leetcode.com/problems/kth-largest-element-in-a-stream/
    /*Design a class to find the kth largest element in a stream. Note that it is the kth largest
    element in the sorted order, not the kth distinct element.

Implement KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers
nums.
int add(int val) Appends the integer val to the stream and returns the element representing the
kth largest element in the stream.


Example 1:

Input
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output
[null, 4, 5, 5, 8, 8]

Explanation
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8


Constraints:

1 <= k <= 10^4
0 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
-10^4 <= val <= 10^4
At most 10^4 calls will be made to add.
It is guaranteed that there will be at least k elements in the array when you search for the
kth element.*/

    PriorityQueue<Integer> pq;
    int K;

    public KthLargestElementInStream(int k, int[] nums) {
        K = k;
        //Min Heap
        pq = new PriorityQueue<Integer>(k);
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        pq.add(val);

        if (pq.size() > K) {
            pq.poll();
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        KthLargestElementInStream stream = new KthLargestElementInStream(3, nums);
        System.out.println(stream.add(3));   // return 4
        System.out.println(stream.add(5));   // return 5
        System.out.println(stream.add(10));  // return 5
        System.out.println(stream.add(9));   // return 8
        System.out.println(stream.add(4));   // return 8
    }
}