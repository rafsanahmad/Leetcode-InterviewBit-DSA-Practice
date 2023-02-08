/*
 * *
 *  * TestClass.java
 *  * Created by Rafsan Ahmad on 4/20/22, 7:22 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class TestClass {

    public static void testFunc(int[] arr) {

    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();

//        SummaryRanges summaryRanges = new SummaryRanges();
//        summaryRanges.addNum(1);      // arr = [1]
//        summaryRanges.printArray(summaryRanges.getIntervals()); // return [[1, 1]]
//        summaryRanges.addNum(3);      // arr = [1, 3]
//        summaryRanges.printArray(summaryRanges.getIntervals()); // return [[1, 1], [3, 3]]
//        summaryRanges.addNum(7);      // arr = [1, 3, 7]
//        summaryRanges.printArray(summaryRanges.getIntervals()); // return [[1, 1], [3, 3], [7, 7]]
//        summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
//        summaryRanges.printArray(summaryRanges.getIntervals()); // return [[1, 3], [7, 7]]
//        summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
//        summaryRanges.printArray(summaryRanges.getIntervals()); // return [[1, 3], [6, 7]]

        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(6);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(6);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(0);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(4);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(8);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(7);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(6);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(4);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(7);
        summaryRanges.printArray(summaryRanges.getIntervals());
        summaryRanges.addNum(5);
        summaryRanges.printArray(summaryRanges.getIntervals());
    }
}

class SummaryRanges {

    List<int[]> list;
    List<Integer> currentList;
    HashSet<Integer> set;

    public SummaryRanges() {
        list = new ArrayList<>();
        currentList = new ArrayList<>();
        set = new HashSet<>();
    }

    public void addNum(int value) {
        if (set.contains(value)) return;

        if (currentList.isEmpty()) {
            currentList.add(value);
            set.add(value);
            return;
        }

        //Binary Search
        int len = currentList.size();
        int low = 0;
        int high = len;
        while (low < high) {
            int mid = (low + high) / 2;
            if (currentList.get(mid) < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if (low < len) {
            currentList.add(low, value);
        } else {
            currentList.add(value);
        }

        set.addAll(currentList);
    }

    public int[][] getIntervals() {
        list = new ArrayList<>();
        for (int i = 0; i < currentList.size(); i++) {
            int val = currentList.get(i);
            if (list.isEmpty()) {
                list.add(new int[]{val, val});
                continue;
            }

            if (currentList.get(i - 1) + 1 == val) {
                //Merge interval
                int intervalSize = list.size();
                list.get(intervalSize - 1)[1] = val;
                continue;
            }

            list.add(new int[]{val, val});
        }

        return list.toArray(new int[list.size()][]);
    }


    public void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[" + arr[i][0] + "," + arr[i][1] + "]");
        }
        System.out.println();
    }


    class Score {
        int score;
        int age;

        public Score(int s, int a) {
            this.score = s;
            this.age = a;
        }
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int bestScore = 0;
        List<Score> scoresList = new ArrayList<>();

        for (int i = 0; i < ages.length; i++) {
            Score score = new Score(scores[i], ages[i]);
            scoresList.add(score);
        }

        Collections.sort(scoresList, new Comparator<Score>() {

            @Override
            public int compare(Score o1, Score o2) {
                return o2.score - o1.score;
            }

        });


        int minAge = Integer.MAX_VALUE;
        int tempScore = 0;

        for (Score currScore : scoresList) {
            if (currScore.age <= minAge) {
                minAge = currScore.age;
                bestScore += currScore.score;
            } else {
                tempScore += currScore.score;
            }
        }

        return Math.max(bestScore, tempScore);
    }


    boolean foundInclusion = false;

    public boolean checkInclusion(String s1, String s2) {
        backtrackPermute(s1, s2, new ArrayList<>(), new boolean[s1.length()]);
        return foundInclusion;
    }

    private void backtrackPermute(String s1, String s2, List<Character> tempList, boolean[] used) {
        if (foundInclusion) return;
        if (tempList.size() == s1.length()) {
            StringBuilder builder = new StringBuilder();
            for (char ch : tempList) {
                builder.append(ch);
            }
            if (s2.contains(builder.toString())) {
                foundInclusion = true;
            }
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (used[i] || i > 0 && s1.charAt(i) == s1.charAt(i - 1) && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(s1.charAt(i));
                backtrackPermute(s1, s2, tempList, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}