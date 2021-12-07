/*
 * * Add Two Numbers.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.LinkedList;

//Leetcode 2
/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

* */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(0);
        ListNode tmp = resultNode;
        if (l1 == null && l2 == null) {
            return null;
        }
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val1 = 0, val2 = 0;
            if (l1 != null) {
                val1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val2 = l2.val;
                l2 = l2.next;
            }
            int sum = val1 + val2 + carry;
            tmp.next = new ListNode(sum % 10);
            if (sum / 10 > 0) {
                carry = 1;
            } else {
                carry = 0;
            }
            tmp = tmp.next;
        }
        if (carry == 1) {
            tmp.next = new ListNode(1);
        }
        //tmp.next = null;
        return resultNode.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();

        // creating first linked list
        list1 = new ListNode(2);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(3);

        // creating second linked list
        list2 = new ListNode(5);
        list2.next = new ListNode(6);
        list2.next.next = new ListNode(4);

        AddTwoNumbers numbers = new AddTwoNumbers();
        ListNode result = numbers.addTwoNumbers(list1, list2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
