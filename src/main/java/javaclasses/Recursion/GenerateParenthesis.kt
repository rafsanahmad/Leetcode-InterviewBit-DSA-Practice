/*
 * * Generate Parenthesis.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Recursion

class GenerateParenthesis {
    //Leetcode 22
    /*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
*/
    fun generateParenthesis(n: Int): Array<String> {
        val result = ArrayList<String>()
        dfs(result, "", n, n)
        return result.toTypedArray()
    }

    fun dfs(result: ArrayList<String>, str: String, left: Int, right: Int) {
        if (left > right) return
        if (left == 0 && right == 0) {
            result.add(str)
            return
        }
        if (left > 0) {
            dfs(result, "$str(", left - 1, right)
        }
        if (right > 0) {
            dfs(result, "$str)", left, right - 1)
        }
    }
}

fun main(args: Array<String>) {
    val parenthesis = GenerateParenthesis()
    println(parenthesis.generateParenthesis(3).contentToString());
}