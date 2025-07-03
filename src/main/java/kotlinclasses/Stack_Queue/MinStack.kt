/*
 * *
 *  * Min Stack.kt
 *  * Created by Rafsan Ahmad on 7/3/25, 12:52PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Stack_Queue

import kotlin.math.min

class MinStack {
    //https://leetcode.com/problems/min-stack/description/
    /*Design a stack that supports push, pop, top, and retrieving the minimum element in
    constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.


Example 1:
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2


Constraints:
-2^31 <= val <= 2^31 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 1^04 calls will be made to push, pop, top, and getMin.*/

    class StackNode(
        var value: Int,
        var min: Int,
        var next: StackNode? = null
    )

    var top: StackNode? = null

    fun push(`val`: Int) {
        val min = top?.min?.let { min(it, `val`) } ?: `val`
        val node = StackNode(`val`, min)
        node.next = top
        top = node
    }

    fun pop() {
        top?.let { node ->
            val temp = node.next
            top = temp
        }
    }

    fun top(): Int {
        top?.let { return it.value }
        return -1
    }

    fun getMin(): Int {
        top?.let { return it.min }
        return -1
    }
}

fun main() {
    val obj = MinStack()
    obj.push(-2)
    obj.push(0)
    obj.push(-3)
    println(obj.getMin()) // return -3
    obj.pop()
    println(obj.top())    // return 0
    println(obj.getMin()) // return -2
}