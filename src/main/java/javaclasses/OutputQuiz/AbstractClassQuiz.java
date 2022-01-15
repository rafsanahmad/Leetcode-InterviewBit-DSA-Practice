/*
 * *
 *  * Abstract Class Quiz.java
 *  * Created by Rafsan Ahmad on 1/12/22, 9:24 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

abstract class Animal {
    public abstract void makeNoise();

    public abstract void move();
}

abstract class Demo {
    public int a;

    void demo() {
        a = 10;
    }

    abstract public void get();
}

abstract class Canine extends Animal {
    public void wagTail() {
        System.out.println("Wagging");
    }

    @Override
    public void move() {
        System.out.println("Run");
    }
}

class Dog extends Canine {

    public void fetch() {
        System.out.println("Fetch");
    }

    @Override
    public void makeNoise() {
        System.out.println("Bark");
    }
}

class DemoTest extends Demo {
    public void get() {
        System.out.println("a = " + a);
    }
}

public class AbstractClassQuiz {
    public static void main(String[] args) {
        //Quiz 1
        Dog d = new Dog();
        d.makeNoise();
        d.move();
        d.wagTail();
        d.fetch();

        Canine c = new Dog();
        c.makeNoise();
        c.move();
        c.wagTail();
        //c.fetch();

        Animal a = new Dog();
        a.makeNoise();
        a.move();
        //a.wagTail();
        //a.fetch();

        //Quiz 2
        DemoTest obj = new DemoTest();
        obj.get();  // a = 0
    }
}
