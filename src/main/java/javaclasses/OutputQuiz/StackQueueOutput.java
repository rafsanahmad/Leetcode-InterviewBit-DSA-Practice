/*
 * *
 *  * Stack Queue Output.java
 *  * Created by Rafsan Ahmad on 1/15/22, 4:49 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueueOutput {

    public void stackQueueTest() {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");

        ((LinkedList<String>) queue).add(stack.pop());
        stack.push("Dingleberrry");
        stack.push("Eggplant");
        ((LinkedList<String>) queue).add("Fig");

        stack.push(queue.remove());
        ((LinkedList<String>) queue).add(stack.pop());
        ((LinkedList<String>) queue).add(stack.pop());

        System.out.println(stack);  //Apple, Banana, Dingleberry
        System.out.println(queue);  //Fig, Cherry, Eggplant
    }

    public static void main(String[] args) {
        StackQueueOutput output = new StackQueueOutput();
        output.stackQueueTest();
    }
}
