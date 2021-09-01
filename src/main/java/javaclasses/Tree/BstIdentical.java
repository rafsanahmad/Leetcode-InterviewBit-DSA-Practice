package javaclasses.Tree;

public class BstIdentical {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }


    static int isIdentical(Node root1, Node root2) {
        //check if both trees are empty
        if (root1 == null && root2 == null)
            return 1;

            //If any one of the tree is non empty & other is empty
        else if (root1 != null && root2 == null) {
            return 0;
        } else if (root1 == null && root2 != null) {
            return 0;
        } else {
            //Check if current data of both tree are same & recursively iterate both the node
            if (root1.data == root2.data && isIdentical(root1.left, root2.left) == 1 &&
                    isIdentical(root1.right, root2.right) == 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }


    public static void main(String[] args) {
        Node root1 = new Node(5);
        root1.left = new Node(3);
        root1.right = new Node(8);
        root1.left.left = new Node(3);
        root1.left.right = new Node(4);

        Node root2 = new Node(5);
        root2.left = new Node(3);
        root2.right = new Node(8);
        root2.left.left = new Node(2);
        root2.left.right = new Node(4);

        if (isIdentical(root1, root2) == 1) {
            System.out.print("BST are equal");
        } else {
            System.out.print("BST are not equal");
        }
    }

}
