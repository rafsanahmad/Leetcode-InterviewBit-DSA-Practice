/*
 * *
 *  * DequeExample.java
 *  * Created by Rafsan Ahmad on 2/2/22, 12:32 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.DataStructure;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {

    public static void main(String[] args) {
        Deque<Integer> dequeue = new ArrayDeque<>();
        dequeue.offerFirst(20); // -> 20
        dequeue.offerFirst(21); // -> 21 20
        dequeue.offerFirst(22); // -> 22 21 20
        dequeue.offerLast(23);  // -> 22 21 20 23
        dequeue.offerLast(24);  // -> 22 21 20 23 24
        dequeue.offerLast(25);  // -> 22 21 20 23 24 25
        dequeue.offerFirst(26); // -> 26 22 21 20 23 24 25
        dequeue.offerFirst(27); // -> 27 26 22 21 20 23 24 25
        System.out.println("dequeue = " + dequeue);

        System.out.println();
        System.out.println("dequeue.offerLast(28) = " + dequeue.offerLast(28));
        System.out.println("dequeue.offerFirst(29) = " + dequeue.offerFirst(29));
        System.out.println("dequeue = " + dequeue);

        while (!dequeue.isEmpty()) {
            System.out.println();
            System.out.println("dequeue.pollFirst() = " + dequeue.pollFirst());
            System.out.println("dequeue.pollLast() = " + dequeue.pollLast());
            System.out.println("dequeue = " + dequeue);
        }
    }
}
