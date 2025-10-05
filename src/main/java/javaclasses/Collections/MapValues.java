/*
 * *
 *  * Map Values.java
 *  * Created by Rafsan Ahmad on 9/29/25, 2:12PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package javaclasses.Collections;

import java.util.Map;
import java.util.stream.Collectors;

public class MapValues {
    /*Java, there’s no direct mapValues like Kotlin has, but you can achieve the same effect using
    Streams or by iterating over the map.
    mapValues = transform values only, keep the same keys.
    */

    public static void main(String[] args) {
        Map<Integer, String> names = Map.of(
                1, "alice",
                2, "bob",
                3, "charlie"
        );

        // Transform values -> uppercase
        Map<Integer, String> upper = names.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toUpperCase()
                ));

        System.out.println(upper);
        // {1=ALICE, 2=BOB, 3=CHARLIE}
    }
}
