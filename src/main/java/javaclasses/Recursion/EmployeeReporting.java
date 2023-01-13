/*
 * *
 *  * Employee Reporting.java
 *  * Created by Rafsan Ahmad on 2/20/22, 1:56 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmployeeReporting {
    /*
A company's organizational structure is represented as
        1: 2, 3, 4
        In the above employees with id 2, 3 and 4 report to 1
        Assume the following hierarchy.
        1: 2, 3, 4
        3: 5, 6, 7
        5: 8, 9, 10
        Given an employee Id, return all the employees reporting to him directly or indirectly
*/

    public List<Integer> getReportees(Map<Integer, List<Integer>> empMap, int manager) {
        List<Integer> result = new ArrayList<>();
        getReporteesHelper(result, empMap, manager);
        return result;
    }

    private void getReporteesHelper(List<Integer> result, Map<Integer, List<Integer>> empMap, Integer manager) {
        List<Integer> reportees = empMap.get(manager);
        if (reportees == null)
            return;
        for (Integer i : reportees) {
            result.add(i);
            getReporteesHelper(result, empMap, i);
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> empMap = new HashMap<>();
        empMap.put(1, Arrays.asList(2, 3, 4));
        empMap.put(3, Arrays.asList(5, 6, 7));
        empMap.put(5, Arrays.asList(8, 9, 10));
        empMap.put(9, Arrays.asList(11, 12, 13));
        EmployeeReporting reporting = new EmployeeReporting();
        System.out.println(reporting.getReportees(empMap, 3));
    }
}
