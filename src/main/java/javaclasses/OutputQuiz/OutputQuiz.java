/*
 * *
 *  * Created by Rafsan Ahmad on 11/27/21, 8:45 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

class PassByTest {
    int num;

    PassByTest(int x) {
        num = x;
    }

    PassByTest() {
        num = 0;
    }
}

class box {
    int width;
    int height;
    int length;
}

public class OutputQuiz {

    public static void updateObject(PassByTest ibObj) {
        // Point the object to new reference
        ibObj = new PassByTest();
        // Update the value
        ibObj.num = 50;
    }

    public static void updateObject2(PassByTest ibObj) {
        // no changes are made to point the ibObj to new location
        // Update the value of num
        ibObj.num = 50;
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

    public static void stringEquality() {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String(s1);
        String s4 = new String("Hello");
        System.out.println(s1 == s2);       // true
        System.out.println(s1.equals(s2));  // true
        System.out.println(s1 == s3);       // false
        System.out.println(s1.equals(s3));  // true
        System.out.println(s2 == s4);       // false
        System.out.println(s2.equals(s4));  // true
    }

    public static void sortComparator() {
        Integer myArray[] = {2, 3, 1};
        List<Integer> list = Arrays.asList(2, 3, 1);
        list.sort(new Sorting());
        System.out.println(list);
    }

    static class Sorting implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            //For Ascending o1.compareTo(o2)
            return o2.compareTo(o1); //Descending
        }
    }

    public static void tryCatchFinally(int a, int b) {
        try {
            int z = a / b;
        } finally {
            System.out.println("Finally!");
        }
    }

    public static void treeSetOutput() {
        Set<Integer> set = new TreeSet<Integer>();
        set.add(3);
        set.add((int) 3.0);
        set.add(2);
        set.add(2);
        set.add(new Integer(2));
        set.add(Integer.parseInt("2"));
        System.out.println(set);  // Prints: [2,3]
    }

    public static void listOutputTest() {
        List<String> list1 = new ArrayList<>();
        list1.add("foo");

        List<String> list2 = list1;
        List<String> list3 = new ArrayList<>(list2);

        list1.clear();
        list2.add("bar");
        list3.add("baz");

        System.out.print(list1); // [bar]
        System.out.print(list2); // [bar]
        System.out.print(list3); // [foo, baz]
        System.out.println();

        list2.replaceAll(String::toUpperCase);
        list3.replaceAll(String::toUpperCase);
        System.out.println(list2);
        System.out.println(list3);
    }

    public static void mathCeilRound() {
        double x1 = 22.9;
        System.out.println(Math.ceil(x1));
        System.out.println(Math.round(x1));
        System.out.println(Math.rint(x1));
    }

    public static void objectOutputTest() {
        box obj1 = new box();
        box obj2 = new box();
        obj1.height = 1;
        obj1.length = 1;
        obj1.width = 1;
        obj2 = obj1;
        System.out.println(obj2.height);
    }

    public static void removeEvenIntegers() {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 8, 9));
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list);

        List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 8, 9));
        List<Integer> tempList = new ArrayList<>();
        for (Integer element : list2) {
            if (element % 2 == 0) {
                tempList.add(element);
            }
        }
        list2.removeAll(tempList);
        System.out.println(list2);
    }

    public static void stringConcat() {
        String x = "abc";
        String y = "abc";
        x.concat(y);
        System.out.println(x);  //abc
    }

    public static void incrementOperator() {
        int x, y, z;
        x = 9;
        y = 10;
        z = ++x + y++;
        System.out.println(x); // 10
        System.out.println(y); // 11
        System.out.println(z); // 20
    }

    public static void main(String[] args) {
        //Quiz 1
        Y y = new Y();
        System.out.println(y.i);    //L5

        //Quiz 2
        Integer num1 = 1000, num2 = 1000;
        //False
        System.out.println(num1 == num2);//1
        Integer num3 = 20, num4 = 20;
        //True - Cause Integer cache response between  -128 to 127, then it returns the Integer instance from
        // the cache, else it creates a new instance. This means that the num3 and num4 point to the same object
        // in the IntegerCache and thereby the comparison results in true.
        System.out.println(num3 == num4);//2

        //Quiz 3
        System.out.println(Math.min(Double.MIN_VALUE, 0.0d));
        System.out.println(Double.MIN_VALUE);

        //Quiz 4
        System.out.println(0.1 * 3 == 0.3); //false
        /*This expectation mismatch is due to the error that occurs while rounding float-point numbers and the
        fact that in Java, only the floating-point numbers that are powers of 2 are represented accurately by
        the binary representation.*/
        System.out.println(0.1 * 2 == 0.2); //true

        //Quiz 5
        someMethod(null);

        //Quiz 6
        PassByTest ibTestObj = new PassByTest(20);
        //Pass the reference to updateObject Method
        updateObject(ibTestObj);
        //After the updateObject is executed, check for the value of num in the object.
        System.out.println(ibTestObj.num);

        //Quiz 7
        updateObject2(ibTestObj);
        System.out.println(ibTestObj.num);

        //Quiz 8
        stringEquality();

        //Quiz 9
        try {
            tryCatchFinally(10, 0);
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }

        //Quiz 10
        sortComparator();

        //Quiz 11
        treeSetOutput();

        //Quiz 12
        listOutputTest();

        //Quiz 13
        objectOutputTest();

        //Quiz 14
        mathCeilRound();

        //Quiz 15
        removeEvenIntegers();

        //Quiz 16
        stringConcat();

        //Quiz 17
        incrementOperator();
    }
}