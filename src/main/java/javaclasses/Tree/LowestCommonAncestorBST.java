package javaclasses.Tree;

//Lowest Common Ancestor
//Leetcode 235
public class LowestCommonAncestorBST {

/*Algorithm

Start traversing the tree from the root node.
If both the nodes p and q are in the right subtree,
then continue the search with right subtree starting step 1.
If both the nodes p and q are in the left subtree,
then continue the search with left subtree starting step 1.
If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's subtrees.
and hence we return this common node as the LCA.
*/

    //Iterative Approach
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        int pVal = p.val;
        int qVal = q.val;
        while (root != null) {
            if (pVal < root.val && qVal < root.val) {
                //left subtree
                root = root.left;
            } else if (pVal > root.val && qVal > root.val) {
                //right subtree
                root = root.right;
            } else {
                //found LCA
                return root;
            }
        }
        return null;
    }

    //Recursive Approach
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }

}
