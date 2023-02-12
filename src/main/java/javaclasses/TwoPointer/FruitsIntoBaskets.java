/*
 * *
 *  * Fruit Into Baskets.java
 *  * Created by Rafsan Ahmad on 2/12/23, 2:00 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.TwoPointer;

import java.util.HashMap;
import java.util.Map;

public class FruitsIntoBaskets {
    //https://leetcode.com/problems/fruit-into-baskets/
    /*You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented
    by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit
each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree)
while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.


Example 1:
Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.

Example 2:
Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].

Example 3:
Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


Constraints:

1 <= fruits.length <= 10^5
0 <= fruits[i] < fruits.length*/

    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruit = 0;

        for (int right = 0; right < fruits.length; ++right) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0)
                    basket.remove(fruits[left]);
                left++;
            }

            maxFruit = Math.max(maxFruit, right - left + 1);
        }

        return maxFruit;
    }

    public static void main(String[] args) {
        FruitsIntoBaskets baskets = new FruitsIntoBaskets();

        int[] arr = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(baskets.totalFruit(arr));
    }
}
