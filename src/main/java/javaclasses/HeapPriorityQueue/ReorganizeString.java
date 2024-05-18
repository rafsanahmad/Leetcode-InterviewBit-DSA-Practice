/*
 * *
 *  * Reorganize String.java
 *  * Created by Rafsan Ahmad on 5/18/24, 5:29 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    //https://leetcode.com/problems/reorganize-string/description/
    /*Given a string s, rearrange the characters of s so that any two adjacent characters are not
    the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.*/

    public String reorganizeString(String s) {
        int len = s.length();
        if (len <= 1) return "";
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            int count = map.getOrDefault(ch, 0) + 1;
            if (count > (len + 1) / 2) return "";
            map.put(ch, count);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(new int[]{entry.getKey(), entry.getValue()});
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            char ch = (char) curr[0];
            if (sb.isEmpty() || ch != sb.charAt(sb.length() - 1)) {
                sb.append(ch);
                if (--curr[1] > 0) {
                    queue.add(curr);
                }
            } else {
                int[] curr2 = queue.poll();
                if (curr2 != null) {
                    char ch2 = (char) curr2[0];
                    sb.append(ch2);

                    if (--curr2[1] > 0) {
                        queue.add(curr2);
                    }

                    queue.add(curr);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ReorganizeString string = new ReorganizeString();
        System.out.println(string.reorganizeString("aab"));
    }
}
