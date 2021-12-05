/*
 *  * Sorting Algorithm.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Algorithm;

import java.util.Arrays;

public class SortingAlgorithm {
    //res/merge_sort.png
    /*
    Example of merge sort in Java
    merge() function take two intervals one from start to mid second from mid+1, to end and merge them in sorted order
    Time complexity of Merge Sort is  θ(nLogn) in all 3 cases (worst, average and best) as merge sort
    always divides the array into two halves and takes linear time to merge two halves.
Auxiliary Space: O(n)
Algorithmic Paradigm: Divide and Conquer*/

    void merge(int[] Arr, int start, int mid, int end) {

        // create a temp array
        int[] temp = new int[end - start + 1];

        // crawlers for both intervals and for temp
        int i = start, j = mid + 1, k = 0;

        // traverse both arrays and in each iteration add smaller of both elements in temp
        while (i <= mid && j <= end) {
            if (Arr[i] <= Arr[j]) {
                temp[k] = Arr[i];
                k += 1;
                i += 1;
            } else {
                temp[k] = Arr[j];
                k += 1;
                j += 1;
            }
        }

        // add elements left in the first interval
        while (i <= mid) {
            temp[k] = Arr[i];
            k += 1;
            i += 1;
        }

        // add elements left in the second interval
        while (j <= end) {
            temp[k] = Arr[j];
            k += 1;
            j += 1;
        }

        // copy temp to original interval
        for (i = start; i <= end; i += 1) {
            Arr[i] = temp[i - start];
        }
    }

// Arr is an array of integer type
// start and end are the starting and ending index of current interval of Arr

    void mergeSort(int[] Arr, int start, int end) {

        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(Arr, start, mid);
            mergeSort(Arr, mid + 1, end);
            merge(Arr, start, mid, end);
        }
    }


    //Worst and Average Case Time Complexity: O(n^2)
    //res/bubble_sort.gif
    void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void main(String[] args) {
        SortingAlgorithm algorithm = new SortingAlgorithm();
        int[] ar = {14, 9, 0, 3, 10, 7, 2, 20, -1};
        algorithm.mergeSort(ar, 0, 8);
        System.out.println("Merge Sort Result: " + Arrays.toString(ar));
        algorithm.bubbleSort(ar);
        System.out.println("Bubble Sort Result: " + Arrays.toString(ar));
    }
}
