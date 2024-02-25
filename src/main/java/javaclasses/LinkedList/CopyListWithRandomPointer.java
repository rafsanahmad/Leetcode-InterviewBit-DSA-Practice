/*
 * *
 *  * Copy List With Random Pointer.java
 *  * Created by Rafsan Ahmad on 2/26/24, 1:52 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.LinkedList;

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointer {
    //https://leetcode.com/problems/copy-list-with-random-pointer/
    /*A linked list of length n is given such that each node contains an additional random
    pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
where each new node has its value set to the value of its corresponding original node.
Both the next and random pointer of the new nodes should point to new nodes in the copied list
such that the pointers in the original list and copied list represent the same list state.
None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y,
then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is
represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
or null if it does not point to any node.
Your code will only be given the head of the original linked list.


Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]


Constraints:
0 <= n <= 1000
-10^4 <= Node.val <= 10^4
Node.random is null or is pointing to some node in the linked list.*/

    public Node copyRandomList(Node head) {
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node temp = head;
        //first iteration for inserting deep nodes of every node in the hashmap.
        while (temp != null) {
            Node newNode = new Node(temp.val);
            hashMap.put(temp, newNode);
            temp = temp.next;
        }
        Node t = head;
        //second iteration for linking next and random pointer as given question.
        while (t != null) {
            Node node = hashMap.get(t);
            node.next = (t.next != null) ? hashMap.get(t.next) : null;
            node.random = (t.random != null) ? hashMap.get(t.random) : null;
            t = t.next;
        }
        return hashMap.get(head);
    }

    public Node copyRandomListMemoryOptimized(Node head) {
        if (head == null) return head;

        Node curr = head;

        //Step 1 - copy a list with new nodes
        while (curr != null) {
            Node newNode = new Node(curr.val);
            Node next = curr.next;
            curr.next = newNode;
            curr.next.next = next;
            curr = next;
        }

        curr = head;

        //Step 2: assign random pointer for new list
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        curr = head;

        //Step 3: Extract copy list
        Node result = new Node(0);
        Node runner = result;
        while (curr != null) {
            Node next = curr.next.next;

            //Extract
            Node temp = curr.next;
            runner.next = temp;
            runner = temp;

            //restore
            curr.next = next;

            curr = next;
        }

        return result.next;
    }
}
