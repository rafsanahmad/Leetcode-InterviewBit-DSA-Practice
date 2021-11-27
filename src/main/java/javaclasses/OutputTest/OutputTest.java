/*
 * *
 *  * Created by Rafsan Ahmad on 11/27/21, 8:45 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.OutputTest;

class X {
    static int i = 1111;

    static {
        i = i-- - --i;    //L1
    }

    {
        i = i++ + ++i;    //L2
    }
}

class Y extends X {
    static {
        i = --i - i--;    //L3
    }

    {
        i = ++i + i++;    //L4
    }
}

/*
Execution Timeline-> L1->L3->L2->L4

L1 ->
i = i-- - --i;
i = 1111 - 1109 = 2

L3 ->
i = --i - i--;
i = 1 - 1 = 0

L2 ->
i = i++ + ++i;
i = 0 + 2 = 2

L4 ->
i = ++i + i++;
i = 3 + 3 = 6 = y.i
*/

public class OutputTest {
    public static void main(String[] args) {
        //Test1
        Y y = new Y();
        System.out.println(y.i);    //L5

        //Test2
        Integer num1 = 1000, num2 = 1000;
        //False
        System.out.println(num1 == num2);//1
        Integer num3 = 20, num4 = 20;
        //True - Cause Integer cache response between  -128 to 127, then it returns the Integer instance from
        // the cache, else it creates a new instance. This means that the num3 and num4 point to the same object
        // in the IntegerCache and thereby the comparison results in true.
        System.out.println(num3 == num4);//2
    }
}