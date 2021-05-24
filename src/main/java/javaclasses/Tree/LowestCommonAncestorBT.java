package javaclasses.Tree;

public class LowestCommonAncestorBT {

    //Lowest Common Ancestor
    //Leetcode 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        //Use post order traversal to find LCA
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if(leftNode != null && rightNode != null) {
            return root;
        }
        if(leftNode == null) {
            return rightNode;
        } else {
            return leftNode;
        }
    }
}
