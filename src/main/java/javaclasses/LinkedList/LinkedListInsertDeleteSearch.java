/*
 * *
 *  * LinkedList Insert Delete Search.java
 *  * Created by Rafsan Ahmad on 12/31/21, 12:03 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class LinkedListInsertDeleteSearch {

    ListNode head; //Head of the list

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

    /* Inserts a new node after the given prev_node. */
    public void insertAfter(ListNode prev_node, int new_data) {
        /* 1. Check if the given Node is null */
        if (prev_node == null) {
            System.out.println("The given previous node cannot be null");
            return;
        }

        /* 2 & 3: Allocate the Node &
                  Put in the data*/
        ListNode new_node = new ListNode(new_data);

        /* 4. Make next of new Node as next of prev_node */
        new_node.next = prev_node.next;

        /* 5. make next of prev_node as new_node */
        prev_node.next = new_node;
    }

    /* Appends a new node at the end.  This method is
       defined inside LinkedList class shown above */
    public void append(int new_data) {
        /* 1. Allocate the Node &
           2. Put in the data
           3. Set next as null */
        ListNode new_node = new ListNode(new_data);

        /* 4. If the Linked List is empty, then make the
              new node as head */
        if (head == null) {
            head = new ListNode(new_data);
            return;
        }

        /* 4. This new node is going to be the last node, so
              make next of it as null */
        new_node.next = null;

        /* 5. Else traverse till the last node */
        ListNode last = head;
        while (last.next != null)
            last = last.next;

        /* 6. Change the next of last node */
        last.next = new_node;
    }


    /* Given a key, deletes the first
       occurrence of key in
     * linked list */
    void deleteNode(int key) {
        // Store head node
        ListNode temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.val == key) {
            head = temp.next; // Changed head
            return;
        }

        // Search for the key to be deleted, keep track of
        // the previous node as we need to change temp.next
        while (temp != null && temp.val != key) {
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if (temp == null)
            return;

        // Unlink the node from linked list
        prev.next = temp.next;
    }


    /* Given a position, deletes the node at the given
       position */
    void deleteNodeAtPosition(int position) {
        // If linked list is empty
        if (head == null)
            return;

        // Store head node
        ListNode temp = head;

        // If head needs to be removed
        if (position == 0) {
            head = temp.next; // Change head
            return;
        }

        // Find previous node of the node to be deleted
        for (int i = 0; temp != null && i < position - 1;
             i++)
            temp = temp.next;

        // If position is more than number of nodes
        if (temp == null || temp.next == null)
            return;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        ListNode next = temp.next.next;

        temp.next = next; // Unlink the deleted node from list
    }

    /* This function prints contents of linked list starting from
        the given node */
    public void printList() {
        ListNode tnode = head;
        while (tnode != null) {
            System.out.print(tnode.val + " ");
            tnode = tnode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /* Start with the empty list */
        LinkedListInsertDeleteSearch llist = new LinkedListInsertDeleteSearch();

        // Insert 6.  So linked list becomes 6->NUllist
        llist.append(6);
        System.out.println("Append 6");
        llist.printList();

        // Insert 7 at the beginning. So linked list becomes
        // 7->6->NUllist
        llist.push(7);
        System.out.println("Push 7");
        llist.printList();

        // Insert 1 at the beginning. So linked list becomes
        // 1->7->6->NUllist
        llist.push(1);
        System.out.println("Push 1");
        llist.printList();

        // Insert 4 at the end. So linked list becomes
        // 1->7->6->4->NUllist
        llist.append(4);
        System.out.println("Append 4");
        llist.printList();

        // Insert 8, after 7. So linked list becomes
        // 1->7->8->6->4->NUll
        llist.insertAfter(llist.head.next, 8);
        System.out.println("Insert 8 after 7");
        llist.printList();

        //Delete node
        // 1->8->6->4->NUll
        llist.deleteNode(7);
        System.out.println("Delete node 7");
        llist.printList();

        //Delete node at position 2
        // 1->8->4->NUll
        llist.deleteNodeAtPosition(2);
        System.out.println("Delete node at position 2");
        llist.printList();

        System.out.println("Final List:");
        llist.printList();
    }
}
