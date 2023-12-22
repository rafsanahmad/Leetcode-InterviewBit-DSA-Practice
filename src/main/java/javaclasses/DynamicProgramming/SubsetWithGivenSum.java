/*
 * *
 *  * Subset With Given Sum.java
 *  * Created by Rafsan Ahmad on 1/25/22, 7:49 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class SubsetWithGivenSum {
    /*Given an array of integers and a sum, the task is to print all subsets of the given array with a sum equal
    to a given sum.
Examples:

Input : arr[] = {2, 3, 5, 6, 8, 10}
        sum = 10
Output : 5 2 3
         2 8
         10

Input : arr[] = {1, 2, 3, 4, 5}
        sum = 10
Output : 4 3 2 1
         5 3 2
         5 4 1 */

    /*Approach 1: (Using Recursion)
Algorithm:
Below is the algorithm for the subset sum problem using recursion:

1. At every index in the array you will be having two choices i.e., to include the element
 in the subset or else not include it in the subset.
2. If you include that element it means that the target sum has to be decremented by the
value of that element and the index should be decremented to the next index.
3. But you are allowed to include that element only when its value is less than target_sum
 at the moment.
4. If you don't include then the target sum remains the same and we need to decrement the
index to the following index.
5. Using these two statements we will be writing our recurrence relation.
6. The base cases in this approach can be when the target sum is equal to zero which means
there is a subset whose sum is equal to the target sum which is given. So, we return true.
And the other base can be when the index reaches 0, which means we didn't find a subset-sum
 which is equal to the given target sum. So, we return false.*/

    /*Time complexity: O(2^N), where N is the total number of elements in the given set.

For every element we are calling the two recurrence relation:
To include the element in the subset
To not include the element in the subset
So the worst-case time complexity turns out to be O(2^N)
where N is the number of elements in the given set or an array.
Space Complexity: O(N), where N is the total number of elements in the given set.*/
    List<List<Integer>> SubsetSumRec(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<>();
        SubsetSumHelper(numbers, numbers.length - 1, target, new ArrayList<>(), result);
        return result;
    }

    // This recursive function returns true if there exists a sum, else false
    void SubsetSumHelper(int[] numbers, int N, int target_sum, List<Integer> list,
                         List<List<Integer>> result) {
        // Base Case 1 If the target sum reaches 0, we have found a subset-sum equal to
        // target sum
        if (target_sum == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        // Base Case 2, if we reach the 0 indexes without finding the subset sum
        if (N < 0) {
            return;
        }
        //If we find an element larger than the target we just don't include it in the subset
        if (numbers[N] > target_sum) {
            //We recur without changing the sum and decrementing the index
            SubsetSumHelper(numbers, N - 1, target_sum, list, result);
            return;
        }

        //If the number is not greater than target_sum we call both recurrence relations
        //If any of the below gives true, we have found the case.
        SubsetSumHelper(numbers, N - 1, target_sum, list, result);
        list.add(numbers[N]);
        SubsetSumHelper(numbers, N - 1, target_sum - numbers[N], list, result);
        list.remove(Integer.valueOf(numbers[N]));
    }


    /*Approach 2: Using Dynamic Programming*/
    //Time complexity: O(N*target_sum)
    List<List<Integer>> SubsetSumDP(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // the value of dp[i][j] is true then there exists a subset sum equals to target_sum
        int N = numbers.length;
        boolean[][] dp = new boolean[N][target + 1];
        for (int i = 0; i < N; ++i) {
            dp[i][0] = true;
        }

        // Sum arr[0] can be achieved with single element
        if (numbers[0] <= target)
            dp[0][numbers[0]] = true;

        // Fill rest of the entries in dp[][]
        for (int i = 1; i < N; i++)
            for (int j = 0; j < target + 1; j++) {
                if (numbers[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - numbers[i]];
                }
            }

        if (!dp[N - 1][target])
            return result;


        // Now recursively traverse dp[][] to find all
        // paths from dp[n-1][sum]
        printSubsetsRec(numbers, N - 1, target, new ArrayList<>(), dp, result);
        return result;
    }

    void printSubsetsRec(int[] arr, int i, int sum, ArrayList<Integer> list, boolean[][] dp,
                         List<List<Integer>> result) {
        // If we reached end and sum is non-zero. We add to list
        // only if arr[0] is equal to sum OR dp[0][sum] is true.
        if (i == 0 && sum != 0 && dp[0][sum]) {
            list.add(arr[i]);
            result.add(new ArrayList<>(list));
            list.remove(Integer.valueOf(arr[i]));
            return;
        }

        // If sum becomes 0
        if (sum == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        if (i < 0) return;

        // If given sum can be achieved after ignoring current element.
        if (dp[i - 1][sum]) {
            printSubsetsRec(arr, i - 1, sum, list, dp, result);
        }

        // If given sum can be achieved after considering current element.
        if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
            list.add(arr[i]);
            printSubsetsRec(arr, i - 1, sum - arr[i], list, dp, result);
            list.remove(Integer.valueOf(arr[i]));
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        SubsetWithGivenSum sum = new SubsetWithGivenSum();
        System.out.println(sum.SubsetSumRec(arr, 21));
        System.out.println(sum.SubsetSumDP(arr, 21));
    }
}
