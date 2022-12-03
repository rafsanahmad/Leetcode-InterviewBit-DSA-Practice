/*
 * *
 *  * Check If A Binary Tree Is Balanced.java
 *  * Created by Rafsan Ahmad on 12/3/22, 10:57 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class Check_If_TreeBalanced {
    /*A height balanced binary tree is a binary tree in which the height of the left subtree and right subtree of any node
    does not differ by more than 1 and both the left and right subtree are also height balanced.

    Steps:
    For each node make two recursion calls – one for left subtree and the other for the right subtree.
    Based on the heights returned from the recursion calls, decide if the subtree whose root is the current node is
    height-balanced or not.
    If it is balanced then return the height of that subtree. Otherwise, return -1 to denote that the subtree is not
    height-balanced.
    */

    /*Time Complexity: O(n)
    Because we are only one dfs call and utilizing the height returned from that to determine the height balance,
    it is performing the task in linear time.
    Auxiliary Space: O(n)
    */
    public static int isBalanced(Node root) {
        if (root == null)
            return 0;
        int lh = isBalanced(root.left);
        if (lh == -1)
            return -1;
        int rh = isBalanced(root.right);
        if (rh == -1)
            return -1;

        if (Math.abs(lh - rh) > 1)
            return -1;
        else
            return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(30);
        root.right.left = new Node(15);
        root.right.right = new Node(20);

        if (isBalanced(root) > 0)
            System.out.print("Balanced");
        else
            System.out.print("Not Balanced");
    }
}
