/*
 * * Stack Operation.java
 *  * Created by Rafsan Ahmad on 11/22/21, 2:21 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.Stack;
import java.util.regex.Pattern;

public class StackOperation {

    /*Implement a stack with POP, DUP, +, - operation*/

    public static final int MAX = 1048575;
    public static final int MIN = 0;
    private final Stack<Integer> stack = new Stack<>();

    public int solution(String s) {
        try {
            String str = s.trim();
            String[] splited = str.split(" ");
            for (String item : splited) {
                Pattern pattern = Pattern.compile("\\d+");
                if (pattern.matcher(item).matches()) {
                    //Numeric
                    push(Integer.parseInt(item));
                } else {
                    switch (item) {
                        case "POP":
                            this.pop();
                            break;
                        case "DUP":
                            this.dup();
                            break;
                        case "+":
                            this.add();
                            break;
                        case "-":
                            this.remove();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return pop();
    }

    private void rangeCheck(int i) {
        if (i < MIN || i > MAX) {
            throw new RuntimeException("Invalid operation");
        }
    }

    private void checkStackSize(int i) {
        if (stack.size() < i) {
            throw new RuntimeException("Invalid stack size");
        }
    }

    private void push(int i) {
        rangeCheck(i);
        stack.push(i);
    }

    private int pop() {
        return stack.pop();
    }

    private void dup() {
        checkStackSize(1);
        push(stack.peek());
    }

    private void add() {
        checkStackSize(2);
        int value = stack.pop() + stack.pop();
        push(value);
    }

    private void remove() {
        checkStackSize(2);
        int value = stack.pop() - stack.pop();
        push(value);
    }

    public static void main(String[] args) {
        String s1 = "4 5 6 - 7 +";
        String s2 = "13 DUP 4 POP 5 DUP + DUP + -";
        String s3 = "3 DUP 5 - -";
        String s4 = "5 6 + -";
        String s5 = "1000 DUP 400 POP 50 80 + 140 - 20 500";
        StackOperation machine = new StackOperation();
        System.out.println(machine.solution(s1));
    }

}

