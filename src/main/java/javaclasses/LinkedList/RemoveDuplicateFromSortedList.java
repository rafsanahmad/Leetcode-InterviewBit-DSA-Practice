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
    ListNode head;

    void removeDuplicates() {
        /*Another reference to head*/
        ListNode curr = head;

        /* Traverse list till the last node */
        while (curr != null) {
            ListNode temp = curr;
            /*Compare current node with the next node and
            keep on deleting them until it matches the current
            node data */
            while (temp != null && temp.val == curr.val) {
                temp = temp.next;
            }
            /*Set current node next to the next different
            element denoted by temp*/
            curr.next = temp;
            curr = curr.next;
        }
    }

    /* Utility functions */

    /* Inserts a new Node at front of the list. */
    public void push(int new_data) {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        ListNode new_node = new ListNode(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    /* Function to print linked list */
    void printList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /* Driver program to test above functions */
    public static void main(String args[]) {
        RemoveDuplicateFromSortedList llist = new RemoveDuplicateFromSortedList();
        llist.push(20);
        llist.push(13);
        llist.push(13);
        llist.push(11);
        llist.push(11);
        llist.push(11);

        System.out.println("List before removal of duplicates");
        llist.printList();

        llist.removeDuplicates();

        System.out.println("List after removal of elements");
        llist.printList();
    }
}
