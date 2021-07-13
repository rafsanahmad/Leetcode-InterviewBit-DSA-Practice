package javaclasses.Matrix;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeKSortedArray {
    //Same as Leetcode 23
    //This problem can be solved by using a Heap
    //Time complexity 0(nlog(k))

    static class ArrayContainer implements Comparable<ArrayContainer> {

        int[] arr;
        int index;

        public ArrayContainer(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        @Override
        public int compareTo(ArrayContainer container) {
            return this.arr[this.index] - container.arr[container.index];
        }
    }

    public static int[] mergeKSortedArray(int[][] arr) {
        //PriorityQueue is heap in Java
        PriorityQueue<ArrayContainer> queue = new PriorityQueue<>();
        int total = 0;

        //Add arrays to Heap
        for (int i = 0; i < arr.length; i++) {
            queue.add(new ArrayContainer(arr[i], 0));
            total = total + arr[i].length;
        }
        int m = 0;
        int[] result = new int[total];

        //while heap is not empty
        while (!queue.isEmpty()) {
            ArrayContainer ac = queue.poll();
            result[m++] = ac.arr[ac.index];
            if (ac.index < ac.arr.length - 1) {
                queue.add(new ArrayContainer(ac.arr, ac.index + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] arr3 = {0, 9, 10, 11};

        int[] result = mergeKSortedArray(new int[][]{arr1, arr2, arr3});
        System.out.println(Arrays.toString(result));
    }
}
