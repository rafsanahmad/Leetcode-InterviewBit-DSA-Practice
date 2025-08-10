/*
 * * N ary Tree Traversal.java
 *  * Created by Rafsan Ahmad on 11/26/21, 8:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class N_ary_TreeTraversal {
    //Leetcode 589, 590, 429
    //res/n_ary_tree.png
    /*Given the root of an n-ary tree, return the preorder,postorder,level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated
by the null value (See examples)

Example 1:
          1
       /  |   \
     3    2    4
   /   \
  5     6

Input: root = [1,null,3,2,4,null,5,6]
Output (Pre-order): [1,3,5,6,2,4]
Output (Post-order) : [5,6,3,2,4,1]
Output (Level-order) : [[1],[3,2,4],[5,6]]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output (Pre-order): [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
Output (Post-order): [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
Output (Level-order) ; [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
*/

    static class TreeNode {
        public int val;
        public List<TreeNode> children = new ArrayList<>();

        public TreeNode() {
        }

        public TreeNode(int _val) {
            val = _val;
        }

        public TreeNode(int _val, List<TreeNode> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;

        result.add(node.val); // Visit the node
        for (TreeNode child : node.children) {
            preorderHelper(child, result); // Recurse on children
        }
    }

    public List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;

        for (TreeNode child : node.children) {
            postorderHelper(child, result); // Recurse on children
        }

        result.add(node.val); // Visit the node
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> values = new ArrayList<>();

        if (root == null) {
            return values;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> temp = new ArrayList<>();

            while (n-- > 0) {
                TreeNode node = queue.remove();
                temp.add(node.val);
                List<TreeNode> childrens = node.children;
                queue.addAll(childrens);
            }

            values.add(temp);
        }

        return values;
    }

    public static void main(String[] args) {
        N_ary_TreeTraversal traversal = new N_ary_TreeTraversal();
        TreeNode root = new TreeNode(1);
        List<TreeNode> list = new ArrayList<>();
        list.add(new TreeNode(3));
        list.add(new TreeNode(2));
        list.add(new TreeNode(4));
        root.children = list;

        List<TreeNode> list2 = new ArrayList<>();
        list2.add(new TreeNode(5));
        list2.add(new TreeNode(6));
        root.children.get(0).children = list2;

        System.out.println(traversal.preorder(root));
        System.out.println(traversal.postorder(root));
        System.out.println(traversal.levelOrder(root));
    }
}
