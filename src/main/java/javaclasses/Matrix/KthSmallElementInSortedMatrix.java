/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Matrix;

import java.util.PriorityQueue;

public class KthSmallElementInSortedMatrix {
    //Leetcode 378
    /*Given an n x n matrix where each of the rows and columns are sorted in ascending order,
    return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example 1:
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

Example 2:
Input: matrix = [[-5]], k = 1
Output: -5*/
    static class ArrayComparator implements Comparable<ArrayComparator> {

        int[] arr;
        int index;

        public ArrayComparator(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        @Override
        public int compareTo(ArrayComparator arrayComparator) {
            return this.arr[this.index] - arrayComparator.arr[arrayComparator.index];
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int total = 0;
        PriorityQueue<ArrayComparator> queue = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            ArrayComparator comparator = new ArrayComparator(matrix[i], 0);
            queue.add(comparator);
            total += matrix[i].length;
        }
        int[] result = new int[total];
        int index = 0;
        while (!queue.isEmpty()) {
            ArrayComparator comp = queue.poll();
            result[index++] = comp.arr[comp.index];
            if (comp.index < comp.arr.length - 1) {
                queue.add(new ArrayComparator(comp.arr, comp.index + 1));
            }
        }
        if (result.length >= k) {
            return result[k - 1];
        }
        return -1;
    }
}
