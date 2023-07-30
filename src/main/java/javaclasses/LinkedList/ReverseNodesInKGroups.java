/*
 * *
 *  * Reverse Nodes In K Groups.java
 *  * Created by Rafsan Ahmad on 7/30/23, 8:25 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.LinkedList;

public class ReverseNodesInKGroups {
    // https://leetcode.com/problems/reverse-nodes-in-k-group/
    /*Given the head of a linked list, reverse the nodes of the list k at a time, and return
    the modified list.

k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain
as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]

Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000

Follow-up: Can you solve the problem in O(1) extra memory space?
*/

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return head;
        ListNode node = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode begin = dummy;
        int i = 0;
        while (node != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, node.next);
                node = begin.next;
            } else {
                node = node.next;
            }
        }

        return dummy.next;
    }

    public ListNode reverse(ListNode begin, ListNode end) {
        ListNode prev = begin;
        ListNode next = null;
        ListNode curr = begin.next;
        ListNode head = curr;

        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        begin.next = prev;
        head.next = curr;
        return head;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ReverseNodesInKGroups kGroups = new ReverseNodesInKGroups();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        ListNode result = kGroups.reverseKGroup(node, 3);
        kGroups.printList(result);
    }
}
