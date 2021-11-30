/*
 * *
 *  * Created by Rafsan Ahmad on 11/27/21, 8:45 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

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

public class OutputQuiz {

    public static void main(String[] args) {
        //Quiz1
        Y y = new Y();
        System.out.println(y.i);    //L5

        //Quiz2
        Integer num1 = 1000, num2 = 1000;
        //False
        System.out.println(num1 == num2);//1
        Integer num3 = 20, num4 = 20;
        //True - Cause Integer cache response between  -128 to 127, then it returns the Integer instance from
        // the cache, else it creates a new instance. This means that the num3 and num4 point to the same object
        // in the IntegerCache and thereby the comparison results in true.
        System.out.println(num3 == num4);//2

        //Quiz3
        System.out.println(Math.min(Double.MIN_VALUE, 0.0d));
        System.out.println(Double.MIN_VALUE);

        //Quiz4
        System.out.println(0.1 * 3 == 0.3); //false
        /*This expectation mismatch is due to the error that occurs while rounding float-point numbers and the
        fact that in Java, only the floating-point numbers that are powers of 2 are represented accurately by
        the binary representation.*/
        System.out.println(0.1 * 2 == 0.2); //true

        //Quiz5
        someMethod(null);
    }

    public static void someMethod(Object o) {
        System.out.println("Object method Invoked");
    }

    public static void someMethod(String s) {
        /*null is not an object in Java.The Java compiler chooses the method with the most specific parameters
        in method overloading. this means that since the String class is more specific, the method with String
        input parameter is called.*/
        System.out.println("String method Invoked");
    }
}