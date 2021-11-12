/*
 * *
 *  * Created by Rafsan Ahmad on 11/12/21, 10:30 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import jdk.nashorn.internal.parser.JSONParser;
import netscape.javascript.JSObject;

public class MinCost_MakeArrayElementEqual {

    //https://www.codingninjas.com/codestudio/problems/make-array-elements-equal_1806889
    /*Problem Statement
You are given an array of integers of size ‘N’. You have to make the elements of the array equal,
and the cost of changing the element ‘x’ to ‘y’ is abs(x -y).
Your task is to find the minimum cost to make the elements of the array equal.

For Example:
Consider ARR = [3, 4, 5] now suppose if we want to change every element value to 4, then the cost of changing
3 to 4 is |3 - 4| which is 1, and the cost of changing 5 to 4 is |5 - 4| which is 1,
so the total cost is 1 + 1 = 2. The value 2 is the minimum cost to make all values in the array equal.
Hence, the answer is 2.

Input Format:
The first line of the input contains an integer ‘T’ denoting the number of test cases.
The first line of each test case contains a single integer, ‘N’, denoting the number of elements in the array.
The second line of each test case contains 'N' space-separated integers denoting the elements of the array.

Output Format:
For each test case, print the minimum cost to make elements of the array equal.

Print the output of each test case in a separate line.
Constraints:
1 <= T <= 5
1 <= N <= 10^5
1 <= ARR[i] <= 10^8

Time limit: 1 sec
Sample Input 1:
2
3
1 8 19
4
2 17 26 11
Sample Output 1:
18
30

Explanation:
In test case 1
We can change all element’s value to 8 with cost |1-8| + |19 - 8| = 18. The value 18 is the minimum cost
to make all values in the array equal. Hence, the answer is 18.

In test case 2
We can change all element’s value to 11 with cost |2 - 11| + |17 - 11| + |26 - 11| = 30. The value 30 is the
minimum cost to make all values in the array equal. Hence, the answer is 30.

Sample Input 2:
1
3
1 100 101
Sample Output 2:
100
*/

    public int findMinimumCost(int[] arr) {
        int minCost = Integer.MAX_VALUE;
        int currentCost = 0;
        int lowerLimit = Arrays.stream(arr).min().getAsInt();
        int upperLimit = Arrays.stream(arr).max().getAsInt();

        for (int val = lowerLimit; val < upperLimit; val++) {
            for (int j = 0; j < arr.length; j++) {
                int diff = Math.abs(arr[j] - val);
                currentCost = currentCost + diff;
            }
            if (currentCost < minCost) minCost = currentCost;
            currentCost = 0;
        }
        return minCost;
    }


    public static void main(String[] args) {
        MinCost_MakeArrayElementEqual equal = new MinCost_MakeArrayElementEqual();
        int[] arr = {3, 4, 5};
        int[] arr2 = {1, 8, 19};
        int[] arr3 = {2, 17, 26, 11};
        int[] arr4 = {2, 100, 101};
        System.out.println(equal.findMinimumCost(arr));
        System.out.println(equal.findMinimumCost(arr2));
        System.out.println(equal.findMinimumCost(arr3));
        System.out.println(equal.findMinimumCost(arr4));
    }
}
