package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTree {
    //res/unique_bst.jpeg
    /*Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n
    nodes of unique values from 1 to n. Return the answer in any order.

Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
Input: n = 1
Output: [[1]]*/
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTreeHelper(1, n);
    }

    public List<TreeNode> generateTreeHelper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtree = generateTreeHelper(start, i - 1);
            List<TreeNode> rightSubtree = generateTreeHelper(i + 1, end);
            for (TreeNode left : leftSubtree) {
                for (TreeNode right : rightSubtree) {
                    TreeNode current = new TreeNode(i);
                    current.left = left;
                    current.right = right;
                    result.add(current);
                }
            }
        }
        return result;
    }

    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        UniqueBinarySearchTree tree = new UniqueBinarySearchTree();
        List<TreeNode> res = tree.generateTrees(3);
        for (TreeNode node : res) {
            System.out.print("[");
            tree.preOrder(node);
            System.out.print("]");
        }
    }
}
