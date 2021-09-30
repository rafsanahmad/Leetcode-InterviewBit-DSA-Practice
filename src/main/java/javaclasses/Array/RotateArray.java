package javaclasses.Array;

import java.util.Arrays;

public class RotateArray {
    //Leetcode 189
    /*Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]*/

    //Using 0(1) - Time limit exceeded
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int temp = 0;
        if (len == 0) {
            return;
        }
        while (k > 0) {
            int last = nums[len - 1];
            for (int i = 0; i < len; i++) {
                if (i == 0) {
                    temp = nums[i];
                    nums[i] = last;
                } else {
                    int prev = nums[i];
                    nums[i] = temp;
                    temp = prev;
                }

            }
            k--;
        }
    }


    /*Optimized solution:
    rotate k steps once for an element. Space O(1).
e.g. nums = [1 2 3 4 5 6], k=2
1st iteration: starts from nums[i=0], keep rotate nums[i+k] until meet the start position
      nums = [5 2 1 4 3 6]
2nd iteration: starts from nums[i=1]
      nums = [5 6 1 2 3 4]

How to determine the number of iterations?
Use the gcd (greatest common divisor) between n and k. Because we need to find how many "circles" are there
in the sequence, one circle is like counting each element nums[i] + k + k + k... and back to nums[i].
If the gcd is 1, which means only one circle will cover all the elements in the sequence, so only 1 iteration
is needed.If the gcd is 2, there are 2 circles, so 2 iterations are needed.*/

    private int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    private void rotateOptimized(int[] nums, int k) {
        int j, temp, prev;
        int len = nums.length;
        k = k % len;
        int gcd = gcd(len, k);
        for (int i = 0; i < gcd; i++) {
            prev = nums[i];
            j = i;
            while ((j + k) % len != i) {
                temp = nums[(j + k) % len];
                nums[(j + k) % len] = prev;
                prev = temp;
                j = (j + k) % len;
            }
            nums[(j + k) % len] = prev;
        }
    }

    public static void main(String[] args) {
        int[] ar = {-1, -100, 3, 99};
        new RotateArray().rotateOptimized(ar, 2);
        System.out.println(Arrays.toString(ar));
    }

}
