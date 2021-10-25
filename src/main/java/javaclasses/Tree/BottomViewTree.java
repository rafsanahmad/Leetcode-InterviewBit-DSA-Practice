/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.TreeMap;

public class BottomViewTree {
    /*Given a Binary Tree, we need to print the bottom view from left to right. A node x is there in output if
    x is the bottommost node at its horizontal distance. Horizontal distance of left child of a node x is equal
    to horizontal distance of x minus 1, and that of right child is horizontal distance of x plus 1.

Examples:

                      20
                    /    \
                  8       22
                /   \      \
              5      3      25
                    / \
                  10    14
For the above tree the output should be 5, 10, 3, 14, 25.
If there are multiple bottom-most nodes for a horizontal distance from root, then print the later one in level
traversal. For example, in the below diagram, 3 and 4 are both the bottom-most nodes at horizontal distance 0,
we need to print 4.


                      20
                    /    \
                  8       22
                /   \    /   \
              5      3 4     25
                    / \
                  10    14
For the above tree the output should be 5, 10, 4, 14, 25.*/

    /*Approach:
Create a map like, map where key is the horizontal distance and value is a pair(a, b)
where a is the value of the node and b is the height of the node.
Perform a pre-order traversal of the tree.
If the current node at a horizontal distance of h is the first we’ve seen, insert it in the map.
Otherwise, compare the node with the existing one in map and if the height of the new node is greater,
update in the Map.
*/
    static void printBottomViewUtil(Node root, int curr, int hd,
                                    TreeMap<Integer, int[]> m) {

        // Base case
        if (root == null)
            return;

        // If node for a particular
        // horizontal distance is not
        // present, add to the map.
        if (!m.containsKey(hd)) {
            m.put(hd, new int[]{root.data, curr});
        }

        // Compare height for already
        // present node at similar horizontal
        // distance
        else {
            int[] p = m.get(hd);
            if (p[1] <= curr) {
                p[1] = curr;
                p[0] = root.data;
            }
            m.put(hd, p);
        }

        // Recur for left subtree
        printBottomViewUtil(root.left, curr + 1,
                hd - 1, m);

        // Recur for right subtree
        printBottomViewUtil(root.right, curr + 1,
                hd + 1, m);
    }

    static void printBottomView(Node root) {

        // Map to store Horizontal Distance,
        // Height and Data.
        TreeMap<Integer, int[]> m = new TreeMap<>();

        printBottomViewUtil(root, 0, 0, m);

        // Prints the values stored by printBottomViewUtil()
        for (int val[] : m.values()) {
            System.out.print(val[0] + " ");
        }
    }

    // Driver Code
    public static void main(String[] args) {
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(5);
        root.left.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(25);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);

        System.out.println(
                "Bottom view of the given binary tree:");

        printBottomView(root);
    }

}
