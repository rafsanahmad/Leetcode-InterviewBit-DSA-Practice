/*
 * *
 *  * Created by Rafsan Ahmad on 12/5/21, 10:33 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

class Scaler {
    static int i;

    static {
        System.out.println("a");

        i = 100;
    }
}

public class StaticBlock {
    static {
        System.out.println("b");
    }

    public static void main(String[] args) {
        System.out.println("c");

        System.out.println(Scaler.i);
    }
}
