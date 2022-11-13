/*
 * * Remove Duplicate From Sorted List.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class RemoveDuplicateFromSortedList {
    //Leetcode 82
    /*Write a function that takes a list sorted in non-decreasing order and deletes any duplicate nodes from the list.
    The list should only be traversed once.
For example if the linked list is 11->11->11->21->43->43->60 then removeDuplicates() should convert the list to
11->21->43->60. */

    /*Algorithm:
Traverse the list from the head (or start) node.
While traversing, compare each node with its next node.
If the data of the next node is the same as the current node then delete the next node.
Before we delete a node, we need to store the next pointer of the node

Time Complexity: O(n) where n is the number of nodes in the given linked list.
*/
    public ListNode deleteDuplicates(ListNode A) {
        ListNode head = A;
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        while (head != null) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            result.next = new ListNode(head.val);
            head = head.next;
            result = result.next;
        }
        return temp.next;
    }

    /* Function to print linked list */
    void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RemoveDuplicateFromSortedList list = new RemoveDuplicateFromSortedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);

        ListNode result = list.deleteDuplicates(head);
        list.printList(result);
    }
}
