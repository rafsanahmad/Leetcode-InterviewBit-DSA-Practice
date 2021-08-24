package javaclasses.Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DfsGraph_AdjacencyList {

    /*res/dfs.png*/
    /*Time complexity =  O(V) + O(E) = O(V + E)
    * V is the number of nodes
    * E is the number of edges*/
    static class Node {
        int data;
        boolean visited;
        List<Node> neighbours;

        Node(int data) {
            this.data = data;
            this.neighbours = new ArrayList<>();
        }

        public void addNeighbours(Node node) {
            this.neighbours.add(node);
        }

        public List<Node> getNeighbours() {
            return neighbours;
        }

        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }
    }

    //Recursive DFS
    public void dfsRecursive(Node node) {
        System.out.print(node.data + "  ");
        List<Node> neighbours = node.getNeighbours();
        node.visited = true;

        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.visited) {
                dfsRecursive(n);
            }
        }
    }

    //Iterative DFS using Stack
    public void dfsUsingStack(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);

        while (!stack.isEmpty()) {
            Node element = stack.pop();
            if (!element.visited) {
                System.out.print(element.data + "  ");
                element.visited = true;
            }
            List<Node> neighbours = element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.add(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node node40 = new Node(40);
        Node node10 = new Node(10);
        Node node20 = new Node(20);
        Node node30 = new Node(30);
        Node node60 = new Node(60);
        Node node50 = new Node(50);
        Node node70 = new Node(70);

        node40.addNeighbours(node10);
        node40.addNeighbours(node20);
        node10.addNeighbours(node30);
        node20.addNeighbours(node10);
        node20.addNeighbours(node30);
        node20.addNeighbours(node60);
        node20.addNeighbours(node50);
        node30.addNeighbours(node60);
        node60.addNeighbours(node70);
        node50.addNeighbours(node70);

        DfsGraph_AdjacencyList dfsExample = new DfsGraph_AdjacencyList();

        System.out.println("The DFS traversal of the graph using stack ");
        dfsExample.dfsUsingStack(node40);

        System.out.println();

        // Resetting the visited flag for nodes
        node40.visited = false;
        node10.visited = false;
        node20.visited = false;
        node30.visited = false;
        node60.visited = false;
        node50.visited = false;
        node70.visited = false;

        System.out.println("The DFS traversal of the graph using recursion ");
        dfsExample.dfsRecursive(node40);
    }
}
