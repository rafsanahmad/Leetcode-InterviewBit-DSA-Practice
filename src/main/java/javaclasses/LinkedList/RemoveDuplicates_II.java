package javaclasses.LinkedList;

import java.util.HashSet;

public class RemoveDuplicates_II {
    //Leetcode 82
    /*Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
    leaving only distinct numbers from the original list. Return the linked list sorted as well.
    Input: head = [1,2,3,3,4,4,5]
    Output: [1,2,5]
    */

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode curr = head;
        while (curr != null) {
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }
            if (pre.next == curr) {
                pre = pre.next;
            } else {
                pre.next = curr.next;
            }
            curr = curr.next;
        }
        return fakeHead.next;
    }

    //Using Sentinel
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;

        //sentinel
        ListNode sentinel = new ListNode(0, head);

        //Predecessor = the last node
        //before the siblist of duplicates
        ListNode pred = sentinel;
        while (head != null) {
            //Check for duplicate sublist
            if (head.next != null && head.val == head.next.val) {
                //move til the end of duplicates
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                //skip all duplicates
                pred.next = head.next;
            } else {
                //otherwise move predecessor
                pred = pred.next;
            }
            head = head.next;
        }
        return sentinel.next;
    }
}
