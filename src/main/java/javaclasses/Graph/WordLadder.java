package javaclasses.Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    //Leetcode 127
    /*A transformation sequence from word beginWord to word endWord using a dictionary wordList is a
    sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation
sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 */

    //Using BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 1;
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return result;
                for (int j = 0; j < word.length(); j++) {
                    char[] arr = word.toCharArray();
                    for (int k = 'a'; k <= 'z'; k++) {
                        arr[j] = (char) k;
                        String str = new String(arr);
                        if (set.contains(str) && !visited.contains(str)) {
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            ++result;
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] strs = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> list = Arrays.asList(strs);
        WordLadder ladder = new WordLadder();
        System.out.println(ladder.ladderLength("hit", "cog", list));
    }
}
