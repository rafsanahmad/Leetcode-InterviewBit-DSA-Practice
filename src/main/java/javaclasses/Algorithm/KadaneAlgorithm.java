/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Algorithm;

public class KadaneAlgorithm {
    /*A simple idea of Kadane’s algorithm is to look for all positive contiguous segments of the array and keep
    track of the maximum sum contiguous subarray among all positive segments.

First, we will consider two elements, one which stores the maximum end of the subarray and another which stores
the maximum sum so far.

Let these two variables be max_ending_here and max_so_far, respectively.
We will initialise both of them to 0.
Each time we get a positive sum, we compare it with max_so_far and update max_so_far if it is greater than it.
This logic is written in the form of an algorithm as follows:

1. Start
2. max_so_far = 0
3. max_ending_here = 0
4. Loop for each element of the array
       1. max_ending_here = max_ending_here + a[i]
       2. if(max_ending_here < 0)
          max_ending_here = 0

       4. if(max_so_far < max_ending_here)
          max_so_far = max_ending_here

5. return max_so_far*/


    public int kadane(int[] arr, int n) {
        //stores maximum sum subarray found so far

        int max_so_far = 0;

        //stores the maximum sum of subarray ending at the current position

        int max_ending_here = 0;

        //traverse the given array

        for (int i = 0; i < n; i++) {
            /* update maximum sum of subarray "ending" at index i (by adding the current element to maximum sum ending at previous index i-1) */

            max_ending_here = max_ending_here + arr[i];

            /* if maximum sum is negative, set it to 0 (which represents an empty sub-array) */
            if (max_ending_here < 0) {
                max_ending_here = 0;
            }

            //update result if current subarray sum is found to be greater
            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
            }
        }
        return max_so_far;
    }

    public static void main(String[] args) {
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        int n = arr.length;
        System.out.println(new KadaneAlgorithm().kadane(arr, n));
    }


}
