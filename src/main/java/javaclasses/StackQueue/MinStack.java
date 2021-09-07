package javaclasses.StackQueue;

import java.util.Stack;

public class MinStack {
    /*Design a Data Structure SpecialStack that supports all the stack operations like
    push(), pop(), isEmpty(), isFull() and an additional operation getMin() which should return
    minimum element from the SpecialStack.
    All these operations of SpecialStack must be O(1).
    Example:

Consider the following SpecialStack
16  --> TOP
15
29
19
18

When getMin() is called it should
return 15, which is the minimum
element in the current stack.

If we do pop two times on stack,
the stack becomes
29  --> TOP
19
18

When getMin() is called, it should
return 18 which is the minimum in
the current stack.

optimized O(1) time complexity and O(1) space complexity solution :
The idea is to store min element found till current insertion) along with all the elements as a reminder of a
DUMMY_VALUE, and the actual element as a multiple of the DUMMY_VALUE.
For example, while pushing an element ‘e’ into the stack, store it as (e * DUMMY_VALUE + minFoundSoFar),
this way we know what was the minimum value present in the stack at the time ‘e’ was being inserted.

To pop the actual value just return e/DUMMY_VALUE and set the new minimum as (minFoundSoFar % DUMMY_VALUE).

Note: Following method will fail if we try to insert DUMMY_VALUE in the stack, so we have to make our selection
of DUMMY_VALUE carefully.
Let’s say the following elements are being inserted in the stack – 3 2 6 1 8 5


d is dummy value.

s is wrapper stack

top is top element of the stack

min is the minimum value at that instant when the elements were inserted/removed

The following steps shows the current state of the above variables at any instant –

s.push(3);
min=3 //updated min as stack here is empty
s = {3*d + 3}
top = (3*d + 3)/d = 3

s.push(2);
min = 2 //updated min as min > current element
s = {3*d + 3-> 2*d + 2}
top = (2*d + 2)/d = 2
    */

    int min = -1; // sentinel value for min
    static int demoVal = 9999; // DEMO_VALUE
    Stack<Integer> st = new Stack<Integer>();

    void getMin() {
        System.out.println("min is: " + min);
    }

    void push(int val) {
        // if stack is empty OR current element is less than
        // min, update min..
        if (st.isEmpty() || val < min) {
            min = val;
        }

        st.push(val * demoVal
                + min); // encode the current value with
        // demoVal, combine with min and
        // insert into stack
        System.out.println("pushed: " + val);
    }

    int pop() {
        // if stack is empty return -1;
        if (st.isEmpty()) {
            System.out.println("stack underflow");
            return -1;
        }

        int val = st.pop();

        if (!st.isEmpty()) // if stack is empty, there would
            // be no min value present, so
            // make min as -1
            min = st.peek() % demoVal;
        else
            min = -1;
        System.out.println("popped: " + val / demoVal);
        return val / demoVal; // decode actual value from
        // encoded value
    }

    int peek() {
        return st.peek() / demoVal; // decode actual value
        // from encoded value
    }

    // Driver Code
    public static void main(String[] args) {
        MinStack s = new MinStack();

        int[] arr = {3, 2, 6, 1, 8, 5, 5, 5, 5};

        for (int i = 0; i < arr.length; i++) {
            s.push(arr[i]);
            s.getMin();
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            s.pop();
            s.getMin();
        }
    }
}
