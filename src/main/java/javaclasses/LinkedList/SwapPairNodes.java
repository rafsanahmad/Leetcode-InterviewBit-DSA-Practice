/*
 * *
 *  * Swap List Nodes in pairs.java
 *  * Created by Rafsan Ahmad on 11/13/22, 12:18 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class SwapPairNodes {
    //https://www.interviewbit.com/problems/swap-list-nodes-in-pairs/
    /*Given a linked list, swap every two adjacent nodes and return its head.

For example,

Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

*/

    public ListNode swapPairs(ListNode A) {
        ListNode head = A;
        ListNode prev = null;
        int i = 0;

        while (head != null) {
            if (i % 2 == 1) {
                int temp = prev.val;
                prev.val = head.val;
                head.val = temp;
            }
            i++;
            prev = head;
            head = head.next;
        }

        return A;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        SwapPairNodes nodes = new SwapPairNodes();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode result = nodes.swapPairs(head);
        nodes.printList(result);
    }
}
