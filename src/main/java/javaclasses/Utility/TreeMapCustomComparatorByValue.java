/*
 * *
 *  * TreeMap Custom Comparator.java
 *  * Created by Rafsan Ahmad on 2/25/24, 4:05 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Utility;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapCustomComparatorByValue {

    public static <K, V extends Comparable<V>> Map<K, V>
    valueSort(final Map<K, V> map) {
        // Static Method with return type Map and
        // extending comparator class which compares values
        // associated with two keys
        Comparator<K> valueComparator = new Comparator<K>() {

            // return comparison results of values of
            // two keys
            public int compare(K k1, K k2) {
                return map.get(k2).compareTo(map.get(k1));
            }
        };

        // SortedMap created using the comparator
        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);

        sorted.putAll(map);

        return sorted;
    }

    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();

        // Put elements to the map
        map.put("Anshu", 2);
        map.put("Rajiv", 4);
        map.put("Chhotu", 3);
        map.put("Golu", 5);
        map.put("Sita", 1);

        // Calling the method valueSort
        Map sortedMap = valueSort(map);

        // Get a set of the entries on the sorted map
        Set set = sortedMap.entrySet();

        // Get an iterator
        Iterator i = set.iterator();

        // Display elements
        while (i.hasNext()) {

            Map.Entry mp = (Map.Entry) i.next();
            System.out.print(mp.getKey() + ": ");
            System.out.println(mp.getValue());

        }
    }
}
