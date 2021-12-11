/*
 * * Max Width Binary Tree.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthBinaryTree {
    /*Given a binary tree, write a function to get the maximum width of the given tree. Width of a tree is
    maximum of widths of all levels.

Let us consider the below example tree.

         1
        /  \
       2    3
     /  \     \
    4    5     8
              /  \
             6    7
For the above tree,
width of level 1 is 1,
width of level 2 is 2,
width of level 3 is 3
width of level 4 is 2.
So the maximum width of the tree is 3.*/


    public int maxWidthBinaryTree(Node root) {
        //Base case
        if (root == null)
            return 0;

        int maxWidth = 0;
        //Do level order traversal keeping
        //track of number of nodes at every level
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            //Get the size of the queue
            int count = queue.size();
            //Update the maximum node count
            maxWidth = Math.max(maxWidth, count);

            //Iterate for all nodes in the queue
            while (count-- > 0) {
                Node temp = queue.poll();
                //Enqueue left and right children
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        MaxWidthBinaryTree tree = new MaxWidthBinaryTree();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(8);
        root.right.right.left = new Node(6);
        root.right.right.right = new Node(7);
        System.out.println(tree.maxWidthBinaryTree(root));
    }
}
