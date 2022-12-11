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
    //https://leetcode.com/problems/swap-nodes-in-pairs/description/
    /*Given a linked list, swap every two adjacent nodes and return its head.

For example,

Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

*/

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode temp = new ListNode();
        ListNode result = temp;
        ListNode prev = null;
        int len = 1;

        while (head != null) {
            if (len % 2 == 0) {
                //swap
                temp.next = new ListNode(head.val);
                temp = temp.next;
                temp.next = prev;
                temp = temp.next;
            } else {
                prev = new ListNode(head.val);
            }
            ++len;
            head = head.next;
        }

        if ((len - 1) % 2 == 1) temp.next = prev;
        if (len - 1 == 1) result.next = prev;
        return result.next;
    }

    //Space Complexity: O(1)
    public ListNode swapPairsUsingFastPointer(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            current.next = second;
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
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
        System.out.println();

        ListNode result2 = nodes.swapPairsUsingFastPointer(head);
        nodes.printList(result2);
    }
}
