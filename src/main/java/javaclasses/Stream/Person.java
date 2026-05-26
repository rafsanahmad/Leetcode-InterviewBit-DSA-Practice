/*
 *
 *  * Person.java
 *  *
 *  * Created by Rafsan Ahmad on 05/23/26, 3:20 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Stream;

// =========================================================
// Person class
// =========================================================

class Person implements Comparable<Person> {

    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Sort by name
    @Override
    public int compareTo(Person o) {
        return getName().compareTo(o.getName());
    }
}