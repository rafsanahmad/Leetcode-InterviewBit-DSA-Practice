package javaclasses.Tree;

import java.util.Arrays;
import java.util.Stack;

public class Lowest_Common_Ancestor {
    // Binary tree node
    public static class BstNode {
        int value;
        BstNode left;
        BstNode right;

        BstNode(int x) {
            value = x;
        }
    }

    public static String BinarySearchTreeLCA(String[] strArr) {
        // code goes here
        //Construct bst with pre-order traversal
        if (strArr.length < 3) return "";

        String nodes = strArr[0];
        int min = Integer.parseInt(strArr[1]);
        int max = Integer.parseInt(strArr[2]);

        //Convert "[1,2,3,4]" to int array
        int[] intArr = Arrays.stream(nodes.substring(1, nodes.length() - 1).split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();

        BstNode root = bstConstruct(intArr);
        //Got Bst root - Find LCA between min & max
        BstNode LCA = findLCA(root, min, max);
        return String.valueOf(LCA.value);
    }

    public static BstNode bstConstruct(int[] intArr) {
        int n = intArr.length;
        if (n == 0) return null;

        Stack<BstNode> stack = new Stack<>();
        BstNode root = new BstNode(intArr[0]);
        stack.push(root);

        //loop on stack
        for (int i = 1; i < n; i++) {
            BstNode node = stack.peek();
            while (!stack.isEmpty() && stack.peek().value < intArr[i]) {
                node = stack.pop();
            }
            BstNode child = new BstNode(intArr[i]);
            if (node.value < intArr[i]) {
                node.right = child;
            } else {
                node.left = child;
            }
            stack.push(child);
        }
        return root;
    }

    public static BstNode findLCA(BstNode root, int min, int max) {
        BstNode node = root;
        while (node != null) {
            int parent = node.value;
            if (min > parent && max > parent) {
                //both are greater than parent
                node = node.right;
            } else if (min < parent && max < parent) {
                node = node.left;
            } else {
                //got possible LCA
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // keep this function call here
        //Scanner s = new Scanner(System.in);
        //System.out.print(BinarySearchTreeLCA(s.nextLine()));
        String[] array = new String[]{"232", "22", "12", "3"};
        BinarySearchTreeLCA(array);
    }
}
