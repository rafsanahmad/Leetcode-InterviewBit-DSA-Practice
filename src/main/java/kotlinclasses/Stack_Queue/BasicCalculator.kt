/*
 * *
 *  * Basic Calculator.kt
 *  * Created by Rafsan Ahmad on 8/20/25, 9:50PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Stack_Queue

import java.util.Stack

class BasicCalculator {
    //https://leetcode.com/problems/basic-calculator/description/
    /*Given a string s representing a valid expression, implement a basic calculator to evaluate it,
    and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical
expressions, such as eval().

Example 1:
Input: s = "1 + 1"
Output: 2

Example 2:
Input: s = " 2-1 + 2 "
Output: 3

Example 3:
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23


Constraints:
1 <= s.length <= 3 * 10^5
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.*/


    /*Time complexity: O(n)
Space complexity: O(n) (for stack, due to parentheses nesting)*/
    fun calculate(s: String): Int {
        val stack = Stack<Int>()
        var result = 0          // running total for current expression
        var num = 0             // current number being built
        var sign = 1            // +1 for '+', -1 for '-'
        var i = 0

        while (i < s.length) {
            val ch = s[i]

            when {
                ch.isDigit() -> {
                    num = 0
                    // build full number (handles multi-digit)
                    while (i < s.length && s[i].isDigit()) {
                        num = num * 10 + (s[i] - '0')
                        i++
                    }
                    // apply sign and add to result
                    result += sign * num
                    continue  // skip i++ since already moved inside loop
                }

                ch == '+' -> sign = 1      // update sign
                ch == '-' -> sign = -1     // update sign

                ch == '(' -> {
                    // save current state before new sub-expression
                    stack.push(result)     // previous result
                    stack.push(sign)       // previous sign
                    // reset for new sub-expression
                    result = 0
                    sign = 1
                }

                ch == ')' -> {
                    // close sub-expression:
                    // multiply with saved sign, then add to saved result
                    result = stack.pop() * result + stack.pop()
                }
            }
            i++
        }

        return result
    }
}

fun main() {
    val obj = BasicCalculator()
    println(obj.calculate("(1+(4+5+2)-3)+(6+8)"))
}