/*
 * *
 *  * Stream Quiz.java
 *  * Created by Rafsan Ahmad on 9/29/25, 2:14PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package javaclasses.Collections;

import java.util.*;
import java.util.stream.*;

/*Give a list of employees with id, name, department, salary Print the employee name with
highest salary in each department*/

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + department + ") : " + salary;
    }
}

public class StreamQuiz {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 75000),
                new Employee(2, "Bob", "IT", 90000),
                new Employee(3, "Charlie", "HR", 60000),
                new Employee(4, "David", "HR", 65000),
                new Employee(5, "Eve", "Finance", 85000),
                new Employee(6, "Frank", "Finance", 80000)
        );

        // Group employees by department and find max salary per department
        Map<String, Optional<Employee>> maxByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));

        // Print results
        maxByDept.forEach((dept, emp) ->
                System.out.println(dept + " -> " +
                        emp.get().getName() + " (" + emp.get().getSalary() + ")")
        );

        //Group by
        //output: {A=[Alice, Alex], B=[Bob], C=[Charlie]}
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Alex");

        Map<Character, List<String>> grouped = names.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));

        System.out.println(grouped);
    }
}
