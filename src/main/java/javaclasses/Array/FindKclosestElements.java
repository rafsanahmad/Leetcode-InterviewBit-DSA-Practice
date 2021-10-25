/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindKclosestElements {
    //Leetcode 658
    /*Given a sorted integer array arr, two integers k and x,
    return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:
|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]

Example 2:
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 */

    class Elements implements Comparable<Elements> {
        int value;
        int diff;

        Elements(int value, int diff) {
            this.value = value;
            this.diff = diff;
        }

        @Override
        public int compareTo(Elements elements) {
            return this.diff - elements.diff;
        }
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        List<Elements> elements = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            Elements el = new Elements(arr[i], Math.abs(arr[i] - x));
            elements.add(el);
        }

        Collections.sort(elements,
                Elements::compareTo);
        for (int i = 0; i < k; i++) {
            result.add(elements.get(i).value);
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        FindKclosestElements elements = new FindKclosestElements();
        int[] ar = {1, 2, 3, 4, 5};
        List<Integer> result = elements.findClosestElements(ar, 4, 3);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
