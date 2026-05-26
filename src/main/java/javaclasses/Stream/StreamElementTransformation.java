/*
 *
 *  * StreamElementTransformation.java
 *  *
 *  * Created by Rafsan Ahmad on 05/23/26, 3:19 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Stream;

import java.util.ArrayList;
import java.util.List;

public class StreamElementTransformation {

    /*Output:
Lara
Peter
Zach
Karl
Barbara
Lois
*/
    public static void main(String[] args) {

        // Create list of persons
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Lara", 35));
        persons.add(new Person("Peter", 21));
        persons.add(new Person("Zach", 45));
        persons.add(new Person("Karl", 50));
        persons.add(new Person("Barbara", 17));
        persons.add(new Person("Lois", 16));

        // =========================================================
        // map() transforms one element into another
        // =========================================================

        // Convert Person objects into names
        persons.stream()
                .map(Person::getName)
                .forEach(System.out::println);
    }
}
