/*
 * *
 *  * Thread Output.java
 *  * Created by Rafsan Ahmad on 1/13/22, 3:02 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

class newThread extends Thread {

    newThread() {
        super("My Thread");
        start();
    }

    public void run() {
        // prints: Thread[My Thread,5,main]
        // 5 = Thread Priority
        // My Thread = Thread name
        System.out.println(this);
    }
}

public class ThreadOutput {

    public static void main(String[] args) {
        new newThread();
    }
}
