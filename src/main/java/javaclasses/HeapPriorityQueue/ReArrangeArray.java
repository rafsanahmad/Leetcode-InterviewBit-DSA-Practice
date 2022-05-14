/*
 * *
 *  * Rearrange The Array.java
 *  * Created by Rafsan Ahmad on 5/11/22, 12:08 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ReArrangeArray {
    //https://www.codingninjas.com/codestudio/problems/rearrange-the-array_799559?leftPanelTab=0
    /*Problem Statement
You are given an array/list 'NUM' of integers. You are supposed to rearrange the elements of the given 'NUM' so that after
rearranging the given array/list there are no two adjacent elements present in the rearranged 'NUM' which will be the same.
For Example:
Input: NUM[] = {1,1,1,2,2,2}
Output: {1,2,1,2,1,2}
Note: {2,1,2,1,2,1} is also valid because there are no two adjacent which are the same.
Input Format :
The first line contains an integer 'T' which denotes the number of test cases or queries to be run.
Then the test cases follow.

The first line of each test case contains an Integer 'N' denoting the size of the array/list.

The second line of each test case contains 'N' space-separated Integers denoting the elements of the array/list.
Output Format :
For each test case/query, if it is possible to rearrange then print “YES” else print “NO” in separate lines. And if the
output given by the user is wrong then print “Invalid Output”.

If it is possible to rearrange then return any right arrangement of the given array/list otherwise put a single integer
INT_MIN in the array/list and return that.
Note :
You do not need to print anything, it has already been taken care of. Just implement the given function.
Constraints :
1 <= T <= 10
1 <= N <= 10 ^ 4
-10 ^ 9 <= NUM[i] <= 10 ^ 9

Where 'N' is the size of the given array/list and, NUM[i] denotes the i-th element in the array/list.

Time Limit: 1 sec.
Sample Input 1 :
2
5
10 10 10 32 32
6
89 47 89 47 42 21
Sample Output 1 :
YES
YES
Explanation To Sample Input 1 :
For the first test case, We can put 32 in between 10 and arrangement looks like. {10,32,10,32,10}.

For the second test case, We have to arrange only 47 and 89 because the rest of the element is unique in itself.
So one arrangement looks like { 89, 47, 89, 47, 42, 21}.
Sample Input 2 :
3
5
10 7 21 5 1
6
21 21 21 12 12 21
6
10 10 10 20 20 20
Sample Output 2 :
YES
NO
YES
Explanation To Sample Input 2 :
For the first test case, all the elements have the same frequency, so you can return any arrangement of those elements,
i.e. {1, 7, 21, 5, 10}.

For the second test case, we can not rearrange the given array/list because after rearranging {21,12,21,12}, we will be
stuck with two 21. There is no way to arrange them.

For the third test case, we can put all the 10 in between 20. So there will be no such adjacent existence which will be
the same.*/

    /*We have to follow the step to solve this problem, they are:
Build a Priority_queue or max_heap, pq that stores elements and their frequencies.
…… Priority_queue or max_heap is built on the bases of the frequency of elements.
Create a temporary Key that will be used as the previously visited element (the previous element in the resultant array.
Initialize it { num = -1, freq = -1 }
While pq is not empty.
Pop an element and add it to the result.
Decrease frequency of the popped element by ‘1’.
Push the previous element back into the priority_queue if it’s frequency > ‘0’.
Make the current element as the previous element for the next iteration.
If the length of resultant list and original are not equal, return MIN_INT array. Else print result.*/

    static class Element {
        int freq;
        int num;

        Element(int freq, int num) {
            this.freq = freq;
            this.num = num;
        }
    }

    //Comparator class to sort in descending order
    static class ElementComparator implements Comparator<Element> {

        @Override
        public int compare(Element o1, Element o2) {
            if (o2.freq > o1.freq)
                return 1;
            else if (o2.freq < o1.freq)
                return -1;
            return 0;
        }
    }

    static ArrayList<Integer> rearrangeArray(ArrayList<Integer> num) {
        int size = num.size();

        // Store frequencies of all elements of the arraylist
        HashMap<Integer, Integer> countMap = new HashMap<>();
        HashMap<Integer, Integer> visitedMap = new HashMap<>();

        for (int i : num) {
            countMap.put(i, countMap.getOrDefault(i, 0) + 1);
            visitedMap.put(i, 0);
        }

        // Insert all characters with their frequencies into a priority_queue
        PriorityQueue<Element> pq = new PriorityQueue<>(new ElementComparator());

        // Adding high freq elements in descending order
        for (int i = 0; i < size; i++) {
            int val = num.get(i);

            if (countMap.get(val) > 0 && visitedMap.get(val) != 1)
                pq.add(new Element(countMap.get(val), val));
            visitedMap.put(val, 1);
        }

        // 'result[]' that will store resultant value
        ArrayList<Integer> result = new ArrayList<>();

        // work as the previous visited element
        // initial previous element will be ( '-1' and it's frequency will also be '-1' )
        Element prev = new Element(-1, -1);

        // Traverse queue
        while (pq.size() != 0) {
            // pop top element from queue and add it to result
            Element element = pq.peek();
            pq.poll();
            result.add(element.num);

            // If frequency of previous element is less than zero that means it is useless, we need not to push it
            if (prev.freq > 0)
                pq.add(prev);

            // make current element as the previous decrease frequency by 'one'
            element.freq--;
            prev = element;
        }
        if (result.size() < size) {
            return new ArrayList<>(Arrays.asList(Integer.MIN_VALUE));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {21, 12, 13, 21, 12, 13, 21, 12, 21};
        int[] arr2 = {1, 1, 1, 2, 3};
        int[] arr3 = {21, 21, 21, 12, 12, 21};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(rearrangeArray(new ArrayList<>(list)));

        List<Integer> list2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        System.out.println(rearrangeArray(new ArrayList<>(list2)));

        List<Integer> list3 = Arrays.stream(arr3).boxed().collect(Collectors.toList());
        System.out.println(rearrangeArray(new ArrayList<>(list3)));
    }
}
