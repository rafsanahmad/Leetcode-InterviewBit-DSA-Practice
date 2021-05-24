package javaclasses.LinkedList;

public class ReverserLinkList_II {
    //Leecode 92
    /*
    Given the head of a singly linked list and two integers left and right where left <= right,
    reverse the nodes of the list from position left to position right, and return the reversed list.

    Input: head = [1,2,3,4,5], left = 2, right = 4
    Output: [1,4,3,2,5]
    */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        //Base case
        if (head == null) return null;

        //Move the two pointers until they reach the
        //proper starting point
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        //Two pointers that will fix the final connections
        ListNode con = prev, tail = cur;

        //Iteratively reverse the nodes until n
        //becomes 0
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        //Adjust the final connections
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }
}
