/*
 * *
 *  * Lexicographically Smallest Equivalent String.java
 *  * Created by Rafsan Ahmad on 1/15/23, 11:20 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.DisjointSet;

public class LexicographicSmallestString {
    //https://leetcode.com/problems/lexicographically-smallest-equivalent-string/description/
    /*You are given two strings of the same length s1 and s2 and a string baseStr.

We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of
baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.

Example 1:
Input: s1 = "parker", s2 = "morris", baseStr = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as
[m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order.
So the answer is "makkek".

Example 2:
Input: s1 = "hello", s2 = "world", baseStr = "hold"
Output: "hdld"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".


Example 3:
Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
Output: "aauaaaaada"
Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m],
thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".


Constraints:

1 <= s1.length, s2.length, baseStr <= 1000
s1.length == s2.length
s1, s2, and baseStr consist of lowercase English letters.*/

    //Using Union Find Algorithm
    //Time Complexity = O(n)
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] graph = new int[26];
        for (int i = 0; i < 26; i++) {
            graph[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';
            int end1 = find(graph, b);
            int end2 = find(graph, a);
            if (end1 < end2) {
                graph[end2] = end1;
            } else {
                graph[end1] = end2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = baseStr.charAt(i);
            sb.append((char) ('a' + find(graph, ch - 'a')));
        }
        
        return sb.toString();
    }

    public int find(int[] graph, int index) {
        while (graph[index] != index) {
            index = graph[index];
        }
        return index;
    }

    public static void main(String[] args) {
        LexicographicSmallestString string = new LexicographicSmallestString();
        System.out.println(string.smallestEquivalentString("parker", "morris", "parser"));
        System.out.println(string.smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }
}
