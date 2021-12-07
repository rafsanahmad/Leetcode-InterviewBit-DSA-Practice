/*
 * * Reverse Linked List II.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class ReverseLinkList_II {
    //Leecode 92
    /*
    Given the head of a singly linked list and two integers left and right where left <= right,
    reverse the nodes of the list from position left to position right, and return the reversed list.

    Input: head = [1,2,3,4,5], left = 2, right = 4
    Output: [1,4,3,2,5]
    */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        //Base case
        if (head == null) return null;

        //Move the two pointers until they reach the
        //proper starting point
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        //Two pointers that will fix the final connections
        ListNode con = prev, tail = cur;

        //Iteratively reverse the nodes until n
        //becomes 0
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        //Adjust the final connections
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);

        ReverseLinkList_II list = new ReverseLinkList_II();
        ListNode result = list.reverseBetween(root, 2, 4);
        list.printList(result);
    }

}
