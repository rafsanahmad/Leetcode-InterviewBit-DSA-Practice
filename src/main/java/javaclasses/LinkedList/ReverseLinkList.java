/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class ReverseLinkList {
    /*Given pointer to the head node of a linked list, the task is to reverse the linked list.
    We need to reverse the list by changing the links between nodes.

    Examples:

    Input: Head of following linked list
    1->2->3->4->NULL
    Output: Linked list should be changed to,
    4->3->2->1->NULL

    Input: Head of following linked list
    1->2->3->4->5->NULL
    Output: Linked list should be changed to,
    5->4->3->2->1->NULL

    Input: NULL
    Output: NULL
    */

    //Iterative Method

    //Initialize three pointers prev as NULL, curr as head and next as NULL.
    //Iterate through the linked list. In loop, do following.
    // Before changing next of current,
    // store next node
    // next = curr->next
    // Now change next of current
    // This is where actual reversing happens
    //curr->next = prev
    // Move prev and curr one step forward
    //prev = curr
    //curr = next */

    static Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

    void printList(Node node) {
        while(node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ReverseLinkList list = new ReverseLinkList();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);

        System.out.println("Given Linked list");
        list.printList(head);
        head = list.reverse(head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList(head);
    }
}
