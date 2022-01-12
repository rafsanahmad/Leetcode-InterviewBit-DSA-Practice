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

public class AbstractClassQuiz {
    public static void main(String[] args) {
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
    }
}
