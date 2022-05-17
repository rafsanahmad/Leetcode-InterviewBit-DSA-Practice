/*
 * *
 *  * Frog Jump.java
 *  * Created by Rafsan Ahmad on 5/17/22, 2:16 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

import java.util.Arrays;

public class FrogJump {
    //https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/
    //res/recursion_tree_frog_jump.jpeg
    /*Problem Statement
There is a frog on the 1st step of an N stairs long staircase. The frog wants to reach the Nth stair.
HEIGHT[i] is the height of the (i+1)th stair.If Frog jumps from ith to jth stair, the energy lost in the jump is given
by |HEIGHT[i-1] - HEIGHT[j-1] |.In the Frog is on ith staircase, he can jump either to (i+1)th stair or to (i+2)th stair.
Your task is to find the minimum total energy used by the frog to reach from 1st stair to Nth stair.

For Example
If the given ‘HEIGHT’ array is [10,20,30,10], the answer 20 as the frog can jump from 1st stair to 2nd stair (|20-10| = 10
energy lost) and then a jump from 2nd stair to last stair (|10-20| = 10 energy lost). So, the total energy lost is 20.

Input Format:
The first line of the input contains an integer, 'T,’ denoting the number of test cases.

The first line of each test case contains a single integer,' N’, denoting the number of stairs in the staircase,

The next line contains ‘HEIGHT’ array.
Output Format:
For each test case, return an integer corresponding to the minimum energy lost to reach the last stair.
Note:
You do not need to print anything. It has already been taken care of. Just implement the given function.
Constraints:
1 <= T <= 10
1 <= N <= 100000.
1 <= HEIGHTS[i] <= 1000 .

Time limit: 1 sec

Sample Input 1:
2
4
10 20 30 10
3
10 50 10

Sample Output 1:
20
0

Explanation Of Sample Input 1:
For the first test case,
The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
Then a jump from the 2nd stair to the last stair (|10-20| = 10 energy lost).
So, the total energy lost is 20 which is the minimum.
Hence, the answer is 20.

For the second test case:
The frog can jump from 1st stair to 3rd stair (|10-10| = 0 energy lost).
So, the total energy lost is 0 which is the minimum.
Hence, the answer is 0.

Sample Input 2:
2
8
7 4 4 2 6 6 3 4
6
4 8 3 10 4 4

Sample Output 2:
7
2*/
    public static int frogJump(int n, int[] heights) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return frogJumpRec(n - 1, heights, dp);
    }

    public static int frogJumpRec(int n, int[] heights, int[] dp) {
        //base case
        if (n == 0) return 0;
        if (dp[n] != -1) return dp[n];
        int jumpOne = frogJumpRec(n - 1, heights, dp) + Math.abs(heights[n] - heights[n - 1]);
        int jumpTwo = Integer.MAX_VALUE;
        if (n > 1) {
            jumpTwo = frogJumpRec(n - 2, heights, dp) + Math.abs(heights[n] - heights[n - 2]);
        }
        return dp[n] = Math.min(jumpOne, jumpTwo);
    }

    public static void main(String[] args) {
        int[] ar = {7, 4, 4, 2, 6, 6, 3, 4};
        System.out.println(frogJump(8, ar));
    }
}
