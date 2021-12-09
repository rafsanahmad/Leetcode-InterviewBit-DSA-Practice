/*
 * * Best Time To Buy Stock II.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

public class BestTimeToBuyStockII {
    //Leetcode 122
    /*You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you
buy again).

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions
at the same time.
You must sell before buying again.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e., max profit = 0.*/

/*Time complexity : O(n). Single pass.
Space complexity : O(1). Constant space required.*/

    public int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peek = prices[0];
        int maxprofit = 0;

        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];

            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peek = prices[i];

            maxprofit += peek - valley;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        BestTimeToBuyStockII stock = new BestTimeToBuyStockII();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(stock.maxProfit(prices));
    }
}
