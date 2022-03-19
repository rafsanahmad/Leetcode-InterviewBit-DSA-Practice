/*
 * *
 *  * Flatten Nested List Iterator.java
 *  * Created by Rafsan Ahmad on 3/19/22, 7:46 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlattenListIterator {
    //Leetcode 341
    /*You are given a nested list of integers nestedList. Each element is either an integer or a list whose
    elements may also be integers or other lists. Implement an iterator to flatten it.

Implement the NestedIterator class:

NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
int next() Returns the next integer in the nested list.
boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
Your code will be tested with the following pseudocode:

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
If res matches the expected flattened list, then your code will be judged as correct.


Example 1:
Input: nestedList = [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should
be: [1,1,2,1,1].

Example 2:
Input: nestedList = [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should
be: [1,4,6].*/


    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {
        List<Integer> resultList = new ArrayList<>();
        int currentIndex = 0;

        public NestedIterator(List<NestedInteger> nestedList) {
            flattenList(nestedList);
            //printList(resultList);
        }

        public void flattenList(List<NestedInteger> nestedList) {
            for (NestedInteger item : nestedList) {
                if (item.isInteger() && item.getList().isEmpty()) {
                    resultList.add(item.getInteger());
                } else if (!item.getList().isEmpty()) {
                    List<NestedInteger> inner = item.getList();
                    flattenList(inner);
                }
            }
        }

        void printList(List<Integer> list) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
        }

        @Override
        public Integer next() {
            return resultList.get(currentIndex++);
        }

        @Override
        public boolean hasNext() {
            return currentIndex <= resultList.size() - 1;
        }
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
    public static void main(String[] args) {
        //[1,[4,[6]]]
    }

}
