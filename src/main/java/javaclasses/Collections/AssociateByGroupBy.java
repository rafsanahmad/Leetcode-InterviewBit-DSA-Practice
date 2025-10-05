/*
 * *
 *  * Associate By Group By.java
 *  * Created by Rafsan Ahmad on 9/29/25, 4:01PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package javaclasses.Collections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssociateByGroupBy {
    static class Employee {
        int id;
        String name;

        Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return "Employee(" + id + ", " + name + ")";
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice"),
                new Employee(2, "Bob"),
                new Employee(3, "Charlie"),
                new Employee(2, "Bobby") // duplicate key
        );

        //Java Equivalent AssociateBy
        Map<Integer, Employee> empMap = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getId,
                        e -> e,
                        (oldVal, newVal) -> newVal  // last one wins
                ));

        System.out.println(empMap);
        // {1=Employee(1, Alice), 2=Employee(2, Bobby), 3=Employee(3, Charlie)}


        //Java Equivalent-GroupBy
        List<String> words = Arrays.asList("apple", "apricot", "banana", "blueberry");

        Map<Character, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));

        System.out.println(grouped);
        // {a=[apple, apricot], b=[banana, blueberry]}
    }
}
