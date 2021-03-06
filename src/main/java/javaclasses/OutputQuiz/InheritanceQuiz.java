/*
 * *
 *  * Inheritance Quiz.java
 *  * Created by Rafsan Ahmad on 1/15/22, 1:11 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;


//Inheritance Quiz
interface BaseClass1 {
    default void BaseClass1() {
        System.out.println("BaseClass1 constructor called");
    }
};

interface BaseClass2 {
    default void BaseClass2() {
        System.out.println("BaseClass2 constructor called");
    }
};

class DerivedClass implements BaseClass1, BaseClass2 {
    DerivedClass() {
        BaseClass1.super.BaseClass1();
        BaseClass2.super.BaseClass2();
        System.out.println("DerivedClass constructor called");
    }
};

class A {
    void print() {
        System.out.println("Inside A::");
    }
};

class B extends A {
    void print() {
        System.out.println("Inside B");
    }
};

class C extends B {
};

class Parent {
    void foo() {
        System.out.println("Foo() inside Parent");
    }
}

class Child extends Parent {
    void foo() {
        System.out.println("Foo() inside Child");
    }
}

//Static block Test
class Super {
    {
        System.out.print("A ");
    }

    static {
        System.out.print("B ");
    }
}

class Subclass extends Super {
    {
        System.out.print("C ");
    }

    static {
        System.out.print("D ");
    }
}

public class InheritanceQuiz {

    public static void main(String[] args) {
        //Quiz 1
        DerivedClass derived_class = new DerivedClass();

        //Quiz 2
        C c = new C();
        c.print();

        //Quiz 3
        Child ch = new Child();
        ch.foo();
        Parent parent = new Child();
        parent.foo();

        //Quiz 4
        //static block
        Subclass s = new Subclass(); //B D A C
        System.out.println();
    }
}
