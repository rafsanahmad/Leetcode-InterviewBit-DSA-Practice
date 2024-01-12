/*
 * *
 *  * HashMap Equals and HashCode.java
 *  * Created by Rafsan Ahmad on 1/12/24, 5:15 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OutputQuiz;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


// Both Equals and Hashcode overridden
class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                name == student.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

class StudentWithHashCodeOverride {
    int id;
    String name;

    public StudentWithHashCodeOverride(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

class StudentWithEqualsOverride {
    int id;
    String name;

    public StudentWithEqualsOverride(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                name == student.name;
    }
}

public class HashMap_Equals_HashCode {
    public static void main(String[] args) {
        Student student1 = new Student(1, "John");
        Student student2 = new Student(2, "Mayer");
        Student student3 = new Student(1, "John");
        Map<Student, Integer> map = new HashMap<>();
        map.put(student1, 1);
        map.put(student2, 2);
        map.forEach((K, V) -> System.out.println("Existing values in the map: " + V));
        System.out.println();
        map.put(student1, 3);
        map.forEach((K, V) -> System.out.println("Existing values in the map: " + V));
        System.out.println();
        System.out.println(map.containsKey(student1));
        System.out.println(map.containsKey(student3));
        map.put(student3, 4);
        map.forEach((K, V) -> System.out.println("Existing values in the map: " + V));


        StudentWithHashCodeOverride student11 = new StudentWithHashCodeOverride(1, "John");
        StudentWithHashCodeOverride student22 = new StudentWithHashCodeOverride(2, "Mayer");
        StudentWithHashCodeOverride student33 = new StudentWithHashCodeOverride(1, "John");
        Map<StudentWithHashCodeOverride, Integer> map2 = new HashMap<>();
        map2.put(student11, 1);
        map2.put(student22, 2);
        map2.forEach((K, V) -> System.out.println("Existing values in the map: " + V));
        System.out.println();
        map2.put(student11, 3);
        map2.forEach((K, V) -> System.out.println("Existing values in the map: " + V));
        System.out.println();
        System.out.println(map2.containsKey(student11));
        System.out.println(map2.containsKey(student33));
        map2.put(student33, 4);
        map2.forEach((K, V) -> System.out.println("Existing values in the map: " + V));
    }
}
