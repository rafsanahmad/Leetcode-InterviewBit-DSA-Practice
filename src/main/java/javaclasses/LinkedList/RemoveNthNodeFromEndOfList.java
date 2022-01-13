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
    //res/remove_node.jpeg
    /*Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]


Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]
*/
    ListNode result = null;

    public ListNode removeNthFromEnd(ListNode head, int n) {
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
