/*
 * * Highest Product Of 3.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HighestProductOf3 {
/*
Given an integer array, find a maximum product of a triplet in the array.

Examples:

Input:  [10, 3, 5, 6, 20]
Output: 1200
Explanation: Multiplication of 10, 6 and 20

Input:  [-10, -3, -5, -6, -20]
Output: -90

Input:  [1, -4, 3, -6, 7, 0]
Output: 168

Approach 1:
1. Construct four auxiliary arrays leftMax[], rightMax[], leftMin[] and rightMin[] of
same size as input array.
2. Fill leftMax[], rightMax[], leftMin[] and rightMin[] in below manner.
    leftMax[i] will contain maximum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.
    leftMin[i] will contain minimum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.
    rightMax[i] will contain maximum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.
    rightMin[i] will contain minimum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.
3. For all array indexes i except first and last index, compute maximum of arr[i]*x*y where x can be leftMax[i] or leftMin[i] and y can be rightMax[i] or rightMin[i].
4. Return the maximum from step 3.
*/


    /*
    Time Complexity: O(n)
    Auxiliary Space: O(n)
    */
    static int maxProduct(int[] arr, int n) {
        // if size is less than 3, no triplet exists
        if (n < 3)
            return -1;

        // Construct four auxiliary vectors of size n and initialize them by -1
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        Arrays.fill(leftMin, -1);
        Arrays.fill(leftMax, -1);
        Arrays.fill(rightMax, -1);
        Arrays.fill(rightMin, -1);

        // will contain max product
        int max_product = Integer.MIN_VALUE;

        // to store maximum element on left of array
        int max_sum = arr[0];

        // to store minimum element on left of array
        int min_sum = arr[0];

        // leftMax[i] will contain max element on left of arr[i] excluding arr[i].
        // leftMin[i] will contain min element on left of arr[i] excluding arr[i].
        for (int i = 1; i < n; i++) {
            leftMax[i] = max_sum;
            if (arr[i] > max_sum)
                max_sum = arr[i];

            leftMin[i] = min_sum;
            if (arr[i] < min_sum)
                min_sum = arr[i];
        }

        // reset max_sum to store maximum element on right of array
        max_sum = arr[n - 1];

        // reset min_sum to store minimum element on right of array
        min_sum = arr[n - 1];

        // rightMax[i] will contain max element on right of arr[i] excluding arr[i].
        // rightMin[i] will contain min element on right of arr[i] excluding arr[i].
        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = max_sum;
            if (arr[j] > max_sum)
                max_sum = arr[j];

            rightMin[j] = min_sum;
            if (arr[j] < min_sum)
                min_sum = arr[j];
        }

        // For all array indexes i except first and
        // last, compute maximum of arr[i]*x*y where
        // x can be leftMax[i] or leftMin[i] and
        // y can be rightMax[i] or rightMin[i].
        for (int i = 1; i < n - 1; i++) {
            int max1 = Math.max(arr[i] * leftMax[i] * rightMax[i],
                    arr[i] * leftMin[i] * rightMin[i]);

            int max2 = Math.max(arr[i] * leftMax[i] * rightMin[i],
                    arr[i] * leftMin[i] * rightMax[i]);

            max_product = Math.max(max_product, Math.max(max1, max2));
        }

        return max_product;
    }

    /*Approach 2: Using priority queues.

1. Create two Priority Queues. The first one (pqmin) is of default order and the
Second one (pqmax) is of reverse order.
2. Iterate through the array and insert all elements in both priority queues.
3. Initialize a variable maximum with the first element in pqmax and remove it from
pqmax.
4. Create two variables product1 and product2.
product1= maximum*pqmax.poll()*pqmax.poll().
product2= maximum*pqmin.poll()*pqmin.poll().
5. return the greatest of product1 and product2.*/

    /*
    Time Complexity: O(nlogn)
    Auxiliary Space: O(n)
    */
    static int maxProduct2(int[] arr, int n) {
        // if size is less than 3, no triplet exists
        if (n < 3) {
            return -1;
        }
        // first priority queue of default order which is
        // lower value -> higher prioirty
        PriorityQueue<Integer> pqmin = new PriorityQueue<>();

        // second priority queue of rever order
        PriorityQueue<Integer> pqmax = new PriorityQueue<>(Comparator.reverseOrder());

        // iterating through array
        for (int i = 0; i < arr.length; i++) {
            pqmin.add(arr[i]);
            pqmax.add(arr[i]);
        }

        // initializing the maximum
        int maximum = pqmax.poll();
        // calculating product1
        int product1 = maximum * pqmax.poll() * pqmax.poll();
        // calculating product2
        int product2 = maximum * pqmin.poll() * pqmin.poll();

        // returning the maximum triplet product
        return product1 > product2 ? product1 : product2;
    }


    public static void main(String[] args) {
        int[] arr = {1, 4, 3, -6, -7, 0};
        int n = arr.length;
        int max = maxProduct(arr, n);
        if (max == -1)
            System.out.println("No Triplet Exists");
        else
            System.out.println("Maximum product is " + max);

        int max2 = maxProduct2(arr, n);
        System.out.println("Maximum product is " + max2);
    }
}
