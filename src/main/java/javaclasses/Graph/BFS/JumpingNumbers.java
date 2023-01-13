/*
 * *
 *  * Jumping Numbers.java
 *  * Created by Rafsan Ahmad on 5/8/22, 12:43 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class JumpingNumbers {
    //https://www.codingninjas.com/codestudio/problems/jumping-numbers_1069220?leftPanelTab=0
    /*Problem Statement
You are given a positive integer N, your task is to find all the Jumping Numbers smaller than or equal to N.
A number is defined as a Jumping Number if all adjacent digits in it have an absolute difference of 1.
Example :
2, 23, and 4343456 are Jumping numbers but 296 and 89498 are not.
Note:
The difference between ‘9’ and ‘0’ is not considered as 1. All single-digit numbers are considered as Jumping Numbers.
Input Format:
The first line contains an integer 'T' denoting the number of test cases.

The only line of each test case contains an integer N.
Output Format:
For each test case, print all the Jumping Numbers smaller than or equal to N in ascending order.
Note:
You do not need to print anything, it has already been taken care of. Just implement the given function.
Constraints:
1 <= T <= 100
1 <= N <= 10^8

Time Limit: 1 sec

Sample Input 1:
1
20
Sample Output 1:
0 1 2 3 4 5 6 7 8 9 10 12
Explanation For Sample Input 1:
These are all the jumping numbers from 0 to 12 as all the single-digit numbers are jumping numbers and out of the
two-digit numbers only 10 and 12 are the jumping numbers less than 20 as the absolute difference in the adjacent digits
of 10 and 12 is 1.

Sample Input 2:
2
1
30
Sample Output 2:
0 1
0 1 2 3 4 5 6 7 8 9 10 12 21 23 */

    private static ArrayList<Integer> list;

    public static ArrayList<Integer> jumpingNumbers(int n) {
        // Write your code here.
        list = new ArrayList<>();
        list.add(0);
        for (int i = 1; i <= 9 && i <= n; i++) {
            bfs(n, i);
        }
        Collections.sort(list);
        return list;
    }

    // Prints all jumping numbers smaller than or equal to x starting
    // with 'num'. It mainly does BFS starting from 'num'.
    private static void bfs(int range, int start) {
        int currentNum = start;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(currentNum);

        // Do BFS starting from 0
        while (!q.isEmpty()) {
            currentNum = q.peek();
            q.poll();
            if (currentNum <= range) {
                list.add(currentNum);
                int last_digit = currentNum % 10;

                // If last digit is 0, append next digit only
                if (last_digit == 0) {
                    q.add((currentNum * 10) + (last_digit + 1));
                }

                // If last digit is 9, append previous digit only
                else if (last_digit == 9) {
                    q.add((currentNum * 10) + (last_digit - 1));
                }

                // If last digit is neither 0 nor 9, append both
                // previous and next digits
                else {
                    q.add((currentNum * 10) + (last_digit - 1));
                    q.add((currentNum * 10) + (last_digit + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(jumpingNumbers(30));
        System.out.println(jumpingNumbers(100));
        System.out.println(jumpingNumbers(200));
        System.out.println(jumpingNumbers(1000));
        System.out.println(jumpingNumbers(10000));
        System.out.println(jumpingNumbers(100000));
    }
}
