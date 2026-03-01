/*
 *
 *  * ThreadCoordination.java
 *  *
 *  * Created by Rafsan Ahmad on 02/27/26, 1:13 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.MultiThread;

import java.util.concurrent.Semaphore;

public class ThreadCoordination {
    /*Suppose you have 2 threads. One of them prints (1,2,3.) and the other one prints
    (A,B,C,.). How will you ensure that they run in a sequence so that it prints
    (1A2B3C)?*/

    /*What does 1 mean?
new Semaphore(1)
→ Starts with 1 permit available
→ First acquire() succeeds immediately
→ No blocking initially

So numberSemaphore allows the number thread to run first.

What does 0 mean?
new Semaphore(0)

→ Starts with 0 permits available
→ First acquire() will block
→ Thread must wait until someone calls release()
So charSemaphore forces the character thread to wait initially.*/

    static Semaphore numberSemaphore = new Semaphore(1);
    static Semaphore charSemaphore = new Semaphore(0);

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    numberSemaphore.acquire();
                    System.out.print(i);
                    charSemaphore.release();
                } catch (InterruptedException e) {
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (char c = 'A'; c <= 'C'; c++) {
                try {
                    charSemaphore.acquire();
                    System.out.print(c);
                    numberSemaphore.release();
                } catch (InterruptedException e) {
                }
            }
        });

        t1.start();
        t2.start();
    }
}
