/*
 *
 *  * ImportantHashMapFunctions.java
 *  *
 *  * Created by Rafsan Ahmad on 04/23/26, 12:51 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.HashTable;

import java.util.HashMap;

public class ImportantHashMapFunctions {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        // put
        map.put("apple", 1);
        map.put("banana", 2);

        System.out.println(map); // {apple=1, banana=2}

        // get
        System.out.println(map.get("apple")); // 1
        System.out.println(map.get("orange")); // null

        // getOrDefault
        System.out.println(map.getOrDefault("orange", 0)); // 0

        // putIfAbsent
        map.putIfAbsent("banana", 100);
        System.out.println(map.get("banana")); // 2

        // computeIfAbsent (IMPORTANT)
        int val1 = map.computeIfAbsent("orange", key -> 10);
        System.out.println(val1); // 10
        System.out.println(map); // {apple=1, banana=2, orange=10}

        // computeIfAbsent when exists
        int val2 = map.computeIfAbsent("apple", key -> 99);
        System.out.println(val2); // 1 (NOT 99)

        // computeIfPresent (IMPORTANT)
        map.computeIfPresent("banana", (key, val) -> val + 10);
        System.out.println(map.get("banana")); // 12

        // compute (IMPORTANT)
        map.compute("banana", (key, val) -> val == null ? 0 : val * 2);
        System.out.println(map.get("banana")); // 24

        // replace
        map.replace("banana", 50);
        System.out.println(map.get("banana")); // 50

        // replace with condition
        map.replace("banana", 50, 500);
        System.out.println(map.get("banana")); // 500

        // remove
        map.remove("apple");
        System.out.println(map); // {banana=500, orange=10}

        // remove with condition
        map.remove("banana", 100);
        System.out.println(map); // still {banana=500, orange=10}

        // contains
        System.out.println(map.containsKey("orange")); // true
        System.out.println(map.containsValue(10)); // true

        // iteration
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }
}
