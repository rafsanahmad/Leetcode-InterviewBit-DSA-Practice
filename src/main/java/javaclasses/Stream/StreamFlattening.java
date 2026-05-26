/*
 *
 *  * StreamFlattening.java
 *  *
 *  * Created by Rafsan Ahmad on 05/23/26, 3:22 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Stream;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamFlattening {

    //Output: Karl, Michael, Zach
    public static void main(String[] args) {

        // Create businesses list
        ArrayList<Business> businesses = new ArrayList<>();

        // =========================================================
        // Business 1
        // =========================================================

        businesses.add(
                new Business(
                        "Google",
                        new ArrayList<Person>() {{
                            add(new Person("Lara", 35));
                            add(new Person("Peter", 21));
                        }}
                )
        );

        // =========================================================
        // Business 2
        // =========================================================

        businesses.add(
                new Business(
                        "Apple",
                        new ArrayList<Person>() {{
                            add(new Person("Zach", 45));
                            add(new Person("Karl", 50));
                            add(new Person("Barbara", 17));
                            add(new Person("Lois", 16));
                        }}
                )
        );

        // =========================================================
        // Business 3
        // =========================================================

        businesses.add(
                new Business(
                        "Amazon",
                        new ArrayList<Person>() {{
                            add(new Person("Michael", 41));
                            add(new Person("Jessica", 28));
                            add(new Person("Laura", 13));
                        }}
                )
        );

        // =========================================================
        // Stream Pipeline
        // =========================================================

        String s = businesses

                // Convert list into stream
                .stream()

                // Keep businesses whose name starts with A
                .filter(b -> b.getName().startsWith("A"))

                // Flatten employees of businesses
                .flatMap(b -> b.getEmployees().stream())

                // Sort employees by name
                .sorted()

                // Keep employees age >= 30
                .filter(e -> e.getAge() >= 30)

                // Convert Person -> name
                .map(Person::getName)

                // Join names into single string
                .collect(Collectors.joining(", "));

        // Print result
        System.out.println(s);
    }
}
