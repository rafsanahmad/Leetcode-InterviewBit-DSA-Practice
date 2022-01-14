/*
 * *
 *  * Reduce Example Output.java
 *  * Created by Rafsan Ahmad on 1/13/22, 10:59 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceExampleOutput {

    public String name;
    private double diameter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public ReduceExampleOutput(String name, double diameter) {
        this.setName(name);
        this.setDiameter(diameter);
    }

    @Override
    public String toString() {
        return String.format("%s [%.2f]", getName(), getDiameter());
    }

    public void listReduce() {
        List<String> countries = Arrays.asList("Germany", "England", "China",
                "Denmark", "Brazil", "France", "Australia");
        Optional<String> countryName = countries.stream().reduce(
                (c1, c2) -> c1.length() > c2.length() ? c1 : c2);
        countryName.ifPresent(System.out::println); //Australia
    }

    public static void main(String[] args) {
        List<ReduceExampleOutput> shapes = new ArrayList<>();
        shapes.add(new ReduceExampleOutput("S1", 286));
        shapes.add(new ReduceExampleOutput("S2", 512));
        shapes.add(new ReduceExampleOutput("S3", 268));
        shapes.add(new ReduceExampleOutput("S4", 258));
        shapes.add(new ReduceExampleOutput("S5", 431));
        shapes.add(new ReduceExampleOutput("S6", 289));

        ReduceExampleOutput output = shapes.stream().reduce(shapes.get(0), (a, b) ->
                a.getDiameter() > b.getDiameter() ? a : b);
        System.out.println(output);

        output.listReduce();
    }
}
