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

    //Time complexity: O(n log k)
    public int[] K_Largest(int[] A, int k) {
        // Min-heap with size k to keep track of the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        // Iterate through the array
        for (int num : A) {
            // Add element to heap
            minHeap.add(num);

            // If heap size exceeds k, remove the smallest element (root of the heap)
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Prepare result array to store the k largest elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();  // Extract elements from the heap
        }

        return result;
    }

    //Time complexity: O(n log k)
    public int[] K_Smallest(int[] A, int k) {
        // Max Heap with size k to keep track of the k largest elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (a, b) -> b - a); // Max-heap

        // Iterate through the array
        for (int num : A) {
            // Add element to heap
            maxHeap.add(num);

            // If heap size exceeds k, remove the smallest element (root of the heap)
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Prepare result array to store the k largest elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();  // Extract elements from the heap
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
