/*
 * * Min Stack.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

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


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}
