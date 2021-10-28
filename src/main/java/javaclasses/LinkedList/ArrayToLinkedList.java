/*
 * *
 *  * Created by Rafsan Ahmad on 10/28/21, 4:19 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class ArrayToLinkedList {
    /*Given an array arr[] of size N. The task is to create linked list from the given array.
Examples:


Input : arr[]={1, 2, 3, 4, 5}
Output : 1->2->3->4->5

Input :arr[]={10, 11, 12, 13, 14}
Output : 10->11->12->13->14*/

    public ListNode insertNode(ListNode root, int data) {
        ListNode tempNode = new ListNode(data);
        tempNode.next = root;
        root = tempNode;
        return root;
    }

    public ListNode array2List(int[] arr) {
        ListNode root = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            root = insertNode(root, arr[i]);
        }
        return root;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ArrayToLinkedList list = new ArrayToLinkedList();
        int[] arr = {1, 2, 3, 4, 5};
        ListNode node = list.array2List(arr);
        list.printList(node);
    }
}
