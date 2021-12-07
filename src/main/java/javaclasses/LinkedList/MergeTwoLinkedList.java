/*
 * * Merge Two Linked List.java
 *  * Created by Rafsan Ahmad on 10/28/21, 4:32 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class MergeTwoLinkedList {
    //Leetcode 21
    /*Merge two sorted linked lists and return it as a sorted list.
    The list should be made by splicing together the nodes of the first two lists.

Example 1:
Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: l1 = [], l2 = []
Output: []

Example 3:
Input: l1 = [], l2 = [0]
Output: [0]
 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode tail = result;

        while (true) {
            /* if either list runs out, use the other list */
            if (l1 == null) {
                tail.next = l2;
                break;
            }
            if (l2 == null) {
                tail.next = l1;
                break;
            }

            /* Compare the data of the two lists whichever lists' data is smaller, append it into tail
            and advance the head to the next Node
        */
            if (l1.val > l2.val) {
                tail.next = l2;
                l2 = l2.next;
            } else {
                tail.next = l1;
                l1 = l1.next;
            }

            /* Advance the tail */
            tail = tail.next;
        }
        return result.next;
    }

    public ListNode insertNode(ListNode root, int val) {
        ListNode temp = new ListNode(val);
        if (root == null) root = temp;
        else {
            ListNode node = root;
            while (node.next != null) {
                node = node.next;
            }
            node.next = temp;
        }
        return root;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        MergeTwoLinkedList list = new MergeTwoLinkedList();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(4);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        node2.next.next.next = new ListNode(10);
        node2.next.next.next.next = new ListNode(12);

        ListNode result = list.mergeTwoLists(node, node2);
        list.printList(result);
    }

}
