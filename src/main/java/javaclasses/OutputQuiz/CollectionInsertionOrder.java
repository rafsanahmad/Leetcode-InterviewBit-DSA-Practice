/*
 * *
 *  * Collection Insertion Order.java
 *  * Created by Rafsan Ahmad on 1/15/22, 4:29 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class CollectionInsertionOrder {
    public void collectionInsertionOrder() {
        //Linked List
        System.out.println("Linked List");
        Collection<String> collection = new LinkedList<>();
        collection.add("Foo");
        collection.add("Bar");
        collection.add("Baz");
        collection.forEach(System.out::print); //FooBarBaz
        System.out.println();

        //TreeSet
        System.out.println("TreeSet");
        Collection<String> collection2 = new TreeSet<>();
        collection2.add("Foo");
        collection2.add("Bar");
        collection2.add("Baz");
        collection2.forEach(System.out::print); //BarBazFoo
        System.out.println();

        //ArrayList
        System.out.println("ArrayList");
        Collection<String> collection3 = new ArrayList<>();
        collection3.add("Foo");
        collection3.add("Bar");
        collection3.add("Baz");
        collection3.forEach(System.out::print); //FooBarBaz
        System.out.println();

        //LinkedHashSet
        System.out.println("LinkedHashSet");
        Collection<String> collection4 = new LinkedHashSet<>();
        collection4.add("Foo");
        collection4.add("Bar");
        collection4.add("Baz");
        collection4.forEach(System.out::print); //FooBarBaz
        System.out.println();

        //ArrayDeque
        System.out.println("ArrayDeque");
        Collection<String> collection5 = new ArrayDeque<>();
        collection5.add("Foo");
        collection5.add("Bar");
        collection5.add("Baz");
        collection5.forEach(System.out::print); //FooBarBaz
        System.out.println();

        Set set = new TreeSet();
        set.add("2");
        set.add("1");
        set.add("3");
        System.out.println(set); // [1,2,3]
    }

    public static void main(String[] args) {
        CollectionInsertionOrder order = new CollectionInsertionOrder();
        order.collectionInsertionOrder();
    }
}
