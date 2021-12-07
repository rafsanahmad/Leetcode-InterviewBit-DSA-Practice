/*
 * * Rotate List.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class RotateList {
    //Leetcode 61
    /*Given the head of a linked list, rotate the list to the right by k places.
    * Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]*/

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode copyHead = head;
        int len = 1;
        while (copyHead.next != null) {
            copyHead = copyHead.next;
            len++;
        }
        copyHead.next = head;
        for (int i = len - k % len; i > 1; i--) {
            head = head.next;
        }
        copyHead = head.next;
        head.next = null;
        return copyHead;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        RotateList rt = new RotateList();
        ListNode result = rt.rotateRight(node, 2);
        rt.printList(result);
    }
}
