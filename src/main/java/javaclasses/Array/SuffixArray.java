/*
 * *
 *  * Suffix Array.java
 *  * Created by Rafsan Ahmad on 12/16/21, 1:05 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuffixArray {
    /*A suffix array is a sorted array of all suffixes of a given string. The definition is similar to Suffix
    Tree which is compressed trie of all suffixes of the given text. Any suffix tree based algorithm can be
    replaced with an algorithm that uses a suffix array enhanced with additional information and solves the
    same problem in the same time complexity (Source Wiki).

A suffix array can be constructed from Suffix tree by doing a DFS traversal of the suffix tree.
In fact Suffix array and suffix tree both can be constructed from each other in linear time.
Advantages of suffix arrays over suffix trees include improved space requirements, simpler linear time
construction algorithms (e.g., compared to Ukkonen’s algorithm) and improved cache locality (Source: Wiki)

Example:
Let the given string be "banana".

0 banana                          5 a
1 anana     Sort the Suffixes     3 ana
2 nana      ---------------->     1 anana
3 ana        alphabetically       0 banana
4 na                              4 na
5 a                               2 nana

So the suffix array for "banana" is {5, 3, 1, 0, 4, 2}

Applications of Suffix Array
Suffix array is an extremely useful data structure, it can be used for a wide range of problems.
Following are some famous problems where Suffix array can be used.
1) Pattern Searching
2) Finding the longest repeated substring
3) Finding the longest common substring
4) Finding the longest palindrome in a string
*/
    // Structure to store information of a suffix
    class Suffix implements Comparable {
        int index;
        String suff;

        Suffix(int i, String s) {
            this.index = i;
            this.suff = s;
        }

        @Override
        public int compareTo(Object o) {
            Suffix obj = (Suffix) o;
            return this.suff.compareTo(obj.suff);
        }
    }

    public int[] buildSuffixArray(String str) {
        int len = str.length();
        List<Suffix> suffixes = new ArrayList<>();

        // Store suffixes and their indexes in an array of structures.
        // The structure is needed to sort the suffixes alphabetically
        // and maintain their old indexes while sorting
        for (int i = 0; i < len; i++) {
            String subString = str.substring(i);
            Suffix suf = new Suffix(i, subString);
            suffixes.add(suf);
        }

        // Sort the suffixes using the comparison function
        // defined above.
        Collections.sort(suffixes);

        // Store indexes of all sorted suffixes in the suffix array
        int[] suffixArr = new int[len];
        for (int i = 0; i < len; i++)
            suffixArr[i] = suffixes.get(i).index;

        // Return the suffix array
        return suffixArr;
    }

    public static void main(String[] args) {
        SuffixArray array = new SuffixArray();
        System.out.println(Arrays.toString(array.buildSuffixArray("banana")));
    }
}
