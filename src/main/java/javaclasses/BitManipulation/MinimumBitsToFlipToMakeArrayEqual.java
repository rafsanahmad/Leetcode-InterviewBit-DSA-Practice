/*
 * *
 *  * Minimum Bits To Flip To Make Array Element Equal.java
 *  * Created by Rafsan Ahmad on 6/27/23, 5:24 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.BitManipulation;

public class MinimumBitsToFlipToMakeArrayEqual {
    /*Given an array arr[] consisting of N positive integers, the task is to find the minimum
    number of bits of array elements required to be flipped to make all array elements equal.

Examples:

Input: arr[] = {3, 5}
Output: 2
Explanation:
Following are the flipping of bits of the array elements required:

For element arr[0](= 3): Flipping the 3rd bit from the right modifies arr[0] to (= 7(111)2).
Now, the array becomes {7, 5}.
For element arr[0](= 7): Flipping the 2nd bit from the right modifies arr[0] to 5 (= (101)2).
 Now, the array becomes {5, 5}.
After performing the above operations, all the array elements are equal. Therefore, the total
number of flips of bits required is 2.*/

    /*Approach:
    The given problem can be solved by modifying the array element in such a way that the number
    of set bits and unset bits at every position between all array elements. Follow the below steps
    to solve the problem:
    1. Initialize two frequency arrays say bit0[] and bit1[] of size 32 for counting the frequency
    of 0 and 1 for every bit of array elements.
    2. Traverse the given array and for each array element, arr[i] if the jth bit of arr[i] is a
    set bit, then increment the frequency of bit1[j] by 1. Otherwise, increment the frequency of
    bit0[j] by 1.
    3. After completing the above steps, print the sum of the minimum of bit0[i] and bit1[i]
    for each bit i over the range [0, 32].*/

    int makeEqual(int[] arr, int n) {
        // Stores the count of unset bits
        int[] bit0 = new int[33];
        // Stores the count of set bits
        int[] bit1 = new int[33];

        for (int i = 0; i < n; i++) {
            int x = arr[i];
            for (int j = 0; j < 33; j++) {

                // If current bit is set
                if ((x & 1) != 0) {
                    // Increment bit1[j]
                    bit1[j] += 1;
                } else {
                    // Increment bit0[j]
                    bit0[j] += 1;
                }
                // Right shift x by 1
                x = x >> 1;
            }
        }
        int ans = 0;

        for (int i = 0; i < 33; i++) {
            ans += Math.min(bit0[i], bit1[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        MinimumBitsToFlipToMakeArrayEqual flip = new MinimumBitsToFlipToMakeArrayEqual();
        int[] arr = {3, 5};
        int N = arr.length;

        System.out.print(flip.makeEqual(arr, N));
    }
}
