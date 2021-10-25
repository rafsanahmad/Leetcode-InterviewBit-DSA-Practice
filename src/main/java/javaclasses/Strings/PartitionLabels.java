/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    //Leetcode 763
    /*You are given a string s. We want to partition the string into as many parts as possible so that each
    letter appears in at most one part.

Return a list of integers representing the size of these parts.

Example 1:
Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

Example 2:
Input: s = "eccbbbbdec"
Output: [10]*/
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        int j = 0;
        int anchor = 0;
        for (int i = 0; i < s.length(); i++) {
            j = Math.max(j, last[s.charAt(i) - 'a']);
            if (i == j) {
                result.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels labels = new PartitionLabels();
        System.out.println(labels.partitionLabels("eccbbbbdec"));
    }
}
