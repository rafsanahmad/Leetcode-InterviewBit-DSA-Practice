/*
 *
 *  * StreamVsImperative.java
 *  *
 *  * Created by Rafsan Ahmad on 05/23/26, 3:24 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Stream;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamVsImperative {

    /*Output:
    * Stream Result:
Karl, Michael, Zach

Imperative Result:
Karl, Michael, Zach, */
    public static void main(String[] args) {

        // Create businesses list
        ArrayList<Business> businesses = new ArrayList<>();

        businesses.add(
                new Business(
                        "Google",
                        new ArrayList<Person>() {{
                            add(new Person("Lara", 35));
                            add(new Person("Peter", 21));
                        }}
                )
        );

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
        // STREAM VERSION
        // =========================================================

        String s = businesses
                .stream()
                .filter(b -> b.getName().startsWith("A"))
                .flatMap(b -> b.getEmployees().stream())
                .sorted()
                .filter(e -> e.getAge() >= 30)
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println("Stream Result:");
        System.out.println(s);

        System.out.println();

        // =========================================================
        // IMPERATIVE VERSION
        // =========================================================

        // Store all employees from businesses starting with A
        ArrayList<Person> personList = new ArrayList<Person>();

        for (Business b : businesses) {

            if (b.getName().startsWith("A")) {

                personList.addAll(b.getEmployees());
            }
        }

        // Sort employees
        personList.sort(Person::compareTo);

        // Keep employees age >= 30
        ArrayList<Person> olderThan30 = new ArrayList<Person>();

        for (Person p : personList) {

            if (p.getAge() >= 30) {

                olderThan30.add(p);
            }
        }

        // Build final string manually
        StringBuilder builder = new StringBuilder();

        for (Person p : olderThan30) {

            builder.append(p.getName() + ", ");
        }

        // Print result
        System.out.println("Imperative Result:");
        System.out.println(builder);
    }
}