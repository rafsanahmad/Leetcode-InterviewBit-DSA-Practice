/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

public class OddEvenLinkList {
    //Leetcode 328
    /*
    Given the head of a singly linked list, group all the nodes with odd indices together
    followed by the nodes with even indices, and return the reordered list.
    The first node is considered odd, and the second node is even, and so on.
    Note that the relative order inside both the even and odd groups should remain as it was in the input
    Input: head = [1,2,3,4,5]
    Output: [1,3,5,2,4]
    */

    public ListNode oddEvenList(ListNode head) {
        if(head == null) return null;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while(even !=null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
