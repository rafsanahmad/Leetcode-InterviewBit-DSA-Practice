/*
 * *
 *  * Delete Node In BST.java
 *  * Created by Rafsan Ahmad on 2/26/24, 1:39 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Tree;

public class DeleteNodeInBST {
    //Leetcode: 450
    //https://leetcode.com/problems/delete-node-in-a-bst/description/
    /*Given a root node reference of a BST and a key, delete the node with the given key in
    the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.


Example 1:

         5                    5
       /   \                /   \
     3      6              4     6
   /   \     \             /      \
  2     4     7           2        7

Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]

Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

Example 2:
Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.

Example 3:
Input: root = [], key = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-10^5 <= Node.val <= 10^5
Each node has a unique value.
root is a valid binary search tree.
-10^5 <= key <= 10^5*/

    /*Three cases of deletion
When deleting a node in BST:
Node has no children → just remove it.
Node has one child → replace it with its child.
Node has two children → replace it with inorder successor (or predecessor), then delete that
successor from its original position.*/

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode minNode = findInorderMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findInorderMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void inorderTraverse(TreeNode root) {
        if (root != null) {
            inorderTraverse(root.left);
            System.out.print(root.val + " ");
            inorderTraverse(root.right);
        }
    }

    public static void main(String[] args) {
        DeleteNodeInBST bst = new DeleteNodeInBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        TreeNode node = bst.deleteNode(root, 3);
        bst.inorderTraverse(node);
    }
}
