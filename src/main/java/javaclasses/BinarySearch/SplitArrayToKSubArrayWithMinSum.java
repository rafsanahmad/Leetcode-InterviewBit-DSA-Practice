/*
 * *
 *  * Split Array To K SubArray With Min Sum.java
 *  * Created by Rafsan Ahmad on 12/12/23, 2:53 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.BinarySearch;

public class SplitArrayToKSubArrayWithMinSum {
    /*Split the given array into K sub-arrays such that maximum sum of all sub arrays is minimum
Given an Array[] of N elements and a number K. ( 1 <= K <= N ) . Split the given array into K
subarrays (they must cover all the elements). The maximum subarray sum achievable out of K
subarrays formed, must be the minimum possible. Find that possible subarray sum.
Examples:

Input : Array[] = {1, 2, 3, 4}, K = 3
Output : 4
Optimal Split is {1, 2}, {3}, {4} . Maximum sum of all subarrays is 4, which is minimum
possible for 3 splits.
Input : Array[] = {1, 1, 2} K = 2
Output : 2

Naive approach:
Idea is to find out all the possibilities.
To find out all possibilities we use BACKTRACKING.
At each step, we divide the array into sub-array and find the sum of the sub-array and update
the maximum sum
*/

    public static int ans = 10000000;

    /*Time Complexity: O((N−1)c(K−1) (NOTE: ‘c’ here depicts combinations i.e.
    ((n-1)!/((n-k)!*(k-1)!)
     Where N is the number of elements of the array and K is the number of divisions.
     Space Complexity: O(K)
     */
    public static void solveUsingBackTracking(int[] a, int n, int k, int index, int sum,
                                              int maxsum) {
        // K=1 is the base Case
        if (k == 1) {
            maxsum = Math.max(maxsum, sum);
            sum = 0;
            for (int i = index; i < n; i++) {
                sum += a[i];
            }

            // we update maxsum
            maxsum = Math.max(maxsum, sum);
            // the answer is stored in ans
            ans = Math.min(ans, maxsum);
            return;
        }
        sum = 0;
        // using for loop to divide the array into K-subarray

        for (int i = index; i < n; i++) {
            sum += a[i];
            // for each subarray we calculate sum ans update maxsum
            maxsum = Math.max(maxsum, sum);
            // calling function again
            solveUsingBackTracking(a, n, k - 1, i + 1, sum, maxsum);
        }
    }

    /*Efficient Approach :

Idea is to use Binary Search to find an optimal solution.
1. For binary search minimum sum can be 1 and the maximum sum can be the sum of all the elements.
2. To check if mid is the maximum subarray sum possible. Maintain a count of sub-arrays, include
all possible elements in subarray until their sum is less than mid. After this evaluation,
if the count is less than or equal to K, then mid is achievable else not.
(Since if the count is less than K, we can further divide any subarray its sum will never
increase mid ).
3. Find the minimum possible value of mid which satisfies the condition.*/

    // Function to check if mid can be maximum sub - arrays sum
    static boolean check(int mid, int[] array, int n, int K) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            // If individual element is greater maximum possible sum
            if (array[i] > mid)
                return false;

            // Increase sum of current sub - array
            sum += array[i];

            // If the sum is greater than mid increase count
            if (sum > mid) {
                count++;
                sum = array[i];
            }
        }

        count++;

        // Check condition
        if (count <= K)
            return true;

        return false;
    }

    /*Time Complexity : O(N*log(Sum))
    Where N is the number of elements of the array and Sum is the sum of all the
    elements of the array.
    Auxiliary Space: O(1) as no extra space has been used.
    */
    static int solveUsingBinarySearch(int[] array, int n, int K) {
        int start = 1;
        for (int i = 0; i < n; ++i) {
            if (array[i] > start)
                start = array[i]; //Max subarray sum, considering subarray of length 1
        }

        int end = 0;
        for (int i = 0; i < n; i++) {
            end += array[i]; //Max subarray sum, considering subarray of length n
        }

        // Answer stores possible maximum sub array sum
        int answer = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            // If mid is possible solution Put answer = mid;

            if (check(mid, array, n, K)) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 3; // K divisions
        int n = 4; // Size of Array
        solveUsingBackTracking(arr, n, k, 0, 0, 0);
        System.out.println(ans);
        System.out.println(solveUsingBinarySearch(arr, n, k));
    }
}
