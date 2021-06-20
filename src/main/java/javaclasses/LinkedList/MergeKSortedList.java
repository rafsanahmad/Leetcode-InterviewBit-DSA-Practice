package javaclasses.LinkedList;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MergeKSortedList {

    //Leetcode 23
    /*
    * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
    Merge all the linked-lists into one sorted linked-list and return it.

     Example 1:

    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    merging them into one sorted list:
    1->1->2->3->4->4->5->6
    Example 2:

    Input: lists = []
    Output: []
    Example 3:

    Input: lists = [[]]
    Output: []*/

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode resultNode = new ListNode();
        ListNode tmp = resultNode;
        List<Integer> values = new ArrayList<>();

        int len = lists.length;
        for (int i = 0; i < lists.length; i++) {
            ListNode current = lists[i];
            while (current != null) {
                values.add(current.val);
                current = current.next;
            }
        }
        Collections.sort(values);
        for (int i = 0; i < values.size(); i++) {
            tmp.next = new ListNode(values.get(i));
            tmp = tmp.next;
        }
        return resultNode.next;
    }
}
