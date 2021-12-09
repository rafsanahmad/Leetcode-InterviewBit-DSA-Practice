/*
 * * Sort Hash Map.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortHashMap {
    public static void main(String[] args) throws Exception {

        // a Map with string keys and integer values
        Map<String, Integer> budget = new HashMap<>();
        budget.put("clothes", 120);
        budget.put("grocery", 150);
        budget.put("transportation", 100);
        budget.put("utility", 130);
        budget.put("rent", 1150);
        budget.put("miscellneous", 90);

        //Loop through map
        for (Map.Entry<String, Integer> entry : budget.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " : " + value.toString());
        }

        System.out.println();
        System.out.println("Map before sorting: " + budget);

        // let's sort this map by values first
        Map<String, Integer> sorted = budget
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println("Map after sorting by values: " + sorted);

        // above code can be cleaned a bit by using method reference
        sorted = budget
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        // now let's sort the map in decreasing order of value
        sorted = budget
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println("Map after sorting by values in descending order: "
                + sorted);

        //Sort map by Key ascending
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        treeMap.putAll(budget);

        System.out.println("Map after sorting by keys in ascending order: "
                + treeMap);

        //Sort map by Key descending
        TreeMap<String, Integer> treeMap2 = new TreeMap<String, Integer>(Collections.reverseOrder());
        treeMap2.putAll(budget);

        System.out.println("Map after sorting by keys in descending order: "
                + treeMap2);


        //Custom comparator
        //Value Descending, Key Ascending (if value is same after sorting)
        Map<String, Integer> customMap = new HashMap<>();
        customMap.put("A", 25);
        customMap.put("D", 10);
        customMap.put("B", 15);
        customMap.put("E", 15);
        customMap.put("C", 17);
        Comparator<Map.Entry<String, Integer>> byValueDesc =
                Map.Entry.comparingByValue(Comparator.reverseOrder());
        Comparator<Map.Entry<String, Integer>> byKeyAsc = Map.Entry.comparingByKey();
        Comparator<Map.Entry<String, Integer>> byKeyDesc =
                Map.Entry.comparingByKey(Comparator.reverseOrder());

        Map<String, Integer> result =
                customMap.entrySet()
                        .stream()
                        .sorted(byValueDesc.thenComparing(byKeyDesc))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) ->
                                e1, LinkedHashMap::new));

        System.out.println(result);

    }
}
