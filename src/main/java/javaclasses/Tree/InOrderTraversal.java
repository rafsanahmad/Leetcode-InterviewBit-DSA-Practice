package javaclasses.Tree;
import java.util.List;
import java.util.ArrayList;

public class InOrderTraversal {
    //Leetcode 93
    /*
    * Given the root of a binary tree, return the inorder traversal of its nodes' values.

     Example 1:
    Input: root = [1,null,2,3]
    Output: [1,3,2]
    Example 2:

    Input: root = []
    Output: []
    Example 3:

    Input: root = [1]
    Output: [1]*/

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            helper(root, result);
        }
        return result;
    }

    public void helper(TreeNode node, List<Integer> result) {
        //Traverse left
        if (node.left != null) {
            helper(node.left, result);
        }
        //Add root
        result.add(node.val);
        //Traverse right
        if (node.right != null) {
            helper(node.right, result);
        }
    }
}
