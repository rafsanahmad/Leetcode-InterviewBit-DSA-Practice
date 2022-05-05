/*
 * *
 *  * Pair Sum.java
 *  * Created by Rafsan Ahmad on 5/5/22, 7:42 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PairSum {
    //https://www.codingninjas.com/codestudio/problems/pair-sum_697295?leftPanelTab=0
    /*Problem Statement
You are given an integer array 'ARR' of size 'N' and an integer 'S'. Your task is to return the list of all pairs of
elements such that each sum of elements of each pair equals 'S'.
Note:
Each pair should be sorted i.e the first value should be less than or equals to the second value.

Return the list of pairs sorted in non-decreasing order of their first value. In case if two pairs have the same first
value, the pair with a smaller second value should come first.
Input Format:
The first line of input contains two space-separated integers 'N' and 'S', denoting the size of the input array and the
value of 'S'.

The second and last line of input contains 'N' space-separated integers, denoting the elements of the input array: ARR[i]
where 0 <= i < 'N'.
Output Format:
Print 'C' lines, each line contains one pair i.e two space-separated integers, where 'C' denotes the count of pairs
having sum equals to given value 'S'.
Note:
You are not required to print the output, it has already been taken care of. Just implement the function.
Constraints:
1 <= N <= 10^3
-10^5 <= ARR[i] <= 10^5
-2 * 10^5 <= S <= 2 * 10^5

Time Limit: 1 sec
Sample Input 1:
5 5
1 2 3 4 5
Sample Output 1:
1 4
2 3
Explaination For Sample Output 1:
Here, 1 + 4 = 5
      2 + 3 = 5
Hence the output will be, (1,4) , (2,3).

Sample Input 2:
5 0
2 -3 3 3 -2
Sample Output 2:
-3 3
-3 3
-2 2
*/
    public static int[][] pairSum(int[] arr, int s) {
        // Write your code here.
        Arrays.sort(arr);
        List<int[]> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(s - arr[i])) {
                int count = map.get(s - arr[i]);
                for (int j = 0; j < count; j++) {
                    int[] pair = {s - arr[i], arr[i]};
                    result.add(pair);
                }
            }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int[][] resultArr = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        Arrays.sort(resultArr, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        return resultArr;
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, -6, 2, 5, 2};
        int[][] res = pairSum(arr, 4);
        printArray(res);
    }

}
