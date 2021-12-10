/*
 * * Stack Using Queues.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {

/*A stack can be implemented using two queues. Let stack to be implemented be ‘s’ and queues used to
implement be ‘q1’ and ‘q2’. Stack ‘s’ can be implemented in two ways:

Method 1 (By making push operation costly)
This method makes sure that newly entered element is always at the front of ‘q1’, so that pop operation
just dequeues from ‘q1’. ‘q2’ is used to put every new element at front of ‘q1’.

push(s, x) operation’s step are described below:
Enqueue x to q2
One by one dequeue everything from q1 and enqueue to q2.
Swap the names of q1 and q2
pop(s) operation’s function are described below:
Dequeue an item from q1 and return it.*/

    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();

    static int curr_size;

    StackUsingQueues() {
        curr_size = 0;
    }

    static void push(int x) {
        curr_size++;

        //push to q2
        q2.add(x);

        //Push all the remaining
        //elements in q1 to q2
        while (!q1.isEmpty()) {
            q2.add(q1.peek());
            //printQueue(q2);
            q1.remove();
            //printQueue(q1);
        }

        //Swap two queues
        Queue<Integer> q = q1;
        q1 = q2;
        q2 = q;
    }

    static void pop() {
        //pop from q1
        if (q1.isEmpty()) {
            return;
        }
        q1.remove();
        curr_size--;
    }

    static int top() {
        if (q1.isEmpty()) {
            return -1;
        }
        return q1.peek();
    }

    static int size() {
        return curr_size;
    }

    static void printQueue(Queue queue) {
        for (Object s : queue) {
            System.out.println("Poll " + s.toString());
        }
    }

    // driver code
    public static void main(String[] args) {
        StackUsingQueues s = new StackUsingQueues();
        s.push(1);
        s.push(2);
        s.push(3);

        System.out.println("current size: " + s.size());
        System.out.println(s.top());
        s.pop();
        System.out.println(s.top());
        s.pop();
        System.out.println(s.top());

        System.out.println("current size: " + s.size());
    }
}
