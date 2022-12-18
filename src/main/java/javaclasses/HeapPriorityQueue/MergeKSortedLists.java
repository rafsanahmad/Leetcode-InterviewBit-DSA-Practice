/*
 * *
 *  * Merge K Sorted Lists.java
 *  * Created by Rafsan Ahmad on 12/7/21, 9:05 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javaclasses.LinkedList.ListNode;

public class MergeKSortedLists {
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

    //Runtime: O(nlogn)
    public ListNode mergeKListsNaive(ListNode[] lists) {
        ListNode resultNode = new ListNode();
        ListNode tmp = resultNode;
        List<Integer> values = new ArrayList<>();

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

    //Using Min-heap
    //Time complexity: O(nlog(k))
    //Space complexity: O(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // push the first node of each list into the min-heap
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }

        // take two pointers, head and tail, where the head points to the first node
        // of the output list and tail points to its last node
        ListNode head = null, last = null;

        // run till min-heap is empty
        while (!queue.isEmpty()) {
            // extract the minimum node from the min-heap
            ListNode min = queue.poll();

            // add the minimum node to the output list
            if (head == null) {
                head = last = min;
            } else {
                last.next = min;
                last = min;
            }

            // take the next node from the "same" list and insert it into the min-heap
            if (min.next != null) queue.add(min.next);
        }

        // return head node of the merged list
        return head;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " —> ");
            node = node.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        MergeKSortedLists list = new MergeKSortedLists();
        int k = 3;    // total number of linked lists

        // an array to store the head nodes of the linked lists
        ListNode[] lists = new ListNode[k];

        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(5);
        lists[0].next.next = new ListNode(7);

        lists[1] = new ListNode(2);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(6);
        lists[1].next.next.next = new ListNode(9);

        lists[2] = new ListNode(4);
        lists[2].next = new ListNode(8);
        lists[2].next.next = new ListNode(10);

        // Merge all lists into one
        ListNode head = list.mergeKLists(lists);
        list.printList(head);
    }
}
