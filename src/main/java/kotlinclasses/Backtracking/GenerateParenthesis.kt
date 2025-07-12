/*
 * *
 *  * Generate Parentheses.kt
 *  * Created by Rafsan Ahmad on 7/10/25, 3:04PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Backtracking

class GenerateParenthesis {
    //https://leetcode.com/problems/generate-parentheses/description/
    /*Given n pairs of parentheses, write a function to generate all combinations of well-formed
    parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

Constraints:
1 <= n <= 8
*/
    fun generateParenthesis(n: Int): List<String> {
        if (n == 0) return listOf()
        val list: MutableList<String> = mutableListOf()
        val builder = StringBuilder()
        parenthesisBacktrack(n, n, builder, list)
        return list
    }

    fun parenthesisBacktrack(
        left: Int,
        right: Int,
        builder: StringBuilder,
        list: MutableList<String>
    ) {
        // invalid: more ')' than '('
        if (left > right) return
        if (left == 0 && right == 0) {
            list.add(builder.toString())
            return
        }

        if (left > 0) {
            builder.append("(")
            parenthesisBacktrack(left - 1, right, builder, list)
            builder.deleteAt(builder.length - 1)
        }

        if (right > 0) {
            builder.append(")")
            parenthesisBacktrack(left, right - 1, builder, list)
            builder.deleteAt(builder.length - 1)
        }
    }
}

fun main(args: Array<String>) {
    val parenthesis = GenerateParenthesis()
    println(parenthesis.generateParenthesis(3));
}