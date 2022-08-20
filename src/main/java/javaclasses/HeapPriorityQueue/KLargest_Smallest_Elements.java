/*
 * *
 *  * K Largest & Smallest Elements.java
 *  * Created by Rafsan Ahmad on 8/15/22, 1:43 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class KLargest_Smallest_Elements {
    /*Problem Description

Given an 1D integer array A of size N you have to find and return the B largest elements of the array A.

NOTE:

Return the largest B elements in any order you like.


Problem Constraints
1 <= N <= 10^5
1 <= B <= N
1 <= A[i] <= 10^3

Input Format
First argument is an 1D integer array A

Second argument is an integer B.

Output Format
Return a 1D array of size B denoting the B largest elements.

Example Input
Input 1:
 A = [11, 3, 4]
 B = 2

Input 2:
 A = [11, 3, 4, 6]
 B = 3

Example Output
Output 1:
 [11, 4]

Output 2:
 [4, 6, 11]

Example Explanation
Explanation 1:
 The two largest elements of A are 11 and 4

Explanation 2:
 The three largest elements of A are 11, 4 and 6
*/

    public int[] K_Largest(int[] A, int B) {
        // Creating Min Heap for given array with only k elements
        // Create min heap with priority queue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] result = new int[B];

        for (int i = 0; i < B; i++) {
            minHeap.add(A[i]);
        }

        // Loop For each element in array after the kth element
        for (int i = B; i < A.length; i++) {

            // If current element is smaller than minimum ((top element of  the minHeap) element, do nothing
            // and continue to next element
            if (minHeap.peek() > A[i])
                continue;

                // Otherwise Change minimum element (top element of the minHeap) to current element by polling out
                // the top element of the minHeap
            else {
                minHeap.poll();
                minHeap.add(A[i]);
            }
        }

        // Now min heap contains k maximum elements, Iterate and print
        Iterator iterator = minHeap.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            result[index++] = (int) iterator.next();
        }

        return result;
    }

    public int[] K_Smallest(int[] A, int B) {
        // Creating Max Heap for given array with only k elements
        // Create Max heap with priority queue
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int[] result = new int[B];

        for (int i = 0; i < B; i++) {
            maxHeap.add(A[i]);
        }

        // Loop For each element in array after the kth element
        for (int i = B; i < A.length; i++) {

            // If current element is greater than maximum ((top element of  the maxHeap) element, do nothing
            // and continue to next element
            if (maxHeap.peek() < A[i])
                continue;

                // Otherwise Change maximum element (top element of the maxHeap) to current element by polling out
                // the top element of the maxHeap
            else {
                maxHeap.poll();
                maxHeap.add(A[i]);
            }
        }

        // Now max heap contains k maximum elements, Iterate and print
        Iterator iterator = maxHeap.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            result[index++] = (int) iterator.next();
        }

        return result;
    }

    public static void main(String[] args) {
        KLargest_Smallest_Elements elements = new KLargest_Smallest_Elements();
        int[] arr = {3, 11, 4, 6, 1, 16};
        System.out.println(Arrays.toString(elements.K_Largest(arr, 3)));
        System.out.println(Arrays.toString(elements.K_Smallest(arr, 3)));
    }
}
