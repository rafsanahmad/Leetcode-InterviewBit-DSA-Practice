package javaclasses.Tree;

public class MinDepthTree {
    //https://www.interviewbit.com/problems/min-depth-of-binary-tree/
    /*Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

NOTE : The path has to end on a leaf node.

Example 1:

         1
        /
       2
min depth = 2.

Example 2:
         1
        / \
       2    3
          /
        4
         \
          5

min depth = 3;
*/

    public int minDepth(TreeNode node) {
        return minDepthHelp(node) + 1;
    }

    public int minDepthHelp(TreeNode node) {
        if (node.left == null && node.right == null) return 1;
        int lDepth = Integer.MAX_VALUE;
        int rDepth = Integer.MAX_VALUE;
        if (node.left != null) lDepth = minDepthHelp(node.left) + 1;
        if (node.right != null) rDepth = minDepthHelp(node.right) + 1;
        return Math.min(lDepth, rDepth);
    }

    public static void main(String[] args) {
        MinDepthTree tree = new MinDepthTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.right = new TreeNode(5);
        System.out.println(tree.minDepth(root));
    }
}
