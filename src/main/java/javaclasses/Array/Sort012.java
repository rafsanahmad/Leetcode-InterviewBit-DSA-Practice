/*
 * *
 *  * Sort012.java
 *  * Created by Rafsan Ahmad on 4/20/22, 8:55 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.stream.IntStream;

//https://www.codingninjas.com/codestudio/problems/sort-0-1-2_631055?leftPanelTab=0
public class Sort012 {
    /*Problem Statement
You have been given an integer array/list(ARR) of size 'N'. It only contains 0s, 1s and 2s.
Write a solution to sort this array/list.
Note :
Try to solve the problem in 'Single Scan'. ' Single Scan' refers to iterating over the array/list just once or to
 put it in other words, you will be visiting each element in the array/list just once.
Input Format :
The first line contains an integer 'T' which denotes the number of test cases or queries to be run. Then the
test cases follow.

The first line of each test case contains an Integer 'N' denoting the size of the array/list.

The second line of each test case contains 'N' space-separated Integers denoting the array/list.
Output Format :
For each test case/query, print the sorted array/list(ARR) as space-separated Integers.

Output for every test case will be printed in a separate line.
Note:
You need to change in the given array/list itself. Hence, no need to return or print anything.
Constraints :
1 <= T <= 10
1 <= N <= (5 * (10 ^ 5))
0 <= ARR[i] <= 2

Where 'N' is the size of the given array/list.
And, ARR[i] denotes the i-th element in the array/list.

Time Limit: 1sec
Sample Input 1 :
2
6
0 1 2 2 1 0
7
0 1 2 1 2 1 2
Sample Output 1 :
0 0 1 1 2 2
0 1 1 1 2 2 2

Sample Input 2 :
2
7
2 2 2 1 1 1 0
6
2 1 2 0 1 0
Sample Output 2 :
0 1 1 1 2 2 2
0 0 1 1 2 2*/

    public static void sort012(int[] arr) {
        int[] data = new int[3];
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i];
            data[index]++;
        }
        int numOfZeros = data[0];
        IntStream.range(0, numOfZeros).forEach(val -> arr[val] = 0);

        int numOfOnes = data[1];
        IntStream.range(numOfZeros, numOfOnes + numOfZeros).forEach(val -> arr[val] = 1);

        int numOfTwos = data[2];
        IntStream.range(numOfZeros + numOfOnes, numOfOnes + numOfZeros + numOfTwos).forEach(val -> arr[val] = 2);
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 1, 0};
        sort012(arr);
        System.out.println(Arrays.toString(arr));
    }
}
