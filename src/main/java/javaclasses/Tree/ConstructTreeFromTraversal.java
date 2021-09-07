package javaclasses.Tree;

import java.util.HashMap;

public class ConstructTreeFromTraversal {
    //Leetcode 105
    //https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
    /*Let us consider the below traversals:
Inorder sequence: D B E A F C
Preorder sequence: A B D E C F

In a Preorder sequence, the leftmost element is the root of the tree. So we know ‘A’ is the root for given
sequences. By searching ‘A’ in the Inorder sequence, we can find out all elements on the left side of ‘A’ is in
the left subtree, and elements on right in the right subtree. So we know the below structure now.

                 A
               /   \
             /       \
           D B E     F C
We recursively follow the above steps and get the following tree.

         A
       /   \
     /       \
    B         C
   / \        /
 /     \    /
D       E  F
Algorithm: buildTree()
1) Pick an element from Preorder. Increment a Preorder Index Variable (preIndex in below code) to pick the next
element in the next recursive call.
2) Create a new tree node tNode with the data as the picked element.
3) Find the picked element’s index in Inorder. Let the index be inIndex.
4) Call buildTree for elements before inIndex and make the built tree as a left subtree of tNode.
5) Call buildTree for elements after inIndex and make the built tree as a right subtree of tNode.
6) return tNode.*/

    public static Node root;

    // Store indexes of all items so that we
    // we can quickly find later
    static HashMap<Character, Integer> mp = new HashMap<>();
    static int preIndex = 0;

    /* Recursive function to construct binary of size
      len from Inorder traversal in[] and Preorder traversal
      pre[]. Initial values of inStrt and inEnd should be
      0 and len -1. The function doesn't do any error
      checking for cases where inorder and preorder
      do not form a tree */
    public static Node buildTree(char[] in, char[] pre, int inStrt, int inEnd) {

        if (inStrt > inEnd) {
            return null;
        }

    /* Pick current node from Preorder traversal using preIndex
        and increment preIndex */
        char curr = pre[preIndex++];
        Node tNode;
        tNode = new Node(curr);

        /* If this node has no children then return */
        if (inStrt == inEnd) {
            return tNode;
        }

        /* Else find the index of this node in Inorder traversal */
        int inIndex = mp.get(curr);

    /* Using index in Inorder traversal, construct left and
        right subtress */
        tNode.left = buildTree(in, pre, inStrt, inIndex - 1);
        tNode.right = buildTree(in, pre, inIndex + 1, inEnd);
        return tNode;
    }

    // This function mainly creates an unordered_map, then
    // calls buildTree()
    public static Node buldTreeWrap(char[] in, char[] pre, int len) {
        for (int i = 0; i < len; i++) {
            mp.put(in[i], i);
        }
        return buildTree(in, pre, 0, len - 1);
    }

    /* This function is here just to test buildTree() */
    static void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    /* Driver code */
    public static void main(String[] args) {
        char[] in = {'D', 'B', 'E', 'A', 'F', 'C'};
        char[] pre = {'A', 'B', 'D', 'E', 'C', 'F'};
        int len = in.length;

        ConstructTreeFromTraversal.root = buldTreeWrap(in, pre, len);

    /* Let us test the built tree by printing
        Inorder traversal */
        System.out.println("Inorder traversal of the constructed tree is");
        printInorder(root);
    }
}
