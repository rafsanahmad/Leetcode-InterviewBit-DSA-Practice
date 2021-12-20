/*
 * *
 *  * Happy Number.java
 *  * Created by Rafsan Ahmad on 12/21/21, 12:21 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

import java.util.HashSet;

public class HappyNumber {
    /*Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not
include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

Example 1:
Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

Example 2:
Input: n = 2
Output: false

Constraints:

1 <= n <= 231 - 1
*/
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        int nextNum = getNextNumber(n);
        while (nextNum != 1 && !set.contains(nextNum)) {
            set.add(nextNum);
            nextNum = getNextNumber(nextNum);
        }
        if (nextNum == 1) return true;
        return false;
    }

    public int getNextNumber(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num = num / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        HappyNumber number = new HappyNumber();
        System.out.println(number.isHappy(19));
        System.out.println(number.isHappy(17));
        System.out.println(number.isHappy(2));
        System.out.println(number.isHappy(231));
    }
}
