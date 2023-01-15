/*
 * * All String Permutation.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AllStringPermutation {
    //res/permutation_recursion.gif
    /*
    Generate all permutations of string in lexicographically sorted order where repetitions of
    character is possible in string.

    Below are the permutations of string ABC.

    ABC
    ACB
    BAC
    BCA
    CAB
    CBA
     */

    /*Time Complexity: O(n*n!) Note that there are n! permutations and it requires O(n) time to print a permutation.
    Auxiliary Space: O(n) - n = length of string
    */

    public List<String> permute(char[] input) {
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }
        char[] str = new char[countMap.size()];
        int[] count = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            str[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }

        List<String> resultList = new ArrayList<>();
        char[] result = new char[input.length];
        permuteUtil(str, count, result, 0, resultList);
        return resultList;
    }

    public void permuteUtil(char[] str, int[] count, char[] result, int level, List<String> resultList) {
        if (level == result.length) {
            resultList.add(new String(result));
            return;
        }

        for (int i = 0; i < str.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            result[level] = str[i];
            count[i]--;
            permuteUtil(str, count, result, level + 1, resultList);
            count[i]++;
        }
    }

    private void printArray(char[] input) {
        for (char ch : input) {
            System.out.print(ch);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String str = "ABC";
        AllStringPermutation permutation = new AllStringPermutation();
        permutation.permute(str.toCharArray()).forEach(System.out::println);
    }
}
