/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;
/*Given two sorted arrays, Write a java code to find intersection of two arrays.

For example, if the input arrays are:

arr1[] = {2, 3, 6, 7, 9, 11}
arr2[] = {4, 6, 8, 9, 12}

Then your program should print intersection as {6, 9}. Before writing actual code,
let’s first discuss different approaches to solve this problem.*/

/*In this approach, we first initialize an empty set.Then, we traverse the first array and put each element of
the first array in a set.
Now, For every element x of the second array, we search x in the set.
If x is present, then print it. The time complexity of this approach is O(m+n).*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Using hashset to find intersection of two arrays
 */
public class FindIntersection {

    public List<Integer> findIntersection(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        //Declare hashset
        HashSet<Integer> set = new HashSet<>();

        //Traverse an array, put each element in a set
        for (int val : arr1) {
            set.add(val);
        }

        /**
         Traverse second array values,
         Search the value in a set (set1),
         If element is found then print it.
         */
        for (int val : arr2) {
            if (set.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    //Using Set property
    public Set<Integer> findIntersection2(int[] arr1, int[] arr2) {
        Set<Integer> set1 = Arrays.stream(arr1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(arr2).boxed().collect(Collectors.toSet());
        set1.retainAll(set2);
        return set1;
    }

    public static void main(String[] args) {
        FindIntersection intersection = new FindIntersection();
        int[] arr1 = {2, 3, 6, 7, 9, 11};
        int[] arr2 = {4, 6, 8, 9, 12};
        System.out.println("Approach 1: " + intersection.findIntersection(arr1, arr2));
        System.out.println("Approach 2: " + intersection.findIntersection2(arr1, arr2));
    }
}
