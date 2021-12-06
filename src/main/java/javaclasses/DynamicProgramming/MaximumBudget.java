/*
 * * Maximum Budget.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

public class MaximumBudget {
    /*Given 'budget' and prices for each category of ads (mxn int array), pick one price from each
    category and maximize the budget utilization

budget = 30
adPrices=
[3,1,5,7,10]
[7,9,2,20,10]
[3,1,9,45,51]

ans = 30 (take 7 from first, 20 from second and 3 from third category)*/

    public int closest(int[][] array, int target) {
        int m = array.length;
        int n = array[0].length;
        boolean[][] dp = new boolean[m][target + 1];
        for (int num : array[0]) {
            if (num <= target) {
                dp[0][num] = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int num : array[i]) {
                for (int j = 0; j <= target; j++) {
                    if (dp[i - 1][j] && num + j <= target) {
                        dp[i][j + num] = true;
                    }
                }
            }
        }
        for (int i = target; i >= 0; i--) {
            if (dp[m - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        MaximumBudget budget = new MaximumBudget();
        int[][] arr = {
                {3, 1, 5, 7, 10},
                {7, 9, 2, 20, 10},
                {3, 1, 9, 45, 51}
        };
        System.out.println(budget.closest(arr, 30));
    }
}
