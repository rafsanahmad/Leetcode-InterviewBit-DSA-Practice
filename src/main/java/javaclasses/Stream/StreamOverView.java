/*
 *
 *  * StreamOverView.java
 *  *
 *  * Created by Rafsan Ahmad on 05/23/26, 3:18 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Stream;

import java.util.ArrayList;
import java.util.List;

public class StreamOverView {

    /*Imperative Result:
[Barbara (17), Lois (16)]

Stream Result:
[Barbara (17), Lois (16)]*/
    public static void main(String[] args) {

        // Create a list to store Person objects
        List<Person> persons = new ArrayList<>();

        // Add sample persons
        persons.add(new Person("Lara", 35));
        persons.add(new Person("Peter", 21));
        persons.add(new Person("Zach", 45));
        persons.add(new Person("Karl", 50));
        persons.add(new Person("Barbara", 17));
        persons.add(new Person("Lois", 16));

        // =========================================================
        // Imperative approach
        // =========================================================

        // Store minors manually using loop + if condition
        List<Person> minors = new ArrayList<>();

        for (Person p : persons) {
            if (p.getAge() < 18) {
                minors.add(p);
            }
        }

        // =========================================================
        // Stream approach
        // =========================================================

        // Filter all persons whose age is below 18
        List<Person> minorsPerStream = persons
                .stream()
                .filter(p -> p.getAge() < 18)
                .toList();

        // Print results
        System.out.println("Imperative Result:");
        System.out.println(minors);

        System.out.println();

        System.out.println("Stream Result:");
        System.out.println(minorsPerStream);
    }
}

