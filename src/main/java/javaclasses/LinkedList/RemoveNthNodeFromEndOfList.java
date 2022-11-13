/*
 * *
 *  * Remove Nth Node From End Of List.java
 *  * Created by Rafsan Ahmad on 1/13/22, 12:32 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class RemoveNthNodeFromEndOfList {
    //Leetcode 19
    //https://www.interviewbit.com/problems/remove-nth-node-from-list-end/
    //res/remove_node.jpeg
    /*Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:

If n is greater than the size of the list, remove the first node of the list.
Try doing it using constant additional space.
*/
    public ListNode removeNthFromEnd(ListNode A, int B) {
        if (A == null) return null;
        ListNode node = A;
        int len = 0;

        while (node != null) {
            len++;
            node = node.next;
        }

        if (B >= len) {
            //Remove first node
            return A.next;
        }

        node = A;
        int curr = 1;
        while (curr < len - B) {
            node = node.next;
            curr++;
        }

        if (node.next != null)
            node.next = node.next.next;
        return A;
    }


    ListNode result = null;
    // Optimized memory approach
    public ListNode removeNthFromEndEfficientMemory(ListNode head, int n) {
        int len = 0;
        if (head == null) return head;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        if (n > len) return head;
        temp = head;
        int index = len - n;
        int i = 0;
        while (i < index) {
            insertNode(temp.val);
            temp = temp.next;
            i++;
        }
        //Go to end
        ListNode first = result;
        if (first != null) {
            while (first.next != null) {
                first = first.next;
            }
            first.next = temp.next;
        } else {
            result = temp.next;
        }
        return result;
    }

    public void insertNode(int data) {
        ListNode tempNode = new ListNode(data);
        ListNode last = result;
        if (result == null) {
            result = tempNode;
            return;
        }
        while (last.next != null)
            last = last.next;
        last.next = tempNode;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList list = new RemoveNthNodeFromEndOfList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = list.removeNthFromEnd(head, 2);
        list.printList(res);
    }
}
