/*
 * *
 *  * Palindrome List.java
 *  * Created by Rafsan Ahmad on 11/28/22, 6:33 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class PalindromeList {
    //https://www.interviewbit.com/problems/palindrome-list/
    /*Given a singly linked list, determine if its a palindrome. Return 1 or 0 denoting if its a palindrome or not,
     respectively.

Notes:

Expected solution is linear in time and constant in space.
For example,

List 1-->2-->1 is a palindrome.
List 1-->2-->3 is not a palindrome.*/

    public int palindromeList(ListNode A) {
        if (A == null) return 0;
        ListNode head = A;
        ListNode tail = null;

        while (A != null) {
            ListNode temp = tail;
            tail = new ListNode(A.val);
            tail.next = temp;
            A = A.next;
        }

        while (head != null && tail != null) {
            if (head.val != tail.val) return 0;
            head = head.next;
            tail = tail.next;
        }

        return 1;
    }

    public static void main(String[] args) {
        PalindromeList list = new PalindromeList();
        ListNode node = new ListNode(10);
        node.next = new ListNode(7);
        node.next.next = new ListNode(10);
        System.out.println(list.palindromeList(node));

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        node2.next.next = new ListNode(1);
        node2.next.next.next = new ListNode(2);
        System.out.println(list.palindromeList(node2));
    }
}
