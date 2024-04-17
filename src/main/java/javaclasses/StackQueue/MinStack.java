/*
 * * Min Stack.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.Stack;

public class MinStack {

    //https://leetcode.com/problems/min-stack/
    /*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.


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

To make constant time of getMin(), we need to keep track of the minimum element for each element in the stack.
Define an element class that holds element value, min value, and pointer to elements below it.
    */

    class Elem {
        public int value;
        public int min;
        public Elem next;

        public Elem(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    public Elem top;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        if (top == null) {
            top = new Elem(x, x);
        } else {
            Elem e = new Elem(x, Math.min(x, top.min));
            e.next = top;
            top = e;
        }

    }

    public void pop() {
        if (top == null)
            return;
        Elem temp = top.next;
        top.next = null;
        top = temp;

    }

    public int top() {
        if (top == null)
            return -1;
        return top.value;
    }

    public int getMin() {
        if (top == null)
            return -1;
        return top.min;
    }


    //Using 2 Stack
    /*using two stacks, push value as usually, but at mean time, push the top of another stack, minStack,
    or the current value onto minStack, whichever is smaller.*/
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push2(int x) {
        if (stack.empty()) {
            stack.push(x);
            minStack.push(x);
        } else {
            stack.push(x);
            if (minStack.peek() > x) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
        }
    }

    public void pop2() {
        if (stack.empty()) {
            //throw exception
        } else {
            stack.pop();
            minStack.pop();
        }
    }

    public int top2() {
        if (stack.empty()) {
            //throw Exception();
            return -1;
        } else {
            return stack.peek();
        }
    }

    public int getMin2() {
        if (minStack.empty()) {
            //throw Exception
            return -1;
        } else {
            return minStack.peek();
        }
    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
        minStack.pop();
        System.out.println(minStack.getMin());

        //Using approach 2
        minStack.push2(-2);
        minStack.push2(0);
        minStack.push2(-3);
        System.out.println(minStack.getMin2()); // return -3
        minStack.pop2();
        System.out.println(minStack.top2());    // return 0
        System.out.println(minStack.getMin2()); // return -2
    }
}
